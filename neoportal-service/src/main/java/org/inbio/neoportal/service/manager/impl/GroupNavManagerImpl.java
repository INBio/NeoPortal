package org.inbio.neoportal.service.manager.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.GroupNavDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonCDTO;
import org.inbio.neoportal.core.entity.GroupNav;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.service.dto.Response;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
import org.inbio.neoportal.service.manager.GroupNavManager;
import org.inbio.neoportal.service.manager.SearchManager;
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
	private TaxonDAO taxonDAO;
	
	@Autowired
	private SearchManager searchManager;
	
	@Autowired
    private HibernateTransactionManager transactionManager;
	
	@Override
	public List<GroupNavCDTO> getFirstLevel(String groupNavName) {
		
		return groupNavDAO.getFirstLevel(groupNavName);
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

	@Override
	public Response getSpeciesByGroupNav(int id, int offset, int quantity) {

		Response response = new Response();
		
		GroupNavCDTO gn = groupNavDAO.getById(new BigDecimal(id));

		//get taxon list
		List<TaxonCDTO> taxonList = getTaxonList(gn);
		
		//create query for lucene based on taxon
		String query = taxonListToQuery(taxonList);
		if(query.length() > 0){
			query = "(" + query + ")" + 
					" AND (taxonomicalRangeId:" + Taxon.TaxonomicalRange.SPECIES.getId() +
					" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.SUBSPECIES.getId() +
					" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.VARIETY.getId() +
					" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.FORM.getId() +
					" OR taxonomicalRangeId:" + Taxon.TaxonomicalRange.DOMAIN.getId() +
					")";
		}
		//retrieve species list...
		try {
			response.setTotal(searchManager.taxonSearchCount(query));
			response.setResult(searchManager.taxonPaginatedSearch(query, offset, quantity));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public List<TaxonCDTO> getTaxonList(GroupNavCDTO gn) {
		if(gn.getTaxonCDTO() != null){
			ArrayList<TaxonCDTO> finalList =new ArrayList<TaxonCDTO>();
			finalList.add(gn.getTaxonCDTO());
			return finalList;
		}
		
		ArrayList<TaxonCDTO> list =new ArrayList<TaxonCDTO>();
		
		for (GroupNavCDTO gnCDTO : gn.getGroupNavChilds()) {
			list.addAll(getTaxonList(gnCDTO));
		}
		
		return list;
	}

	
	public String taxonListToQuery(List<TaxonCDTO> taxonList){
		String query = "";
		
		for(TaxonCDTO taxon: taxonList){
			Taxon.TaxonomicalRange taxonRange = Taxon.TaxonomicalRange.getById(Long.parseLong(taxon.getTaxonomicalRangeId()));
			//FIXME: get taxon range to look for other than family
			query += " " + taxonRange.getTaxonomicalRangeName() + ":\"" + taxon.getDefaultName() + "\" OR"; 
		}
		
		if(query.length() > 0){
			query = query.substring(0, query.length() - 2);
		}
		
		return query;
	}
}
