//To determine and define table columns
var myColumnDefs = [ {
    key : "catalog",
    sortable : true,
    label : "# Catálogo"
}, {
    key : "institution",
    sortable : true,
    label : "Inst."
}, {
    key : "scientificname",
    sortable : true,
    label : "Nombre científico."
}, {
    key : "country",
    sortable : true,
    label : "País"
}, {
    key : "province",
    sortable : true,
    label : "Provincia"
}, {
    key : "county",
    sortable : true,
    label : "Condado"
}, /*{
    key : "locality",
    sortable : true,
    label : "Localidad"
},*/ {
    key : "latitude",
    sortable : false,
    label : "Lati."
}, {
    key : "longitude",
    sortable : false,
    label : "Long."
} ];
//Ocurrences table
var singleSelectDataTable;

// ----------------------------------------------------------------------------
//--------------------- Function that listen the YUI events -------------------
function globalListener(e) {
    //If the event comes from tablePanel
    if(this.id == 'occuPanel'){
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
            lati = content[6].innerHTML;
            longi = content[7].innerHTML;
            //Set the values on "selectedFeature" variable
            var attributes = createAttrib(nc,lati,longi,cata,inst);
            selectedFeature = new OpenLayers.Feature.Vector(
                new OpenLayers.Geometry.Point(longi,lati), attributes);

            //Show pop up on map
            onFeatureSelectFromTable(selectedFeature);
        }
        return;
    }
    //If the event comes from mapPanel
    if(this.id == 'mapPanel'){
        /*var mapCatalog = '';
        if(selectedFeature.attributes != null){
            mapCatalog = selectedFeature.attributes.Catalog;
        }
        alert('Comes from map \n'+'Catalog = '+mapCatalog);*/
        return;
    }
    //If the event comes from another DOM element
    else{
        alert('Comes from other -> '+this.id);
    }
}

// ----------------------------------------------------------------------------
//--------------------- To initialize the table -------------------------------
function initTable(searchString) {
    //Data source to get the information for filling the table
    var ssws = searchString.replace(' ','_'); //search string without spaces
	var myDataSource = new YAHOO.util.DataSource("../search/occurrences?searchString=scientificname:"+ ssws+"&format=xml");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
	myDataSource.useXPath = true;
	myDataSource.responseSchema = {
	        resultNode: "element",
	        fields: [{key:"gui"},{key:"scientificname"},{key:"country"},{key:"province"},{key:"county"},{key:"locality"},
	                 {key:"latitude", parser:"number"},{key:"longitude", parser:"number"},{key:"catalog", parser:"number"},
                     {key:"institution"}]
	        };

    singleSelectDataTable = new YAHOO.widget.ScrollingDataTable("occuPanel",
        myColumnDefs, myDataSource, {
            selectionMode:"single",
            width: "100%"
        });

    // Subscribe to events for row selection
    singleSelectDataTable.subscribe("rowMouseoverEvent", singleSelectDataTable.onEventHighlightRow);
    singleSelectDataTable.subscribe("rowMouseoutEvent", singleSelectDataTable.onEventUnhighlightRow);
    //Overwrite onEventSelectRow function
    singleSelectDataTable.onEventSelectRow = function(oArgs) {
        //------------- Taken from original function ---------------------
        var sMode = this.get("selectionMode");
        if(sMode == "single") {
            this._handleSingleSelectionByMouse(oArgs);
        }
        else {
            this._handleStandardSelectionByMouse(oArgs);
        }
        //-------------
        //Subscribe the event to the global listener
        var fromObj = document.getElementById('occuPanel');
        var myEvent = new YAHOO.util.CustomEvent("myEvent", fromObj);
        myEvent.subscribe(globalListener, fromObj);
        myEvent.fire();
    }
    // Also subscribe the overwrite method
    singleSelectDataTable.subscribe("rowClickEvent", singleSelectDataTable.onEventSelectRow);
}
