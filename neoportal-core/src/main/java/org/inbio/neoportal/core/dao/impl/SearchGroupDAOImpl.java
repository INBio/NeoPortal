/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.SearchGroupDAO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.dto.transformers.SearchGroupTransformer;
import org.inbio.neoportal.core.entity.SearchGroup;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class SearchGroupDAOImpl 
        extends GenericDAOImpl<SearchGroup, BigDecimal>
        implements SearchGroupDAO {

    @Override
    public List<SearchGroupCDTO> getAllSearchGroups() {
      Session session = getSessionFactory().getCurrentSession();       
      Query query = session.createQuery(
        "from SearchGroup");
      query.setResultTransformer(new SearchGroupTransformer());

      query.setCacheable(true);
      return query.list();
     
    }

    @Override
    public SearchGroupCDTO getSearchGroupByKey(final String keyName) {
       Session session = getSessionFactory().getCurrentSession();
       Query query = session.createQuery(
         "from SearchGroup");
       query.setParameter("Key", keyName);
       query.setResultTransformer(new SearchGroupTransformer());
       return (SearchGroupCDTO)query.list().get(0);
    }
    
}
