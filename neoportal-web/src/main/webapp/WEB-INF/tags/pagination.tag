<%@ tag description="Generate the pagination block" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="paginationFirstUrl" description="The url for first link" %>
<%@ attribute name="paginationNextUrl" description="The url for next link" %>
<%@ attribute name="paginationBackUrl" description="The back url link"%>
<%@ attribute name="paginationLastUrl" description="Url for the last page" %>
	
	<div class="pagination">
		<p><fmt:message key="pagination.info">
			<fmt:param value="${paginationCurrent}" />
			<fmt:param value="${paginationTotal}" />
		</fmt:message></p>
		<div>
			<a href="${paginationFirstUrl}"><fmt:message key="pagination.first" /></a>
			<c:choose>
				<c:when test="${paginationBackUrl != ''}">
					<a href="${paginationBackUrl}"><fmt:message key="pagination.back" /></a>
				</c:when>
				<c:otherwise>
					<a href="#" class="disable"><fmt:message key="pagination.back" /></a>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${paginationNextUrl != ''}">
			<a href="${paginationNextUrl}"><fmt:message key="pagination.next" /></a>
			</c:if>
			<a href="${paginationLastUrl}"><fmt:message key="pagination.last" /></a>
		</div>
	</div>