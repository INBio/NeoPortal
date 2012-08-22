/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import java.util.List;
import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class SearchFilterCDTO
        extends BaseDTO
            implements Comparable {

    private String searchFilterId;
    private String searchGroupId;
    private String filterKey;
    private String type;
    
    private List<String> values;

    public SearchFilterCDTO() {
        
    }

    public SearchFilterCDTO(String searchFilterId, String searchGroupId, String filterKey, String type, List values) {
        this.searchFilterId = searchFilterId;
        this.searchGroupId = searchGroupId;
        this.filterKey = filterKey;
        this.type = type;
        this.values = values;
    }
    
    
    @Override
    public int compareTo(Object t) {
        SearchFilterCDTO oc = (SearchFilterCDTO) t;
        return this.filterKey.compareTo(oc.getFilterKey());
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getSearchFilterId() {
        return searchFilterId;
    }

    public void setSearchFilterId(String searchFilterId) {
        this.searchFilterId = searchFilterId;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
    

}
