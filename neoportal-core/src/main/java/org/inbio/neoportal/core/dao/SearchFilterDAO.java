/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.entity.SearchFilter;

/**
 *
 * @author avargas
 */
public interface SearchFilterDAO
        extends GenericBaseDAO<SearchFilter, BigDecimal> {
    
    public List<SearchFilterCDTO> getAllFilters();
    
    public SearchFilterCDTO getSearchFilterByKey(String keyName);
}
