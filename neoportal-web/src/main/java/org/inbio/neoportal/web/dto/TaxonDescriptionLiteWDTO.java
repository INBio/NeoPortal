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
 * @author esmata
 */
@RooJavaBean
@RooToString
public class TaxonDescriptionLiteWDTO {

    @XmlElement(name="scname")
	private String scientificName;
    
	@XmlElement(name="cname")
	private String commonName;

	@XmlElement(name="inst")
	private String institution;

    // Constructor
    public TaxonDescriptionLiteWDTO() {
    }

    public TaxonDescriptionLiteWDTO(String scientificName) {
        
        this.scientificName = scientificName;
    }

    public TaxonDescriptionLiteWDTO
        (String scientificName, String commonName, String institution) {
        
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.institution = institution;
    }
}
