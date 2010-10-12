package org.inbio.neoportal.dao;

import java.util.List;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.entity.DarwinCore;
import org.springframework.stereotype.Repository;


/**
 * @author jgutierrez
 *
 */
public interface DwCDAO extends GenericBaseDAO<DarwinCore, Integer> {
	

    public Integer searchCount(final String[] fields, final String searchText);

    public List<OcurrenceLiteDTO> search(final String[] fields, final String searchText, final int offset, final int quantity) ;

}
