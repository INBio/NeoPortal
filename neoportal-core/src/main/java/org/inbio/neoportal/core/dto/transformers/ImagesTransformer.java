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

import java.util.List;
import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Images;

/**
 *
 * @author avargas
 */


public class ImagesTransformer 
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        ImagesCDTO imagesCDTO = null;
        
        Images img = (Images)tuple[0];
        
        try {
            imagesCDTO = new ImagesCDTO();
            
            imagesCDTO.setImageId(img.getImageId().toString());
            imagesCDTO.setM3sImageId(img.getM3sImageId().toString());
            imagesCDTO.setTaxonId(img.getTaxon().getTaxonId().toString());
            imagesCDTO.setAuthor(img.getAuthor());
            imagesCDTO.setRights(img.getRights());

            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return imagesCDTO;
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }
    
}
