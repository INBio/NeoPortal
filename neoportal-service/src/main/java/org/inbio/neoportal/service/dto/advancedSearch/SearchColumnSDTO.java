/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
package org.inbio.neoportal.service.dto.advancedSearch;

import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class SearchColumnSDTO 
        extends BaseDTO
            implements Comparable {
    
    private String columnListId;
    private String lang;
    private String key;
    private String value;

    public SearchColumnSDTO() {
    }

    public SearchColumnSDTO(String id, String lang, String key, String value) {
        this.columnListId = id;
        this.lang = lang;
        this.key = key;
        this.value = value;
    }
    
    @Override
    public int compareTo(Object o) {

        SearchColumnSDTO cl = (SearchColumnSDTO)o;
        return this.getKey().compareTo(cl.getKey());
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getColumnListId() {
        return columnListId;
    }

    public void setColumnListId(String id) {
        this.columnListId = id;
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
