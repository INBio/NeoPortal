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
import org.inbio.neoportal.core.dto.taxon.ImagesCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;
import org.inbio.neoportal.core.entity.CommonName;
import org.inbio.neoportal.core.entity.Image;
import org.inbio.neoportal.core.entity.Taxon;
import org.springframework.stereotype.Component;


/**
 * Transfrom a list of Taxon entities to OccurrenceLiteDTO
 * @author avargas
 */
@Component
public class TaxonTransformer 
    implements ResultTransformer {

    ImagesTransformer imagesRT = 
            new ImagesTransformer();
    
    @Override
    public List transformList(List list) {
        List<Taxon> taxonList = (List<Taxon>) list;
        List<TaxonCDTO> newList = new ArrayList<TaxonCDTO>();

        for(Taxon taxon: taxonList){
            newList.add(entityToDTO(taxon));
        }
        
        return newList;
    }

    @Override
    public Object transformTuple(Object[] os, String[] strings) {
    	
    	Taxon taxon = (Taxon)os[0];
    	
        return entityToDTO(taxon);
    }
    
    public TaxonCDTO entityToDTO(Taxon taxon){
        return entityToDTO(taxon, true);
    }
    
    public TaxonCDTO entityToDTO(Taxon taxon, boolean loadImages) {
    	if (taxon == null) {
			return null;
		}
    	
        TaxonCDTO taxonCDTO = new TaxonCDTO();
        taxonCDTO.setTaxonId(taxon.getTaxonId().toString());

        taxonCDTO.setTaxonomicalRangeId(taxon.getTaxonomicalRangeId().toString());

        taxonCDTO.setDefaultName(taxon.getDefaultName());

        taxonCDTO.setKingdom(taxon.getKingdom());

        taxonCDTO.setPhylum(taxon.getPhylum());

        taxonCDTO.setClass_(taxon.getClass_());

        taxonCDTO.setOrder(taxon.getOrder());

        taxonCDTO.setFamily(taxon.getFamily());

        taxonCDTO.setGenus(taxon.getGenus());

        taxonCDTO.setSpecies(taxon.getSpecies());
        if(taxon.getDominiumId() != null)
        	taxonCDTO.setDominiumId(taxon.getDominiumId().toString());
        if(taxon.getKingdomId() != null)
        	taxonCDTO.setKingdomId(taxon.getKingdomId().toString());
        if(taxon.getPhylumId() != null)
        	taxonCDTO.setPhylumId(taxon.getPhylumId().toString());
        if(taxon.getSubdivisionId() != null)
        	taxonCDTO.setSubdivisionId(taxon.getSubdivisionId().toString());
        if(taxon.getClassId() != null)
        	taxonCDTO.setClassId(taxon.getClassId().toString());
        if(taxon.getSubclassId() != null)
        	taxonCDTO.setSubclassId(taxon.getSubclassId().toString());
        if(taxon.getOrderId() != null)
        	taxonCDTO.setOrderId(taxon.getOrderId().toString());
        if(taxon.getSubOrderId() != null)
        	taxonCDTO.setSubOrderId(taxon.getSubOrderId().toString());
        if(taxon.getSuperFamilyId() != null)
        	taxonCDTO.setSuperFamilyId(taxon.getSuperFamilyId().toString());
        if(taxon.getFamilyId() != null)
        	taxonCDTO.setFamilyId(taxon.getFamilyId().toString());
        if(taxon.getSubFamilyId() != null)
        	taxonCDTO.setSubFamilyId(taxon.getSubFamilyId().toString());
        if(taxon.getTribeId() != null)
        	taxonCDTO.setTribeId(taxon.getTribeId().toString());
        if(taxon.getSubTribeId() != null)
        	taxonCDTO.setSubTribeId(taxon.getSubTribeId().toString());
        if(taxon.getGenusId() != null)
        	taxonCDTO.setGenusId(taxon.getGenusId().toString());
        if(taxon.getSubGenusId() != null)
        	taxonCDTO.setSubGenusId(taxon.getSubGenusId().toString());
        if(taxon.getSectionId() != null)
        	taxonCDTO.setSectionId(taxon.getSectionId().toString());
        if(taxon.getSubSectionId() != null)
        	taxonCDTO.setSubSectionId(taxon.getSubSectionId().toString());
        if(taxon.getRaceId() != null)
        	taxonCDTO.setRaceId(taxon.getRaceId().toString());
        if(taxon.getSpeciesId() != null)
        	taxonCDTO.setSpeciesId(taxon.getSpeciesId().toString());
        if(taxon.getSubSpeciesId() != null)
        	taxonCDTO.setSubSpeciesId(taxon.getSubSpeciesId().toString());
        if(taxon.getVarietyId() != null)
        	taxonCDTO.setVarietyId(taxon.getVarietyId().toString());
        if(taxon.getFormId() != null)
        	taxonCDTO.setFormId(taxon.getFormId().toString());
        taxonCDTO.setDomain(taxon.getDomain());
    	taxonCDTO.setImageUrl(taxon.getImageUrl());
    	taxonCDTO.setCommonNames(taxon.getCommonNames());
    	
    	if(loadImages) {
	        ArrayList<ImagesCDTO> imgList = new ArrayList<ImagesCDTO>();
	        for(Image img: taxon.getImages()){
	            imgList.add((ImagesCDTO)imagesRT.transformTuple(new Object[]{img}, null));
	        }
	        
	        taxonCDTO.setImageList(imgList);
    	}
        
        return taxonCDTO;
	}
}
