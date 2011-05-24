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
import org.apache.lucene.queryParser.ParseException;
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
@RequestMapping("search/*")
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
            speciesList = searchManagerImpl
                .speciesPaginatedSearch(searchString, startIndex , results); 

            for(TaxonDescriptionLiteSDTO spDTO : speciesList)
                rw.addElement(new TaxonDescriptionLiteWDTO(
                    spDTO.getScientificName(),
                    spDTO.getCommonNameList(),
                    spDTO.getInstitution()));

        } catch (ParseException ex) {
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
            throws ParseException {
        
        XMLCountWrapper cw = new XMLCountWrapper();
        cw.setCount(searchManagerImpl.speciesSearchCount(searchString));
        return cw;
    }

    /**
     * Get a well formated xml containing paginated species
     * @param searchString
     * @return
     */
    @RequestMapping(
        value="/taxa", 
        method=RequestMethod.GET, 
        params={"format=xml","searchString", "startIndex", "results"})
    
    public @ResponseBody XMLSpeciesWrapper searchTaxonWriteXml(
        @RequestParam String searchString,
        @RequestParam int startIndex,
        @RequestParam int results) {
        
        List<SpeciesLiteSDTO> speciesList = null;

        XMLSpeciesWrapper rw = new XMLSpeciesWrapper();
        try {
            speciesList = searchManagerImpl
                .taxonPaginatedSearch(searchString, startIndex , results); 

            for(SpeciesLiteSDTO spDTO : speciesList)
                rw.addElement(new SpeciesLiteWDTO(
                spDTO.getImageURL(),
                spDTO.getCommonName(),
                spDTO.getScientificName()));

        } catch (ParseException ex) {
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
            throws ParseException {
        
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
        method=RequestMethod.GET, 
        params={"format=xml","searchString", "startIndex", "results"})
    
    public @ResponseBody XMLSpecimenWrapper searchOccurrencesWriteXml
        (@RequestParam String searchString,
         @RequestParam int startIndex,
         @RequestParam int results) {

        List<OccurrenceLiteSDTO> occurrenceList = null;

        XMLSpecimenWrapper rw = new XMLSpecimenWrapper();
        try {
            occurrenceList 
                = searchManagerImpl.occurrencePaginatedSearch(searchString, startIndex, results); 
            
            for(OccurrenceLiteSDTO olDTO : occurrenceList)
                rw.addElement(
                    new OccurrenceLiteWDTO(
                        olDTO.getScientificName(),
                        olDTO.getCountry(),
                        olDTO.getProvince(),
                        olDTO.getCounty(),      
                        olDTO.getLocality(),
                        olDTO.getLatitude(),
                        olDTO.getLongitude(),
                        olDTO.getCatalog(),
                        olDTO.getInstitution()));

        } catch (ParseException ex) {
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
            throws ParseException {
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
