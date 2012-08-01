package org.inbio.neoportal.service.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.inbio.neoportal.core.dao.GroupNavDAO;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.entity.GroupNav;
import org.inbio.neoportal.service.manager.GroupNavManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class GroupNavManagerImpl 
	implements GroupNavManager {

	@Autowired
	private GroupNavDAO groupNavDAO;
	
	@Autowired
    private HibernateTransactionManager transactionManager;
	
	@Override
	public List<GroupNavCDTO> getFirstLevel() {
		
		return groupNavDAO.getFirstLevel();
	}

	@Override
	public GroupNavCDTO getGroupNavById(int id) {
		return groupNavDAO.getById(new BigDecimal(id));
	}

	@Override
	public List<GroupNavCDTO> getAllTree() {

		//start new transaction
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(transaction);
        
        List<GroupNavCDTO> groupNavCDTO = new ArrayList<GroupNavCDTO>();
        
        try {
        	
    		List<GroupNav> groupNavAll = groupNavDAO.findAll(GroupNav.class);
    		
    		//create the groupNavCDTO like a tree
    		
    	
    		transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
		}
        		
		return groupNavCDTO;
	}

}
