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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dto.transformers.OccurrenceDWCTransformer;
import org.inbio.neoportal.core.dto.transformers.OccurrenceTransformer;
import org.inbio.neoportal.core.entity.Occurrence;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asanabria
 */
@Repository
public class OccurrenceDAOImpl 
    extends GenericBaseDAOImpl<Occurrence, BigDecimal>
        implements OccurrenceDAO{ 
    
       /**
    * Return a generic search.
    * @param entityClass
    * @param fields
    * @param searchText
    * @param offset
    * @param quantity
    * @return 
    */
    @Override
    public List search(
        final String searchText,
        final int offset, 
        final int quantity){
        
          String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));
        
        return super.search(Occurrence.class,
                            new OccurrenceTransformer(),
                            fieldList.toArray(new String[fieldList.size()]),
                            searchText,
                            offset,
                            quantity);
    }

    /**
     * Return a search count
     * @param entityClass
     * @param fields
     * @param searchText
     * @return 
     */
    @Override
    public Long searchCount( final String searchText){
        
          String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));
        
        return super.searchCount(Occurrence.class, 
                                 new OccurrenceTransformer(),
                                 fieldList.toArray(new String[fieldList.size()]),
                                 searchText);
    }
    
    /**
    * Return a generic search.
    * @param entityClass
    * @param fields
    * @param searchText
    * @param offset
    * @param quantity
    * @return 
    */
    @Override
    public List advancedSearch(
        final String searchText,
        final int offset, 
        final int quantity){
        
        return super.search(Occurrence.class,
                            new OccurrenceDWCTransformer(),
                            searchText,
                            offset,
                            quantity);
    }
    
    @Override
    public Occurrence findByLocationId(
            final String locationId){
        HibernateTemplate template = getHibernateTemplate();
		return (Occurrence) template.execute(new HibernateCallback() {
            @Override
			public Object doInHibernate(Session session) {
                Query query = session.createQuery(
						"from Occurrence as oc"
						+ " where oc.locationId = :locationId");
				query.setParameter("locationId", locationId);
				return query.list().get(0);
			}
		});
    }
}
