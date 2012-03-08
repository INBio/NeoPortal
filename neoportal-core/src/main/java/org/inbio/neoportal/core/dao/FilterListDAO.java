/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.FilterListCDTO;
import org.inbio.neoportal.core.entity.FilterList;

/**
 *
 * @author avargas
 */
public interface FilterListDAO
        extends GenericBaseDAO<FilterList, BigDecimal> {
    
    public List<FilterListCDTO> getAllFilters();
    
    public FilterListCDTO getFilterListByKey(String keyName);
}
