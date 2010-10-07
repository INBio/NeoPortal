package org.inbio.neoportal.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.dao.IndexDAO;
import org.inbio.neoportal.entity.DarwinCore;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 */

/**
 * @author jgutierrez
 *
 */
public class IndexDAOImpl extends GenericBaseDAOImpl<DarwinCore,Integer> implements IndexDAO {

    public void createIndex(){

        HibernateTemplate template = getHibernateTemplate();

        template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

                System.out.println("Creando el <Indice>\n");

                FullTextSession fullTextSession = Search.getFullTextSession(session);

                try {
                    fullTextSession.createIndexer(DarwinCore.class).startAndWait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(IndexDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }
        });

    }
}
