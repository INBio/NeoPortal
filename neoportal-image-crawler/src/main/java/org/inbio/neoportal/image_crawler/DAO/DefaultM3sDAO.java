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
package org.inbio.neoportal.image_crawler.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author avargas
 *
 */
@Transactional("transactionManagerM3s")
@Component
public class DefaultM3sDAO implements M3sDAO {
  
  @Autowired
  @Qualifier(value="sessionFactoryM3s")
  private SessionFactory sessionFactoryM3s;

  
  public SessionFactory getSessionFactory() {
    return sessionFactoryM3s;
  }
  
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactoryM3s = sessionFactory;
  }
  
  /* (non-Javadoc)
   * @see org.inbio.neoportal.image_crawler.DAO.M3sDAO#getImages(int, int)
   */
  public List<Map<String,Object>> getImages(int offset, int quantity) {
    Session session = sessionFactoryM3s.getCurrentSession();
    
    Query query = session.createSQLQuery("select m.media_id, tm.taxon_id, " +
    		"p.first_name || ' ' || p.last_name as author, " +
    		"t.name as rights " +
    		"from core.media m " +
    		"left join core.taxon_media tm ON m.media_id = tm.media_id " +
    		"left join core.person p ON m.author_person_id = p.person_id " +
    		"left join core.use_policy up ON m.use_policy_id = up.use_policy_id " +
    		"left join core.text_translation t ON up.name_text_id = t.text_id " +
    		"where tm.taxon_id is not null");
    query.setFirstResult(offset);
    query.setMaxResults(quantity);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    
    List<Map<String,Object>> result = query.list();
    return result;
  }

}
