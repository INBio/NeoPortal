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
// Generated 02/03/2011 05:06:45 PM by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @author asanabria
 * TaxonHasReferenceElementId generated by hbm2java
 */

public class TaxonHasReferenceElementId  implements java.io.Serializable {


    // Fields    

     private BigDecimal taxonId;
     private BigDecimal referenceElementId;


    // Constructors

    /** default constructor */
    public TaxonHasReferenceElementId() {
    }

    
    /** full constructor */
    public TaxonHasReferenceElementId
        (BigDecimal taxonId, BigDecimal referenceElementId) {
        
        this.taxonId = taxonId;
        this.referenceElementId = referenceElementId;
    }
    

   
    // Property accessors

    public BigDecimal getTaxonId() {
        return this.taxonId;
    }
    
    public void setTaxonId(BigDecimal taxonId) {
        this.taxonId = taxonId;
    }

    public BigDecimal getReferenceElementId() {
        return this.referenceElementId;
    }
    
    public void setReferenceElementId(BigDecimal referenceElementId) {
        this.referenceElementId = referenceElementId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TaxonHasReferenceElementId) ) return false;
		 TaxonHasReferenceElementId castOther = ( TaxonHasReferenceElementId ) other; 
         
		 return ( (this.getTaxonId()==castOther.getTaxonId()) || ( this.getTaxonId()!=null && castOther.getTaxonId()!=null && this.getTaxonId().equals(castOther.getTaxonId()) ) )
 && ( (this.getReferenceElementId()==castOther.getReferenceElementId()) || ( this.getReferenceElementId()!=null && castOther.getReferenceElementId()!=null && this.getReferenceElementId().equals(castOther.getReferenceElementId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTaxonId() == null ? 0 : this.getTaxonId().hashCode() );
         result = 37 * result + ( getReferenceElementId() == null ? 0 : this.getReferenceElementId().hashCode() );
         return result;
   }   





}
