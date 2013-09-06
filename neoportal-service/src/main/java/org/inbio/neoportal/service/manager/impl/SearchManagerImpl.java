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
package org.inbio.neoportal.service.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.dto.commonname.CommonNameLiteCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceGeospatialLiteCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceLiteCDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionLiteCDTO;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionLiteSDTO;
import org.inbio.neoportal.service.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria
 */
@Service
public class SearchManagerImpl implements SearchManager{

    @Autowired
    private TaxonDAO taxonDAO;
    
    @Autowired
    private TaxonDescriptionDAO taxonDescriptionDAO;

    @Autowired
    private OccurrenceDAO occurrenceDAO;

        /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<TaxonDescriptionLiteSDTO> 
        speciesPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        
        TaxonDescriptionLiteSDTO sp = null;
                
        //Set to store all the different scientific names
        List<TaxonDescriptionLiteCDTO> speciesList = new ArrayList<TaxonDescriptionLiteCDTO>();
        
        //Result List of SpeciesLiteDTO Objects
        List<TaxonDescriptionLiteSDTO> result = new ArrayList<TaxonDescriptionLiteSDTO>();



        // Search the results of the query
        speciesList = taxonDescriptionDAO.search(searchText, offset, quantity);
        
        for (TaxonDescriptionLiteCDTO tldto: speciesList ){
             
            sp = new TaxonDescriptionLiteSDTO();
             
             sp.setCommonNameList(searchText);
             sp.setScientificName(tldto.getScientificName());
             sp.setInstitution(tldto.getInstitution());
             result.add(sp);
        }

        return result;
    }
   
    
    /**
     * Returns the element count of the search result
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long speciesSearchCount(String searchText)
            throws ParseException{
        
        return taxonDescriptionDAO.searchCount(searchText);
    }
    
    
    /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<SpeciesLiteSDTO> 
        taxonPaginatedSearch(String searchTerms, int offset, int quantity)
            throws ParseException{

        List<TaxonLiteCDTO> taxonList = null;
        SpeciesLiteSDTO sp = null;
                
        //Set to store all the different scientific names
        Set<SpeciesLiteSDTO> speciesList = new HashSet<SpeciesLiteSDTO>();
        
        //Result List of SpeciesLiteDTO Objects
        List<SpeciesLiteSDTO> result = new ArrayList<SpeciesLiteSDTO>();

        searchTerms = formatTaxonSearch(searchTerms);
        
        // Search the results of the query
        taxonList = taxonDAO.search(searchTerms, offset, quantity);
        
        for (TaxonLiteCDTO tldto: taxonList ){
             
            sp = new SpeciesLiteSDTO();
             
             sp.setCommonName(this.joinCommonNames(tldto.getCommonNameList()));
             sp.setScientificName(tldto.getDefaultName());
             
             if(tldto.getImageUrl() != null){
                 sp.setImageURL(tldto.getImageUrl());
             }
             else if(tldto.getImageList().size() > 0){
            	ImagesCDTO imagesCDTO = tldto.getImageList().get(0);
            	 
                sp.setImageURL(Image.getSmallUrl(imagesCDTO));
             }
             else
                 //sp.setImageURL("http://pulsatrix.inbio.ac.cr/projects/atta2/chrome/site/header.png");
            	 sp.setImageURL("");
             
             speciesList.add(sp);
        }

        result.addAll(speciesList);

        return result;
    }
   
    
    /**
     * Returns the element count of the search result
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long taxonSearchCount(String searchTerms)
            throws ParseException{
    	searchTerms = formatTaxonSearch(searchTerms);
        return taxonDAO.searchCount(searchTerms);
    }
    
    /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<OccurrenceLiteSDTO> occurrencePaginatedSearch
        (String searchTerms, int offset, int quantity) 
            throws ParseException{
      
        
        ArrayList<OccurrenceLiteSDTO> olsdto = new 
            ArrayList<OccurrenceLiteSDTO>();
        
        // retrieve the search results
        ArrayList<OccurrenceLiteCDTO> ocList 
            =  (ArrayList<OccurrenceLiteCDTO>) occurrenceDAO.search(
            		searchTerms, 
                    offset, 
                    quantity);
        
        ArrayList<OccurrenceGeospatialLiteCDTO> ogl = null;
        
        String latitude = null;
        String longitude = null;
        
        
        for(OccurrenceLiteCDTO ol : ocList ){
            
            // take only the firs of a list of coordinates
            ogl = ol.getGeospatialList();
            
            if(ogl.size() > 0){
                latitude = ogl.get(0).getLatitude();
                longitude = ogl.get(0).getLongitude();
            }else{
                latitude = null;
                longitude = null;
            }
            
            
            olsdto.add(
                new OccurrenceLiteSDTO(
                    ol.getOccurrenceId(),
                    ol.getScientificName(),
                    ol.getInstitution(),
                    ol.getCountry(),
                    ol.getProvince(),
                    ol.getCounty(),
                    ol.getLocality(),
                    latitude,
                    longitude,
                    ol.getCatalog()));
        }

        return olsdto;
    }

    /**
     * 
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long occurrenceSearchCount(String searchText)
            throws ParseException {

        return occurrenceDAO.searchCount( searchText);
    }
    
    
    /**
     * Put toguether all the common names of a list and concatenate them into a 
     * single string.
     * @param list
     * @return 
     */
    private String joinCommonNames(ArrayList<CommonNameLiteCDTO> list) {

        CommonNameLiteCDTO cm = null;
        
        int length = list.size();
        StringBuilder commonNameList = new StringBuilder();
        
        for(int i = 0; i < length; i++){
            cm = list.get(i);
            commonNameList.append(cm.getName());
            
            if( ! cm.getUsedBy().isEmpty() )
                commonNameList.append(" (").append(cm.getUsedBy()).append(")");
            
            if(i < length-1)
                commonNameList.append(", ");          
        }
        
        return commonNameList.toString();
    }

    @Override
    public List<String> taxonSuggestions(String term) 
            throws ParseException {
        
        return taxonDAO.taxonSuggestions(term);
    }
    
    private String formatTaxonSearch(String searchTerms){
    	//filter taxon range to match only species
        //TODO: change taxonomical Range number for enum
        searchTerms = "(" + searchTerms + ") AND (taxonomicalRangeId:19 taxonomicalRangeId:20 taxonomicalRangeId:21 taxonomicalRangeId:22)";
        
    	return searchTerms;
    }


	@Override
	public List<SpeciesLiteSDTO> taxonInPaginatedSearch(
			List<BigDecimal> idList,
			int offset, 
			int quantity) throws ParseException {
		List<TaxonLiteCDTO> taxonList = null;
        SpeciesLiteSDTO sp = null;
                
        //Set to store all the different scientific names
        Set<SpeciesLiteSDTO> speciesList = new HashSet<SpeciesLiteSDTO>();
        
        //Result List of SpeciesLiteDTO Objects
        List<SpeciesLiteSDTO> result = new ArrayList<SpeciesLiteSDTO>();
        
        // Search the results of the query
        taxonList = taxonDAO.searchIn(idList, offset, quantity);
        
        for (TaxonLiteCDTO tldto: taxonList ){
             
            sp = new SpeciesLiteSDTO();
             
             sp.setCommonName(this.joinCommonNames(tldto.getCommonNameList()));
             sp.setScientificName(tldto.getDefaultName());
             
             if(tldto.getImageUrl() != null){
                 sp.setImageURL(tldto.getImageUrl());
             }
             else if(tldto.getImageList().size() > 0){
            	 ImagesCDTO imagesCDTO = tldto.getImageList().get(0);
                sp.setImageURL(Image.getSmallUrl(imagesCDTO));
             }
             else
                 sp.setImageURL("http://pulsatrix.inbio.ac.cr/projects/atta2/chrome/site/header.png");
             
             speciesList.add(sp);
        }

        result.addAll(speciesList);

        return result;
	}


	@Override
	public Long taxonInSearchCount(List<BigDecimal> idList) throws ParseException {
		return taxonDAO.searchInCount(idList);
	}


	@Override
	public List<SpeciesLiteSDTO> basicPaginatedSearch(String searchText,
			int offset, int quantity) throws ParseException {
		// analyze and prepare basic search
		searchText = "(" + searchText + ")" + 
				" AND (taxonomicalRangeId:" + Taxon.TaxonomicalRange.SPECIES.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.SUBSPECIES.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.VARIETY.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.FORM.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.DOMAIN.getId() +
				")";
		
		return this.taxonPaginatedSearch(searchText, offset, quantity);
	}


	@Override
	public Long basicSearchCount(String searchText) throws ParseException {
		// analyze and prepare basis search
		searchText = "(" + searchText + ")" + 
				" AND (taxonomicalRangeId:" + Taxon.TaxonomicalRange.SPECIES.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.SUBSPECIES.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.VARIETY.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.FORM.getId() +
				" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.DOMAIN.getId() +
				")";
		
		return this.taxonSearchCount(searchText);
	}

}
