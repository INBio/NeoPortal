<%@ tag description="Generate the taxonomy fields" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="taxon" description="The taxon description of the element" required="true" type="org.inbio.neoportal.core.dto.taxon.TaxonCDTO" %>
	
	<div id="taxonomy">
		<h3><fmt:message key="taxonDes.taxonomy"/></h3>
   		<ul>
   			<c:if test="${not empty taxon.kingdom }">
   			<li><fmt:message key="taxon.kingdom"/>: <span>${taxon.kingdom}</span></li>
   			</c:if>
   			<c:if test="${not empty taxon.division }">
   			<li><fmt:message key="taxon.phylum"/>: <span>${taxon.division}</span></li>
   			</c:if>
   			<c:if test="${not empty taxon.class_ }">
   			<li><fmt:message key="taxon.class"/>: <span>${taxon.class_}</span></li>
   			</c:if>
   			<c:if test="${not empty taxon.order }">
   			<li><fmt:message key="taxon.order"/>: <span>${taxon.order}</span></li>
   			</c:if>
   			<c:if test="${not empty taxon.family }">
   			<li><fmt:message key="taxon.family"/>: <span>${taxon.family}</span></li>
   			</c:if>
   			<c:if test="${not empty taxon.genus }">
   			<li><fmt:message key="taxon.genus"/>: <span class="scientific-name">${taxon.genus}</span></li>
   			</c:if>
   		</ul>
	</div>
	