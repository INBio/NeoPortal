package org.inbio.neoportal.messagebean;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class SpecimenLiteBean {

	@XmlElement(name="catalognumber")
	private String catalogNumber;
	
	@XmlElement(name="institutioncode")
	private String institutionCode;
	
	@XmlElement(name="scientificname")
	private String scientificName;
	
	@XmlElement(name="latitude")
	private String latitude;

	@XmlElement(name="longitude")
	private String longitude;

	
	/**
	 * 
	 */
	public SpecimenLiteBean() {
		super();
	}

	/**
	 * @param catalogNumber
	 * @param institutionCode
	 * @param scientificName
	 * @param latitude
	 * @param longitude
	 */
	public SpecimenLiteBean(String catalogNumber, String institutionCode,
			String scientificName, String latitude, String longitude) {
		this.catalogNumber = catalogNumber;
		this.institutionCode = institutionCode;
		this.scientificName = scientificName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
}
