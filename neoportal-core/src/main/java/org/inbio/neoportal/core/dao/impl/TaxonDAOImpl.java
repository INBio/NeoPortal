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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.sql.Template;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.transformers.TaxonDescriptionFullTransformer;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.dto.transformers.TaxonLiteTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementantion of TaxonDAO that grant access to the Taxon information.
 * @author asanabria
 */
@Repository
public class TaxonDAOImpl 
    extends GenericBaseDAOImpl<Taxon, BigDecimal> 
        implements TaxonDAO{
    
    
    @Override
    public List<TaxonLiteCDTO> search
        (final String searchText, final int offset, final int quantity) {
        
       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species",
                                "taxonomicalRangeId", "commonNames.name"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        return super.search(Taxon.class,
                            new TaxonLiteTransformer(), 
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, 
                            offset, 
                            quantity);
       
    }

    @Override
    public Long searchCount(final String searchText) {
        
        String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species",
                                "taxonomicalRangeId", "commonNames"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        
        return super.searchCount(Taxon.class, 
                                new TaxonLiteTransformer(), 
                                fieldList.toArray(new String[fieldList.size()]), 
                                searchText);
    }

//    @Override
//    public List<TaxonDescriptionFullCDTO> searchBoost(
//            final String searchText, 
//            final int offset, 
//            final int quantity) {
//        
//
//        String[] taxon =
//                new String[]{ "defaultName", "kingdom", "division", "class_",
//                                 "order", "family", "genus", "species",
//                                "taxonomicalRangeId" };
//
//        ArrayList<String> fieldList = new ArrayList<String>();
//
//        fieldList.addAll(Arrays.asList(taxon));
//
//        return super.search(Taxon.class,
//                            new TaxonDescriptionFullTransformer(), 
//                            fieldList.toArray(new String[fieldList.size()]), 
//                            searchText, 
//                            offset, 
//                            quantity);
//        
//    }
    
    @Override
    public List<Taxon> findAllByScientificName(
       final String scientificName) {
        
        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                String searchText = "defaultName:\"" + scientificName + "\"";
                
                // create native Lucene query
                QueryParser parser = 
                        new QueryParser(Version.LUCENE_33, 
                            searchText, new StandardAnalyzer(Version.LUCENE_33));

                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(Taxon.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.Query hsQuery =
                        (org.hibernate.Query) fullTextSession.createFullTextQuery(query, Taxon.class);
                
                
                return hsQuery.list();
            }
        });
        
    }
    
    @Override
    public List<TaxonLiteCDTO> findCDTOByScientificName(
       final String scientificName) {
        
        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                String searchText = "defaultName:\"" + scientificName + "\"";
                
                // create native Lucene query
                QueryParser parser = 
                        new QueryParser(Version.LUCENE_33, 
                            searchText, new StandardAnalyzer(Version.LUCENE_33));

                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(Taxon.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.Query hsQuery =
                        (org.hibernate.Query) fullTextSession.createFullTextQuery(query, Taxon.class);
                
                hsQuery.setResultTransformer(new TaxonLiteTransformer());
                
                return hsQuery.list();
            }
        });
        
    }

	@Override
	public Long searchInCount(final List idList) {
		
		HibernateTemplate template = getHibernateTemplate();

        return (Long) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

            	org.hibernate.Query query = session.createQuery(
            			"select count(*) from Taxon" +
            			" where id IN (:idList)");
            	
            	query.setParameterList("idList", idList);
            	
            	return (Long)query.uniqueResult();
            }
        });
	}

	@Override
	public List<TaxonLiteCDTO> searchIn(
			final List idList, 
			final int offset, 
			final int quantity) {
		HibernateTemplate template = getHibernateTemplate();

        return (List<TaxonLiteCDTO>) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

            	org.hibernate.Query query = session.createQuery(
            			"from Taxon" +
            			" where id IN (:idList)");
            	
            	query.setParameterList("idList", idList);
            	query.setFirstResult(offset);
            	query.setMaxResults(quantity);
            	query.setResultTransformer(new TaxonLiteTransformer());
            	
            	return query.list();
            }
        });
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> taxonSuggestions(final String searchTerm){
		
		HibernateTemplate template = getHibernateTemplate();
		
		return (List<String>) template.execute(new HibernateCallback<Object>() {
			
			@Override
			public Object doInHibernate(Session session){
                FullTextSession fullTextSession = Search.getFullTextSession(session);
                BooleanQuery booleanQuery = new BooleanQuery();
                QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                		.buildQueryBuilder().forEntity(Taxon.class).get();
                
                String [] taxonFields = Taxon.TaxonFieldsForAutocomplete.split("\\|");
                String searchTermLowerCase = searchTerm.toLowerCase();
                
                // create wildcard queries for every field
                for (String taxonField : taxonFields) {
					Query query = queryBuilder
										.keyword()
										.wildcard()
										.onField(taxonField)
										.matching(searchTermLowerCase + "*")
										.createQuery();
					
					booleanQuery.add(query, BooleanClause.Occur.SHOULD);
				}
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery =
                        fullTextSession.createFullTextQuery(booleanQuery, Taxon.class);
                
                // return only the field values
                hsQuery
                	.setProjection(taxonFields)
                	.setMaxResults(10);
                
                List<Object[]> results = hsQuery.list();
                
                List<String> suggestions = new ArrayList<String>();
                Set<String> suggest = new HashSet<String>();
                
                for (Object[] result : results) {
					for (int i = 0; i < taxonFields.length; i++) {
						if(result[i] == null)
							continue;
						String fieldValue = result[i].toString();
						fieldValue = fieldValue.toLowerCase();
						if(fieldValue.contains(searchTermLowerCase)){
							suggest.add(fieldValue);
						}
					}
				}
                
                suggestions.addAll(suggest);
                return suggestions;
			}
		});
	}
}
