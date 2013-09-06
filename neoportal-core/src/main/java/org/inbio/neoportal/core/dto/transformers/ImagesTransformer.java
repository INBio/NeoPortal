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
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.entity.Image;

/**
 *
 * @author avargas
 */


public class ImagesTransformer 
        implements ResultTransformer{

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        ImagesCDTO imagesCDTO = null;
        
        Image img = (Image)tuple[0];
        
        try {
            imagesCDTO = new ImagesCDTO();
            
            String occurrenceId = 
            		img.getOccurrence() != null? img.getOccurrence().getOccurrenceId() : null; 
            
            imagesCDTO.setImageId(img.getImageId().toString());
            imagesCDTO.setExternalImageId(img.getExternalImageId().toString());
            imagesCDTO.setTaxonId(img.getTaxon().getTaxonId().toString());
            imagesCDTO.setAuthor(img.getAuthor());
            imagesCDTO.setRights(img.getRights());
            imagesCDTO.setOccurrenceId(occurrenceId);
            imagesCDTO.setSource(img.getSource());
            imagesCDTO.setSecret(img.getSecret());
            imagesCDTO.setServer(String.valueOf(img.getServer()));
            imagesCDTO.setFarm(String.valueOf(img.getFarm()));
            imagesCDTO.setTitle(img.getTitle());
            imagesCDTO.setDateAdded(String.valueOf(img.getDateAdded()));
            imagesCDTO.setDescription(img.getDescription());
            imagesCDTO.setDateUpload(String.valueOf(img.getDateUpload()));
            imagesCDTO.setDateTaken(String.valueOf(img.getDateTaken()));
            imagesCDTO.setOriginalSecret(img.getOriginalSecret());
            imagesCDTO.setOriginalFormat(img.getOriginalFormat());
            imagesCDTO.setLastUpdate(String.valueOf(img.getLastUpdate()));
            imagesCDTO.setLatitude(img.getLatitude());
            imagesCDTO.setLongitude(img.getLongitude());
            imagesCDTO.setAccuracy(img.getAccuracy());
            imagesCDTO.setTags(img.getTags());
            
            imagesCDTO.setMediumUrl(Image.getSmallUrl(imagesCDTO));
            imagesCDTO.setBigUrl(Image.getBigUrl(imagesCDTO));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return imagesCDTO;
    }

    @Override
    public List transformList(List collection) {
    	List<ImagesCDTO> images = new ArrayList<ImagesCDTO>();
    	
    	for (Image image : (List<Image>)collection) {
			images.add((ImagesCDTO)this.transformTuple(new Object[]{image}, null));
		}
        return images;
    }
    
}
