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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.commonname.CommonNameLiteDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteDTO;
import org.inbio.neoportal.core.dto.species.SpeciesLiteDTO;
import org.inbio.neoportal.service.manager.SearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria
 */
@Service
public class SearchManagerImpl implements SearchManager{

    @Autowired
    private TaxonDAO taxonDAO;

    /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<SpeciesLiteDTO> 
        speciesListPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        List<TaxonLiteDTO> occurrenceList = null;
        SpeciesLiteDTO sp = null;
                
        //Set to store all the diferent scientific names
        Set<SpeciesLiteDTO> speciesList = new HashSet<SpeciesLiteDTO>();
        
        //Result List of SpeciesLiteDTO Objects
        List<SpeciesLiteDTO> result = new ArrayList<SpeciesLiteDTO>();

       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        // Search the results of the query
        occurrenceList = taxonDAO.search(
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, offset, quantity);
        
        for (TaxonLiteDTO tldto: occurrenceList ){
             
            sp = new SpeciesLiteDTO();
             
             sp.setCommonName(this.joinCommonNames(tldto.getCommonNameList()));
             sp.setScientificName(tldto.getScientificName());
             sp.setImageURL("http://pulsatrix.inbio.ac.cr/projects/atta2/chrome/site/header.png");
             
             speciesList.add(sp);
        }

        result.addAll(speciesList);

        return result;
    }
   
    
    /**
     * Returns the element count of the search result
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long speciesListSearchCount(String searchText)
            throws ParseException{
        
       String[] taxon =
        new String[]{ "defaultName", "kingdom", "division", "class_",
                         "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        
        return taxonDAO.searchCount(
            fieldList.toArray(new String[fieldList.size()]),
            searchText);
    }
    
    /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<TaxonLiteDTO> fullPaginatedSearch
        (String searchText, int offset, int quantity) 
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

        return taxonDAO.search(fieldList.toArray(
            new String[fieldList.size()]), searchText, offset, quantity);

    }

    /**
     * 
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long fullSearchCount(String searchText)
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


        return taxonDAO.searchCount(
            fieldList.toArray(new String[fieldList.size()]), searchText);
    }
    
    
    /**
     * Put toguether all the common names of a list and concatenate them into a 
     * single string.
     * @param list
     * @return 
     */
    private String joinCommonNames(ArrayList<CommonNameLiteDTO> list) {

        CommonNameLiteDTO cm = null;
        
        int length = list.size();
        StringBuilder commonNameList = new StringBuilder();
        
        for(int i = 0; i < length; i++){
            cm = list.get(i);
            commonNameList.append(cm.getName());
            
            if( ! cm.getUsedBy().isEmpty() )
                commonNameList.append("(").append(cm.getUsedBy()).append(")");
            
            if(i < length-1)
                commonNameList.append(", ");          
        }
        
        return commonNameList.toString();
    }
}
