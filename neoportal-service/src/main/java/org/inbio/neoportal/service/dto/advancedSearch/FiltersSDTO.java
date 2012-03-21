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
import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class FiltersSDTO 
        extends BaseDTO{
    
    private List<ColumnListSDTO> columnList;
    private List<ColumnDefaultSDTO> columnDefault;
    private List<FilterListSDTO> filterList;

    public FiltersSDTO() {
    }

    public FiltersSDTO(List<ColumnListSDTO> columnList, List<ColumnDefaultSDTO> columnDefault, List<FilterListSDTO> filterList) {
        this.columnList = columnList;
        this.columnDefault = columnDefault;
        this.filterList = filterList;
    }

    public List<ColumnDefaultSDTO> getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(List<ColumnDefaultSDTO> columnDefault) {
        this.columnDefault = columnDefault;
    }

    public List<ColumnListSDTO> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnListSDTO> columnList) {
        this.columnList = columnList;
    }

    public List<FilterListSDTO> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<FilterListSDTO> filterList) {
        this.filterList = filterList;
    }
    
    
}
