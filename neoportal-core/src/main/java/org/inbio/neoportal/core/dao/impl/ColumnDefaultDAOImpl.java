/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.ColumnDefaultDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.transformers.SearchColumnTransformer;
import org.inbio.neoportal.core.entity.SearchColumnDefault;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class ColumnDefaultDAOImpl 
        extends GenericBaseDAOImpl<SearchColumnDefault, BigDecimal>
        implements ColumnDefaultDAO {

    @Override
    public List<ColumnDefaultCDTO> getAllColumns() {
        HibernateTemplate template = getHibernateTemplate();
		return (List<ColumnDefaultCDTO>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from SearchColumnDefault");
                query.setResultTransformer(new SearchColumnTransformer());
                
                query.setCacheable(true);
				return query.list();
			}
		});
     
    }

    @Override
    public ColumnDefaultCDTO getColumnDefaultByKey(final String keyName) {
         HibernateTemplate template = getHibernateTemplate();
		return (ColumnDefaultCDTO) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from SearchColumnDefault");
                query.setParameter("ColumnKey", keyName);
                query.setResultTransformer(new SearchColumnTransformer());
				return query.list();
			}
		});
    }
    
}
