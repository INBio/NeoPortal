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
package org.inbio.neoportal.core.dto;

import java.util.List;
import org.inbio.neoportal.common.dto.BaseDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;


/**
 *
 * @author avargas
 */
public class LocationCDTO
        extends BaseDTO
            implements Comparable {

     private String locationId;
     private String decimalLatitude;
     private String decimalLongitude;
     private String geodeticDatum;
     private String coordinateUncertaintyInMeters;
     private String pointRadiusSpatialFit;
     private String footprintWkt;
     private String footPrintSpatialFit;
     private String verbatimCoordinates;
     private String verbatimLatitude;
     private String verbatimLongitude;
     private String verbatimCoordinateSystem;
     private String georeferenceProtocol;
     private String georeferenceSources;
     private String georeferenceVerificationStatus;
     private String georeferenceRemarks;
    
     private List<GeoFeatureCDTO> geoFeaturesCDTO;
     
    @Override
    public int compareTo(Object t) {
        return this.locationId.compareTo(((LocationCDTO)t).getLocationId());
    }

    public LocationCDTO() {
    }

    public LocationCDTO(String locationId, String decimalLatitude, String decimalLongitude, String geodeticDatum, String coordinateUncertaintyInMeters, String pointRadiusSpatialFit, String footprintWkt, String footPrintSpatialFit, String verbatimCoordinates, String verbatimLatitude, String verbatimLongitude, String verbatimCoordinateSystem, String georeferenceProtocol, String georeferenceSources, String georeferenceVerificationStatus, String georeferenceRemarks) {
        this.locationId = locationId;
        this.decimalLatitude = decimalLatitude;
        this.decimalLongitude = decimalLongitude;
        this.geodeticDatum = geodeticDatum;
        this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
        this.pointRadiusSpatialFit = pointRadiusSpatialFit;
        this.footprintWkt = footprintWkt;
        this.footPrintSpatialFit = footPrintSpatialFit;
        this.verbatimCoordinates = verbatimCoordinates;
        this.verbatimLatitude = verbatimLatitude;
        this.verbatimLongitude = verbatimLongitude;
        this.verbatimCoordinateSystem = verbatimCoordinateSystem;
        this.georeferenceProtocol = georeferenceProtocol;
        this.georeferenceSources = georeferenceSources;
        this.georeferenceVerificationStatus = georeferenceVerificationStatus;
        this.georeferenceRemarks = georeferenceRemarks;
    }

    public String getCoordinateUncertaintyInMeters() {
        return coordinateUncertaintyInMeters;
    }

    public void setCoordinateUncertaintyInMeters(String coordinateUncertaintyInMeters) {
        this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
    }

    public String getDecimalLatitude() {
        return decimalLatitude;
    }

    public void setDecimalLatitude(String decimalLatitude) {
        this.decimalLatitude = decimalLatitude;
    }

    public String getDecimalLongitude() {
        return decimalLongitude;
    }

    public void setDecimalLongitude(String decimalLongitude) {
        this.decimalLongitude = decimalLongitude;
    }

    public String getFootPrintSpatialFit() {
        return footPrintSpatialFit;
    }

    public void setFootPrintSpatialFit(String footPrintSpatialFit) {
        this.footPrintSpatialFit = footPrintSpatialFit;
    }

    public String getFootprintWkt() {
        return footprintWkt;
    }

    public void setFootprintWkt(String footprintWkt) {
        this.footprintWkt = footprintWkt;
    }

    public String getGeodeticDatum() {
        return geodeticDatum;
    }

    public void setGeodeticDatum(String geodeticDatum) {
        this.geodeticDatum = geodeticDatum;
    }

    public String getGeoreferenceProtocol() {
        return georeferenceProtocol;
    }

    public void setGeoreferenceProtocol(String georeferenceProtocol) {
        this.georeferenceProtocol = georeferenceProtocol;
    }

    public String getGeoreferenceRemarks() {
        return georeferenceRemarks;
    }

    public void setGeoreferenceRemarks(String georeferenceRemarks) {
        this.georeferenceRemarks = georeferenceRemarks;
    }

    public String getGeoreferenceSources() {
        return georeferenceSources;
    }

    public void setGeoreferenceSources(String georeferenceSources) {
        this.georeferenceSources = georeferenceSources;
    }

    public String getGeoreferenceVerificationStatus() {
        return georeferenceVerificationStatus;
    }

    public void setGeoreferenceVerificationStatus(String georeferenceVerificationStatus) {
        this.georeferenceVerificationStatus = georeferenceVerificationStatus;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPointRadiusSpatialFit() {
        return pointRadiusSpatialFit;
    }

    public void setPointRadiusSpatialFit(String pointRadiusSpatialFit) {
        this.pointRadiusSpatialFit = pointRadiusSpatialFit;
    }

    public String getVerbatimCoordinateSystem() {
        return verbatimCoordinateSystem;
    }

    public void setVerbatimCoordinateSystem(String verbatimCoordinateSystem) {
        this.verbatimCoordinateSystem = verbatimCoordinateSystem;
    }

    public String getVerbatimCoordinates() {
        return verbatimCoordinates;
    }

    public void setVerbatimCoordinates(String verbatimCoordinates) {
        this.verbatimCoordinates = verbatimCoordinates;
    }

    public String getVerbatimLatitude() {
        return verbatimLatitude;
    }

    public void setVerbatimLatitude(String verbatimLatitude) {
        this.verbatimLatitude = verbatimLatitude;
    }

    public String getVerbatimLongitude() {
        return verbatimLongitude;
    }

    public void setVerbatimLongitude(String verbatimLongitude) {
        this.verbatimLongitude = verbatimLongitude;
    }

    public List<GeoFeatureCDTO> getGeoFeaturesCDTO() {
        return geoFeaturesCDTO;
    }

    public void setGeoFeaturesCDTO(List<GeoFeatureCDTO> geoFeaturesCDTO) {
        this.geoFeaturesCDTO = geoFeaturesCDTO;
    }
    
}
