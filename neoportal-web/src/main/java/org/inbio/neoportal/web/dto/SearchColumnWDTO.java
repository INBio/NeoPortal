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
public class SearchColumnWDTO {
    
    private String searchColumnId;
    private String columnKey;
    private String label;

    public SearchColumnWDTO(String searchColumnId, String columnKey, String label) {
        this.searchColumnId = searchColumnId;
        this.columnKey = columnKey;
        this.label = label;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSearchColumnId() {
        return searchColumnId;
    }

    public void setSearchColumnId(String searchColumnId) {
        this.searchColumnId = searchColumnId;
    }

}
