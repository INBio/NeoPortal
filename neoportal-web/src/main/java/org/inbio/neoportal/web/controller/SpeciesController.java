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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/species/*")
public class SpeciesController {

//    @RequestMapping(value = "/{scientificname}", method = RequestMethod.GET)
//    public String prepareSN
//        (Model model, @PathVariable(value = "scientificname") String scientificName) {
//        
//        model.addAttribute("scientificname", scientificName);
//        return "species"; //Return occurrences view with scientificname as a parameter into the model
//    }
    
    @RequestMapping (
            value = "/{scientificName}",
            method = RequestMethod.GET)
    public String getTaxonDescriptionByProvider (
            Model model,
            @PathVariable(value = "scientificName") String scientificName) {
        
        model.addAttribute("scientificName", scientificName);
        //model.addAttribute("provider", provider);
        
        return "taxonDescription";
    }
}


