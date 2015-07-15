package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.inbio.neoportal.core.dao.TaxonPlicBooksDAO;
import org.inbio.neoportal.core.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class TaxonPlicBooksDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements TaxonPlicBooksDAO 
{
	  @Override
	  public List<Book> getByTaxonPlicId(BigDecimal idList) 
	  {
	      Session session = getSessionFactory().getCurrentSession();	    
	    
		  org.hibernate.Query query = session.createQuery(
			  		"from Book B"
			  		+ " INNER JOIN taxon_plic_books T ON B.id_book = T.book_id where taxon_record_id = :idList");
		  query.setParameter("idList", idList);
		  
	      return query.list();	      
	      
	  }
}
