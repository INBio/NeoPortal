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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.neoportal.core.dao.ColumnDefaultDAO;
import org.inbio.neoportal.core.dao.GeoLayerDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.SearchColumnDAO;
import org.inbio.neoportal.core.dao.SearchFilterDAO;
import org.inbio.neoportal.core.dao.SearchGroupDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoLayerCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.entity.SearchGroup;
import org.inbio.neoportal.service.dto.advancedSearch.FilterSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.OccurrenceSDTO;
import org.inbio.neoportal.service.entity.AdvancedSearchData;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author avargas
 */
@Service
public class AdvancedSearchManagerImpl
            implements AdvancedSearchManager{
    
	
    @Autowired
    private SearchColumnDAO columnListDAO;
    
    @Autowired 
    private ColumnDefaultDAO columnDefaultDAO;

    @Autowired
    private SearchFilterDAO filterListDAO;
    
    @Autowired
    private OccurrenceDAO occurrenceDAO;    
    
    @Autowired
    private SearchGroupDAO searchGroupDAO; 
    
    @Autowired
    private GeoLayerDAO geoLayerDAO;

    @Override
    public List<SearchColumnCDTO> getAllColumns() {
        List<SearchColumnCDTO> clCDTO;
        
        clCDTO = columnListDAO.getAllSearchColumns();
        
        return clCDTO;
    }

    @Override
    public SearchColumnCDTO getColumnListByKey(String keyName) {
        SearchColumnCDTO clCDTO;
        
        clCDTO = columnListDAO.getSearchColumnByKey(keyName);
        
        return clCDTO;
    }
        
    @Override
    public List<ColumnDefaultCDTO> getColumnDefault(){
        List<ColumnDefaultCDTO> columnDefaultCDTO;
                
        columnDefaultCDTO = columnDefaultDAO.getAllColumns();
        
        return columnDefaultCDTO;
    }

    @Override
    public List<SearchFilterCDTO> getAllFilters() {
        List<SearchFilterCDTO> flCDTO;
                
        flCDTO = filterListDAO.getAllFilters();
        
        return flCDTO;
    }
    
	@Override
    public List<SearchGroupCDTO> getAllSearchGroup(){
        List<SearchGroupCDTO> sgCDTO;
        sgCDTO = searchGroupDAO.getAllSearchGroups();
        
        //prepare filters with their value list
        for (SearchGroupCDTO searchGroup: sgCDTO){
        	
        	//sort every column list based on sort field
        	Collections.sort(searchGroup.getSearchColumnList());
        
            for (SearchFilterCDTO searchFilterCDTO : searchGroup.getSearchFilterList()) {
                
                //add value list for particular filters
                if("combo".equals(searchFilterCDTO.getType())){
                	//manage geographic select value list
                	if(searchGroup.getKey().equals(SearchGroup.Group.GEOGRAPHIC.toString())){
	                    List<GeoLayerCDTO> geoLayers = 
	                            geoLayerDAO.getGeoLayerByName(searchFilterCDTO.getFilterKey());
	
	                    GeoLayerCDTO geoLayer = geoLayers.get(0);
	                    
	                    if(geoLayer != null){
	                        List<String> geoFeaturesValues = new ArrayList<String>();
	                        
	                        for(GeoFeatureCDTO feature: geoLayer.getGeoFeatures()){
	                            geoFeaturesValues.add(feature.getName());
	                        }
	
	                        searchFilterCDTO.setValues(geoFeaturesValues);
	                    }
                	}
                	
                	//sort by values
                	Collections.sort(searchFilterCDTO.getValues());
                    //add all option
                	searchFilterCDTO.getValues().add(0, "all");
                }
            }
        }
        
        return sgCDTO;
    }
    
    @Override
    public List<OccurrenceDwcCDTO> occurrencePaginatedSearch(
            FilterSDTO filters, 
            int offset, 
            int quantity) {
        
        //create search text base on filters
        String query = createQuery(filters);
                
        return occurrenceDAO.advancedSearchPaginated(query, offset, quantity);
    }

    @Override
    public Long occurrenceSearchCount(FilterSDTO filters) {
        //create search text base on filters
        String query = createQuery(filters);
            
        return occurrenceDAO.searchCount(query);        
    }
    
    /**
     * 
     * @param filters
     * @return 
     */
    private String createQuery(FilterSDTO filters){
        String query = "";
        
        for(AdvancedSearchData groupData: filters.getFilterGroups()){
            
            // seudo switch throw filterGroups aka searchGroups
            if("taxonomic".equals(groupData.getKey())){
                for (LinkedHashMap filter : groupData.getFilters()) {
                    if(query.length() > 0)
                        query += " AND ";
            
                    // Taxon terms only if the user write something
                    if("taxon_name".equals(filter.get("key")) && filter.get("value").toString().length() > 0){
                        query += " (";
                        query += "defaultName: \"" + filter.get("value") + "\" OR ";
                        query += "kingdom: \"" + filter.get("value") + "\" OR ";
                        query += "division: \"" + filter.get("value") + "\" OR ";
                        query += "class_: \"" + filter.get("value") + "\" OR ";
                        query += "order: \"" + filter.get("value") + "\" OR ";
                        query += "family: \"" + filter.get("value") + "\" OR ";
                        query += "genus: \"" + filter.get("value") + "\" OR ";
                        query += "scientificName: \"" + filter.get("value") + "\") ";
                    }
                }
            }
            else{
                for (LinkedHashMap filter : groupData.getFilters()) {
                    if ("all".equals(filter.get("value")) || filter.get("value").toString().length() == 0)
                        continue;
                    
                    if(query.length() > 0)
                        query += " AND ";
                    
                    //date range
                    if(filter.get("key").toString().compareTo("dateIdentified") == 0){
                    	String range = "";
                    	String [] dates = filter.get("value").toString().split("[|]"); 
                    	
                    	if(dates.length > 0){
                    		String [] date = dates[0].split("/");
                    		range = "[" + date[2] + date[1] + date[0];
                    		range += " TO ";
                    		
                    		date = dates[1].split("/");
                    		range += date[2] + date[1] + date[0] + "]";
                    	}
                    	
                    	query += filter.get("key") + ":" + range + " ";
                    }
                    else{
                    	query += filter.get("key") + ":\"" + filter.get("value") + "\" ";
                    }
                    
                    
                }
            }
        }
        
        Logger.getLogger(this.getClass().getName()).
        	debug("Query: " + query);
        
        return query;
    }

    
	@Override
	public List<OccurrenceDwcCDTO> occurrenceSearch(FilterSDTO filters) {
		//create search text base on filters
        String query = createQuery(filters);
            
        Long total = occurrenceDAO.searchCount(query);
        
        if (total == 0)
        	return new ArrayList<OccurrenceDwcCDTO>();
        
        ArrayList<OccurrenceDwcCDTO> occurrenceCDTOs = new ArrayList<OccurrenceDwcCDTO>();
        
        int index = 0;
        
        do {
        	List<OccurrenceDwcCDTO> blockList = 
        			occurrenceDAO.advancedSearchPaginated(query, index, 100); 
        	occurrenceCDTOs.addAll(blockList);
        	
        	index += 100;
		} while (index <= total);

        return occurrenceCDTOs;
	}
}
