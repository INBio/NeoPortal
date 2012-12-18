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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.ProjectionConstants;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionLiteCDTO;
import org.inbio.neoportal.core.dto.transformers.TaxonDescriptionTransformer;
import org.inbio.neoportal.core.dto.transformers.TaxonDescriptionFullTransformer;
import org.inbio.neoportal.core.dto.transformers.TaxonLiteTransformer;
import org.inbio.neoportal.core.entity.TaxonDescription;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Grant access to the Taxon entity
 * @author asanabria
 * @author avargas
 */
@Repository
public class TaxonDescriptionDAOImpl 
    extends GenericBaseDAOImpl<TaxonDescription, BigDecimal>
        implements TaxonDescriptionDAO{    
        @Override
    public List<TaxonDescriptionLiteCDTO> search
        (final String searchText, final int offset, final int quantity) {
        
       String[] taxon =
                new String[]{ "scientificName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        return super.search(TaxonDescription.class,
                            new TaxonDescriptionTransformer(), 
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, 
                            offset, 
                            quantity);
       
    }

    @Override
    public Long searchCount(final String searchText) {
        
        String[] taxon =
                new String[]{ "scientificName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        
        return super.searchCount(TaxonDescription.class, 
                                new TaxonLiteTransformer(), 
                                fieldList.toArray(new String[fieldList.size()]), 
                                searchText);
    }

    @Override
    public List<TaxonDescriptionFullCDTO> findAllByScientificName(
       final String scientificName,
       final String provider) {
        HibernateTemplate template = getHibernateTemplate();
		return (List<TaxonDescriptionFullCDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from TaxonDescription as td"
						+ " where lower(td.scientificName) = lower(:scientificName)"
                        + " and lower(td.institutionCode) = lower(:provider)");
				query.setParameter("scientificName", scientificName);
                query.setParameter("provider", provider);
                query.setResultTransformer(new TaxonDescriptionFullTransformer());
                
                //query.setCacheable(true);
				return query.list();
			}
		});
        
    }
    
    @Override
    public List<TaxonDescriptionFullCDTO> findAllByScientificName(
       final String scientificName) {
        HibernateTemplate template = getHibernateTemplate();
		return (List<TaxonDescriptionFullCDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from TaxonDescription as td"
						+ " where lower(td.scientificName) = lower(:scientificName)");
				query.setParameter("scientificName", scientificName);
                query.setResultTransformer(new TaxonDescriptionFullTransformer());
                
                //query.setCacheable(true);
				return query.list();
			}
		});
    }

    @Override
    public List<TaxonDescriptionFullCDTO> searchFull(
            final String searchText, 
            final int offset, 
            final int quantity) {
        

        HibernateTemplate template = getHibernateTemplate();

        return (List) template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                String[] taxon =
                new String[]{ "scientificName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

                org.apache.lucene.search.Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                // create native Lucene query
                MultiFieldQueryParser parser = 
                        new MultiFieldQueryParser(Version.LUCENE_33, 
                            taxon, new StandardAnalyzer(Version.LUCENE_33));

                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(TaxonDescription.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery =
                        fullTextSession.createFullTextQuery(query, TaxonDescription.class);

                // Configure the result list
//                hsQuery.setResultTransformer(new TaxonDescriptionFullTransformer());
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                
                hsQuery.setProjection(
                    FullTextQuery.SCORE, ProjectionConstants.THIS, "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species");
                
                List results = ((Query)hsQuery).list();
                Object[] firstResult = (Object[]) results.get(0);
                
                return results;
            }
        });
    }
}
