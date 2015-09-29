package org.inbio.neoportal.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.inbio.neoportal.core.entity.Book;

public interface TaxonPlicBooksDAO extends  GenericDAO<Book, BigDecimal>
{
	  /**
	   * Return only the Published items
	   * @param idList
	   * @return
	   */
		public List<Book> getByTaxonPlicId(BigDecimal idList) ;
	
}
