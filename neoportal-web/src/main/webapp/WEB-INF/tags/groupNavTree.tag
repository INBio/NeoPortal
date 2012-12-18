<%@ tag description="Generate GroupNav tree recursively" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="groupNavList" description="The group nav list of objects" required="true" type="java.util.List" %>
<%@ attribute name="gni" description="The group nav id for the selected element" required="false" %>
	
		<ul>
		<c:forEach var="groupNav" items="${groupNavList}">
			<li class="gn-node<c:if test="${groupNav.groupNavId == gni}"> current</c:if>">
				<a href="${pageContext.request.contextPath}/groupNav?gni=${groupNav.groupNavId}" id="gn-node_${groupNav.groupNavId}">
					<c:choose>
						<c:when test="${pageContext.request.locale.language == 'es'}">
							${groupNav.name}
						</c:when>
						<c:otherwise>
							${groupNav.nameEng}
						</c:otherwise>
					</c:choose>
				</a>
				<!-- recursive call to make children for this branch -->
				<c:if test="${groupNav.groupNavChilds != null}">
					<n:groupNavTree groupNavList="${groupNav.groupNavChilds}" gni="${gni}"></n:groupNavTree>
				</c:if>
			</li>
		</c:forEach>
		</ul>
