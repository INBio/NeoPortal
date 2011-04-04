/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.occurrence.TaxonLiteDTO;
import org.inbio.neoportal.core.entity.Taxon;

/**
 *
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
    public Integer searchCount(final String[] fields, final String searchText);

    /**
     * Search the <code>searchText</code> in the lucene index in the <code>fields</code>
     * Results are paginated.
     * @param fields
     * @param searchText
     * @param offset: first result of the list.
     * @param quantity: lengt of the result list.
     * @return
     */
    public List<TaxonLiteDTO> search(final String[] fields,
        final String searchText, final int offset, final int quantity) ;

}
