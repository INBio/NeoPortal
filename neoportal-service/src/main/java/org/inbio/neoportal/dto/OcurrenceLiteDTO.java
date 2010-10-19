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
package org.inbio.neoportal.dto;

/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class OcurrenceLiteDTO extends BaseDTO {

    private String globalUniqueIdentifier;
	private String catalogNumber;
	private String institutionCode;
	private String scientificName;
	private String latitude;
	private String longitude;
    private String locality;

    public OcurrenceLiteDTO() {
    }

    public OcurrenceLiteDTO(String globalUniqueIdentifier, String catalogNumber, String institutionCode, String scientificName, String latitude, String longitude, String locality) {
        this.globalUniqueIdentifier = globalUniqueIdentifier;
        this.catalogNumber = catalogNumber;
        this.institutionCode = institutionCode;
        this.scientificName = scientificName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locality = locality;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getGlobalUniqueIdentifier() {
        return globalUniqueIdentifier;
    }

    public void setGlobalUniqueIdentifier(String globalUniqueIdentifier) {
        this.globalUniqueIdentifier = globalUniqueIdentifier;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
