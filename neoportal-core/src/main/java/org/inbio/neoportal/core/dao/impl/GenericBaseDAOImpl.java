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
package org.inbio.neoportal.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dao.GenericBaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author jgutierrez
 * @author asanabria 
 */
@Repository
public class GenericBaseDAOImpl<E ,I> 
    extends HibernateDaoSupport 
        implements GenericBaseDAO<E, I> {

    @Autowired
    private void initFactory(SessionFactory sessionFactory){
        this.setSessionFactory(sessionFactory);
    }

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#create(java.lang.Object)
	 */
	public void create(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.persist(entity);
		template.flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#update(java.lang.Object)
	 */
	public void update(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.update(entity);
		template.flush();
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#delete(java.lang.Object)
	 */
	public void delete(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(entity);
		template.flush();		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public E findById(Class<E> entityClass, I entityId) {
		HibernateTemplate template = getHibernateTemplate();
		return (E) template.get(entityClass, (Serializable) entityId);
	}	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entityClass) {
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(entityClass);
	}
    

    @Override
    public List search(
        final Class<E> entityClass,
        final ResultTransformer resultTransformer,
        final String[] fields, 
        final String searchText,
        final int offset, 
        final int quantity) {

        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                // create native Lucene query
                MultiFieldQueryParser parser = 
                        new MultiFieldQueryParser(Version.LUCENE_33, 
                            fields, new StandardAnalyzer(Version.LUCENE_33));

                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(entityClass.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.Query hsQuery =
                        (org.hibernate.Query) fullTextSession.createFullTextQuery(query, entityClass);

                // Configure the result list
                hsQuery.setResultTransformer(resultTransformer);
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                
                
                return hsQuery.list();
            }
        });
    }

    @Override
    public Long searchCount(
        final Class<E> entityClass, 
        final ResultTransformer resultTransformer,
        final String[] fields,
        final String searchText) {

        HibernateTemplate template = getHibernateTemplate();

        return (Long) template.execute(new HibernateCallback() {
            
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);

                // create native Lucene query
                MultiFieldQueryParser parser =
                        new MultiFieldQueryParser(Version.LUCENE_33, 
                            fields, new StandardAnalyzer(Version.LUCENE_33));

                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(entityClass.getName())
                        .log(Level.SEVERE, null, ex);
                    
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                FullTextQuery hsQuery =
                    fullTextSession.createFullTextQuery(query, entityClass);

                //hsQuery.enableFullTextFilter(searchText)
                
                return new Long(hsQuery.getResultSize());
            }
        });
    }

    @Override
    public List search(
            final Class<E> entityClass,
            final ResultTransformer resultTransformer,
            final String searchText,
            final int offset,
            final int quantity) {
        
        
        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                // create native Lucene query
                QueryParser parser = 
                        new QueryParser(Version.LUCENE_33, "",
                            new StandardAnalyzer(Version.LUCENE_33));

                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(entityClass.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.Query hsQuery =
                        (org.hibernate.Query) fullTextSession.createFullTextQuery(query, entityClass);

                // Configure the result list
                hsQuery.setResultTransformer(resultTransformer);
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                
                return hsQuery.list();
            }
        });
    }

    @Override
    public Long searchCount(
            final Class<E> entityClass,
            final ResultTransformer resultTransformer,
            final String searchText) {
        
        HibernateTemplate template = getHibernateTemplate();

        return (Long) template.execute(new HibernateCallback() {
            
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);

                // create native Lucene query
                QueryParser parser =
                        new QueryParser(Version.LUCENE_33, 
                            "", new StandardAnalyzer(Version.LUCENE_33));

                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(entityClass.getName())
                        .log(Level.SEVERE, null, ex);
                    
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                FullTextQuery hsQuery =
                    fullTextSession.createFullTextQuery(query, entityClass);

                //hsQuery.enableFullTextFilter(searchText)
                
                return new Long(hsQuery.getResultSize());
            }
        });
    }
      
    /**
     * Scrollable iterator for all elements of passing entity
     * Use offline pagination
     * @param entityClass   The required entity
     * @param maxResults    Number of items per page
     * @param firstResult   First result, the offset for pagination
     * @return 
     */
    public List<E> scrollAll(
            final Class<E> entityClass,
            final int maxResults,
            final int firstResult){
        HibernateTemplate template = getHibernateTemplate();
        
        return (List<E>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {
                org.hibernate.Query query = session.createQuery(
						"from " + entityClass.getSimpleName()
                        + " order by ID");
				query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
                return query.list();
			}
		});
        
    }
    
}
