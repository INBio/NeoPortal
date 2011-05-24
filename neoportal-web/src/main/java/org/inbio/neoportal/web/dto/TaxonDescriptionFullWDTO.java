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

import java.io.UnsupportedEncodingException;
import javax.xml.bind.annotation.XmlElement;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 *
 * @author avargas
 */
@RooJavaBean
@RooToString
public class TaxonDescriptionFullWDTO {
    
    @XmlElement(name="scientificName")
    private String scientificName;

    @XmlElement(name="institutionCode")
    private String institutionCode;
    
    @XmlElement(name="dateLastModified")
    private String dateLastModified;
    
    @XmlElement(name="taxonRecordId")
    private String taxonRecordId;
    
    @XmlElement(name="language")
    private String language;
    
    @XmlElement(name="creators")
    private String creators;
    
    @XmlElement(name="distribution")
    private String distribution;
    
    @XmlElement(name="abstract_")
    private String abstract_;
    
    @XmlElement(name="kingdomTaxon")
    private String kingdomTaxon;
    
    @XmlElement(name="phylumTaxon")
    private String phylumTaxon;
    
    @XmlElement(name="classTaxon")
    private String classTaxon;
    
    @XmlElement(name="orderTaxon")
    private String orderTaxon;

    @XmlElement(name="familyTaxon")
    private String familyTaxon;

    @XmlElement(name="genusTaxon")
    private String genusTaxon;

    @XmlElement(name="synonyms")
    private String synonyms;

    @XmlElement(name="authorYearOfScientificName")
    private String authorYearOfScientificName;
    
    private String speciesPublicationReference;
    
    @XmlElement(name="commonNames")
    private String commonNames;

    @XmlElement(name="typification")
    private String typification;
    
    @XmlElement(name="contributors")
    private String contributors;
    
    @XmlElement(name="dateCreated")
    private String dateCreated;
    
    @XmlElement(name="habit")
    private String habit;
    
    @XmlElement(name="lifeCycle")
    private String lifeCycle;
    
    @XmlElement(name="reproduction")
    private String reproduction;
    
    @XmlElement(name="annualCycle")
    private String annualCycle;
    
    private String scientificDescription;
    
    @XmlElement(name="briefDescription")
    private String briefDescription;
    
    @XmlElement(name="feeding")
    private String feeding;
    
    @XmlElement(name="behavior")
    private String behavior;
    
    @XmlElement(name="interactions")
    private String interactions;
    
    @XmlElement(name="chromosomicNumberN")
    private String chromosomicNumberN;
    
    @XmlElement(name="molecularData")
    private String molecularData;
    
    @XmlElement(name="populationBiology")
    private String populationBiology;
    
    @XmlElement(name="threatStatus")
    private String threatStatus;
    
    @XmlElement(name="legislation")
    private String legislation;
    
    @XmlElement(name="habitat")
    private String habitat;
    
    @XmlElement(name="territory")
    private String territory;
    
    @XmlElement(name="endemicity")
    private String endemicity;
    
    private String theUses;
    
    @XmlElement(name="theManagement")
    private String theManagement;
    
    @XmlElement(name="folklore")
    private String folklore;
    
    @XmlElement(name="theReferences")
    private String theReferences;
    
    @XmlElement(name="unstructuredDocumentation")
    private String unstructuredDocumentation;
    
    @XmlElement(name="otherInformationSources")
    private String otherInformationSources;
    
    @XmlElement(name="papers")
    private String papers;
    
    @XmlElement(name="identificationKeys")
    private String identificationKeys;
    
    @XmlElement(name="migratoryData")
    private String migratoryData;
    
    @XmlElement(name="ecologicalSignificance")
    private String ecologicalSignificance;
    
    @XmlElement(name="unstructuredNaturalHistory")
    private String unstructuredNaturalHistory;
    
    @XmlElement(name="invasivenessData")
    private String invasivenessData;
    
    @XmlElement(name="targetAudiences")
    private String targetAudiences;

    public TaxonDescriptionFullWDTO() {
        
    }

    public TaxonDescriptionFullWDTO(String scientificName, String institutionCode, String dateLastModified, String taxonRecordId, String language, String creators, String distribution, String abstract_, String kingdomTaxon, String phylumTaxon, String classTaxon, String orderTaxon, String familyTaxon, String genusTaxon, String synonyms, String authorYearOfScientificName, String speciesPublicationReference, String commonNames, String typification, String contributors, String dateCreated, String habit, String lifeCycle, String reproduction, String annualCycle, String scientificDescription, String briefDescription, String feeding, String behavior, String interactions, String chromosomicNumberN, String molecularData, String populationBiology, String threatStatus, String legislation, String habitat, String territory, String endemicity, String theUses, String theManagement, String folklore, String theReferences, String unstructuredDocumentation, String otherInformationSources, String papers, String identificationKeys, String migratoryData, String ecologicalSignificance, String unstructuredNaturalHistory, String invasivenessData, String targetAudiences) {
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

    @XmlElement(name="speciesPublicationReference")
    public String getSpeciesPublicationReference() throws UnsupportedEncodingException {
        speciesPublicationReference = speciesPublicationReference.replace("\u000b", "" );
        //return StringEscapeUtils.escapeHtml(speciesPublicationReference);
        return speciesPublicationReference;
    }
    
    @XmlElement(name="scientificDescription")
    public String getScientificDescription() throws UnsupportedEncodingException {
        scientificDescription = scientificDescription.replace("\u000b", "" );
        //return StringEscapeUtils.escapeHtml(scientificDescription);
        return scientificDescription;
    }
    
    @XmlElement(name="theUses")
    public String getTheUses() throws UnsupportedEncodingException {
        theUses = theUses.replace("\u000b", "" );
        //return StringEscapeUtils.escapeHtml(theUses);
        return theUses;
    }
}
