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
import java.util.List;
import org.inbio.neoportal.core.dao.ColumnDefaultDAO;
import org.inbio.neoportal.core.dao.ColumnListDAO;
import org.inbio.neoportal.core.dao.FilterListDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnListCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.FilterListCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceLiteCDTO;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnDefaultSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FiltersSDTO;
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
    private ColumnListDAO columnListDAO;
    
    @Autowired 
    private ColumnDefaultDAO columnDefaultDAO;

    @Autowired
    private FilterListDAO filterListDAO;
    
    @Autowired
    private OccurrenceDAO occurrenceDAO;
    
    
    @Override
    public List<ColumnListSDTO> getAllColumns() {
        List<ColumnListCDTO> clCDTO;
        List<ColumnListSDTO> result = new ArrayList<ColumnListSDTO>();
        
        clCDTO = columnListDAO.getAllColumns();
        
        for (ColumnListCDTO itemCDTO : clCDTO) {
            result.add(
                new ColumnListSDTO(
                        itemCDTO.getColumnListId(),
                        itemCDTO.getLang(),
                        itemCDTO.getKey(),
                        itemCDTO.getValue()));
        }
        
        return result;
    }

    @Override
    public ColumnListSDTO getColumnListByKey(String keyName) {
        ColumnListCDTO clCDTO;
        
        clCDTO = columnListDAO.getColumnListByKey(keyName);
        
        return new ColumnListSDTO(
                clCDTO.getColumnListId(), 
                clCDTO.getLang(), 
                clCDTO.getKey(), 
                clCDTO.getValue());
    }
    
    @Override
    public FiltersSDTO getFilters(){
        FiltersSDTO filtersSDTO = new FiltersSDTO();
        
        filtersSDTO.setColumnList(this.getAllColumns());
        filtersSDTO.setColumnDefault(this.getColumnDefault());
        filtersSDTO.setFilterList(this.getAllFilters());
        
        return filtersSDTO;
    }
    
    @Override
    public List<ColumnDefaultSDTO> getColumnDefault(){
        List<ColumnDefaultCDTO> columnDefaultCDTO;
        List<ColumnDefaultSDTO> result = new ArrayList<ColumnDefaultSDTO>();
        
        columnDefaultCDTO = columnDefaultDAO.getAllColumns();
        
        for (ColumnDefaultCDTO item : columnDefaultCDTO) {
            result.add(new ColumnDefaultSDTO(
                            item.getColumnDefaultId(),
                            item.getKey(),
                            item.getValue()));
        }
        
        return result;
    }

    @Override
    public List<FilterListSDTO> getAllFilters() {
        List<FilterListCDTO> flCDTO;
        List<FilterListSDTO> result = new ArrayList<FilterListSDTO>();
        
        flCDTO = filterListDAO.getAllFilters();
        
        for (FilterListCDTO itemCDTO : flCDTO) {
            result.add(
                new FilterListSDTO(
                        itemCDTO.getFilterListId(),
                        itemCDTO.getLang(),
                        itemCDTO.getKey(),
                        itemCDTO.getValue()));
        }
        
        return result;
    }
    
    @Override
    public List<OccurrenceCDTO> occurrencePaginatedSearch(
            FilterSDTO filters, 
            int offset, 
            int quantity) {
        
        //create search text base on filters
        String query = createQuery(filters);
        
        List<OccurrenceCDTO> occurrenceCDTO = 
                occurrenceDAO.advancedSearch(query, offset, quantity);
        
        return occurrenceCDTO;
    }

    @Override
    public Long occurrenceSearchCount(FilterSDTO filters) {
        //create search text base on filters
        String query = createQuery(filters);
        
        return occurrenceDAO.searchCount(query);        
    }
    
    private String createQuery(FilterSDTO filters){
        String query = "";
        
        /* taxon terms */
        AdvancedSearchData taxonomy = filters.getTaxonomy();
        if (taxonomy.getFilters().containsKey("taxon") && 
            taxonomy.getFilters().get("taxon") != ""){
            query += " (";
            query += "defaultName: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "kingdom: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "division: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "class_: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "order: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "family: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "genus: \"" + taxonomy.getFilters().get("taxon") + "\" OR ";
            query += "scientificName: \"" + taxonomy.getFilters().get("taxon") + "\") ";
        }
        
        return query;
    }

}
