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
import org.inbio.neoportal.core.dto.commonname.CommonNameLiteCDTO;
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
                    td.getScientificName(),
                    td.getInstitutionCode(),
                    td.getDateLastModified() != null ? td.getDateLastModified().toString() : "",
                    td.getTaxonRecordId(),
                    td.getLanguage(),
                    td.getCreators(),
                    td.getDistribution(),
                    td.getAbstract_(),
                    td.getKingdomTaxon(),
                    td.getPhylumTaxon(),
                    td.getClassTaxon(),
                    td.getOrderTaxon(),
                    td.getFamilyTaxon(),
                    td.getGenusTaxon(),
                    td.getSynonyms(),
                    td.getAuthorYearOfScientificName(),
                    td.getSpeciesPublicationReference(),
                    td.getCommonNames(),
                    td.getTypification(),
                    td.getContributors(),
                    td.getDateCreated() != null ? td.getDateCreated().toString() : "",
                    td.getHabit(),
                    td.getLifeCycle(),
                    td.getReproduction(),
                    td.getAnnualCycle(),
                    td.getScientificDescription(),
                    td.getBriefDescription(),
                    td.getFeeding(),
                    td.getBehavior(),
                    td.getInteractions(),
                    td.getChromosomicNumberN(),
                    td.getMolecularData(),
                    td.getPopulationBiology(),
                    td.getThreatStatus(),
                    td.getLegislation(),
                    td.getHabitat(),
                    td.getTerritory(),
                    td.getEndemicity(),
                    td.getTheUses(),
                    td.getTheManagement(),
                    td.getFolklore(),
                    td.getTheReferences(),
                    td.getUnstructuredDocumentation(),
                    td.getOtherInformationSources(),
                    td.getPapers(),
                    td.getIdentificationKeys(),
                    td.getMigratoryData(),
                    td.getEcologicalSignificance(),
                    td.getUnstructuredNaturalHistory(),
                    td.getInvasivenessData(),
                    td.getTargetAudiences()));

        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
       return os.length > 0 ? os[0]:null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
