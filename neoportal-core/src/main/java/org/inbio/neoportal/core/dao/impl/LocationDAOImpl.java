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
import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.inbio.neoportal.core.dao.LocationDAO;
import org.inbio.neoportal.core.entity.Location;
import org.springframework.stereotype.Repository;

/**
 * @author avargas
 *
 */
@Repository
public class LocationDAOImpl 
	extends GenericDAOImpl<Location, BigDecimal> 
	implements LocationDAO {

  @Override
  public List<Location> searchLocationsByDistance(double radius, double latitude, double longitude) {
    Session session = getSessionFactory().getCurrentSession();
    
    FullTextSession fullTextSession = Search.getFullTextSession(session);
    QueryBuilder builder = fullTextSession
        .getSearchFactory()
        .buildQueryBuilder()
        .forEntity(Location.class)
        .get();
    
    Query luceneQuery = builder.spatial()
        .onDefaultCoordinates()
        .within(radius, Unit.KM)
        .ofLatitude(latitude)
        .andLongitude(longitude)
        .createQuery();
    
    org.hibernate.Query hQuery = fullTextSession.createFullTextQuery(luceneQuery, Location.class);
    
    return hQuery.list();
  }

}
