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

import org.apache.lucene.search.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.entity.Taxon;

/**
 * Grant access to the Taxon entity 
 * @author asanabria
 */
public interface TaxonDAO 
    extends GenericBaseDAO<Taxon, BigDecimal> {
    
    
    /**
     * Return the total amount of items of a query.
     * @param fields
     * @param searchText
     * @return
     */
    public Long searchCount(final String searchText);

    /**
     * Search the <code>searchText</code> in the lucene index
     * Results are paginated.
     * @param searchText
     * @param offset: first result of the list.
     * @param quantity: length of the result list.
     * @return
     */
    public List<TaxonLiteCDTO> search(
        final String searchText,
        final int offset, 
        final int quantity) ;

    
    public List<TaxonLiteCDTO> search(
            String luceneQuery,
            String sortField,
            int offset, 
            int quantity) ;
    
    /**
     * Return the total amount of items of a query.
     * @param fields
     * @param searchText
     * @return
     */
    public Long searchInCount(
    		final List idList);

    /**
     * Search the <code>searchText</code> in the lucene index
     * Results are paginated.
     * @param searchText
     * @param offset: first result of the list.
     * @param quantity: lengt of the result list.
     * @return
     */
    public List<TaxonLiteCDTO> searchIn(
        final List idList,
        final int offset, 
        final int quantity) ;

    
//    public List<TaxonDescriptionFullCDTO> searchBoost(
//        final String searchText,
//        final int offset, 
//        final int quantity) ;
    
    /**
     * 
     */
    public List<Taxon> findAllByScientificName(
       final String scientificName);
    
    /**
     * 
     * @param scientificName
     * @return 
     */
    public List<TaxonLiteCDTO> findCDTOByScientificName(
       final String scientificName);
    
    /**
     * Suggest taxon names starting with <code>searchTerm</code>
     * @param searchTerm
     * @return a list of taxon names
     */
    public List<String> taxonSuggestions(final String searchTerm);
    
    /**
     * Find the taxon with the <code>defaultName</code>
     * @param defaultName
     * @return Taxon entity
     */
    public Taxon findByDefaultName(final String defaultName);
    
    public List search(
    		String searchText,
    		String[] fields,
    		int offset,
    		int quantity,
    		ResultTransformer resultTransformer
    		);
    
}
