package org.inbio.neoportal.core.entity;
// Generated 02/03/2011 05:06:45 PM by Hibernate Tools 3.1.0.beta4



/**
 * TaxonAssociatedAttributeHasReferenceElement generated by hbm2java
 */

public class TaxonAssociatedAttributeHasReferenceElement  implements java.io.Serializable {


    // Fields    

     private TaxonAssociatedAttributeHasReferenceElementId id;
     private TaxonHasAssociatedAttribute taxonHasAssociatedAttribute;
     private String value;


    // Constructors

    /** default constructor */
    public TaxonAssociatedAttributeHasReferenceElement() {
    }

	/** minimal constructor */
    public TaxonAssociatedAttributeHasReferenceElement(TaxonAssociatedAttributeHasReferenceElementId id, TaxonHasAssociatedAttribute taxonHasAssociatedAttribute) {
        this.id = id;
        this.taxonHasAssociatedAttribute = taxonHasAssociatedAttribute;
    }
    
    /** full constructor */
    public TaxonAssociatedAttributeHasReferenceElement(TaxonAssociatedAttributeHasReferenceElementId id, TaxonHasAssociatedAttribute taxonHasAssociatedAttribute, String value) {
        this.id = id;
        this.taxonHasAssociatedAttribute = taxonHasAssociatedAttribute;
        this.value = value;
    }
    

   
    // Property accessors

    public TaxonAssociatedAttributeHasReferenceElementId getId() {
        return this.id;
    }
    
    public void setId(TaxonAssociatedAttributeHasReferenceElementId id) {
        this.id = id;
    }

    public TaxonHasAssociatedAttribute getTaxonHasAssociatedAttribute() {
        return this.taxonHasAssociatedAttribute;
    }
    
    public void setTaxonHasAssociatedAttribute(TaxonHasAssociatedAttribute taxonHasAssociatedAttribute) {
        this.taxonHasAssociatedAttribute = taxonHasAssociatedAttribute;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
   








}