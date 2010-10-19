/*
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
package org.inbio.neoportal.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.neoportal.service.dao.GenericSearchDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class GenericSearchDAOImpl extends HibernateDaoSupport implements GenericSearchDAO {

	/**
	 * Returns the total of elements that could be retrived using that search
	 * criteria
	 * 
	 * @return number of results
	 */
	public Integer getTotalResults(final String HSQL) throws IllegalArgumentException {
		logger.debug("query: " + HSQL);
		HibernateTemplate template = getHibernateTemplate();
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(HSQL);
				query.setCacheable(false);
				logger.debug("result: " + new Integer ( query.uniqueResult().toString()));
				return new Integer ( query.uniqueResult().toString());
			}
		});		
		
	}

	/**
	 * 
	 * @param searchCriteria
	 * @param first
	 * @param last
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getResults(final String HSQL, final int first, final int last) throws IllegalArgumentException {

		logger.debug("query getResults: " + HSQL);
		HibernateTemplate template = getHibernateTemplate();
		return (List<Integer>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(HSQL);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(false);
				query.setFirstResult(first-1);
				query.setMaxResults(last-(first-1));
				return query.list();
			}
		});		
	}
	
	
}
