//Ocurrences table
var singleSelectDataTable;
//Total count of occurrences by searchCriteria (Use on pagination)
var totalcount;
//ContextPath
var contextPath;
//puntero x donde va la lista de occurrencias en html
var actualIndex;
// xml data
var xmlOccu;

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
    
    //Initialize open layers map
    initMap('map', scientificname);
    
    //Initialize ocurrences table
    initTable(scientificname);
    
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
    //create the table
	createOccurrencesTable();

    //configure datatable...
    $("#resultTable").dataTable({
        //"sDom": '<"top">rt<"bottom"flp><"clear">',
        "oLanguage": {
            "sInfo": tableInfo,
            "oPaginate": {
                "sFirst": tableFirst,
                "sLast": tableLast,
                "sNext": tableNext,
                "sPrevious": tablePrevious
            }
        },
        //show pagination on both, top and bottom
        "sDom": '<"top"lfip>rt<"bottom"ip<"clear">',
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            /* add scname class for scientific name celd */
            $('td:eq(1)', nRow).attr("class", "scname");
            return nRow;
        },
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bDestroy": true,
        "bServerSide": true,
        "sAjaxSource": contextPath+"/api/search/occurrences",
        "fnServerData": function( sSource, aoData, fnCallback){
            //get actual index for pagging

            var sEcho = aoData[0].value;
            $.get(contextPath+"/api/search/occurrences", {searchString: searchString,
                format:     "xml",
                startIndex: aoData[3].value,    //iDisplayStart
                results:    10,
                sort:       'scientificname',
                dir:        'asc'}, function(data){
                    //prepare the json for datatable to use it the right way
                    /* convert to format that DataTables understands */
                    var jData = $( data );

                    totalcount = jData.find("count").text();

                    var json = {"sEcho": sEcho,"aaData" : []};

                    json.iTotalRecords = totalcount;
                    json.iTotalDisplayRecords = totalcount;

                    jData.find("element").each(function(){
                       json.aaData.push([
                           $(this).find("scientificname").text(),
                           $(this).find("institution").text(),
                           $(this).find("country").text(),
                           $(this).find("province").text(),
                           $(this).find("county").text(),
                           $(this).find("latitude").text(),
                           $(this).find("longitude").text(),
                           $(this).find("catalog").text(),
                       ]);
                    });

                    fnCallback(json);

                    //hide the show 10 entries component
                    $("#resultTable_length").hide();

                    configureTable();
                    
                    //repaint the map with the new points
                    showSpecimenPoints(searchString, aoData[3].value);

                }, "xml");
        }
    });
    
}

/**
 * Creates the occurrences results table
 */
function createOccurrencesTable(divContainer){
    //check if table exist...
    if ($("#resultTable").length < 1){
        var tHead = "<thead><tr>";
        //tHead += "<th class='first_column'>" + scientificNameT +"</th>";
        tHead += "<th class='first_column'>" + institutionT +"</th>";
        tHead += "<th>" + countryT +"</th>";
        tHead += "<th>" + provinceT +"</th>";
        tHead += "<th>" + countyT +"</th>";
        tHead += "<th>" + latitudeT +"</th>";
        tHead += "<th>" + longitudeT +"</th>";
        tHead += "<th class='last_column'>" + catalogT +"</th>";
        tHead += "</tr></thead>";

        //create table
        $(divContainer).append("<table id='resultTable' class='simpleTable occurrences'></table>");
        $("#resultTable").append(tHead);
        
        //crea tabla para vista resumida
        tHead = "<thead><tr>";
        tHead += "<th class='first_column'>" + "Location" +"</th>";
        tHead += "<th>" + latitudeT +"</th>";
        tHead += "<th class='last_column'>" + longitudeT +"</th>";
        tHead += "</tr></thead>";
        
        $(divContainer).append("<table id='resultTableHalf' class='simpleTable occurrences'></table>");
        $("#resultTableHalf").hide().append(tHead);
    }
}


function configureTable(){
    //wrap the table after data table is generate
    //the div hold the horizontal scroll
    if($("div.occuPanel").length < 1){
        $("#resultTable").wrap('<div class="occuPanel" />');
    }
    
    $("#resultTable tbody tr").click(function(){
        //Clear map pop ups
        clearPopups();
        
        var nsc='',latitude='',longitude='',catalog='',institution='';
        catalog = $(this)[0].cells[7].innerHTML;
        institution = $(this)[0].cells[1].innerHTML;
        nsc = $(this)[0].cells[0].innerHTML;
        latitude = $(this)[0].cells[5].innerHTML;
        longitude = $(this)[0].cells[6].innerHTML;
        //Set the values on "selectedFeature" variable
        var attributes = createAttrib(nsc,latitude,longitude,catalog,institution);
        selectedFeature = new OpenLayers.Feature.Vector(
            new OpenLayers.Geometry.Point(longitude,latitude), attributes);
        //Show pop up on map
        onFeatureSelectFromTable(selectedFeature);
    });
}


function createTable(xmlDoc, divContainer){
    if(!xmlOccu){
        xmlOccu = xmlDoc;
        totalcount = $(xmlDoc).find('count')[0].textContent;
        actualIndex = 0;
        
        createOccurrencesTable(divContainer);
        
        // verifica si necesita botón de 'ver más'
        if(totalcount > 10){
            //add the 'show more' button
            $(divContainer).append("<a href='#' id='show-more'>show more</a>");

            $("#show-more").click(function(){
                $(this).hide();
                createTable();

                //avoid change current url hash
                return false;
            });
        }
    }
    
    maxIndex = actualIndex + 10;
    if(maxIndex > totalcount)
        maxIndex = totalcount;
    
    var htmlRows = "";
    var htmlRowsHalf = "";
    
    for (actualIndex; actualIndex < maxIndex; actualIndex++) {
        
        var elem = $(xmlOccu).find('element')[actualIndex];
        
        htmlRows += "<tr class=occurrence_"+ $(elem).find('occurrenceId').text() + ">"+
                //"<td>"+ $(elem).find('scientificname').text() + "</td>"+
                "<td class='first_column'>"+ $(elem).find('institution').text() + "</td>"+
                "<td>"+ $(elem).find('country').text() + "</td>"+
                "<td>"+ $(elem).find('province').text() + "</td>"+
                "<td>"+ $(elem).find('county').text() + "</td>"+
                "<td>"+ $(elem).find('latitude').text() + "</td>"+
                "<td>"+ $(elem).find('longitude').text() + "</td>"+
                "<td>"+ $(elem).find('catalog').text() + "</td>"+
                "</tr>";
            
        htmlRowsHalf += "<tr class=occurrence_"+ $(elem).find('occurrenceId').text() + ">"+
            //"<td>"+ $(elem).find('scientificname').text() + "</td>"+
            "<td class='first_column'>"+ $(elem).find('country').text() + ", "+
            $(elem).find('province').text() + ", "+
            $(elem).find('county').text() + "</td>"+
            "<td>"+ $(elem).find('latitude').text() + "</td>"+
            "<td>"+ $(elem).find('longitude').text() + "</td>"+
            "</tr>";
    }
    
    //put new rows at the end of the table
    $("#resultTable").append(htmlRows);
    $("#resultTableHalf").append(htmlRowsHalf);
    
    if (actualIndex < totalcount){
        $("#show-more").show();
    }
}

function selectRowsFromMap(arrayFeatures){
    //debugger;
    
    for(i=0; i<arrayFeatures.length; i++){
        $("tr.occurrence_" + arrayFeatures[i].attributes.OccurrenceId).addClass('selected');
    }
}

function unselectAllRows(){
    $("tr.selected").removeClass('selected');
}