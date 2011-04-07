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
package org.inbio.neoportal.core.dto.taxon;

import java.util.ArrayList;
import org.inbio.neoportal.core.dto.*;
import org.inbio.neoportal.core.dto.commonname.CommonNameLiteDTO;

/**
 * A subset of the data hold by the taxon entity
 * @author asanabria
 */
public class TaxonLiteDTO extends BaseDTO implements Comparable {

    private String scientificName;
    private ArrayList<CommonNameLiteDTO> commonNameList;

    public TaxonLiteDTO() {
    }

    public TaxonLiteDTO(String scientificName) {

        this.scientificName = scientificName;
    }

    public TaxonLiteDTO
        (String scientificName, ArrayList<CommonNameLiteDTO> commonNameList) {
        
        this.scientificName = scientificName;
        this.commonNameList = commonNameList;
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
        
        final TaxonLiteDTO other = (TaxonLiteDTO) obj;
        
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

        TaxonLiteDTO ol = (TaxonLiteDTO)o;
        return this.scientificName.compareTo(ol.getScientificName());
    }

    /* Getters & Setters */

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public ArrayList<CommonNameLiteDTO> getCommonNameList() {
        return commonNameList;
    }

    public void setCommonNameList(ArrayList<CommonNameLiteDTO> commonNameList) {
        this.commonNameList = commonNameList;
    }
    
}