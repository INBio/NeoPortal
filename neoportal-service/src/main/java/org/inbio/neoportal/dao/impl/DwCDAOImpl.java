package org.inbio.neoportal.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTO;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTOFactory;
import org.inbio.neoportal.entity.DarwinCore;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 */

/**
 * @author jgutierrez
 *
 */
@Repository
public class DwCDAOImpl extends GenericBaseDAOImpl<DarwinCore,Integer> implements DwCDAO {

    public List<MinimalOccurrenceInfoDTO> search(final String[] fields, final String searchText, final int offset, final int quantity) {

        HibernateTemplate template = getHibernateTemplate();

        return (List<MinimalOccurrenceInfoDTO>) template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

//                FullTextSession fullTextSession = Search.createFullTextSession(session);
                FullTextSession fullTextSession = Search.getFullTextSession(session);
                
                // create native Lucene query
                MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
                org.apache.lucene.search.Query query = null;

                try {
                    query = parser.parse(searchText);
                } catch (ParseException ex) {
                    Logger.getLogger(DwCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);

                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                hsQuery.setResultTransformer(new MinimalOccurrenceInfoDTOFactory());
                

                int totalAmount = hsQuery.getResultSize();
                // execute search
                System.out.println("#-> Result Count "+ totalAmount);

                return hsQuery.list();
            }
        });
    }

    public Integer searchSize(final String[] fields, final String searchText) {

        HibernateTemplate template = getHibernateTemplate();

        return (Integer) template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

//                FullTextSession fullTextSession = Search.getFullTextSession(session);
                FullTextSession fullTextSession = Search.createFullTextSession(session);

                // create native Lucene query
                MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
                org.apache.lucene.search.Query query = null;

                try {
                    query = parser.parse(searchText);
                } catch (ParseException ex) {
                    Logger.getLogger(DwCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                // wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);

                return new Integer(hsQuery.getResultSize());
            }
        });
    }
}
