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
package org.inbio.neoportal.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterSDTO;
import org.inbio.neoportal.service.entity.AdvancedSearchData;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.inbio.neoportal.web.dto.SearchColumnWDTO;
import org.inbio.neoportal.web.dto.SearchFilterWDTO;
import org.inbio.neoportal.web.dto.SearchGroupWDTO;
import org.inbio.neoportal.web.view.CSVview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author avargas
 */
@Controller
@RequestMapping("/api/advancedSearch/*")
public class AdvancedSearchApiController{
    
    @Autowired
    private AdvancedSearchManager advancedSearchManager;
    
    @Autowired
    private MessageSource messageSource;
   
    
    @RequestMapping(value="getColumnList", headers="Accept=application/json")
    @ResponseBody
    public Object getAllColumns(Locale locale){
        
        List<SearchGroupCDTO> searchGroupCDTOs = advancedSearchManager.getAllSearchGroup();
        List<SearchGroupWDTO> searchGroupWDTOs = new ArrayList<SearchGroupWDTO>();
             
        
        for(SearchGroupCDTO searchGroup: searchGroupCDTOs){
            List<SearchColumnWDTO> columnWDTOs = new ArrayList<SearchColumnWDTO>();
            List<SearchColumnWDTO> columnDefaults = new ArrayList<SearchColumnWDTO>();
            List<SearchFilterWDTO> filterWDTOs = new ArrayList<SearchFilterWDTO>();
            
            //fill column list
            for(SearchColumnCDTO searchColumn: searchGroup.getSearchColumnList()){
                
                columnWDTOs.add(
                        new SearchColumnWDTO(
                                searchColumn.getSearchColumnId(), 
                                searchColumn.getColumnKey(), 
                                messageSource.getMessage(
                                    searchColumn.getColumnKey(), 
                                    null,
                                    "?? " + searchColumn.getColumnKey() + " ??"
                        + "",
                                    locale)));
            }
            
            //fill column defaults
            for(ColumnDefaultCDTO columnDefault: searchGroup.getColumnDefaultList()){
                columnDefaults.add(
                        new SearchColumnWDTO(
                                columnDefault.getColumnDefaultId(), 
                                columnDefault.getColumnDefaultKey(), 
                                messageSource.getMessage(
                                    columnDefault.getColumnDefaultKey(), 
                                    null, 
                                    "?? " + columnDefault.getColumnDefaultKey() + " ??",
                                    locale)));
            }
            
            //fill filters
            for(SearchFilterCDTO searchFilter: searchGroup.getSearchFilterList()){
                ArrayList<HashMap<String, String>> values = null;
                if("combo".equals(searchFilter.getType())){
                
                    values = new ArrayList<HashMap<String, String>>();
                    
                    //make a map with value, label
                    //add ?? value ?? when no label was found
                    for(String value: searchFilter.getValues()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("key", value);
                        
                        if(value.equals("all")){
                            value = messageSource.getMessage(
                                    value,
                                    null,
                                    "?? " + value,
                                    locale);
                        }
                        
                        map.put("label", value);
                        values.add(map
                        // FIXME: not all values required translation, uncomment to get translated values
                                //                                messageSource.getMessage(
//                                    value,
//                                    null,
//                                    "?? " + value,
//                                    locale)
//                                value
                                );
                    }
                }
                
                filterWDTOs.add(
                        new SearchFilterWDTO(
                                searchFilter.getSearchFilterId(), 
                                searchFilter.getFilterKey(), 
                                searchFilter.getType(), 
                                messageSource.getMessage(
                                    searchFilter.getFilterKey(), 
                                    null,
                                    "?? " + searchFilter.getFilterKey() + " ??",
                                    locale),
                                values));
            }
            
            //fill the searchGroup
            searchGroupWDTOs.add(
                    new SearchGroupWDTO(
                            searchGroup.getSearchGroupId(), 
                            searchGroup.getKey(), 
                            messageSource.getMessage(
                                searchGroup.getKey(),
                                null,
                                "?? " + searchGroup.getKey() + " ??",
                                locale), 
                            columnWDTOs, 
                            columnDefaults, 
                            filterWDTOs));
        }
        
        return searchGroupWDTOs;
    }
    
    
    /**
     * 
     * @param columnList
     * @param filterList
     * @param startIndex
     * @param results
     * @return 
     */
    @RequestMapping (
        value = "getOccurrences",
        method = RequestMethod.POST, 
        headers="Accept=application/json"
        )
    @ResponseBody
    public Object advancedSearchOccurrences (
        @RequestBody FilterSDTO filterSDTO,
        @RequestParam (value = "startIndex", defaultValue="0", required=false) int startIndex,
        @RequestParam (value = "results", defaultValue="10", required=false) int results
       ){

        List<OccurrenceDwcCDTO> occurrenceDwcCDTO =
                advancedSearchManager.occurrencePaginatedSearch(
                filterSDTO,
                startIndex, 
                results);
                
        /* TODO: usar columnList para retornar solo los datos solicitados
        * y reducir tamaño de los datos transferidos */
                
        return occurrenceDwcCDTO;
    }
    
    
    @RequestMapping (
        value = "countOccurrences",
        method = RequestMethod.POST, 
        headers="Accept=application/json"
        )
    @ResponseBody
    public Object advancedSearchCountOccurrences (
        @RequestBody FilterSDTO filterSDTO,
        @RequestParam (value = "startIndex", defaultValue="0", required=false) int startIndex,
        @RequestParam (value = "results", defaultValue="10", required=false) int results
       ){
        
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("count", advancedSearchManager.occurrenceSearchCount(filterSDTO));
        
        return result;
    }
    
    @RequestMapping (
            value = "exportOccurrences",
            method = RequestMethod.POST
            )
        public ModelAndView advancedSearchOccurrencesCSV (
        //    @RequestParam (value = "filterSDTO") Object filterSDTOs
        		@RequestParam ("filterSDTO") String filters
           ){
            

    		ModelAndView mav = new ModelAndView(new CSVview());;
    		List<OccurrenceDwcCDTO> occurrenceCDTO;
    		List<String> columns;
    		ObjectMapper mapper = new ObjectMapper();
    		try {
    			
				FilterSDTO filterSDTO = mapper.readValue(filters, FilterSDTO.class);
				
	    		//get all occurrences
				occurrenceCDTO =
	                    advancedSearchManager.occurrenceSearch(filterSDTO);
	            
	            //get columns selected by the user
	    		columns = new ArrayList<String>();
	    		for (AdvancedSearchData data : filterSDTO.getFilterGroups()) {
					columns.addAll(data.getColumns());
				}
	    		
	            mav.addObject("data", occurrenceCDTO);
	            mav.addObject("columns", columns);
	            
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return mav;
        }
}
