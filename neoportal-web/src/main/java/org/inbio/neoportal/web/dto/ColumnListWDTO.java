/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.dto;

import org.springframework.roo.addon.json.RooJson;

/**
 *
 * @author avargas
 */
@RooJson
public class ColumnListWDTO {
    
    private String columnListId;
    private String lang;
    private String key;
    private String value;

    public ColumnListWDTO(String id, String lang, String key, String value) {
        this.columnListId = id;
        this.lang = lang;
        this.key = key;
        this.value = value;
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

    public void setColumnListId(String id) {
        this.columnListId = id;
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
