<%-- 
    Document   : taxonDescription
    Created on : 10/05/2011, 04:20:34 PM
    Author     : avargas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>
        
        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>

        <!-- YUI -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/datatable/assets/skins/sam/datatable.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/fonts/fonts-min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/paginator/assets/skins/sam/paginator.css"/>">
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo/yahoo-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/event/event-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/paginator/paginator-min.js"/>"></script>
        
        <!-- Util javaScript -->
          
        <script type="text/javascript" src="<c:url value="/resources/species/taxon-description-behavior.js" />"></script>
        <script type="text/javascript" >
            var scientificName = "${scientificName}";
            var provider = "";
            var contextPath = "${pageContext.request.contextPath}";
            
            
        </script>
        
    </head>
    <body>
         <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <div id="content">
                <div id="tabsContainer"></div>
            </div> <!-- end content -->
            
            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div> <!-- Contenido ends -->
    </body>
</html>
