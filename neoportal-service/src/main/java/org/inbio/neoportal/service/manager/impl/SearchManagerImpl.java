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
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.service.dao.DwCDAO;
import org.inbio.neoportal.service.dto.occurrence.OccurrenceLiteDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteDTO;
import org.inbio.neoportal.service.manager.SearchManager;
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
    public List<SpeciesLiteDTO> speciesListPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        List<OccurrenceLiteDTO> occurrenceList = null;
        OccurrenceLiteDTO ol = null; //Used to iterate over occurrenceList
        //Set to store all the diferent scientific names
        Set<SpeciesLiteDTO> scientificNames = new HashSet<SpeciesLiteDTO>();
        int lastResult = 0;
        //Result List of SpeciesLiteDTO Objects
        List<SpeciesLiteDTO> result = new ArrayList<SpeciesLiteDTO>();

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

        do{
            // Search the results of the query
            occurrenceList = dwcDAO.search(fields, searchText, nextStartItem, maxQuantity);

            // Iterate over the results an leave only distinct scientific Names
            for (Iterator<OccurrenceLiteDTO> iter = occurrenceList.iterator(); iter.hasNext(); lastResult++ ) {

                ol = iter.next();
                // Ignore duplictes scientificNames by inserting them in a java.util.Set.
                SpeciesLiteDTO sp = new SpeciesLiteDTO();
                sp.setImageURL("http://pulsatrix.inbio.ac.cr/projects/atta2/chrome/site/test.JPG");
                sp.setCommonName("Nombre común X");
                sp.setScientificName(ol.getScientificName());
                scientificNames.add(sp);

                // if the resultSet reach the required quantity then quits
                if(scientificNames.size() == quantity){
                    next = false;
                    break;
                }
            }

            nextStartItem += maxQuantity;

        } while(next && nextStartItem < 10000 && occurrenceList.size() < maxQuantity);

        occurrenceList.clear();
        result.addAll(scientificNames);

        return result;
    }


    @Override
    public List<OccurrenceLiteDTO> fullPaginatedSearch(String searchText, int offset, int quantity)
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
