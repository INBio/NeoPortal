/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.index;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.dao.DataProviderDAO;
import org.inbio.neoportal.core.dao.GenericBaseDAO;
import org.inbio.neoportal.core.dao.ImportDwcDAO;
import org.inbio.neoportal.core.dao.LocationDAO;
import org.inbio.neoportal.core.dao.OccurrenceNewDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.TaxonNewDAO;
import org.inbio.neoportal.core.entity.DataProvider;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.GeoFeatureId;
import org.inbio.neoportal.core.entity.GeoLayer;
import org.inbio.neoportal.core.entity.ImportDwc;
import org.inbio.neoportal.core.entity.Location;
import org.inbio.neoportal.core.entity.OccurrenceDwc;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonDescription;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import au.com.bytecode.opencsv.CSVReader;

/**
 *
 * @author avargas
 */
@Service
public class Importer {

    private static final int BATCH_SIZE = 30;

	static int maxResults = 1000;
    
    @Autowired
    private HibernateTransactionManager transactionManager;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ImportDwcDAO importDwcDAO;
    
    @Autowired
    @Qualifier("genericBaseDAOImpl")
    private GenericBaseDAO genericBaseDAO;
    
    @Autowired
    private DataProviderDAO dataProviderDAO;
        
    @Autowired
    private OccurrenceNewDAO occurrenceDAO;
    
    @Autowired
    private TaxonDAO taxonDAO;
    
    @Autowired
    private TaxonNewDAO taxonNewDAO;
    
    @Autowired
    private LocationDAO locationDAO;
    
    public Importer() {
        super();
    }
    
    public void importAll(
            String geoLayerFile, 
            String geoFeatureFile, 
            String locationFeatureFile,
            String taxonDescriptionFile){
        indexOccurrences();
    //    importGeoLayers(geoLayerFile);
    //    importGeoFeatures(geoFeatureFile);
    //    importLocationFeatures(locationFeatureFile);
    //    importTaxonDescription(taxonDescriptionFile);
    }

    /**
     * Import DarwinCore from csv file to importDwc table
     * and then update and create occurrences from importDwc to OccurrencesDwc table
     * @param csvFile
     */
    public void importIndexOccurrences(String csvFile){
      this.importDwcOccurrences(csvFile);
      this.indexOccurrences();
    }
    
    /**
     * 
     */
    @Transactional
    public void indexOccurrences(){
      
      org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Importer.class.getName());
      
      Session session = sessionFactory.getCurrentSession();
//      FullTextSession fullTextSession = Search.getFullTextSession(session);

      // config session for bash job
      session.setFlushMode(FlushMode.MANUAL);
//      fullTextSession.setFlushMode(FlushMode.MANUAL);

    	logger.log(org.apache.log4j.Level.DEBUG, "Starting importOccurrences process");
        
        //get current date for dateLastModified field
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateLastModified = dateFormat.format(date);
        
        DateFormat sourceDateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
        
        int firstResult = 0;
        int setCounter = 0; //every 100 (the jdbc batch size) call flush 
        
        DataProvider dp = dataProviderDAO.findAll().get(0);
        
        logger.log(org.apache.log4j.Level.DEBUG, "importOccurrences Begin Transaction");
        
        ScrollableResults scroll = session.createCriteria(ImportDwc.class)
        		.setFetchSize(BATCH_SIZE)
        		.setCacheMode(CacheMode.IGNORE)
        		.setReadOnly(true)
        		.scroll(ScrollMode.FORWARD_ONLY);
        
        boolean update;
        
        int batch = 0;
        int rowsCounter = 0;
        
        while(scroll.next()){
        	batch++;
        	rowsCounter++;
        	    
            ImportDwc importDwc = (ImportDwc) scroll.get(0);
            logger.trace("ImportDwc after scroll.get");
            
                try{
                
                //avoid repeated occurrenceId
                OccurrenceDwc occurrence = occurrenceDAO.findByCatalogNumber(
                        importDwc.getCatalogNumber().replace("\"", ""));
                logger.trace("OccurrenceDwc after findByCatalogNumber " + importDwc.getCatalogNumber().replace("\"", ""));
                
                if(occurrence != null){
//                    Logger.getLogger(Importer.class.getName()).log
//                        (Level.INFO, "OccurrenceId {0} already exist!", 
//                                new BigDecimal(importDwc.getCatalogNumber().replace("\"", "")));
            
                    update = true;
//                    continue;
                }
                else {
                	update = false;
                    occurrence = new OccurrenceDwc();
                }
                
//                OccurrenceDwc occurrence = new OccurrenceDwc();
                
               Taxon taxon = null;
               //check if taxonId is empty (unidentify specimens)
               if(importDwc.getTaxonId().isEmpty()){
                   taxon = null;
               }
               else{
                 // check if is the same taxon already associated with the occurrence
                 if(update && occurrence.getTaxonId().equals(importDwc.getTaxonId().replace("\"", ""))){
                   taxon = occurrence.getTaxon();
                   logger.trace("Occurrence update with same taxon");
                 }
                 else{
                 
            	   // find taxon entity
            	   // taxon = taxonNewDAO.findById(new BigDecimal(importDwc.getTaxonId().replace("\"", "")));
            	   List<Taxon> taxonList = taxonDAO.findByDefaultName(importDwc.getScientificName());
            	   logger.trace("Taxon after findByDefaultName");
            	   if (taxonList.size() == 1)
            		   taxon = taxonList.get(0);
            	   else if(taxonList.size() > 1) {
            		   for (Taxon taxon2 : taxonList) {
            			   if (taxon2.getKingdom().equals(importDwc.getKingdom())) {
            				   taxon = taxon2;
            				   break;
            			   }
            		   }
            	   }
                 }
               }

                // TODO: fix, use specimenId instead
               occurrence.setOccurrenceId(importDwc.getCatalogNumber().replace("\"", ""));
                       
               occurrence.setDataProvider(dp);
               occurrence.setTaxon(taxon);
               
             //find or create location
               Location location = locationDAO.findById(new BigDecimal(importDwc.getLocationId()));
               logger.trace("Location after findById");
//            		   (Location)
//                       genericBaseDAO.findById(
//                           Location.class, 
//                           new BigDecimal(importDwc.getLocationId()));

               if(location == null){
                   location = new Location(
                                   new BigDecimal(importDwc.getLocationId()));
                   location.setHigherGeographyId(importDwc.getHigherGeographyId());
                   location.setHigherGeography(importDwc.getHigherGeography());
                   location.setContinent(importDwc.getContinent());
                   location.setWaterBody(importDwc.getWaterBody());
                   location.setIslandGroup(importDwc.getIslandGroup());
                   location.setIsland(importDwc.getIsland());
                   location.setCountry(importDwc.getCountry());
                   location.setCountryCode(importDwc.getCountryCode());
                   location.setStateProvince(importDwc.getStateProvince());
                   location.setCounty(importDwc.getCounty());
                   location.setMunicipality(importDwc.getMunicipality());
                   location.setLocality(importDwc.getLocality());
                   location.setVerbatimLocality(importDwc.getVerbatimLocality());
                   location.setVerbatimElevation(importDwc.getVerbatimElevation());
                   location.setMinimumElevationInMeters(importDwc.getMinimumElevationInMeters());
                   location.setMaximumElevationInMeters(importDwc.getMaximumElevationInMeters());
                   location.setVerbatimDepth(importDwc.getVerbatimDepth());
                   location.setMinimumDepthInMeters(importDwc.getMinimumDepthInMeters());
                   location.setMaximumDepthInMeters(importDwc.getMaximumDepthInMeters());
                   location.setMinimumDistanceAboveSurfaceInMeters(importDwc.getMinimumDistanceAboveSurfaceInMeters());
                   location.setMaximumDistanceAboveSurfaceInMeters(importDwc.getMaximumDistanceAboveSurfaceInMeters());
                   location.setLocationAccordingTo(importDwc.getLocationAccordingTo());
                   location.setLocationRemarks(importDwc.getLocationRemarks());
                   location.setVerbatimCoordinates(importDwc.getVerbatimCoordinates());
                   location.setVerbatimLatitude(importDwc.getVerbatimLatitude());
                   location.setVerbatimLongitude(importDwc.getVerbatimLongitude());
                   location.setVerbatimCoordinateSystem(importDwc.getVerbatimCoordinateSystem());
                   location.setVerbatimSRS(importDwc.getVerbatimSRS());
                   location.setDecimalLatitude(Double.valueOf(importDwc.getDecimalLatitude()));
                   location.setDecimalLongitude(Double.valueOf(importDwc.getDecimalLongitude()));
                   location.setGeodeticDatum(importDwc.getGeodeticDatum());
                   location.setCoordinateUncertaintyInMeters(importDwc.getCoordinateUncertaintyInMeters());
                   location.setCoordinatePrecision(importDwc.getCoordinatePrecision());
                   location.setPointRadiusSpatialFit(importDwc.getPointRadiusSpatialFit());
                   location.setFootprintWKT(importDwc.getFootprintWKT());
                   location.setFootprintSRS(importDwc.getFootprintSRS());
                   location.setFootprintSpatialFit(importDwc.getFootprintSpatialFit());
                   location.setGeoreferencedBy(importDwc.getGeoreferencedBy());
                   location.setGeoreferencedDate(importDwc.getGeoreferencedDate());
                   location.setGeoreferenceProtocol(importDwc.getGeoreferenceProtocol());
                   location.setGeoreferenceSources(importDwc.getGeoreferenceSources());
                   location.setGeoreferenceVerificationStatus(importDwc.getGeoreferenceVerificationStatus());
                   location.setGeoreferenceRemarks(importDwc.getGeoreferenceRemarks());
                   
                   locationDAO.create(location);
                   // increment batch because location should be inserted
                   batch++;
               }
               occurrence.setLocation(location);
               
               occurrence.setType(importDwc.getType());
               occurrence.setModified(importDwc.getModified());
               occurrence.setLanguage(importDwc.getLanguage());
               occurrence.setRights(importDwc.getRights());
               occurrence.setRightsHolder(importDwc.getRightsHolder());
               occurrence.setAccessRights(importDwc.getAccessRights());
               occurrence.setBibliographicCitation(importDwc.getBibliographicCitation());
               occurrence.setReferences(importDwc.getReferences());
               occurrence.setInstitutionId(importDwc.getInstitutionId());
               occurrence.setCollectionId(importDwc.getCollectionId());
               occurrence.setDatasetId(importDwc.getDatasetId());
               occurrence.setInstitutionCode(importDwc.getInstitutionCode());
               occurrence.setCollectionCode(importDwc.getCollectionCode());
               occurrence.setDatasetName(importDwc.getDatasetName());
               occurrence.setOwnerInstitutionCode(importDwc.getOwnerInstitutionCode());
               occurrence.setBasisOfRecord(importDwc.getBasisOfRecord());
               occurrence.setInformationWithheld(importDwc.getInformationWithheld());
               occurrence.setDataGeneralizations(importDwc.getDataGeneralizations());
               occurrence.setDynamicProperties(importDwc.getDynamicProperties());
               
       	   	   occurrence.setOccurrenceId(importDwc.getOccurrenceId().toString());
               occurrence.setCatalogNumber(importDwc.getCatalogNumber());
               occurrence.setOccurrenceRemarks(importDwc.getOccurrenceRemarks());
               occurrence.setRecordNumber(importDwc.getRecordNumber());
               occurrence.setRecordedBy(importDwc.getRecordedBy());
               occurrence.setIndividualId(importDwc.getIndividualId());
               occurrence.setIndividualCount(importDwc.getIndividualCount());
               occurrence.setSex(importDwc.getSex());
               occurrence.setLifeStage(importDwc.getLifeStage());
               occurrence.setReproductiveCondition(importDwc.getReproductiveCondition());
               occurrence.setBehavior(importDwc.getBehavior());
               occurrence.setEstablishmentMeans(importDwc.getEstablishmentMeans());
               occurrence.setOccurrenceStatus(importDwc.getOccurrenceStatus());
               occurrence.setPreparations(importDwc.getPreparations());
               occurrence.setDisposition(importDwc.getDisposition());
               occurrence.setOtherCatalogNumbers(importDwc.getOtherCatalogNumbers());
               occurrence.setPreviousIdentifications(importDwc.getPreviousIdentifications());
               occurrence.setAssociatedMedia(importDwc.getAssociatedMedia());
               occurrence.setAssociatedReferences(importDwc.getAssociatedReferences());
               occurrence.setAssociatedOccurrences(importDwc.getAssociatedOccurrences());
               occurrence.setAssociatedSequences(importDwc.getAssociatedSequences());
               occurrence.setAssociatedTaxa(importDwc.getAssociatedTaxa());
               
               occurrence.setEventId(importDwc.getEventId());
               occurrence.setSamplingProtocol(importDwc.getSamplingProtocol());
               occurrence.setSamplingEffort(importDwc.getSamplingEffort());
               occurrence.setEventDate(importDwc.getEventDate());
               occurrence.setEventTime(importDwc.getEventTime());
               occurrence.setStartDayOfYear(importDwc.getStartDayOfYear());
               occurrence.setEndDayOfYear(importDwc.getEndDayOfYear());
               occurrence.setYear(importDwc.getYear());
               occurrence.setMonth(importDwc.getMonth());
               occurrence.setDay(importDwc.getDay());
               occurrence.setVerbatimEventDate(importDwc.getVerbatimEventDate());
               occurrence.setHabitat(importDwc.getHabitat());
               occurrence.setFieldNotes(importDwc.getFieldNumber());
               occurrence.setFieldNotes(importDwc.getFieldNotes());
               occurrence.setEventRemarks(importDwc.getEventRemarks());
               
               occurrence.setGeologicalContextId(importDwc.getGeologicalContextId());
               occurrence.setEarliestEonOrLowestEonothem(importDwc.getEarliestEonOrLowestEonothem());
               occurrence.setLatestEonOrHighestEonothem(importDwc.getLatestEonOrHighestEonothem());
               occurrence.setEarliestEraOrLowestErathem(importDwc.getEarliestEraOrLowestErathem());
               occurrence.setLatestEraOrHighestErathem(importDwc.getLatestEraOrHighestErathem());
               occurrence.setEarliestPeriodOrLowestSystem(importDwc.getEarliestPeriodOrLowestSystem());
               occurrence.setLatestPeriodOrHighestSystem(importDwc.getLatestPeriodOrHighestSystem());
               occurrence.setEarliestEpochOrLowestSeries(importDwc.getEarliestEpochOrLowestSeries());
               occurrence.setLatestEpochOrHighestSeries(importDwc.getLatestEpochOrHighestSeries());
               occurrence.setEarliestAgeOrLowestStage(importDwc.getEarliestAgeOrLowestStage());
               occurrence.setLatestAgeOrHighestStage(importDwc.getLatestAgeOrHighestStage());
               occurrence.setLowestBiostratigraphicZone(importDwc.getLowestBiostratigraphicZone());
               occurrence.setHighestBiostratigraphicZone(importDwc.getHighestBiostratigraphicZone());
               occurrence.setLithostratigraphicTerms(importDwc.getLithostratigraphicTerms());
               occurrence.setGroup(importDwc.getGroup());
               occurrence.setFormation(importDwc.getFormation());
               occurrence.setMember(importDwc.getMember());
               occurrence.setBed(importDwc.getBed());
               
               occurrence.setIdentificationId(importDwc.getIdentificationId());
               occurrence.setIdentifiedBy(importDwc.getIdentifiedBy());
               if(importDwc.getDateIdentified() != null && importDwc.getDateIdentified().length() > 0)
            	   occurrence.setDateIdentified(sourceDateFormat.parse(importDwc.getDateIdentified()));
               occurrence.setIdentificationReferences(importDwc.getIdentificationReferences());
               occurrence.setIdentificationVerificationStatus(importDwc.getIdentificationVerificationStatus());
               occurrence.setIdentificationRemarks(importDwc.getIdentificationRemarks());
               occurrence.setIdentificationQualifier(importDwc.getIdentificationQualifier());
               occurrence.setTypeStatus(importDwc.getTypeStatus());
               
               occurrence.setTaxonId(importDwc.getTaxonId());
               occurrence.setScientificNameId(importDwc.getScientificNameId());
               occurrence.setAcceptedNameUsageId(importDwc.getAcceptedNameUsageId());
               occurrence.setParentNameUsageId(importDwc.getParentNameUsageId());
               occurrence.setOriginalNameUsageId(importDwc.getOriginalNameUsageId());
               occurrence.setNameAccordingToId(importDwc.getNameAccordingToId());
               occurrence.setNamePublishedInId(importDwc.getNamePublishedInId());
               occurrence.setTaxonConceptId(importDwc.getTaxonConceptId());
               occurrence.setScientificName(importDwc.getScientificName());
               occurrence.setAcceptedNameUsage(importDwc.getAcceptedNameUsage());
               occurrence.setParentNameUsage(importDwc.getParentNameUsage());
               occurrence.setOriginalNameUsage(importDwc.getOriginalNameUsage());
               occurrence.setNameAccordingTo(importDwc.getNameAccordingTo());
               occurrence.setNamePublishedIn(importDwc.getNamePublishedIn());
               occurrence.setNamePublishedInYear(importDwc.getNamePublishedInYear());
               occurrence.setHigherClassification(importDwc.getHigherClassification());
               occurrence.setKingdom(importDwc.getKingdom());
               occurrence.setPhylum(importDwc.getPhylum());
               occurrence.setClass_(importDwc.getClass_());
               occurrence.setOrder(importDwc.getOrder());
               occurrence.setFamily(importDwc.getFamily());
               occurrence.setGenus(importDwc.getGenus());
               occurrence.setSubgenus(importDwc.getSubgenus());
               occurrence.setSpecificEpithet(importDwc.getSpecificEpithet());
               occurrence.setInfraspecificEpithet(importDwc.getInfraspecificEpithet());
               occurrence.setTaxonRank(importDwc.getTaxonRank());
               occurrence.setVerbatimTaxonRank(importDwc.getVerbatimTaxonRank());
               occurrence.setScientificNameAuthorship(importDwc.getScientificNameAuthorship());
               occurrence.setVernacularName(importDwc.getVernacularName());
               occurrence.setNomenclaturalCode(importDwc.getNomenclaturalCode());
               occurrence.setTaxonomicStatus(importDwc.getTaxonomicStatus());
               occurrence.setNomenclaturalStatus(importDwc.getNomenclaturalStatus());
               occurrence.setTaxonRemarks(importDwc.getTaxonRemarks());

               if(!update)
                 occurrenceDAO.create(occurrence);
               else
            	   occurrenceDAO.update(occurrence);
            	   
                }catch(NumberFormatException ex){
                    Logger.getLogger(Importer.class.getName()).log
                        (Level.SEVERE, 
                            "NumberFormatException occurrenceId {0}", 
                            new Object[]{ importDwc.getId() });
                    ex.printStackTrace();
                    
                    System.exit(-1);
                } catch (ParseException e) {
//					Logger.getLogger(Importer.class.getName()).log
//                        (Level.SEVERE, 
//                            "ParseException occurrenceId {0} {1}", 
//                            new Object[]{importDwc.getId(),e.});
                	e.printStackTrace();
				}
//            } // end for, 1000 importDwc rows
            
            session.evict(importDwc);
                
            if(batch == BATCH_SIZE){
            	batch = 0;
	            session.flush();
//	            fullTextSession.flushToIndexes();
	            session.clear();
//	            System.exit(1);
            }
	    
            if(rowsCounter % maxResults == 0){
//              fullTextSession.flushToIndexes();
            	Logger.getLogger(Importer.class.getName()).log
                (Level.INFO, 
                		"Occurrences added " + rowsCounter);
            }
            
            // ******* for debug only ***********
//            if(rowsCounter == 1) {
//            	session.getTransaction().rollback();
//            	scroll.close();
//            	System.exit(1);
//            }
            
//            firstResult += maxResults;
                
//            occurrencesDwcList =
//                    importDwcDAO.scrollAll(ImportDwc.class,
//                        maxResults,
//                        firstResult);
        } // end while, no more importDwc rows
        
        scroll.close();
//        transactionManager.commit(status);
        session.flush();
        session.clear();
        
        Logger.getLogger(Importer.class.getName()).log
        (Level.INFO, 
                "Total occurrences processed " + rowsCounter);
//        session.getTransaction().commit();
//        session.close();
    }
    
    /**
     * 
     * @param csvFile
     */
    @Transactional
    public void importDwcOccurrences(String csvFile) {
      org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Importer.class.getName());
      // clear and load new records to import_dwc table
      BufferedReader bReader;
      try {
        bReader = new BufferedReader(new FileReader(csvFile));
        logger.debug("Deleting importDwc");
        importDwcDAO.deleteAll();
        logger.debug("Start copy to importDwc");
        long copyRows = importDwcDAO.copy(bReader);
        logger.info(String.valueOf(copyRows) + " total rows copy to importDwc");
        
      } catch (FileNotFoundException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    
    /**
     * 
     * @param file_csv 
     */
    public void importGeoLayers(String file_csv){
        try {
            CSVReader reader = new CSVReader(new FileReader(file_csv));
            List layers = reader.readAll();
            
            Logger.getLogger(Importer.class.getName()).log
                    (Level.INFO, "Adding {0} geoLayers", layers.size());
            
            for (Object object : layers) {
                String[] layer = (String[])object;
                
                long geoLayerId = Long.parseLong(layer[0].trim().replace("\"", ""));
                
                GeoLayer geoLayer;
                geoLayer = (GeoLayer)genericBaseDAO.findById(GeoLayer.class, geoLayerId);
                
                if(geoLayer != null)
                    continue;
                else
                    geoLayer = new GeoLayer();
                
                geoLayer.setGeoLayerId( geoLayerId );
                geoLayer.setName(layer[1]);
                
                genericBaseDAO.create(geoLayer);
            }
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param file_csv 
     */
    public void importGeoFeatures(String file_csv){
        try {
            CSVReader reader = new CSVReader(new FileReader(file_csv));
            List features = reader.readAll();
            
            Logger.getLogger(Importer.class.getName()).log
                    (Level.INFO, "Adding {0} features", features.size());
            
            for (Object object : features) {
                String[] feature = (String[])object;
                
                GeoFeatureId featureId = new GeoFeatureId(
                                Long.parseLong(feature[0].trim().replace("\"", "")), 
                                Long.parseLong(feature[1].trim().replace("\"", "")));
                
                long geoLayerId = Long.parseLong(feature[0].trim().replace("\"", ""));
                
                GeoFeature geoFeature;
                geoFeature = (GeoFeature)
                        genericBaseDAO.findById(GeoFeature.class, featureId);
                
                if(geoFeature != null)
                    continue;
                else
                    geoFeature = new GeoFeature();
                
                geoFeature.setGeoLayer((GeoLayer)
                        genericBaseDAO.findById(
                            GeoLayer.class, geoLayerId ));
                geoFeature.setId(featureId);
                geoFeature.setName(feature[2]);
                
                genericBaseDAO.create(geoFeature);
            }
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param file_csv 
     */
    public void importLocationFeatures(String file_csv){
        try {
            CSVReader reader = new CSVReader(new FileReader(file_csv));
            List locationFeatures = reader.readAll();
            
            Logger.getLogger(Importer.class.getName()).log
                    (Level.INFO, "Adding {0} features per location", locationFeatures.size());
            
            //start new transaction
            DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
            TransactionStatus status = transactionManager.getTransaction(transaction);

            
            for (Object object : locationFeatures) {
                String[] locationFeature = (String[]) object;
                
                GeoFeatureId featureId = 
                    new GeoFeatureId(
                        Long.parseLong(locationFeature[1].trim().replace("\"", "")),
                        Long.parseLong(locationFeature[2].trim().replace("\"", "")));
                
                GeoFeature feature = 
                       (GeoFeature) genericBaseDAO.findById(GeoFeature.class, 
                            featureId);
                
                if(feature == null){
                    Logger.getLogger(Importer.class.getName()).log
                        (Level.WARNING, "Feature not found: {0} / {1}", 
                        new Object[]{featureId.getGeoLayerId(), featureId.getGeoFeatureId()});
                    continue;
                }
                
                Location location =
                        (Location) genericBaseDAO.findById(Location.class,
                            new BigDecimal(locationFeature[0].trim().replace("\"", "")));

                if(location == null){
                    Logger.getLogger(Importer.class.getName()).log
                        (Level.WARNING, "Location not found: {0}", 
                            new BigDecimal(locationFeature[0].trim().replace("\"", "")));
                    continue;
                }
                    
                //get actual feature and set new one
              //  if(location.getFeatures() == null)
                //    location.setFeatures(new ArrayList<GeoFeature>());
                location.getFeatures().add(feature);
                
            }
            
            transactionManager.commit(status);
            
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
    public void importTaxonDescription(String file_csv){
        try {
            CSVReader reader = new CSVReader(new FileReader(file_csv));
            List taxonDesList = reader.readAll();
            
            Logger.getLogger(Importer.class.getName()).log
                    (Level.INFO, "Adding {0} taxon descriptions", taxonDesList.size());
            
            //start new transaction
            DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
            TransactionStatus status = transactionManager.getTransaction(transaction);

            int contador = 0;
            
            DataProvider dataProvider = (DataProvider)
                    genericBaseDAO.findById(DataProvider.class, new BigDecimal(1));
            
            for (Object object : taxonDesList) {
                String[] taxonDes = (String[]) object;
            
                //find taxon to get its id
                List<Taxon> taxonList = taxonDAO.findAllByScientificName(taxonDes[2].trim());
                Taxon taxon = null;
                if(taxonList.size() > 0)
                    taxon = taxonList.get(0);
                else{
                    Logger.getLogger(Importer.class.getName()).log
                    (Level.WARNING, "Scientific name {0} not found!", taxonDes[2].trim());
                
                    continue;
                }
                
                TaxonDescription taxonDescription = new TaxonDescription();
                
                taxonDescription.setTaxonDescriptionId(new BigDecimal(taxonDes[0]));
                taxonDescription.setVersion(taxonDes[1]);
                taxonDescription.setDataProvider(dataProvider);
                taxonDescription.setTaxon(taxon);
          //??  taxonDescription.setGlobalUniqueIdentifier(null);
                
                taxonDescription.setScientificName(taxonDes[2].trim());
                // FIXME: institutionCode not include in file yet
                taxonDescription.setInstitutionCode("INB");
                
                Date dateLastModified = null;
                if(taxonDes[69].length() > 0){
                    String[] arrayDateLastModified = taxonDes[69].split("/");
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    cal.set(Calendar.MONTH, 
                            Integer.parseInt(arrayDateLastModified[0]) - 1);
                    cal.set(Calendar.DATE, 
                            Integer.parseInt(arrayDateLastModified[1]));
                    cal.set(Calendar.YEAR,
                            Integer.parseInt(arrayDateLastModified[2]));
                    
                    dateLastModified = cal.getTime();
                }
                    
                taxonDescription.setDateLastModified( dateLastModified );
                String taxonRecordId = taxon != null?taxon.getTaxonId().toString():null;
                taxonDescription.setTaxonRecordId(taxonRecordId);
                taxonDescription.setLanguage(taxonDes[79]);
                taxonDescription.setCreators(taxonDes[70]);
                taxonDescription.setDistribution(taxonDes[14] + taxonDes[15]);
                //taxonDescription.setAbstract_(null);
                taxonDescription.setKingdomTaxon(taxon.getKingdom());
                taxonDescription.setPhylumTaxon(taxon.getPhylum());
                taxonDescription.setClassTaxon(taxon.getClass_());
                taxonDescription.setOrderTaxon(taxon.getOrder());
                taxonDescription.setFamilyTaxon(taxon.getFamily());
                taxonDescription.setGenusTaxon(taxon.getGenus());
                taxonDescription.setSynonyms(taxonDes[9]);
                taxonDescription.setAuthorYearOfScientificName(
                        taxonDes[3] + " (" + taxonDes[4] + ")");
                taxonDescription.setSpeciesPublicationReference(taxonDes[52]);
                taxonDescription.setCommonNames(taxonDes[8]);
                //taxonDescription.setTypification(null);
                String contributors = taxonDes[41];
                if(contributors.length() > 0 && taxonDes[42].length() > 0)
                    contributors += ", " + taxonDes[42];
                taxonDescription.setContributors(contributors);
                
                Date dateCreated = null;
                if(taxonDes[68].length() > 0){
                    String[] arrayDateLastModified = taxonDes[68].split("/");
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    cal.set(Calendar.MONTH, 
                            Integer.parseInt(arrayDateLastModified[0]) - 1);
                    cal.set(Calendar.DATE, 
                            Integer.parseInt(arrayDateLastModified[1]));
                    cal.set(Calendar.YEAR,
                            Integer.parseInt(arrayDateLastModified[2]));
                    
                    dateCreated = cal.getTime();
                }
                taxonDescription.setDateCreated(dateCreated);
                //taxonDescription.setHabit(null);
                taxonDescription.setLifeCycle(taxonDes[20]);
                taxonDescription.setReproduction(taxonDes[23]);
                //taxonDescription.setAnnualCycle(null);
                taxonDescription.setScientificDescription(taxonDes[12]);
                //taxonDescription.setBriefDescription(null);
                taxonDescription.setFeeding(taxonDes[56]);
                taxonDescription.setBehavior(taxonDes[65]);
                //taxonDescription.setInteractions();
                //taxonDescription.setChromosomicNumberN(null);
                taxonDescription.setMolecularData(taxonDes[13]);
                taxonDescription.setPopulationBiology(taxonDes[24]);
                taxonDescription.setThreatStatus(taxonDes[72]);
                //taxonDescription.setLegislation(null);
                taxonDescription.setHabitat(taxonDes[54]);
                taxonDescription.setTerritory(taxonDes[55]);
                taxonDescription.setEndemicity(taxonDes[26].equals("Endémica")?"true":"false");
                taxonDescription.setTheUses(taxonDes[18]);
                //taxonDescription.setTheManagement(null);
                //taxonDescription.setFolklore(null);
                taxonDescription.setTheReferences(taxonDes[27]);
                //taxonDescription.setUnstructuredDocumentation(null);
                //taxonDescription.setOtherInformationSources(null);
                //taxonDescription.setPapers(null);
                //taxonDescription.setIdentificationKeys(null);
                //taxonDescription.setMigratoryData(null);
                //taxonDescription.setEcologicalSignificance(null);
                //taxonDescription.setUnstructuredNaturalHistory(null);
                //taxonDescription.setInvasivenessData(null);
                //taxonDescription.setTargetAudiences(null);
                
                genericBaseDAO.create(taxonDescription);
                
                //update Taxon with image from ubis (thumb image - estampilla)
                String imageUrl = taxonDes[67].trim();
                if(imageUrl.length() > 0){
                    taxon.setImageUrl(imageUrl);
                    taxonDAO.update(taxon);
                }
                
                contador++;
            }
            
            
            transactionManager.commit(status);
            
            Logger.getLogger(Importer.class.getName()).log
                    (Level.INFO, "{0} taxon descriptions inserts", contador);
            
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @param csvFile the absolute root for the csvFile 
     * @author avargas
     */
    public void importTaxonomy(String csvFile){

    	Taxon taxon;
    	boolean update;
		int batch = 0;
		String [] nextLine = null;
		CSVReader reader = null;
		
    	try {
    		// start transaction
            DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
            TransactionStatus status = transactionManager.getTransaction(transaction);
            Session session = transactionManager.getSessionFactory().getCurrentSession();

            // read csv file
			reader = new CSVReader(new FileReader(csvFile));
			String [] header = reader.readNext();
			int totalColumns = header.length;    // use for validation of lines
			
			// match header db columns with entity names
			Map<String, Integer> columnProperties = new HashMap<String, Integer>();
			for (int i=0; i < header.length; i++) {
				columnProperties.put(Taxon.customNameToProperty.get(header[i].toLowerCase()), i);
			}
			
			while ((nextLine = reader.readNext()) != null) {
				String taxonId = nextLine[columnProperties.get("taxonId")];
				
				// check the number of lines against header fields
				if (totalColumns != nextLine.length){
				  Logger.getLogger(Importer.class.getName()).log
                  (Level.WARNING, "Wrong line with taxon id {0} with batch number {1}", new Object[]{ taxonId, batch });
				  continue;
				}
				
				taxon = taxonNewDAO.findById(new BigDecimal(taxonId));
				
				if(taxon != null)
					update = true;
				else {
					update = false;
					taxon = new Taxon();
				}
				
				DirectFieldAccessor taxonAccessor = new DirectFieldAccessor(taxon);
				
				for (String indexKey : columnProperties.keySet()) {
					if(indexKey == null)
						continue;
					if(indexKey.equals("ancestorTaxonId")){
					  String ancestorTaxonId = nextLine[columnProperties.get(indexKey)];
					  Taxon ancestorTaxon = taxonNewDAO.findById(new BigDecimal(ancestorTaxonId));
					  taxon.setAncestorTaxonId(ancestorTaxon);
					}
					else
					  taxonAccessor.setPropertyValue(indexKey, nextLine[columnProperties.get(indexKey)]);
				}
				
				if(update)
					taxonDAO.update(taxon);
				else
					taxonDAO.create(taxon);
				
				// flush session based on batch size
				if(batch % BATCH_SIZE == 0){
		            
		            session.flush();
		            session.clear();
	            }
		    
	            if(batch % maxResults == 0){
	            	Logger.getLogger(Importer.class.getName()).log
	                (Level.INFO, "Adding {0} to {1} taxon", new Object[]{batch, batch + maxResults});
	            
	            }
	            batch++;
			}
			
			transactionManager.commit(status);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TypeMismatchException e) {
			// TODO: handle exception
			Logger.getLogger(Importer.class.getName()).log
            (Level.SEVERE, "NumberFormatException batch {0} ", new Object[]{batch });
			e.printStackTrace();
		} catch (NumberFormatException e) {
		  Logger.getLogger(Importer.class.getName()).log
          (Level.SEVERE, "NumberFormatException batch {0} ", new Object[]{batch });
		  String line = null;
		  for (String string : nextLine) {
            line += string + ",";
          }
		  // remove las comma
		  line = line.substring(0, line.length() - 2);
		  Logger.getLogger(Importer.class.getName()).log
          (Level.SEVERE, "line: {0} ", new Object[]{ line });
          e.printStackTrace();
        }
    	finally {
    	  if (reader != null){
    	    try {
            reader.close();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
    	  }
    	}
        
	
    }

}
