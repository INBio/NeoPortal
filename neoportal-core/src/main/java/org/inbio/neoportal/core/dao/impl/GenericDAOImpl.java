package org.inbio.neoportal.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class 
	GenericDAOImpl<E, ID extends Serializable>
		implements GenericDAO<E, ID> {

	private Class<E> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
	

	public GenericDAOImpl() {
		this.entityClass = (Class<E>)
				((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		
	
	}

	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#findById(java.io.Serializable)
	 */
	@Override
	public E findById(ID id) {
		return (E) this.sessionFactory.getCurrentSession()
				.get(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#create(java.lang.Object)
	 */
	@Override
	public void create(E entity) {
		this.sessionFactory.getCurrentSession()
			.save(entity);
	}

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#update(java.lang.Object)
	 */
	@Override
	public void update(E entity) {
		this.sessionFactory.getCurrentSession()
			.update(entity);
	}

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(E entity) {
		this.sessionFactory.getCurrentSession()
			.delete(entity);
	}

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#findAll()
	 */
	@Override
	public List<E> findAll() {
		return this.sessionFactory.getCurrentSession()
			.createCriteria(entityClass)
			.list();
	}

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#flush()
	 */
	@Override
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}


	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#search(org.hibernate.transform.ResultTransformer, java.lang.String[], java.lang.String, int, int)
	 */
	@Override
	public List search(ResultTransformer resultTransformer, String[] fields,
			String searchText, int offset, int quantity) {

		Session session = this.sessionFactory.getCurrentSession();
	
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


	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#searchCount(org.hibernate.transform.ResultTransformer, java.lang.String[], java.lang.String)
	 */
	@Override
	public Long searchCount(
			String[] fields,
			String searchText) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
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

        return new Long(hsQuery.getResultSize());
	}


	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#search(org.hibernate.transform.ResultTransformer, java.lang.String, int, int)
	 */
	@Override
	public List search(ResultTransformer resultTransformer, String searchText,
			int offset, int quantity) {
        
		Session session = this.sessionFactory.getCurrentSession();
		
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


	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.GenericDAO#searchCount(org.hibernate.transform.ResultTransformer, java.lang.String)
	 */
	@Override
	public Long searchCount(ResultTransformer resultTransformer,
			String searchText) {
		
		Session session = this.sessionFactory.getCurrentSession();

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

        return new Long(hsQuery.getResultSize());
	}
	
	/**
	 * 
	 * @param entityClass
	 * @param maxResults
	 * @param firstResult
	 * @return
	 */
	public List<E> scrollAll(
            final Class<E> entityClass,
            final int maxResults,
            final int firstResult){
    	Session session = this.sessionFactory.getCurrentSession();
	
        org.hibernate.Query query = session.createQuery(
				"from " + entityClass.getSimpleName());
		query.setMaxResults(maxResults);
        query.setFirstResult(firstResult);
        return query.list();

	}
}
