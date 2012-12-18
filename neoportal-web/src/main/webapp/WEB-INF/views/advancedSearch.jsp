<%-- 
    Document   : advanceSearch
    Created on : 17/12/2012, 17:27:28 PM
    Author     : avargas
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>
<n:base>
	<jsp:attribute name="head">
		<script type="text/javascript">
			var scientificName = "${scientificName}";
	        var provider = "";
	        var contextPath = "${pageContext.request.contextPath}"; 
		</script>

        <script type="text/javascript" src="<c:url value="/resources/js/advancedSearch.js" />" ></script>
	
	</jsp:attribute>
	<jsp:body>
        <div id="content">
                <div class="leftPanel">
                    <div class="controls">
                        <a id="searchBtn" href="#">search</a>
                        <a id="exportBtn" href="#">export</a>
                        <div id="countLabel"></div>
                    </div>
                </div>
                 
                <form:form id="exportForm" action="${pageContext.request.contextPath}/api/advancedSearch/exportOccurrences">                      
           			<input type="hidden" name="filterSDTO" id="filterSDTO" />
            	</form:form>
            </div> <!-- end content -->
	</jsp:body>
</n:base>