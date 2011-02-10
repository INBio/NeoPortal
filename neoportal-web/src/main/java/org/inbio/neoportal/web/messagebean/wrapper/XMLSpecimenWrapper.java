/*
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
package org.inbio.neoportal.web.messagebean.wrapper;

import org.inbio.neoportal.web.messagebean.*;
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
public class XMLSpecimenWrapper {

	@XmlElementWrapper(name="response-elements", nillable=true)
	@XmlElement(name="element")
	private List<OccurrenceLiteBean> elements = new ArrayList<OccurrenceLiteBean>();

	/**
	 *
	 * @param responseType
	 */
	public XMLSpecimenWrapper() {
		super();
	}

	/**
     * @param xmlBean
     * @return
     */
	public boolean addElement(OccurrenceLiteBean xmlBean){
		return elements.add(xmlBean);
	}
}

/* This class tries to implementm the wraper with generics, nonetheless,when the
 * controller returns the XMLSpecimenWrapper object, the annotation @responsebody
 * gets an error traying to marshal the object to xml format
 * See writeToResult() method of Jaxb2RootElementHttpMessageConverter class
@RooJavaBean
@RooToString
@XmlRootElement(name="neoportal-response")
    public class XMLSpecimenWrapper <K> {

	@XmlElementWrapper(name="response-elements", nillable=true)
	@XmlElement(name="element")
	private List elements = new ArrayList<K>();

	/**
	 *
	 * @param responseType
	 */
	/*public XMLSpecimenWrapper() {
		super();
	}*/

	/**
     * @param xmlBean
     * @return
     */
	/*public boolean addElement(K xmlBean){
		return elements.add(xmlBean);
	}
}
*/