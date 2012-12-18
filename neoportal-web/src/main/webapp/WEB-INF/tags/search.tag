<%@ tag description="Generate species list from a search result" language="java" 
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="items" description="The species list" required="true" type="java.util.List" %>
<%@ attribute name="paginated" description="Require pagination or not" required="true" type="java.lang.String" %>

	<!-- Search result -->
		<section id="search">
            <!-- Results panel -->
            <div id="resultsPanel" class="section">
                
            <c:if test="${paginated == 'Y'}">
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first"><fmt:message key="table_pagination_first" /></span>
                    <span class="previousControl paginate_button" id="resultTable_previous"><fmt:message key="table_pagination_back" /></span>
                    
                    <span class="nextControl paginate_button" id="resultTable_next"><fmt:message key="table_pagination_next" /></span>
                    <span class="lastControl paginate_button" id="resultTable_last"><fmt:message key="table_pagination_last" /></span>
                </div>
            </c:if>
                
                <div id="tablePanel">
                	<c:forEach var="item" items="${items}">
                	<div>
                		<img alt="${item.scientificName}" src="${item.imageURL}" />
                		<h2>${item.scientificName}</h2>
                		<p>${item.commonName}</p>
                	</div>
                	</c:forEach>
                </div>
                
            <c:if test="${paginated == 'Y'}">
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first"><fmt:message key="table_pagination_first" /></span>
                    <span class="previousControl paginate_button" id="resultTable_previous"><fmt:message key="table_pagination_back" /></span>
                    
                    <span class="nextControl paginate_button" id="resultTable_next"><fmt:message key="table_pagination_next" /></span>
                    <span class="lastControl paginate_button" id="resultTable_last"><fmt:message key="table_pagination_last" /></span>
                </div>
            </c:if>
                
                <div id="externalResult">
                    <h4>Recursos Externos</h4>
                </div>
                
            </div>
        </section>