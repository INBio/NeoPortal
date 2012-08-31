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

import java.util.HashMap;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.inbio.neoportal.common.dto.*;
import org.inbio.neoportal.core.dto.LocationCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.entity.DataProvider;
import org.inbio.neoportal.core.entity.Location;
import org.inbio.neoportal.core.entity.Taxon;

/**
 * Occurrence record
 * @author avargas
 */
public class OccurrenceDwcCDTO 
    extends BaseDTO 
        implements Comparable {

	private String Id;
    private LocationCDTO location;
    
    /**  
     * Fields based on DarwinCore Terms http://rs.tdwg.org/dwc/terms/index.htm  
     **/
	//Record-level terms
    private String type;
    private String modified;
    private String language;
    private String rights;
    private String rightsHolder;
    private String accessRights;
    private String bibliographicCitation;
    private String references;
    private String institutionId;
    private String collectionId;
    private String datasetId;
    private String institutionCode;
    private String collectionCode;
    private String datasetName;
    private String ownerInstitutionCode;
    private String basisOfRecord;
    private String informationWithheld;
    private String dataGeneralizations;
    private String dynamicProperties;
    //Occurrence
    private String occurrenceId;
    private String catalogNumber;
    private String occurrenceRemarks;
    private String recordNumber;
    private String recordedBy;
    private String individualId;
    private String individualCount;
    private String sex;
    private String lifeStage;
    private String reproductiveCondition;
    private String behavior;
    private String establishmentMeans;
    private String occurrenceStatus;
    private String preparations;
    private String disposition;
    private String otherCatalogNumbers;
    private String previousIdentifications;
    private String associatedMedia;
    private String associatedReferences;
    private String associatedOccurrences;
    private String associatedSequences;
    private String associatedTaxa;
    //Event
    private String eventId;
    private String samplingProtocol;
    private String samplingEffort;
    private String eventDate;
    private String eventTime;
    private String startDayOfYear;
    private String endDayOfYear;
    private String year;
    private String month;
    private String day;
    private String verbatimEventDate;
    private String habitat;
    private String fieldNumber;
    private String fieldNotes;
    private String eventRemarks; //56
    //Location
    private String locationId;
    private String higherGeographyId;
    private String higherGeography;
    private String continent;
    private String waterBody;
    private String islandGroup;
    private String island;
    private String country;
    private String countryCode;
    private String stateProvince;
    private String county;
    private String municipality;
    private String locality;
    private String verbatimLocality;
    private String verbatimElevation;
    private String minimumElevationInMeters;
    private String maximumElevationInMeters;
    private String verbatimDepth;
    private String minimumDepthInMeters;
    private String maximumDepthInMeters;
    private String minimumDistanceAboveSurfaceInMeters;
    private String maximumDistanceAboveSurfaceInMeters;
    private String locationAccordingTo;
    private String locationRemarks;
    private String verbatimCoordinates;
    private String verbatimLatitude;
    private String verbatimLongitude;
    private String verbatimCoordinateSystem;
    private String verbatimSRS;
    private String decimalLatitude;
    private String decimalLongitude;
    private String geodeticDatum;
    private String coordinateUncertaintyInMeters;
    private String coordinatePrecision;
    private String pointRadiusSpatialFit;
    private String footprintWKT;
    private String footprintSRS;
    private String footprintSpatialFit;
    private String georeferencedBy;
    private String georeferencedDate;
    private String georeferenceProtocol;
    private String georeferenceSources;
    private String georeferenceVerificationStatus;
    private String georeferenceRemarks; //100
    //GeologicalContext
    private String geologicalContextId;
    private String earliestEonOrLowestEonothem;
    private String latestEonOrHighestEonothem;
    private String earliestEraOrLowestErathem;
    private String latestEraOrHighestErathem;
    private String earliestPeriodOrLowestSystem;
    private String latestPeriodOrHighestSystem;
    private String earliestEpochOrLowestSeries;
    private String latestEpochOrHighestSeries;
    private String earliestAgeOrLowestStage;
    private String latestAgeOrHighestStage;
    private String lowestBiostratigraphicZone;
    private String highestBiostratigraphicZone;
    private String lithostratigraphicTerms;
    private String group;
    private String formation;
    private String member;
    private String bed;
    //Identification
    private String identificationId;
    private String identifiedBy;
    private String dateIdentified;
    private String identificationReferences;
    private String identificationVerificationStatus;
    private String identificationRemarks;
    private String identificationQualifier;
    private String typeStatus;
    //Taxon
    private String taxonId;
    private String scientificNameId;
    private String acceptedNameUsageId;
    private String parentNameUsageId;
    private String originalNameUsageId;
    private String nameAccordingToId;
    private String namePublishedInId;
    private String taxonConceptId;
    private String scientificName;
    private String acceptedNameUsage;
    private String parentNameUsage;
    private String originalNameUsage;
    private String nameAccordingTo;
    private String namePublishedIn;
    private String namePublishedInYear;
    private String higherClassification;
    private String kingdom;
    private String phylum;
    private String class_;
    private String taxonOrder;
    private String family;
    private String genus;
    private String subgenus;
    private String specificEpithet;
    private String infraspecificEpithet;
    private String taxonRank;
    private String verbatimTaxonRank;
    private String scientificNameAuthorship;
    private String vernacularName;
    private String nomenclaturalCode;
    private String taxonomicStatus;
    private String nomenclaturalStatus;
    private String taxonRemarks; //159
	

    /**
     * Additional properties from other entities
     */
    private HashMap<String, String> properties;
    
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
        
        final OccurrenceDwcCDTO other = (OccurrenceDwcCDTO) obj;
        
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

        OccurrenceDwcCDTO ol = (OccurrenceDwcCDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the location
	 */
	public LocationCDTO getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(LocationCDTO location) {
		this.location = location;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the modified
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	/**
	 * @return the rightsHolder
	 */
	public String getRightsHolder() {
		return rightsHolder;
	}

	/**
	 * @param rightsHolder the rightsHolder to set
	 */
	public void setRightsHolder(String rightsHolder) {
		this.rightsHolder = rightsHolder;
	}

	/**
	 * @return the accessRights
	 */
	public String getAccessRights() {
		return accessRights;
	}

	/**
	 * @param accessRights the accessRights to set
	 */
	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	/**
	 * @return the bibliographicCitation
	 */
	public String getBibliographicCitation() {
		return bibliographicCitation;
	}

	/**
	 * @param bibliographicCitation the bibliographicCitation to set
	 */
	public void setBibliographicCitation(String bibliographicCitation) {
		this.bibliographicCitation = bibliographicCitation;
	}

	/**
	 * @return the references
	 */
	public String getReferences() {
		return references;
	}

	/**
	 * @param references the references to set
	 */
	public void setReferences(String references) {
		this.references = references;
	}

	/**
	 * @return the institutionId
	 */
	public String getInstitutionId() {
		return institutionId;
	}

	/**
	 * @param institutionId the institutionId to set
	 */
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * @return the collectionId
	 */
	public String getCollectionId() {
		return collectionId;
	}

	/**
	 * @param collectionId the collectionId to set
	 */
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	/**
	 * @return the datasetId
	 */
	public String getDatasetId() {
		return datasetId;
	}

	/**
	 * @param datasetId the datasetId to set
	 */
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

	/**
	 * @return the institutionCode
	 */
	public String getInstitutionCode() {
		return institutionCode;
	}

	/**
	 * @param institutionCode the institutionCode to set
	 */
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	/**
	 * @return the collectionCode
	 */
	public String getCollectionCode() {
		return collectionCode;
	}

	/**
	 * @param collectionCode the collectionCode to set
	 */
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	/**
	 * @return the datasetName
	 */
	public String getDatasetName() {
		return datasetName;
	}

	/**
	 * @param datasetName the datasetName to set
	 */
	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	/**
	 * @return the ownerInstitutionCode
	 */
	public String getOwnerInstitutionCode() {
		return ownerInstitutionCode;
	}

	/**
	 * @param ownerInstitutionCode the ownerInstitutionCode to set
	 */
	public void setOwnerInstitutionCode(String ownerInstitutionCode) {
		this.ownerInstitutionCode = ownerInstitutionCode;
	}

	/**
	 * @return the basisOfRecord
	 */
	public String getBasisOfRecord() {
		return basisOfRecord;
	}

	/**
	 * @param basisOfRecord the basisOfRecord to set
	 */
	public void setBasisOfRecord(String basisOfRecord) {
		this.basisOfRecord = basisOfRecord;
	}

	/**
	 * @return the informationWithheld
	 */
	public String getInformationWithheld() {
		return informationWithheld;
	}

	/**
	 * @param informationWithheld the informationWithheld to set
	 */
	public void setInformationWithheld(String informationWithheld) {
		this.informationWithheld = informationWithheld;
	}

	/**
	 * @return the dataGeneralizations
	 */
	public String getDataGeneralizations() {
		return dataGeneralizations;
	}

	/**
	 * @param dataGeneralizations the dataGeneralizations to set
	 */
	public void setDataGeneralizations(String dataGeneralizations) {
		this.dataGeneralizations = dataGeneralizations;
	}

	/**
	 * @return the dynamicProperties
	 */
	public String getDynamicProperties() {
		return dynamicProperties;
	}

	/**
	 * @param dynamicProperties the dynamicProperties to set
	 */
	public void setDynamicProperties(String dynamicProperties) {
		this.dynamicProperties = dynamicProperties;
	}

	/**
	 * @return the occurrenceId
	 */
	public String getOccurrenceId() {
		return occurrenceId;
	}

	/**
	 * @param occurrenceId the occurrenceId to set
	 */
	public void setOccurrenceId(String occurrenceId) {
		this.occurrenceId = occurrenceId;
	}

	/**
	 * @return the catalogNumber
	 */
	public String getCatalogNumber() {
		return catalogNumber;
	}

	/**
	 * @param catalogNumber the catalogNumber to set
	 */
	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	/**
	 * @return the occurrenceRemarks
	 */
	public String getOccurrenceRemarks() {
		return occurrenceRemarks;
	}

	/**
	 * @param occurrenceRemarks the occurrenceRemarks to set
	 */
	public void setOccurrenceRemarks(String occurrenceRemarks) {
		this.occurrenceRemarks = occurrenceRemarks;
	}

	/**
	 * @return the recordNumber
	 */
	public String getRecordNumber() {
		return recordNumber;
	}

	/**
	 * @param recordNumber the recordNumber to set
	 */
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	/**
	 * @return the recordedBy
	 */
	public String getRecordedBy() {
		return recordedBy;
	}

	/**
	 * @param recordedBy the recordedBy to set
	 */
	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}

	/**
	 * @return the individualId
	 */
	public String getIndividualId() {
		return individualId;
	}

	/**
	 * @param individualId the individualId to set
	 */
	public void setIndividualId(String individualId) {
		this.individualId = individualId;
	}

	/**
	 * @return the individualCount
	 */
	public String getIndividualCount() {
		return individualCount;
	}

	/**
	 * @param individualCount the individualCount to set
	 */
	public void setIndividualCount(String individualCount) {
		this.individualCount = individualCount;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the lifeStage
	 */
	public String getLifeStage() {
		return lifeStage;
	}

	/**
	 * @param lifeStage the lifeStage to set
	 */
	public void setLifeStage(String lifeStage) {
		this.lifeStage = lifeStage;
	}

	/**
	 * @return the reproductiveCondition
	 */
	public String getReproductiveCondition() {
		return reproductiveCondition;
	}

	/**
	 * @param reproductiveCondition the reproductiveCondition to set
	 */
	public void setReproductiveCondition(String reproductiveCondition) {
		this.reproductiveCondition = reproductiveCondition;
	}

	/**
	 * @return the behavior
	 */
	public String getBehavior() {
		return behavior;
	}

	/**
	 * @param behavior the behavior to set
	 */
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	/**
	 * @return the establishmentMeans
	 */
	public String getEstablishmentMeans() {
		return establishmentMeans;
	}

	/**
	 * @param establishmentMeans the establishmentMeans to set
	 */
	public void setEstablishmentMeans(String establishmentMeans) {
		this.establishmentMeans = establishmentMeans;
	}

	/**
	 * @return the occurrenceStatus
	 */
	public String getOccurrenceStatus() {
		return occurrenceStatus;
	}

	/**
	 * @param occurrenceStatus the occurrenceStatus to set
	 */
	public void setOccurrenceStatus(String occurrenceStatus) {
		this.occurrenceStatus = occurrenceStatus;
	}

	/**
	 * @return the preparations
	 */
	public String getPreparations() {
		return preparations;
	}

	/**
	 * @param preparations the preparations to set
	 */
	public void setPreparations(String preparations) {
		this.preparations = preparations;
	}

	/**
	 * @return the disposition
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * @param disposition the disposition to set
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	/**
	 * @return the otherCatalogNumbers
	 */
	public String getOtherCatalogNumbers() {
		return otherCatalogNumbers;
	}

	/**
	 * @param otherCatalogNumbers the otherCatalogNumbers to set
	 */
	public void setOtherCatalogNumbers(String otherCatalogNumbers) {
		this.otherCatalogNumbers = otherCatalogNumbers;
	}

	/**
	 * @return the previousIdentifications
	 */
	public String getPreviousIdentifications() {
		return previousIdentifications;
	}

	/**
	 * @param previousIdentifications the previousIdentifications to set
	 */
	public void setPreviousIdentifications(String previousIdentifications) {
		this.previousIdentifications = previousIdentifications;
	}

	/**
	 * @return the associatedMedia
	 */
	public String getAssociatedMedia() {
		return associatedMedia;
	}

	/**
	 * @param associatedMedia the associatedMedia to set
	 */
	public void setAssociatedMedia(String associatedMedia) {
		this.associatedMedia = associatedMedia;
	}

	/**
	 * @return the associatedReferences
	 */
	public String getAssociatedReferences() {
		return associatedReferences;
	}

	/**
	 * @param associatedReferences the associatedReferences to set
	 */
	public void setAssociatedReferences(String associatedReferences) {
		this.associatedReferences = associatedReferences;
	}

	/**
	 * @return the associatedOccurrences
	 */
	public String getAssociatedOccurrences() {
		return associatedOccurrences;
	}

	/**
	 * @param associatedOccurrences the associatedOccurrences to set
	 */
	public void setAssociatedOccurrences(String associatedOccurrences) {
		this.associatedOccurrences = associatedOccurrences;
	}

	/**
	 * @return the associatedSequences
	 */
	public String getAssociatedSequences() {
		return associatedSequences;
	}

	/**
	 * @param associatedSequences the associatedSequences to set
	 */
	public void setAssociatedSequences(String associatedSequences) {
		this.associatedSequences = associatedSequences;
	}

	/**
	 * @return the associatedTaxa
	 */
	public String getAssociatedTaxa() {
		return associatedTaxa;
	}

	/**
	 * @param associatedTaxa the associatedTaxa to set
	 */
	public void setAssociatedTaxa(String associatedTaxa) {
		this.associatedTaxa = associatedTaxa;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the samplingProtocol
	 */
	public String getSamplingProtocol() {
		return samplingProtocol;
	}

	/**
	 * @param samplingProtocol the samplingProtocol to set
	 */
	public void setSamplingProtocol(String samplingProtocol) {
		this.samplingProtocol = samplingProtocol;
	}

	/**
	 * @return the samplingEffort
	 */
	public String getSamplingEffort() {
		return samplingEffort;
	}

	/**
	 * @param samplingEffort the samplingEffort to set
	 */
	public void setSamplingEffort(String samplingEffort) {
		this.samplingEffort = samplingEffort;
	}

	/**
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the startDayOfYear
	 */
	public String getStartDayOfYear() {
		return startDayOfYear;
	}

	/**
	 * @param startDayOfYear the startDayOfYear to set
	 */
	public void setStartDayOfYear(String startDayOfYear) {
		this.startDayOfYear = startDayOfYear;
	}

	/**
	 * @return the endDayOfYear
	 */
	public String getEndDayOfYear() {
		return endDayOfYear;
	}

	/**
	 * @param endDayOfYear the endDayOfYear to set
	 */
	public void setEndDayOfYear(String endDayOfYear) {
		this.endDayOfYear = endDayOfYear;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the verbatimEventDate
	 */
	public String getVerbatimEventDate() {
		return verbatimEventDate;
	}

	/**
	 * @param verbatimEventDate the verbatimEventDate to set
	 */
	public void setVerbatimEventDate(String verbatimEventDate) {
		this.verbatimEventDate = verbatimEventDate;
	}

	/**
	 * @return the habitat
	 */
	public String getHabitat() {
		return habitat;
	}

	/**
	 * @param habitat the habitat to set
	 */
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	/**
	 * @return the fieldNumber
	 */
	public String getFieldNumber() {
		return fieldNumber;
	}

	/**
	 * @param fieldNumber the fieldNumber to set
	 */
	public void setFieldNumber(String fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	/**
	 * @return the fieldNotes
	 */
	public String getFieldNotes() {
		return fieldNotes;
	}

	/**
	 * @param fieldNotes the fieldNotes to set
	 */
	public void setFieldNotes(String fieldNotes) {
		this.fieldNotes = fieldNotes;
	}

	/**
	 * @return the eventRemarks
	 */
	public String getEventRemarks() {
		return eventRemarks;
	}

	/**
	 * @param eventRemarks the eventRemarks to set
	 */
	public void setEventRemarks(String eventRemarks) {
		this.eventRemarks = eventRemarks;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the higherGeographyId
	 */
	public String getHigherGeographyId() {
		return higherGeographyId;
	}

	/**
	 * @param higherGeographyId the higherGeographyId to set
	 */
	public void setHigherGeographyId(String higherGeographyId) {
		this.higherGeographyId = higherGeographyId;
	}

	/**
	 * @return the higherGeography
	 */
	public String getHigherGeography() {
		return higherGeography;
	}

	/**
	 * @param higherGeography the higherGeography to set
	 */
	public void setHigherGeography(String higherGeography) {
		this.higherGeography = higherGeography;
	}

	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

	/**
	 * @return the waterBody
	 */
	public String getWaterBody() {
		return waterBody;
	}

	/**
	 * @param waterBody the waterBody to set
	 */
	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
	}

	/**
	 * @return the islandGroup
	 */
	public String getIslandGroup() {
		return islandGroup;
	}

	/**
	 * @param islandGroup the islandGroup to set
	 */
	public void setIslandGroup(String islandGroup) {
		this.islandGroup = islandGroup;
	}

	/**
	 * @return the island
	 */
	public String getIsland() {
		return island;
	}

	/**
	 * @param island the island to set
	 */
	public void setIsland(String island) {
		this.island = island;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the stateProvince
	 */
	public String getStateProvince() {
		return stateProvince;
	}

	/**
	 * @param stateProvince the stateProvince to set
	 */
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the municipality
	 */
	public String getMunicipality() {
		return municipality;
	}

	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the verbatimLocality
	 */
	public String getVerbatimLocality() {
		return verbatimLocality;
	}

	/**
	 * @param verbatimLocality the verbatimLocality to set
	 */
	public void setVerbatimLocality(String verbatimLocality) {
		this.verbatimLocality = verbatimLocality;
	}

	/**
	 * @return the verbatimElevation
	 */
	public String getVerbatimElevation() {
		return verbatimElevation;
	}

	/**
	 * @param verbatimElevation the verbatimElevation to set
	 */
	public void setVerbatimElevation(String verbatimElevation) {
		this.verbatimElevation = verbatimElevation;
	}

	/**
	 * @return the minimumElevationInMeters
	 */
	public String getMinimumElevationInMeters() {
		return minimumElevationInMeters;
	}

	/**
	 * @param minimumElevationInMeters the minimumElevationInMeters to set
	 */
	public void setMinimumElevationInMeters(String minimumElevationInMeters) {
		this.minimumElevationInMeters = minimumElevationInMeters;
	}

	/**
	 * @return the maximumElevationInMeters
	 */
	public String getMaximumElevationInMeters() {
		return maximumElevationInMeters;
	}

	/**
	 * @param maximumElevationInMeters the maximumElevationInMeters to set
	 */
	public void setMaximumElevationInMeters(String maximumElevationInMeters) {
		this.maximumElevationInMeters = maximumElevationInMeters;
	}

	/**
	 * @return the verbatimDepth
	 */
	public String getVerbatimDepth() {
		return verbatimDepth;
	}

	/**
	 * @param verbatimDepth the verbatimDepth to set
	 */
	public void setVerbatimDepth(String verbatimDepth) {
		this.verbatimDepth = verbatimDepth;
	}

	/**
	 * @return the minimumDepthInMeters
	 */
	public String getMinimumDepthInMeters() {
		return minimumDepthInMeters;
	}

	/**
	 * @param minimumDepthInMeters the minimumDepthInMeters to set
	 */
	public void setMinimumDepthInMeters(String minimumDepthInMeters) {
		this.minimumDepthInMeters = minimumDepthInMeters;
	}

	/**
	 * @return the maximumDepthInMeters
	 */
	public String getMaximumDepthInMeters() {
		return maximumDepthInMeters;
	}

	/**
	 * @param maximumDepthInMeters the maximumDepthInMeters to set
	 */
	public void setMaximumDepthInMeters(String maximumDepthInMeters) {
		this.maximumDepthInMeters = maximumDepthInMeters;
	}

	/**
	 * @return the minimumDistanceAboveSurfaceInMeters
	 */
	public String getMinimumDistanceAboveSurfaceInMeters() {
		return minimumDistanceAboveSurfaceInMeters;
	}

	/**
	 * @param minimumDistanceAboveSurfaceInMeters the minimumDistanceAboveSurfaceInMeters to set
	 */
	public void setMinimumDistanceAboveSurfaceInMeters(
			String minimumDistanceAboveSurfaceInMeters) {
		this.minimumDistanceAboveSurfaceInMeters = minimumDistanceAboveSurfaceInMeters;
	}

	/**
	 * @return the maximumDistanceAboveSurfaceInMeters
	 */
	public String getMaximumDistanceAboveSurfaceInMeters() {
		return maximumDistanceAboveSurfaceInMeters;
	}

	/**
	 * @param maximumDistanceAboveSurfaceInMeters the maximumDistanceAboveSurfaceInMeters to set
	 */
	public void setMaximumDistanceAboveSurfaceInMeters(
			String maximumDistanceAboveSurfaceInMeters) {
		this.maximumDistanceAboveSurfaceInMeters = maximumDistanceAboveSurfaceInMeters;
	}

	/**
	 * @return the locationAccordingTo
	 */
	public String getLocationAccordingTo() {
		return locationAccordingTo;
	}

	/**
	 * @param locationAccordingTo the locationAccordingTo to set
	 */
	public void setLocationAccordingTo(String locationAccordingTo) {
		this.locationAccordingTo = locationAccordingTo;
	}

	/**
	 * @return the locationRemarks
	 */
	public String getLocationRemarks() {
		return locationRemarks;
	}

	/**
	 * @param locationRemarks the locationRemarks to set
	 */
	public void setLocationRemarks(String locationRemarks) {
		this.locationRemarks = locationRemarks;
	}

	/**
	 * @return the verbatimCoordinates
	 */
	public String getVerbatimCoordinates() {
		return verbatimCoordinates;
	}

	/**
	 * @param verbatimCoordinates the verbatimCoordinates to set
	 */
	public void setVerbatimCoordinates(String verbatimCoordinates) {
		this.verbatimCoordinates = verbatimCoordinates;
	}

	/**
	 * @return the verbatimLatitude
	 */
	public String getVerbatimLatitude() {
		return verbatimLatitude;
	}

	/**
	 * @param verbatimLatitude the verbatimLatitude to set
	 */
	public void setVerbatimLatitude(String verbatimLatitude) {
		this.verbatimLatitude = verbatimLatitude;
	}

	/**
	 * @return the verbatimLongitude
	 */
	public String getVerbatimLongitude() {
		return verbatimLongitude;
	}

	/**
	 * @param verbatimLongitude the verbatimLongitude to set
	 */
	public void setVerbatimLongitude(String verbatimLongitude) {
		this.verbatimLongitude = verbatimLongitude;
	}

	/**
	 * @return the verbatimCoordinateSystem
	 */
	public String getVerbatimCoordinateSystem() {
		return verbatimCoordinateSystem;
	}

	/**
	 * @param verbatimCoordinateSystem the verbatimCoordinateSystem to set
	 */
	public void setVerbatimCoordinateSystem(String verbatimCoordinateSystem) {
		this.verbatimCoordinateSystem = verbatimCoordinateSystem;
	}

	/**
	 * @return the verbatimSRS
	 */
	public String getVerbatimSRS() {
		return verbatimSRS;
	}

	/**
	 * @param verbatimSRS the verbatimSRS to set
	 */
	public void setVerbatimSRS(String verbatimSRS) {
		this.verbatimSRS = verbatimSRS;
	}

	/**
	 * @return the decimalLatitude
	 */
	public String getDecimalLatitude() {
		return decimalLatitude;
	}

	/**
	 * @param decimalLatitude the decimalLatitude to set
	 */
	public void setDecimalLatitude(String decimalLatitude) {
		this.decimalLatitude = decimalLatitude;
	}

	/**
	 * @return the decimalLongitude
	 */
	public String getDecimalLongitude() {
		return decimalLongitude;
	}

	/**
	 * @param decimalLongitude the decimalLongitude to set
	 */
	public void setDecimalLongitude(String decimalLongitude) {
		this.decimalLongitude = decimalLongitude;
	}

	/**
	 * @return the geodeticDatum
	 */
	public String getGeodeticDatum() {
		return geodeticDatum;
	}

	/**
	 * @param geodeticDatum the geodeticDatum to set
	 */
	public void setGeodeticDatum(String geodeticDatum) {
		this.geodeticDatum = geodeticDatum;
	}

	/**
	 * @return the coordinateUncertaintyInMeters
	 */
	public String getCoordinateUncertaintyInMeters() {
		return coordinateUncertaintyInMeters;
	}

	/**
	 * @param coordinateUncertaintyInMeters the coordinateUncertaintyInMeters to set
	 */
	public void setCoordinateUncertaintyInMeters(
			String coordinateUncertaintyInMeters) {
		this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
	}

	/**
	 * @return the coordinatePrecision
	 */
	public String getCoordinatePrecision() {
		return coordinatePrecision;
	}

	/**
	 * @param coordinatePrecision the coordinatePrecision to set
	 */
	public void setCoordinatePrecision(String coordinatePrecision) {
		this.coordinatePrecision = coordinatePrecision;
	}

	/**
	 * @return the pointRadiusSpatialFit
	 */
	public String getPointRadiusSpatialFit() {
		return pointRadiusSpatialFit;
	}

	/**
	 * @param pointRadiusSpatialFit the pointRadiusSpatialFit to set
	 */
	public void setPointRadiusSpatialFit(String pointRadiusSpatialFit) {
		this.pointRadiusSpatialFit = pointRadiusSpatialFit;
	}

	/**
	 * @return the footprintWKT
	 */
	public String getFootprintWKT() {
		return footprintWKT;
	}

	/**
	 * @param footprintWKT the footprintWKT to set
	 */
	public void setFootprintWKT(String footprintWKT) {
		this.footprintWKT = footprintWKT;
	}

	/**
	 * @return the footprintSRS
	 */
	public String getFootprintSRS() {
		return footprintSRS;
	}

	/**
	 * @param footprintSRS the footprintSRS to set
	 */
	public void setFootprintSRS(String footprintSRS) {
		this.footprintSRS = footprintSRS;
	}

	/**
	 * @return the footprintSpatialFit
	 */
	public String getFootprintSpatialFit() {
		return footprintSpatialFit;
	}

	/**
	 * @param footprintSpatialFit the footprintSpatialFit to set
	 */
	public void setFootprintSpatialFit(String footprintSpatialFit) {
		this.footprintSpatialFit = footprintSpatialFit;
	}

	/**
	 * @return the georeferencedBy
	 */
	public String getGeoreferencedBy() {
		return georeferencedBy;
	}

	/**
	 * @param georeferencedBy the georeferencedBy to set
	 */
	public void setGeoreferencedBy(String georeferencedBy) {
		this.georeferencedBy = georeferencedBy;
	}

	/**
	 * @return the georeferencedDate
	 */
	public String getGeoreferencedDate() {
		return georeferencedDate;
	}

	/**
	 * @param georeferencedDate the georeferencedDate to set
	 */
	public void setGeoreferencedDate(String georeferencedDate) {
		this.georeferencedDate = georeferencedDate;
	}

	/**
	 * @return the georeferenceProtocol
	 */
	public String getGeoreferenceProtocol() {
		return georeferenceProtocol;
	}

	/**
	 * @param georeferenceProtocol the georeferenceProtocol to set
	 */
	public void setGeoreferenceProtocol(String georeferenceProtocol) {
		this.georeferenceProtocol = georeferenceProtocol;
	}

	/**
	 * @return the georeferenceSources
	 */
	public String getGeoreferenceSources() {
		return georeferenceSources;
	}

	/**
	 * @param georeferenceSources the georeferenceSources to set
	 */
	public void setGeoreferenceSources(String georeferenceSources) {
		this.georeferenceSources = georeferenceSources;
	}

	/**
	 * @return the georeferenceVerificationStatus
	 */
	public String getGeoreferenceVerificationStatus() {
		return georeferenceVerificationStatus;
	}

	/**
	 * @param georeferenceVerificationStatus the georeferenceVerificationStatus to set
	 */
	public void setGeoreferenceVerificationStatus(
			String georeferenceVerificationStatus) {
		this.georeferenceVerificationStatus = georeferenceVerificationStatus;
	}

	/**
	 * @return the georeferenceRemarks
	 */
	public String getGeoreferenceRemarks() {
		return georeferenceRemarks;
	}

	/**
	 * @param georeferenceRemarks the georeferenceRemarks to set
	 */
	public void setGeoreferenceRemarks(String georeferenceRemarks) {
		this.georeferenceRemarks = georeferenceRemarks;
	}

	/**
	 * @return the geologicalContextId
	 */
	public String getGeologicalContextId() {
		return geologicalContextId;
	}

	/**
	 * @param geologicalContextId the geologicalContextId to set
	 */
	public void setGeologicalContextId(String geologicalContextId) {
		this.geologicalContextId = geologicalContextId;
	}

	/**
	 * @return the earliestEonOrLowestEonothem
	 */
	public String getEarliestEonOrLowestEonothem() {
		return earliestEonOrLowestEonothem;
	}

	/**
	 * @param earliestEonOrLowestEonothem the earliestEonOrLowestEonothem to set
	 */
	public void setEarliestEonOrLowestEonothem(String earliestEonOrLowestEonothem) {
		this.earliestEonOrLowestEonothem = earliestEonOrLowestEonothem;
	}

	/**
	 * @return the latestEonOrHighestEonothem
	 */
	public String getLatestEonOrHighestEonothem() {
		return latestEonOrHighestEonothem;
	}

	/**
	 * @param latestEonOrHighestEonothem the latestEonOrHighestEonothem to set
	 */
	public void setLatestEonOrHighestEonothem(String latestEonOrHighestEonothem) {
		this.latestEonOrHighestEonothem = latestEonOrHighestEonothem;
	}

	/**
	 * @return the earliestEraOrLowestErathem
	 */
	public String getEarliestEraOrLowestErathem() {
		return earliestEraOrLowestErathem;
	}

	/**
	 * @param earliestEraOrLowestErathem the earliestEraOrLowestErathem to set
	 */
	public void setEarliestEraOrLowestErathem(String earliestEraOrLowestErathem) {
		this.earliestEraOrLowestErathem = earliestEraOrLowestErathem;
	}

	/**
	 * @return the latestEraOrHighestErathem
	 */
	public String getLatestEraOrHighestErathem() {
		return latestEraOrHighestErathem;
	}

	/**
	 * @param latestEraOrHighestErathem the latestEraOrHighestErathem to set
	 */
	public void setLatestEraOrHighestErathem(String latestEraOrHighestErathem) {
		this.latestEraOrHighestErathem = latestEraOrHighestErathem;
	}

	/**
	 * @return the earliestPeriodOrLowestSystem
	 */
	public String getEarliestPeriodOrLowestSystem() {
		return earliestPeriodOrLowestSystem;
	}

	/**
	 * @param earliestPeriodOrLowestSystem the earliestPeriodOrLowestSystem to set
	 */
	public void setEarliestPeriodOrLowestSystem(String earliestPeriodOrLowestSystem) {
		this.earliestPeriodOrLowestSystem = earliestPeriodOrLowestSystem;
	}

	/**
	 * @return the latestPeriodOrHighestSystem
	 */
	public String getLatestPeriodOrHighestSystem() {
		return latestPeriodOrHighestSystem;
	}

	/**
	 * @param latestPeriodOrHighestSystem the latestPeriodOrHighestSystem to set
	 */
	public void setLatestPeriodOrHighestSystem(String latestPeriodOrHighestSystem) {
		this.latestPeriodOrHighestSystem = latestPeriodOrHighestSystem;
	}

	/**
	 * @return the earliestEpochOrLowestSeries
	 */
	public String getEarliestEpochOrLowestSeries() {
		return earliestEpochOrLowestSeries;
	}

	/**
	 * @param earliestEpochOrLowestSeries the earliestEpochOrLowestSeries to set
	 */
	public void setEarliestEpochOrLowestSeries(String earliestEpochOrLowestSeries) {
		this.earliestEpochOrLowestSeries = earliestEpochOrLowestSeries;
	}

	/**
	 * @return the latestEpochOrHighestSeries
	 */
	public String getLatestEpochOrHighestSeries() {
		return latestEpochOrHighestSeries;
	}

	/**
	 * @param latestEpochOrHighestSeries the latestEpochOrHighestSeries to set
	 */
	public void setLatestEpochOrHighestSeries(String latestEpochOrHighestSeries) {
		this.latestEpochOrHighestSeries = latestEpochOrHighestSeries;
	}

	/**
	 * @return the earliestAgeOrLowestStage
	 */
	public String getEarliestAgeOrLowestStage() {
		return earliestAgeOrLowestStage;
	}

	/**
	 * @param earliestAgeOrLowestStage the earliestAgeOrLowestStage to set
	 */
	public void setEarliestAgeOrLowestStage(String earliestAgeOrLowestStage) {
		this.earliestAgeOrLowestStage = earliestAgeOrLowestStage;
	}

	/**
	 * @return the latestAgeOrHighestStage
	 */
	public String getLatestAgeOrHighestStage() {
		return latestAgeOrHighestStage;
	}

	/**
	 * @param latestAgeOrHighestStage the latestAgeOrHighestStage to set
	 */
	public void setLatestAgeOrHighestStage(String latestAgeOrHighestStage) {
		this.latestAgeOrHighestStage = latestAgeOrHighestStage;
	}

	/**
	 * @return the lowestBiostratigraphicZone
	 */
	public String getLowestBiostratigraphicZone() {
		return lowestBiostratigraphicZone;
	}

	/**
	 * @param lowestBiostratigraphicZone the lowestBiostratigraphicZone to set
	 */
	public void setLowestBiostratigraphicZone(String lowestBiostratigraphicZone) {
		this.lowestBiostratigraphicZone = lowestBiostratigraphicZone;
	}

	/**
	 * @return the highestBiostratigraphicZone
	 */
	public String getHighestBiostratigraphicZone() {
		return highestBiostratigraphicZone;
	}

	/**
	 * @param highestBiostratigraphicZone the highestBiostratigraphicZone to set
	 */
	public void setHighestBiostratigraphicZone(String highestBiostratigraphicZone) {
		this.highestBiostratigraphicZone = highestBiostratigraphicZone;
	}

	/**
	 * @return the lithostratigraphicTerms
	 */
	public String getLithostratigraphicTerms() {
		return lithostratigraphicTerms;
	}

	/**
	 * @param lithostratigraphicTerms the lithostratigraphicTerms to set
	 */
	public void setLithostratigraphicTerms(String lithostratigraphicTerms) {
		this.lithostratigraphicTerms = lithostratigraphicTerms;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the formation
	 */
	public String getFormation() {
		return formation;
	}

	/**
	 * @param formation the formation to set
	 */
	public void setFormation(String formation) {
		this.formation = formation;
	}

	/**
	 * @return the member
	 */
	public String getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(String member) {
		this.member = member;
	}

	/**
	 * @return the bed
	 */
	public String getBed() {
		return bed;
	}

	/**
	 * @param bed the bed to set
	 */
	public void setBed(String bed) {
		this.bed = bed;
	}

	/**
	 * @return the identificationId
	 */
	public String getIdentificationId() {
		return identificationId;
	}

	/**
	 * @param identificationId the identificationId to set
	 */
	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	/**
	 * @return the identifiedBy
	 */
	public String getIdentifiedBy() {
		return identifiedBy;
	}

	/**
	 * @param identifiedBy the identifiedBy to set
	 */
	public void setIdentifiedBy(String identifiedBy) {
		this.identifiedBy = identifiedBy;
	}

	/**
	 * @return the dateIdentified
	 */
	public String getDateIdentified() {
		return dateIdentified;
	}

	/**
	 * @param dateIdentified the dateIdentified to set
	 */
	public void setDateIdentified(String dateIdentified) {
		this.dateIdentified = dateIdentified;
	}

	/**
	 * @return the identificationReferences
	 */
	public String getIdentificationReferences() {
		return identificationReferences;
	}

	/**
	 * @param identificationReferences the identificationReferences to set
	 */
	public void setIdentificationReferences(String identificationReferences) {
		this.identificationReferences = identificationReferences;
	}

	/**
	 * @return the identificationVerificationStatus
	 */
	public String getIdentificationVerificationStatus() {
		return identificationVerificationStatus;
	}

	/**
	 * @param identificationVerificationStatus the identificationVerificationStatus to set
	 */
	public void setIdentificationVerificationStatus(
			String identificationVerificationStatus) {
		this.identificationVerificationStatus = identificationVerificationStatus;
	}

	/**
	 * @return the identificationRemarks
	 */
	public String getIdentificationRemarks() {
		return identificationRemarks;
	}

	/**
	 * @param identificationRemarks the identificationRemarks to set
	 */
	public void setIdentificationRemarks(String identificationRemarks) {
		this.identificationRemarks = identificationRemarks;
	}

	/**
	 * @return the identificationQualifier
	 */
	public String getIdentificationQualifier() {
		return identificationQualifier;
	}

	/**
	 * @param identificationQualifier the identificationQualifier to set
	 */
	public void setIdentificationQualifier(String identificationQualifier) {
		this.identificationQualifier = identificationQualifier;
	}

	/**
	 * @return the typeStatus
	 */
	public String getTypeStatus() {
		return typeStatus;
	}

	/**
	 * @param typeStatus the typeStatus to set
	 */
	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus;
	}

	/**
	 * @return the taxonId
	 */
	public String getTaxonId() {
		return taxonId;
	}

	/**
	 * @param taxonId the taxonId to set
	 */
	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
	}

	/**
	 * @return the scientificNameId
	 */
	public String getScientificNameId() {
		return scientificNameId;
	}

	/**
	 * @param scientificNameId the scientificNameId to set
	 */
	public void setScientificNameId(String scientificNameId) {
		this.scientificNameId = scientificNameId;
	}

	/**
	 * @return the acceptedNameUsageId
	 */
	public String getAcceptedNameUsageId() {
		return acceptedNameUsageId;
	}

	/**
	 * @param acceptedNameUsageId the acceptedNameUsageId to set
	 */
	public void setAcceptedNameUsageId(String acceptedNameUsageId) {
		this.acceptedNameUsageId = acceptedNameUsageId;
	}

	/**
	 * @return the parentNameUsageId
	 */
	public String getParentNameUsageId() {
		return parentNameUsageId;
	}

	/**
	 * @param parentNameUsageId the parentNameUsageId to set
	 */
	public void setParentNameUsageId(String parentNameUsageId) {
		this.parentNameUsageId = parentNameUsageId;
	}

	/**
	 * @return the originalNameUsageId
	 */
	public String getOriginalNameUsageId() {
		return originalNameUsageId;
	}

	/**
	 * @param originalNameUsageId the originalNameUsageId to set
	 */
	public void setOriginalNameUsageId(String originalNameUsageId) {
		this.originalNameUsageId = originalNameUsageId;
	}

	/**
	 * @return the nameAccordingToId
	 */
	public String getNameAccordingToId() {
		return nameAccordingToId;
	}

	/**
	 * @param nameAccordingToId the nameAccordingToId to set
	 */
	public void setNameAccordingToId(String nameAccordingToId) {
		this.nameAccordingToId = nameAccordingToId;
	}

	/**
	 * @return the namePublishedInId
	 */
	public String getNamePublishedInId() {
		return namePublishedInId;
	}

	/**
	 * @param namePublishedInId the namePublishedInId to set
	 */
	public void setNamePublishedInId(String namePublishedInId) {
		this.namePublishedInId = namePublishedInId;
	}

	/**
	 * @return the taxonConceptId
	 */
	public String getTaxonConceptId() {
		return taxonConceptId;
	}

	/**
	 * @param taxonConceptId the taxonConceptId to set
	 */
	public void setTaxonConceptId(String taxonConceptId) {
		this.taxonConceptId = taxonConceptId;
	}

	/**
	 * @return the scientificName
	 */
	public String getScientificName() {
		return scientificName;
	}

	/**
	 * @param scientificName the scientificName to set
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	/**
	 * @return the acceptedNameUsage
	 */
	public String getAcceptedNameUsage() {
		return acceptedNameUsage;
	}

	/**
	 * @param acceptedNameUsage the acceptedNameUsage to set
	 */
	public void setAcceptedNameUsage(String acceptedNameUsage) {
		this.acceptedNameUsage = acceptedNameUsage;
	}

	/**
	 * @return the parentNameUsage
	 */
	public String getParentNameUsage() {
		return parentNameUsage;
	}

	/**
	 * @param parentNameUsage the parentNameUsage to set
	 */
	public void setParentNameUsage(String parentNameUsage) {
		this.parentNameUsage = parentNameUsage;
	}

	/**
	 * @return the originalNameUsage
	 */
	public String getOriginalNameUsage() {
		return originalNameUsage;
	}

	/**
	 * @param originalNameUsage the originalNameUsage to set
	 */
	public void setOriginalNameUsage(String originalNameUsage) {
		this.originalNameUsage = originalNameUsage;
	}

	/**
	 * @return the nameAccordingTo
	 */
	public String getNameAccordingTo() {
		return nameAccordingTo;
	}

	/**
	 * @param nameAccordingTo the nameAccordingTo to set
	 */
	public void setNameAccordingTo(String nameAccordingTo) {
		this.nameAccordingTo = nameAccordingTo;
	}

	/**
	 * @return the namePublishedIn
	 */
	public String getNamePublishedIn() {
		return namePublishedIn;
	}

	/**
	 * @param namePublishedIn the namePublishedIn to set
	 */
	public void setNamePublishedIn(String namePublishedIn) {
		this.namePublishedIn = namePublishedIn;
	}

	/**
	 * @return the namePublishedInYear
	 */
	public String getNamePublishedInYear() {
		return namePublishedInYear;
	}

	/**
	 * @param namePublishedInYear the namePublishedInYear to set
	 */
	public void setNamePublishedInYear(String namePublishedInYear) {
		this.namePublishedInYear = namePublishedInYear;
	}

	/**
	 * @return the higherClassification
	 */
	public String getHigherClassification() {
		return higherClassification;
	}

	/**
	 * @param higherClassification the higherClassification to set
	 */
	public void setHigherClassification(String higherClassification) {
		this.higherClassification = higherClassification;
	}

	/**
	 * @return the kingdom
	 */
	public String getKingdom() {
		return kingdom;
	}

	/**
	 * @param kingdom the kingdom to set
	 */
	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}

	/**
	 * @return the phylum
	 */
	public String getPhylum() {
		return phylum;
	}

	/**
	 * @param phylum the phylum to set
	 */
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}

	/**
	 * @return the class_
	 */
	public String getClass_() {
		return class_;
	}

	/**
	 * @param class_ the class_ to set
	 */
	public void setClass_(String class_) {
		this.class_ = class_;
	}

	/**
	 * @return the taxonOrder
	 */
	public String getTaxonOrder() {
		return taxonOrder;
	}

	/**
	 * @param taxonOrder the taxonOrder to set
	 */
	public void setTaxonOrder(String taxonOrder) {
		this.taxonOrder = taxonOrder;
	}

	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}

	/**
	 * @return the genus
	 */
	public String getGenus() {
		return genus;
	}

	/**
	 * @param genus the genus to set
	 */
	public void setGenus(String genus) {
		this.genus = genus;
	}

	/**
	 * @return the subgenus
	 */
	public String getSubgenus() {
		return subgenus;
	}

	/**
	 * @param subgenus the subgenus to set
	 */
	public void setSubgenus(String subgenus) {
		this.subgenus = subgenus;
	}

	/**
	 * @return the specificEpithet
	 */
	public String getSpecificEpithet() {
		return specificEpithet;
	}

	/**
	 * @param specificEpithet the specificEpithet to set
	 */
	public void setSpecificEpithet(String specificEpithet) {
		this.specificEpithet = specificEpithet;
	}

	/**
	 * @return the infraspecificEpithet
	 */
	public String getInfraspecificEpithet() {
		return infraspecificEpithet;
	}

	/**
	 * @param infraspecificEpithet the infraspecificEpithet to set
	 */
	public void setInfraspecificEpithet(String infraspecificEpithet) {
		this.infraspecificEpithet = infraspecificEpithet;
	}

	/**
	 * @return the taxonRank
	 */
	public String getTaxonRank() {
		return taxonRank;
	}

	/**
	 * @param taxonRank the taxonRank to set
	 */
	public void setTaxonRank(String taxonRank) {
		this.taxonRank = taxonRank;
	}

	/**
	 * @return the verbatimTaxonRank
	 */
	public String getVerbatimTaxonRank() {
		return verbatimTaxonRank;
	}

	/**
	 * @param verbatimTaxonRank the verbatimTaxonRank to set
	 */
	public void setVerbatimTaxonRank(String verbatimTaxonRank) {
		this.verbatimTaxonRank = verbatimTaxonRank;
	}

	/**
	 * @return the scientificNameAuthorship
	 */
	public String getScientificNameAuthorship() {
		return scientificNameAuthorship;
	}

	/**
	 * @param scientificNameAuthorship the scientificNameAuthorship to set
	 */
	public void setScientificNameAuthorship(String scientificNameAuthorship) {
		this.scientificNameAuthorship = scientificNameAuthorship;
	}

	/**
	 * @return the vernacularName
	 */
	public String getVernacularName() {
		return vernacularName;
	}

	/**
	 * @param vernacularName the vernacularName to set
	 */
	public void setVernacularName(String vernacularName) {
		this.vernacularName = vernacularName;
	}

	/**
	 * @return the nomenclaturalCode
	 */
	public String getNomenclaturalCode() {
		return nomenclaturalCode;
	}

	/**
	 * @param nomenclaturalCode the nomenclaturalCode to set
	 */
	public void setNomenclaturalCode(String nomenclaturalCode) {
		this.nomenclaturalCode = nomenclaturalCode;
	}

	/**
	 * @return the taxonomicStatus
	 */
	public String getTaxonomicStatus() {
		return taxonomicStatus;
	}

	/**
	 * @param taxonomicStatus the taxonomicStatus to set
	 */
	public void setTaxonomicStatus(String taxonomicStatus) {
		this.taxonomicStatus = taxonomicStatus;
	}

	/**
	 * @return the nomenclaturalStatus
	 */
	public String getNomenclaturalStatus() {
		return nomenclaturalStatus;
	}

	/**
	 * @param nomenclaturalStatus the nomenclaturalStatus to set
	 */
	public void setNomenclaturalStatus(String nomenclaturalStatus) {
		this.nomenclaturalStatus = nomenclaturalStatus;
	}

	/**
	 * @return the taxonRemarks
	 */
	public String getTaxonRemarks() {
		return taxonRemarks;
	}

	/**
	 * @param taxonRemarks the taxonRemarks to set
	 */
	public void setTaxonRemarks(String taxonRemarks) {
		this.taxonRemarks = taxonRemarks;
	}

	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

    

}