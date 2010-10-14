function initTable() {
    //Example data
    YAHOO.example.Data = {
        specimens: {
            spList: [
                {Catalog: "3385445",Inst:"INB",ScientificName:"Neurolaena lobata",Latitude:"8.58187",Longitude:"-83.49861"},
                {Catalog: "3107701",Inst:"INB",ScientificName:"Mollinedia costaricensis",Latitude:"9.67361",Longitude:"-83.026389"},
                {Catalog: "3101956",Inst:"INB",ScientificName:"Cymbopetalum torulosum",Latitude:"9.7425",Longitude:"-84.376667"}
            ]
        }
    }

    //Building the table
    var myColumnDefs = [
        {key:"Catalog", sortable:true,label:"Catálogo"},
        {key:"Inst", sortable:true,label:"Insti."},
        {key:"ScientificName", sortable:true,label:"Nombre cient."},
        {key:"Latitude", sortable:true,label:"Lat."},
        {key:"Longitude", sortable:true,label:"Long."}
    ];

    var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.specimens);
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "spList",
        fields: ["Catalog","Inst","ScientificName","Latitude","Longitude"]
    };

    singleSelectDataTable = new YAHOO.widget.DataTable("tablePanel",
        myColumnDefs, myDataSource, {
            selectionMode:"single"
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
        //------------- Taken from original function ---------------------
        //My new code
        var fromObj = document.getElementById('tablePanel');
        var myEvent = new YAHOO.util.CustomEvent("myEvent", fromObj);
        myEvent.subscribe(globalListener, fromObj);
        myEvent.fire();
    }
    // Also subscribe the overwrite method
    singleSelectDataTable.subscribe("rowClickEvent", singleSelectDataTable.onEventSelectRow);
}
