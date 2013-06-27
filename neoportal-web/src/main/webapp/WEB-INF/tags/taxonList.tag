<%@ tag description="Generate the results of taxon list" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="taxonList" description="The taxon list with the elements" required="true" type="java.util.List" %>
	
	<n:pagination></n:pagination>
	<ul class="taxon_list">
	<c:forEach var="taxon" items="${taxonList}">
		<li>
			<div class="image_wrap">
			<c:if test="${taxon.imageURL != ''}">
			<img alt="image of ${taxon.scientificName}" src="${taxon.imageURL}" />
			</c:if>
			</div>
			<div class="taxon_inner">
			<a href="<c:out value="${pageContext.request.contextPath}"/>/species/${taxon.scientificName}"><h3>${taxon.scientificName}</h3></a>
			<c:if test="${not empty taxon.commonName}">
			<p><fmt:message key="common_name"></fmt:message>: ${taxon.commonName}</p>
			</c:if>
			</div>
		</li>
	</c:forEach>
	</ul>
	