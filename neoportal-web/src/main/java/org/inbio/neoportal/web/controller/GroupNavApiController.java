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

import org.inbio.neoportal.service.manager.GroupNavManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author avargas
 *
 */
@Controller
@RequestMapping("/api/groupNav/*")
public class GroupNavApiController {

	@Autowired
	private GroupNavManager groupNavManager;
	
//	@RequestMapping(
//			value="getFirstLevel")
//    @ResponseBody
//    public Object firstLevel(){
//		
//		
//		return groupNavManager.getFirstLevel();
//	}
	
	@RequestMapping(
			value="", 
			headers="Accept=application/json")
    @ResponseBody
	public Object groupNavById(
			@RequestParam (value="gni", required=true) int id
			) {
	
		return groupNavManager.getGroupNavById(id);
	}
	
	@RequestMapping(
			value="species",
			headers="Accept=application/json")
	@ResponseBody
	public Object getSpeciesByGn(
			@RequestParam (value="gni", required=true) int id
			){
		
		return null;
	}
	
	@RequestMapping(
			value="getAllTree")
    @ResponseBody
	public Object getAllTree() {
	
		return groupNavManager.getAllTree();
	}
}
