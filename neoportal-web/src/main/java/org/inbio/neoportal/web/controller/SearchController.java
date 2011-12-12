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
package org.inbio.neoportal.web.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionLiteSDTO;
import org.inbio.neoportal.service.manager.SearchManager;
import org.inbio.neoportal.web.dto.SpeciesLiteWDTO;
import org.inbio.neoportal.web.dto.OccurrenceLiteWDTO;
import org.inbio.neoportal.web.dto.TaxonDescriptionLiteWDTO;
import org.inbio.neoportal.web.dto.wrapper.XMLCountWrapper;
import org.inbio.neoportal.web.dto.wrapper.XMLSpeciesWrapper;
import org.inbio.neoportal.web.dto.wrapper.XMLSpecimenWrapper;
import org.inbio.neoportal.web.dto.wrapper.XMLTaxonDescriptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jgutierrez
 * @author esmata
 * @author asanabria
 */
@Controller
@RequestMapping("api/search/*")
public class SearchController {

    @Autowired
    private SearchManager searchManagerImpl;

    /**
     * Get a well formated xml containing paginated species
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/species*", 
        method=RequestMethod.GET, 
        params={"format=xml","searchString", "startIndex", "results"})
    
    public @ResponseBody XMLTaxonDescriptionWrapper searchTaxonDescriptionWriteXml(
        @RequestParam String searchString,
        @RequestParam int startIndex,
        @RequestParam int results) {
        List<TaxonDescriptionLiteSDTO> speciesList = null;

        XMLTaxonDescriptionWrapper rw = new XMLTaxonDescriptionWrapper();
        
        try {
            
            rw.setCount(searchManagerImpl.speciesSearchCount(searchString));
            
            speciesList = searchManagerImpl
                .speciesPaginatedSearch(searchString, startIndex , results); 

            for(TaxonDescriptionLiteSDTO spDTO : speciesList)
                rw.addElement(new TaxonDescriptionLiteWDTO(
                    spDTO.getScientificName(),
                    spDTO.getCommonNameList(),
                    spDTO.getInstitution()));

        } catch (Exception ex) {
            Logger.getLogger(
                SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rw;     
    }

    /**
     * Get a string containing the total count of species by searchString
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/countSpecies",
        method=RequestMethod.GET, 
        params={"format=xml","searchString"})
    
    public @ResponseBody XMLCountWrapper countTaxonDescriptionWriteXml 
        (@RequestParam String searchString) 
            throws Exception {
        
        XMLCountWrapper cw = new XMLCountWrapper();
        cw.setCount(searchManagerImpl.speciesSearchCount(searchString));
        return cw;
    }

    /**
     * Get a well formated xml containing paginated species
     * @param searchTerms
     * @return
     */
    @RequestMapping(
        value="/taxa", 
        method=RequestMethod.GET)
    public @ResponseBody XMLSpeciesWrapper searchTaxonWriteXml(
        @RequestParam (value = "searchTerms") String searchTerms,
        @RequestParam (value = "startIndex") int startIndex,
        @RequestParam (value = "itemsPerPage", defaultValue = "10") int itemsPerPage,
        @RequestParam (value = "format", defaultValue = "xml") String format) {
        
        List<SpeciesLiteSDTO> speciesList = null;

        XMLSpeciesWrapper rw = new XMLSpeciesWrapper();
        try {
            //filter taxon range to match only species
            //TODO: change taxonomical Range number for enum
            searchTerms = "(" + searchTerms + ") AND (taxonomicalRangeId:19 taxonomicalRangeId:20 taxonomicalRangeId:21 taxonomicalRangeId:22)";
            
            //include the count, this reduce one server call
            rw.setCount(searchManagerImpl.taxonSearchCount(searchTerms));
            
            speciesList = searchManagerImpl
                .taxonPaginatedSearch(searchTerms, startIndex, itemsPerPage); 

            for(SpeciesLiteSDTO spDTO : speciesList)
                rw.addElement(new SpeciesLiteWDTO(
                spDTO.getImageURL(),
                spDTO.getCommonName(),
                spDTO.getScientificName()));

        } catch (Exception ex) {
            Logger.getLogger(
                SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rw;
    }

    /**
     * Get a string containing the total count of species by searchString
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/countTaxa",
        method=RequestMethod.GET, 
        params={"format=xml","searchString"})
    
    public @ResponseBody XMLCountWrapper countTaxonWriteXml 
        (@RequestParam String searchString) 
            throws Exception {
        
        XMLCountWrapper cw = new XMLCountWrapper();
        cw.setCount(searchManagerImpl.taxonSearchCount(searchString));
        return cw;
    }
 
    /**
     * Get a well formated xml containing paginated occurrences
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/occurrences", 
        method=RequestMethod.GET)
    public @ResponseBody XMLSpecimenWrapper searchOccurrencesWriteXml
        (@RequestParam (value="searchString") String searchString,
         @RequestParam (value = "startIndex", defaultValue="0") int startIndex,
         @RequestParam (value = "results", defaultValue="10") int results,
         @RequestParam (value = "format", defaultValue="xml") String format) {

        List<OccurrenceLiteSDTO> occurrenceList = null;

        XMLSpecimenWrapper rw = new XMLSpecimenWrapper();
        try {
            //get total record set, usefull for pagination
            rw.setCount(searchManagerImpl.occurrenceSearchCount(searchString));
            
            occurrenceList 
                = searchManagerImpl.occurrencePaginatedSearch(searchString, startIndex, results); 
            
            for(OccurrenceLiteSDTO olDTO : occurrenceList)
                rw.addElement(
                    new OccurrenceLiteWDTO(
                        olDTO.getOccurrenceId(),    
                        olDTO.getScientificName(),
                        olDTO.getCountry(),
                        olDTO.getProvince(),
                        olDTO.getCounty(),      
                        olDTO.getLocality(),
                        olDTO.getLatitude(),
                        olDTO.getLongitude(),
                        olDTO.getCatalog(),
                        olDTO.getInstitution()));

        } catch (Exception ex) {
            Logger.getLogger(
                SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rw;
    }
    
    /**
     * Get a string containing the total count of occurrences by searchString
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/countOcurrences",
        method=RequestMethod.GET,
        params={"format=xml","searchString"})
    
    public @ResponseBody XMLCountWrapper countOccurrencesWriteXml
        (@RequestParam String searchString) 
            throws Exception {
        XMLCountWrapper cw = new XMLCountWrapper();
        cw.setCount(searchManagerImpl.occurrenceSearchCount(searchString));
        return cw;
    }
    
    
    
    public SearchManager getSearchManagerImpl() {
        return searchManagerImpl;
    }

    public void setSearchManagerImpl(SearchManager searchManagerImpl) {
        this.searchManagerImpl = searchManagerImpl;
    }
}
