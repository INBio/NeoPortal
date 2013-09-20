<%@ tag description="Generate the feature block of taxon" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="taxonFeature" description="The taxon to feature with images" required="true" type="org.inbio.neoportal.service.dto.species.TaxonFeatureDTO" %>
	<c:if test="${not empty taxonFeature }">
	<div id="feature">
		<a href="<c:out value="${pageContext.request.contextPath}"/>/species/${taxonFeature.defaultName}">
		<c:choose>
			<c:when test="${taxonFeature.taxonomicalRangeName == 'species' }">
				<h3 class="scientific-name">${taxonFeature.defaultName}</h3>
			</c:when>
		
			<c:otherwise>
				<h3><fmt:message key="taxon.${taxonFeature.taxonomicalRangeName}" />: ${taxonFeature.defaultName}</h3>
			</c:otherwise>
		</c:choose>
		</a>
		<p>${taxonFeature.occurrencesCount}&nbsp;<fmt:message key="taxonFeature.occurrencesCount" />, ${taxonFeature.imagesCount}&nbsp;<fmt:message key="taxonFeature.imagesCount" /></p>
		
		<c:forEach var="image" items="${taxonFeature.featureImages}">
		<div class="image-item">
			<img alt="${image.title}" src="${image.mediumUrl}" />
			<p>${image.taxon.defaultName }</p>
		</div>
		</c:forEach>
	</div>
	</c:if>