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
package org.inbio.neoportal.core.test;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author asanabria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:tests-context.xml"})

@TransactionConfiguration(
    transactionManager = "transactionManagerTest"
    ,defaultRollback = false)
public class NeoportalTestBase {
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * initialize the index according to a single class.
     * @param clazz
     * @throws InterruptedException 
     */
    public void index(Class clazz) {
               
        FullTextSession fullTextSession = 
            Search.getFullTextSession(sessionFactory.openSession());
        try{
            fullTextSession.createIndexer(clazz).startAndWait();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
