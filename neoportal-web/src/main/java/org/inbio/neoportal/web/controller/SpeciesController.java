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

import javax.servlet.http.HttpServletRequest;

import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.manager.SpeciesManager;
import org.inbio.neoportal.web.model.PaginationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/species/*")
public class SpeciesController {
	
	@Autowired
    private SpeciesManager speciesManager;
    
    @RequestMapping (
            value = "/{defaultName}",
            method = RequestMethod.GET)
    public String getTaxonDescriptionByProvider (
            Model model,
            @PathVariable(value = "defaultName") String defaultName,
	        HttpServletRequest request) {
    	
    	String taxonUrl = request.getContextPath() + "/species/" + defaultName;
        
    	// get taxon description
        List<TaxonDescriptionFullSDTO> taxonDescription = null;
        
        defaultName = defaultName.replace('_', ' ');
        
        taxonDescription = 
                speciesManager.taxonDescriptionByProvider(defaultName, "INB");
        
        if(taxonDescription.isEmpty()) {
        	model.addAttribute("taxonDescription", null);
        }
        else
        	model.addAttribute("taxonDescription", taxonDescription.get(0));
        
        // get taxon hierarchy 
        Taxon taxon = speciesManager.getTaxonByDefaultName(defaultName);

        // get some images
        List<ImagesCDTO> images = speciesManager.getImagesByDefaultName(defaultName, 0, 4);
        
        model.addAttribute("taxon", taxon);
        model.addAttribute("context", "taxonDescription");
        model.addAttribute("taxonUrl", taxonUrl);
        model.addAttribute("images", images);
        
        return "species";
    }
    
    @RequestMapping (
            value = "/{defaultName}/images",
            method = RequestMethod.GET)
    public String getTaxonImages (
            Model model,
            @PathVariable (value = "defaultName") String defaultName,
            @RequestParam (value = "startIndex", defaultValue = "0", required=false) int startIndex,
	        @RequestParam (value = "itemsPerPage", defaultValue = "10", required=false) int itemsPerPage,
	        HttpServletRequest request
	        ) {
    	
    	String taxonUrl = request.getContextPath() + "/species/" + defaultName;
        
    	String requestUrl;
		String nextUrl = "";
		String previousUrl = "";
		String lastUrl = "";
		int totalPages;
		int totalImages = 0;

        List<ImagesCDTO> images = speciesManager.getImagesByDefaultName(defaultName, startIndex, itemsPerPage);
        totalImages = speciesManager.countImagesByDefaultName(defaultName).intValue();
		
		//prepare pagination
		requestUrl = request.getRequestURL().toString();
		requestUrl += "?itemsPerPage=" + itemsPerPage;
		
		if(startIndex + itemsPerPage < totalImages)
			nextUrl = requestUrl + "&startIndex=" + (startIndex + itemsPerPage);
		
		if(startIndex >= itemsPerPage)
			previousUrl = requestUrl + "&startIndex=" + (startIndex - itemsPerPage);
		
		totalPages = (int) (totalImages / itemsPerPage);
		
		lastUrl = requestUrl + "&startIndex=" + (totalPages * itemsPerPage);
		
		if(totalPages * itemsPerPage < totalImages)
			totalPages++;
		
		PaginationModel pagination = new PaginationModel();
		pagination.setCurrentPage(String.valueOf((startIndex / itemsPerPage) + 1));
		pagination.setTotalPages(String.valueOf(totalPages));
		pagination.setFirstUrl(requestUrl);
		pagination.setNextUrl(nextUrl);
		pagination.setPreviousUrl(previousUrl);
		pagination.setLastUrl(lastUrl);
		
        // get taxon hierarchy 
        Taxon taxon = speciesManager.getTaxonByDefaultName(defaultName);

        model.addAttribute("images", images);
        model.addAttribute("taxon", taxon);
        model.addAttribute("pagination", pagination);
        model.addAttribute("context", "images");
        model.addAttribute("taxonUrl", taxonUrl);
        
        return "species";
    }
    
    @RequestMapping (
            value = "/{defaultName}/occurrences",
            method = RequestMethod.GET)
    public String getTaxonOccurrences (
            Model model,
            @PathVariable (value = "defaultName") String defaultName,
            @RequestParam (value = "startIndex", defaultValue = "0", required=false) int startIndex,
	        @RequestParam (value = "itemsPerPage", defaultValue = "20", required=false) int itemsPerPage,
	        HttpServletRequest request
	        ) {
        
    	String taxonUrl = request.getContextPath() + "/species/" + defaultName;
        
    	String requestUrl;
		String nextUrl = "";
		String previousUrl = "";
		String lastUrl = "";
		int totalPages;
		int totalOccurrences = 0;

        List<OccurrenceDwcCDTO> occurrences = speciesManager.getOccurrencesByDefaultName(defaultName, startIndex, itemsPerPage);
        totalOccurrences = speciesManager.countOccurrencesByDefaultName(defaultName).intValue();
		
		//prepare pagination
		requestUrl = request.getRequestURL().toString();
		requestUrl += "?itemsPerPage=" + itemsPerPage;
		
		if(startIndex + itemsPerPage < totalOccurrences)
			nextUrl = requestUrl + "&startIndex=" + (startIndex + itemsPerPage);
		
		if(startIndex >= itemsPerPage)
			previousUrl = requestUrl + "&startIndex=" + (startIndex - itemsPerPage);
		
		totalPages = (int) (totalOccurrences / itemsPerPage);
		
		lastUrl = requestUrl + "&startIndex=" + (totalPages * itemsPerPage);
		
		if(totalPages * itemsPerPage < totalOccurrences)
			totalPages++;
		
		PaginationModel pagination = new PaginationModel();
		pagination.setCurrentPage(String.valueOf((startIndex / itemsPerPage) + 1));
		pagination.setTotalPages(String.valueOf(totalPages));
		pagination.setFirstUrl(requestUrl);
		pagination.setNextUrl(nextUrl);
		pagination.setPreviousUrl(previousUrl);
		pagination.setLastUrl(lastUrl);
		
        // get taxon hierarchy 
        Taxon taxon = speciesManager.getTaxonByDefaultName(defaultName);

        model.addAttribute("occurrences", occurrences);
        model.addAttribute("taxon", taxon);
        model.addAttribute("pagination", pagination);
        model.addAttribute("context", "occurrences");
        model.addAttribute("taxonUrl", taxonUrl);
        if(totalOccurrences < 3000)
        	model.addAttribute("scientificName", defaultName);
        
        return "species";
    }
}

