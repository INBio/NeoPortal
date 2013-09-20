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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dto.transformers.OccurrenceDWCTransformer;
import org.inbio.neoportal.core.dto.transformers.OccurrenceTransformer;
import org.inbio.neoportal.core.entity.OccurrenceDwc;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import org.inbio.neoportal.core.NeoportalCoreConstants;

/**
 *
 * @author asanabria
 */
@Repository
public class OccurrenceDAOImpl 
    extends GenericBaseDAOImpl<OccurrenceDwc, BigDecimal>
        implements OccurrenceDAO{ 
    
       /**
    * Return a generic search.
    * @param entityClass
    * @param fields
    * @param searchText
    * @param offset
    * @param quantity
    * @return 
    */
    @Override
    public List search(
        final String searchText,
        final int offset, 
        final int quantity){
        
          String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));
        
        return super.search(OccurrenceDwc.class,
                            new OccurrenceTransformer(),
                            fieldList.toArray(new String[fieldList.size()]),
                            searchText,
                            offset,
                            quantity);
    }

    /**
     * Return a search count
     * @param entityClass
     * @param fields
     * @param searchText
     * @return 
     */
    @Override
    public Long searchCount( final String searchText){
        
          String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));
        
        return super.searchCount(OccurrenceDwc.class, 
                                 new OccurrenceTransformer(),
                                 fieldList.toArray(new String[fieldList.size()]),
                                 searchText);
    }
    
    /**
    * Return a generic search.
    * @param entityClass
    * @param fields
    * @param searchText
    * @param offset
    * @param quantity
    * @return 
    */
    @Override
    public List advancedSearchPaginated(
        final String searchText,
        final int offset, 
        final int quantity){
        
        return super.search(OccurrenceDwc.class,
                            new OccurrenceDWCTransformer(),
                            searchText,
                            offset,
                            quantity);
    }
    
    @Override
    public OccurrenceDwc findByLocationId(
            final String locationId){
        HibernateTemplate template = getHibernateTemplate();
		return (OccurrenceDwc) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {
                Query query = session.createQuery(
						"from OccurrenceDwc as oc"
						+ " where oc.locationId = :locationId");
				query.setParameter("locationId", locationId);
				return query.list().get(0);
			}
		});
    }

	/* (non-Javadoc)
	 * @see org.inbio.neoportal.core.dao.OccurrenceDAO#getSexValues()
	 */
	@Override
	public List<String> getSexValues() {
		HibernateTemplate template = getHibernateTemplate();
		return (List<String>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {
                Query query = session.createQuery(
						"select distinct oc.sex from OccurrenceDwc as oc");
                query.setCacheable(true);
				return query.list();
			}
		});
		
	}
	
	/**
	 * This function make a lucene query
	 */
	@Override
	public OccurrenceDwc findByCatalogNumber(final String catalogNumber){
		Session session = getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		// create Lucene query using the query DSL
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(OccurrenceDwc.class).get();
		org.apache.lucene.search.Query query = qb
				.keyword()
				.onField("catalogNumber")
				.matching(catalogNumber)
				.createQuery();
		
		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.Query hQuery = 
				fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);
		
		return (OccurrenceDwc)hQuery.uniqueResult();
	}
	
	@Override
	public long searchPhraseCount (String field, String searchText) {
		Session session = getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		// create Lucene query using the query DSL
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(OccurrenceDwc.class).get();
		org.apache.lucene.search.Query query = qb
				.phrase()
				.onField(field)
				.sentence(searchText)
				.createQuery();
		
		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.search.FullTextQuery hQuery = 
				fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);
		
		return hQuery.getResultSize();
	}
	
	 public List searchPhrase(
	    		String field,
	    		String searchText,
	    		ResultTransformer resultTransformer,
	    		int offset,
	    		int quantity) {
		 
		 Session session = getSession();
		 FullTextSession fullTextSession = Search.getFullTextSession(session);
		 
		 QueryBuilder qb = fullTextSession.getSearchFactory()
				 .buildQueryBuilder().forEntity(OccurrenceDwc.class).get();
		 org.apache.lucene.search.Query query = qb
				 .phrase()
				 .onField(field)
				 .sentence(searchText)
				 .createQuery();
		 
		 FullTextQuery fQuery =
				 fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);
		 
		 fQuery.setResultTransformer(resultTransformer);
		 fQuery.setFirstResult(offset);
		 fQuery.setMaxResults(quantity);
		 
		 return fQuery.list();
	 }

	@Override
	public List searchLucene(String luceneQuery, String sortField,
			ResultTransformer resultTransformer, int offset, int quantity) {
		Session session = getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		org.apache.lucene.search.Query query = null;
		
		QueryParser parser = new QueryParser(
				NeoportalCoreConstants.LuceneVersion,
				"",
				new StandardAnalyzer(NeoportalCoreConstants.LuceneVersion));
		
		try {
			query = parser.parse(luceneQuery);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FullTextQuery fQuery = fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);
		
		Sort sort = new Sort(new SortField(sortField, SortField.STRING));
		fQuery.setSort(sort);
		
		fQuery.setResultTransformer(resultTransformer);
		fQuery.setFirstResult(offset);
		fQuery.setMaxResults(quantity);
		
		return fQuery.list();
	}

	@Override
	public long searchLuceneCount(String luceneQuery) {
		Session session = getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		org.apache.lucene.search.Query query = null;
		
		QueryParser parser = new QueryParser(
				NeoportalCoreConstants.LuceneVersion,
				"",
				new StandardAnalyzer(NeoportalCoreConstants.LuceneVersion));
		
		try {
			query = parser.parse(luceneQuery);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FullTextQuery fQuery = fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);

		return fQuery.getResultSize();
	}
	 
	 
}
