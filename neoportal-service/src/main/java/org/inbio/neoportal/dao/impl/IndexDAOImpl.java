package org.inbio.neoportal.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.inbio.neoportal.dao.IndexDAO;
import org.inbio.neoportal.entity.DarwinCore;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 */

/**
 * @author jgutierrez
 *
 */
@Repository
public class IndexDAOImpl extends GenericBaseDAOImpl<DarwinCore,Integer> implements IndexDAO {

    public void createIndex(){

        HibernateTemplate template = getHibernateTemplate();

        template.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {

                int begin = 0;

                FullTextSession fullTextSession = Search.createFullTextSession(session);
                Query query = session.createQuery("from DarwinCore");

                query.setMaxResults(1000);

                while(begin < 10000){

                    query.setFirstResult(begin);
                    List<DarwinCore> listDwC = query.list();

                    for(DarwinCore dwc: listDwC){
                        fullTextSession.index(dwc);
                        System.out.println("dwc: "+dwc.getScientificname() );
                    }
                    begin +=1000;
                }


                //                try {
                //createIndexer(DarwinCore.class).startAndWait();
                //                } catch (InterruptedException ex) {
                //                    Logger.getLogger(IndexDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                //                }

                return null;
            }
        });
    }
}
