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
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.occurrence.TaxonLiteDTO;
import org.inbio.neoportal.core.dto.taxon.description.TaxonDescriptionLiteDTO;
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
    private TaxonDAO taxonDAO;

    @Override
    public List<TaxonDescriptionLiteDTO> 
        speciesListPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        List<TaxonLiteDTO> occurrenceList = null;
        
        TaxonLiteDTO ol = null; //Used to iterate over occurrenceList
        
        //Set to store all the diferent scientific names
        Set<TaxonDescriptionLiteDTO> scientificNames = 
            new HashSet<TaxonDescriptionLiteDTO>();
        
        TaxonDescriptionLiteDTO sp = null;
        
        int lastResult = 0;
        
        //Result List of SpeciesLiteDTO Objects
        List<TaxonDescriptionLiteDTO> result = 
            new ArrayList<TaxonDescriptionLiteDTO>();

        int         maxQuantity     = 10;
        int         nextStartItem   = offset;

       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        // Search the results of the query
        occurrenceList = taxonDAO.search(
            fieldList.toArray(new String[fieldList.size()]), 
            searchText, nextStartItem, maxQuantity);
        
        for (TaxonLiteDTO tldto: occurrenceList ){
             sp = new TaxonDescriptionLiteDTO();
             sp.setImageURL("http://pulsatrix.inbio.ac.cr/projects/atta2/chrome/site/test.JPG");
             sp.setCommonName("Nombre común X");
             sp.setScientificName(tldto.getScientificName());
             scientificNames.add(sp);
        }

        result.addAll(scientificNames);

        return result;
    }


    @Override
    public List<TaxonLiteDTO> fullPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        
        
        String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        String[] taxonDescription =
                new String[]{ "scientificName", "kingdomTaxon", "phylumTaxon",
                             "classTaxon", "orderTaxon", "familyTaxon",
                             "genusTaxon", "synonyms", "commonNames"};

         ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        fieldList.addAll(Arrays.asList(occurrence));
        fieldList.addAll(Arrays.asList(taxonDescription));

        return taxonDAO.search(fieldList.toArray(new String[fieldList.size()]), searchText, offset, quantity);

    }

    @Override
    public Integer fullSearchCount(String searchText)
            throws ParseException {

                String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        String[] taxonDescription =
                new String[]{ "scientificName", "kingdomTaxon", "phylumTaxon",
                             "classTaxon", "orderTaxon", "familyTaxon",
                             "genusTaxon", "synonyms", "commonNames"};

         ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        fieldList.addAll(Arrays.asList(occurrence));
        fieldList.addAll(Arrays.asList(taxonDescription));


        return taxonDAO.searchCount(fieldList.toArray(new String[fieldList.size()]), searchText);
    }
}
