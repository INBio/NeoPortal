/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author avargas
 *
 */
public class SearchFilterValue implements Serializable {

	private BigDecimal searchFilterValueId;
	private String value;
	private SearchFilter searchFilter;
	
	public SearchFilterValue() {
		
	}

	/**
	 * @return the searchFilterValueId
	 */
	public BigDecimal getSearchFilterValueId() {
		return searchFilterValueId;
	}

	/**
	 * @param searchFilterValueId the searchFilterValueId to set
	 */
	public void setSearchFilterValueId(BigDecimal searchFilterValueId) {
		this.searchFilterValueId = searchFilterValueId;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the searchFilter
	 */
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	/**
	 * @param searchFilter the searchFilter to set
	 */
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}
	
	
}
