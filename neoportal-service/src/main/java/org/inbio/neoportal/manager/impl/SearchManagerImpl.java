/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager.impl;

import java.util.List;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.dto.MinimalOccurrenceInfoDTO;
import org.inbio.neoportal.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria
 */
@Service
public class SearchManagerImpl implements SearchManager{

    @Autowired
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
    public List<MinimalOccurrenceInfoDTO> searchByAll(String searchText) throws ParseException{


        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county" };
        return dwcDAO.search(fields, searchText, 0, 20);

    }


    public DwCDAO getDwcDAO() {
        return dwcDAO;
    }

    public void setDwcDAO(DwCDAO dwcDAO) {
        this.dwcDAO = dwcDAO;
    }
}
