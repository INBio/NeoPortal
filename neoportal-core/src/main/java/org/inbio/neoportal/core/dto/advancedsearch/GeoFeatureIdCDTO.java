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
public class GeoFeatureIdCDTO
        extends BaseDTO
            implements Comparable {

    private String geoLayerId;
    private String geoFeatureId;
    

    public GeoFeatureIdCDTO() {
        
    }

    public GeoFeatureIdCDTO(String geoLayerId, String geoFeatureId) {
        this.geoLayerId = geoLayerId;
        this.geoFeatureId = geoFeatureId;
    }

    public String getGeoFeatureId() {
        return geoFeatureId;
    }

    public void setGeoFeatureId(String geoFeatureId) {
        this.geoFeatureId = geoFeatureId;
    }

    public String getGeoLayerId() {
        return geoLayerId;
    }

    public void setGeoLayerId(String geoLayerId) {
        this.geoLayerId = geoLayerId;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
