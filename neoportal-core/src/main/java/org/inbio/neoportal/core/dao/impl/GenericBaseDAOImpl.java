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

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.inbio.neoportal.core.dao.GenericBaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 * @author asanabria 
 */
//@Configuration
public class GenericBaseDAOImpl<E ,I> 
    extends HibernateDaoSupport implements GenericBaseDAO<E, I> {

    @Autowired
    private void initFactory(SessionFactory sessionFactory){
        this.setSessionFactory(sessionFactory);
    }

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#create(java.lang.Object)
	 */
	public void create(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.persist(entity);
		template.flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#update(java.lang.Object)
	 */
	public void update(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.update(entity);
		template.flush();
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#delete(java.lang.Object)
	 */
	public void delete(E entity) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(entity);
		template.flush();		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public E findById(Class<E> entityClass, I entityId) {
		HibernateTemplate template = getHibernateTemplate();
		return (E) template.get(entityClass, (Serializable) entityId);
	}	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.GenericBaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entityClass) {
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(entityClass);
	}
}
