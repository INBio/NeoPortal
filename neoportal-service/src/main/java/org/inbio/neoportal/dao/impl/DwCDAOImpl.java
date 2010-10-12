package org.inbio.neoportal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.entity.DarwinCore;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<OcurrenceLiteDTO> search(final String[] fields, final String searchText, final int offset, final int quantity) {

        HibernateTemplate template = getHibernateTemplate();

        return (List<OcurrenceLiteDTO>) template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

                FullTextSession fullTextSession = Search.getFullTextSession(session);
                
                // create native Lucene query
                MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
                org.apache.lucene.search.Query query = null;

                //FIXME Manejo de errores
                try {
                    query = parser.parse(searchText);
                } catch (ParseException ex) {
                    Logger.getLogger(DwCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
                
                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);

                // Configure the result list
                hsQuery.setResultTransformer(new OccurrenceResultTransformer());
                hsQuery.setFirstResult(offset);
                hsQuery.setMaxResults(quantity);
                
                return hsQuery.list();
            }
        });
    }

    public Integer searchCount(final String[] fields, final String searchText) {

        HibernateTemplate template = getHibernateTemplate();

        return (Integer) template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

                FullTextSession fullTextSession = Search.getFullTextSession(session);

                // Create native Lucene query
                MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
                org.apache.lucene.search.Query query = null;

                try {
                    query = parser.parse(searchText);
                } catch (ParseException ex) {
                    Logger.getLogger(DwCDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Wrap Lucene query in a org.hibernate.Query
                org.hibernate.search.FullTextQuery hsQuery = fullTextSession.createFullTextQuery(query, DarwinCore.class);

                return new Integer(hsQuery.getResultSize());
            }
        });
    }

    private class OccurrenceResultTransformer implements ResultTransformer {

        @Override
        public List transformList(List list) {
            List<DarwinCore> dwcList = (List<DarwinCore>) list;
            List<OcurrenceLiteDTO> newList = new ArrayList<OcurrenceLiteDTO>();

            for(DarwinCore dwc: dwcList)
                newList.add(
                    new OcurrenceLiteDTO(dwc.getGlobaluniqueidentifier(),
                                         dwc.getCatalognumber(),
                                         dwc.getInstitutioncode(),
                                         dwc.getScientificname(),
                                         dwc.getDecimallatitude(),
                                         dwc.getDecimallongitude()));
            return newList;
        }

        @Override
        public Object transformTuple(Object[] os, String[] strings) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
