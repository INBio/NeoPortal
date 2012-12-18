<%@ tag description="Generate GroupNav Menu based on it attributes" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="groupNavList" description="The group nav list of objects" required="true" type="java.util.List" %>
<%@ attribute name="style" description="The group nav style to generated" required="true" %>
<%@ attribute name="gni" description="The group nav id for the selected element" required="false" %>

	<c:if test="${style=='grid'}">
<section id="groupNav" class="grid">
		<c:forEach var="groupNav" items="${groupNavList}">
			<div>
				<h2>
					<c:choose>
						<c:when test="${pageContext.request.locale.language == 'es'}">
							${groupNav.name}
						</c:when>
						<c:otherwise>
							${groupNav.nameEng}
						</c:otherwise>
					</c:choose>
				</h2>
				<a href="<c:out value="${pageContext.request.contextPath}"/>/groupNav?gni=${groupNav.groupNavId}">
					<img alt="${groupNav.name}" src="${groupNav.imageUrl}">
				</a>
			</div>
		</c:forEach>
</section>
	</c:if>
	
	<c:if test="${style=='aside'}">
	<section id="groupNav" class="aside">
		<%-- tag that build a tree recursively --%>
		<n:groupNavTree groupNavList="${groupNavList}" gni="${gni}"></n:groupNavTree>
	</section>
	</c:if>
