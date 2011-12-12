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
import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.taxondescription.TaxonDescriptionFullCDTO;
import org.inbio.neoportal.core.entity.TaxonDescription;


/**
 * Transfrom a list of Taxon entities to TaxonDescriptionFullDTO
 * @author avargas
 */
public class TaxonDescriptionFullTransformer 
    implements ResultTransformer {

    @Override
    public List transformList(List list) {
        List<TaxonDescription> taxonList = (List<TaxonDescription>)list;
        
        List<TaxonDescriptionFullCDTO> newList = new ArrayList<TaxonDescriptionFullCDTO>();
        
        for(TaxonDescription td: taxonList) {
            newList.add(new TaxonDescriptionFullCDTO(
                    td.getScientificName() != null ? td.getScientificName() : "",
                    td.getInstitutionCode() != null ? td.getInstitutionCode() : "",
                    td.getDateLastModified() != null ? td.getDateLastModified().toString() : "",
                    td.getTaxonRecordId() != null ? td.getTaxonRecordId() : "",
                    td.getLanguage() != null ? td.getLanguage() : "",
                    td.getCreators() != null ? td.getCreators() : "",
                    td.getDistribution() != null ? td.getDistribution() : "",
                    td.getAbstract_() != null ? td.getAbstract_() : "",
                    td.getKingdomTaxon() != null ? td.getKingdomTaxon() : "",
                    td.getPhylumTaxon() != null ? td.getPhylumTaxon() : "",
                    td.getClassTaxon() != null ? td.getClassTaxon() : "",
                    td.getOrderTaxon() != null ? td.getOrderTaxon() : "",
                    td.getFamilyTaxon() != null ? td.getFamilyTaxon() : "",
                    td.getGenusTaxon() != null ? td.getGenusTaxon() : "",
                    td.getSynonyms() != null ? td.getSynonyms() : "",
                    td.getAuthorYearOfScientificName() != null ? td.getAuthorYearOfScientificName() : "",
                    td.getSpeciesPublicationReference() != null ? td.getSpeciesPublicationReference() : "",
                    td.getCommonNames() != null ? td.getCommonNames() : "",
                    td.getTypification() != null ? td.getTypification() : "",
                    td.getContributors() != null ? td.getContributors() : "",
                    td.getDateCreated() != null ? td.getDateCreated().toString() : "",
                    td.getHabit() != null ? td.getHabit() : "",
                    td.getLifeCycle() != null ? td.getLifeCycle() : "",
                    td.getReproduction() != null ? td.getReproduction() : "",
                    td.getAnnualCycle() != null ? td.getAnnualCycle() : "",
                    td.getScientificDescription() != null ? td.getScientificDescription() : "",
                    td.getBriefDescription() != null ? td.getBriefDescription() : "",
                    td.getFeeding() != null ? td.getFeeding() : "",
                    td.getBehavior() != null ? td.getBehavior() : "",
                    td.getInteractions() != null ? td.getInteractions() : "",
                    td.getChromosomicNumberN() != null ? td.getChromosomicNumberN() : "",
                    td.getMolecularData() != null ? td.getMolecularData() : "",
                    td.getPopulationBiology() != null ? td.getPopulationBiology() : "",
                    td.getThreatStatus() != null ? td.getThreatStatus() : "",
                    td.getLegislation() != null ? td.getLegislation() : "",
                    td.getHabitat() != null ? td.getHabitat() : "",
                    td.getTerritory() != null ? td.getTerritory() : "",
                    td.getEndemicity() != null ? td.getEndemicity() : "",
                    td.getTheUses() != null ? td.getTheUses() : "",
                    td.getTheManagement() != null ? td.getTheManagement() : "",
                    td.getFolklore() != null ? td.getFolklore() : "",
                    td.getTheReferences() != null ? td.getTheReferences() : "",
                    td.getUnstructuredDocumentation() != null ? td.getUnstructuredDocumentation() : "",
                    td.getOtherInformationSources() != null ? td.getOtherInformationSources() : "",
                    td.getPapers() != null ? td.getPapers() : "",
                    td.getIdentificationKeys() != null ? td.getIdentificationKeys() : "",
                    td.getMigratoryData() != null ? td.getMigratoryData() : "",
                    td.getEcologicalSignificance() != null ? td.getEcologicalSignificance() : "",
                    td.getUnstructuredNaturalHistory() != null ? td.getUnstructuredNaturalHistory() : "",
                    td.getInvasivenessData() != null ? td.getInvasivenessData() : "",
                    td.getTargetAudiences() != null ? td.getTargetAudiences() : ""
                    ));

        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
       return os.length > 0 ? os[0]:null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
