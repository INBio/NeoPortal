package org.inbio.neoportal.core.entity;
// Generated 02/03/2011 05:06:45 PM by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * TaxonAssociatedAttributeHasReferenceElementId generated by hbm2java
 */

public class TaxonAssociatedAttributeHasReferenceElementId  implements java.io.Serializable {


    // Fields    

     private BigDecimal associatedAttributeId;
     private BigDecimal referenceElementId;
     private BigDecimal taxonId;


    // Constructors

    /** default constructor */
    public TaxonAssociatedAttributeHasReferenceElementId() {
    }

    
    /** full constructor */
    public TaxonAssociatedAttributeHasReferenceElementId(BigDecimal associatedAttributeId, BigDecimal referenceElementId, BigDecimal taxonId) {
        this.associatedAttributeId = associatedAttributeId;
        this.referenceElementId = referenceElementId;
        this.taxonId = taxonId;
    }
    

   
    // Property accessors

    public BigDecimal getAssociatedAttributeId() {
        return this.associatedAttributeId;
    }
    
    public void setAssociatedAttributeId(BigDecimal associatedAttributeId) {
        this.associatedAttributeId = associatedAttributeId;
    }

    public BigDecimal getReferenceElementId() {
        return this.referenceElementId;
    }
    
    public void setReferenceElementId(BigDecimal referenceElementId) {
        this.referenceElementId = referenceElementId;
    }

    public BigDecimal getTaxonId() {
        return this.taxonId;
    }
    
    public void setTaxonId(BigDecimal taxonId) {
        this.taxonId = taxonId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TaxonAssociatedAttributeHasReferenceElementId) ) return false;
		 TaxonAssociatedAttributeHasReferenceElementId castOther = ( TaxonAssociatedAttributeHasReferenceElementId ) other; 
         
		 return ( (this.getAssociatedAttributeId()==castOther.getAssociatedAttributeId()) || ( this.getAssociatedAttributeId()!=null && castOther.getAssociatedAttributeId()!=null && this.getAssociatedAttributeId().equals(castOther.getAssociatedAttributeId()) ) )
 && ( (this.getReferenceElementId()==castOther.getReferenceElementId()) || ( this.getReferenceElementId()!=null && castOther.getReferenceElementId()!=null && this.getReferenceElementId().equals(castOther.getReferenceElementId()) ) )
 && ( (this.getTaxonId()==castOther.getTaxonId()) || ( this.getTaxonId()!=null && castOther.getTaxonId()!=null && this.getTaxonId().equals(castOther.getTaxonId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAssociatedAttributeId() == null ? 0 : this.getAssociatedAttributeId().hashCode() );
         result = 37 * result + ( getReferenceElementId() == null ? 0 : this.getReferenceElementId().hashCode() );
         result = 37 * result + ( getTaxonId() == null ? 0 : this.getTaxonId().hashCode() );
         return result;
   }   





}