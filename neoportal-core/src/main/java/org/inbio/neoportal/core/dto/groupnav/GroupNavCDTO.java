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
		implements Comparable{
	
	private String groupNavId;
	private String label;
	private String groupNavParentId;
	private String taxonId;
	private List<GroupNavCDTO> groupNavChilds;
	
	@Override
	public int compareTo(Object o) {
		return Integer.parseInt(this.groupNavId) - 
				Integer.parseInt(((GroupNavCDTO)o).getGroupNavId());
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
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
