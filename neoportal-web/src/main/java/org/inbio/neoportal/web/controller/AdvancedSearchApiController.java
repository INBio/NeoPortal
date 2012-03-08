/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.inbio.neoportal.service.dto.advancedSearch.FiltersSDTO;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.inbio.neoportal.web.dto.FiltersWDTO;
import org.inbio.neoportal.web.dto.OccurrenceLiteWDTOJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author avargas
 */
@Controller
@RequestMapping("/api/advancedSearch/*")
public class AdvancedSearchApiController {
    
    @Autowired
    private AdvancedSearchManager advancedSearchManager;
    
    @RequestMapping(value="getColumnList", headers="Accept=application/json")
    @ResponseBody
    public Object getAllColumns(){
        
        FiltersSDTO filtersSDTO = advancedSearchManager.getFilters();
        FiltersWDTO filters = new FiltersWDTO();
        //List<ColumnListSDTO> columnListSDTO = advancedSearchManager.getAllColumns();
        //List<ColumnListWDTO> columnListWDTO = new ArrayList<ColumnListWDTO>();
        
//        for (ColumnListSDTO item : columnListSDTO) {
//            columnListWDTO.add(
//                    new ColumnListWDTO(
//                            item.getColumnListId(), 
//                            item.getLang(), 
//                            item.getKey(), 
//                            item.getValue()));
//        }
        
        filters.setColumnList(filtersSDTO.getColumnList());
        filters.setColumnDefault(filtersSDTO.getColumnDefault());
        filters.setFilterList(filtersSDTO.getFilterList());
        
        return filters;
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
        method = RequestMethod.GET
        )
    @ResponseBody
    public Object advancedSearchOccurrences (
        @RequestParam String columnList,
        @RequestParam String filterList,
        @RequestParam (value = "startIndex", defaultValue="0", required=false) int startIndex,
        @RequestParam (value = "results", defaultValue="10", required=false) int results){
        
        List<OccurrenceLiteSDTO> ols =
                advancedSearchManager.occurrencePaginatedSearch(
                filterList, 
                startIndex, 
                results);
        
        List<OccurrenceLiteWDTOJson> result = new ArrayList<OccurrenceLiteWDTOJson>();
        
        for (OccurrenceLiteSDTO elem : ols) {
            result.add(new OccurrenceLiteWDTOJson(
                    elem.getOccurrenceId(),
                    elem.getScientificName(),
                    elem.getCountry(),
                    elem.getProvince(),
                    elem.getCounty(),
                    elem.getLocality(),
                    elem.getLatitude(),
                    elem.getLongitude(),
                    elem.getCatalog(),
                    elem.getInstitution()));
        }
        
        return result;
    }
}
