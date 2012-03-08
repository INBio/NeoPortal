/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.FilterListCDTO;
import org.inbio.neoportal.core.entity.FilterList;

/**
 *
 * @author avargas
 */
public class FilterListTransformer
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple.length > 0? tuple[0] : null;        
    }

    @Override
    public List transformList(List collection) {
        List<FilterList> filterListArray = (List<FilterList>) collection;
        List<FilterListCDTO> newList = new ArrayList<FilterListCDTO>();
        
        for(FilterList filterList: filterListArray){
            newList.add(
                new FilterListCDTO(
                        filterList.getFilterListId().toString(),
                        filterList.getLang(),
                        filterList.getKey(),
                        filterList.getValue()
                ));
        }
        
        return newList;
    }
    
}
