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

import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class ImagesCDTO 
        extends BaseDTO 
            implements Comparable {
    
    private String imageId;
    private String m3sImageId;
    private String taxonId;
    private String author;
    private String rights;

    @Override
    public int compareTo(Object t) {
        return this.m3sImageId.compareTo(((ImagesCDTO)t).getM3sImageId());
    }

    public ImagesCDTO() {
    }

        
    public ImagesCDTO(String imageId, String m3sImageId, String taxonId, String author, String rights) {
        this.imageId = imageId;
        this.m3sImageId = m3sImageId;
        this.taxonId = taxonId;
        this.author = author;
        this.rights = rights;
    }
    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getM3sImageId() {
        return m3sImageId;
    }

    public void setM3sImageId(String m3sImageId) {
        this.m3sImageId = m3sImageId;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(String taxonId) {
        this.taxonId = taxonId;
    }
    
}
