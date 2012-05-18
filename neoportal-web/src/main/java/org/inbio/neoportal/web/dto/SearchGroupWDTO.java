/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.dto;

import java.util.List;
import org.springframework.roo.addon.json.RooJson;

/**
 *
 * @author avargas
 */
@RooJson
public class SearchGroupWDTO {
    
    private String searchGroupId;
    private String key;
    private String label;
    
    private List<SearchColumnWDTO> searchColumnList;
    private List<SearchColumnWDTO> columnDefaultList;
    private List<SearchFilterWDTO> searchFilterList;

    public SearchGroupWDTO(String searchGroupId, String key, String label, List<SearchColumnWDTO> searchColumnList, List<SearchColumnWDTO> columnDefaultList, List<SearchFilterWDTO> searchFilterList) {
        this.searchGroupId = searchGroupId;
        this.key = key;
        this.label = label;
        this.searchColumnList = searchColumnList;
        this.columnDefaultList = columnDefaultList;
        this.searchFilterList = searchFilterList;
    }

    public List<SearchColumnWDTO> getColumnDefaultList() {
        return columnDefaultList;
    }

    public void setColumnDefaultList(List<SearchColumnWDTO> columnDefaultList) {
        this.columnDefaultList = columnDefaultList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SearchColumnWDTO> getSearchColumnList() {
        return searchColumnList;
    }

    public void setSearchColumnList(List<SearchColumnWDTO> searchColumnList) {
        this.searchColumnList = searchColumnList;
    }

    public List<SearchFilterWDTO> getSearchFilterList() {
        return searchFilterList;
    }

    public void setSearchFilterList(List<SearchFilterWDTO> searchFilterList) {
        this.searchFilterList = searchFilterList;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

}
