/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import java.math.BigDecimal;

import org.inbio.neoportal.core.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class SearchColumnCDTO
        extends BaseDTO
            implements Comparable<SearchColumnCDTO> {

    private String searchColumnId;
    private String searchGroupId;
    private String columnKey;
    private BigDecimal sort;

    public SearchColumnCDTO() {
        
    }

    public SearchColumnCDTO(
            String searchColumnId, 
            String searchGroupId, 
            String columnKey,
            BigDecimal sort) {
        this.searchColumnId = searchColumnId;
        this.searchGroupId = searchGroupId;
        this.columnKey = columnKey;
        this.sort = sort;
    }
    
    @Override
    public int compareTo(SearchColumnCDTO t) {
        return this.sort.compareTo(t.getSort());
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getSearchColumnId() {
        return searchColumnId;
    }

    public void setSearchColumnId(String searchColumnId) {
        this.searchColumnId = searchColumnId;
    }

    public String getSearchGroupId() {
        return searchGroupId;
    }

    public void setSearchGroupId(String searchGroupId) {
        this.searchGroupId = searchGroupId;
    }

	/**
	 * @return the sort
	 */
	public BigDecimal getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}
    
}
