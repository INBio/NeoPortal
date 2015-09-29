package org.inbio.neoportal.core.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements java.io.Serializable
{
	private BigDecimal idBook;
	private String service;
	private String link;
	private String title;
	private String externalId;
	private Set <TaxonPlic> taxonPlic = new HashSet<TaxonPlic>(0);
	
	public Book()
	{
		
	}
	
	public Book(BigDecimal idBook, String service, String link,String title,String externalId , Set<TaxonPlic> taxonPlic) 
	{
		this.idBook = idBook;
		this.service = service;
		this.link = link;
		this.title = title;
		this.externalId = externalId;
		this.taxonPlic = taxonPlic;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Set<TaxonPlic> getTaxonPlic() {
		return taxonPlic;
	}

	public void setTaxonPlic(Set<TaxonPlic> taxonPlic) {
		this.taxonPlic = taxonPlic;
	}

	public BigDecimal getIdBook() {
		return idBook;
	}

	public void setIdBook(BigDecimal idBook) {
		this.idBook = idBook;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	
}
