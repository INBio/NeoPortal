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
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.core.entity.OccurrenceDwc;

/**
 *
 * @author asanabria
 */
public interface OccurrenceDAO 
    extends GenericDAO<OccurrenceDwc, BigDecimal>{
    
           /**
    * Return a generic search.
    * @param entityClass
    * @param fields
    * @param searchText
    * @param offset
    * @param quantity
    * @return 
    */
    public List search(
        final String searchText,
        final int offset, 
        final int quantity);

    /**
     * Return a search count
     * @param entityClass
     * @param fields
     * @param searchText
     * @return 
     */
    public Long searchCount(final String searchText);
    
    /**
     * 
     * @param fields
     * @param searchText
     * @param offset
     * @param quantity
     * @return 
     */
    public List advancedSearchPaginated(
            final String searchText,
            final int offset,
            final int quantity);

    /**
     * 
     * @param locationId 
     */
    public OccurrenceDwc findByLocationId(
            final String locationId);
    
    /**
     * 
     * @return string list the list of distinct sex values, useful for advancedSearch
     */
    public List<String> getSexValues();
    
    /**
     * Find a occurrence by the catalog number.
     * @param catalogNumber
     * @return
     */
    public OccurrenceDwc findByCatalogNumber(String catalogNumber);
    
    /**
     * Count occurrences that match searchText on particular fields
     * @param fields
     * @param searchText
     * @return
     */
    public long searchPhraseCount(String field, String searchText);
    
    /**
     * 
     * @param field
     * @param searchText
     * @param resultTransformer
     * @return
     */
    public List searchPhrase(
    		String field,
    		String searchText,
    		ResultTransformer resultTransformer,
    		int offset,
    		int quantity);
    
    /**
     * Search by parsing the <code>luceneQuery</code>
     * @param luceneQuery
     * @param sortField
     * @param resultTransformer
     * @param offset
     * @param quantity
     * @return
     */
    public List searchLucene(
    		String luceneQuery,
    		String sortField,
    		ResultTransformer resultTransformer,
    		int offset,
    		int quantity);
    
    /**
     * Count the total occurrences for <code>searchLucene</code> method
     * @param luceneQuery
     * @return
     */
    public long searchLuceneCount(
    		String luceneQuery);
}
