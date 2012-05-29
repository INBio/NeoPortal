/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class SearchColumnCDTO
        extends BaseDTO
            implements Comparable {

    private String searchColumnId;
    private String searchGroupId;
    private String columnKey;

    public SearchColumnCDTO() {
        
    }

    public SearchColumnCDTO(
            String searchColumnId, 
            String searchGroupId, 
            String columnKey) {
        this.searchColumnId = searchColumnId;
        this.searchGroupId = searchGroupId;
        this.columnKey = columnKey;
    }
    
    @Override
    public int compareTo(Object t) {
        SearchColumnCDTO oc = (SearchColumnCDTO) t;
        return this.columnKey.compareTo(oc.getColumnKey());
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getSearchColumnId() {
        return searchColumnId;
    }

    public void setSearchColumnId(String searchColumnId) {
        this.searchColumnId = searchColumnId;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

    
}
