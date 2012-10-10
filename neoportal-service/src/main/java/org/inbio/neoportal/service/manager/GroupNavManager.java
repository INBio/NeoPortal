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
package org.inbio.neoportal.service.manager;

import java.util.List;

import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;

/**
 * 
 * @author avargas
 *
 */
public interface GroupNavManager {

	/**
	 * 
	 * @return Return the nodes with parent = null 
	 */
	public List<GroupNavCDTO> getFirstLevel(String groupNavName);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public GroupNavCDTO getGroupNavById(int id);
	
	/**
	 * 
	 * @return Return a list because the first level
	 * is made by many nodes
	 */
	public List<GroupNavCDTO> getAllTree();
	
	/**
	 * Find all the species corresponding to indicate groupNav item
	 * @param id the GroupNav identification
	 * @return list of lite taxon description of the species
	 */
	public List<SpeciesLiteSDTO> getSpeciesByGroupNav(int id);
}
