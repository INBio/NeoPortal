<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>
        <script type="text/javascript" src="<c:url value="/resources/home.js" />"></script>

        <!-- yui js and css -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/datatable/assets/skins/sam/datatable.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/fonts/fonts-min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/container/assets/container.css"/>">
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/container/container.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/dragdrop/dragdrop-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/animation/animation-min.js"/>"></script>

        <script>
            //Using to show the loading panel
            YAHOO.namespace("example.container");
        </script>

    </head>

    <body class="yui-skin-sam" onload="initSearch('${pageContext.request.contextPath}')">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <!-- Search panel -->
            <div id="searchPanel">
                <input id="searchInput" type="text" value=""> 
                <a id="simple" href="javascript:homeSearch();"><fmt:message key="search"/></a>
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
