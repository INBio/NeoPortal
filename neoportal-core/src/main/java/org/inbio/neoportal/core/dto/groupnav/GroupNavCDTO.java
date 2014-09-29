package org.inbio.neoportal.core.dto.groupnav;

import java.util.List;

import org.inbio.neoportal.core.common.dto.BaseDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonCDTO;
import org.inbio.neoportal.core.dto.taxon.TaxonLiteCDTO;

/**
 * @author avargas
 *
 * DTO for GroupNav, because this is for use 
 * after closing database call, the property groupNavChilds
 * only keep one level (groupNavChilds of a child would be null).
 */
public class GroupNavCDTO extends BaseDTO
		implements Comparable<GroupNavCDTO>{
	
	private String groupNavId;
	private String name;
	private String nameEng;
	private String groupNavParentId;
	private TaxonCDTO taxonCDTO;
	private String imageUrl;
	private List<GroupNavCDTO> groupNavChilds;
	
	@Override
	public int compareTo(GroupNavCDTO o) {
		return name.compareTo(o.getName());
	}
	
	public GroupNavCDTO() {
		super();
	}

	
	public GroupNavCDTO(String groupNavId, String groupNavParentId,
			TaxonCDTO taxonCDTO, String imageUrl, List<GroupNavCDTO> groupNavChilds) {
		super();
		this.groupNavId = groupNavId;
		this.groupNavParentId = groupNavParentId;
		this.taxonCDTO = taxonCDTO;
		this.imageUrl = imageUrl;
		this.groupNavChilds = groupNavChilds;
	}

	/**
	 * @return the groupNavId
	 */
	public String getGroupNavId() {
		return groupNavId;
	}

	/**
	 * @param groupNavId the groupNavId to set
	 */
	public void setGroupNavId(String groupNavId) {
		this.groupNavId = groupNavId;
	}

	/**
	 * @return the groupNavParentId
	 */
	public String getGroupNavParentId() {
		return groupNavParentId;
	}

	/**
	 * @param groupNavParentId the groupNavParentId to set
	 */
	public void setGroupNavParentId(String groupNavParentId) {
		this.groupNavParentId = groupNavParentId;
	}

	public TaxonCDTO getTaxonCDTO() {
		return taxonCDTO;
	}

	public void setTaxonCDTO(TaxonCDTO taxonCDTO) {
		this.taxonCDTO = taxonCDTO;
	}

	/**
	 * @return the groupNavChilds
	 */
	public List<GroupNavCDTO> getGroupNavChilds() {
		return groupNavChilds;
	}

	/**
	 * @param groupNavChilds the groupNavChilds to set
	 */
	public void setGroupNavChilds(List<GroupNavCDTO> groupNavChilds) {
		this.groupNavChilds = groupNavChilds;
	}

	/**
	 * @return the label
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the label to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nameEng
	 */
	public String getNameEng() {
		return nameEng;
	}

	/**
	 * @param nameEng the nameEng to set
	 */
	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
