package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.inbio.neoportal.core.dao.GroupNavDAO;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.dto.transformers.GroupNavTransformer;
import org.inbio.neoportal.core.entity.GroupNav;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GroupNavDAOImpl
		extends GenericBaseDAOImpl<GroupNav, BigDecimal>
			implements GroupNavDAO {
	
	@Override
	public GroupNavCDTO getById(final BigDecimal id) {
		
		HibernateTemplate template = getHibernateTemplate();
		return (GroupNavCDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"select gn from GroupNav gn"
						+ " where gn.groupNavId = :id");
				query.setParameter("id", id);
                query.setResultTransformer(new GroupNavTransformer());
				//query.setResultTransformer(new AliasToBeanResultTransformer(GroupNavCDTO.class));
                
                //query.setCacheable(true);
				return query.uniqueResult();
			}
		});
		
	}
	
	@Override
	public List<GroupNavCDTO> getFirstLevel(){
		
		HibernateTemplate template = getHibernateTemplate();
		return (List<GroupNavCDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						//"select distinct gn from GroupNav gn " +
						//"left join fetch gn.groupNavChilds left join fetch gn.groupNavParent" +
                		"select gn from GroupNav gn" +
						" where gn.groupNavParent = null");
                query.setResultTransformer(new GroupNavTransformer());
                //query.setResultTransformer(Transformers.aliasToBean(GroupNavCDTO.class));
                
				return query.list();
			}
		});
	}
	
	@Override
	public List<GroupNav> findAll(Class<GroupNav> entityClass) {
		HibernateTemplate template = getHibernateTemplate();
		return (List<GroupNav>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {       
                Query query = session.createQuery(
						"select distinct gn from GroupNav gn " +
						"left join fetch gn.groupNavChilds left join fetch gn.groupNavParent");
                
				return query.list();
			}
		});			
	}

}
