/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.entity.SearchColumn;

/**
 *
 * @author avargas
 */
public interface SearchColumnDAO
        extends GenericDAO<SearchColumn, BigDecimal> {
    
    public List<SearchColumnCDTO> getAllSearchColumns();
    
    public List<SearchColumnCDTO> getSearchColumnsByGroup(String searchGroupKey);
    
    public SearchColumnCDTO getSearchColumnByKey(String columnKey);
}
