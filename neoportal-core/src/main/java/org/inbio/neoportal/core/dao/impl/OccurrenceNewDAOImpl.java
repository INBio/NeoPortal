/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.inbio.neoportal.core.dao.OccurrenceNewDAO;
import org.inbio.neoportal.core.entity.OccurrenceDwc;
import org.springframework.stereotype.Repository;

/**
 * @author avargas
 *
 */
@Repository
public class OccurrenceNewDAOImpl 
	extends GenericDAOImpl<OccurrenceDwc, BigDecimal> implements
		OccurrenceNewDAO {

  @Override
  public OccurrenceDwc findByCatalogNumber(final String catalogNumber){
      Session session = getSessionFactory().getCurrentSession();
      FullTextSession fullTextSession = Search.getFullTextSession(session);
      
      // create Lucene query using the query DSL
      QueryBuilder qb = fullTextSession.getSearchFactory()
              .buildQueryBuilder().forEntity(OccurrenceDwc.class).get();
      org.apache.lucene.search.Query query = qb
              .keyword()
              .onField("catalogNumber")
              .matching(catalogNumber)
              .createQuery();
      
      // wrap Lucene query in a org.hibernate.Query
      org.hibernate.Query hQuery = 
              fullTextSession.createFullTextQuery(query, OccurrenceDwc.class);
      
      return (OccurrenceDwc)hQuery.uniqueResult();
  }

  /* (non-Javadoc)
   * @see org.inbio.neoportal.core.dao.OccurrenceNewDAO#findByCatalogNumberHql(java.lang.String)
   */
  @Override
  public OccurrenceDwc findByCatalogNumberHql(String catalogNumber) {
    Session session = getSessionFactory().getCurrentSession();
    org.hibernate.Query query = session.createQuery("from OccurrenceDwc " +
            "where catalogNumber = :catalogNumber ");
    query.setParameter("catalogNumber", catalogNumber);
    
    return (OccurrenceDwc) query.uniqueResult();
  }
}
