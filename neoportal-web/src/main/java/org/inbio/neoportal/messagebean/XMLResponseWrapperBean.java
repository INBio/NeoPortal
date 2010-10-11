package org.inbio.neoportal.messagebean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * TODO: hacer este wrapper reutilizable. Intenté hacerlo con generics pero me 
 * dio problemas.
 * 
 * @author jgutierrez
 *
 */

@RooJavaBean
@RooToString
@XmlRootElement(name="neoportal-response")
public class XMLResponseWrapperBean {
	
	@XmlElementWrapper(name="response-elements", nillable=true)
	@XmlElement(name="element")
	private List<SpecimenLiteBean> elements = new ArrayList<SpecimenLiteBean>();
	
	/**
	 * 
	 * @param responseType
	 */
	public XMLResponseWrapperBean() {
		super();
	}
	
	
	/**
	 * 
	 * @param slb
	 * @return
	 */
	public boolean addElement(SpecimenLiteBean slb){
		return elements.add(slb);
	}
	
}