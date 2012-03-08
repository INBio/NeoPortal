/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.ColumnListDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnListCDTO;
import org.inbio.neoportal.core.dto.transformers.ColumnListTransformer;
import org.inbio.neoportal.core.entity.ColumnList;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author avargas
 */
@Repository
public class ColumnListDAOImpl 
        extends GenericBaseDAOImpl<ColumnList, BigDecimal>
        implements ColumnListDAO {

    @Override
    public List<ColumnListCDTO> getAllColumns() {
        HibernateTemplate template = getHibernateTemplate();
		return (List<ColumnListCDTO>) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from ColumnList");
                query.setResultTransformer(new ColumnListTransformer());
                
                query.setCacheable(true);
				return query.list();
			}
		});
     
    }

    @Override
    public ColumnListCDTO getColumnListByKey(final String keyName) {
         HibernateTemplate template = getHibernateTemplate();
		return (ColumnListCDTO) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"from ColumnList");
                query.setParameter("Key", keyName);
                query.setResultTransformer(new ColumnListTransformer());
				return query.list();
			}
		});
    }
    
}
