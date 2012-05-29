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
public class SearchGroupCDTO 
        extends BaseDTO
            implements Comparable {
    
    private String searchGroupId;
    private String key;
    
    private List<SearchColumnCDTO> searchColumnList;
    private List<SearchFilterCDTO> searchFilterList;
    private List<ColumnDefaultCDTO> columnDefaultList;

    public SearchGroupCDTO() {
    }

    public SearchGroupCDTO(String searchGroupId, String key, List<SearchColumnCDTO> searchColumnList, List<SearchFilterCDTO> searchFilterList, List<ColumnDefaultCDTO> columnDefaultList) {
        this.searchGroupId = searchGroupId;
        this.key = key;
        this.searchColumnList = searchColumnList;
        this.searchFilterList = searchFilterList;
        this.columnDefaultList = columnDefaultList;
    }

    @Override
    public int compareTo(Object t){
        SearchGroupCDTO sgDTO = (SearchGroupCDTO)t;
        return this.key.compareTo(sgDTO.getKey());
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

    public List<ColumnDefaultCDTO> getColumnDefaultList() {
        return columnDefaultList;
    }

    public void setColumnDefaultList(List<ColumnDefaultCDTO> columnDefaultList) {
        this.columnDefaultList = columnDefaultList;
    }

    public List<SearchColumnCDTO> getSearchColumnList() {
        return searchColumnList;
    }

    public void setSearchColumnList(List<SearchColumnCDTO> searchColumnList) {
        this.searchColumnList = searchColumnList;
    }

    public List<SearchFilterCDTO> getSearchFilterList() {
        return searchFilterList;
    }

    public void setSearchFilterList(List<SearchFilterCDTO> searchFilterList) {
        this.searchFilterList = searchFilterList;
    }
    
}
