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
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceGeospatialLiteCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceLiteCDTO;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.core.entity.OccurrenceGeospatialExtension;


/**
 * Transfrom a list of Taxon entities to OccurrenceLiteDTO
 * @author asanabria
 */
public class OccurrenceTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
        
        ArrayList<OccurrenceGeospatialExtension> temp = null;
        ArrayList<OccurrenceGeospatialLiteCDTO> ogl = null;
        
        List<Occurrence> occurrenceList = (List<Occurrence>) list;
        
        List<OccurrenceLiteCDTO> newList = new ArrayList<OccurrenceLiteCDTO>();
        
        OccurrenceGeospatialTransformer ogt = 
            new OccurrenceGeospatialTransformer();
                
       for(Occurrence oc: occurrenceList){
           
           temp = new ArrayList<OccurrenceGeospatialExtension>
                (oc.getOccurrenceGeospatialExtensions());
           
           ogl = (ArrayList<OccurrenceGeospatialLiteCDTO>) ogt.transformList(temp);
                               
            newList.add(new OccurrenceLiteCDTO(
                                oc.getScientificName(),
                                oc.getInstitutionCode(),
                                oc.getCountry(),
                                oc.getStateProvince(),
                                oc.getCounty(),
                                oc.getLocality(),
                                oc.getCatalogNumber(),
                                ogl));
        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
