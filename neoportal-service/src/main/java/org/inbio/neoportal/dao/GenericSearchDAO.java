/**
 * 
 */
package org.inbio.neoportal.dao;

import java.util.List;

/**
 * @author jgutierrez
 *
 */
public interface GenericSearchDAO {

	public Integer getTotalResults(String HSQL) throws IllegalArgumentException;
	
	public List<Integer> getResults(String HSQL, int first, int last) throws IllegalArgumentException;
}
