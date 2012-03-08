<%-- 
    Document   : advanceSearch
    Created on : 18/01/2012, 11:35:28 AM
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
<!--        <script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>-->
        
        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
        
        

        <!-- Util javaScript -->
          
        <script type="text/javascript" >
            var scientificName = "${scientificName}";
            var provider = "";
            var contextPath = "${pageContext.request.contextPath}"; 
            
            //labels
//            var scientificNameT = '<fmt:message key="scientific_name"/>';
//            var institutionT = '<fmt:message key="institution"/>';
//            var catalogT = '<fmt:message key="catalog_number"/>';
//            var countryT = '<fmt:message key="country"/>';
//            var provinceT = '<fmt:message key="province"/>';
//            var countyT = '<fmt:message key="county"/>';
//            var latitudeT = '<fmt:message key="latitude"/>';
//            var longitudeT = '<fmt:message key="longitude"/>';
            
            //Info labels
//            var naturalHistoryT = '<fmt:message key="taxonDes_naturalHistory"/>';
//            var habitatDistributionT = '<fmt:message key="taxonDes_habitatDistribution"/>';
//            var demographyConservationT = '<fmt:message key="taxonDes_demographyConservation"/>';
//            var usesManagementT = '<fmt:message key="taxonDes_usesManagement"/>';
//            var descriptionT = '<fmt:message key="taxonDes_description"/>';
//            var documentationT = '<fmt:message key="taxonDes_documentation"/>';
//            var informationT = '<fmt:message key="taxonDes_information"/>';
        </script>
        
        <script type="text/javascript" src="<c:url value="/resources/js/advancedSearch.js" />" ></script>

    </head>
    <body>
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header_inner.jsp"/>

            <div id="content">

                                            
            </div> <!-- end content -->
            
            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
    </body>
</html>
