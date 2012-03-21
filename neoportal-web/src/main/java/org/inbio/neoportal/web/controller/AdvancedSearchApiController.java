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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.FromStringDeserializer.URLDeserializer;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FiltersSDTO;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.inbio.neoportal.web.dto.FilterWDTO;
import org.inbio.neoportal.web.dto.FiltersWDTO;
import org.inbio.neoportal.web.dto.OccurrenceWDTOJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    private FilterSDTO filterSDTO;
    
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
        method = RequestMethod.POST, 
        headers="Accept=application/json"
        )
    @ResponseBody
    public Object advancedSearchOccurrences (
        @RequestBody FilterSDTO filterSDTO,
        @RequestParam (value = "startIndex", defaultValue="0", required=false) int startIndex,
        @RequestParam (value = "results", defaultValue="10", required=false) int results
       ){
                
        List<OccurrenceCDTO> occurrenceCDTO =
                advancedSearchManager.occurrencePaginatedSearch(
                filterSDTO,
                startIndex, 
                results);
                
        /* TODO: usar columnList para retornar solo los datos solicitados
        * y reducir el tiempo de respuesta */
                
        return occurrenceCDTO;
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
}
