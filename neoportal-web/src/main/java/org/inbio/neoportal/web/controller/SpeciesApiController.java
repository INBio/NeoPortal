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
package org.inbio.neoportal.web.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.inbio.neoportal.core.dto.advancedsearch.SearchColumnCDTO;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.entity.TaxonPlic;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.manager.AdvancedSearchManager;
import org.inbio.neoportal.service.manager.SpeciesManager;
import org.inbio.neoportal.web.dto.TaxonDescriptionFullWDTO;
import org.inbio.neoportal.web.dto.wrapper.XMLTaxonDescriptionWrapper;
import org.inbio.neoportal.web.view.CSVview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Métodos para accesar a la información de especies 
 * como API retornando datos en xml
 * @author arturo
 */
@Controller
@RequestMapping("/api/species/*")
public class SpeciesApiController {
    
    @Autowired
    private SpeciesManager speciesManager;
    
    @Autowired
    private AdvancedSearchManager advancedSearchManager;
    
    @RequestMapping (
            value = "/{scientificName}/{provider}",
            method = RequestMethod.GET,
            headers = "Accept=application/xml")
    public @ResponseBody XMLTaxonDescriptionWrapper getTaxonDescriptionByProvider (
            @PathVariable(value = "scientificName") String scientificName,
            @PathVariable(value = "provider") String provider) {
        
        List<TaxonDescriptionFullSDTO> taxonDescription = null;
        
        XMLTaxonDescriptionWrapper tdw = new XMLTaxonDescriptionWrapper();
        
        try {
            
            scientificName = scientificName.replace('_', ' ');
            
            taxonDescription = 
                    speciesManager.taxonDescriptionByProvider(scientificName, provider);
            
            for(TaxonDescriptionFullSDTO tdsdto : taxonDescription) {
                tdw.addElement(new TaxonDescriptionFullWDTO (
                        tdsdto.getScientificName(),
                        tdsdto.getInstitutionCode(),
                        tdsdto.getDateLastModified(),
                        tdsdto.getTaxonRecordId(),
                        tdsdto.getLanguage(),
                        tdsdto.getCreators(),
                        tdsdto.getDistribution(),
                        tdsdto.getAbstract_(),
                        tdsdto.getKingdomTaxon(),
                        tdsdto.getPhylumTaxon(),
                        tdsdto.getClassTaxon(),
                        tdsdto.getOrderTaxon(),
                        tdsdto.getFamilyTaxon(),
                        tdsdto.getGenusTaxon(),
                        tdsdto.getSynonyms(),
                        tdsdto.getAuthorYearOfScientificName(),
                        tdsdto.getSpeciesPublicationReference(),
                        tdsdto.getCommonNames(),
                        tdsdto.getTypification(),
                        tdsdto.getContributors(),
                        tdsdto.getDateCreated(),
                        tdsdto.getHabit(),
                        tdsdto.getLifeCycle(),
                        tdsdto.getReproduction(),
                        tdsdto.getAnnualCycle(),
                        tdsdto.getScientificDescription(),
                        tdsdto.getBriefDescription(),
                        tdsdto.getFeeding(),
                        tdsdto.getBehavior(),
                        tdsdto.getInteractions(),
                        tdsdto.getChromosomicNumberN(),
                        tdsdto.getMolecularData(),
                        tdsdto.getPopulationBiology(),
                        tdsdto.getThreatStatus(),
                        tdsdto.getLegislation(),
                        tdsdto.getHabitat(),
                        tdsdto.getTerritory(),
                        tdsdto.getEndemicity(),
                        tdsdto.getTheUses(),
                        tdsdto.getTheManagement(),
                        tdsdto.getFolklore(),
                        tdsdto.getTheReferences(),
                        tdsdto.getUnstructuredDocumentation(),
                        tdsdto.getOtherInformationSources(),
                        tdsdto.getPapers(),
                        tdsdto.getIdentificationKeys(),
                        tdsdto.getMigratoryData(),
                        tdsdto.getEcologicalSignificance(),
                        tdsdto.getUnstructuredNaturalHistory(),
                        tdsdto.getInvasivenessData(),
                        tdsdto.getTargetAudiences()));
            }
            
        } catch (Exception e) {
            Logger.getLogger(
                SpeciesController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return tdw;
    }
    
    @RequestMapping (
            value = "/{scientificName}/{provider}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public @ResponseBody Object getTaxonDescriptionByProviderJson (
            @PathVariable(value = "scientificName") String scientificName,
            @PathVariable(value = "provider") String provider) {
        
        List<TaxonDescriptionFullSDTO> taxonDescription = null;
        LinkedHashMap<String, LinkedHashMap> data = new LinkedHashMap<String, LinkedHashMap>();
        
        try {
            
            scientificName = scientificName.replace('_', ' ');
            
            taxonDescription = 
                    speciesManager.taxonDescriptionByProvider(scientificName, provider);
            
            // TODO: fix non data scenary
            if(taxonDescription.isEmpty())
                return null;
            
            TaxonDescriptionFullSDTO taxon = taxonDescription.get(0);
            
            //transforme SDTO to species record form
            // -- Natural History --
            LinkedHashMap<String, String> naturalHistory = new LinkedHashMap<String, String>();
            naturalHistory.put("habit", taxon.getHabit());
            naturalHistory.put("reproduction", taxon.getReproduction());
            naturalHistory.put("feeding", taxon.getFeeding());
            naturalHistory.put("behavior", taxon.getBehavior());
            naturalHistory.put("annualCycle", taxon.getAnnualCycle());
            naturalHistory.put("lifeCycle", taxon.getLifeCycle());
            //naturalHistory.put("phenology", taxon);
            
            LinkedHashMap<String, String> habitatDistribution = new LinkedHashMap<String, String>();
            habitatDistribution.put("habitat", taxon.getHabitat());
            habitatDistribution.put("distribution", taxon.getDistribution());
            
            LinkedHashMap<String, String> demographyConservation = new LinkedHashMap<String, String>();
            demographyConservation.put("threatStatus", taxon.getThreatStatus());
            demographyConservation.put("territory", taxon.getTerritory());
            demographyConservation.put("populationBiology", taxon.getPopulationBiology());
            
            LinkedHashMap<String, String> usesManagement = new LinkedHashMap<String, String>();
            usesManagement.put("uses", taxon.getTheUses());
            usesManagement.put("management", taxon.getTheManagement());
            
            LinkedHashMap<String, String> description = new LinkedHashMap<String, String>();
            description.put("scientificDescription", taxon.getScientificDescription());
            description.put("briefDescription", taxon.getBriefDescription());
            
            LinkedHashMap<String, String> information = new LinkedHashMap<String, String>();
            information.put("language", taxon.getLanguage());
            information.put("author", taxon.getCreators());
            information.put("contributors", taxon.getContributors());
            information.put("taxonRecordId", taxon.getTaxonRecordId());
            information.put("dateLastModified", taxon.getDateLastModified());
            information.put("dateCreated", taxon.getDateCreated());
            
            //wrap all the data
            data.put("naturalHistory", naturalHistory);
            data.put("habitatDistribution", habitatDistribution);
            data.put("demographyConservation", demographyConservation);
            data.put("usesManagement", usesManagement);
            data.put("description", description);
            data.put("information", information);
            
        } catch (Exception e) {
            Logger.getLogger(
                SpeciesController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return data;
    }
    
    @RequestMapping (
    		value = {"/{defaultName}/{language}"}, 
              method = RequestMethod.GET,
              produces = {"application/xml", "application/json"})
    @ResponseBody
    public Object getTaxonDescription (
            @PathVariable(value = "defaultName") String scientificName ,
            @PathVariable(value = "language") String language) {
      scientificName = scientificName.replace('_', ' ');
      TaxonPlic taxonPlic ;
     
     
      taxonPlic = speciesManager.getTaxonPLicByDefaultName(scientificName,language); 
      
      HttpHeaders headers = new HttpHeaders();
      headers.add("Access-Control-Allow-Origin", "*");
      return new ResponseEntity<TaxonPlic>(taxonPlic, headers, HttpStatus.OK);
    }
    
    @RequestMapping (
    		value = {"/{defaultName}"}, 
              method = RequestMethod.GET,
              produces = {"application/xml", "application/json"})
    @ResponseBody
    public Object getTaxonDescription (
            @PathVariable(value = "defaultName") String scientificName )
             {
      scientificName = scientificName.replace('_', ' ');
      TaxonPlic taxonPlic ;
      
      taxonPlic = speciesManager.getTaxonPLicByDefaultName(scientificName,"Español"); 
    
      HttpHeaders headers = new HttpHeaders();
      headers.add("Access-Control-Allow-Origin", "*");
      return new ResponseEntity<TaxonPlic>(taxonPlic, headers, HttpStatus.OK);
    }
    
    
    
    
    
    
    
//    @RequestMapping (
//            value = "/{scientificName}",
//            method = RequestMethod.GET)
//    public @ResponseBody XMLTaxonDescriptionWrapper getTaxonDescription (
//            @PathVariable(value = "scientificName") String scientificName) {
//        
//        List<TaxonDescriptionFullSDTO> taxonDescription = null;
//        
//        XMLTaxonDescriptionWrapper tdw = new XMLTaxonDescriptionWrapper();
//        
//        try {
//            
//            scientificName = scientificName.replace('_', ' ');
//            
//            taxonDescription = 
//                    speciesManager.taxonDescription(scientificName);
//            
//            for(TaxonDescriptionFullSDTO tdsdto : taxonDescription) {
//                tdw.addElement(new TaxonDescriptionFullWDTO (
//                        tdsdto.getScientificName(),
//                        tdsdto.getInstitutionCode(),
//                        tdsdto.getDateLastModified(),
//                        tdsdto.getTaxonRecordId(),
//                        tdsdto.getLanguage(),
//                        tdsdto.getCreators(),
//                        tdsdto.getDistribution(),
//                        tdsdto.getAbstract_(),
//                        tdsdto.getKingdomTaxon(),
//                        tdsdto.getPhylumTaxon(),
//                        tdsdto.getClassTaxon(),
//                        tdsdto.getOrderTaxon(),
//                        tdsdto.getFamilyTaxon(),
//                        tdsdto.getGenusTaxon(),
//                        tdsdto.getSynonyms(),
//                        tdsdto.getAuthorYearOfScientificName(),
//                        tdsdto.getSpeciesPublicationReference(),
//                        tdsdto.getCommonNames(),
//                        tdsdto.getTypification(),
//                        tdsdto.getContributors(),
//                        tdsdto.getDateCreated(),
//                        tdsdto.getHabit(),
//                        tdsdto.getLifeCycle(),
//                        tdsdto.getReproduction(),
//                        tdsdto.getAnnualCycle(),
//                        tdsdto.getScientificDescription(),
//                        tdsdto.getBriefDescription(),
//                        tdsdto.getFeeding(),
//                        tdsdto.getBehavior(),
//                        tdsdto.getInteractions(),
//                        tdsdto.getChromosomicNumberN(),
//                        tdsdto.getMolecularData(),
//                        tdsdto.getPopulationBiology(),
//                        tdsdto.getThreatStatus(),
//                        tdsdto.getLegislation(),
//                        tdsdto.getHabitat(),
//                        tdsdto.getTerritory(),
//                        tdsdto.getEndemicity(),
//                        tdsdto.getTheUses(),
//                        tdsdto.getTheManagement(),
//                        tdsdto.getFolklore(),
//                        tdsdto.getTheReferences(),
//                        tdsdto.getUnstructuredDocumentation(),
//                        tdsdto.getOtherInformationSources(),
//                        tdsdto.getPapers(),
//                        tdsdto.getIdentificationKeys(),
//                        tdsdto.getMigratoryData(),
//                        tdsdto.getEcologicalSignificance(),
//                        tdsdto.getUnstructuredNaturalHistory(),
//                        tdsdto.getInvasivenessData(),
//                        tdsdto.getTargetAudiences()));
//            }
//            
//        } catch (Exception e) {
//            Logger.getLogger(
//                SpeciesController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        
//        return tdw;
//    }
    
    @RequestMapping(
            value="/{scientificName}/images",
            method= RequestMethod.GET,
            headers = "Accept=application/json")
    public @ResponseBody Object getImages(
            @PathVariable(value = "scientificName") String scientificName){
        
    	// FIXME: call the appropriate function
//        return speciesManager.getImagesByDefaultName(scientificName);
    	return null;
    }
    
    @RequestMapping (
            value = "/{defaultName}/occurrences/export",
            method = RequestMethod.POST
            )
        public ModelAndView advancedSearchOccurrencesCSV (
        		@PathVariable(value = "defaultName") String defaultName
           ){

    		ModelAndView mav = new ModelAndView(new CSVview());
    		List<OccurrenceDwcCDTO> occurrenceCDTO;
    		int totalOccurrences;
    		List<String> columns;
    		
    		// get all occurrences for the giving defaultName
    		totalOccurrences = speciesManager.countOccurrencesByDefaultName(defaultName).intValue();
    		occurrenceCDTO = speciesManager.getOccurrencesByDefaultName(defaultName, 0, totalOccurrences);

    		// get all columns
    		List<SearchColumnCDTO> columnList = advancedSearchManager.getAllColumns();
    		
    		//get columns selected by the user
    		columns = new ArrayList<String>();
    		for (SearchColumnCDTO searchColumnCDTO : columnList) {
				columns.add(searchColumnCDTO.getColumnKey());
			}
    		
            mav.addObject("data", occurrenceCDTO);
            mav.addObject("columns", columns);
    		
    		return mav;
        }
}
