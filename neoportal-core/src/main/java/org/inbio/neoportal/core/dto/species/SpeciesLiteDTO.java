/*
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
package org.inbio.neoportal.core.dto.species;

import org.inbio.neoportal.core.dto.*;

/**
 *
 * @author esmata
 */
public class SpeciesLiteDTO extends BaseDTO implements Comparable {

    private String imageURL;
	private String commonName;
    private String scientificName;

    public SpeciesLiteDTO() {
    }

    public SpeciesLiteDTO(String imageURL,
                            String commonName,
                            String scientificName) {

        this.imageURL = imageURL;
        this.commonName = commonName;
        this.scientificName = scientificName;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.getScientificName() != null ? this.getScientificName().hashCode() : 0);
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
        final SpeciesLiteDTO other = (SpeciesLiteDTO) obj;
        if ((this.getScientificName() == null) ? (other.getScientificName() != null) : !this.scientificName.equals(other.scientificName)) {
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

        SpeciesLiteDTO ol = (SpeciesLiteDTO)o;
        return this.getScientificName().compareTo(ol.getScientificName());
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return the commonName
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * @param commonName the commonName to set
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * @return the scientificName
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * @param scientificName the scientificName to set
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

}
