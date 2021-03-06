/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.entity.GroupNav;

/**
 * @author avargas
 *
 */
public interface GroupNavDAO 
		extends GenericDAO<GroupNav, BigDecimal> {

	public enum TreePart{LEVEL, CHILD_SIBLING};
	
	public GroupNavCDTO getById(final BigDecimal id);
	
	public List<GroupNavCDTO> getFirstLevel(String groupNavName);
	
	public List<GroupNavCDTO> getTreePart(String groupNavName, String treePart );
	
	/**
	 * Return parent entity with all childs prefetch in one select
	 * @param id
	 * @return
	 */
	public GroupNav getChilds(BigDecimal id);

  /**
   * @param entityClass
   * @return
   */
  List<GroupNav> findAll(Class<GroupNav> entityClass);
}
