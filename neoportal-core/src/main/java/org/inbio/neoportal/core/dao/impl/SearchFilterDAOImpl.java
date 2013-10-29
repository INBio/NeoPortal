/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.SearchFilterDAO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.transformers.SearchFilterTransformer;
import org.inbio.neoportal.core.entity.SearchFilter;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class SearchFilterDAOImpl 
        extends GenericDAOImpl<SearchFilter, BigDecimal>
        implements SearchFilterDAO {

    @Override
    public List<SearchFilterCDTO> getAllFilters() {
      Session session = getSessionFactory().getCurrentSession();       
      Query query = session.createQuery(
        "from SearchFilter");
      query.setResultTransformer(new SearchFilterTransformer());

      query.setCacheable(true);
      return query.list();
     
    }

    @Override
    public SearchFilterCDTO getSearchFilterByKey(final String keyName) {
       Session session = getSessionFactory().getCurrentSession();       
       Query query = session.createQuery(
         "from SearchFilter");
       query.setParameter("FilterKey", keyName);
       query.setResultTransformer(new SearchFilterTransformer());
       return (SearchFilterCDTO)query.list().get(0);
    }
    
}
