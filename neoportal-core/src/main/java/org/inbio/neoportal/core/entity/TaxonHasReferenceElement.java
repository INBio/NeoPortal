package org.inbio.neoportal.core.entity;
// Generated 02/03/2011 05:06:44 PM by Hibernate Tools 3.1.0.beta4



/**
 * TaxonHasReferenceElement generated by hbm2java
 */

public class TaxonHasReferenceElement  implements java.io.Serializable {


    // Fields    

     private TaxonHasReferenceElementId id;
     private ReferenceElement referenceElement;
     private Taxon taxon;
     private String value;


    // Constructors

    /** default constructor */
    public TaxonHasReferenceElement() {
    }

	/** minimal constructor */
    public TaxonHasReferenceElement(TaxonHasReferenceElementId id, ReferenceElement referenceElement, Taxon taxon) {
        this.id = id;
        this.referenceElement = referenceElement;
        this.taxon = taxon;
    }
    
    /** full constructor */
    public TaxonHasReferenceElement(TaxonHasReferenceElementId id, ReferenceElement referenceElement, Taxon taxon, String value) {
        this.id = id;
        this.referenceElement = referenceElement;
        this.taxon = taxon;
        this.value = value;
    }
    

   
    // Property accessors

    public TaxonHasReferenceElementId getId() {
        return this.id;
    }
    
    public void setId(TaxonHasReferenceElementId id) {
        this.id = id;
    }

    public ReferenceElement getReferenceElement() {
        return this.referenceElement;
    }
    
    public void setReferenceElement(ReferenceElement referenceElement) {
        this.referenceElement = referenceElement;
    }

    public Taxon getTaxon() {
        return this.taxon;
    }
    
    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
   








}