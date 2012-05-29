/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.core.dto.advancedsearch;

import org.inbio.neoportal.common.dto.BaseDTO;

/**
 *
 * @author avargas
 */
public class GeoFeatureCDTO
        extends BaseDTO
            implements Comparable {

    private String geoLayerId;
    private String geoFeatureId;
    private String name;
    // suitable field to easy access layer info
    private String geoLayerName;

    public GeoFeatureCDTO() {
        
    }

    public GeoFeatureCDTO(String geoLayerId, String geoFeatureId, String name) {
        this.geoLayerId = geoLayerId;
        this.geoFeatureId = geoFeatureId;
        this.name = name;
    }

    public String getGeoFeatureId() {
        return geoFeatureId;
    }

    public void setGeoFeatureId(String geoFeatureId) {
        this.geoFeatureId = geoFeatureId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String geoFeatureName) {
        this.name = geoFeatureName;
    }

    public String getGeoLayerId() {
        return geoLayerId;
    }

    public void setGeoLayerId(String geoLayerId) {
        this.geoLayerId = geoLayerId;
    }

    public String getGeoLayerName() {
        return geoLayerName;
    }

    public void setGeoLayerName(String geoLayerName) {
        this.geoLayerName = geoLayerName;
    }
    

    @Override
    public int compareTo(Object t) {
        return this.name.compareTo( ((GeoFeatureCDTO)t).getName() );
    }
    
}
