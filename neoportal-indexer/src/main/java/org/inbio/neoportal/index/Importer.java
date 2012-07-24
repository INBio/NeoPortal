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

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.inbio.neoportal.core.dao.GenericBaseDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.impl.GenericBaseDAOImpl;
import org.inbio.neoportal.core.entity.DataProvider;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.GeoFeatureId;
import org.inbio.neoportal.core.entity.GeoLayer;
import org.inbio.neoportal.core.entity.ImportDwc;
import org.inbio.neoportal.core.entity.Location;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author avargas
 */
@Service
//@Transactional
public class Importer {

    static int maxResults = 1000;
    
    @Autowired
    private HibernateTransactionManager transactionManager;
    
    @Autowired
    @Qualifier("genericBaseDAOImpl")
    private GenericBaseDAOImpl<ImportDwc, BigDecimal> importDwcDAO;
    
    @Autowired
    @Qualifier("genericBaseDAOImpl")
    private GenericBaseDAO genericBaseDAO;
        
    @Autowired
    private OccurrenceDAO occurrenceDAO;
    
    @Autowired
    private TaxonDAO taxonDAO;
    
    public Importer() {
        super();
    }
    
    public void importAll(
            String geoLayerFile, 
            String geoFeatureFile, 
            String locationFeatureFile,
            String taxonDescriptionFile){
    //    importOccurrences();
    //    importGeoLayers(geoLayerFile);
    //    importGeoFeatures(geoFeatureFile);
    //    importLocationFeatures(locationFeatureFile);
        importTaxonDescription(taxonDescriptionFile);
    }

    public void importOccurrences(){
        
        
        //get current date for dateLastModified field
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateLastModified = dateFormat.format(date);
        
        DataProvider dp = (DataProvider)
                       genericBaseDAO.findAll(DataProvider.class).get(0);
        
        int firstResult = 0;
                
        //start new transaction
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(transaction);
        
        List<ImportDwc> occurrencesDwcList =
                importDwcDAO.scrollAll(ImportDwc.class,
                        maxResults,
                        firstResult);
        
        while(!occurrencesDwcList.isEmpty()){
            Logger.getLogger(Importer.class.getName()).log
                (Level.INFO, "Adding {0} to {1} occurrences", new Object[]{firstResult, firstResult + maxResults});
                
            for (ImportDwc importDwc : occurrencesDwcList) {

                try{
                //avoid repeated occurrenceId
                Occurrence occurrence = occurrenceDAO.findById(
                        Occurrence.class, 
                        new BigDecimal(importDwc.getCatalognumber().replace("\"", "")));
                
                if(occurrence != null){
                    Logger.getLogger(Importer.class.getName()).log
                        (Level.INFO, "OccurrenceId {0} already exist!", 
                                new BigDecimal(importDwc.getCatalognumber().replace("\"", "")));
            
                    continue;
                }
                else {
                    occurrence = new Occurrence();
                }
                
               Taxon taxon;
               //check if taxonId is empty (unidentify specimens)
               if(importDwc.getTaxonid().isEmpty()){
                   taxon = null;
               }else{
                //find taxon entity
                taxon = (Taxon) genericBaseDAO.findById(
                        Taxon.class, new BigDecimal(importDwc.getTaxonid().replace("\"", "")));
               }

                // TODO: fix, use specimenId instead
               occurrence.setOccurrenceId(
                       new BigDecimal(importDwc.getCatalognumber().replace("\"", "")));
                       
               occurrence.setDataProvider(dp);
               occurrence.setTaxon(taxon);
               occurrence.setGlobalUniqueIdentifier(
                       importDwc.getInstitutioncode() + ":" +
                       importDwc.getCollectioncode() + ":" +
                       importDwc.getCatalognumber());
               occurrence.setDateLastModified(dateLastModified);
               occurrence.setInstitutionCode(importDwc.getInstitutioncode());
               occurrence.setCollectionCode(importDwc.getCollectioncode());
               occurrence.setCatalogNumber(importDwc.getCatalognumber());
               occurrence.setScientificName(importDwc.getScientificname());
               occurrence.setBasisOfRecord(importDwc.getBasisofrecord());
               occurrence.setInformationWithheld(importDwc.getInformationwithheld());
               // TODO: change field to update Darwin Core standard
      //       occurrence.setHigherTaxon(taxon.getTaxonByAncestorTaxonId().getDefaultName());
               occurrence.setKingdom(importDwc.getKingdom());
               occurrence.setPhylum(importDwc.getPhylum());
               occurrence.setClass_(importDwc.getClass_());
               occurrence.setOrders(importDwc.getTaxonOrder());
               occurrence.setFamily(importDwc.getFamily());
               occurrence.setGenus(importDwc.getGenus());
               occurrence.setSpecificEpithet(importDwc.getSpecificepithet());
               occurrence.setInfraspecificEpithet(importDwc.getInfraspecificepithet());
               occurrence.setInfraspecificRank(null);
               // TODO: change field to update Darwin Core standard
               occurrence.setAuthorYearOfScientificName(
                       importDwc.getScientificnameauthorship() + ", ");
               occurrence.setNomenclaturalCode(importDwc.getNomenclaturalcode());
               occurrence.setIdentificationQualifier(importDwc.getIdentificationqualifier());
               occurrence.setCollectingMethod(null);
               occurrence.setValidDistributionFlag(null);
               occurrence.setCollector(importDwc.getRecordedBy());
               occurrence.setEarliestDateCollected(null);
               occurrence.setLatestDateCollected(null);
               occurrence.setDayOfYear(new BigDecimal(importDwc.getDay()));
               occurrence.setHigherGeography(importDwc.getHighergeography());
               occurrence.setContinent(importDwc.getContinent());
               occurrence.setWaterBody(importDwc.getWaterbody());
               occurrence.setIslandGroup(importDwc.getIslandgroup());
               occurrence.setIsland(importDwc.getIsland());
               occurrence.setCountry(importDwc.getCountry());
               occurrence.setStateProvince(importDwc.getStateprovince());
               occurrence.setCounty(importDwc.getCounty());
               occurrence.setLocality(importDwc.getLocality());
               occurrence.setMinimumElevationInMeters(importDwc.getMinimumelevationinmeters());
               occurrence.setMaximumElevationInMeters(importDwc.getMaximumelevationinmeters());
               occurrence.setMinimumDepthInmeters(importDwc.getMinimumdepthinmeters());
               occurrence.setMaximumDepthInmeters(importDwc.getMaximumdepthinmeters());
               occurrence.setSex(importDwc.getSex());
               occurrence.setLifeStage(importDwc.getLifestage());
               occurrence.setRemarks(null);
               occurrence.setAttributes(null);			
               occurrence.setImageUrl(null);	
               occurrence.setRelatedInformation(null);

               
               //find or create location
                Location location = (Location)
                        genericBaseDAO.findById(
                            Location.class, 
                            new BigDecimal(importDwc.getLocationid()));

                if(location == null){
                    location = new Location(
                                    new BigDecimal(importDwc.getLocationid()));
                    //set coordinates values
                    location.setDecimalLatitude(importDwc.getDecimallatitude());
                    location.setDecimalLongitude(importDwc.getDecimallongitude());

                    genericBaseDAO.create(location);
                }
                occurrence.setLocation(location);

               occurrenceDAO.create(occurrence);
               
                }catch(NumberFormatException ex){
                    Logger.getLogger(Importer.class.getName()).log
                        (Level.SEVERE, 
                            "NumberFormatException occurrenceId {0} {1}", 
                            new Object[]{importDwc.getId(),ex.getStackTrace()});
                    
                    System.exit(-1);
                }
               

            }
            
            //store data every 1000 regs
            transactionManager.commit(status);
                        
            //create new transaction for the next 1000 regs
            transaction = new DefaultTransactionDefinition();
            status = transactionManager.getTransaction(transaction);            
            
            firstResult += maxResults;
                
            //ScrollableResults occurrencesDwcList = 
            occurrencesDwcList =
                    importDwcDAO.scrollAll(ImportDwc.class,
                        maxResults,
                        firstResult);
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
                taxonDescription.setPhylumTaxon(taxon.getDivision());
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

}
