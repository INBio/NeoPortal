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

import org.inbio.neoportal.core.common.dto.*;

/**
 * A subset of the data hold by the taxon entity
 * @author asanabria
 */
public class TaxonLiteCDTO 
    extends BaseDTO 
        implements Comparable {

    private String defaultName;
    private String commonNames;
    private ArrayList<ImagesCDTO> imageList;
    private String imageUrl;

    public TaxonLiteCDTO() {
    }

    public TaxonLiteCDTO(String defaultName) {

        this.defaultName = defaultName;
    }

    public TaxonLiteCDTO
        (String scientificName, String commonNames) {
        
        this.defaultName = scientificName;
        this.commonNames = commonNames;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 23 * hash + 
            (this.defaultName != null ? this.defaultName.hashCode() : 0);
        
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
        
        final TaxonLiteCDTO other = (TaxonLiteCDTO) obj;
        
        if ((this.defaultName == null) ? 
                (other.defaultName != null) :
                !this.defaultName.equals(other.defaultName)) {
            
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

        TaxonLiteCDTO ol = (TaxonLiteCDTO)o;
        return this.defaultName.compareTo(ol.getDefaultName());
    }

    /* Getters & Setters */

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(String commonNameList) {
        this.commonNames = commonNameList;
    }

    public ArrayList<ImagesCDTO> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImagesCDTO> imageList) {
        this.imageList = imageList;
    }

    /**
     * The imageUrl from UBIS for use like thumb
     * @return 
     */
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
        
}