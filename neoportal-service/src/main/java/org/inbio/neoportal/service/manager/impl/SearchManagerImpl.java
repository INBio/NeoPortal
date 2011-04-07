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
import java.util.List;
import java.util.Set;
import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.commonname.CommonNameLiteCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceGeospatialLiteCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceLiteCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
import org.inbio.neoportal.service.dto.species.SpeciesLiteSDTO;
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
    
    @Autowired
    private OccurrenceDAO occurrenceDAO;

    /**
     * 
     * @param searchText
     * @param offset
     * @param quantity
     * @return
     * @throws ParseException 
     */
    @Override
    public List<SpeciesLiteSDTO> 
        speciesPaginatedSearch(String searchText, int offset, int quantity)
            throws ParseException{

        List<TaxonLiteCDTO> occurrenceList = null;
        SpeciesLiteSDTO sp = null;
                
        //Set to store all the diferent scientific names
        Set<SpeciesLiteSDTO> speciesList = new HashSet<SpeciesLiteSDTO>();
        
        //Result List of SpeciesLiteDTO Objects
        List<SpeciesLiteSDTO> result = new ArrayList<SpeciesLiteSDTO>();

       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        // Search the results of the query
        occurrenceList = taxonDAO.search(
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, offset, quantity);
        
        for (TaxonLiteCDTO tldto: occurrenceList ){
             
            sp = new SpeciesLiteSDTO();
             
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
    public Long speciesSearchCount(String searchText)
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
    public List<OccurrenceLiteSDTO> occurrencePaginatedSearch
        (String searchText, int offset, int quantity) 
            throws ParseException{

        String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));
        
        ArrayList<OccurrenceLiteSDTO> olsdto = new 
            ArrayList<OccurrenceLiteSDTO>();
        
        // retrieve the search results
        ArrayList<OccurrenceLiteCDTO> ocList 
            =  (ArrayList<OccurrenceLiteCDTO>) occurrenceDAO.search(
                    fieldList.toArray(new String[fieldList.size()]), 
                    searchText, 
                    offset, 
                    quantity);
        
        ArrayList<OccurrenceGeospatialLiteCDTO> ogl = null;
        
        String latitude = null;
        String longitude = null;
        
        
        for(OccurrenceLiteCDTO ol : ocList ){
            
            // take only the firs of a list of coordinates
            ogl = ol.getGeospatialList();
            
            if(ogl.size() > 0){
                latitude = ogl.get(0).getLatitude();
                longitude = ogl.get(0).getLongitude();
            }else{
                latitude = null;
                longitude = null;
            }
            
            
            olsdto.add(
                new OccurrenceLiteSDTO(
                    ol.getScientificName(),
                    ol.getInstitution(),
                    ol.getCountry(),
                    ol.getProvince(),
                    ol.getCounty(),
                    ol.getLocality(),
                    latitude,
                    longitude,
                    ol.getCatalog()));
        }

        return olsdto;
    }

    /**
     * 
     * @param searchText
     * @return
     * @throws ParseException 
     */
    @Override
    public Long occurrenceSearchCount(String searchText)
            throws ParseException {

        String[] occurrence =
                new String[]{"scientificName", "higherTaxon", "kingdom",
                                 "phylum", "class_", "orders", "family",
                                   "genus", "specificEpithet", "country",
                                     "stateProvince", "county", "locality"};

        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.addAll(Arrays.asList(occurrence));

        return occurrenceDAO.searchCount(
            fieldList.toArray(new String[fieldList.size()]), searchText);
    }
    
    
    /**
     * Put toguether all the common names of a list and concatenate them into a 
     * single string.
     * @param list
     * @return 
     */
    private String joinCommonNames(ArrayList<CommonNameLiteCDTO> list) {

        CommonNameLiteCDTO cm = null;
        
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

/*  Jajajaj, apologies to the future me for a comment like this...
 * 
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

 */