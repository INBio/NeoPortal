/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.entity.ColumnDefault;

/**
 *
 * @author avargas
 */
public class ColumnDefaultTransformer
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple.length > 0? tuple[0] : null;        
    }

    @Override
    public List transformList(List collection) {
        List<ColumnDefault> columnDefaultArray = (List<ColumnDefault>) collection;
        List<ColumnDefaultCDTO> newList = new ArrayList<ColumnDefaultCDTO>();
        
        for(ColumnDefault columnDefault: columnDefaultArray){
            newList.add(
                new ColumnDefaultCDTO(
                        columnDefault.getColumnDefaultId().toString(),
                        columnDefault.getKey(),
                        columnDefault.getValue()
                ));
        }
        
        return newList;
    }
    
}
