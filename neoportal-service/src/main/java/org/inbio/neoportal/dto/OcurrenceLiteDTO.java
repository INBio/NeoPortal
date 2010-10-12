/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.dto;

/**
 *
 * @author asanabria
 */
public class OcurrenceLiteDTO extends BaseDTO {

    private String globalUniqueIdentifier;
	private String catalogNumber;
	private String institutionCode;
	private String scientificName;
	private String latitude;
	private String longitude;

    public OcurrenceLiteDTO() {
    }

    public OcurrenceLiteDTO(String globalUniqueIdentifier, String catalogNumber, String institutionCode, String scientificName, String latitude, String longitude) {
        this.globalUniqueIdentifier = globalUniqueIdentifier;
        this.catalogNumber = catalogNumber;
        this.institutionCode = institutionCode;
        this.scientificName = scientificName;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
