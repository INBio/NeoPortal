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
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.entity.SearchColumn;
import org.inbio.neoportal.core.entity.SearchColumnDefault;
import org.inbio.neoportal.core.entity.SearchFilter;
import org.inbio.neoportal.core.entity.SearchFilterValue;
import org.inbio.neoportal.core.entity.SearchGroup;


/**
 * Transfrom a list of SearchGroup entities to SearchGroupCDTO
 * @author avargas
 */
public class SearchGroupTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
                
        return list;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        
        return transformObject(os[0]);
        
    }
    
    /**
     * 
     * @param searchGroupObject
     * @return 
     */
    private SearchGroupCDTO transformObject(Object searchGroupObject){
        SearchGroupCDTO searchGroupCDTO = new SearchGroupCDTO();
        SearchGroup searchGroup = (SearchGroup)searchGroupObject;
        
        searchGroupCDTO.setSearchGroupId(searchGroup.getSearchGroupId().toString());
        searchGroupCDTO.setKey(searchGroup.getKey());
            
        List<SearchColumnCDTO> searchColumnList = new ArrayList<SearchColumnCDTO> ();
        List<SearchFilterCDTO> searchFilterList = new ArrayList<SearchFilterCDTO>();
        List<ColumnDefaultCDTO> columnDefaultList = new ArrayList<ColumnDefaultCDTO>();

        for(Iterator it= searchGroup.getSearchColumns().iterator(); it.hasNext();){
            SearchColumn searchColumn = (SearchColumn)it.next();
            searchColumnList.add(
                new SearchColumnCDTO(
                    searchColumn.getColumnId().toString(),
                    searchGroup.getSearchGroupId().toString(),
                    searchColumn.getColumnKey(),
                    searchColumn.getSort()));
        }

        for(Iterator it= searchGroup.getSearchColumnDefaults().iterator(); it.hasNext();){
            SearchColumnDefault columnDefault = (SearchColumnDefault) it.next();
            columnDefaultList.add(
                new ColumnDefaultCDTO(
                        columnDefault.getColumnDefaultId().toString(),
                        searchGroup.getSearchGroupId().toString(),
                        columnDefault.getColumnDefaultKey()));
        }

        for(Iterator it= searchGroup.getSearchFilters().iterator(); it.hasNext();){
            SearchFilter searchFilter = (SearchFilter) it.next();
        
            List<String> values = new ArrayList<String>();
            for(Iterator valuesIt= searchFilter.getSearchFilterValues().iterator(); valuesIt.hasNext();){
            	SearchFilterValue sValue = (SearchFilterValue) valuesIt.next();
            	values.add(sValue.getValue());
            }
            
            searchFilterList.add(
                new SearchFilterCDTO(
                    searchFilter.getFilterId().toString(), 
                    searchGroup.getSearchGroupId().toString(),
                    searchFilter.getFilterKey(), 
                    searchFilter.getType(),
                    values));
        }
        
        searchGroupCDTO.setSearchColumnList(searchColumnList);
        searchGroupCDTO.setColumnDefaultList(columnDefaultList);
        searchGroupCDTO.setSearchFilterList(searchFilterList);
        
        return searchGroupCDTO;
    }
}
