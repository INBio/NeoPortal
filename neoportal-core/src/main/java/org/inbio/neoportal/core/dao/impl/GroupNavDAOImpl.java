package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.GroupNavDAO;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.dto.transformers.GroupNavSimpleTransformer;
import org.inbio.neoportal.core.dto.transformers.GroupNavTransformer;
import org.inbio.neoportal.core.entity.GroupNav;
import org.springframework.stereotype.Repository;

@Repository
public class GroupNavDAOImpl
		extends GenericDAOImpl<GroupNav, BigDecimal>
			implements GroupNavDAO {
	
	@Override
	public GroupNavCDTO getById(final BigDecimal id) {
		Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(
          "select gn from GroupNav gn"
              + " where gn.groupNavId = :id");
        query.setParameter("id", id);
        query.setResultTransformer(new GroupNavTransformer());
        return (GroupNavCDTO)query.uniqueResult();
	}
	
	@Override
	public List<GroupNavCDTO> getFirstLevel(final String groupNavName){
		
	  Session session = getSessionFactory().getCurrentSession();       
	  Query query = session.createQuery(
      		"from GroupNav gn " +
  						"inner join gn.groupNavParent p " +
      		"where p.groupNavParent = null and p.name = :groupNavName");
      query.setParameter("groupNavName", groupNavName);
      query.setResultTransformer(new GroupNavSimpleTransformer());
                  
      return (List<GroupNavCDTO>)query.list();
	}
	
	@Override
	public List<GroupNav> findAll(Class<GroupNav> entityClass) {
	  Session session = getSessionFactory().getCurrentSession();

      Query query = session.createQuery(
		"select distinct gn from GroupNav gn " +
		"left join fetch gn.groupNavChilds left join fetch gn.groupNavParent");
                
      return (List<GroupNav>)query.list();
	}

	@Override
	public List<GroupNavCDTO> getTreePart(String groupNavName, String treePart) {
		// TODO Auto-generated method stub
		return null;
	}

}
