package org.inbio.neoportal.dao;

import org.inbio.neoportal.entity.DarwinCore;


/**
 * @author jgutierrez
 *
 */
public interface IndexDAO extends GenericBaseDAO<DarwinCore, Integer> {
	
    public void createIndex();
}
