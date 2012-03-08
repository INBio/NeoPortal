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
public class ColumnDefaultCDTO
        extends BaseDTO
            implements Comparable {

    private String columnDefaultId;
    private String key;
    private String value;

    public ColumnDefaultCDTO() {
        
    }

    /**
     * 
     * @param columnDefaultId
     * @param key
     * @param value 
     */
    public ColumnDefaultCDTO(String columnDefaultId, String key, String value) {
        this.columnDefaultId = columnDefaultId;
        this.key = key;
        this.value = value;
    }
    
    @Override
    public int compareTo(Object t) {
        ColumnDefaultCDTO oc = (ColumnDefaultCDTO) t;
        return this.key.compareTo(oc.getKey());
    }

    public String getColumnDefaultId() {
        return columnDefaultId;
    }

    public void setColumnDefaultId(String columnDefaultId) {
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
