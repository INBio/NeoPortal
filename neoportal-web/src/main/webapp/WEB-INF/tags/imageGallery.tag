<%@ tag description="Display the images for species page" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="images" description="Lits of images" required="true" type="java.util.List" %>
	
<div class="image-gallery">
<n:pagination></n:pagination>
<c:forEach var="image" items="${images}">
	<img alt="${image.title}" src="${image.mediumUrl}" />
</c:forEach>
</div>
	