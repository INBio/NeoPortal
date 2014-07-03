/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.core.dao.TaxonPlicDAO;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonPlic;
import org.springframework.stereotype.Repository;

/**
 * @author avargas
 *
 */
@Repository
public class TaxonPlicDAOImpl
          extends GenericDAOImpl<TaxonPlic, BigDecimal>
              implements TaxonPlicDAO {

  /* (non-Javadoc)
   * @see org.inbio.neoportal.core.dao.TaxonPlicDAO#findAllByScientificName(java.lang.String)
   */
  @Override
  public List<TaxonPlic> findAllByScientificName(String scientificName) {
    Session session = getSessionFactory().getCurrentSession();
    Query query = null;
    FullTextSession fullTextSession = 
        Search.getFullTextSession(session);
    
    String searchText = "taxon.defaultName:\"" + scientificName + "\"";
    
    // create native Lucene query
    QueryParser parser = 
            new QueryParser(Version.LUCENE_33, 
                searchText, new StandardAnalyzer(Version.LUCENE_33));

    //FIXME Manejo de errores
    try {
        
        query = parser.parse(searchText);
        
    } catch (ParseException ex) {
        
        Logger.getLogger(Taxon.class.getName())
            .log(Level.SEVERE, null, ex);
        
        return null;
    }
    
    // Wrap Lucene query in a org.hibernate.Query
    org.hibernate.Query hsQuery =
            (org.hibernate.Query) fullTextSession.createFullTextQuery(query, TaxonPlic.class);
      
    return hsQuery.list();
  }

  /**
   * 
   */
  @Override
  public List<TaxonPlic> getByTaxonId(BigDecimal taxonId) {
    Session session = getSessionFactory().getCurrentSession();
    org.hibernate.Query query = session.createQuery("from TaxonPlic " +
    		"where taxon.id = :taxonId " +
    		"and published = 'Si'");
    query.setParameter("taxonId", taxonId);
    
    return query.list();
  }

}
