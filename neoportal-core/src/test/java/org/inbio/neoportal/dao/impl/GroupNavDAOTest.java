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
package org.inbio.neoportal.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.inbio.neoportal.core.dao.GroupNavDAO;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.entity.GroupNav;
import org.inbio.neoportal.core.test.NeoportalTestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupNavDAOTest 
	extends NeoportalTestBase {
	
	@Autowired
	GroupNavDAO groupNavDAO;
	
	@Before
	public void setup(){
		if(groupNavDAO.findAll(GroupNav.class).isEmpty()){
			GroupNav groupNav = new GroupNav();
			GroupNav groupNavParent = new GroupNav();
			
			groupNavParent.setGroupNavId(new BigDecimal(0));
			groupNavParent.setTaxonId(null);
			groupNavParent.setGroupNavParent(null);
			groupNavParent.setName("test");
			
			groupNavDAO.create(groupNavParent);
			
			//first level items
			
			groupNav.setGroupNavId(new BigDecimal(1));
			groupNav.setTaxonId(null);
			groupNav.setGroupNavParent(groupNavParent);
			
			groupNavDAO.create(groupNav);
			
			groupNav.setGroupNavId(new BigDecimal(2));
			groupNav.setTaxonId(null);
			groupNav.setGroupNavParent(groupNavParent);
			
			groupNavDAO.create(groupNav);
			
			groupNav.setGroupNavId(new BigDecimal(3));
			groupNav.setTaxonId(null);
			groupNav.setGroupNavParent(groupNavParent);
			
			groupNavDAO.create(groupNav);
						
			//second level items
			groupNav.setGroupNavId(new BigDecimal(4));
			groupNav.setTaxonId(null);
			groupNav.setGroupNavParent(
					groupNavDAO.findById(GroupNav.class, new BigDecimal(1)));
			
			groupNavDAO.create(groupNav);
			
			groupNav.setGroupNavId(new BigDecimal(5));
			groupNav.setTaxonId(null);
			groupNav.setGroupNavParent(
					groupNavDAO.findById(GroupNav.class, new BigDecimal(1)));
			
			groupNavDAO.create(groupNav);
			
		}
	}
	
	@Test
	public void testGetFirstLevel(){
		List<GroupNavCDTO> items = groupNavDAO.getFirstLevel("test");
		
		Assert.assertEquals(items.size(), 3);
	}
}
