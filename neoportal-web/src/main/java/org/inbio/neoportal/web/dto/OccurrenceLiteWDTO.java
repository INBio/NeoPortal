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
package org.inbio.neoportal.web.dto;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/*
 * @author jgutierrez
 */
@RooJavaBean
@RooToString
public class OccurrenceLiteWDTO {


	@XmlElement(name="gui")
    private String globalUniqueIdentifier;

	@XmlElement(name="scientificname")
	private String scientificName;

	@XmlElement(name="country")
    private String country;

	@XmlElement(name="province")
    private String province;

	@XmlElement(name="county")
    private String county;

	@XmlElement(name="locality")
    private String locality;

	@XmlElement(name="latitude")
	private String latitude;

	@XmlElement(name="longitude")
	private String longitude;

	@XmlElement(name="catalog")
	private String catalog;

	@XmlElement(name="institution")
	private String institution;
	
	/**
	 * No argument contructor
	 */
	public OccurrenceLiteWDTO() {
		super();
	}
   /**
     * Argument constructor
     * @param scientificName 
     */
    public OccurrenceLiteWDTO(String scientificName) {
        this.scientificName = scientificName;
    }
   
    /**
     * Argument constructor
     * @param globalUniqueIdentifier
     * @param scientificName
     * @param country
     * @param province
     * @param county
     * @param locality
     * @param latitude
     * @param longitude
     */
    public OccurrenceLiteWDTO(String globalUniqueIdentifier,
            String scientificName,
            String country,
            String province,
            String county,
            String locality,
            String latitude,
            String longitude,
            String catalog,
            String institution) {

        this.globalUniqueIdentifier = globalUniqueIdentifier;
        this.scientificName = scientificName;
        this.country = country;
        this.province = province;
        this.county = county;
        this.locality = locality;
        this.latitude = latitude;
        this.longitude = longitude;
        this.catalog = catalog;
        this.institution = institution;
    }
}
