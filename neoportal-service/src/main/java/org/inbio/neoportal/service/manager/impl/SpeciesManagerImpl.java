/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.service.manager.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;








import org.codehaus.jackson.impl.JsonReadContext;
import org.hibernate.service.config.spi.ConfigurationService.Converter;
import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.dao.TaxonPlicBooksDAO;
import org.inbio.neoportal.core.dao.TaxonPlicDAO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.transformers.ImagesTransformer;
import org.inbio.neoportal.core.dto.transformers.OccurrenceDWCTransformer;
import org.inbio.neoportal.core.entity.Book;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonPlic;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.dto.species.TaxonFeatureDTO;
import org.inbio.neoportal.service.manager.SpeciesManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author avargas
 */
@Service
@Transactional
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
    
    @Autowired
    private TaxonPlicDAO taxonPlicDAO;
    
    @Autowired
    private TaxonPlicBooksDAO taxonPlicBooksDAO;
    
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
    @Transactional
    public List<ImagesCDTO> getImagesByDefaultName(
    		String defaultName,
    		int offset,
    		int quantity
    		){
    	List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName); 
    	Taxon taxon;
    	
    	if (taxonList.size() == 0)
    		return null;
    	
    	taxon = taxonList.get(0);
    	
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	
    	if(field.equals("taxon.species")) {
    		field = "taxon.defaultName_keyword";
    	}
    	
    	List<ImagesCDTO> images = imageDAO.searchPhrase(field, defaultName, "taxon.defaultName_keyword", offset, quantity);

        return images;
    }
    
    
    public Long countImagesByDefaultName(String defaultName){
    	List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
    	Taxon taxon;
    	
    	if (taxonList.size() == 0)
    		return Long.valueOf(0);
    	
    	taxon = taxonList.get(0);
    	
    	String field = "taxon." + Taxon.TaxonomicalRange.getById(
				taxon.getTaxonomicalRangeId().longValue())
				.getTaxonomicalRangeName();
    	
    	if(field.equals("taxon.species")) {
    		field = "taxon.defaultName_keyword";
    	}
    	
    	return imageDAO.searchPhraseCount(field, defaultName);
    	
    }

    /**
     * 
     */
    @Transactional
    public TaxonFeatureDTO getTaxonFeatureByDefaultName(String defaultName) {
    	TaxonFeatureDTO taxonFeature = new TaxonFeatureDTO();
    	String field;
    	List<ImagesCDTO> images;
    	long imageCount;
    	long occurrenceCount;
    	
    	if(defaultName.isEmpty())
    	  return null;
    	
    	List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
    	
    	if (taxonList.size() == 0)
    		return null;
    	
    	Taxon taxon = taxonList.get(0);
    				
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
    	field = "taxon." + taxonFeature.getTaxonomicalRangeName();
    	
    	if(field.equals("taxon.species")) {
    		List<Image> imageList = new ArrayList<Image>(taxon.getImages());
    		ImagesTransformer imagesTransformer = new ImagesTransformer();
    		
    		images = imagesTransformer.transformList(imageList);
    		imageCount = imageList.size();
    	}
    	else {
    		images = imageDAO.searchPhrase(field, defaultName, "taxon.defaultName_keyword", 0, 4);
    		imageCount = imageDAO.searchPhraseCount(field, defaultName);
    	}
    	
    	occurrenceCount = this.countOccurrencesByDefaultName(defaultName);
    	
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
		
		List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
		Taxon taxon;
    	
    	if (taxonList.size() == 0)
    		return null;
    	
    	taxon = taxonList.get(0);
    	
    	// prepare lucene query
    	String luceneQuery = getLuceneQueryOccurrences(taxon);
    	
    	return occurrenceDAO.searchLucene(
    			luceneQuery,
    			"taxon.defaultName_keyword",
    			new OccurrenceDWCTransformer(), 
    			offset, quantity);
    	
	}

	@Override
	public Long countOccurrencesByDefaultName(
			String defaultName) {
		
		List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
		Taxon taxon;
    	
    	if (taxonList.size() == 0)
    		return null;
    	
    	taxon = taxonList.get(0);
    	
    	String luceneQuery = getLuceneQueryOccurrences(taxon);
    	
    	return occurrenceDAO.searchLuceneCount(luceneQuery);
	}

	@Override
	public Taxon getTaxonByDefaultName(String defaultName) 
	{
		// field used for exact match query
		String[] fields = {"defaultName_keyword"};
		
		List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
		Taxon taxon;
    	
    	if (taxonList.size() == 0)
    	{
    		return null;
    	}
    		
    	
    	taxon = taxonList.get(0);
    	
		
//		List<TaxonCDTO> list = taxonDAO.search(defaultName, fields, 0, 1, resultTransformer);
//		TaxonCDTO taxonCDTO;
//		
//		if (list.isEmpty())
//			return null;
//		else
//			taxonCDTO = list.get(0);
//		
//		// values to lowercase
		taxon.setKingdom(taxon.getKingdom().toLowerCase());
		taxon.setPhylum(taxon.getPhylum().toLowerCase());
		taxon.setClass_(taxon.getClass_().toLowerCase());
		taxon.setOrder(taxon.getOrder().toLowerCase());
		taxon.setFamily(taxon.getFamily().toLowerCase());
		taxon.setGenus(taxon.getGenus().toLowerCase());
//		taxon.setDefaultName(taxon.getDefaultName().toLowerCase());
		
		return taxon;
	}

	/**
	 * Return a lucene query to get occurrences related to species or lower level elements of a giving taxon 
	 * @param taxon
	 * @return
	 */
	private String getLuceneQueryOccurrences(Taxon taxon) {
		String luceneQuery;
		if(taxon.getTaxonomicalRangeId().longValue() < Taxon.TaxonomicalRange.SPECIES.getId()) {
			luceneQuery = Taxon.TaxonomicalRange.getById(
						taxon.getTaxonomicalRangeId().longValue()).getTaxonomicalRangeName() + ":" + taxon.getDefaultName();
		}
		else {
			luceneQuery = "scientificName:\"" + taxon.getDefaultName() + "\"";
		}
		
		return luceneQuery;
	}
	
  /* (non-Javadoc)
   * @see org.inbio.neoportal.service.manager.SpeciesManager#getTaxonPLicByDefaultName(java.lang.String)
   */
  @Override
  public TaxonPlic getTaxonPLicByDefaultName(String defaultName,String language) {
 // field used for exact match query
    String[] fields = {"defaultName_keyword"};
    
    List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
    Taxon taxon;
    
    if (taxonList.size() == 0)
    {
        return null;
    }
    else
    {
    	taxon = taxonList.get(0);
    }
    
    List<TaxonPlic> taxonPlicList = taxonPlicDAO.getByTaxonId(taxon.getTaxonId());
    
    if(taxonPlicList.size() == 0 )
    {
    	return null;
    }
    else if (taxonPlicList.size() > 0)
    {
    	TaxonPlic list;
    	for(int i = 0; i <= taxonPlicList.size(); i++)
    	{
    		list = taxonPlicList.get(i);
    		
    		if(list.getLanguage().toString().equals(language) == true)
    		{
    			return list;
    		}
    	}
	    return null;
    }
    else 
    {
       TaxonPlic taxonPlic = taxonPlicList.get(0);
       return taxonPlic;
    }
  }
  /**
   * 
   * @param defaultName
   * @return 
   */
  public List<TaxonPlic> getTaxonListLanguaje(String defaultName)
  {
	  List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);	    
	  Taxon taxon;
	  
	  if (taxonList.size() == 0)
	  {
		  return null;
	  }
	  else
	  {
		  taxon = taxonList.get(0);
	  }
	        
	  List<TaxonPlic> taxonPlicList = taxonPlicDAO.getByTaxonId(taxon.getTaxonId());
	  
	  return taxonPlicList;  
  }	
  
  

  public List<Book> getBook(String defaultName,String language)
  {
	 
  // field used for exact match query
     String[] fields = {"defaultName_keyword"};
     
     List<Taxon> taxonList = taxonDAO.findByDefaultName(defaultName);
     Taxon taxon;
     
     if (taxonList.size() == 0)
         return null;
     
     taxon = taxonList.get(0);
     
     List<TaxonPlic> taxonPlicList = taxonPlicDAO.getByTaxonId(taxon.getTaxonId());
     
     if (taxonPlicList.size() > 0)
     {
     	TaxonPlic list;
     	for(int i = 0; i <= taxonPlicList.size(); i++)
     	{
     		list = taxonPlicList.get(i);
     		
     		if(list.getLanguage().toString().equals(language) == true)
     		{  			
     			List<Book> booksList = taxonPlicBooksDAO.getByTaxonPlicId(taxonPlicList.get(i).getTaxonRecordId());
     			return booksList;
     		}	
     	}
 	    return null;
     }
     else 
     {
        TaxonPlic taxonPlic = taxonPlicList.get(0);
		List<Book> booksList = taxonPlicBooksDAO.getByTaxonPlicId(taxonPlicList.get(0).getTaxonRecordId());
		return booksList;     
     }  
  }	  
}
