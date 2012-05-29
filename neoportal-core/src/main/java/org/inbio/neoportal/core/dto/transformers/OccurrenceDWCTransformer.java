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
import java.util.Set;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.LocationCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.core.entity.Location;

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
        

       for(Occurrence oc: occurrenceList){
            OccurrenceCDTO newCDTO = new OccurrenceCDTO();
            newCDTO.setOccurrenceId(oc.getOccurrenceId().toString());
            newCDTO.setCatalogNumber(oc.getCatalogNumber());
            newCDTO.setRemarks(oc.getRemarks());
            newCDTO.setCollector(oc.getCollector());
            newCDTO.setSex(oc.getSex());
            newCDTO.setLifeStage(oc.getLifeStage());
            
            if(oc.getTaxon() != null)
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
            
            //location information
            newCDTO.setLocation(transformLocation(oc.getLocation()));
            
            newList.add(newCDTO);
        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private LocationCDTO transformLocation(Location location){
        if(location == null)
            return null;
        
        LocationCDTO locationCDTO = new LocationCDTO();
        
        locationCDTO.setLocationId(location.getLocationId().toString());
        locationCDTO.setDecimalLatitude(location.getDecimalLatitude());
        locationCDTO.setDecimalLongitude(location.getDecimalLongitude());
        locationCDTO.setGeodeticDatum(location.getGeodeticDatum());
        locationCDTO.setCoordinateUncertaintyInMeters(location.getCoordinateUncertaintyInMeters());
        locationCDTO.setPointRadiusSpatialFit(location.getPointRadiusSpatialFit());
        locationCDTO.setFootprintWkt(location.getFootprintWkt());
        locationCDTO.setFootPrintSpatialFit(location.getFootPrintSpatialFit());
        locationCDTO.setVerbatimCoordinates(location.getVerbatimCoordinates());
        locationCDTO.setVerbatimLatitude(location.getVerbatimLatitude());
        locationCDTO.setVerbatimLongitude(location.getVerbatimLongitude());
        locationCDTO.setVerbatimCoordinateSystem(location.getVerbatimCoordinateSystem());
        locationCDTO.setGeoreferenceProtocol(location.getGeoreferenceProtocol());
        locationCDTO.setGeoreferenceSources(location.getGeoreferenceSources());
        locationCDTO.setGeoreferenceVerificationStatus(location.getGeoreferenceVerificationStatus());
        locationCDTO.setGeoreferenceRemarks(location.getGeoreferenceRemarks());
        
        List<GeoFeatureCDTO> featureCDTOs = new ArrayList<GeoFeatureCDTO>();
        
        Iterator itr = location.getFeatures().iterator();
        
        while (itr.hasNext()) {
            GeoFeature geoFeature = (GeoFeature) itr.next();
            
            GeoFeatureCDTO featureCDTO = new GeoFeatureCDTO();
            
            featureCDTO.setGeoLayerId(
                    String.valueOf(geoFeature.getGeoLayer().getGeoLayerId()));
            
            featureCDTO.setGeoFeatureId(
                    String.valueOf(geoFeature.getId().getGeoFeatureId()));
            
            featureCDTO.setName(geoFeature.getName());
            
            featureCDTO.setGeoLayerName(geoFeature.getGeoLayer().getName());
            
            featureCDTOs.add(featureCDTO);
        }
                
        locationCDTO.setGeoFeaturesCDTO(featureCDTOs);
        
        return locationCDTO;
                    
    }
}
