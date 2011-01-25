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
package org.inbio.neoportal.service.dto;

/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class OccurrenceLiteDTO extends BaseDTO implements Comparable {

    private String globalUniqueIdentifier;
	private String scientificName;
    private String country;
    private String province;
    private String county;
    private String locality;
	private String latitude;
	private String longitude;
    private String catalog;
    private String institution;

    public OccurrenceLiteDTO() {
    }

    public OccurrenceLiteDTO(String globalUniqueIdentifier, 
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

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.scientificName != null ? this.scientificName.hashCode() : 0);
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
        final OccurrenceLiteDTO other = (OccurrenceLiteDTO) obj;
        if ((this.scientificName == null) ? (other.scientificName != null) : !this.scientificName.equals(other.scientificName)) {
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

        OccurrenceLiteDTO ol = (OccurrenceLiteDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters & Setters */
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

    public String getGlobalUniqueIdentifier() {
        return globalUniqueIdentifier;
    }

    public void setGlobalUniqueIdentifier(String globalUniqueIdentifier) {
        this.globalUniqueIdentifier = globalUniqueIdentifier;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * @return the catalog
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * @param catalog the catalog to set
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

}
