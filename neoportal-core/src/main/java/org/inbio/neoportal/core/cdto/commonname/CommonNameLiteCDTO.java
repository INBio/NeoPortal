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
package org.inbio.neoportal.core.cdto.commonname;

import java.math.BigDecimal;
import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 * A subset of the data hold by the CommonName entity
 * @author asanabria
 */
public class CommonNameLiteCDTO 
    extends BaseDTO 
        implements Comparable {

    private BigDecimal commonNameId;
    private String name;
    private String usedBy;

    /*
     * Empty constructor
     */
    public CommonNameLiteCDTO() {
    }

    /**
     * If just the name is well known
     * @param name 
     */
    public CommonNameLiteCDTO(String name) {
        this.name = name;
    }

    /**
     * Use the name and the people qualifier that use that name.
     * @param name
     * @param usedBy 
     */
    public CommonNameLiteCDTO(String name, String usedBy) {
        this.name = name;
        this.usedBy = usedBy;
    }
    
    /**
     * create the dto with id, name, and who is using that name.
     * @param commonNameId
     * @param name
     * @param usedBy 
     */
    public CommonNameLiteCDTO(BigDecimal commonNameId, String name, String usedBy) {
        this.commonNameId = commonNameId;
        this.name = name;
        this.usedBy = usedBy;
    }
   
    
    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;

        hash = 23 * hash
            + (this.name != null ? this.name.hashCode() : 0);

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

        final CommonNameLiteCDTO other = (CommonNameLiteCDTO) obj;

        if ((this.name == null)
            ? (other.name != null)
            : !this.name.equals(other.name)) {

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

        CommonNameLiteCDTO ol = (CommonNameLiteCDTO) o;
        return this.name.compareTo(ol.getname());
    }

    /* Getters & Setters */
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public BigDecimal getCommonNameId() {
        return commonNameId;
    }

    public void setCommonNameId(BigDecimal commonNameId) {
        this.commonNameId = commonNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }
}