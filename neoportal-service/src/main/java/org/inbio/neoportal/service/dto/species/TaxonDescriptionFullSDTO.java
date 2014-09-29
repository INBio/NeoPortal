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
package org.inbio.neoportal.service.dto.species;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.inbio.neoportal.core.common.dto.BaseDTO;
/**
 *
 * @author avargas
 */
@XmlRootElement(name="taxonDescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaxonDescriptionFullSDTO
    extends BaseDTO 
        implements Comparable {
    
    private String scientificName;

    private String institutionCode;
    private String dateLastModified;
    private String taxonRecordId;
    private String language;
    private String creators;
    private String distribution;
    private String abstract_;

    private String kingdomTaxon;

    private String phylumTaxon;

    private String classTaxon;

    private String orderTaxon;

    private String familyTaxon;

    private String genusTaxon;

    private String synonyms;

    private String authorYearOfScientificName;
    private String speciesPublicationReference;

    private String commonNames;

    private String typification;
    private String contributors;
    private String dateCreated;
    private String habit;
    private String lifeCycle;
    private String reproduction;
    private String annualCycle;
    private String scientificDescription;
    private String briefDescription;
    private String feeding;
    private String behavior;
    private String interactions;
    private String chromosomicNumberN;
    private String molecularData;
    private String populationBiology;
    private String threatStatus;
    private String legislation;
    private String habitat;
    private String territory;
    private String endemicity;
    private String theUses;
    private String theManagement;
    private String folklore;
    private String theReferences;
    private String unstructuredDocumentation;
    private String otherInformationSources;
    private String papers;
    private String identificationKeys;
    private String migratoryData;
    private String ecologicalSignificance;
    private String unstructuredNaturalHistory;
    private String invasivenessData;
    private String targetAudiences;

    public TaxonDescriptionFullSDTO() {
    
    }

    public TaxonDescriptionFullSDTO(String scientificName, 
        String institutionCode, 
        String dateLastModified, 
        String taxonRecordId, 
        String language, 
        String creators, 
        String distribution, 
        String abstract_, 
        String kingdomTaxon, 
        String phylumTaxon, 
        String classTaxon, 
        String orderTaxon, 
        String familyTaxon, 
        String genusTaxon, 
        String synonyms, 
        String authorYearOfScientificName, 
        String speciesPublicationReference, 
        String commonNames, 
        String typification, 
        String contributors, 
        String dateCreated, 
        String habit, 
        String lifeCycle, 
        String reproduction, 
        String annualCycle, 
        String scientificDescription, 
        String briefDescription, 
        String feeding, 
        String behavior, 
        String interactions, 
        String chromosomicNumberN, 
        String molecularData, 
        String populationBiology, 
        String threatStatus, 
        String legislation, 
        String habitat, 
        String territory, 
        String endemicity, 
        String theUses, 
        String theManagement, 
        String folklore, 
        String theReferences, 
        String unstructuredDocumentation, 
        String otherInformationSources, 
        String papers, 
        String identificationKeys, 
        String migratoryData, 
        String ecologicalSignificance, 
        String unstructuredNaturalHistory, 
        String invasivenessData, 
        String targetAudiences) {
        
        this.scientificName = scientificName;
        this.institutionCode = institutionCode;
        this.dateLastModified = dateLastModified;
        this.taxonRecordId = taxonRecordId;
        this.language = language;
        this.creators = creators;
        this.distribution = distribution;
        this.abstract_ = abstract_;
        this.kingdomTaxon = kingdomTaxon;
        this.phylumTaxon = phylumTaxon;
        this.classTaxon = classTaxon;
        this.orderTaxon = orderTaxon;
        this.familyTaxon = familyTaxon;
        this.genusTaxon = genusTaxon;
        this.synonyms = synonyms;
        this.authorYearOfScientificName = authorYearOfScientificName;
        this.speciesPublicationReference = speciesPublicationReference;
        this.commonNames = commonNames;
        this.typification = typification;
        this.contributors = contributors;
        this.dateCreated = dateCreated;
        this.habit = habit;
        this.lifeCycle = lifeCycle;
        this.reproduction = reproduction;
        this.annualCycle = annualCycle;
        this.scientificDescription = scientificDescription;
        this.briefDescription = briefDescription;
        this.feeding = feeding;
        this.behavior = behavior;
        this.interactions = interactions;
        this.chromosomicNumberN = chromosomicNumberN;
        this.molecularData = molecularData;
        this.populationBiology = populationBiology;
        this.threatStatus = threatStatus;
        this.legislation = legislation;
        this.habitat = habitat;
        this.territory = territory;
        this.endemicity = endemicity;
        this.theUses = theUses;
        this.theManagement = theManagement;
        this.folklore = folklore;
        this.theReferences = theReferences;
        this.unstructuredDocumentation = unstructuredDocumentation;
        this.otherInformationSources = otherInformationSources;
        this.papers = papers;
        this.identificationKeys = identificationKeys;
        this.migratoryData = migratoryData;
        this.ecologicalSignificance = ecologicalSignificance;
        this.unstructuredNaturalHistory = unstructuredNaturalHistory;
        this.invasivenessData = invasivenessData;
        this.targetAudiences = targetAudiences;
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
        
        final TaxonDescriptionFullSDTO other = (TaxonDescriptionFullSDTO) obj;
        
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

        TaxonDescriptionFullSDTO ol = (TaxonDescriptionFullSDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters and Setters */
    
    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getAnnualCycle() {
        return annualCycle;
    }

    public void setAnnualCycle(String annualCycle) {
        this.annualCycle = annualCycle;
    }

    public String getAuthorYearOfScientificName() {
        return authorYearOfScientificName;
    }

    public void setAuthorYearOfScientificName(String authorYearOfScientificName) {
        this.authorYearOfScientificName = authorYearOfScientificName;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getChromosomicNumberN() {
        return chromosomicNumberN;
    }

    public void setChromosomicNumberN(String chromosomicNumberN) {
        this.chromosomicNumberN = chromosomicNumberN;
    }

    public String getClassTaxon() {
        return classTaxon;
    }

    public void setClassTaxon(String classTaxon) {
        this.classTaxon = classTaxon;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNames) {
        this.commonNames = commonNames;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(String dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getEcologicalSignificance() {
        return ecologicalSignificance;
    }

    public void setEcologicalSignificance(String ecologicalSignificance) {
        this.ecologicalSignificance = ecologicalSignificance;
    }

    public String getEndemicity() {
        return endemicity;
    }

    public void setEndemicity(String endemicity) {
        this.endemicity = endemicity;
    }

    public String getFamilyTaxon() {
        return familyTaxon;
    }

    public void setFamilyTaxon(String familyTaxon) {
        this.familyTaxon = familyTaxon;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getFolklore() {
        return folklore;
    }

    public void setFolklore(String folklore) {
        this.folklore = folklore;
    }

    public String getGenusTaxon() {
        return genusTaxon;
    }

    public void setGenusTaxon(String genusTaxon) {
        this.genusTaxon = genusTaxon;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getIdentificationKeys() {
        return identificationKeys;
    }

    public void setIdentificationKeys(String identificationKeys) {
        this.identificationKeys = identificationKeys;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getInvasivenessData() {
        return invasivenessData;
    }

    public void setInvasivenessData(String invasivenessData) {
        this.invasivenessData = invasivenessData;
    }

    public String getKingdomTaxon() {
        return kingdomTaxon;
    }

    public void setKingdomTaxon(String kingdomTaxon) {
        this.kingdomTaxon = kingdomTaxon;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLegislation() {
        return legislation;
    }

    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getMigratoryData() {
        return migratoryData;
    }

    public void setMigratoryData(String migratoryData) {
        this.migratoryData = migratoryData;
    }

    public String getMolecularData() {
        return molecularData;
    }

    public void setMolecularData(String molecularData) {
        this.molecularData = molecularData;
    }

    public String getOrderTaxon() {
        return orderTaxon;
    }

    public void setOrderTaxon(String orderTaxon) {
        this.orderTaxon = orderTaxon;
    }

    public String getOtherInformationSources() {
        return otherInformationSources;
    }

    public void setOtherInformationSources(String otherInformationSources) {
        this.otherInformationSources = otherInformationSources;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public String getPhylumTaxon() {
        return phylumTaxon;
    }

    public void setPhylumTaxon(String phylumTaxon) {
        this.phylumTaxon = phylumTaxon;
    }

    public String getPopulationBiology() {
        return populationBiology;
    }

    public void setPopulationBiology(String populationBiology) {
        this.populationBiology = populationBiology;
    }

    public String getReproduction() {
        return reproduction;
    }

    public void setReproduction(String reproduction) {
        this.reproduction = reproduction;
    }

    public String getScientificDescription() {
        return scientificDescription;
    }

    public void setScientificDescription(String scientificDescription) {
        this.scientificDescription = scientificDescription;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSpeciesPublicationReference() {
        return speciesPublicationReference;
    }

    public void setSpeciesPublicationReference(String speciesPublicationReference) {
        this.speciesPublicationReference = speciesPublicationReference;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getTargetAudiences() {
        return targetAudiences;
    }

    public void setTargetAudiences(String targetAudiences) {
        this.targetAudiences = targetAudiences;
    }

    public String getTaxonRecordId() {
        return taxonRecordId;
    }

    public void setTaxonRecordId(String taxonRecordId) {
        this.taxonRecordId = taxonRecordId;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getTheManagement() {
        return theManagement;
    }

    public void setTheManagement(String theManagement) {
        this.theManagement = theManagement;
    }

    public String getTheReferences() {
        return theReferences;
    }

    public void setTheReferences(String theReferences) {
        this.theReferences = theReferences;
    }

    public String getTheUses() {
        return theUses;
    }

    public void setTheUses(String theUses) {
        this.theUses = theUses;
    }

    public String getThreatStatus() {
        return threatStatus;
    }

    public void setThreatStatus(String threatStatus) {
        this.threatStatus = threatStatus;
    }

    public String getTypification() {
        return typification;
    }

    public void setTypification(String typification) {
        this.typification = typification;
    }

    public String getUnstructuredDocumentation() {
        return unstructuredDocumentation;
    }

    public void setUnstructuredDocumentation(String unstructuredDocumentation) {
        this.unstructuredDocumentation = unstructuredDocumentation;
    }

    public String getUnstructuredNaturalHistory() {
        return unstructuredNaturalHistory;
    }

    public void setUnstructuredNaturalHistory(String unstructuredNaturalHistory) {
        this.unstructuredNaturalHistory = unstructuredNaturalHistory;
    }
    
}
