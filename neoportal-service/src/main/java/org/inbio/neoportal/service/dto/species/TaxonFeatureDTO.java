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
package org.inbio.neoportal.service.dto.species;

import java.util.List;

import org.inbio.neoportal.core.common.dto.BaseDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Image;

/**
 * Model class to send feature info of a taxon
 * Include name, description, images from species in case of higher level taxon
 * @author avargas
 *
 */
public class TaxonFeatureDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String defaultName;
	private String briefDescription;
	private String taxonomicalRangeName;
	private List<ImagesCDTO> featureImages;
	private long imagesCount;
	private long occurrencesCount;
	
	public TaxonFeatureDTO () {
		
	}

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getTaxonomicalRangeName() {
		return taxonomicalRangeName;
	}

	public void setTaxonomicalRangeName(String taxonomicalRangeName) {
		this.taxonomicalRangeName = taxonomicalRangeName;
	}

	public List<ImagesCDTO> getFeatureImages() {
		return featureImages;
	}

	public void setFeatureImages(List<ImagesCDTO> featureImages) {
		this.featureImages = featureImages;
	}

	public long getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(long imagesCount) {
		this.imagesCount = imagesCount;
	}

	public long getOccurrencesCount() {
		return occurrencesCount;
	}

	public void setOccurrencesCount(long occurrencesCount) {
		this.occurrencesCount = occurrencesCount;
	}
	
}
