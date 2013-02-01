<%@ tag description="Generate the results of taxon list" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="taxonList" description="The taxon list with the elements" required="true" type="java.util.List" %>
	
	<n:pagination></n:pagination>
	<ul>
	<c:forEach var="taxon" items="${taxonList}">
		<li>
			<c:if test="${taxon.imageURL != ''}">
			<img alt="image of ${taxon.scientificName}" src="${taxon.imageURL}" />
			</c:if>
			<h3><a href="<c:out value="${pageContext.request.contextPath}"/>/species/${taxon.scientificName}">${taxon.scientificName}</a></h3>
			<c:if test="${not empty taxon.commonName}">
			<p><fmt:message key="common_name"></fmt:message>: ${taxon.commonName}</p>
			</c:if>
		</li>
	</c:forEach>
	</ul>
	