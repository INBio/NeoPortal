/*
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
package org.inbio.neoportal.messagebean;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/*
 * @author jgutierrez
 */
@RooJavaBean
@RooToString
public class SpecimenLiteBean {

	@XmlElement(name="catalognumber")
	private String catalogNumber;
	
	@XmlElement(name="institutioncode")
	private String institutionCode;
	
	@XmlElement(name="scientificname")
	private String scientificName;
	
	@XmlElement(name="latitude")
	private String latitude;

	@XmlElement(name="longitude")
	private String longitude;

	
	/**
	 * 
	 */
	public SpecimenLiteBean() {
		super();
	}

	/**
	 * @param catalogNumber
	 * @param institutionCode
	 * @param scientificName
	 * @param latitude
	 * @param longitude
	 */
	public SpecimenLiteBean(String catalogNumber, String institutionCode,
			String scientificName, String latitude, String longitude) {
		this.catalogNumber = catalogNumber;
		this.institutionCode = institutionCode;
		this.scientificName = scientificName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
}
