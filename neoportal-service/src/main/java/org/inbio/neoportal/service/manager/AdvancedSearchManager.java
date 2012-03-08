/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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

import java.util.List;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnDefaultSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.ColumnListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FilterListSDTO;
import org.inbio.neoportal.service.dto.advancedSearch.FiltersSDTO;
import org.inbio.neoportal.service.dto.occurrences.OccurrenceLiteSDTO;

/**
 *
 * @author avargas
 */
public interface AdvancedSearchManager {

    /**
     * 
     * @return 
     */
    public List<ColumnListSDTO> getAllColumns();
    
    /**
     * 
     * @param keyName
     * @return 
     */
    public ColumnListSDTO getColumnListByKey(String keyName);
    
    /**
     * 
     * @return 
     */
    public FiltersSDTO getFilters();
    
    /**
     * 
     * @return 
     */
    public List<ColumnDefaultSDTO> getColumnDefault();
    
    /**
     * 
     * @return 
     */
    public List<FilterListSDTO> getAllFilters();
    
    /**
     * 
     * @param filters
     * @param offset
     * @param quantity
     * @return 
     */
    public List<OccurrenceLiteSDTO> occurrencePaginatedSearch(
            String filters, int offset, int quantity);
    
    /**
     * 
     * @param filters
     * @return 
     */
    public Long occurrenceSearchCount(
            String filters);
}
