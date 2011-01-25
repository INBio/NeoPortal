//ContextPath
var contextPath;
//YUI data table
var singleSelectDataTable;
//To determine and define table columns
var myColumnDefs = [ {
    key : "image",
    sortable : false,
    label : "Imagen"
}, {
    key : "scientificname",
    sortable : true,
    label : "Nombre científico"
} ];

// ----------------------------------------------------------------------------
//--------------------- To initialize the page --------------------------------
function initSearch(context){
    //Sets the focus over search input
    document.getElementById("searchInput").focus();
    //Set the variable that contains the context path
    contextPath = context;
    //Inits yui panel support
    initYUIPanel();
}

//--------------------- To initialize YUI panel -------------------------------
function initYUIPanel(){
    if (!YAHOO.example.container.wait) {
        YAHOO.example.container.wait =
        new YAHOO.widget.Panel("wait",
        {
            width:"300px",
            fixedcenter:true,
            close:true,
            draggable:true,
            zindex:999,
            modal:true,
            visible:false
        });
    }
}
//---------------- To show specific service on the YUI panel ------------------
function showOnPanel(spName,occuUrl,imageUrl){
    YAHOO.example.container.wait.setHeader('<b> -- '+spName+' -- </b>');
    var content = '<br><a href="'+contextPath+occuUrl+'" class="occurrences">Ver ocurrencias</a><br><br>';
    content+='<a href="'+contextPath+imageUrl+'" class="multimedia">Ver imágenes</a><br><br>';
    YAHOO.example.container.wait.setBody(content);
    YAHOO.example.container.wait.render(document.getElementById('contenido'));
    YAHOO.example.container.wait.show();
}

// ----------------------------------------------------------------------------
//------------------------- Procced the search --------------------------------
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
        var scName= content[1].innerHTML;
        var occuUrl = '/occurrences/'+scName;
        showOnPanel(scName,occuUrl, occuUrl);
	}
	// Also subscribe the overwrite method
	singleSelectDataTable.subscribe("rowClickEvent",
			singleSelectDataTable.onEventSelectRow);
}
