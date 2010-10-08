package org.inbio.neoportal.messagebean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@XmlRootElement(name="neoportal-response")
public class SpecimenLiteWrapperBean {
	
	@XmlElementWrapper(name="elements", nillable=true)
	@XmlElement(name="specimenlite")
	private List<SpecimenLiteBean> elements = new ArrayList<SpecimenLiteBean>();
	
	/**
	 * 
	 * @param slb
	 * @return
	 */
	public boolean addElement(SpecimenLiteBean slb){
		return elements.add(slb);
		
	}
}