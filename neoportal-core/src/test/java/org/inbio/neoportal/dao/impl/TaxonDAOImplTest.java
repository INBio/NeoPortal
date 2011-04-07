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
package org.inbio.neoportal.dao.impl;

import org.inbio.neoportal.core.test.NeoportalTestBase;
import java.math.BigDecimal;
import java.util.List;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.entity.Taxon;
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
public class TaxonDAOImplTest extends NeoportalTestBase{

    @Autowired
    private TaxonDAO taxonDAOImpl;

    @BeforeClass
    public static void setUpClass() 
         throws Exception {  }

    @AfterClass
    public static void tearDownClass() 
            throws Exception {  }

    @Before
    public void setUp() 
        throws InterruptedException{
    
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
    public void tearDown() { }

    /**
     * Test of search method, of class DwCDAOImpl.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String[] fields = {"defaultName"};
        String searchText = "Inga";
        int offset = 0;
        int quantity = 20;
        Integer expResult = new Integer(5);
        List result = taxonDAOImpl.search(fields, searchText, offset, quantity);
        assertEquals(expResult, new Integer(result.size()));
    }

    /**
     * Test of searchCount method, of class DwCDAOImpl.
     */
    @Test
    public void testSearchCount() {
        System.out.println("searchCount");
        String[] fields = {"defaultName"};
        String searchText = "Inga_vera";
        Long expResult = new Long(5);
        Long result = taxonDAOImpl.searchCount(fields, searchText);
        assertEquals(expResult, result);
    }

}