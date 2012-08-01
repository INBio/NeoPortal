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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.inbio.neoportal.service.manager.GroupNavManager;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	public HomePageController() {}
	
	@RequestMapping("/")
	protected ModelAndView homePage() {
		
		List<GroupNavCDTO> groupNav = groupNavManager.getFirstLevel();
		
		Collections.sort(groupNav);
		
		return new ModelAndView("home", "group_nav", groupNav);
	}
	
}
