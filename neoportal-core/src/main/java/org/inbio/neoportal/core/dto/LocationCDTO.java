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
     private String higherGeographyId;
     private String higherGeography;
     private String continent;
     private String waterBody;
     private String islandGroup;
     private String island;
     private String country;
     private String countryCode;
     private String stateProvince;
     private String county;
     private String municipality;
     private String locality;
     private String verbatimLocality;
     private String verbatimElevation;
     private String minimumElevationInMeters;
     private String maximumElevationInMeters;
     private String verbatimDepth;
     private String minimumDepthInMeters;
     private String maximumDepthInMeters;
     private String minimumDistanceAboveSurfaceInMeters;
     private String maximumDistanceAboveSurfaceInMeters;
     private String locationAccordingTo;
     private String locationRemarks;
     private String verbatimCoordinates;
     private String verbatimLatitude;
     private String verbatimLongitude;
     private String verbatimCoordinateSystem;
     private String verbatimSRS;
     private String decimalLatitude;
     private String decimalLongitude;
     private String geodeticDatum;
     private String coordinateUncertaintyInMeters;
     private String coordinatePrecision;
     private String pointRadiusSpatialFit;
     private String footprintWKT;
     private String footprintSRS;
     private String footprintSpatialFit;
     private String georeferencedBy;
     private String georeferencedDate;
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

    public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getHigherGeographyId() {
		return higherGeographyId;
	}

	public void setHigherGeographyId(String higherGeographyId) {
		this.higherGeographyId = higherGeographyId;
	}

	public String getHigherGeography() {
		return higherGeography;
	}

	public void setHigherGeography(String higherGeography) {
		this.higherGeography = higherGeography;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getWaterBody() {
		return waterBody;
	}

	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
	}

	public String getIslandGroup() {
		return islandGroup;
	}

	public void setIslandGroup(String islandGroup) {
		this.islandGroup = islandGroup;
	}

	public String getIsland() {
		return island;
	}

	public void setIsland(String island) {
		this.island = island;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getVerbatimLocality() {
		return verbatimLocality;
	}

	public void setVerbatimLocality(String verbatimLocality) {
		this.verbatimLocality = verbatimLocality;
	}

	public String getVerbatimElevation() {
		return verbatimElevation;
	}

	public void setVerbatimElevation(String verbatimElevation) {
		this.verbatimElevation = verbatimElevation;
	}

	public String getMinimumElevationInMeters() {
		return minimumElevationInMeters;
	}

	public void setMinimumElevationInMeters(String minimumElevationInMeters) {
		this.minimumElevationInMeters = minimumElevationInMeters;
	}

	public String getMaximumElevationInMeters() {
		return maximumElevationInMeters;
	}

	public void setMaximumElevationInMeters(String maximumElevationInMeters) {
		this.maximumElevationInMeters = maximumElevationInMeters;
	}

	public String getVerbatimDepth() {
		return verbatimDepth;
	}

	public void setVerbatimDepth(String verbatimDepth) {
		this.verbatimDepth = verbatimDepth;
	}

	public String getMinimumDepthInMeters() {
		return minimumDepthInMeters;
	}

	public void setMinimumDepthInMeters(String minimumDepthInMeters) {
		this.minimumDepthInMeters = minimumDepthInMeters;
	}

	public String getMaximumDepthInMeters() {
		return maximumDepthInMeters;
	}

	public void setMaximumDepthInMeters(String maximumDepthInMeters) {
		this.maximumDepthInMeters = maximumDepthInMeters;
	}

	public String getMinimumDistanceAboveSurfaceInMeters() {
		return minimumDistanceAboveSurfaceInMeters;
	}

	public void setMinimumDistanceAboveSurfaceInMeters(
			String minimumDistanceAboveSurfaceInMeters) {
		this.minimumDistanceAboveSurfaceInMeters = minimumDistanceAboveSurfaceInMeters;
	}

	public String getMaximumDistanceAboveSurfaceInMeters() {
		return maximumDistanceAboveSurfaceInMeters;
	}

	public void setMaximumDistanceAboveSurfaceInMeters(
			String maximumDistanceAboveSurfaceInMeters) {
		this.maximumDistanceAboveSurfaceInMeters = maximumDistanceAboveSurfaceInMeters;
	}

	public String getLocationAccordingTo() {
		return locationAccordingTo;
	}

	public void setLocationAccordingTo(String locationAccordingTo) {
		this.locationAccordingTo = locationAccordingTo;
	}

	public String getLocationRemarks() {
		return locationRemarks;
	}

	public void setLocationRemarks(String locationRemarks) {
		this.locationRemarks = locationRemarks;
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

	public String getVerbatimCoordinateSystem() {
		return verbatimCoordinateSystem;
	}

	public void setVerbatimCoordinateSystem(String verbatimCoordinateSystem) {
		this.verbatimCoordinateSystem = verbatimCoordinateSystem;
	}

	public String getVerbatimSRS() {
		return verbatimSRS;
	}

	public void setVerbatimSRS(String verbatimSRS) {
		this.verbatimSRS = verbatimSRS;
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

	public String getGeodeticDatum() {
		return geodeticDatum;
	}

	public void setGeodeticDatum(String geodeticDatum) {
		this.geodeticDatum = geodeticDatum;
	}

	public String getCoordinateUncertaintyInMeters() {
		return coordinateUncertaintyInMeters;
	}

	public void setCoordinateUncertaintyInMeters(
			String coordinateUncertaintyInMeters) {
		this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
	}

	public String getCoordinatePrecision() {
		return coordinatePrecision;
	}

	public void setCoordinatePrecision(String coordinatePrecision) {
		this.coordinatePrecision = coordinatePrecision;
	}

	public String getPointRadiusSpatialFit() {
		return pointRadiusSpatialFit;
	}

	public void setPointRadiusSpatialFit(String pointRadiusSpatialFit) {
		this.pointRadiusSpatialFit = pointRadiusSpatialFit;
	}

	public String getFootprintWKT() {
		return footprintWKT;
	}

	public void setFootprintWKT(String footprintWKT) {
		this.footprintWKT = footprintWKT;
	}

	public String getFootprintSRS() {
		return footprintSRS;
	}

	public void setFootprintSRS(String footprintSRS) {
		this.footprintSRS = footprintSRS;
	}

	public String getFootprintSpatialFit() {
		return footprintSpatialFit;
	}

	public void setFootprintSpatialFit(String footprintSpatialFit) {
		this.footprintSpatialFit = footprintSpatialFit;
	}

	public String getGeoreferencedBy() {
		return georeferencedBy;
	}

	public void setGeoreferencedBy(String georeferencedBy) {
		this.georeferencedBy = georeferencedBy;
	}

	public String getGeoreferencedDate() {
		return georeferencedDate;
	}

	public void setGeoreferencedDate(String georeferencedDate) {
		this.georeferencedDate = georeferencedDate;
	}

	public String getGeoreferenceProtocol() {
		return georeferenceProtocol;
	}

	public void setGeoreferenceProtocol(String georeferenceProtocol) {
		this.georeferenceProtocol = georeferenceProtocol;
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

	public void setGeoreferenceVerificationStatus(
			String georeferenceVerificationStatus) {
		this.georeferenceVerificationStatus = georeferenceVerificationStatus;
	}

	public String getGeoreferenceRemarks() {
		return georeferenceRemarks;
	}

	public void setGeoreferenceRemarks(String georeferenceRemarks) {
		this.georeferenceRemarks = georeferenceRemarks;
	}

	public List<GeoFeatureCDTO> getGeoFeaturesCDTO() {
        return geoFeaturesCDTO;
    }

    public void setGeoFeaturesCDTO(List<GeoFeatureCDTO> geoFeaturesCDTO) {
        this.geoFeaturesCDTO = geoFeaturesCDTO;
    }
    
}
