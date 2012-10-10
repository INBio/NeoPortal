package org.inbio.neoportal.core.dto.groupnav;

import java.util.List;

import org.inbio.neoportal.common.dto.BaseDTO;

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
	private String groupNavParentId;
	private String taxonId;
	private List<GroupNavCDTO> groupNavChilds;
	
	@Override
	public int compareTo(GroupNavCDTO o) {
		return name.compareTo(o.getName());
	}
	
	public GroupNavCDTO() {
		super();
	}

	
	public GroupNavCDTO(String groupNavId, String groupNavParentId,
			String taxonId, List<GroupNavCDTO> groupNavChilds) {
		super();
		this.groupNavId = groupNavId;
		this.groupNavParentId = groupNavParentId;
		this.taxonId = taxonId;
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

	/**
	 * @return the taxonId
	 */
	public String getTaxonId() {
		return taxonId;
	}

	/**
	 * @param taxonId the taxonId to set
	 */
	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
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
	
	
}
