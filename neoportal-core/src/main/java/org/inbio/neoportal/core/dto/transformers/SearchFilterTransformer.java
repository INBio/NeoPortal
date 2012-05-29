/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.entity.SearchFilter;

/**
 *
 * @author avargas
 */
public class SearchFilterTransformer
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple.length > 0? tuple[0] : null;        
    }

    @Override
    public List transformList(List collection) {
        List<SearchFilter> searchFilterArray = (List<SearchFilter>) collection;
        List<SearchFilterCDTO> newList = new ArrayList<SearchFilterCDTO>();
        
        for(SearchFilter searchFilter: searchFilterArray){
            
            newList.add(
                new SearchFilterCDTO(
                        searchFilter.getFilterId().toString(),
                        searchFilter.getSearchGroup().getSearchGroupId().toString(),
                        searchFilter.getFilterKey(),
                        searchFilter.getType()
                ));
        }
        
        return newList;
    }
    
}
