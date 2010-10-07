package org.inbio.neoportal.dao;

import java.util.List;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTO;
import org.inbio.neoportal.entity.DarwinCore;


/**
 * @author jgutierrez
 *
 */
public interface DwCDAO extends GenericBaseDAO<DarwinCore, Integer> {
	

    public Integer searchSize(final String[] fields, final String searchText);

    public List<MinimalOccurrenceInfoDTO> search(final String[] fields, final String searchText, final int offset, final int quantity) ;

}
