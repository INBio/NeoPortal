//ContextPath
var contextPath;
//YUI data table
var singleSelectDataTable;
//Total count of species by searchCriteria (Use on pagination)
var totalcount;
/*
 * This structure defines the requiered data to show in ocurrences page
 */
var myColumnDefs = [ {
    key : "cname",
    sortable : false,
    label : commonNameT
}, {
    key : "scname",
    sortable : false,
    label : scientificNameT
}, {
    key : "url",
    sortable : false,
    label : imageT,
    formatter: function(el, oRecord, oColumn, oData) {
        // el is the HTMLElement of the current cell
        // oRecord gives you access to other fields from the DataSource, e.g.: oRecord.getData('scname')
        // oData is the value of the current field
        if (oData != null) {
            el.innerHTML = '<img class="imagePreview" src="'+oData+'" alt="'+oData+'">';
        } else {
            el.innerHTML = '<img src="">';
        }
    }
} ];

/*
 * To initialize the page
 */
function initSearch(context){
    //Sets the focus over search input
    document.getElementById("searchInput").focus();
    //Set the variable that contains the context path
    contextPath = context;
    //Inits yui panel support
    initYUIPanels();
}

/*
 * To initialize YUI panel where the services gonna be shown
 */
function initYUIPanels(){
    if(!YAHOO.example.container.wait) {
        YAHOO.example.container.wait =
        new YAHOO.widget.Panel("wait",
        {
            width:"450px",
            fixedcenter:true,
            close:true,
            draggable:true,
            zindex:999,
            modal:true,
            visible:false
        });
    }
    if(!YAHOO.example.container.moduleExplanation){
        YAHOO.example.container.moduleExplanation =
            new YAHOO.widget.Module("moduleExplanation", {
            visible: false
        });
        YAHOO.example.container.moduleExplanation.show();
    }
}

/*
 * To show specific services on the YUI panel
 */
function showOnPanel(scName){
    //Create header
    YAHOO.example.container.wait.setHeader(scName);
    //Create body
    var content = '<p id="title">'+serviceTitleT+'</p>';
    content += createServiceUrl(contextPath+occurrencesUrl(scName),'service',seeMultimediaT);
    content += createServiceUrl(contextPath+speciesUrl(scName),'service',seeSpeciesT);
    content += createServiceUrl(contextPath+occurrencesUrl(scName),'service',seeOccurrencesT);
    content += '<p id="title">'+externalTitleT+'</p>';
    content += createExternalUrl(picasaUrl(scName),'picasa',seeOnPicasaT);
    content += createExternalUrl(flickrUrl(scName),'flickr',seeOnFlickrT);
    content += createExternalUrl(gbifUrl(scName),'gbif',seeOnGbifT);
    content += createExternalUrl(eolUrl(scName),'eol',seeOnEolT);
    content += createExternalUrl(wikiUrl(scName),'wikis',seeOnWikiST);

    YAHOO.example.container.wait.setBody(content);
    //Show the panel os services
    YAHOO.example.container.wait.render(document.getElementById('contenido'));
    YAHOO.example.container.wait.show();
}

/*
 * This function creates a specific url for a specific service
 */
function createServiceUrl(url,cssClass,title){
    return '<a href="'+url+'" class="'+cssClass+'">'+title+'</a><br><br>';
}
function createExternalUrl(url,cssClass,title){
    return '<a href="'+url+'" class="'+cssClass+'" target="_blank">'+
        title+'</a><br><br>';
}

/*
 * Functions to create the correct url's of external services
 */
function occurrencesUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for underscores
    var strSearch = cleanedInput.replace(" ","_");
    return '/occurrences/'+strSearch;
}

function speciesUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for underscores
    var strSearch = cleanedInput.replace(" ","_");
    return '/species/'+strSearch;
}


function picasaUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://picasaweb.google.com/lh/view?q='+strSearch+'#slideshow';
}
function flickrUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://www.flickr.com/search/show/?q='+strSearch;
}
function gbifUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    return 'http://data.gbif.org/search/'+cleanedInput;
}
function eolUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://www.eol.org/search?q='+strSearch;
}
function wikiUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","_");
    return 'http://species.wikimedia.org/wiki/'+strSearch;
}
function cleanInputSearch(input){
    //Trim the string
    var strTrimed = trim(input);
    //If the user types a couple of blank spaces amoung words
    return strTrimed.replace("  "," ");
}

/*
 * String Trim function
 */
function trim(string)
{
    var str = string.replace(/^\s*|\s*$/g,"");
    return str;
}

/*
 * Procced the search occurrences on data base
 */
function homeSearch() {
    //Hide the search explanation panel
    YAHOO.example.container.moduleExplanation.hide();
    //Get the input search string from textbox
    var searchString = document.getElementById('searchInput').value;
    //Replace the blank spaces for underscores (Requiered for search engine)
    var clearInput = cleanInputSearch(searchString);
    var input = clearInput.replace(" ","_");
    
    //Create the data source of yui table
    var myDataSource = createSpeciesDS(input);

    // DataTable configuration
    var myConfigs = createSpeciesConfigs();

    //Get total results to use it on pagination
    getTotalSpecies(contextPath,input);

    //Creates the new instance for species table
    singleSelectDataTable = new YAHOO.widget.DataTable("tablePanel",
        myColumnDefs, myDataSource, myConfigs);

    // Update totalRecords on the fly with value from server
    singleSelectDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = totalcount;
        return oPayload;
    }

    // Subscribe events for row selection
    singleSelectDataTable.subscribe("rowMouseoverEvent",singleSelectDataTable.onEventHighlightRow);
    singleSelectDataTable.subscribe("rowMouseoutEvent",singleSelectDataTable.onEventUnhighlightRow);

    // Overwrite onEventSelectRow function
    singleSelectDataTable.onEventSelectRow = function(oArgs) {
        // ------------- Taken from original function ---------------------
        var sMode = this.get("selectionMode");
        if (sMode == "single") {
            this._handleSingleSelectionByMouse(oArgs);
        } else {
            this._handleStandardSelectionByMouse(oArgs);
        }//----------------------------------------------------------------
        //Get the scientific name of selected row
        var selectedRow = this.getSelectedTrEls()[0];
        var content = selectedRow.getElementsByTagName('div');
        var scName= content[1].innerHTML; //Getting the scientific name
        //Call function that renders the services
        showOnPanel(scName);
    }

    //Finally subscribe the overwrite method
    singleSelectDataTable.subscribe("rowClickEvent",
        singleSelectDataTable.onEventSelectRow);
}

/**
 * Yui data table pagination config
 */
function createSpeciesConfigs(){
    var myConfigs = {
        // Initial request for first page of data
        initialRequest: "sort=cname&dir=asc&startIndex=0&results=10",
        dynamicData: true, // Enables dynamic server-driven data
        // Sets UI initial sort arrow
        sortedBy : {
            key:"cname",
            dir:YAHOO.widget.DataTable.CLASS_ASC
        },
        // Enables pagination
        paginator: new YAHOO.widget.Paginator({
            rowsPerPage:10
        }),
        selectionMode : "single"
    };
    return myConfigs;
}

/**
 * Creates the species data source for results table
 */
function createSpeciesDS(searchString){
    var myDataSource = new YAHOO.util.DataSource("search/taxa?searchString="+searchString+"&format=xml&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
    myDataSource.useXPath = true;
    myDataSource.responseSchema = {
        resultNode: "element",
        fields: [{
            key:"cname"
        },{
            key:"scname"
        },{
            key:"url"
        }],
        metaFields: {
            // Access to value in the server response
            totalRecords: 'totalrecords'
        }
    };
    return myDataSource;
}
