<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='jquery-ui'/>"/>
        
        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>

        <script type="text/javascript" src="<c:url value="/resources/species/getTotalSpecies.js" />"></script>
        
        <script type="text/javascript">
            
            //Variables to internacionalize the service titles
            var serviceTitleT = '<fmt:message key="service_title"/>';
            var seeOccurrencesT = '<fmt:message key="see_occurrences"/>';
            var seeMultimediaT = '<fmt:message key="see_multimedia"/>';
            var seeSpeciesT = '<fmt:message key="see_species"/>';
            var externalTitleT = '<fmt:message key="external_services"/>';
            var seeOnFlickrT = '<fmt:message key="see_on_flickr"/>';
            var seeOnPicasaT = '<fmt:message key="see_on_picasa"/>';
            var seeOnGbifT = '<fmt:message key="see_on_gbif"/>';
            var seeOnEolT = '<fmt:message key="see_on_eol"/>';
            var seeOnWikiST = '<fmt:message key="see_on_wiki"/>';            
            var commonNameT = '<fmt:message key="common_name"/>';
            var scientificNameT = '<fmt:message key="scientific_name"/>';
            var imageT = '<fmt:message key="image"/>';
            
            //internacionalize the table pagination 
            var tableInfo = '<fmt:message key="table_pagination_info"/>';
            var tableFirst = '<fmt:message key="table_pagination_first"/>';
            var tableLast = '<fmt:message key="table_pagination_last"/>';
            var tableNext = '<fmt:message key="table_pagination_next"/>';
            var tablePrevious = '<fmt:message key="table_pagination_previous"/>';
            
            
        </script>

        <script type="text/javascript" src="<c:url value="/resources/species/home.js" />"></script>
        
    </head>

    <body class="yui-skin-sam" onload="initSearch('${pageContext.request.contextPath}')">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <!-- Search panel -->
            <div id="searchPanel">
                <input id="searchInput" type="text" value="">
                <a id="simple" class="searchButton" href="#"><fmt:message key="search"/></a>
            </div>

            <!-- Search explanation panel -->
            <div id="moduleExplanation">
                <div class="hd"><fmt:message key="home_welcome"/></div>
                <div class="bd">
                    <p><fmt:message key="home_help"/></p>
                </div>
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
