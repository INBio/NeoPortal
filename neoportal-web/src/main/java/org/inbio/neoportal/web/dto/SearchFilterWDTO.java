/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.dto;

import java.util.HashMap;
import java.util.List;
import org.springframework.roo.addon.json.RooJson;

/**
 *
 * @author avargas
 */
@RooJson
public class SearchFilterWDTO {
    
    private String searchFilterId;
    private String filterKey;
    private String type;
    private String label;
    
    private List<HashMap<String,String>> values;

    public SearchFilterWDTO(
            String searchFilterId, 
            String filterKey, 
            String type, 
            String label,
            List<HashMap<String,String>> values) {
        this.searchFilterId = searchFilterId;
        this.filterKey = filterKey;
        this.type = type;
        this.label = label;
        this.values = values;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSearchFilterId() {
        return searchFilterId;
    }

    public void setSearchFilterId(String searchFilterId) {
        this.searchFilterId = searchFilterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<HashMap<String,String>> getValues() {
        return values;
    }

    public void setValues(List<HashMap<String,String>> values) {
        this.values = values;
    }

    
}
