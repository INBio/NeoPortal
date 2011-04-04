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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.occurrence.TaxonLiteDTO;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.transformers.TaxonResultTransformer;
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
    public List<TaxonLiteDTO> search(final String[] fields, 
        final String searchText, final int offset, final int quantity) {

        HibernateTemplate template = getHibernateTemplate();

        return (List<TaxonLiteDTO>) 
            template.execute(new HibernateCallback() {
                
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);
                
                // create native Lucene query
                MultiFieldQueryParser parser = 
                        new MultiFieldQueryParser(Version.LUCENE_29, 
                            fields, new StandardAnalyzer(Version.LUCENE_29));


                //FIXME Manejo de errores
                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(TaxonDAOImpl.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                FullTextQuery hsQuery =
                        fullTextSession.createFullTextQuery(query, Taxon.class);

                // Configure the result list
                hsQuery.setResultTransformer(new TaxonResultTransformer());
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);

                return hsQuery.list();
            }
        });
    }

    @Override
    public Integer searchCount(final String[] fields, final String searchText) {

        HibernateTemplate template = getHibernateTemplate();

        return (Integer) template.execute(new HibernateCallback() {
            
            @Override
            public Object doInHibernate(Session session) {

                Query query = null;
                FullTextSession fullTextSession = 
                    Search.getFullTextSession(session);

                // create native Lucene query
                MultiFieldQueryParser parser =
                        new MultiFieldQueryParser(Version.LUCENE_29, 
                            fields, new StandardAnalyzer(Version.LUCENE_29));

                try {
                    
                    query = parser.parse(searchText);
                    
                } catch (ParseException ex) {
                    
                    Logger.getLogger(TaxonDAOImpl.class.getName())
                        .log(Level.SEVERE, null, ex);
                    
                }

                // Wrap Lucene query in a org.hibernate.Query
                FullTextQuery hsQuery =
                    fullTextSession.createFullTextQuery(query, Taxon.class);

                return new Integer(hsQuery.getResultSize());
            }
        });
    }
}
