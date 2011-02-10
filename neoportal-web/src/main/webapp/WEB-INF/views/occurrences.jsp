<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>

        <!-- Open Layers -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>

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

        <!-- To internacionalize js texts -->
        <script>
            var scientificNameT = '<fmt:message key="scientific_name"/>';
            var institutionT = '<fmt:message key="institution"/>';
            var catalogT = '<fmt:message key="catalog_number"/>';
            var countryT = '<fmt:message key="country"/>';
            var provinceT = '<fmt:message key="province"/>';
            var countyT = '<fmt:message key="county"/>';
            var latitudeT = '<fmt:message key="latitude"/>';
            var longitudeT = '<fmt:message key="longitude"/>';
        </script>

        <!-- Util javaScript -->
        <script type="text/javascript" src="<c:url value="/resources/occurrences/map-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/occurrences/yui-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/occurrences/getTotalOccurrences.js" />"></script>

        <!-- Google maps -->
        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAGtIHQJm1-pS3ci26k9D7hRRURa6X8semXTOqalZdyJcp_MFd9RS_0fj31egxhzrJ1gql_bQ3Rcc7Qw" type="text/javascript"></script>
    </head>

    <body class="yui-skin-sam" onload="initOccurrences('${pageContext.request.contextPath}','${scientificname}')">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <div id="content">
                <h2><fmt:message key="scientific_name"/>: ${scientificname} </h2>
                <!-- Table Panel -->
                <div id="tablePanelContainer">
                    <!--<h4><fmt:message key="occurrences_table"/></h4>-->
                    <div id="occuPanel"></div>
                </div>
                <!-- Map Panel -->
                <div id="mapPanel">
                    <div id="map"> </div>
                    <div id="wrapper">
                        <div id="location"></div>
                        <div id="scale"></div>
                    </div>
                </div>
                <!-- Extra Panel -->
                <div id="extraPanel"></div>
            </div>
            
            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div> <!-- Contenido ends -->

    </body>    
</html>
