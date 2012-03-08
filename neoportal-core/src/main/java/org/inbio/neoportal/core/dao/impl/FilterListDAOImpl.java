/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.FilterListDAO;
import org.inbio.neoportal.core.dto.advancedsearch.FilterListCDTO;
import org.inbio.neoportal.core.dto.transformers.FilterListTransformer;
import org.inbio.neoportal.core.entity.FilterList;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class FilterListDAOImpl 
        extends GenericBaseDAOImpl<FilterList, BigDecimal>
        implements FilterListDAO {

    @Override
    public List<FilterListCDTO> getAllFilters() {
        HibernateTemplate template = getHibernateTemplate();
		return (List<FilterListCDTO>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from FilterList");
                query.setResultTransformer(new FilterListTransformer());
                
                query.setCacheable(true);
				return query.list();
			}
		});
     
    }

    @Override
    public FilterListCDTO getFilterListByKey(final String keyName) {
         HibernateTemplate template = getHibernateTemplate();
		return (FilterListCDTO) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from FilterList");
                query.setParameter("Key", keyName);
                query.setResultTransformer(new FilterListTransformer());
				return query.list();
			}
		});
    }
    
}
