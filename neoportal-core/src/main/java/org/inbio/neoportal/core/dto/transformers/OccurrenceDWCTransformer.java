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
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.core.entity.Occurrence;

/**
 * Transfrom a list of Occurrences entities to OccurrenceCDTO
 * @author avargas
 */
public class OccurrenceDWCTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
        List<Occurrence> occurrenceList = (List<Occurrence>) list;
        
        List<OccurrenceCDTO> newList = new ArrayList<OccurrenceCDTO>();
        
        OccurrenceGeospatialTransformer ogt = 
            new OccurrenceGeospatialTransformer();
                
       for(Occurrence oc: occurrenceList){
            OccurrenceCDTO newCDTO = new OccurrenceCDTO();
            newCDTO.setOccurrenceId(oc.getOccurrenceId().toString());
            newCDTO.setCatalogNumber(oc.getCatalogNumber());
            newCDTO.setRemarks(oc.getRemarks());
            newCDTO.setCollector(oc.getCollector());
            newCDTO.setSex(oc.getSex());
            newCDTO.setLifeStage(oc.getLifeStage());
            
            newCDTO.setTaxonId(oc.getTaxon().getTaxonId().toString());
            newCDTO.setScientificName(oc.getScientificName());
            // TODO: include AcceptedNameUsage to Occurrence Entity
            newCDTO.setAcceptedNameUsage("Not implemented");
            newCDTO.setParentNameUsage("Not implemented");
            newCDTO.setOriginalNameUsage("Not implemented");
            newCDTO.setNameAccordingTo("Not implemented");
            newCDTO.setNamePublishedIn("Not implemented");
            newCDTO.setNamePublishedInYear("Not implemented");
            newCDTO.setHigherClassification("Not implemented");
            newCDTO.setKingdom(oc.getKingdom());
            newCDTO.setPhylum(oc.getPhylum());
            newCDTO.setClass_(oc.getClass_());
            newCDTO.setOrder(oc.getOrders());
            newCDTO.setFamily(oc.getFamily());
            newCDTO.setGenus(oc.getGenus());
            newCDTO.setSubgenus("Not implemented");
            newCDTO.setSpecificEpithet(oc.getSpecificEpithet());
            newCDTO.setInfraspecificEpithet(oc.getInfraspecificEpithet());
            newCDTO.setInfraspecificRank(oc.getInfraspecificRank());   /*  dwc = taxonRank  */
            newCDTO.setVerbatimTaxonRank("Not implemented");
            newCDTO.setScientificNameAuthorship("Not implemented");
            newCDTO.setVernacularName("Not implemented");
            newCDTO.setNomenclaturalCode(oc.getNomenclaturalCode());
            newCDTO.setTaxonomicStatus("Not implemented");
            newCDTO.setNomenclaturalStatus("Not implemented");
            newCDTO.setTaxonRemarks("Not implemented");
            
            newList.add(newCDTO);
        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
