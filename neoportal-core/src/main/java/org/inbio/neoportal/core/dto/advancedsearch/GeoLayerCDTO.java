/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import java.util.List;
import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class GeoLayerCDTO
        extends BaseDTO
            implements Comparable {

    private String geoLayerId;
    private String name;
    private List<GeoFeatureCDTO> geoFeatures;

    public GeoLayerCDTO() {
        
    }

    public GeoLayerCDTO(String geoLayerId, String geoLayerName, List<GeoFeatureCDTO> geoFeatures) {
        this.geoLayerId = geoLayerId;
        this.name = geoLayerName;
        this.geoFeatures = geoFeatures;
    }
    
    @Override
    public int compareTo(Object t) {
        return this.geoLayerId.compareTo(((GeoLayerCDTO) t).getGeoLayerId());
    }

    public List<GeoFeatureCDTO> getGeoFeatures() {
        return geoFeatures;
    }

    public void setGeoFeatures(List<GeoFeatureCDTO> geoFeatures) {
        this.geoFeatures = geoFeatures;
    }

    public String getGeoLayerId() {
        return geoLayerId;
    }

    public void setGeoLayerId(String geoLayerId) {
        this.geoLayerId = geoLayerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
}
