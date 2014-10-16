if (typeof OpenLayers !== "undefined") {
    //Use a proxy for GeoServer requesting
    OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";
    //Pink tile avoidance
    OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
    //Make OL compute scale according to WMS spec
    OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
}
//GLOBAL VARIABLES DECLARATION
var map;
//Control to manage pop ups on the map
var selectControl;
//Current selected especimen point into the map
var selectedFeature;
//Layer to show specimens points
var vectorLayer;
//cantidad de features del cluster de mayor tamaño
var clusterMaxLenght;

/*
 * This function initializes the gis functionality for occurrences page
 */
function initMap2(divId){
    map = new OpenLayers.Map( {
        div: divId,
        projection: new OpenLayers.Projection("EPSG:900913")
    });
    
    var googleLayer = new OpenLayers.Layer.Google('Google Hybrid', {
                        type: google.maps.MapTypeId.TERRAIN,
                        numZoomLevels: 15,
                        minZoomLevel: 2
                });
    
    //cluster strategy
    var strategy = new OpenLayers.Strategy.Cluster({distance: 5});
    
    //estilo del mapa para uso de clusters
    var style = new OpenLayers.Style({
        pointRadius: "10",
        fillColor: "${fillColor}",
        fillOpacity: 0.7,
        strokeOpacity: 0,
        label: "${label}",
        fontColor: "#555"
        },
        {
            context: {
                /*radius: function(feature){
                    return Math.min(feature.attributes.count, 16) + 8;
                },*/
                fillColor: "#0000ff",
//                	function(feature) {
//
//                    var r = Math.round( 255 * feature.cluster.length / clusterMaxLenght);
//                    var g = Math.round(Math.sin(Math.PI * feature.cluster.length / clusterMaxLenght) * 255);
//                    var b = Math.round( 255 * (clusterMaxLenght - feature.cluster.length) / clusterMaxLenght);
//
//                    return "rgb(" + r + "," + g + "," + b + ")";
//                },
                label: ""
//                	function(feature){ 
//                    return feature.cluster.length;
//                }
            }
        }
    );
    
    //Specimens layer
    vectorLayer = new OpenLayers.Layer.Vector('Specimens',{
//        strategies: [ strategy ],
        styleMap: new OpenLayers.StyleMap({
            "default": style,
            "select": style
        })
    });
    vectorLayer.setVisibility(false);
    
    map.addLayers([googleLayer, vectorLayer]);
    
    //Set up a control for specimens pop ups
    selectControl = new OpenLayers.Control.SelectFeature(
        vectorLayer,
        {
            onSelect: onFeatureSelect, 
            onUnselect: onFeatureUnselect,
            multiple: false
        }
    );
    map.addControl(selectControl);
    selectControl.activate();
    
}

/*
 * Creates a new atributes array for each speciemns point
 */
function createAttrib(occurrenceId, scientificName,latitude,longitude,catalog,institution) {
    attrib = {
        OccurrenceId: occurrenceId,
        ScientificName: scientificName,
        Latitude: latitude,
        Longitude: longitude,
        Catalog: catalog,
        Institution: institution
    };
    return attrib;
}

/*
 * This function create a new point feature
 */
function createPoint(x, y, attribute) {
    var point = new OpenLayers.Geometry.Point(x, y);
    //transform lat/lon to google projection, a Mercator projection variant
    point.transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
    var feature = new OpenLayers.Feature.Vector(
            point , attribute);
    //vectorLayer.addFeatures(feature);
    return feature;
}

/*
 * Event onFeatureSelect (When especific specimen point was selected)
 * Momentarily this functionallity is disable because of the occurrences
 * selectable row option, that shows the pop up too
 */
function onFeatureSelect(feature) {
    selectedFeature = feature;
    
    if(!selectedFeature.cluster){
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
    }
    else{
        popup = new OpenLayers.Popup.FramedCloud("point",
        feature.geometry.getBounds().getCenterLonLat(),
        null,
        "<div style=\"font-size:.8em\">"+
        "<br><b>Cluster con "+
            feature.cluster.length+
            " ocurrencias</b>"+
            "</div>",
        null, true, onPopupClose);
        feature.popup = popup;
        map.addPopup(popup);
    }
    
    //mark features on table
    selectRowsFromMap(selectedFeature.cluster);
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
    
    //unselect table rows
    unselectAllRows();
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
function showSpecimenPoints(xmlDoc)  {
        //clear the vector layer
        vectorLayer.destroyFeatures();
        
        //Get the list of specimens
        var specimenList = xmlDoc.getElementsByTagName("element");
        //List of coordinates (to determine the posible bounderies)
        var latArray = new Array();
        var longArray = new Array();
        var points = [];
        //Add all the specimen point
        for(var i = 0;i<specimenList.length;i++){
            var catalog,latitude,longitude,scientificname,institution, occurrenceId;
            var node = specimenList[i];
            if(node.getElementsByTagName("occurrenceId")[0] != null){
                occurrenceId = node.getElementsByTagName("occurrenceId")[0].childNodes[0].nodeValue;
            }
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
            attributes = createAttrib(occurrenceId,scientificname,latitude,longitude,catalog,institution);
            points.push( createPoint(longitude,latitude,attributes) );
            latArray.push(parseFloat(latitude));
            longArray.push(parseFloat(longitude));
        }

        //Zooming on the correct geographical area (deppending on results)
        var minX = getMinX(longArray);
        var minY = getMinY(latArray);
        var maxX = getMaxX(longArray);
        var maxY = getMaxY(latArray);
        var bounds = new OpenLayers.Bounds();
        bounds.extend(new OpenLayers.LonLat(minX, minY));
        bounds.extend(new OpenLayers.LonLat(maxX, maxY));
        bounds.transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913"));
        map.maxExtent = bounds;
        map.zoomToExtent(bounds);
        
        vectorLayer.addFeatures(points);
        
//        clusterMaxLenght = getMaxCluster();
        
        vectorLayer.setVisibility(true);

}

/*
 * Retorna el largo del mayor cluster
 */
function getMaxCluster(){
    var arrayCluster = vectorLayer.features;
    var maxLength = 0;
    
    
    for( i=0; i<arrayCluster.length; i++){
        if(arrayCluster[i].cluster.length > maxLength){
            maxLength = arrayCluster[i].cluster.length;
        }
    }
    
    return maxLength;
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
