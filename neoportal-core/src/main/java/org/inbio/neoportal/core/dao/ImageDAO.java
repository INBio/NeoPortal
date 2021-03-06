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
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Image;

public interface ImageDAO extends
						GenericDAO<Image, BigInteger> {
	
	public List<Image> UnprocessedImages();
	
	/**
	 * 
	 * @param flickrId
	 * @return
	 */
	public Image findByFlickrId(BigInteger flickrId);

	public Image findM3sImage(BigInteger m3sId, BigDecimal taxonId);
	/**
	 * 
	 * @return
	 */
	public List<Image> findAll();
	
	public List<ImagesCDTO> searchPhrase(String field,
			String searchText, String sortField, int offset, int quantity);
	
	public Long searchPhraseCount(
			final String field,
	        final String searchText
	);
	
	/**
	 * 
	 * @param fields
	 * @param searchText
	 * @return
	 */
	public List<ImagesCDTO> search(
			String[] fields,
			String searchText);
}
