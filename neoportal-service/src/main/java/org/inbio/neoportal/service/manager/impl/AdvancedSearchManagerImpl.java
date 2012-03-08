/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.inbio.neoportal.core.dao.ColumnDefaultDAO;
import org.inbio.neoportal.core.dao.ColumnListDAO;
import org.inbio.neoportal.core.dao.FilterListDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnListCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.FilterListCDTO;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnDefaultSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FiltersSDTO;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;
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
    public List<OccurrenceLiteSDTO> occurrencePaginatedSearch(
            String filters, 
            int offset, 
            int quantity) {
        
        //create search text base on filters
        String query = createQuery(filters);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long occurrenceSearchCount(String filters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private String createQuery(String filters){
        
        return "";
    }
}
