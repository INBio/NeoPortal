<%-- 
    Document   : taxonDescription
    Created on : 10/05/2011, 04:20:34 PM
    Last Modified: 19/04/2012
    Author     : avargas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="app_name"/></title>
        
        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet_2'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='jquery-ui'/>"/>
        
        <!-- Open Layers and Google Maps API-->
        <script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>
        
        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.xslt.js" />" ></script>

        <!-- Util javaScript -->
        <script type="text/javascript" >
            var scientificName = "${scientificName}";
            var provider = "";
            var contextPath = "${pageContext.request.contextPath}"; 
            
            //labels
            var scientificNameT = '<fmt:message key="scientific_name"/>';
            var institutionT = '<fmt:message key="institution"/>';
            var catalogT = '<fmt:message key="catalog_number"/>';
            var countryT = '<fmt:message key="country"/>';
            var provinceT = '<fmt:message key="province"/>';
            var countyT = '<fmt:message key="county"/>';
            var latitudeT = '<fmt:message key="latitude"/>';
            var longitudeT = '<fmt:message key="longitude"/>';
            
            //Info labels
            var naturalHistoryT = '<fmt:message key="taxonDes_naturalHistory"/>';
            var habitT = '<fmt:message key="taxonDes_habit"/>';
            var reproductionT = '<fmt:message key="taxonDes_reproduction"/>';
            var feedingT = '<fmt:message key="taxonDes_feeding"/>';
            var behaviorT = '<fmt:message key="taxonDes_behavior"/>';
            var annualCycleT = '<fmt:message key="taxonDes_annualCycle"/>';
            var lifeCycleT = '<fmt:message key="taxonDes_lifeCycle"/>';
            
            var habitatDistributionT = '<fmt:message key="taxonDes_habitatDistribution"/>';
            var habitatT = '<fmt:message key="taxonDes_habitat"/>';
            var distributionT = '<fmt:message key="taxonDes_distribution"/>';
            
            var demographyConservationT = '<fmt:message key="taxonDes_demographyConservation"/>';
            var threatStatusT = '<fmt:message key="taxonDes_threatStatus"/>';
            var territoryT = '<fmt:message key="taxonDes_territory"/>';
            var populationBiologyT = '<fmt:message key="taxonDes_populationBiology"/>';
            
            var usesManagementT = '<fmt:message key="taxonDes_usesManagement"/>';
            var usesT = '<fmt:message key="taxonDes_uses"/>';
            var managementT = '<fmt:message key="taxonDes_management"/>';
            
            var descriptionT = '<fmt:message key="taxonDes_description"/>';
            var scientificDescriptionT = '<fmt:message key="taxonDes_scientificDescription"/>';
            var briefDescriptionT = '<fmt:message key="taxonDes_briefDescription"/>';
            
            var documentationT = '<fmt:message key="taxonDes_documentation"/>';
            
            var informationT = '<fmt:message key="taxonDes_information"/>';
            var languageT = '<fmt:message key="taxonDes_language"/>';
            var authorT = '<fmt:message key="taxonDes_author"/>';
            var contributorsT = '<fmt:message key="taxonDes_contributors"/>';
            var taxonRecordIdT = '<fmt:message key="taxonDes_taxonRecordId"/>';
            var dateLastModifiedT = '<fmt:message key="taxonDes_dateLastModified"/>';
            var dateCreatedT = '<fmt:message key="taxonDes_dateCreated"/>';
        </script>
        
        <script type="text/javascript" src="<c:url value="/resources/occurrences/map-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/occurrences/dataTable-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/species/taxon-description-behavior.js" />"></script>
                
    </head>
    <body>
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header_inner.jsp"/>

            <div id="content">
                <div id="content_header">
                    <div id="content_header_inner" class="data_wrapper">
                        <h2>${scientificName}</h2>
                        <div id="tabsContainer">
                            <ul>
                                <li class="currentTab"><a href="#info"><fmt:message key="tab_taxonDescription"/></a></li>
<!--                                <li><a href="#images"><fmt:message key="tab_images"/></a></li>-->
                                <li><a href="#occurrences"><fmt:message key="tab_occurrences"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <div id="taxonDescription" class="container data_wrapper tab_wrapper">
                    <div id="menu-panel" class="left-column">
                        <ul id="menu-species" class="menu">
                            <li id="item-naturalHistory" class="menu-top"><fmt:message key="taxonDes_naturalHistory"/></li>
                            <li id="item-habitatDistribution" class="menu-top" style=""><fmt:message key="taxonDes_habitatDistribution"/></li>
                            <li id="item-demographyConservation" class="menu-top"><fmt:message key="taxonDes_demographyConservation"/></li>
                            <li id="item-usesManagement" class="menu-top" style=""><fmt:message key="taxonDes_usesManagement"/></li>
                            <li id="item-description" class="menu-top" style=""><fmt:message key="taxonDes_description"/></li>
                            <li id="item-information" class="menu-top" style=""><fmt:message key="taxonDes_information"/></li>
                            <li id="item-externalSource" class="menu-top" style=""><fmt:message key="taxonDes_externalSource"/></li>
                        </ul>
                    </div>
                    <div class="content-column">
                        <div class="naturalHistory data-panel">
                            <h3><fmt:message key="taxonDes_naturalHistory"/></h3>
                        </div>
                        
                        <div class="habitatDistribution data-panel">
                            <h3><fmt:message key="taxonDes_habitatDistribution"/></h3>
                        </div>
                        
                        <div class="usesManagement data-panel">
                            <h3><fmt:message key="taxonDes_usesManagement"/></h3>
                        </div>
                        
                        <div class="demographyConservation data-panel">
                            <h3><fmt:message key="taxonDes_demographyConservation"/></h3>
                        </div>
                        
                        <div class="description data-panel">
                            <h3><fmt:message key="taxonDes_description"/></h3>
                        </div>
                        
                        <div class="information data-panel">
                            <h3><fmt:message key="taxonDes_information"/></h3>
                        </div>
                        
                        <div class="externalSource data-panel">
                            <%-- TODO: get external sources from database --%>
                            <h3><fmt:message key="taxonDes_externalSource"/></h3>
                            <p><a href="http://www.biodiversitylibrary.org/name/${scientificName}">BHL</a></p>
                            <p><a href="http://ara.inbio.ac.cr/SSTN-IABIN/search/${scientificName}">IABIN</a></p>
                            <p><a href="http://cro.ots.ac.cr/rdmcnfs/datasets/exsrch.phtml?words=${scientificName}&ds=binabitrop">OET Binabitrop</a></p>
                            <p><a href="http://species.wikimedia.org/wiki/${scientificName}">Wikispecies</a></p>
                            <p><a href="http://www.boldsystems.org/views/taxbrowser.php?taxon=${scientificName}">Barcode of life data systems</a></p>
                        </div>
                    </div>
                </div>
                
                <div id="occurrences" class="tab_wrapper data_wrapper">
                    <ul id="view_control">
                        <li><a rel="map" href="#"><fmt:message key="occurrences_map_label" /></a></li>
                        <li><a rel="data" href="#"><fmt:message key="occurrences_data_label" /></a></li>
                        <li><a rel="split" href="#"><fmt:message key="occurrences_split_label" /></a></li>
                    </ul>
                    <!-- Table Panel -->
                    <div id="tablePanelContainer">
                        <!--<h4><fmt:message key="occurrences_table"/></h4>-->

                    </div>
                    <!-- Map Panel -->
                    <div id="mapPanel" class="extendMap">
                        <div id="map"></div>
<!--                        <div id="wrapper">
                            <div id="location"></div>
                            <div id="scale"></div>
                        </div>-->
                    </div>

                    <!-- Extra Panel -->
                    <div id="extraPanel"></div>
                </div>
            </div> <!-- end content -->
            
            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
    </body>
</html>
