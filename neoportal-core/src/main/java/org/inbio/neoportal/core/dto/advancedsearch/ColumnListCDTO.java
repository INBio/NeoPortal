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
public class ColumnListCDTO
        extends BaseDTO
            implements Comparable {

    private String columnListId;
    private String lang;
    private String key;
    private String value;

    public ColumnListCDTO() {
        
    }

    public ColumnListCDTO(String id, String lang, String key, String value) {
        this.columnListId = id;
        this.lang = lang;
        this.key = key;
        this.value = value;
    }
    
    
    @Override
    public int compareTo(Object t) {
        ColumnListCDTO oc = (ColumnListCDTO) t;
        return this.key.compareTo(oc.getKey());
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

    public void setColumnListId(String metaId) {
        this.columnListId = metaId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String metaKey) {
        this.key = metaKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
