<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/tags.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Occurrences</title>

        <!-- neoportal js and css -->
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='styleSheet'/>"/>

        <!-- Open Layers -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>


        <!-- YUI -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/datatable/assets/skins/sam/datatable.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/yui/fonts/fonts-min.css"/>">
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo/yahoo-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/event/event-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js" />"></script>

        <!-- Util javaScript -->
        <script type="text/javascript" src="<c:url value="/resources/occurrences/map-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/occurrences/yui-stuff.js" />"></script>

        <!-- Google maps -->
        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAGtIHQJm1-pS3ci26k9D7hRRURa6X8semXTOqalZdyJcp_MFd9RS_0fj31egxhzrJ1gql_bQ3Rcc7Qw" type="text/javascript"></script>

        <script defer="defer" type="text/javascript">

            //Use a proxy for GeoServer requesting
            OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";
            //Pink tile avoidance
            OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
            //Make OL compute scale according to WMS spec
            OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
            //GLOBAL VARIABLES DECLARATION
            var map;
            //Control to manage pop ups on the map
            var selectControl;
            //Current selected especimen point into the map
            var selectedFeature;
            //Layer to show specimens points
            var vectorLayer;
            //Ocurrences table
            var singleSelectDataTable;

            //Init function called in the body onload method
            function initOccurrences(){
                //To attach an event handler to multiple DOM elements
                var domElements = ["extraPanel"];
                YAHOO.util.Event.addListener(domElements, "click", globalListener);
                //Initialize open layers map
                initMap('map');
                //Initialize ocurrences table
                initTable();
            }

            //Function that listen the YUI events
            function globalListener(e) {
                //If the event comes from tablePanel
                if(this.id == 'tablePanel'){
                    //Clear map pop ups
                    clearPopups();
                    //If there is some selected row
                    if(singleSelectDataTable != null){
                        var selectedArray = singleSelectDataTable.getSelectedTrEls();
                        var selectedRow = selectedArray[0];
                        var content = selectedRow.getElementsByTagName('div');
                        var nc='',lati='',longi='',cata='',inst='';
                        cata = content[0].innerHTML;
                        inst = content[1].innerHTML;
                        nc = content[2].innerHTML;
                        lati = content[3].innerHTML;
                        longi = content[4].innerHTML;
                        //Set the values on "selectedFeature" variable
                        var attributes = createAttrib(nc,lati,longi,cata,inst);
                        selectedFeature = new OpenLayers.Feature.Vector(
                        new OpenLayers.Geometry.Point(longi,lati), attributes);

                        //Show pop up on map
                        onFeatureSelectFromTable(selectedFeature);
                    }
                    return;
                }
                if(this.id == 'mapPanel'){
                    var mapCatalog = '';
                    if(selectedFeature.attributes != null){
                        mapCatalog = selectedFeature.attributes.Catalog;
                    }
                    alert('Comes from map \n'+'Catalog = '+mapCatalog);
                    return;
                }
                else{
                    alert('Comes from other -> '+this.id);
                }
            }
        </script>

    </head>

    <body onload="initOccurrences()" class="yui-skin-sam">
              
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/views/header.jsp"/>

            <div id="content">
                <h2>Test - Pantalla de ocurrencias</h2>
                <!-- Table Panel -->
                <div id="tablePanelContainer">
                    <h3>Tabla de ocurrencias</h3>
                    <div id="tablePanel"></div>
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
                <div id="extraPanel">
                    <h4>Panel de prueba</h4>
                </div>
            </div>
            
        </div> <!-- Contenido ends -->

    </body>    
</html>
