<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet_2'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='jquery-ui'/>"/>

        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.inbPaginate-0.1.js" />" ></script>

        <script type="text/javascript">
            
            //internacionalize the table pagination 
            var tableInfo = '<fmt:message key="table_pagination_info"/>';
            var tableFirst = '<fmt:message key="table_pagination_first"/>';
            var tableLast = '<fmt:message key="table_pagination_last"/>';
            var tableNext = '<fmt:message key="table_pagination_next"/>';
            var tablePrevious = '<fmt:message key="table_pagination_previous"/>';
            
            var noResultsFoundT = '<fmt:message key="noResultsFound"/>';
            
        </script>

		<script type="text/javascript" src="<c:url value="/resources/js/groupNav.js" />"></script>

    </head>

    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/views/header_inner.jsp"/>
        
        
        <div id="content">
        	<ul>
	            <c:forEach items="${groupNavList}" var="gn">
	            <li class="gn-firstLevel<c:if test="${gn.groupNavId == selectedGN}"> current</c:if>">
	            	<a id="gn_<c:out value='${gn.groupNavId}'/>" href="#"><fmt:message key="${gn.name}" /></a>
	            	<c:forEach items="${gn.groupNavChilds}" var="gnChild">
	            	<ul>
	            		<li class="gn-child">
	            			<a id="child_<c:out value='${gnChild.groupNavId}'/>" href="#"><fmt:message key="${gnChild.name}" /></a>
	            		</li>
	            	</ul>
	            	</c:forEach>
            	</li>
	            </c:forEach>
			</ul>
            <!-- Results panel -->
            <div id="resultsPanel" class="section">
                
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first">Inicio</span>
                    <span class="previousControl paginate_button" id="resultTable_previous">Anterior</span>
                    
                    <span class="nextControl paginate_button" id="resultTable_next">Siguiente</span>
                    <span class="lastControl paginate_button" id="resultTable_last">Último</span>
                </div>
                
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first">Inicio</span>
                    <span class="previousControl paginate_button" id="resultTable_previous">Anterior</span>
                    
                    <span class="nextControl paginate_button" id="resultTable_next">Siguiente</span>
                    <span class="lastControl paginate_button" id="resultTable_last">Último</span>
                </div>
            </div>
        </div> <!-- Contenido ends -->

        <!-- Footer -->
        <div id="footer">
            <div class="section">
                <fmt:message key="footer_text"/>
            </div>
        </div>
    </body>    
</html>
