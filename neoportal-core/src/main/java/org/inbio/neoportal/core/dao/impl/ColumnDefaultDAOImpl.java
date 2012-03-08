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
import org.inbio.neoportal.core.dto.transformers.ColumnDefaultTransformer;
import org.inbio.neoportal.core.entity.ColumnDefault;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class ColumnDefaultDAOImpl 
        extends GenericBaseDAOImpl<ColumnDefault, BigDecimal>
        implements ColumnDefaultDAO {

    @Override
    public List<ColumnDefaultCDTO> getAllColumns() {
        HibernateTemplate template = getHibernateTemplate();
		return (List<ColumnDefaultCDTO>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from ColumnDefault");
                query.setResultTransformer(new ColumnDefaultTransformer());
                
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
						"from ColumnDefault");
                query.setParameter("key", keyName);
                query.setResultTransformer(new ColumnDefaultTransformer());
				return query.list();
			}
		});
    }
    
}
