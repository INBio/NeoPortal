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
package org.inbio.neoportal.service.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.inbio.neoportal.core.dto.occurrence.OccurrenceDwcCDTO;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Book;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.entity.TaxonPlic;
import org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO;
import org.inbio.neoportal.service.dto.species.TaxonFeatureDTO;

/**
 *
 * @author avargas
 */
public interface SpeciesManager {
    
    public List<TaxonDescriptionFullSDTO> taxonDescriptionByProvider
            (String scientificName, String provider);
    
    public List<TaxonDescriptionFullSDTO> taxonDescription(String scientificName);
    
    public List<TaxonPlic> getTaxonListLanguaje(String defaultName);
    
    public List<ImagesCDTO> getImagesByDefaultName(String defaultName, int offset, int quantity);
    
    public List<Book> getBook(String defaultName,String language);
    
    public Long countImagesByDefaultName(String defaultName);
    
    public TaxonFeatureDTO getTaxonFeatureByDefaultName(String defaultName);
    
    /**
     * Search <code>searchText</code> in the indicated <code>fields</code>
     * Results are paginatedString
     * @param searchText
     * @return
     * @throws ParseException
     */
    public List<OccurrenceDwcCDTO> getOccurrencesByDefaultName (
    		String searchText, 
    		int offset, 
    		int quantity);
    
    /**
     * Return the amount of items returned by a Lucene search
     * @param searchText
     * @return
     * @throws ParseException
     */
    public Long countOccurrencesByDefaultName(
    		String searchText);
    
    /**
     * 
     * @param defaultName
     * @return
     */
    public Taxon getTaxonByDefaultName(String defaultName);
    
    /**
     * 
     * @param defaultName
     * @return
     */
    public TaxonPlic getTaxonPLicByDefaultName(String defaultName,String language);

}
