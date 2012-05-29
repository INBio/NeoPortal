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

import java.math.BigDecimal;

/**
 *
 * @author avargas
 */

public class Images
        implements java.io.Serializable{
    
    private BigDecimal ImageId;
    private BigDecimal M3sImageId;
    private Taxon taxon;
    private String author;
    private String rights;

    public Images() {
    }

    public BigDecimal getImageId() {
        return ImageId;
    }

    public void setImageId(BigDecimal ImageId) {
        this.ImageId = ImageId;
    }

    public BigDecimal getM3sImageId() {
        return M3sImageId;
    }

    public void setM3sImageId(BigDecimal M3sImageId) {
        this.M3sImageId = M3sImageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }
    
}
