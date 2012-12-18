package org.inbio.neoportal.core.dto.transformers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.inbio.neoportal.core.dto.groupnav.GroupNavCDTO;
import org.inbio.neoportal.core.entity.GroupNav;

/**
 * This transformer does not load the taxonCDTO for performance issues
 * @author avargas
 *
 */
public class GroupNavSimpleTransformer implements ResultTransformer {
	
	TaxonTransformer taxonTransformer = new TaxonTransformer();

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		
		GroupNav gn = (GroupNav)tuple[0];
		GroupNavCDTO gnCDTO = new GroupNavCDTO();
		
		gnCDTO.setGroupNavId(gn.getGroupNavId().toString());
		gnCDTO.setName(gn.getName());
		gnCDTO.setNameEng(gn.getNameEng());
		gnCDTO.setImageUrl(gn.getImageUrl());
		if (gn.getGroupNavParent() != null){
			gnCDTO.setGroupNavParentId(gn.getGroupNavParent().getGroupNavId().toString());
		}
		else
			gnCDTO.setGroupNavParentId(null);
		
//		if(gn.getTaxon() != null)
//			gnCDTO.setTaxonCDTO(taxonTransformer.entityToDTO(gn.getTaxon()));
		
		List<GroupNavCDTO> childList = new ArrayList<GroupNavCDTO>(); 
		
		for (GroupNav item : gn.getGroupNavChilds()){
			GroupNavCDTO childGnCDTO = entityToDto(item);
			childList.add(childGnCDTO);
		}
		
		gnCDTO.setGroupNavChilds(childList);
		
		return gnCDTO;
	}

	@Override
	public List transformList(List collection) {
		return collection;
	}

	private GroupNavCDTO entityToDto(GroupNav groupNav){
		GroupNavCDTO groupNavCDTO = new GroupNavCDTO();
		
		groupNavCDTO.setGroupNavId(groupNav.getGroupNavId().toString());
		groupNavCDTO.setName(groupNav.getName());
		groupNavCDTO.setNameEng(groupNav.getNameEng());
		groupNavCDTO.setImageUrl(groupNav.getImageUrl());
		if (groupNav.getGroupNavParent() != null){
			groupNavCDTO.setGroupNavParentId(groupNav.getGroupNavParent().getGroupNavId().toString());
		}
		else
			groupNavCDTO.setGroupNavParentId(null);
		
//		if(groupNav.getTaxon() != null)
//			groupNavCDTO.setTaxonCDTO(taxonTransformer.entityToDTO(groupNav.getTaxon()));
		
		List<GroupNavCDTO> childList = new ArrayList<GroupNavCDTO>(); 
		
		for (GroupNav groupNavChild : groupNav.getGroupNavChilds()) {
			childList.add(entityToDto(groupNavChild));
		}
		
		groupNavCDTO.setGroupNavChilds(childList);
		
		return groupNavCDTO;
	}
	
}
