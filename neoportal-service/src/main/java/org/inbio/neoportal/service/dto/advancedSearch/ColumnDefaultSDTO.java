/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.service.dto.advancedSearch;

import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class ColumnDefaultSDTO 
        extends BaseDTO
            implements Comparable {
    
    private String columnDefaultId;
    private String key;
    private String value;

    public ColumnDefaultSDTO() {
    }

    public ColumnDefaultSDTO(String columnDefaultId, String key, String value) {
        this.columnDefaultId = columnDefaultId;
        this.key = key;
        this.value = value;
    }
    
    @Override
    public int compareTo(Object o) {

        ColumnDefaultSDTO cl = (ColumnDefaultSDTO)o;
        return this.getKey().compareTo(cl.getKey());
    }

    public String getColumnDefaultId() {
        return columnDefaultId;
    }

    public void setColumnDefaultId(String id) {
        this.columnDefaultId = id;
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
