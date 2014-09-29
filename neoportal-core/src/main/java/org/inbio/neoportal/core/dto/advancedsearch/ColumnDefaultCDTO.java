/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class ColumnDefaultCDTO
        extends BaseDTO
            implements Comparable {

    private String columnDefaultId;
    private String searchGroupId;
    private String columnDefaultKey;

    public ColumnDefaultCDTO() {
        
    }

    /**
     * 
     * @param columnDefaultId
     * @param searchGroupId
     * @param columnDefaultKey 
     */
    public ColumnDefaultCDTO(String columnDefaultId, String searchGroupId, String columnDefaultKey) {
        this.columnDefaultId = columnDefaultId;
        this.searchGroupId = searchGroupId;
        this.columnDefaultKey = columnDefaultKey;
    }
    
    @Override
    public int compareTo(Object t) {
        ColumnDefaultCDTO oc = (ColumnDefaultCDTO) t;
        return this.columnDefaultKey.compareTo(oc.getColumnDefaultKey());
    }

    public String getColumnDefaultId() {
        return columnDefaultId;
    }

    public void setColumnDefaultId(String columnDefaultId) {
        this.columnDefaultId = columnDefaultId;
    }

    public String getColumnDefaultKey() {
        return columnDefaultKey;
    }

    public void setColumnDefaultKey(String columnDefaultKey) {
        this.columnDefaultKey = columnDefaultKey;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

    
}
