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

import org.inbio.neoportal.common.dto.*;

/**
 * Occurrence record, fields based on DarwinCore Terms http://rs.tdwg.org/dwc/terms/index.htm
 * @author avargas
 */
public class OccurrenceCDTO 
    extends BaseDTO 
        implements Comparable {

    
/*  Occurrence Terms  */    
    private String occurrenceId;
    private String catalogNumber;
    private String remarks; /* dwc = occurrenceRemarks */
    
    /*private String recordNumber;*/
    private String collector;  /* dwc = recordedBy */
    /*private String individualId;
    private String individualCount;*/
    private String sex;
    private String lifeStage;
    /*private String reproductiveCondition;
    private String behavior;
    private String establishmentMeans;
    private String occurrenceStatus;
    private String preparations;
    private String disposition;
    private String otherCatalogNumbers;
    private String previousIdentifications;
    private String associatedMedia;
    private String associatedReferences;
    private String assocciatedOccurrences;
    private String associatedSequences;
    private String associatedTaxa;*/


/*  Taxon terms */
    private String taxonId;
    /*private String scientificNameId;  //not include in database */
    /*private String acceptedNameUsageId;   //not include in database */
    /*private String parentNameUsageId;   //not include in database */
    /*private String originalNameUsageId;   //not include in database */
    /*private String nameAccordingToId;   //not include in database */
    /*private String namePublishedInId;   //not include in database */
    /*private String taxonConceptId;   //not include in database */
    private String scientificName;
    private String acceptedNameUsage;
    private String parentNameUsage;
    private String originalNameUsage;
    private String nameAccordingTo;
    private String namePublishedIn;
    private String namePublishedInYear;
    private String higherClassification;
    private String kingdom;
    private String phylum;  /*  dwc = phylum  */
    private String class_;  /*  dwc = class  */
    private String order;
    private String family;
    private String genus;
    private String subgenus;
    private String specificEpithet;
    private String infraspecificEpithet;
    private String infraspecificRank;   /*  dwc = taxonRank  */
    private String verbatimTaxonRank;
    private String scientificNameAuthorship;
    private String vernacularName;
    private String nomenclaturalCode;
    private String taxonomicStatus;
    private String nomenclaturalStatus;
    private String taxonRemarks;
    
    
    public OccurrenceCDTO() {
    }

    public OccurrenceCDTO(String scientificName) {

        this.scientificName = scientificName;
    }

    
    public OccurrenceCDTO(String occurrenceId, String catalogNumber, String remarks, String collector, String sex, String lifeStage, String taxonId, String scientificName, String acceptedNameUsage, String parentNameUsage, String originalNameUsage, String nameAccordingTo, String namePublishedIn, String namePublishedInYear, String higherClassification, String kingdom, String phylum, String class_, String order, String family, String genus, String subgenus, String specificEpithet, String infraspecificEpithet, String infraspecificRank, String verbatimTaxonRank, String scientificNameAuthorship, String vernacularName, String nomenclaturalCode, String taxonomicStatus, String nomenclaturalStatus, String taxonRemarks) {
        this.occurrenceId = occurrenceId;
        this.catalogNumber = catalogNumber;
        this.remarks = remarks;
        this.collector = collector;
        this.sex = sex;
        this.lifeStage = lifeStage;
        this.taxonId = taxonId;
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
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.subgenus = subgenus;
        this.specificEpithet = specificEpithet;
        this.infraspecificEpithet = infraspecificEpithet;
        this.infraspecificRank = infraspecificRank;
        this.verbatimTaxonRank = verbatimTaxonRank;
        this.scientificNameAuthorship = scientificNameAuthorship;
        this.vernacularName = vernacularName;
        this.nomenclaturalCode = nomenclaturalCode;
        this.taxonomicStatus = taxonomicStatus;
        this.nomenclaturalStatus = nomenclaturalStatus;
        this.taxonRemarks = taxonRemarks;
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
        
        final OccurrenceCDTO other = (OccurrenceCDTO) obj;
        
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

        OccurrenceCDTO ol = (OccurrenceCDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters & Setters */

    public String getAcceptedNameUsage() {
        return acceptedNameUsage;
    }

    public void setAcceptedNameUsage(String acceptedNameUsage) {
        this.acceptedNameUsage = acceptedNameUsage;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getHigherClassification() {
        return higherClassification;
    }

    public void setHigherClassification(String higherClassification) {
        this.higherClassification = higherClassification;
    }

    public String getInfraspecificEpithet() {
        return infraspecificEpithet;
    }

    public void setInfraspecificEpithet(String infraspecificEpithet) {
        this.infraspecificEpithet = infraspecificEpithet;
    }

    public String getInfraspecificRank() {
        return infraspecificRank;
    }

    public void setInfraspecificRank(String infraspecificRank) {
        this.infraspecificRank = infraspecificRank;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(String lifeStage) {
        this.lifeStage = lifeStage;
    }

    public String getNameAccordingTo() {
        return nameAccordingTo;
    }

    public void setNameAccordingTo(String nameAccordingTo) {
        this.nameAccordingTo = nameAccordingTo;
    }

    public String getNamePublishedIn() {
        return namePublishedIn;
    }

    public void setNamePublishedIn(String namePublishedIn) {
        this.namePublishedIn = namePublishedIn;
    }

    public String getNamePublishedInYear() {
        return namePublishedInYear;
    }

    public void setNamePublishedInYear(String namePublishedInYear) {
        this.namePublishedInYear = namePublishedInYear;
    }

    public String getNomenclaturalCode() {
        return nomenclaturalCode;
    }

    public void setNomenclaturalCode(String nomenclaturalCode) {
        this.nomenclaturalCode = nomenclaturalCode;
    }

    public String getNomenclaturalStatus() {
        return nomenclaturalStatus;
    }

    public void setNomenclaturalStatus(String nomenclaturalStatus) {
        this.nomenclaturalStatus = nomenclaturalStatus;
    }

    public String getOccurrenceId() {
        return occurrenceId;
    }

    public void setOccurrenceId(String occurrenceId) {
        this.occurrenceId = occurrenceId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOriginalNameUsage() {
        return originalNameUsage;
    }

    public void setOriginalNameUsage(String originalNameUsage) {
        this.originalNameUsage = originalNameUsage;
    }

    public String getParentNameUsage() {
        return parentNameUsage;
    }

    public void setParentNameUsage(String parentNameUsage) {
        this.parentNameUsage = parentNameUsage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getScientificNameAuthorship() {
        return scientificNameAuthorship;
    }

    public void setScientificNameAuthorship(String scientificNameAuthorship) {
        this.scientificNameAuthorship = scientificNameAuthorship;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecificEpithet() {
        return specificEpithet;
    }

    public void setSpecificEpithet(String specificEpithet) {
        this.specificEpithet = specificEpithet;
    }

    public String getSubgenus() {
        return subgenus;
    }

    public void setSubgenus(String subgenus) {
        this.subgenus = subgenus;
    }

    public String getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(String taxonId) {
        this.taxonId = taxonId;
    }

    public String getTaxonRemarks() {
        return taxonRemarks;
    }

    public void setTaxonRemarks(String taxonRemarks) {
        this.taxonRemarks = taxonRemarks;
    }

    public String getTaxonomicStatus() {
        return taxonomicStatus;
    }

    public void setTaxonomicStatus(String taxonomicStatus) {
        this.taxonomicStatus = taxonomicStatus;
    }

    public String getVerbatimTaxonRank() {
        return verbatimTaxonRank;
    }

    public void setVerbatimTaxonRank(String verbatimTaxonRank) {
        this.verbatimTaxonRank = verbatimTaxonRank;
    }

    public String getVernacularName() {
        return vernacularName;
    }

    public void setVernacularName(String vernacularName) {
        this.vernacularName = vernacularName;
    }

}