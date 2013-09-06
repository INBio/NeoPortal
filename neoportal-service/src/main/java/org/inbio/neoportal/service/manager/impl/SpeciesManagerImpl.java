/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.transformers.OccurrenceDWCTransformer;
import org.inbio.neoportal.core.dto.transformers.OccurrenceTransformer;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.dto.species.TaxonFeatureDTO;
import org.inbio.neoportal.service.manager.SpeciesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author avargas
 */
@Service
public class SpeciesManagerImpl
        implements SpeciesManager {

    @Autowired
    private TaxonDescriptionDAO taxonDescriptionDAO;
    
    @Autowired
    private TaxonDAO taxonDAO;
    
    @Autowired
    private ImageDAO imageDAO;
    
    @Autowired
    private OccurrenceDAO occurrenceDAO;
    
    @Override
    public List<TaxonDescriptionFullSDTO> taxonDescriptionByProvider
            (String scientificName, String provider) {
        
        List<TaxonDescriptionFullCDTO> taxonDescription = new ArrayList<TaxonDescriptionFullCDTO>();
        
        List<TaxonDescriptionFullSDTO> result = new ArrayList<TaxonDescriptionFullSDTO>();
        
        taxonDescription = taxonDescriptionDAO.findAllByScientificName(scientificName, provider);
        
        for(TaxonDescriptionFullCDTO tfdto: taxonDescription) {
            TaxonDescriptionFullSDTO td = new TaxonDescriptionFullSDTO();
            
            td.setAnnualCycle(tfdto.getAnnualCycle());
            td.setAuthorYearOfScientificName(tfdto.getAuthorYearOfScientificName());
            td.setBehavior(tfdto.getBehavior());
            td.setBriefDescription(tfdto.getBriefDescription());
            td.setChromosomicNumberN(tfdto.getChromosomicNumberN());
            td.setClassTaxon(tfdto.getClassTaxon());
            td.setCommonNames(tfdto.getCommonNames());
            td.setContributors(tfdto.getContributors());
            td.setCreators(tfdto.getCreators());
            td.setDateCreated(tfdto.getDateCreated());
            td.setDateLastModified(tfdto.getDateLastModified());
            td.setDistribution(tfdto.getDistribution());
            td.setEcologicalSignificance(tfdto.getEcologicalSignificance());
            td.setEndemicity(tfdto.getEndemicity());
            td.setFamilyTaxon(tfdto.getFamilyTaxon());
            td.setFeeding(tfdto.getFeeding());
            td.setFolklore(tfdto.getFolklore());
            td.setGenusTaxon(tfdto.getGenusTaxon());                        
            td.setHabit(tfdto.getHabit());
            td.setHabitat(tfdto.getHabitat());
            td.setIdentificationKeys(tfdto.getIdentificationKeys());
            td.setInstitutionCode(tfdto.getInstitutionCode());
            td.setInteractions(tfdto.getInteractions());
            td.setInvasivenessData(tfdto.getInvasivenessData());
            td.setKingdomTaxon(tfdto.getKingdomTaxon());
            td.setLanguage(tfdto.getLanguage());
            td.setLegislation(tfdto.getLegislation());
            td.setLifeCycle(tfdto.getLifeCycle());
            td.setMigratoryData(tfdto.getMigratoryData());
            td.setMolecularData(tfdto.getMolecularData());
            td.setOrderTaxon(tfdto.getOrderTaxon());
            td.setOtherInformationSources(tfdto.getOtherInformationSources());
            td.setPapers(tfdto.getPapers());
            td.setPhylumTaxon(tfdto.getPhylumTaxon());
            td.setPopulationBiology(tfdto.getPopulationBiology());
            td.setReproduction(tfdto.getReproduction());
            td.setScientificDescription(tfdto.getScientificDescription());
            td.setScientificName(scientificName);
            td.setSpeciesPublicationReference(tfdto.getSpeciesPublicationReference());
            td.setSynonyms(tfdto.getSynonyms());
            td.setTargetAudiences(tfdto.getTargetAudiences());
            td.setTaxonRecordId(tfdto.getTaxonRecordId());
            td.setTerritory(tfdto.getTerritory());
            td.setTheManagement(tfdto.getTheManagement());
            td.setTheReferences(tfdto.getTheReferences());
            td.setTheUses(tfdto.getTheUses());
            td.setThreatStatus(tfdto.getThreatStatus());
            td.setTypification(tfdto.getTypification());
            td.setUnstructuredDocumentation(tfdto.getUnstructuredDocumentation());
            td.setUnstructuredNaturalHistory(tfdto.getUnstructuredNaturalHistory());
            td.setUsername(tfdto.getUsername());
            
            result.add(td);
        }
        
        return result;
    }

    @Override
    public List<TaxonDescriptionFullSDTO> taxonDescription(String scientificName) {
        List<TaxonDescriptionFullCDTO> taxonDescription = new ArrayList<TaxonDescriptionFullCDTO>();
        
        List<TaxonDescriptionFullSDTO> result = new ArrayList<TaxonDescriptionFullSDTO>();
        
        taxonDescription = taxonDescriptionDAO.findAllByScientificName(scientificName);
        
        for(TaxonDescriptionFullCDTO tfdto: taxonDescription) {
            TaxonDescriptionFullSDTO td = new TaxonDescriptionFullSDTO();
            
            td.setAnnualCycle(tfdto.getAnnualCycle());
            td.setAuthorYearOfScientificName(tfdto.getAuthorYearOfScientificName());
            td.setBehavior(tfdto.getBehavior());
            td.setBriefDescription(tfdto.getBriefDescription());
            td.setChromosomicNumberN(tfdto.getChromosomicNumberN());
            td.setClassTaxon(tfdto.getClassTaxon());
            td.setCommonNames(tfdto.getCommonNames());
            td.setContributors(tfdto.getContributors());
            td.setCreators(tfdto.getCreators());
            td.setDateCreated(tfdto.getDateCreated());
            td.setDateLastModified(tfdto.getDateLastModified());
            td.setDistribution(tfdto.getDistribution());
            td.setEcologicalSignificance(tfdto.getEcologicalSignificance());
            td.setEndemicity(tfdto.getEndemicity());
            td.setFamilyTaxon(tfdto.getFamilyTaxon());
            td.setFeeding(tfdto.getFeeding());
            td.setFolklore(tfdto.getFolklore());
            td.setGenusTaxon(tfdto.getGenusTaxon());                        
            td.setHabit(tfdto.getHabit());
            td.setHabitat(tfdto.getHabitat());
            td.setIdentificationKeys(tfdto.getIdentificationKeys());
            td.setInstitutionCode(tfdto.getInstitutionCode());
            td.setInteractions(tfdto.getInteractions());
            td.setInvasivenessData(tfdto.getInvasivenessData());
            td.setKingdomTaxon(tfdto.getKingdomTaxon());
            td.setLanguage(tfdto.getLanguage());
            td.setLegislation(tfdto.getLegislation());
            td.setLifeCycle(tfdto.getLifeCycle());
            td.setMigratoryData(tfdto.getMigratoryData());
            td.setMolecularData(tfdto.getMolecularData());
            td.setOrderTaxon(tfdto.getOrderTaxon());
            td.setOtherInformationSources(tfdto.getOtherInformationSources());
            td.setPapers(tfdto.getPapers());
            td.setPhylumTaxon(tfdto.getPhylumTaxon());
            td.setPopulationBiology(tfdto.getPopulationBiology());
            td.setReproduction(tfdto.getReproduction());
            td.setScientificDescription(tfdto.getScientificDescription());
            td.setScientificName(scientificName);
            td.setSpeciesPublicationReference(tfdto.getSpeciesPublicationReference());
            td.setSynonyms(tfdto.getSynonyms());
            td.setTargetAudiences(tfdto.getTargetAudiences());
            td.setTaxonRecordId(tfdto.getTaxonRecordId());
            td.setTerritory(tfdto.getTerritory());
            td.setTheManagement(tfdto.getTheManagement());
            td.setTheReferences(tfdto.getTheReferences());
            td.setTheUses(tfdto.getTheUses());
            td.setThreatStatus(tfdto.getThreatStatus());
            td.setTypification(tfdto.getTypification());
            td.setUnstructuredDocumentation(tfdto.getUnstructuredDocumentation());
            td.setUnstructuredNaturalHistory(tfdto.getUnstructuredNaturalHistory());
            td.setUsername(tfdto.getUsername());
            
            result.add(td);
        }
        
        return result;
    }
    
    /**
     * 
     * @param defaultName
     * @return 
     */
    public List<ImagesCDTO> getImagesByDefaultName(
    		String defaultName,
    		int offset,
    		int quantity
    		){
    	Taxon taxon = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxon == null)
    		return null;
    	
    	// get feature images
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	String[] fieldsArray = {field};
    	
    	List<ImagesCDTO> images = imageDAO.search(fieldsArray, defaultName, offset, quantity);

        return images;
    }
    
    
    public Long countImagesByDefaultName(String defaultName){
    	Taxon taxon = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxon == null)
    		return Long.valueOf(0);
    	
    	// get feature images
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	String[] fieldsArray = {field};
    	
    	return imageDAO.searchCount(fieldsArray, defaultName);
    	
    }

    /**
     * 
     */
    @Transactional
    public TaxonFeatureDTO getTaxonFeatureByDefaultName(String defaultName) {
    	TaxonFeatureDTO taxonFeature = new TaxonFeatureDTO();
    	
    	Taxon taxon = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxon == null)
    		return null;
    				
    	taxonFeature.setDefaultName(taxon.getDefaultName());
    	if(!taxon.getTaxonDescriptions().isEmpty()) {
    		taxonFeature.setBriefDescription(
    				taxon.getTaxonDescriptions().iterator().next().getBriefDescription());
    	}
    	taxonFeature.setTaxonomicalRangeName(
    			Taxon.TaxonomicalRange.getById(
    					taxon.getTaxonomicalRangeId().longValue())
    					.getTaxonomicalRangeName());
    	
    	// get feature images
    	String field = "taxon." + taxonFeature.getTaxonomicalRangeName();
    	String[] fieldsArray = {field};
    	
    	List<ImagesCDTO> images = imageDAO.search(fieldsArray, defaultName, 0, 5);
    	long imageCount = imageDAO.searchCount(fieldsArray, defaultName);
    	
    	long occurrenceCount = occurrenceDAO.searchCount(fieldsArray, defaultName);
    	
    	taxonFeature.setFeatureImages(images);
    	taxonFeature.setImagesCount(imageCount);
    	taxonFeature.setOccurrencesCount(occurrenceCount);
    	
    	return taxonFeature;
    }
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<OccurrenceDwcCDTO> getOccurrencesByDefaultName(
			String defaultName, 
			int offset, 
			int quantity) {
		
		Taxon taxon = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxon == null)
    		return null;
    	
    	// get feature images
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	String[] fieldsArray = {field};
    	
    	return (List<OccurrenceDwcCDTO>)occurrenceDAO.search(
    			fieldsArray, defaultName, new OccurrenceDWCTransformer(), offset, quantity);
	}


	@Override
	public Long countOccurrencesByDefaultName(
			String defaultName) {
		
		Taxon taxon = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxon == null)
    		return null;
    	
    	// get feature images
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	String[] fieldsArray = {field};
    	
    	return occurrenceDAO.searchCount(fieldsArray, defaultName);
	}
    
}
