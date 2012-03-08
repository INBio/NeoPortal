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
public class FilterListCDTO
        extends BaseDTO
            implements Comparable {

    private String filterListId;
    private String lang;
    private String key;
    private String value;

    public FilterListCDTO() {
        
    }

    public FilterListCDTO(String id, String lang, String key, String value) {
        this.filterListId = id;
        this.lang = lang;
        this.key = key;
        this.value = value;
    }
    
    
    @Override
    public int compareTo(Object t) {
        FilterListCDTO oc = (FilterListCDTO) t;
        return this.key.compareTo(oc.getKey());
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFilterListId() {
        return filterListId;
    }

    public void setFilterListId(String metaId) {
        this.filterListId = metaId;
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
