<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='jquery-ui'/>"/>
        

        <!-- Open Layers -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>

        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>

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
            
            //internacionalize the table pagination 
            var tableInfo = '<fmt:message key="table_pagination_info"/>';
            var tableFirst = '<fmt:message key="table_pagination_first"/>';
            var tableLast = '<fmt:message key="table_pagination_last"/>';
            var tableNext = '<fmt:message key="table_pagination_next"/>';
            var tablePrevious = '<fmt:message key="table_pagination_previous"/>';
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
                <h3><fmt:message key="scientific_name"/>: ${scientificname} </h3>
                <!-- Table Panel -->
                <div id="tablePanelContainer">
                    <!--<h4><fmt:message key="occurrences_table"/></h4>-->
                    <div id="occuPanel">

                    </div>
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
