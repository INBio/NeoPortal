package org.inbio.neoportal.core.entity;
// Generated 02/03/2011 05:06:44 PM by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * ReferenceElement generated by hbm2java
 */

public class ReferenceElement  implements java.io.Serializable {


    // Fields    

     private BigDecimal referenceElementId;
     private String name;
     private String label;
     private String description;
     private String guideLines;
     private String examples;
     private Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements = new HashSet<OccurrenceHasReferenceElement>(0);
     private Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements_1 = new HashSet<OccurrenceHasReferenceElement>(0);
     private Set<TaxonDescriptionHasReferenceElement> taxonDescriptionHasReferenceElements = new HashSet<TaxonDescriptionHasReferenceElement>(0);
     private Set<TaxonHasReferenceElement> taxonHasReferenceElements = new HashSet<TaxonHasReferenceElement>(0);


    // Constructors

    /** default constructor */
    public ReferenceElement() {
    }

	/** minimal constructor */
    public ReferenceElement(BigDecimal referenceElementId) {
        this.referenceElementId = referenceElementId;
    }
    
    /** full constructor */
    public ReferenceElement(BigDecimal referenceElementId, String name, String label, String description, String guideLines, String examples, Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements, Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements_1, Set<TaxonDescriptionHasReferenceElement> taxonDescriptionHasReferenceElements, Set<TaxonHasReferenceElement> taxonHasReferenceElements) {
        this.referenceElementId = referenceElementId;
        this.name = name;
        this.label = label;
        this.description = description;
        this.guideLines = guideLines;
        this.examples = examples;
        this.occurrenceHasReferenceElements = occurrenceHasReferenceElements;
        this.occurrenceHasReferenceElements_1 = occurrenceHasReferenceElements_1;
        this.taxonDescriptionHasReferenceElements = taxonDescriptionHasReferenceElements;
        this.taxonHasReferenceElements = taxonHasReferenceElements;
    }
    

   
    // Property accessors

    public BigDecimal getReferenceElementId() {
        return this.referenceElementId;
    }
    
    public void setReferenceElementId(BigDecimal referenceElementId) {
        this.referenceElementId = referenceElementId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuideLines() {
        return this.guideLines;
    }
    
    public void setGuideLines(String guideLines) {
        this.guideLines = guideLines;
    }

    public String getExamples() {
        return this.examples;
    }
    
    public void setExamples(String examples) {
        this.examples = examples;
    }

    public Set<OccurrenceHasReferenceElement> getOccurrenceHasReferenceElements() {
        return this.occurrenceHasReferenceElements;
    }
    
    public void setOccurrenceHasReferenceElements(Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements) {
        this.occurrenceHasReferenceElements = occurrenceHasReferenceElements;
    }

    public Set<OccurrenceHasReferenceElement> getOccurrenceHasReferenceElements_1() {
        return this.occurrenceHasReferenceElements_1;
    }
    
    public void setOccurrenceHasReferenceElements_1(Set<OccurrenceHasReferenceElement> occurrenceHasReferenceElements_1) {
        this.occurrenceHasReferenceElements_1 = occurrenceHasReferenceElements_1;
    }

    public Set<TaxonDescriptionHasReferenceElement> getTaxonDescriptionHasReferenceElements() {
        return this.taxonDescriptionHasReferenceElements;
    }
    
    public void setTaxonDescriptionHasReferenceElements(Set<TaxonDescriptionHasReferenceElement> taxonDescriptionHasReferenceElements) {
        this.taxonDescriptionHasReferenceElements = taxonDescriptionHasReferenceElements;
    }

    public Set<TaxonHasReferenceElement> getTaxonHasReferenceElements() {
        return this.taxonHasReferenceElements;
    }
    
    public void setTaxonHasReferenceElements(Set<TaxonHasReferenceElement> taxonHasReferenceElements) {
        this.taxonHasReferenceElements = taxonHasReferenceElements;
    }
   








}