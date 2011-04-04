/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
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
