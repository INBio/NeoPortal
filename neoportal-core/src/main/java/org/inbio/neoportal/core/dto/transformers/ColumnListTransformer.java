/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnListCDTO;
import org.inbio.neoportal.core.entity.ColumnList;

/**
 *
 * @author avargas
 */
public class ColumnListTransformer
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple.length > 0? tuple[0] : null;        
    }

    @Override
    public List transformList(List collection) {
        List<ColumnList> columnListArray = (List<ColumnList>) collection;
        List<ColumnListCDTO> newList = new ArrayList<ColumnListCDTO>();
        
        for(ColumnList columnList: columnListArray){
            newList.add(
                new ColumnListCDTO(
                        columnList.getColumnListId().toString(),
                        columnList.getLang(),
                        columnList.getKey(),
                        columnList.getValue()
                ));
        }
        
        return newList;
    }
    
}
