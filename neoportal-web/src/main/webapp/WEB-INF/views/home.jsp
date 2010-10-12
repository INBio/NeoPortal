<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>
        <script type="text/javascript" src="<c:url value="/resources/holamundo.js" />"></script>

        <!-- yui js and css -->
        <link rel="stylesheet" type="text/css" href="resources/yui/datatable/assets/skins/sam/datatable.css">
        <link rel="stylesheet" type="text/css" href="resources/yui/fonts/fonts-min.css">
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js"/>"></script>

    </head>

    <body onload="initPage('${pageContext.request.contextPath}');"
          class="yui-skin-sam">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <!-- Search panel -->
            <div id="searchPanel">
                <input id="searchInput" type="text" value=""> 
                <a id="simple" href="javascript:holaMundo();">Search</a>
            </div>

            <!-- Results panel -->
            <div id="resultsPanel">
                <div id="tablePanel"></div>
            </div>
            
            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div> <!-- Contenido ends -->

    </body>    
</html>
