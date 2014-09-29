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
package org.inbio.neoportal.core.dto.taxondescription;

import org.inbio.neoportal.core.common.dto.*;

/**
 * A subset of the data hold by the taxon entity
 * @author asanabria
 */
public class TaxonDescriptionLiteCDTO 
    extends BaseDTO 
        implements Comparable {

    private String scientificName;
    private String commonNameList;
    private String institution;

    // Constructors
    public TaxonDescriptionLiteCDTO() {
    }

    public TaxonDescriptionLiteCDTO(String scientificName) {

        this.scientificName = scientificName;
    }

    public TaxonDescriptionLiteCDTO(String scientificName,
        String commonNameList, 
        String institution) {
        
        this.scientificName = scientificName;
        this.commonNameList = commonNameList;
        this.institution = institution;

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
        
        final TaxonDescriptionLiteCDTO other = (TaxonDescriptionLiteCDTO) obj;
        
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

        TaxonDescriptionLiteCDTO ol = (TaxonDescriptionLiteCDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters & Setters */
    public String getCommonNameList() {
        return commonNameList;
    }

    public void setCommonNameList(String commonNameList) {
        this.commonNameList = commonNameList;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
}