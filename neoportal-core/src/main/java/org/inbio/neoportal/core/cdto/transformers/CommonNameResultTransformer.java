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
package org.inbio.neoportal.core.cdto.transformers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.cdto.commonname.CommonNameLiteCDTO;
import org.inbio.neoportal.core.entity.CommonName;


/**
 * Transfrom a list of CommonName entities to OccurrenceLiteDTO
 * @author asanabria
 */
public class CommonNameResultTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
        List<CommonName> CommonNameList = (List<CommonName>) list;
        List<CommonNameLiteCDTO> newList = new ArrayList<CommonNameLiteCDTO>();

        for(CommonName CommonName: CommonNameList)
            newList.add(
                new CommonNameLiteCDTO(
                    CommonName.getCommonNameId(),
                    CommonName.getName(),
                    CommonName.getUsedBy()));
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
