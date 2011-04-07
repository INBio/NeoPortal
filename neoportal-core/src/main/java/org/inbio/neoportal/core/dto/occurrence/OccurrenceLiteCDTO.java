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
package org.inbio.neoportal.core.dto.occurrence;

import java.util.ArrayList;
import java.util.List;
import org.inbio.neoportal.common.dto.*;

/**
 * A subset of the data hold by the taxon entity
 * @author asanabria
 */
public class OccurrenceLiteCDTO 
    extends BaseDTO 
        implements Comparable {

    
    private String scientificName;
    private String institution;
    private String country;
    private String province;
    private String county;
    private String locality;
    private String catalog;
    
    private ArrayList<OccurrenceGeospatialLiteCDTO> geospatialList;

    public OccurrenceLiteCDTO() {
    }

    public OccurrenceLiteCDTO(String scientificName) {

        this.scientificName = scientificName;
    }

    public OccurrenceLiteCDTO(
                String scientificName, 
                String institution,
                String country,
                String province,
                String county,
                String locality,
                String catalog,
                ArrayList<OccurrenceGeospatialLiteCDTO> list) {
        
        this.scientificName = scientificName;
        this.institution = institution;
        this.country = country;
        this.province = province;
        this.county = county;
        this.locality = locality;
        this.catalog = catalog;
        this.geospatialList = list;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 23 * hash + 
            (this.scientificName != null ? this.scientificName.hashCode() : 0);
        
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
        
        final OccurrenceLiteCDTO other = (OccurrenceLiteCDTO) obj;
        
        if ((this.scientificName == null) ? 
                (other.scientificName != null) :
                !this.scientificName.equals(other.scientificName)) {
            
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

        OccurrenceLiteCDTO ol = (OccurrenceLiteCDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters & Setters */

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ArrayList<OccurrenceGeospatialLiteCDTO> getGeospatialList() {
        return geospatialList;
    }

    public void setGeospatialList(ArrayList<OccurrenceGeospatialLiteCDTO> geospatialList) {
        this.geospatialList = geospatialList;
    }
    
}