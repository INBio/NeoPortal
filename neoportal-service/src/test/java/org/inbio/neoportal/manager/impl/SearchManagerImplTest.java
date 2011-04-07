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
package org.inbio.neoportal.manager.impl;

import org.inbio.neoportal.core.test.NeoportalTestBase;
import java.math.BigDecimal;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.service.manager.SearchManager;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author asanabria
 */
public class SearchManagerImplTest extends NeoportalTestBase {

    @Autowired
    private SearchManager searchManagerImpl;

    @Autowired
    public  TaxonDAO taxonDAOImpl;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Taxon taxon = new Taxon();
        
        if(taxonDAOImpl.findAll(Taxon.class).isEmpty()){

            taxon.setTaxonId(new BigDecimal(1));
            taxon.setDefaultName("Inga vera");
            taxonDAOImpl.create(taxon);

            taxon.setTaxonId(new BigDecimal(2));
            taxon.setDefaultName("Inga vera subsp. spuria");
            taxonDAOImpl.create(taxon);

            taxon.setTaxonId(new BigDecimal(3));
            taxon.setDefaultName("Inga vera subsp. vera");
            taxonDAOImpl.create(taxon);

            taxon.setTaxonId(new BigDecimal(4));
            taxon.setDefaultName("Inga vera");
            taxonDAOImpl.create(taxon);

            taxon.setTaxonId(new BigDecimal(5));
            taxon.setDefaultName("Inga vera");
            taxonDAOImpl.create(taxon);
        }
        
        this.index(Taxon.class);
    }

    @After
    public void tearDown() {
        searchManagerImpl = null;
    }

    /**
     * Test of fullPaginatedSearch method, of class SearchManagerImpl.
     */
    @Test
    public void testSpeciesPaginatedSearch() throws Exception {
        System.out.println("fullPaginatedSearch");
        String searchText = "Inga_vera";
        int offset = 0;
        int quantity = 20;
        Integer expResult = new Integer(3);
        
        List result 
            = searchManagerImpl.speciesListPaginatedSearch(searchText, 
                offset, quantity);
        
        assertEquals(expResult, new Integer(result.size()));
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
        Integer expResult = new Integer(5);
        
        List result 
            = searchManagerImpl.fullPaginatedSearch(searchText, 
                offset, quantity);
        
        assertEquals(expResult, new Integer(result.size()));
    }

    /**
     * Test of fullSearchCount method, of class SearchManagerImpl.
     */
    @Test
    public void testFullSearchCount() throws Exception {
        System.out.println("fullSearchCount");
        String searchText = "Inga";
        Long expResult = new Long(5);
        Long result = searchManagerImpl.fullSearchCount(searchText);
        assertEquals(expResult, result);
    }
}