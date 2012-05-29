/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.entity.SearchColumn;

/**
 *
 * @author avargas
 */
public class SearchColumnTransformer
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple.length > 0? tuple[0] : null;        
    }

    @Override
    public List transformList(List collection) {
        List<SearchColumn> searchColumnArray = (List<SearchColumn>) collection;
        List<SearchColumnCDTO> newList = new ArrayList<SearchColumnCDTO>();
        
        for(SearchColumn searchColumn: searchColumnArray){
            newList.add(
                new SearchColumnCDTO(
                    searchColumn.getColumnId().toString(),
                    searchColumn.getSearchGroup().getSearchGroupId().toString(),
                    searchColumn.getColumnKey()
                ));
        }
        
        return newList;
    }
    
}
