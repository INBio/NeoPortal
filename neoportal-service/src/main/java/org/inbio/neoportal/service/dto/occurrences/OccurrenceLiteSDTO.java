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
package org.inbio.neoportal.service.dto.occurrences;

import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 *
 * @author esmata
 * @author asanabria
 */
public class OccurrenceLiteSDTO 
    extends BaseDTO 
        implements Comparable {
    
    private String occurrenceId;
    private String scientificName;
    private String institution;
    private String country;
    private String province;
    private String county;
    private String locality;
    private String latitude;
    private String longitude;
    private String catalog;
    

    public OccurrenceLiteSDTO() {
    }

    public OccurrenceLiteSDTO
        (String scientificName, String latitude, String longitude) {
        
        this.scientificName = scientificName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public OccurrenceLiteSDTO(
        String occurrenceId,
        String scientificName, 
        String institution, 
        String country, 
        String province, 
        String county, 
        String locality, 
        String latitude, 
        String longitude, 
        String catalog) {
        
        this.occurrenceId = occurrenceId;
        this.scientificName = scientificName;
        this.institution = institution;
        this.country = country;
        this.province = province;
        this.county = county;
        this.locality = locality;
        this.latitude = latitude;
        this.longitude = longitude;
        this.catalog = catalog;
    }


    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash 
            + (this.getScientificName() != null 
                ? this.getScientificName().hashCode() 
                : 0);
        
        return hash;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OccurrenceLiteSDTO other = (OccurrenceLiteSDTO) obj;
        
        if ((this.getScientificName() == null) 
             ? (other.getScientificName() != null) 
             : !this.scientificName.equals(other.scientificName)) {
            
            return false;
        }
        return true;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int compareTo(Object o) {

        OccurrenceLiteSDTO ol = (OccurrenceLiteSDTO)o;
        return this.getScientificName().compareTo(ol.getScientificName());
    }
    
    /* Getthers & Setters */

    public String getCatalog() {
        return catalog;
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getInstitution() {
        return institution;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLocality() {
        return locality;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getProvince() {
        return province;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getOccurrenceId(){
        return occurrenceId;
    }
    
}
