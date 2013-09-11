/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

/**
 * @author avargas
 *
 */
public interface GenericDAO<E, ID extends Serializable> {
	
	public E findById(ID id);
	
	public void create (E entity);
	
	public void update (E entity);
	
	public void delete (E entity);
	
	public List<E> findAll ();
	
	public void flush ();
	
	/**
	    * Return a generic search.
	    * @param entityClass
	    * @param fields
	    * @param searchText
	    * @param offset
	    * @param quantity
	    * @return 
	    */
	    public List search(
	        final ResultTransformer resultTransformer,
	        final String[] fields, 
	        final String searchText,
	        String sortField,
	        final int offset, 
	        final int quantity) ;

	    /**
	     * Return a search count
	     * @param fields
	     * @param searchText
	     * @param entityClass
	     * @return 
	     */
	    public Long searchCount(
	        final String[] fields,
	        final String searchText);

	    /**
	     * Return a search using QueryParser class.
	     * Highly use in advanced search procedures.
	     * @param entityClass
	     * @param resultTransformer
	     * @param searchText
	     * @param offset
	     * @param quantity
	     * @return 
	     */
	    public List search(
	        final ResultTransformer resultTransformer,
	        final String searchText,
	        final int offset, 
	        final int quantity);
	    
	    /**
	     * Return search count for not MultiField search.
	     * Use with search that use QueryParser class.
	     * @param entityClass
	     * @param resultTransformer
	     * @param searchText
	     * @return 
	     */
	    public Long searchCount(
	        final ResultTransformer resultTransformer,
	        final String searchText);
}
