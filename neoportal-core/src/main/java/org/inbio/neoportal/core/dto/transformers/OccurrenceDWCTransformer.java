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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.LocationCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.entity.GeoFeature;
import org.inbio.neoportal.core.entity.Occurrence;
import org.inbio.neoportal.core.entity.Location;
import org.inbio.neoportal.core.entity.OccurrenceDwc;

/**
 * Transfrom a list of Occurrences entities to OccurrenceDwcCDTO
 * @author avargas
 */
public class OccurrenceDWCTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
        List<OccurrenceDwc> occurrenceList = (List<OccurrenceDwc>) list;
        
        List<OccurrenceDwcCDTO> newList = new ArrayList<OccurrenceDwcCDTO>();
        

       for(OccurrenceDwc oc: occurrenceList){
    	   OccurrenceDwcCDTO newCDTO = new OccurrenceDwcCDTO();
            newCDTO.setType(oc.getType());
            newCDTO.setModified(oc.getModified());
            newCDTO.setLanguage(oc.getLanguage());
            newCDTO.setRights(oc.getRights());
            newCDTO.setRightsHolder(oc.getRightsHolder());
            newCDTO.setAccessRights(oc.getAccessRights());
            newCDTO.setBibliographicCitation(oc.getBibliographicCitation());
            newCDTO.setReferences(oc.getReferences());
            newCDTO.setInstitutionId(oc.getInstitutionId());
            newCDTO.setCollectionId(oc.getCollectionId());
            newCDTO.setDatasetId(oc.getDatasetId());
            newCDTO.setInstitutionCode(oc.getInstitutionCode());
            newCDTO.setCollectionCode(oc.getCollectionCode());
            newCDTO.setDatasetName(oc.getDatasetName());
            newCDTO.setOwnerInstitutionCode(oc.getOwnerInstitutionCode());
            newCDTO.setBasisOfRecord(oc.getBasisOfRecord());
            newCDTO.setInformationWithheld(oc.getInformationWithheld());
            newCDTO.setDataGeneralizations(oc.getDataGeneralizations());
            newCDTO.setDynamicProperties(oc.getDynamicProperties());
            
    	   	newCDTO.setOccurrenceId(oc.getOccurrenceId().toString());
            newCDTO.setCatalogNumber(oc.getCatalogNumber());
            newCDTO.setOccurrenceRemarks(oc.getOccurrenceRemarks());
            newCDTO.setRecordNumber(oc.getRecordNumber());
            newCDTO.setRecordedBy(oc.getRecordedBy());
            newCDTO.setIndividualId(oc.getIndividualId());
            newCDTO.setIndividualCount(oc.getIndividualCount());
            newCDTO.setSex(oc.getSex());
            newCDTO.setLifeStage(oc.getLifeStage());
            newCDTO.setReproductiveCondition(oc.getReproductiveCondition());
            newCDTO.setBehavior(oc.getBehavior());
            newCDTO.setEstablishmentMeans(oc.getEstablishmentMeans());
            newCDTO.setOccurrenceStatus(oc.getOccurrenceStatus());
            newCDTO.setPreparations(oc.getPreparations());
            newCDTO.setDisposition(oc.getDisposition());
            newCDTO.setOtherCatalogNumbers(oc.getOtherCatalogNumbers());
            newCDTO.setPreviousIdentifications(oc.getPreviousIdentifications());
            newCDTO.setAssociatedMedia(oc.getAssociatedMedia());
            newCDTO.setAssociatedReferences(oc.getAssociatedReferences());
            newCDTO.setAssociatedOccurrences(oc.getAssociatedOccurrences());
            newCDTO.setAssociatedSequences(oc.getAssociatedSequences());
            newCDTO.setAssociatedTaxa(oc.getAssociatedTaxa());
            
            newCDTO.setEventId(oc.getEventId());
            newCDTO.setSamplingProtocol(oc.getSamplingProtocol());
            newCDTO.setSamplingEffort(oc.getSamplingEffort());
            newCDTO.setEventDate(oc.getEventDate());
            newCDTO.setEventTime(oc.getEventTime());
            newCDTO.setStartDayOfYear(oc.getStartDayOfYear());
            newCDTO.setEndDayOfYear(oc.getEndDayOfYear());
            newCDTO.setYear(oc.getYear());
            newCDTO.setMonth(oc.getMonth());
            newCDTO.setDay(oc.getDay());
            newCDTO.setVerbatimEventDate(oc.getVerbatimEventDate());
            newCDTO.setHabitat(oc.getHabitat());
            newCDTO.setFieldNotes(oc.getFieldNumber());
            newCDTO.setFieldNotes(oc.getFieldNotes());
            newCDTO.setEventRemarks(oc.getEventRemarks());
            
          //location information
            Location location = oc.getLocation();
            newCDTO.setLocationId(location.getLocationId().toString());
            newCDTO.setHigherGeographyId(location.getHigherGeographyId());
            newCDTO.setHigherGeography(location.getHigherGeography());
            newCDTO.setContinent(location.getContinent());
            newCDTO.setWaterBody(location.getWaterBody());
            newCDTO.setIslandGroup(location.getIslandGroup());
            newCDTO.setIsland(location.getIsland());
            newCDTO.setCountry(location.getCountry());
            newCDTO.setCountryCode(location.getCountryCode());
            newCDTO.setStateProvince(location.getStateProvince());
            newCDTO.setCounty(location.getCounty());
            newCDTO.setMunicipality(location.getMunicipality());
            newCDTO.setLocality(location.getLocality());
            newCDTO.setVerbatimLocality(location.getVerbatimLocality());
            newCDTO.setVerbatimElevation(location.getVerbatimElevation());
            newCDTO.setMinimumElevationInMeters(location.getMinimumElevationInMeters());
            newCDTO.setMaximumElevationInMeters(location.getMaximumElevationInMeters());
            newCDTO.setVerbatimDepth(location.getVerbatimDepth());
            newCDTO.setMinimumDepthInMeters(location.getMinimumDepthInMeters());
            newCDTO.setMaximumDepthInMeters(location.getMaximumDepthInMeters());
            newCDTO.setMinimumDistanceAboveSurfaceInMeters(location.getMinimumDistanceAboveSurfaceInMeters());
            newCDTO.setMaximumDistanceAboveSurfaceInMeters(location.getMaximumDistanceAboveSurfaceInMeters());
            newCDTO.setLocationAccordingTo(location.getLocationAccordingTo());
            newCDTO.setLocationRemarks(location.getLocationRemarks());
            newCDTO.setVerbatimCoordinates(location.getVerbatimCoordinates());
            newCDTO.setVerbatimLatitude(location.getVerbatimLatitude());
            newCDTO.setVerbatimLongitude(location.getVerbatimLongitude());
            newCDTO.setVerbatimCoordinateSystem(location.getVerbatimCoordinateSystem());
            newCDTO.setVerbatimSRS(location.getVerbatimSRS());
            newCDTO.setDecimalLatitude(location.getDecimalLatitude());
            newCDTO.setDecimalLongitude(location.getDecimalLongitude());
            newCDTO.setGeodeticDatum(location.getGeodeticDatum());
            newCDTO.setCoordinateUncertaintyInMeters(location.getCoordinateUncertaintyInMeters());
            newCDTO.setCoordinatePrecision(location.getCoordinatePrecision());
            newCDTO.setPointRadiusSpatialFit(location.getPointRadiusSpatialFit());
            newCDTO.setFootprintWKT(location.getFootprintWKT());
            newCDTO.setFootprintSRS(location.getFootprintSRS());
            newCDTO.setFootprintSpatialFit(location.getFootprintSpatialFit());
            newCDTO.setGeoreferencedBy(location.getGeoreferencedBy());
            newCDTO.setGeoreferencedDate(location.getGeoreferencedDate());
            newCDTO.setGeoreferenceProtocol(location.getGeoreferenceProtocol());
            newCDTO.setGeoreferenceSources(location.getGeoreferenceSources());
            newCDTO.setGeoreferenceVerificationStatus(location.getGeoreferenceVerificationStatus());
            newCDTO.setGeoreferenceRemarks(location.getGeoreferenceRemarks());
            
            newCDTO.setGeologicalContextId(oc.getGeologicalContextId());
            newCDTO.setEarliestEonOrLowestEonothem(oc.getEarliestEonOrLowestEonothem());
            newCDTO.setLatestEonOrHighestEonothem(oc.getLatestEonOrHighestEonothem());
            newCDTO.setEarliestEraOrLowestErathem(oc.getEarliestEraOrLowestErathem());
            newCDTO.setLatestEraOrHighestErathem(oc.getLatestEraOrHighestErathem());
            newCDTO.setEarliestPeriodOrLowestSystem(oc.getEarliestPeriodOrLowestSystem());
            newCDTO.setLatestPeriodOrHighestSystem(oc.getLatestPeriodOrHighestSystem());
            newCDTO.setEarliestEpochOrLowestSeries(oc.getEarliestEpochOrLowestSeries());
            newCDTO.setLatestEpochOrHighestSeries(oc.getLatestEpochOrHighestSeries());
            newCDTO.setEarliestAgeOrLowestStage(oc.getEarliestAgeOrLowestStage());
            newCDTO.setLatestAgeOrHighestStage(oc.getLatestAgeOrHighestStage());
            newCDTO.setLowestBiostratigraphicZone(oc.getLowestBiostratigraphicZone());
            newCDTO.setHighestBiostratigraphicZone(oc.getHighestBiostratigraphicZone());
            newCDTO.setLithostratigraphicTerms(oc.getLithostratigraphicTerms());
            newCDTO.setGroup(oc.getGroup());
            newCDTO.setFormation(oc.getFormation());
            newCDTO.setMember(oc.getMember());
            newCDTO.setBed(oc.getBed());
            
            newCDTO.setIdentificationId(oc.getIdentificationId());
            newCDTO.setIdentifiedBy(oc.getIdentifiedBy());
            newCDTO.setDateIdentified(oc.getDateIdentified().toString());
            newCDTO.setIdentificationReferences(oc.getIdentificationReferences());
            newCDTO.setIdentificationVerificationStatus(oc.getIdentificationVerificationStatus());
            newCDTO.setIdentificationRemarks(oc.getIdentificationRemarks());
            newCDTO.setIdentificationQualifier(oc.getIdentificationQualifier());
            newCDTO.setTypeStatus(oc.getTypeStatus());
            
            newCDTO.setTaxonId(oc.getTaxonId());
            newCDTO.setScientificNameId(oc.getScientificNameId());
            newCDTO.setAcceptedNameUsageId(oc.getAcceptedNameUsageId());
            newCDTO.setParentNameUsageId(oc.getParentNameUsageId());
            newCDTO.setOriginalNameUsageId(oc.getOriginalNameUsageId());
            newCDTO.setNameAccordingToId(oc.getNameAccordingToId());
            newCDTO.setNamePublishedInId(oc.getNamePublishedInId());
            newCDTO.setTaxonConceptId(oc.getTaxonConceptId());
            newCDTO.setScientificName(oc.getScientificName());
            newCDTO.setAcceptedNameUsage(oc.getAcceptedNameUsage());
            newCDTO.setParentNameUsage(oc.getParentNameUsage());
            newCDTO.setOriginalNameUsage(oc.getOriginalNameUsage());
            newCDTO.setNameAccordingTo(oc.getNameAccordingTo());
            newCDTO.setNamePublishedIn(oc.getNamePublishedIn());
            newCDTO.setNamePublishedInYear(oc.getNamePublishedInYear());
            newCDTO.setHigherClassification(oc.getHigherClassification());
            newCDTO.setKingdom(oc.getKingdom());
            newCDTO.setPhylum(oc.getPhylum());
            newCDTO.setClass_(oc.getClass_());
            newCDTO.setOrder(oc.getOrder());
            newCDTO.setFamily(oc.getFamily());
            newCDTO.setGenus(oc.getGenus());
            newCDTO.setSubgenus(oc.getSubgenus());
            newCDTO.setSpecificEpithet(oc.getSpecificEpithet());
            newCDTO.setInfraspecificEpithet(oc.getInfraspecificEpithet());
            newCDTO.setTaxonRank(oc.getTaxonRank());
            newCDTO.setVerbatimTaxonRank(oc.getVerbatimTaxonRank());
            newCDTO.setScientificNameAuthorship(oc.getScientificNameAuthorship());
            newCDTO.setVernacularName(oc.getVernacularName());
            newCDTO.setNomenclaturalCode(oc.getNomenclaturalCode());
            newCDTO.setTaxonomicStatus(oc.getTaxonomicStatus());
            newCDTO.setNomenclaturalStatus(oc.getNomenclaturalStatus());
            newCDTO.setTaxonRemarks(oc.getTaxonRemarks());
            
            //aditional properties
            HashMap<String, String> properties = new HashMap<String, String>();
            Iterator<?> itr = location.getFeatures().iterator();
            while (itr.hasNext()) {
                GeoFeature geoFeature = (GeoFeature) itr.next();
                properties.put(geoFeature.getGeoLayer().getName(), geoFeature.getName());
            }
            newCDTO.setProperties(properties);
            
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
        locationCDTO.setHigherGeographyId(location.getHigherGeographyId());
        locationCDTO.setHigherGeography(location.getHigherGeography());
        locationCDTO.setContinent(location.getContinent());
        locationCDTO.setWaterBody(location.getWaterBody());
        locationCDTO.setIslandGroup(location.getIslandGroup());
        locationCDTO.setIsland(location.getIsland());
        locationCDTO.setCountry(location.getCountry());
        locationCDTO.setCountryCode(location.getCountryCode());
        locationCDTO.setStateProvince(location.getStateProvince());
        locationCDTO.setCounty(location.getCounty());
        locationCDTO.setMunicipality(location.getMunicipality());
        locationCDTO.setLocality(location.getLocality());
        locationCDTO.setVerbatimLocality(location.getVerbatimLocality());
        locationCDTO.setVerbatimElevation(location.getVerbatimElevation());
        locationCDTO.setMinimumElevationInMeters(location.getMinimumElevationInMeters());
        locationCDTO.setMaximumElevationInMeters(location.getMaximumElevationInMeters());
        locationCDTO.setVerbatimDepth(location.getVerbatimDepth());
        locationCDTO.setMinimumDepthInMeters(location.getMinimumDepthInMeters());
        locationCDTO.setMaximumDepthInMeters(location.getMaximumDepthInMeters());
        locationCDTO.setMinimumDistanceAboveSurfaceInMeters(location.getMinimumDistanceAboveSurfaceInMeters());
        locationCDTO.setMaximumDistanceAboveSurfaceInMeters(location.getMaximumDistanceAboveSurfaceInMeters());
        locationCDTO.setLocationAccordingTo(location.getLocationAccordingTo());
        locationCDTO.setLocationRemarks(location.getLocationRemarks());
        locationCDTO.setVerbatimCoordinates(location.getVerbatimCoordinates());
        locationCDTO.setVerbatimLatitude(location.getVerbatimLatitude());
        locationCDTO.setVerbatimLongitude(location.getVerbatimLongitude());
        locationCDTO.setVerbatimCoordinateSystem(location.getVerbatimCoordinateSystem());
        locationCDTO.setVerbatimSRS(location.getVerbatimSRS());
        locationCDTO.setDecimalLatitude(location.getDecimalLatitude());
        locationCDTO.setDecimalLongitude(location.getDecimalLongitude());
        locationCDTO.setGeodeticDatum(location.getGeodeticDatum());
        locationCDTO.setCoordinateUncertaintyInMeters(location.getCoordinateUncertaintyInMeters());
        locationCDTO.setCoordinatePrecision(location.getCoordinatePrecision());
        locationCDTO.setPointRadiusSpatialFit(location.getPointRadiusSpatialFit());
        locationCDTO.setFootprintWKT(location.getFootprintWKT());
        locationCDTO.setFootprintSRS(location.getFootprintSRS());
        locationCDTO.setFootprintSpatialFit(location.getFootprintSpatialFit());
        locationCDTO.setGeoreferencedBy(location.getGeoreferencedBy());
        locationCDTO.setGeoreferencedDate(location.getGeoreferencedDate());
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
