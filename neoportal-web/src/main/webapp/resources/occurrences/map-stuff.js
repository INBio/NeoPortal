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

/*
 * This function initializes the gis functionality for occurrences page
 */
function initMap(divId,searchString){
    var initialbounds = new OpenLayers.Bounds(
        -86.109, 8.377,
        -82.555, 11.221
    );
    var options = {
        controls: [],
        maxResolution: 0.09776171875,
        projection: "EPSG:900913",
        units: 'm'
    };
    var myMapDiv = document.getElementById(divId);
    map = new OpenLayers.Map(options);
    map.render(myMapDiv);
    //Base layer
    googleLayer  = new OpenLayers.Layer.Google('Google Hybrid', {type: G_HYBRID_MAP });
    //Specimens layer
    vectorLayer = new OpenLayers.Layer.Vector('Specimens');
    vectorLayer.setVisibility(true);
    //Adding layers
    map.addLayer(vectorLayer);
    map.addLayer(googleLayer);
    //Build up all controls
    map.zoomToExtent(initialbounds);
    map.addControl(new OpenLayers.Control.PanZoomBar
    ({position: new OpenLayers.Pixel(2, 15)}));
    map.addControl(new OpenLayers.Control.LayerSwitcher
    ({'ascending':false},{'position':OpenLayers.Control}));
    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.Scale($('scale')));
    map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
    //Add occurrences points into the map
    showSpecimenPoints(searchString);
    /*/Set up a control for specimens pop ups
    selectControl = new OpenLayers.Control.SelectFeature(vectorLayer,
    {onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
    map.addControl(selectControl);
    selectControl.activate();*/
}

/*
 * Creates a new atributes array for each speciemns point
 */
function createAttrib(scientificName,latitude,longitude,catalog,institution) {
    attrib = {
        ScientificName: scientificName,
        Latitude: latitude,
        Longitude: longitude,
        Catalog: catalog,
        Institution: institution
    }
    return attrib;
}

/*
 * This function adds a new point to the specimens Layer
 */
function addPoint(x, y, attribute) {
    var feature = new OpenLayers.Feature.Vector(
    new OpenLayers.Geometry.Point(x, y), attribute);
    vectorLayer.addFeatures(feature);
}

/*
 * Event onFeatureSelect (When especific specimen point was selected)
 * Momentarily this functionallity is disable because of the occurrences
 * selectable row option, that shows the pop up too
 */
function onFeatureSelect(feature) {
    selectedFeature = feature;
    popup = new OpenLayers.Popup.FramedCloud("point",
    feature.geometry.getBounds().getCenterLonLat(),
    null,
    "<div style=\"font-size:.8em\">"+
    "<br><b>Nombre científico: </b>"+feature.attributes.ScientificName+
    "<br><b>Institución: </b>"+feature.attributes.Institution+
    "<br><b># de catálogo: </b>"+feature.attributes.Catalog+
    "<br><b>Latutud: </b>"+feature.attributes.Latitude+
    "<br><b>Longitud: </b>"+feature.attributes.Longitude+"</div>",
    null, true, onPopupClose);
    feature.popup = popup;
    map.addPopup(popup);
    //Define a custom event and throw the event to the global listener
    var fromObj = document.getElementById('mapPanel');
    var myEvent = new YAHOO.util.CustomEvent("myEvent", fromObj);
    myEvent.subscribe(globalListener, fromObj);
    myEvent.fire();
}

/*
 * When ocurrence is selected from table insted of map
 */
function onFeatureSelectFromTable(feature) {
    popup = new OpenLayers.Popup.FramedCloud("point",
    feature.geometry.getBounds().getCenterLonLat(),
    null,
    "<div style=\"font-size:.8em\">"+
    "<br><b>Nombre científico: </b>"+feature.attributes.ScientificName+
    "<br><b>Institución: </b>"+feature.attributes.Institution+
    "<br><b># de catálogo: </b>"+feature.attributes.Catalog+
    "<br><b>Latutud: </b>"+feature.attributes.Latitude+
    "<br><b>Longitud: </b>"+feature.attributes.Longitude+"</div>",
    null, true, clearPopups);
    feature.popup = popup;
    map.addPopup(popup);
}

/*
 * Event on specimen Popup Close
 */
function onPopupClose(evt) {
    selectControl.unselect(selectedFeature);
}

/*
 * Event onFeatureUnselect
 */
function onFeatureUnselect(feature) {
    map.removePopup(feature.popup);
    feature.popup.destroy();
    feature.popup = null;
}

/*
 * Deletes all the current popups on the map
 */
function clearPopups(){
    for (var i=0; i<map.popups.length; i++) {
        map.removePopup(map.popups[i]);
    }
}

/*
 * Ajax request to show occurrences on the map
 */
function showSpecimenPoints(searchString)  {
    //Prepare URL for XHR request:
    var sUrl = "../search/occurrences?searchString="+searchString+
        "&format=xml&sort=scientificname&dir=asc&startIndex=0&results=20";

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of specimens
            var specimenList = xmlDoc.getElementsByTagName("element");
            //List of coordinates (to determine the posible bounderies)
            var latArray = new Array();
            var longArray = new Array();
            //Add all the specimen point
            for(var i = 0;i<specimenList.length;i++){
                var catalog,latitude,longitude,scientificname,institution;
                var node = specimenList[i];
                if(node.getElementsByTagName("catalog")[0] != null){
                    catalog = node.getElementsByTagName("catalog")[0].childNodes[0].nodeValue;
                }
                if(node.getElementsByTagName("latitude")[0] != null){
                    latitude = node.getElementsByTagName("latitude")[0].childNodes[0].nodeValue;
                }
                if(node.getElementsByTagName("longitude")[0] != null){
                    longitude = node.getElementsByTagName("longitude")[0].childNodes[0].nodeValue;
                }
                if(node.getElementsByTagName("scientificname")[0] != null){
                    scientificname = node.getElementsByTagName("scientificname")[0].childNodes[0].nodeValue;
                }
                if(node.getElementsByTagName("institution")[0] != null){
                    institution = node.getElementsByTagName("institution")[0].childNodes[0].nodeValue;
                } 
                attributes = createAttrib(scientificname,latitude,longitude,catalog,institution);
                addPoint(longitude,latitude,attributes);
                latArray.push(parseFloat(latitude));
                longArray.push(parseFloat(longitude));
            }

            //Zooming on the correct geographical area (deppending on results)
            var minX = getMinX(longArray);
            var minY = getMinY(latArray);
            var maxX = getMaxX(longArray);
            var maxY = getMaxY(latArray);
            var bounds = new OpenLayers.Bounds(
                minX, minY, maxX, maxY);
            map.zoomToExtent(bounds);
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

/*
 * To get the minimun longitude
 */
function getMinX(longitudeList) {
    // Lets assume we are working with validated geographical coordinates, so -180 <= longitude <= l80
    var minX = 180.0;
    for (var i = 0;i<longitudeList.length;i++) {
        var x = longitudeList[i];
        if (x < minX) {
            minX = x;
        }
    }
    return minX;
}

/*
 * To get the minimun latitud
 */
function getMinY(latitudeList) {
    // Lets assume we are working with validated geographical coordinates, so -90 <= latitude <= 90
    var minY = 90.0;
    for (var i = 0;i<latitudeList.length;i++) {
        var y = latitudeList[i];
        if (y < minY) {
            minY = y;
        }
    }
    return minY;
}

/*
 * To get the maximun longitude
 */
function getMaxX(longitudList) {
    // Lets assume we are working with validated geographical coordinates, so -180 <= longitude <= l80
    var maxX = -180.0;
    for (var i = 0;i<longitudList.length;i++) {
        var x = longitudList[i];
        if (x > maxX) {
            maxX = x;
        }
    }
    return maxX;
}

/*
 * To get the maximun latitude
 */
function getMaxY(latitudeList) {
    // Lets assume we are working with validated geographical coordinates, so -90 <= latitude <= 90
    var maxY = -90.0;
    for (var i = 0;i<latitudeList.length;i++) {
        var y = latitudeList[i];
        if (y > maxY) {
            maxY = y;
        }
    }
    return maxY;
}