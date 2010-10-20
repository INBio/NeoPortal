//YUI data table
var singleSelectDataTable;
//To determine and define table columns
var myColumnDefs = [ {
    key : "image",
    sortable : true,
    label : "Imagen"
}, {
    key : "scientificname",
    sortable : true,
    label : "Nombre científico"
}, {
	key : "services",
    sortable : true,
    label : "Servicios"
 } ];

function homeSearch() {

	var searchString = document.getElementById('searchInput').value;
	
	var myDataSource = new YAHOO.util.DataSource("search/species?searchString="+ searchString+"&format=xml");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
	myDataSource.useXPath = true;
	myDataSource.responseSchema = {
	        resultNode: "element",
	        fields: [{key:"gui"},{key:"scientificname"},{key:"country"},{key:"province"},{key:"county"},{key:"locality"},
	                 {key:"latitude", parser:"number"},{key:"longitude", parser:"number"},{key:"catalog"},
                     {key:"institution"}]
	        };
	
	singleSelectDataTable = new YAHOO.widget.DataTable("tablePanel", 
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
		// -------------
        //Get the scientific name of selected row
        var selectedArray = this.getSelectedTrEls();
        var selectedRow = selectedArray[0];
        var content = selectedRow.getElementsByTagName('div');
        var nc= content[2].innerHTML;
        alert(nc);
	}
	// Also subscribe the overwrite method
	singleSelectDataTable.subscribe("rowClickEvent",
			singleSelectDataTable.onEventSelectRow);
}
