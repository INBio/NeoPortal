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
package org.inbio.neoportal.service.dto.advancedSearch;

import java.util.List;

import org.inbio.neoportal.core.common.dto.BaseDTO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;

/**
 *
 * @author avargas
 */
public class FiltersSDTO 
        extends BaseDTO{
    
    private List<SearchGroupCDTO> searchGroupList;
    private List<SearchColumnCDTO> searchColumnList;
    private List<ColumnDefaultCDTO> searchColumnDefaultList;
    private List<SearchFilterCDTO> searchFilterList;

    public FiltersSDTO() {
    }

    public FiltersSDTO(List<SearchGroupCDTO> searchGroupList, List<SearchColumnCDTO> searchColumnList, List<ColumnDefaultCDTO> searchColumnDefaultList, List<SearchFilterCDTO> searchFilterList) {
        this.searchGroupList = searchGroupList;
        this.searchColumnList = searchColumnList;
        this.searchColumnDefaultList = searchColumnDefaultList;
        this.searchFilterList = searchFilterList;
    }

    public List<ColumnDefaultCDTO> getSearchColumnDefaultList() {
        return searchColumnDefaultList;
    }

    public void setSearchColumnDefaultList(List<ColumnDefaultCDTO> searchColumnDefaultList) {
        this.searchColumnDefaultList = searchColumnDefaultList;
    }

    public List<SearchColumnCDTO> getSearchColumnList() {
        return searchColumnList;
    }

    public void setSearchColumnList(List<SearchColumnCDTO> searchColumnList) {
        this.searchColumnList = searchColumnList;
    }

    public List<SearchFilterCDTO> getSearchFilterList() {
        return searchFilterList;
    }

    public void setSearchFilterList(List<SearchFilterCDTO> searchFilterList) {
        this.searchFilterList = searchFilterList;
    }

    public List<SearchGroupCDTO> getSearchGroupList() {
        return searchGroupList;
    }

    public void setSearchGroupList(List<SearchGroupCDTO> searchGroupList) {
        this.searchGroupList = searchGroupList;
    }

    
}
