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
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionLiteCDTO;
import org.inbio.neoportal.core.entity.TaxonDescription;

/**
 * Grant Access to the TaxonDescription Entity
 * @author asanabria
 * @author avargas
 */
public interface TaxonDescriptionDAO 
    extends GenericBaseDAO<TaxonDescription, BigDecimal> {
    
        /**
     * Return the total amount of items of a query.
     * @param fields
     * @param searchText
     * @return
     */
    public Long searchCount(final String searchText);

    /**
     * Search the <code>searchText</code> in the lucene index in the <code>fields</code>
     * Results are paginated.
     * @param fields
     * @param searchText
     * @param offset: first result of the list.
     * @param quantity: lengt of the result list.
     * @return
     */
    public List<TaxonDescriptionLiteCDTO> search(
        final String searchText,
        final int offset, 
        final int quantity) ;

    
   public List<TaxonDescriptionFullCDTO> findAllByScientificName(
        final String scientificName,
        final String provider) ;
}
