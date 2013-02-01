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

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.service.dto.Response;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionLiteSDTO;
import org.inbio.neoportal.service.manager.GroupNavManager;
import org.inbio.neoportal.service.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author avargas
 *
 */
@Controller
public class HomePageController {

	@Autowired
	private GroupNavManager groupNavManager;
	
	@Autowired
	private SearchManager searchManager;
	
	public HomePageController() {
		
	}
	
//	@RequestMapping("/")
//	protected ModelAndView homePage() {
//		
//		List<GroupNavCDTO> groupNav = groupNavManager.getFirstLevel("Nombres comunes");
//		
//		Collections.sort(groupNav);
//		
//		return new ModelAndView("home", "group_nav", groupNav);
//	}
	
	@RequestMapping("/")
	protected ModelAndView testHome(
			@RequestParam(value="q", required=false) String search,
			@RequestParam (value = "startIndex", defaultValue = "0", required=false) int startIndex,
	        @RequestParam (value = "itemsPerPage", defaultValue = "10", required=false) int itemsPerPage,
	        HttpServletRequest request
			){
		
		Response response;
		String requestUrl;
		String nextUrl = "";
		String backUrl = "";
		String lastUrl = "";
		int totalPages;
		int totalSpecies = 0;
		
		//set the view
		ModelAndView modelAndView = new ModelAndView("home");
		/* Define the state of the page
		* home = first view, comes with none search query
		* search = comes with search query
		*/
		if(search == null	){
			modelAndView.addObject("state","home");
			//groupNav style, grid for home 
			modelAndView.addObject("style", "grid");
			//get the necessary data
			List<GroupNavCDTO> groupNav = groupNavManager.getFirstLevel("Nombres comunes");
			Collections.sort(groupNav);
			modelAndView.addObject("group_nav", groupNav);
		}
		else{
			modelAndView.addObject("state","search");
			
			//get search data
			List<SpeciesLiteSDTO> list = null;
			try {
				list = searchManager.basicPaginatedSearch(search, startIndex, itemsPerPage);
				totalSpecies = searchManager.basicSearchCount(search).intValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//prepare pagination
			requestUrl = request.getRequestURL().toString();
			requestUrl += "?q=" + search;
			requestUrl += "&itemsPerPage=" + itemsPerPage;
			
			if(startIndex + itemsPerPage < totalSpecies)
				nextUrl = requestUrl + "&startIndex=" + (startIndex + itemsPerPage);
			
			if(startIndex >= itemsPerPage)
				backUrl = requestUrl + "&startIndex=" + (startIndex - itemsPerPage);
			
			totalPages = (int) (totalSpecies / itemsPerPage);
			
			lastUrl = requestUrl + "&startIndex=" + (totalPages * itemsPerPage);
			
			if(totalPages * itemsPerPage < totalSpecies)
				totalPages++;

			modelAndView.addObject("taxonList", list);
			modelAndView.addObject("paginationFirstUrl", requestUrl);
			modelAndView.addObject("paginationNextUrl", nextUrl);
			modelAndView.addObject("paginationBackUrl", backUrl);
			modelAndView.addObject("paginationLastUrl", lastUrl);
			modelAndView.addObject("paginationCurrent", (startIndex / itemsPerPage) + 1);
			modelAndView.addObject("paginationTotal", totalPages);

			
		}
		
		
		return modelAndView;
	}
	
}
