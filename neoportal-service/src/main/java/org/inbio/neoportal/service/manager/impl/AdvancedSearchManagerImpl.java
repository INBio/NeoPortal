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
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.inbio.neoportal.core.dao.ColumnDefaultDAO;
import org.inbio.neoportal.core.dao.GeoLayerDAO;
import org.inbio.neoportal.core.dao.SearchColumnDAO;
import org.inbio.neoportal.core.dao.SearchFilterDAO;
import org.inbio.neoportal.core.dao.OccurrenceDAO;
import org.inbio.neoportal.core.dao.SearchGroupDAO;
import org.inbio.neoportal.core.dto.advancedsearch.ColumnDefaultCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoFeatureCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.GeoLayerCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchFilterCDTO;
import org.inbio.neoportal.core.dto.advancedsearch.SearchGroupCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceCDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.OccurrenceSDTO;
import org.inbio.neoportal.service.entity.AdvancedSearchData;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author avargas
 */
@Service
public class AdvancedSearchManagerImpl
            implements AdvancedSearchManager{
    
    @Autowired
    private SearchColumnDAO columnListDAO;
    
    @Autowired 
    private ColumnDefaultDAO columnDefaultDAO;

    @Autowired
    private SearchFilterDAO filterListDAO;
    
    @Autowired
    private OccurrenceDAO occurrenceDAO;    
    
    @Autowired
    private SearchGroupDAO searchGroupDAO; 
    
    @Autowired
    private GeoLayerDAO geoLayerDAO;

    @Override
    public List<SearchColumnCDTO> getAllColumns() {
        List<SearchColumnCDTO> clCDTO;
        
        clCDTO = columnListDAO.getAllSearchColumns();
        
        return clCDTO;
    }

    @Override
    public SearchColumnCDTO getColumnListByKey(String keyName) {
        SearchColumnCDTO clCDTO;
        
        clCDTO = columnListDAO.getSearchColumnByKey(keyName);
        
        return clCDTO;
    }
        
    @Override
    public List<ColumnDefaultCDTO> getColumnDefault(){
        List<ColumnDefaultCDTO> columnDefaultCDTO;
                
        columnDefaultCDTO = columnDefaultDAO.getAllColumns();
        
        return columnDefaultCDTO;
    }

    @Override
    public List<SearchFilterCDTO> getAllFilters() {
        List<SearchFilterCDTO> flCDTO;
                
        flCDTO = filterListDAO.getAllFilters();
        
        return flCDTO;
    }
    
    @Override
    public List<SearchGroupCDTO> getAllSearchGroup(){
        List<SearchGroupCDTO> sgCDTO;
        sgCDTO = searchGroupDAO.getAllSearchGroups();
        
        //prepare filters with their value list
        for (SearchGroupCDTO searchGroup: sgCDTO){
        
            for (SearchFilterCDTO searchFilterCDTO : searchGroup.getSearchFilterList()) {
                
                //add value list for combos
                if("combo".equals(searchFilterCDTO.getType())){
                    //load filter values
                    List<GeoLayerCDTO> geoLayers = 
                            geoLayerDAO.getGeoLayerByName(searchFilterCDTO.getFilterKey());

                    GeoLayerCDTO geoLayer = geoLayers.get(0);
                    
                    if(geoLayer != null){
                        List<String> geoFeaturesValues = new ArrayList<String>();
                        
                        //add all option
                        geoFeaturesValues.add("all");
                        for(GeoFeatureCDTO feature: geoLayer.getGeoFeatures()){
                            geoFeaturesValues.add(feature.getName());
                        }

                        searchFilterCDTO.setValues(geoFeaturesValues);
                    }
                }
            }
        }
        
        return sgCDTO;
    }
    
    @Override
    public List<OccurrenceSDTO> occurrencePaginatedSearch(
            FilterSDTO filters, 
            int offset, 
            int quantity) {
        
        //create search text base on filters
        String query = createQuery(filters);
        
        List<OccurrenceCDTO> occurrenceCDTO = 
                occurrenceDAO.advancedSearch(query, offset, quantity);
        
        //transform to OccurrenceSDTO 
        return occurrenceCDTOtoSDTO(occurrenceCDTO);
    }

    @Override
    public Long occurrenceSearchCount(FilterSDTO filters) {
        //create search text base on filters
        String query = createQuery(filters);
            
        return occurrenceDAO.searchCount(query);        
    }
    
    /**
     * 
     * @param filters
     * @return 
     */
    private String createQuery(FilterSDTO filters){
        String query = "";
        
        for(AdvancedSearchData groupData: filters.getFilterGroups()){
            
            // seudo switch throw filterGroups aka searchGroups
            if("taxonomic".equals(groupData.getKey())){
                for (LinkedHashMap filter : groupData.getFilters()) {
                    if(query.length() > 0)
                        query += " AND ";
            
                    // Taxon terms
                    if("taxon_name".equals(filter.get("key"))){
                        query += " (";
                        query += "defaultName: \"" + filter.get("value") + "\" OR ";
                        query += "kingdom: \"" + filter.get("value") + "\" OR ";
                        query += "division: \"" + filter.get("value") + "\" OR ";
                        query += "class_: \"" + filter.get("value") + "\" OR ";
                        query += "order: \"" + filter.get("value") + "\" OR ";
                        query += "family: \"" + filter.get("value") + "\" OR ";
                        query += "genus: \"" + filter.get("value") + "\" OR ";
                        query += "scientificName: \"" + filter.get("value") + "\") ";
                    }
                }
            }
            else if("geographic".equals(groupData.getKey())){
                for (LinkedHashMap filter : groupData.getFilters()) {
                    if ("all".equals(filter.get("value")))
                        continue;
                    
                    if(query.length() > 0)
                        query += " AND ";
                    
                    query += filter.get("key") + ":\"" + filter.get("value") + "\" ";
                }
            }
        }
        
        return query;
    }

    
    private List<OccurrenceSDTO> occurrenceCDTOtoSDTO(List<OccurrenceCDTO> occurrenceCDTOs){
        List<OccurrenceSDTO> result = new ArrayList<OccurrenceSDTO>();
        
        for(OccurrenceCDTO occurrence: occurrenceCDTOs){
            OccurrenceSDTO newOccurrence = new OccurrenceSDTO();
            
            /*  Occurrence Terms  */    
            newOccurrence.setOccurrenceId(occurrence.getOccurrenceId());
            newOccurrence.setCatalogNumber(occurrence.getCatalogNumber());
            newOccurrence.setRemarks(occurrence.getRemarks()); /* dwc = occurrenceRemarks */

            newOccurrence.setCollector(occurrence.getCollector());  /* dwc = recordedBy */
            newOccurrence.setSex(occurrence.getSex());
            newOccurrence.setLifeStage(occurrence.getLifeStage());

            /*  Taxon terms */
            newOccurrence.setTaxonId(occurrence.getTaxonId());
            newOccurrence.setScientificName(occurrence.getScientificName());
            newOccurrence.setAcceptedNameUsage(occurrence.getAcceptedNameUsage());
            newOccurrence.setParentNameUsage(occurrence.getParentNameUsage());
            newOccurrence.setOriginalNameUsage(occurrence.getOriginalNameUsage());
            newOccurrence.setNameAccordingTo(occurrence.getNameAccordingTo());
            newOccurrence.setNamePublishedIn(occurrence.getNamePublishedIn());
            newOccurrence.setNamePublishedInYear(occurrence.getNamePublishedInYear());
            newOccurrence.setHigherClassification(occurrence.getHigherClassification());
            newOccurrence.setKingdom(occurrence.getKingdom());
            newOccurrence.setPhylum(occurrence.getPhylum());  /*  dwc = phylum  */
            newOccurrence.setClass_(occurrence.getClass_());  /*  dwc = class  */
            newOccurrence.setOrder(occurrence.getOrder());
            newOccurrence.setFamily(occurrence.getFamily());
            newOccurrence.setGenus(occurrence.getGenus());
            newOccurrence.setSubgenus(occurrence.getSubgenus());
            newOccurrence.setSpecificEpithet(occurrence.getSpecificEpithet());
            newOccurrence.setInfraspecificEpithet(occurrence.getInfraspecificEpithet());
            newOccurrence.setInfraspecificRank(occurrence.getInfraspecificRank());   /*  dwc = taxonRank  */
            newOccurrence.setVerbatimTaxonRank(occurrence.getVerbatimTaxonRank());
            newOccurrence.setScientificNameAuthorship(occurrence.getScientificNameAuthorship());
            newOccurrence.setVernacularName(occurrence.getVernacularName());
            newOccurrence.setNomenclaturalCode(occurrence.getNomenclaturalCode());
            newOccurrence.setTaxonomicStatus(occurrence.getTaxonomicStatus());
            newOccurrence.setNomenclaturalStatus(occurrence.getNomenclaturalStatus());
            newOccurrence.setTaxonRemarks(occurrence.getTaxonRemarks());

            HashMap<String, String> geoFeatures = new HashMap<String, String>();
            if(occurrence.getLocation() != null){
                for(GeoFeatureCDTO feature: occurrence.getLocation().getGeoFeaturesCDTO()){
                    geoFeatures.put(feature.getGeoLayerName(), feature.getName());
                }
            }
            
            newOccurrence.setProperties(geoFeatures);
            
            result.add(newOccurrence);
        }
        
        return result;        
    }
}
