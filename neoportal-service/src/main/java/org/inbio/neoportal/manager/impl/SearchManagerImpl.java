/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager.impl;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.manager.SearchManager;


/**
 *
 * @author asanabria
 */
public class SearchManagerImpl implements SearchManager{

    private DwCDAO dwcDAO;


    public void searchByTaxon(String searchText) throws ParseException{
        String[] fields =
                new String[]{ "scientificname" };
        dwcDAO.search(fields, searchText, 0, 20);
    }

    public void searchByLocality(String searchText) throws ParseException{
         String[] fields =
                new String[]{ "locality" };
        dwcDAO.search(fields, searchText, 0, 20);

    }
    public void searchByArea(String searchText) throws ParseException{
         String[] fields =
                new String[]{   "country",
                                "stateprovince",
                                "county" };
        dwcDAO.search(fields, searchText, 0, 20);
    }
    public void searchByAll(String searchText) throws ParseException{


        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county" };
        dwcDAO.search(fields, searchText, 0, 20);

    }


    public DwCDAO getDwcDAO() {
        return dwcDAO;
    }

    public void setDwcDAO(DwCDAO dwcDAO) {
        this.dwcDAO = dwcDAO;
    }
}
