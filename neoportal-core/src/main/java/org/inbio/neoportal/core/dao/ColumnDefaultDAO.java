/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.entity.ColumnDefault;

/**
 *
 * @author avargas
 */
public interface ColumnDefaultDAO
        extends GenericBaseDAO<ColumnDefault, BigDecimal> {
    
    public List<ColumnDefaultCDTO> getAllColumns();
    
    public ColumnDefaultCDTO getColumnDefaultByKey(String keyName);
}
