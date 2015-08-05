<%@ tag description="Display the images for species page" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="images" description="Lits of images" required="true" type="java.util.List" %>
<%@ attribute name="showPagination" required="false" type="java.lang.Boolean" %>
	
<div class="image-gallery taxon-content">
<c:if test="${showPagination != false }">
<n:pagination></n:pagination>
</c:if>
<div class="item-list">
<c:forEach var="image" items="${images}">
	<div class="item">
		<div class="image-item" about="${image.bigUrl}">
			<a class="image-main" href="${image.bigUrl}">
				<img alt="${image.title}" src="${image.mediumUrl}" />
			</a>
			<c:if test="${not empty image.taxon}">
			<div class="image-meta">
				
				<p>${image.taxon.defaultName }</p>
				<p>${image.author }</p>
					
				
				<c:choose>
				<c:when test="${image.rights == 'by-nc-sa' }">
					<p>
					<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/3.0/us/">
					
					<img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/3.0/us/80x15.png" />
				    </a> 
				    </p>
				</c:when>
				<c:when test="${image.rights != 'by-nc-sa' }">
					<p class="light"><fmt:message key="rightsReserved"/>		</p>		
				</c:when>
				</c:choose>				

			
				
			</div>
			</c:if>
		</div>
	</div>
</c:forEach>
</div>
</div>
