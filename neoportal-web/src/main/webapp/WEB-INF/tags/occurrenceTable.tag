<%@ tag description="Display the images for species page" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="occurrences" description="Lits of occurrences" required="true" type="java.util.List" %>
	
<div class="occurrences-table taxon-content">
<n:pagination></n:pagination>
<table>
	<thead>
		<tr>
			<th><fmt:message key="institution"/></th>
			<th><fmt:message key="country"/></th>
			<th><fmt:message key="province"/></th>
			<th><fmt:message key="county"/></th>
			<th><fmt:message key="latitude"/></th>
			<th><fmt:message key="longitude"/></th>
			<th><fmt:message key="scientific_name"/></th>
			<th><fmt:message key="catalog_number"/></th>
		</tr>
	</thead>

<c:forEach var="occurrence" items="${occurrences}">
	<tr>
		<td>${occurrence.institutionCode}</td>
		<td>${occurrence.country}</td>
		<td>${occurrence.stateProvince}</td>
		<td>${occurrence.county}</td>
		<td>${occurrence.decimalLatitude}</td>
		<td>${occurrence.decimalLongitude}</td>
		<td class="scientific-name">${occurrence.scientificName}</td>
		<td>${occurrence.catalogNumber}</td>
	</tr>
</c:forEach>
</table>
</div>
	