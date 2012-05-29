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
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoLayerCDTO;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.GeoLayer;


/**
 * Transfrom a list of GeoLayer entities to GeoLayerCDTO
 * @author avargas
 */
public class GeoLayerTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
                
        return list;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
        GeoLayerCDTO result = null;
        
        GeoLayer geoLayer = (GeoLayer)os[0];
        
        try {
            result = new GeoLayerCDTO();
            result.setGeoLayerId(String.valueOf(geoLayer.getGeoLayerId()));
            result.setName(geoLayer.getName());
            
            List<GeoFeatureCDTO> features = new ArrayList<GeoFeatureCDTO>();
            List<GeoFeature> featureList = new ArrayList<GeoFeature>(geoLayer.getGeoFeatures());
            for(GeoFeature feature: featureList){                
                features.add(
                        new GeoFeatureCDTO(
                                String.valueOf(geoLayer.getGeoLayerId()),
                                String.valueOf(feature.getId().getGeoFeatureId()),
                                feature.getName()));
            }
            
            result.setGeoFeatures(features);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return result;
    }
}
