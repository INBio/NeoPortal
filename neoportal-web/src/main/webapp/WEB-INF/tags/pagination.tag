<%@ tag description="Generate the pagination block" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<c:if test="${pagination.totalPages > 1 }">
	<div class="paginationSpecie">
		<p><fmt:message key="pagination.info">
			<fmt:param value="${pagination.currentPage}" />
			<fmt:param value="${pagination.totalPages}" />
		</fmt:message></p>
		<div>
			<a href="${pagination.firstUrl}"><fmt:message key="pagination.first" /></a>
			<c:choose>
				<c:when test="${pagination.previousUrl != ''}">
					<a href="${pagination.previousUrl}"><fmt:message key="pagination.back" /></a>
				</c:when>
				<c:otherwise>
					<a href="#" class="disable"><fmt:message key="pagination.back" /></a>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${pagination.nextUrl != ''}">
					<a href="${pagination.nextUrl}"><fmt:message key="pagination.next" /></a>
				</c:when>
				<c:otherwise>
					<a href="#" class="disable"><fmt:message key="pagination.next" /></a>
				</c:otherwise>
			</c:choose>
			<a href="${pagination.lastUrl}"><fmt:message key="pagination.last" /></a>
		</div>
	</div>
</c:if>