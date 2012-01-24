//ContextPath
var contextPath;
//YUI data table
var singleSelectDataTable;
//Total count of species by searchCriteria (Use on pagination)
var totalcount;
//
var url = "/neoportal-web/api/search/taxa"
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
    $("#searchInput").focus();
    //Set the variable that contains the context path
    contextPath = context;
    //Inits yui panel support
    //initYUIPanels();
    
    $("#simple").click(function(){
        //homeSearch();
        //Get the input search string from textbox
        var searchString = $('#searchInput').val();
        //Replace the blank spaces for underscores (Requiered for search engine)
        var clearInput = cleanInputSearch(searchString);
        //var input = clearInput.replace(/ /g,"_");
        
        //put input on url
        window.location.search = 'q=' + clearInput;
    });
    
    if(window.location.search != '')
    {
        //homeSearch();
        
        homeSearch_();
        
        $("div.dataTables_paginate").show();
        
        externalSearch();
    }
}


/*
 * To show specific services on the YUI panel
 */
function showOnPanel(scName){

    //Create body
    var content = "<div id='dialog' title='" + scName + "'>";
    content += '<p>';
    content += createServiceUrl(contextPath+occurrencesUrl(scName),'service',seeMultimediaT);
    content += createServiceUrl(contextPath+speciesUrl(scName),'service',seeSpeciesT);
    content += createServiceUrl(contextPath+occurrencesUrl(scName),'service',seeOccurrencesT);
    content += '</p>';
    content += "</div>";
    
    $("body").append(content);
    
    $("#dialog").dialog({
       modal: true,
       resizable: false,
       close: function () {
           $("#dialog").remove();
       }
    });
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
    return '/species/'+strSearch+'/';
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
    //var cleanedInput = cleanInputSearch(scName);
    //return 'http://data.gbif.org/search/'+cleanedInput;
    //return 'http://data.gbif.org/ws/rest/taxon/list?scientificname=' +
    //    scName + '&maxresults=10';
    return 'http://data.gbif.org/search/' + scName;
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
    $("#moduleExplanation").hide(0.3);
        
    //get the q param with the corresponding search
    var input = window.location.search;
    input = input.split('=');
    
    var searchString = input[1];
    
    if(input.length > 2) {
        //case when there's more than the q param
        //we need to iterate throw the array until find q
    }
    
    //searchString = searchString.replace(/_/g, ' ');
    searchString = unescape(searchString);
    
    //set search string into input bar
    $('#searchInput').val(searchString);
    
    //check if table exist...
    if ($("#resultTable").length < 1){
        var tHead = "<thead><tr>";
        tHead += "<th>" + commonNameT +"</th>";
        tHead += "<th>" + scientificNameT +"</th>";
        tHead += "<th>" + imageT +"</th>";
        tHead += "</tr></thead>";

        //create tableCamarero, esa realidad está demasiado cruda.


        $("#tablePanel").append("<table id='resultTable' class='home'></table>");
        $("#resultTable").append(tHead);
    }

    // DataTable configuration

            
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
        "sAjaxSource": contextPath+"/api/search/taxa",
        "fnServerData": function( sSource, aoData, fnCallback){
            //get actual index for pagging

            var sEcho = aoData[0].value;
            $.get(contextPath+"/api/search/taxa", {searchTerms: searchString,
                format: "xml",
                startIndex: aoData[3].value,    //iDisplayStart
                itemsPerPage: 10}, function(data){
                    //prepare the json for datatable to use it the right way
                    /* convert to format that DataTables understands */
                    var jData = $( data );
                    totalcount = jData.find("count").text();

                    var json = {"sEcho": sEcho,"aaData" : []};

                    json.iTotalRecords = totalcount;
                    json.iTotalDisplayRecords = totalcount;

                    jData.find("element").each(function(){
                       json.aaData.push([
                           $(this).find("cname").text(),
                           $(this).find("scname").text(),
                           "<img src=\"" + $(this).find("url").text() + "\" />"
                       ]);
                    });

                    fnCallback(json);

                    //hide the show 10 entries component
                    $("#resultTable_length").hide();

                    configureTable();

                }, "xml");
        }
    });

}

function configureTable(){
    $("#tablePanel tbody tr").click(function(){
        //show the panel for the selected row
        showOnPanel($(this).children("td.scname").text());
    });
}

function homeSearch_(){
    //Hide the search explanation panel
    $("#moduleExplanation").hide(300);
        
    //get the q param with the corresponding search
    var input = window.location.search;
    input = input.split('=');
    
    var searchString = input[1];
    
    if(input.length > 2) {
        //case when there's more than the q param
        //we need to iterate throw the array until find q
    }
    
    //searchString = searchString.replace(/_/g, ' ');
    searchString = unescape(searchString);
    
    //set search string into input bar
    $('#searchInput').val(searchString);
    
    $("#tablePanel").inbPaginate(url, 
        {searchTerms: searchString, totalResults: "count",
            firstControl: "span.firstControl", lastControl: "span.lastControl",
            nextControl: "span.nextControl", previousControl: "span.previousControl",
            iterateFunction: function(xmlData){
                //iterate throw the records.. 
                //generate the html code for the resulting set
                debugger;
                                
                var itemList = "";
                
                $(xmlData).find("element").each(function(){
                    var newItem = "<div class='search_item'>";
                    newItem += "<img src=\"" + $(this).find("url").text() + "\" />"
                    newItem += "<span>\n\
                        <a href=\"species/" + $(this).find("scname").text() + "\">" 
                        + $(this).find("scname").text() + "</a></span>";
                    newItem += "<span>" + $(this).find("cname").text() + "</span>";
                    newItem += "</div>";
                    
                    itemList += newItem;
                });
                
                //return the html string
                //the plugin add this to the container
                return itemList;
        }});
}

function externalSearch(){
    //get the q param with the corresponding search
    var input = window.location.search;
    input = input.split('=');
    
    var searchString = input[1];
    
    if(input.length > 2) {
        //case when there's more than the q param
        //we need to iterate throw the array until find q
    }
    
    searchString = unescape(searchString);

    $("#externalResult").append("<a href='" + 
                        gbifUrl(searchString) + 
                        "'>Buscar en GBIF</a>");
    
    $("#externalResult").append("<a href='" + 
                        eolUrl(searchString) + 
                        "'>Buscar en EOL</a>");
    
    $("#externalResult").append("<a href='" + 
        wikiUrl(searchString) + 
        "'>Buscar en wikispecies</a>");
        
        
    //muestra el div de enlaces externos
    $("#externalResult").show();
}