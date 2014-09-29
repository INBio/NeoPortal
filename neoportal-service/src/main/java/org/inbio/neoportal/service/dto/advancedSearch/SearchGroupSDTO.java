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
public class SearchGroupSDTO 
        extends BaseDTO
            implements Comparable {
    
    private String searchGroupId;
    private String key;
    
    public SearchGroupSDTO() {
    }

    public SearchGroupSDTO(String searchGroupId, String key) {
        this.searchGroupId = searchGroupId;
        this.key = key;
    }
    
    @Override
    public int compareTo(Object o) {

        SearchGroupSDTO cl = (SearchGroupSDTO)o;
        return this.getKey().compareTo(cl.getKey());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

    
}
