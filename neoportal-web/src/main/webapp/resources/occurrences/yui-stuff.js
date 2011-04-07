//Ocurrences table
var singleSelectDataTable;
//Total count of occurrences by searchCriteria (Use on pagination)
var totalcount;
//ContextPath
var contextPath;

/*
 * This structure defines the requiered data to show in ocurrences page
 */
var myColumnDefs = [  {
    key : "scientificname",
    sortable : true,
    label : scientificNameT
}, {
    key : "institution",
    sortable : true,
    label : institutionT
}, {
    key : "country",
    sortable : true,
    label : countryT
}, {
    key : "province",
    sortable : true,
    label : provinceT
}, {
    key : "county",
    sortable : true,
    label : countyT
},  {
    key : "latitude",
    sortable : false,
    label : latitudeT
}, {
    key : "longitude",
    sortable : false,
    label : longitudeT
},{
    key : "catalog",
    sortable : true,
    label : catalogT
} ];


/*
 *Init function called in the body onload method
 */
function initOccurrences(context,scientificname){
    //Pass the context path to javascript scope
    contextPath = context;
    //Initialize ocurrences table
    initTable(scientificname);
    //Initialize open layers map
    initMap('map',scientificname);
}

/*
 * This function listens the diferent custom events thrown by yui framework
 */
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
            var nsc='',lati='',longi='',cata='',inst='';
            cata = content[7].innerHTML;
            inst = content[1].innerHTML;
            nsc = content[0].innerHTML;
            lati = content[5].innerHTML;
            longi = content[6].innerHTML;
            //Set the values on "selectedFeature" variable
            var attributes = createAttrib(nsc,lati,longi,cata,inst);
            selectedFeature = new OpenLayers.Feature.Vector(
                new OpenLayers.Geometry.Point(longi,lati), attributes);
            //Show pop up on map
            onFeatureSelectFromTable(selectedFeature);
        }
        return;
    }
    //If the event comes from mapPanel
    if(this.id == 'mapPanel'){
        //See onFeatureSelect() function on map-stuff.js
        return;
    }
    //If the event comes from another DOM element
    else{
        alert('Comes from other -> '+this.id);
    }
}

/*
 * This function initializes the table to show the list of ocurrences
 */
function initTable(searchString) {
    //Data source to get the information for filling the table
	var myDataSource = createOccurrencesDS(searchString);

    // DataTable configuration
    var myConfigs = createOccurrencesConfigs();

    //Get total results to use it on pagination
    getTotalOccurrences(contextPath,searchString);

    //Creates the new instance for species table
    singleSelectDataTable = new YAHOO.widget.ScrollingDataTable("occuPanel",
        myColumnDefs, myDataSource, myConfigs);

    // Update totalRecords on the fly with value from server
    singleSelectDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = totalcount;
        return oPayload;
    }

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
        //---------------------------------------------------------------
        //Subscribe the event to the global listener
        var fromObj = document.getElementById('occuPanel');
        var myEvent = new YAHOO.util.CustomEvent("myEvent", fromObj);
        myEvent.subscribe(globalListener, fromObj);
        myEvent.fire();
    }
    // Finally subscribe the overwrite method
    singleSelectDataTable.subscribe("rowClickEvent", singleSelectDataTable.onEventSelectRow);
}

/**
 * Creates the occurrences data source for results table
 */
function createOccurrencesDS(searchString){
    var myDataSource = new YAHOO.util.DataSource
    ("../search/occurrences?searchString="+searchString+"&format=xml&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
    myDataSource.useXPath = true;
    myDataSource.responseSchema = {
        resultNode: "element",
        fields: [{
            key:"gui"
        },{
            key:"scientificname"
        },{
            key:"country"
        },{
            key:"province"
        },{
            key:"county"
        },{
            key:"locality"
        },{
            key:"latitude",
            parser:"number"
        },{
            key:"longitude",
            parser:"number"
        },{
            key:"catalog"
        },{
            key:"institution"
        }]
    };
    return myDataSource;
}

/**
 * Yui data table pagination config
 */
function createOccurrencesConfigs(){
    var myConfigs = {
        // Initial request for first page of data
        initialRequest: "sort=scientificname&dir=asc&startIndex=0&results=15",
        dynamicData: true, // Enables dynamic server-driven data
        // Sets UI initial sort arrow
        sortedBy : {
            key:"scientificname",
            dir:YAHOO.widget.DataTable.CLASS_ASC
        },
        // Enables pagination
        paginator: new YAHOO.widget.Paginator({
            rowsPerPage:15
        }),
        selectionMode : "single",
        width: "100%"
    };
    return myConfigs;
}
