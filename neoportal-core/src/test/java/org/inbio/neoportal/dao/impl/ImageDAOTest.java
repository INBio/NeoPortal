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
import java.math.BigInteger;

import org.inbio.neoportal.core.dao.ImageDAO;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.test.NeoportalTestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author avargas
 *
 */
public class ImageDAOTest 
				extends NeoportalTestBase {

	@Autowired
	ImageDAO imageDAO;
	
	@Before
	public void setup() {
		if(imageDAO.findAll().isEmpty()){
			Image image = new Image();
//			image.setImageId(new BigDecimal(3));
			image.setSource("flickr");
			image.setExternalImageId(new BigInteger("1234"));
			
			imageDAO.create(image);
		}
	}
	
	@Test
	public void testFindByFlickrId() {
		Image image = imageDAO.findByFlickrId(new BigInteger("1234"));
		Assert.assertNotNull(image);
	}
}