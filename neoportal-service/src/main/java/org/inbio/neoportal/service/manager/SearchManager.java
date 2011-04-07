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
package org.inbio.neoportal.service.manager;

import java.math.BigDecimal;
import java.util.List;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;


/**
 *
 * @author asanabria
 */
public interface SearchManager {

    /**
     * Search <code>searchText</code> int the lucene index.
     * Results are paginated
     * @param searchText
     * @return
     * @throws ParseException
     */
    public List<TaxonLiteCDTO> fullPaginatedSearch
        (String searchText, int offset, int quantity)
            throws ParseException;

    /**
     * Search <code>searchText</code> in the lucene index, this method return only
     * differents species (scientificNames).
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException
     */
    public List<SpeciesLiteSDTO> speciesListPaginatedSearch
        (String searchText, int offset, int quantity)
            throws ParseException;

    /**
     * Search <code>searchText</code> in the lucene index, and count the results
     * @param searchText
     * @return
     * @throws ParseException
     */
    public Long speciesListSearchCount(String searchText)
            throws ParseException;
   
    

    /**
     * Return the amount of items returned by a Lucene search
     * @param searchText
     * @return
     * @throws ParseException
     */
    public Long fullSearchCount(String searchText)
            throws ParseException;

}
