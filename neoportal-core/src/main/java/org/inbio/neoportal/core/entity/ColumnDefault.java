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
public class ColumnDefault
    implements java.io.Serializable {
    
    private BigDecimal columnDefaultId;
    private String key;
    private String value;
    
    public ColumnDefault(){
        
    }

    public ColumnDefault(BigDecimal metaId) {
        this.columnDefaultId = metaId;
    }

    public ColumnDefault(BigDecimal columnDefaultId, String key, String value) {
        this.columnDefaultId = columnDefaultId;
        this.key = key;
        this.value = value;
    }

    

    public BigDecimal getColumnDefaultId() {
        return columnDefaultId;
    }

    public void setColumnDefaultId(BigDecimal columnDefaultId) {
        this.columnDefaultId = columnDefaultId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
