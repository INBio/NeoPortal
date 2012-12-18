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
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.service.dto.Response;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
import org.inbio.neoportal.service.manager.GroupNavManager;
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
@RequestMapping("/groupNav")
public class GroupNavController {

	@Autowired
	private GroupNavManager groupNavManager;
		
	@RequestMapping(
			value="")
	public ModelAndView groupNavById(
			@RequestParam (value="gni", required=false) String id,
			@RequestParam (value = "startIndex", defaultValue = "0", required=false) int startIndex,
	        @RequestParam (value = "itemsPerPage", defaultValue = "10", required=false) int itemsPerPage,
	        HttpServletRequest request
			) {
	
		ModelAndView modelAndView;
		List<GroupNavCDTO> groupNavList;
		Response response;
		String requestUrl;
		String nextUrl = "";
		String backUrl = "";
		String lastUrl = "";
		int totalPages;
		
		modelAndView = new ModelAndView("groupNav");
		
		//get common name browse data
		groupNavList = groupNavManager.getFirstLevel("Nombres comunes");
		sortList(groupNavList);
		
		//get species list to show on results
		response = groupNavManager.getSpeciesByGroupNav(Integer.parseInt(id), startIndex, itemsPerPage);
		
		//prepare pagination
		requestUrl = request.getRequestURL().toString();
		requestUrl += "?gni=" + id;
		requestUrl += "&itemsPerPage=" + itemsPerPage;
		
		if(startIndex + itemsPerPage < response.getTotal())
			nextUrl = requestUrl + "&startIndex=" + (startIndex + itemsPerPage);
		
		if(startIndex >= itemsPerPage)
			backUrl = requestUrl + "&startIndex=" + (startIndex - itemsPerPage);
		
		totalPages = (int) (response.getTotal() / itemsPerPage);
		
		lastUrl = requestUrl + "&startIndex=" + (totalPages * itemsPerPage);
		
		if(totalPages * itemsPerPage < response.getTotal())
			totalPages++;

		modelAndView.addObject("groupNavList", groupNavList);
		modelAndView.addObject("gni", id);
		modelAndView.addObject("taxonList", response.getResult());
		modelAndView.addObject("paginationFirstUrl", requestUrl);
		modelAndView.addObject("paginationNextUrl", nextUrl);
		modelAndView.addObject("paginationBackUrl", backUrl);
		modelAndView.addObject("paginationLastUrl", lastUrl);
		modelAndView.addObject("paginationCurrent", (startIndex / itemsPerPage) + 1);
		modelAndView.addObject("paginationTotal", totalPages);
		
		return modelAndView;
	}

	private void sortList(List<GroupNavCDTO> groupNavList) {
		Collections.sort(groupNavList);
		
		for (GroupNavCDTO groupNavCDTO : groupNavList) {
			Collections.sort(groupNavCDTO.getGroupNavChilds());
		}
		
	}
	
}
