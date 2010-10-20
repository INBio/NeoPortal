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

//Initialazing the gis functionality
function initMap(divId){
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
    map.addControl(new OpenLayers.Control.PanZoomBar({position: new OpenLayers.Pixel(2, 15)}));
    map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending':false},{'position':OpenLayers.Control}));
    map.addControl(new OpenLayers.Control.Navigation());
    map.addControl(new OpenLayers.Control.Scale($('scale')));
    map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
    //Add a simpgle point
    var attributes = createAttrib('Neurolaena lobata','8.58187','-83.49861','3385445','INB');
    addPoint('-83.49861','8.58187',attributes);
    attributes = createAttrib('Mollinedia costaricensis','9.67361','-83.026389','3107701','INB');
    addPoint('-83.026389','9.67361',attributes);
    attributes = createAttrib('Cymbopetalum torulosum','9.7425','-84.376667','3101956','INB');
    addPoint('-84.376667','9.7425',attributes);
    //Set up a control for specimens pop ups
    selectControl = new OpenLayers.Control.SelectFeature(vectorLayer,
    {onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
    map.addControl(selectControl);
    selectControl.activate();
}

//Creates a new atributes array for each speciemns point
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

//This function adds a new point to the specimens Layer
function addPoint(x, y, attribute) {
    var feature = new OpenLayers.Feature.Vector(
    new OpenLayers.Geometry.Point(x, y), attribute);
    vectorLayer.addFeatures(feature);
}

// Event onFeatureSelect (When especific specimen point was selected)
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

// When ocurrence is selected from table
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

//Event on specimen Popup Close
function onPopupClose(evt) {
    selectControl.unselect(selectedFeature);
}

//Event onFeatureUnselect
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