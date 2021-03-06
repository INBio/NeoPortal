package org.inbio.neoportal.core.entity;
// Generated 26/04/2012 10:06:32 AM by Hibernate Tools 3.2.1.GA



/**
 * OccurrenceHasReferenceElement generated by hbm2java
 */
public class OccurrenceHasReferenceElement  implements java.io.Serializable {


     private OccurrenceHasReferenceElementId id;
     private ReferenceElement referenceElement;
     private Occurrence occurrence;
     private String value;

    public OccurrenceHasReferenceElement() {
    }

	
    public OccurrenceHasReferenceElement(OccurrenceHasReferenceElementId id, ReferenceElement referenceElement, Occurrence occurrence) {
        this.id = id;
        this.referenceElement = referenceElement;
        this.occurrence = occurrence;
    }
    public OccurrenceHasReferenceElement(OccurrenceHasReferenceElementId id, ReferenceElement referenceElement, Occurrence occurrence, String value) {
       this.id = id;
       this.referenceElement = referenceElement;
       this.occurrence = occurrence;
       this.value = value;
    }
   
    public OccurrenceHasReferenceElementId getId() {
        return this.id;
    }
    
    public void setId(OccurrenceHasReferenceElementId id) {
        this.id = id;
    }
    public ReferenceElement getReferenceElement() {
        return this.referenceElement;
    }
    
    public void setReferenceElement(ReferenceElement referenceElement) {
        this.referenceElement = referenceElement;
    }
    public Occurrence getOccurrence() {
        return this.occurrence;
    }
    
    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }




}


