/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.SearchColumnDAO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.transformers.SearchColumnTransformer;
import org.inbio.neoportal.core.entity.SearchColumn;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class SearchColumnDAOImpl 
        extends GenericBaseDAOImpl<SearchColumn, BigDecimal>
        implements SearchColumnDAO {

    @Override
    public List<SearchColumnCDTO> getAllSearchColumns() {
        HibernateTemplate template = getHibernateTemplate();
		return (List<SearchColumnCDTO>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from SearchColumn");
                query.setResultTransformer(new SearchColumnTransformer());
                
                query.setCacheable(true);
				return query.list();
			}
		});
     
    }

    @Override
    public SearchColumnCDTO getSearchColumnByKey(final String keyName) {
         HibernateTemplate template = getHibernateTemplate();
		return (SearchColumnCDTO) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from SearchColumn");
                query.setParameter("ColumnKey", keyName);
                query.setResultTransformer(new SearchColumnTransformer());
				return query.list();
			}
		});
    }

    @Override
    public List<SearchColumnCDTO> getSearchColumnsByGroup(String searchGroupKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
