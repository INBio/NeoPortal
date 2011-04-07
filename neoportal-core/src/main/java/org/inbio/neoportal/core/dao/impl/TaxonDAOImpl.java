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
package org.inbio.neoportal.core.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.inbio.neoportal.core.dao.TaxonDAO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.entity.Taxon;
import org.inbio.neoportal.core.dto.transformers.TaxonTransformer;
import org.springframework.stereotype.Repository;

/**
 * Implementantion of TaxonDAO that grant access to the Taxon information.
 * @author asanabria
 */
@Repository
public class TaxonDAOImpl 
    extends GenericBaseDAOImpl<Taxon, BigDecimal> 
        implements TaxonDAO{
    
    
    @Override
    public List<TaxonLiteCDTO> search
        (final String searchText, final int offset, final int quantity) {
        
       String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));

        return super.search(Taxon.class,
                            new TaxonTransformer(), 
                            fieldList.toArray(new String[fieldList.size()]), 
                            searchText, 
                            offset, 
                            quantity);
       
    }

    @Override
    public Long searchCount(final String searchText) {
        
        String[] taxon =
                new String[]{ "defaultName", "kingdom", "division", "class_",
                                 "order", "family", "genus", "species"};

        ArrayList<String> fieldList = new ArrayList<String>();

        fieldList.addAll(Arrays.asList(taxon));
        
        return super.searchCount(Taxon.class, 
                                new TaxonTransformer(), 
                                fieldList.toArray(new String[fieldList.size()]), 
                                searchText);
    }
}
