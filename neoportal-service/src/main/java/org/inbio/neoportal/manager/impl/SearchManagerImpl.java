/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager.impl;

import java.util.List;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria
 */
@Service
@Qualifier
public class SearchManagerImpl implements SearchManager{

    @Autowired
    private DwCDAO dwcDAO;



    public List<OcurrenceLiteDTO> fullSearch(String searchText) throws ParseException{

        // All the indexed fields
        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county" };
        return dwcDAO.search(fields, searchText, 0, 20);

    }

    public Integer fullSearchCount(String searchText) throws ParseException {
        // All the indexed fields
        String[] fields =
                new String[]{ "scientificname",
                                "locality",
                                "country",
                                "stateprovince",
                                "county" };

        return dwcDAO.searchCount(fields, searchText);
    }



    public DwCDAO getDwcDAO() {
        return dwcDAO;
    }

    public void setDwcDAO(DwCDAO dwcDAO) {
        this.dwcDAO = dwcDAO;
    }
}
