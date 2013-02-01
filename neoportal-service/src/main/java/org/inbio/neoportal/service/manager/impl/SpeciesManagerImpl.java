/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inbio.neoportal.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dao.TaxonDescriptionDAO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.manager.SpeciesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author avargas
 */
@Service
public class SpeciesManagerImpl
        implements SpeciesManager {

    @Autowired
    private TaxonDescriptionDAO taxonDescriptionDAO;
    
    @Autowired
    private TaxonDAO taxonDAO;
    
    @Override
    public List<TaxonDescriptionFullSDTO> taxonDescriptionByProvider
            (String scientificName, String provider) {
        
        List<TaxonDescriptionFullCDTO> taxonDescription = new ArrayList<TaxonDescriptionFullCDTO>();
        
        List<TaxonDescriptionFullSDTO> result = new ArrayList<TaxonDescriptionFullSDTO>();
        
        taxonDescription = taxonDescriptionDAO.findAllByScientificName(scientificName, provider);
        
        for(TaxonDescriptionFullCDTO tfdto: taxonDescription) {
            TaxonDescriptionFullSDTO td = new TaxonDescriptionFullSDTO();
            
            td.setAnnualCycle(tfdto.getAnnualCycle());
            td.setAuthorYearOfScientificName(tfdto.getAuthorYearOfScientificName());
            td.setBehavior(tfdto.getBehavior());
            td.setBriefDescription(tfdto.getBriefDescription());
            td.setChromosomicNumberN(tfdto.getChromosomicNumberN());
            td.setClassTaxon(tfdto.getClassTaxon());
            td.setCommonNames(tfdto.getCommonNames());
            td.setContributors(tfdto.getContributors());
            td.setCreators(tfdto.getCreators());
            td.setDateCreated(tfdto.getDateCreated());
            td.setDateLastModified(tfdto.getDateLastModified());
            td.setDistribution(tfdto.getDistribution());
            td.setEcologicalSignificance(tfdto.getEcologicalSignificance());
            td.setEndemicity(tfdto.getEndemicity());
            td.setFamilyTaxon(tfdto.getFamilyTaxon());
            td.setFeeding(tfdto.getFeeding());
            td.setFolklore(tfdto.getFolklore());
            td.setGenusTaxon(tfdto.getGenusTaxon());                        
            td.setHabit(tfdto.getHabit());
            td.setHabitat(tfdto.getHabitat());
            td.setIdentificationKeys(tfdto.getIdentificationKeys());
            td.setInstitutionCode(tfdto.getInstitutionCode());
            td.setInteractions(tfdto.getInteractions());
            td.setInvasivenessData(tfdto.getInvasivenessData());
            td.setKingdomTaxon(tfdto.getKingdomTaxon());
            td.setLanguage(tfdto.getLanguage());
            td.setLegislation(tfdto.getLegislation());
            td.setLifeCycle(tfdto.getLifeCycle());
            td.setMigratoryData(tfdto.getMigratoryData());
            td.setMolecularData(tfdto.getMolecularData());
            td.setOrderTaxon(tfdto.getOrderTaxon());
            td.setOtherInformationSources(tfdto.getOtherInformationSources());
            td.setPapers(tfdto.getPapers());
            td.setPhylumTaxon(tfdto.getPhylumTaxon());
            td.setPopulationBiology(tfdto.getPopulationBiology());
            td.setReproduction(tfdto.getReproduction());
            td.setScientificDescription(tfdto.getScientificDescription());
            td.setScientificName(scientificName);
            td.setSpeciesPublicationReference(tfdto.getSpeciesPublicationReference());
            td.setSynonyms(tfdto.getSynonyms());
            td.setTargetAudiences(tfdto.getTargetAudiences());
            td.setTaxonRecordId(tfdto.getTaxonRecordId());
            td.setTerritory(tfdto.getTerritory());
            td.setTheManagement(tfdto.getTheManagement());
            td.setTheReferences(tfdto.getTheReferences());
            td.setTheUses(tfdto.getTheUses());
            td.setThreatStatus(tfdto.getThreatStatus());
            td.setTypification(tfdto.getTypification());
            td.setUnstructuredDocumentation(tfdto.getUnstructuredDocumentation());
            td.setUnstructuredNaturalHistory(tfdto.getUnstructuredNaturalHistory());
            td.setUsername(tfdto.getUsername());
            
            result.add(td);
        }
        
        return result;
    }

    public List<TaxonDescriptionFullSDTO> taxonDescription(String scientificName) {
        List<TaxonDescriptionFullCDTO> taxonDescription = new ArrayList<TaxonDescriptionFullCDTO>();
        
        List<TaxonDescriptionFullSDTO> result = new ArrayList<TaxonDescriptionFullSDTO>();
        
        taxonDescription = taxonDescriptionDAO.findAllByScientificName(scientificName);
        
        for(TaxonDescriptionFullCDTO tfdto: taxonDescription) {
            TaxonDescriptionFullSDTO td = new TaxonDescriptionFullSDTO();
            
            td.setAnnualCycle(tfdto.getAnnualCycle());
            td.setAuthorYearOfScientificName(tfdto.getAuthorYearOfScientificName());
            td.setBehavior(tfdto.getBehavior());
            td.setBriefDescription(tfdto.getBriefDescription());
            td.setChromosomicNumberN(tfdto.getChromosomicNumberN());
            td.setClassTaxon(tfdto.getClassTaxon());
            td.setCommonNames(tfdto.getCommonNames());
            td.setContributors(tfdto.getContributors());
            td.setCreators(tfdto.getCreators());
            td.setDateCreated(tfdto.getDateCreated());
            td.setDateLastModified(tfdto.getDateLastModified());
            td.setDistribution(tfdto.getDistribution());
            td.setEcologicalSignificance(tfdto.getEcologicalSignificance());
            td.setEndemicity(tfdto.getEndemicity());
            td.setFamilyTaxon(tfdto.getFamilyTaxon());
            td.setFeeding(tfdto.getFeeding());
            td.setFolklore(tfdto.getFolklore());
            td.setGenusTaxon(tfdto.getGenusTaxon());                        
            td.setHabit(tfdto.getHabit());
            td.setHabitat(tfdto.getHabitat());
            td.setIdentificationKeys(tfdto.getIdentificationKeys());
            td.setInstitutionCode(tfdto.getInstitutionCode());
            td.setInteractions(tfdto.getInteractions());
            td.setInvasivenessData(tfdto.getInvasivenessData());
            td.setKingdomTaxon(tfdto.getKingdomTaxon());
            td.setLanguage(tfdto.getLanguage());
            td.setLegislation(tfdto.getLegislation());
            td.setLifeCycle(tfdto.getLifeCycle());
            td.setMigratoryData(tfdto.getMigratoryData());
            td.setMolecularData(tfdto.getMolecularData());
            td.setOrderTaxon(tfdto.getOrderTaxon());
            td.setOtherInformationSources(tfdto.getOtherInformationSources());
            td.setPapers(tfdto.getPapers());
            td.setPhylumTaxon(tfdto.getPhylumTaxon());
            td.setPopulationBiology(tfdto.getPopulationBiology());
            td.setReproduction(tfdto.getReproduction());
            td.setScientificDescription(tfdto.getScientificDescription());
            td.setScientificName(scientificName);
            td.setSpeciesPublicationReference(tfdto.getSpeciesPublicationReference());
            td.setSynonyms(tfdto.getSynonyms());
            td.setTargetAudiences(tfdto.getTargetAudiences());
            td.setTaxonRecordId(tfdto.getTaxonRecordId());
            td.setTerritory(tfdto.getTerritory());
            td.setTheManagement(tfdto.getTheManagement());
            td.setTheReferences(tfdto.getTheReferences());
            td.setTheUses(tfdto.getTheUses());
            td.setThreatStatus(tfdto.getThreatStatus());
            td.setTypification(tfdto.getTypification());
            td.setUnstructuredDocumentation(tfdto.getUnstructuredDocumentation());
            td.setUnstructuredNaturalHistory(tfdto.getUnstructuredNaturalHistory());
            td.setUsername(tfdto.getUsername());
            
            result.add(td);
        }
        
        return result;
    }
    
    /**
     * 
     * @param scientificName
     * @return 
     */
    public List<ImagesCDTO> imagesByScientificName(String scientificName){
        List<TaxonLiteCDTO> taxonCDTO = taxonDAO.findCDTOByScientificName(scientificName);
        
        if(taxonCDTO.size() > 0)
            return taxonCDTO.get(0).getImageList();
        else
            return null;
    }
    
}
