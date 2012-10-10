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

import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
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
			Locale locale
			) {
	
		ModelAndView modelAndView = new ModelAndView("groupNav");
		
		List<GroupNavCDTO> groupNavList = groupNavManager.getFirstLevel("Nombres comunes");
		
		sortList(groupNavList);
		
		modelAndView.addObject("language", locale.getLanguage());		
		modelAndView.addObject("groupNavList", groupNavList);
		modelAndView.addObject("selectedGN", id);
		
		return modelAndView;
	}

	private void sortList(List<GroupNavCDTO> groupNavList) {
		Collections.sort(groupNavList);
		
		for (GroupNavCDTO groupNavCDTO : groupNavList) {
			Collections.sort(groupNavCDTO.getGroupNavChilds());
		}
		
	}
	
}
