package org.inbio.neoportal.messagebean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@XmlRootElement(name="specimenlite")
public class SpecimenLiteBean {
	
	@XmlElement(name="catalognumber")
	private String catalogNumber = "007";
	
	@XmlElement(name="institutioncode")
	private String institutionCode = "INB";
	
	@XmlElement(name="scientificname")
	private String scientificName = "Homo sapiens sapiens";
	
	@XmlElement(name="latitude")
	private String latitude = "9.7425";

	@XmlElement(name="longitude")
	private String longitude = "-84.376667";
	
}
