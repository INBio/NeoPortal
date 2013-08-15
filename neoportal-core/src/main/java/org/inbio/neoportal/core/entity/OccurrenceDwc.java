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
package org.inbio.neoportal.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

/**
 *
 * @author avargas
 */
@Indexed
public class OccurrenceDwc implements Serializable {
    
	/**
	 * Additional id, the occurrences has occurrenceId but with this database manage id 
	 * the system support datasets with occurrenceId fields containing not numbers values.
	 */
	@DocumentId
    private BigDecimal Id;
    
    @IndexedEmbedded
    private Location location;
    
    //@IndexedEmbedded
    private DataProvider dataProvider;
    
    @IndexedEmbedded
    private Taxon taxon;
    
    /* Darwin Core Terms http://rs.tdwg.org/dwc/terms/ */
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
    @Field
    private String collectionCode;
    private String datasetName;
    private String ownerInstitutionCode;
    private String basisOfRecord;
    private String informationWithheld;
    private String dataGeneralizations;
    private String dynamicProperties;
    //Occurrence
    private String occurrenceId;
    @Field
    private String catalogNumber;
    private String occurrenceRemarks;
    private String recordNumber;
    private String recordedBy;
    private String individualId;
    private String individualCount;
    @Field
    private String sex;
    @Field
    private String lifeStage;
    private String reproductiveCondition;
    private String behavior;
    @Field
    private String establishmentMeans;
    private String occurrenceStatus;
    @Field
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
    @Field
    private String eventId;
    @Field
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
    //Locations
//    private String locationId;
//    private String higherGeographyId;
//    private String higherGeography;
//    private String continent;
//    private String waterBody;
//    private String islandGroup;
//    private String island;
//    private String country;
//    private String countryCode;
//    private String stateProvince;
//    private String county;
//    private String municipality;
//    private String locality;
//    private String verbatimLocality;
//    private String verbatimElevation;
//    private String minimumElevationInMeters;
//    private String maximumElevationInMeters;
//    private String verbatimDepth;
//    private String minimumDepthInMeters;
//    private String maximumDepthInMeters;
//    private String minimumDistanceAboveSurfaceInMeters;
//    private String maximumDistanceAboveSurfaceInMeters;
//    private String locationAccordingTo;
//    private String locationRemarks;
//    private String verbatimCoordinates;
//    private String verbatimLatitude;
//    private String verbatimLongitude;
//    private String verbatimCoordinateSystem;
//    private String verbatimSRS;
//    private String decimalLatitude;
//    private String decimalLongitude;
//    private String geodeticDatum;
//    private String coordinateUncertaintyInMeters;
//    private String coordinatePrecision;
//    private String pointRadiusSpatialFit;
//    private String footprintWKT;
//    private String footprintSRS;
//    private String footprintSpatialFit;
//    private String georeferencedBy;
//    private String georeferencedDate;
//    private String georeferenceProtocol;
//    private String georeferenceSources;
//    private String georeferenceVerificationStatus;
//    private String georeferenceRemarks; //100
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
    @Field
    @DateBridge(resolution=Resolution.DAY)
    private Date dateIdentified;
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
    @Field
    private String scientificName;
    private String acceptedNameUsage;
    private String parentNameUsage;
    private String originalNameUsage;
    private String nameAccordingTo;
    private String namePublishedIn;
    private String namePublishedInYear;
    private String higherClassification;
    @Field
    private String kingdom;
    @Field
    private String phylum;
    @Field
    private String class_;
    @Field
    private String taxonOrder;
    @Field
    private String family;
    @Field
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


    private Set<Image> images = new HashSet<Image>(0);

    public OccurrenceDwc() {
    }

    
	public OccurrenceDwc(BigDecimal id, Location location,
			DataProvider dataProvider, Taxon taxon, String type,
			String modified, String language, String rights,
			String rightsHolder, String accessRights,
			String bibliographicCitation, String references,
			String institutionId, String collectionId, String datasetId,
			String institutionCode, String collectionCode, String datasetName,
			String ownerInstitutionCode, String basisOfRecord,
			String informationWithheld, String dataGeneralizations,
			String dynamicProperties, String occurrenceId,
			String catalogNumber, String occurrenceRemarks,
			String recordNumber, String recordedBy, String individualId,
			String individualCount, String sex, String lifeStage,
			String reproductiveCondition, String behavior,
			String establishmentMeans, String occurrenceStatus,
			String preparations, String disposition,
			String otherCatalogNumbers, String previousIdentifications,
			String associatedMedia, String associatedReferences,
			String associatedOccurrences, String associatedSequences,
			String associatedTaxa, String eventId, String samplingProtocol,
			String samplingEffort, String eventDate, String eventTime,
			String startDayOfYear, String endDayOfYear, String year,
			String month, String day, String verbatimEventDate, String habitat,
			String fieldNumber, String fieldNotes, String eventRemarks,
			String geologicalContextId, String earliestEonOrLowestEonothem,
			String latestEonOrHighestEonothem,
			String earliestEraOrLowestErathem,
			String latestEraOrHighestErathem,
			String earliestPeriodOrLowestSystem,
			String latestPeriodOrHighestSystem,
			String earliestEpochOrLowestSeries,
			String latestEpochOrHighestSeries, String earliestAgeOrLowestStage,
			String latestAgeOrHighestStage, String lowestBiostratigraphicZone,
			String highestBiostratigraphicZone, String lithostratigraphicTerms,
			String group, String formation, String member, String bed,
			String identificationId, String identifiedBy,
			Date dateIdentified, String identificationReferences,
			String identificationVerificationStatus,
			String identificationRemarks, String identificationQualifier,
			String typeStatus, String taxonId, String scientificNameId,
			String acceptedNameUsageId, String parentNameUsageId,
			String originalNameUsageId, String nameAccordingToId,
			String namePublishedInId, String taxonConceptId,
			String scientificName, String acceptedNameUsage,
			String parentNameUsage, String originalNameUsage,
			String nameAccordingTo, String namePublishedIn,
			String namePublishedInYear, String higherClassification,
			String kingdom, String phylum, String class_, String taxonOrder,
			String family, String genus, String subgenus,
			String specificEpithet, String infraspecificEpithet,
			String taxonRank, String verbatimTaxonRank,
			String scientificNameAuthorship, String vernacularName,
			String nomenclaturalCode, String taxonomicStatus,
			String nomenclaturalStatus, String taxonRemarks) {
		Id = id;
		this.location = location;
		this.dataProvider = dataProvider;
		this.taxon = taxon;
		this.type = type;
		this.modified = modified;
		this.language = language;
		this.rights = rights;
		this.rightsHolder = rightsHolder;
		this.accessRights = accessRights;
		this.bibliographicCitation = bibliographicCitation;
		this.references = references;
		this.institutionId = institutionId;
		this.collectionId = collectionId;
		this.datasetId = datasetId;
		this.institutionCode = institutionCode;
		this.collectionCode = collectionCode;
		this.datasetName = datasetName;
		this.ownerInstitutionCode = ownerInstitutionCode;
		this.basisOfRecord = basisOfRecord;
		this.informationWithheld = informationWithheld;
		this.dataGeneralizations = dataGeneralizations;
		this.dynamicProperties = dynamicProperties;
		this.occurrenceId = occurrenceId;
		this.catalogNumber = catalogNumber;
		this.occurrenceRemarks = occurrenceRemarks;
		this.recordNumber = recordNumber;
		this.recordedBy = recordedBy;
		this.individualId = individualId;
		this.individualCount = individualCount;
		this.sex = sex;
		this.lifeStage = lifeStage;
		this.reproductiveCondition = reproductiveCondition;
		this.behavior = behavior;
		this.establishmentMeans = establishmentMeans;
		this.occurrenceStatus = occurrenceStatus;
		this.preparations = preparations;
		this.disposition = disposition;
		this.otherCatalogNumbers = otherCatalogNumbers;
		this.previousIdentifications = previousIdentifications;
		this.associatedMedia = associatedMedia;
		this.associatedReferences = associatedReferences;
		this.associatedOccurrences = associatedOccurrences;
		this.associatedSequences = associatedSequences;
		this.associatedTaxa = associatedTaxa;
		this.eventId = eventId;
		this.samplingProtocol = samplingProtocol;
		this.samplingEffort = samplingEffort;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.startDayOfYear = startDayOfYear;
		this.endDayOfYear = endDayOfYear;
		this.year = year;
		this.month = month;
		this.day = day;
		this.verbatimEventDate = verbatimEventDate;
		this.habitat = habitat;
		this.fieldNumber = fieldNumber;
		this.fieldNotes = fieldNotes;
		this.eventRemarks = eventRemarks;
		this.geologicalContextId = geologicalContextId;
		this.earliestEonOrLowestEonothem = earliestEonOrLowestEonothem;
		this.latestEonOrHighestEonothem = latestEonOrHighestEonothem;
		this.earliestEraOrLowestErathem = earliestEraOrLowestErathem;
		this.latestEraOrHighestErathem = latestEraOrHighestErathem;
		this.earliestPeriodOrLowestSystem = earliestPeriodOrLowestSystem;
		this.latestPeriodOrHighestSystem = latestPeriodOrHighestSystem;
		this.earliestEpochOrLowestSeries = earliestEpochOrLowestSeries;
		this.latestEpochOrHighestSeries = latestEpochOrHighestSeries;
		this.earliestAgeOrLowestStage = earliestAgeOrLowestStage;
		this.latestAgeOrHighestStage = latestAgeOrHighestStage;
		this.lowestBiostratigraphicZone = lowestBiostratigraphicZone;
		this.highestBiostratigraphicZone = highestBiostratigraphicZone;
		this.lithostratigraphicTerms = lithostratigraphicTerms;
		this.group = group;
		this.formation = formation;
		this.member = member;
		this.bed = bed;
		this.identificationId = identificationId;
		this.identifiedBy = identifiedBy;
		this.dateIdentified = dateIdentified;
		this.identificationReferences = identificationReferences;
		this.identificationVerificationStatus = identificationVerificationStatus;
		this.identificationRemarks = identificationRemarks;
		this.identificationQualifier = identificationQualifier;
		this.typeStatus = typeStatus;
		this.taxonId = taxonId;
		this.scientificNameId = scientificNameId;
		this.acceptedNameUsageId = acceptedNameUsageId;
		this.parentNameUsageId = parentNameUsageId;
		this.originalNameUsageId = originalNameUsageId;
		this.nameAccordingToId = nameAccordingToId;
		this.namePublishedInId = namePublishedInId;
		this.taxonConceptId = taxonConceptId;
		this.scientificName = scientificName;
		this.acceptedNameUsage = acceptedNameUsage;
		this.parentNameUsage = parentNameUsage;
		this.originalNameUsage = originalNameUsage;
		this.nameAccordingTo = nameAccordingTo;
		this.namePublishedIn = namePublishedIn;
		this.namePublishedInYear = namePublishedInYear;
		this.higherClassification = higherClassification;
		this.kingdom = kingdom;
		this.phylum = phylum;
		this.class_ = class_;
		this.taxonOrder = taxonOrder;
		this.family = family;
		this.genus = genus;
		this.subgenus = subgenus;
		this.specificEpithet = specificEpithet;
		this.infraspecificEpithet = infraspecificEpithet;
		this.taxonRank = taxonRank;
		this.verbatimTaxonRank = verbatimTaxonRank;
		this.scientificNameAuthorship = scientificNameAuthorship;
		this.vernacularName = vernacularName;
		this.nomenclaturalCode = nomenclaturalCode;
		this.taxonomicStatus = taxonomicStatus;
		this.nomenclaturalStatus = nomenclaturalStatus;
		this.taxonRemarks = taxonRemarks;
	}




	/**
	 * @return the id
	 */
	public BigDecimal getId() {
		return Id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(BigDecimal id) {
		Id = id;
	}




	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}




	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}




	/**
	 * @return the dataProvider
	 */
	public DataProvider getDataProvider() {
		return dataProvider;
	}




	/**
	 * @param dataProvider the dataProvider to set
	 */
	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}




	/**
	 * @return the taxon
	 */
	public Taxon getTaxon() {
		return taxon;
	}



	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
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
	public Date getDateIdentified() {
		return dateIdentified;
	}



	/**
	 * @param dateIdentified the dateIdentified to set
	 */
	public void setDateIdentified(Date dateIdentified) {
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

	
	public Set<Image> getImages() {
		return images;
	}


	public void setImages(Set<Image> images) {
		this.images = images;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OccurrenceDwc)) {
            return false;
        }
        OccurrenceDwc other = (OccurrenceDwc) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.inbio.neoportal.core.entity.OccurrenceDwc[ id=" + this.Id + " ]";
    }
    
}
