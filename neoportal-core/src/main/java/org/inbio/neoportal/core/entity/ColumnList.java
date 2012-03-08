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
public class ColumnList
    implements java.io.Serializable {
    
    private BigDecimal columnListId;
    
    private String lang;
    private String key;
    private String value;
    
    public ColumnList(){
        
    }

    public ColumnList(BigDecimal metaId) {
        this.columnListId = metaId;
    }
    
    public ColumnList(BigDecimal id, String lang, String key, String value) {
        this.columnListId = id;
        this.lang = lang;
        this.key = key;
        this.value = value;
    }

    public BigDecimal getColumnListId() {
        return columnListId;
    }

    public void setColumnListId(BigDecimal columnListId) {
        this.columnListId = columnListId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
