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
import org.inbio.neoportal.service.manager.SearchManager;
import org.inbio.neoportal.web.dto.OccurrenceLiteWDTO;
import org.inbio.neoportal.web.dto.wrapper.XMLSpecimenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Métodos para accesar a la información de ocurrencias 
 * como API retornando datos en xml
 * @author arturo
 */
@Controller
public class OccurrencesApiController {
    
    @Autowired
    private SearchManager searchManagerImpl;
    
    @RequestMapping (
            value = "/api/occurrences",
            method = RequestMethod.GET
            //params={"taxonName", "startIndex=0", "results=10"}
            )
    public @ResponseBody XMLSpecimenWrapper searchOccurrencesWriteXml (
            @RequestParam String taxonName,
            @RequestParam (value = "startIndex", defaultValue="0", required=false) int startIndex,
            @RequestParam (value = "results", defaultValue="10", required=false) int results) {

        List<OccurrenceLiteSDTO> occurrenceList = null;

        XMLSpecimenWrapper rw = new XMLSpecimenWrapper();
        try {
            //get total record set, usefull for pagination
            rw.setCount(searchManagerImpl.occurrenceSearchCount(taxonName));
            
            occurrenceList 
                = searchManagerImpl.occurrencePaginatedSearch(taxonName, startIndex, results); 
            
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
}
