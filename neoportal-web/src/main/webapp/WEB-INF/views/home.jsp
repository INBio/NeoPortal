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

    <body onload="initSearch('${pageContext.request.contextPath}')">
        <!-- Header -->
        <jsp:include page="/WEB-INF/views/header_inner.jsp"/>
        
        
        <div id="content">
            <!-- Search panel -->
            <div id="searchPanel" class="data_wrapper">
                <input id="searchInput" type="text" value="">
                <a id="simple" class="searchButton" href="#"><fmt:message key="search"/></a>
                <a href="advancedSearch/"><fmt:message key="advanced_search"/></a>
            </div>

            <!-- Search explanation panel -->
            <div id="moduleExplanation" class="section">
                <div class="hd"><fmt:message key="home_welcome"/></div>
                <div class="bd">
                    <p><fmt:message key="home_help"/></p>
                </div>
            </div>

            <!-- Results panel -->
            <div id="resultsPanel" class="section">
                
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first">Inicio</span>
                    <span class="previousControl paginate_button" id="resultTable_previous">Anterior</span>
                    <!--
                    <span class="paginate_numbers">
                        <span class="paginate_active">1</span>
                        <span class="paginate_button">2</span>
                        <span class="paginate_button">3</span>
                        <span class="paginate_button">4</span>
                        <span class="paginate_button">5</span></span>
                        -->
                    <span class="nextControl paginate_button" id="resultTable_next">Siguiente</span>
                    <span class="lastControl paginate_button" id="resultTable_last">Último</span>
                </div>
                
                <div id="externalResult">
                    <h4>Recursos Externos</h4>
                </div>
                <div id="tablePanel"></div>
                
                <div class="home_paginate dataTables_paginate paging_full_numbers">
                    <span class="firstControl paginate_button" id="resultTable_first">Inicio</span>
                    <span class="previousControl paginate_button" id="resultTable_previous">Anterior</span>
                    <!--
                    <span class="paginate_numbers">
                        <span class="paginate_active">1</span>
                        <span class="paginate_button">2</span>
                        <span class="paginate_button">3</span>
                        <span class="paginate_button">4</span>
                        <span class="paginate_button">5</span></span>
                        -->
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
