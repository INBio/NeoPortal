<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>

        <!-- YUI js and css -->
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='dtspecies'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/fonts/fonts-min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/container/assets/container.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/paginator/assets/skins/sam/paginator.css"/>">

        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/container/container.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/dragdrop/dragdrop-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/animation/animation-min.js"/>"></script>        
        <script type="text/javascript" src="<c:url value="/resources/yui/button/button-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/paginator/paginator-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/event/event-min.js"/>"></script>

        <script>
            //Using to show the loading panel
            YAHOO.namespace("example.container");
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
        </script>

        <script type="text/javascript" src="<c:url value="/resources/species/home.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/species/getTotalSpecies.js" />"></script>

    </head>

    <body class="yui-skin-sam" onload="initSearch('${pageContext.request.contextPath}')">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <!-- Search panel -->
            <div id="searchPanel">
                <input id="searchInput" type="text" value="">
                <a id="simple" class="searchButton" href="javascript:homeSearch();"><fmt:message key="search"/></a>
            </div>

            <!-- Search explanation panel -->
            <div id="moduleExplanation">
                <div class="hd">Bienvenido al Portal de biodiversidad de INBio</div>
                <div class="bd">
                    <h3>Explorar especies:</h3>
                    <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.</p>
                    <h3>Explorar multimedios:</h3>
                    <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.</p>
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
