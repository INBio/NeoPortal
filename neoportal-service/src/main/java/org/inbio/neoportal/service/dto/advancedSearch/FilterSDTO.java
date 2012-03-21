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
package org.inbio.neoportal.service.dto.advancedSearch;

import org.inbio.neoportal.common.dto.BaseDTO;
import org.inbio.neoportal.service.entity.AdvancedSearchData;

/**
 * Para pasar los filtros seleccionados por el usuario de 
 * el Controller a el Manager
 * @author avargas
 */
public class FilterSDTO
        extends BaseDTO{
    
    private AdvancedSearchData taxonomy;
    private AdvancedSearchData geography;

    public FilterSDTO() {
    }

    public AdvancedSearchData getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(AdvancedSearchData taxonomy) {
        this.taxonomy = taxonomy;
    }

    public AdvancedSearchData getGeography() {
        return geography;
    }

    public void setGeography(AdvancedSearchData geography) {
        this.geography = geography;
    }
}
