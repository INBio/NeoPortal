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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.dao.DwCDAO;
import org.inbio.neoportal.dto.OcurrenceLiteDTO;
import org.inbio.neoportal.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
@Service
public class SearchManagerImpl implements SearchManager{

    @Autowired
    private DwCDAO dwcDAO;

    @Override
    public List<OcurrenceLiteDTO> speciesListPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        List<OcurrenceLiteDTO> occurrenceList = null;

        Set<OcurrenceLiteDTO> scientificNames = new HashSet<OcurrenceLiteDTO>();

        boolean     next            = true;
        int         maxQuantity     = 1000;
        int         nextStartItem   = offset;

        // All the indexed fields
        String[] fields =
                new String[]{ "scientificname",
                "locality",
                "country",
                "stateprovince",
                "county" };

        while(next && nextStartItem < 10000){
            occurrenceList = dwcDAO.search(fields, searchText, nextStartItem, maxQuantity);

            for(OcurrenceLiteDTO ol : occurrenceList){
                // ignore duplictes scientificNames
                scientificNames.add(ol);

                if(scientificNames.size() >= quantity){
                    next = false;
                    break;
                }
            }
            nextStartItem += maxQuantity;
        }
        occurrenceList.clear();
        occurrenceList.addAll(scientificNames);

        return occurrenceList;
    }


    @Override
    public List<OcurrenceLiteDTO> fullPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        // All the indexed fields
        String[] fields =
                new String[]{ "scientificname",
                "locality",
                "country",
                "stateprovince",
                "county" };
        return dwcDAO.search(fields, searchText, offset, quantity);

    }

    @Override
    public Integer fullSearchCount(String searchText)
            throws ParseException {
        // All the indexed fields
        String[] fields =
                new String[]{ "scientificname",
                "locality",
                "country",
                "stateprovince",
                "county" };

        return dwcDAO.searchCount(fields, searchText);
    }



    /* Getters & Setters */
    public DwCDAO getDwcDAO() {
        return dwcDAO;
    }

    public void setDwcDAO(DwCDAO dwcDAO) {
        this.dwcDAO = dwcDAO;
    }
}
