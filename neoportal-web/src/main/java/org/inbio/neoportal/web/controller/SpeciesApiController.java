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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.manager.impl.SpeciesManagerImpl;
import org.inbio.neoportal.web.dto.TaxonDescriptionFullWDTO;
import org.inbio.neoportal.web.dto.wrapper.XMLTaxonDescriptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Métodos para accesar a la información de especies 
 * como API retornando datos en xml
 * @author arturo
 */
@Controller
@RequestMapping("/api/species/*")
public class SpeciesApiController {
    
    @Autowired
    private SpeciesManagerImpl speciesManagerImpl;
    
    @RequestMapping (
            value = "/{scientificName}/{provider}",
            method = RequestMethod.GET)
    public @ResponseBody XMLTaxonDescriptionWrapper getTaxonDescriptionByProvider (
            @PathVariable(value = "scientificName") String scientificName,
            @PathVariable(value = "provider") String provider) {
        
        List<TaxonDescriptionFullSDTO> taxonDescription = null;
        
        XMLTaxonDescriptionWrapper tdw = new XMLTaxonDescriptionWrapper();
        
        try {
            
            scientificName = scientificName.replace('_', ' ');
            
            taxonDescription = 
                    speciesManagerImpl.taxonDescriptionByProvider(scientificName, provider);
            
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
            value = "/{scientificName}",
            method = RequestMethod.GET)
    public @ResponseBody XMLTaxonDescriptionWrapper getTaxonDescription (
            @PathVariable(value = "scientificName") String scientificName) {
        
        List<TaxonDescriptionFullSDTO> taxonDescription = null;
        
        XMLTaxonDescriptionWrapper tdw = new XMLTaxonDescriptionWrapper();
        
        try {
            
            scientificName = scientificName.replace('_', ' ');
            
            taxonDescription = 
                    speciesManagerImpl.taxonDescription(scientificName);
            
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
}
