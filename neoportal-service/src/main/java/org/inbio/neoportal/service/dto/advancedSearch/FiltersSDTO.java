/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
