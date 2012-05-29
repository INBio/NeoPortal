/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.entity.SearchGroup;

/**
 *
 * @author avargas
 */
public interface SearchGroupDAO
        extends GenericBaseDAO<SearchGroup, BigDecimal> {
    
    public List<SearchGroupCDTO> getAllSearchGroups();
    
    public SearchGroupCDTO getSearchGroupByKey(String searchGroupKey);
}
