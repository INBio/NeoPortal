/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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

import java.util.List;
import org.hibernate.transform.ResultTransformer;

/**
 * @author jgutierrez
 *
 */
@Deprecated
public interface GenericBaseDAO<E ,I> {

 /**
    *
    * @param entity
    */
   public void create(E entity);


   /**
    *
    * @param entity
    */
   public void delete(E entity);

   /**
    *
    * @param entity
    */
   public void update(E entity);

   /**
    *
    * @param entityClass
    * @param entityId
    * @return
    */
   public E findById(Class<E> entityClass, I entityId);


   /**
    *
    * @param entityClass
    * @return
    */
   public List<E> findAll(Class<E> entityClass);


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
        final Class<E> entityClass,
        final ResultTransformer resultTransformer,
        final String[] fields, 
        final String searchText,
        final int offset, 
        final int quantity) ;

    /**
     * Return a search count
     * @param entityClass
     * @param fields
     * @param searchText
     * @return 
     */
    public Long searchCount(
        final Class<E> entityClass, 
        final ResultTransformer resultTransformer,
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
        final Class<E> entityClass,
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
        final Class<E> entityClass, 
        final ResultTransformer resultTransformer,
        final String searchText);
}
