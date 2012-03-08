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
package org.inbio.neoportal.core.dto.occurrence;

import java.util.ArrayList;
import org.inbio.neoportal.common.dto.*;

/**
 * Occurrence record, fields based on DarwinCore Terms http://rs.tdwg.org/dwc/terms/index.htm
 * @author avargas
 */
public class OccurrenceCDTO 
    extends BaseDTO 
        implements Comparable {

    
/*  Occurrence Terms  */    
    private String occurrenceId;
    private String catalogNumber;
    private String remarks; /* dwc = occurrenceRemarks */
    
    /*private String recordNumber;*/
    private String collector;  /* dwc = recordedBy */
    /*private String individualId;
    private String individualCount;*/
    private String sex;
    private String lifeStage;
    /*private String reproductiveCondition;
    private String behavior;
    private String establishmentMeans;
    private String occurrenceStatus;
    private String preparations;
    private String disposition;
    private String otherCatalogNumbers;
    private String previousIdentifications;
    private String associatedMedia;
    private String associatedReferences;
    private String assocciatedOccurrences;
    private String associatedSequences;
    private String associatedTaxa;*/


/*  Taxon terms */
    private String taxonId;
    /*private String scientificNameId;  //not include in database */
    /*private String acceptedNameUsageId;   //not include in database */
    /*private String parentNameUsageId;   //not include in database */
    /*private String originalNameUsageId;   //not include in database */
    /*private String nameAccordingToId;   //not include in database */
    /*private String namePublishedInId;   //not include in database */
    /*private String taxonConceptId;   //not include in database */
    private String scientificName;
    private String acceptedNameUsage;
    private String parentNameUsage;
    private String originalNameUsage;
    private String nameAccordingTo;
    private String namePublishedIn;
    private String namePublishedInYear;
    private String higherClassification;
    private String kingdom;
    private String division;  /*  dwc = phylum  */
    private String class_;  /*  dwc = class  */
    private String order;
    private String family;
    private String genus;
    private String subgenus;
    private String specificEpithet;
    private String infraspecificEpithet;
    private String infraspecificRank;   /*  dwc = taxonRank  */
    private String verbatimTaxonRank;
    private String scientificNameAuthorship;
    private String vernacularName;
    private String nomenclaturalCode;
    private String taxonomicStatus;
    private String nomenclaturalStatus;
    private String taxonRemarks;
    
    
    public OccurrenceCDTO() {
    }

    public OccurrenceCDTO(String scientificName) {

        this.scientificName = scientificName;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 23 * hash + 
            (this.scientificName != null ? this.scientificName.hashCode() : 0);
        
        return hash;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final OccurrenceCDTO other = (OccurrenceCDTO) obj;
        
        if ((this.scientificName == null) ? 
                (other.scientificName != null) :
                !this.scientificName.equals(other.scientificName)) {
            
            return false;
        }
        return true;
    }

    /**
     * Make posible the search only different species method on SearchManager
     * @return
     */
    @Override
    public int compareTo(Object o) {

        OccurrenceCDTO ol = (OccurrenceCDTO)o;
//        return this.scientificName.compareTo(ol.getScientificName());
        return 1;
    }

    /* Getters & Setters */


}