/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnListCDTO;
import org.inbio.neoportal.core.entity.ColumnList;

/**
 *
 * @author avargas
 */
public interface ColumnListDAO
        extends GenericBaseDAO<ColumnList, BigDecimal> {
    
    public List<ColumnListCDTO> getAllColumns();
    
    public ColumnListCDTO getColumnListByKey(String keyName);
}
