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

package org.inbio.neoportal.manager.impl;

import org.inbio.neoportal.manager.SearchManager;
import java.util.ArrayList;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class SearchManagerImplTest {

    SearchManager instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        ArrayList<String> xmlFiles = new ArrayList<String> ();
        xmlFiles.add("classpath:/META-INF/spring/applicationContext-service.xml");
        String[] xmlLocs = xmlFiles.toArray( new String[xmlFiles.size()]);

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(xmlLocs) ;
        instance = (SearchManager) ac.getBean("searchManagerImpl");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fullPaginatedSearch method, of class SearchManagerImpl.
     */
    @Test
    public void testFullPaginatedSearch() throws Exception {
        System.out.println("fullPaginatedSearch");
        String searchText = "Inga_vera";
        int offset = 0;
        int quantity = 20;
        Integer expResult = new Integer(20);
        List result = instance.fullPaginatedSearch(searchText, offset, quantity);
        assertEquals(expResult, new Integer(result.size()));
    }

    /**
     * Test of fullSearchCount method, of class SearchManagerImpl.
     */
    @Test
    public void testFullSearchCount() throws Exception {
        System.out.println("fullSearchCount");
        String searchText = "Inga_vera";
        Integer expResult = new Integer(240);
        Integer result = instance.fullSearchCount(searchText);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDwcDAO method, of class SearchManagerImpl.
     */
    /*
    @Test
    public void testGetDwcDAO() {
        System.out.println("getDwcDAO");
        SearchManagerImpl instance = new SearchManagerImpl();
        DwCDAO expResult = null;
        DwCDAO result = instance.getDwcDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of setDwcDAO method, of class SearchManagerImpl.
     */
    /*
    @Test
    public void testSetDwcDAO() {
        System.out.println("setDwcDAO");
        DwCDAO dwcDAO = null;
        SearchManagerImpl instance = new SearchManagerImpl();
        instance.setDwcDAO(dwcDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
}