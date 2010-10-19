//where to sent the requests?
var urlContext;
var singleSelectDataTable;


/*
 * Sets the initial values for the page
 */
function initPage(urlContextValue) {

	if (urlContextValue != "")
		urlContext = urlContextValue;
}


function homeSearch() {

	var searchString = document.getElementById('searchInput').value;
	
	var myColumnDefs = [ {
	    key : "image",
	    sortable : true,
	    label : "Image"
	}, {
	    key : "scientificname",
	    sortable : true,
	    label : "Nombre cient."
	}, {
	    key : "services",
	    sortable : true,
	    label : "Servicios"
	 } ];
	
	var myDataSource = new YAHOO.util.DataSource("search/species?searchString="+ searchString+"&format=xml");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
	myDataSource.useXPath = true;
	myDataSource.responseSchema = {
			//metaFields: {rootatt:"/neoportal-response/@rootatt", topnode:"//top", nestedatt:"//second/@nested"},
	        resultNode: "element",
	        fields: [{key:"gui"},{key:"scientificname"},{key:"country"},{key:"province"},{key:"county"},{key:"locality"},
	                 {key:"latitude", parser:"number"},{key:"longitude", parser:"number"}]
	        };
	
	var singleSelectDataTable = new YAHOO.widget.DataTable("tablePanel", 
			myColumnDefs, myDataSource, {
				selectionMode : "single"
			});

	// Subscribe to events for row selection
	singleSelectDataTable.subscribe("rowMouseoverEvent",
			singleSelectDataTable.onEventHighlightRow);
	singleSelectDataTable.subscribe("rowMouseoutEvent",
			singleSelectDataTable.onEventUnhighlightRow);
	// Overwrite onEventSelectRow function
	singleSelectDataTable.onEventSelectRow = function(oArgs) {
		// ------------- Taken from original function ---------------------
		var sMode = this.get("selectionMode");
		if (sMode == "single") {
			this._handleSingleSelectionByMouse(oArgs);
		} else {
			this._handleStandardSelectionByMouse(oArgs);
		}
		// ------------- Taken from original function ---------------------
	}
	// Also subscribe the overwrite method
	singleSelectDataTable.subscribe("rowClickEvent",
			singleSelectDataTable.onEventSelectRow);
}