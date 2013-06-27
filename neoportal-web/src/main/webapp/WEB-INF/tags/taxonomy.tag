<%@ tag description="Generate the taxonomy fields" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="taxonDescription" description="The taxon description of the element" required="true" type="org.inbio.neoportal.service.dto.species.TaxonDescriptionFullSDTO" %>
	
	<div id="taxonomy">
		<h3><fmt:message key="taxonDes.taxonomy"/></h3>
   		<ul>
   			<li><fmt:message key="taxon.kingdom"/>: ${taxonDescription.kingdomTaxon}</li>
   			<li><fmt:message key="taxon.phylum"/>: ${taxonDescription.phylumTaxon}</li>
   			<li><fmt:message key="taxon.class"/>: ${taxonDescription.classTaxon}</li>
   			<li><fmt:message key="taxon.order"/>: ${taxonDescription.orderTaxon}</li>
   			<li><fmt:message key="taxon.family"/>: ${taxonDescription.familyTaxon}</li>
   			<li><fmt:message key="taxon.genus"/>: ${taxonDescription.genusTaxon}</li>
   		</ul>
	</div>
	