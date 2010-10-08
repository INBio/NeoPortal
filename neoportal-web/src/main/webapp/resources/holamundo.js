//where to sent the requests?
var urlContext = 'http://localhost:8082/neoportal-web';


/*
 * Sets the initial values for the page
 */
function initPage(urlContextValue) {

	if (urlContextValue != "")
		urlContext = urlContextValue;
}

/**
 * Gets an XML text
 * 
 * @param oResponse
 * @return
 */
var handleCallbackSuccess = function(oResponse) {

	// window.alert("todo bien: "+oResponse.responseText);
	var element = document.createElement('p');
	element.textContent = oResponse.responseText;

	var resultsContaines = document.getElementById('resultsPanel');
	resultsContaines.appendChild(element, null);

	/* revisar el reload! */
	initTable();
}

/**/
var handleCallbackFailure = function(o) {
	window.alert("Ha ocurrido un error, por favor intentelo de nuevo");
}
/*
 * the async callback with the resources associated to a given user
 */
var yuiCallback = {
	success : handleCallbackSuccess,
	failure : handleCallbackFailure
};

function holaMundo() {

	var searchString = document.getElementById('searchInput').value;

	// get all the dc elements
	YAHOO.util.Connect.asyncRequest('GET', urlContext
			+ '//search/simple?searchString=' + searchString, yuiCallback);
}

// Código original de esmata
function initTable() {
	// Example data
	YAHOO.example.Data = {
		specimens : {
			spList : [ {
				Catalog : "3385445",
				Inst : "INB",
				ScientificName : "Neurolaena lobata",
				Latitude : "8.58187",
				Longitude : "-83.49861"
			}, {
				Catalog : "3107701",
				Inst : "INB",
				ScientificName : "Mollinedia costaricensis",
				Latitude : "9.67361",
				Longitude : "-83.026389"
			}, {
				Catalog : "3101956",
				Inst : "INB",
				ScientificName : "Cymbopetalum torulosum",
				Latitude : "9.7425",
				Longitude : "-84.376667"
			} ]
		}
	}

	// Building the table
	var myColumnDefs = [ {
		key : "Catalog",
		sortable : true,
		label : "Catálogo"
	}, {
		key : "Inst",
		sortable : true,
		label : "Insti."
	}, {
		key : "ScientificName",
		sortable : true,
		label : "Nombre cient."
	}, {
		key : "Latitude",
		sortable : true,
		label : "Lat."
	}, {
		key : "Longitude",
		sortable : true,
		label : "Long."
	} ];

	var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.specimens);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "spList",
		fields : [ "Catalog", "Inst", "ScientificName", "Latitude", "Longitude" ]
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
		// ------------- Taken from original function ---------------------
	}
	// Also subscribe the overwrite method
	singleSelectDataTable.subscribe("rowClickEvent",
			singleSelectDataTable.onEventSelectRow);
}