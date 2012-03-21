/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var filtersUrl = "/neoportal-web/api/advancedSearch/getColumnList";
var occurrencesUrl = "/neoportal-web/api/advancedSearch/getOccurrences";
var countOccurrenceUrl = "/neoportal-web/api/advancedSearch/countOccurrences";

//pagination info
var startIndex = 0; //offset for paginated search
var itemsPerPage = 10; //
var totalItems = 0; //total number of regs based on filters

$(document).ready(function(){
    
    var searchContainer = $("<div></div>").addClass("search");
    addSearchPanel(searchContainer);
    
    $("#content").append(searchContainer);
    
    $("#searchBtn").click(function(){
        var searchData = generatedQueryData();
        //get total count of regs
        $.ajax({
            type: 'post',
            url: countOccurrenceUrl,
            data: JSON.stringify(searchData),
            success: function(countData){
                //set count variable
                totalItems = countData.count;
                //call main data 
                searchOccurrences(0, 10);
            },
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        });
        
        
    });
    
});

/**
 *
 */
function addSearchPanel(searchContainer){
    //var searchContainerLocal = searchContainer;
    $.getJSON(filtersUrl, function(data){
        //debugger;
        
        var columnList = data.columnList;
        var columnDefault = data.columnDefault;
        var filterList = data.filterList;
        var leftPanel = $("<div/>").addClass("leftPanel");
        
        /**
        
                Generating column list
        
        **/
        for(i=0; i < columnList.length; i++){

            //container for column list 
            var filterDiv = $("<div/>").addClass(columnList[i].key);
            filterDiv.addClass("filterGroup");
            
            //add expand button
            filterDiv.append("<span class='expand-button filter-group-button'>expand</span>");
            //add hide button
            filterDiv.append("<span class='reduce-button filter-group-button'>hide</span>");

            //lista de columnas para el actual agrupador (taxonomía, info geográfica, etc)
            var jsonColumnList = eval('(' + columnList[i].value + ')');
            //filterDiv.append("<input type='hidden' value='" + data[i].value + "' />");
            filterDiv.append("<h4>" + jsonColumnList.value + "</h4>");
            
            for(j=0; j < jsonColumnList.columns.length; j++ ){
                var inputString = "<span class='columnItem'><input type='checkbox' class='columnItem' ";
                inputString += " name='" + jsonColumnList.columns[j].key + "' />";
                inputString += "<label for='" + jsonColumnList.columns[j].key + "' class='columnItem'>";
                inputString += jsonColumnList.columns[j].value + "</label></span>";

                $(filterDiv).append(inputString);
            }
            
            //check default columns
            var actualColumnDefault = getColumnDefaultByKey(columnDefault, columnList[i].key);
            if(actualColumnDefault){
                var list = eval('(' + actualColumnDefault.value + ')');
                
                for(n=0; n < list.columns.length; n++){
                    $("input[name=" + list.columns[n].key + "]", filterDiv).attr('checked', true);
                }
            }
            
            $(leftPanel).append(filterDiv);
            
            //hide expand option before resume filterDiv
            $("span.expand-button", filterDiv).hide();
            
        }
        
        $(searchContainer).append(leftPanel);
        
        $("div.filterGroup").each(function(index, filterGroup){
            resumeFilterGroup(filterGroup);
        });
        
        /**
                Generating filter list
        **/
        for(i=0; i < filterList.length; i++){
            var groupDiv = $("div." + filterList[i].key);
            
            var jsonFilterList = eval ('(' + filterList[i].value + ')');
            
            for(j=0; j < jsonFilterList.filters.length; j++){
                var filterString;
                
                //filter types... 
                if(jsonFilterList.filters[j].type == "text"){
                    filterString = "<span class='filterItem'>";
                    filterString += jsonFilterList.filters[j].label + ": ";
                    filterString += "<input type='text' class='filterText' ";
                    filterString += " name='" + jsonFilterList.filters[j].key + "' />";
                    filterString += "</span>";
                }
                
                filterString = "<div>" + filterString + "</div>";
                
                $(groupDiv).append(filterString);
            }
        }
        
        
        //asign events
        $(".expand-button").click(function(){
            expandFilterGroup($(this).parent());
        });
        
        $(".reduce-button").click(function(){
            resumeFilterGroup($(this).parent());
        });
        
    });
}

/**
 * 
 */
function resumeFilterGroup(filterGroup){
    //debugger;
    
    $("span.columnItem", filterGroup).hide();
    
    var columnNames = "";
    $("input.columnItem:checked", filterGroup).each(function(index, elem){
        columnNames += $('label[for="'+ $(elem).attr("name") + '"]').text() + ", ";
    });
    
    if(columnNames.length == 0){
        columnNames= "ninguna";
    }
    else{
        columnNames = columnNames.substr(0, columnNames.length - 2);
    }
    
    $(filterGroup).append("<p class='resume'>Columnas: " + columnNames);
    
    $(filterGroup).width(250);
    
    $("span.filter-group-button", filterGroup).toggle();
}

/**
 *
 */
function expandFilterGroup(filterGroup){
    //debugger;
    
    //hide resume elements
    $("p.resume", filterGroup).remove();

    //show columns
    $("span.columnItem", filterGroup).show();


    $(filterGroup).width(500);
    $(filterGroup).css({position: "relative"});
    
    $("span.filter-group-button", filterGroup).toggle();
}

/**
 * 
 */
function getColumnDefaultByKey(json, key){
    for(i=0; i < json.length; i++){
        if(json[i].key === key){
            return json[i];
        }
    }
    return false;
}

/**
 * Create the object with all the columns and filters needed to return 
 * the appropiate results
 */
function generatedQueryData(){
    var queryData = new Object();
    
/* taxon information */
    queryData.taxonomy = new Object();
    queryData.taxonomy.columns = new Array();
    //columns
    $("div.taxonomy input.columnItem:checked").each(function (index, item){
        queryData.taxonomy.columns[index] = $(item).attr("name");
    });
    queryData.taxonomy.filters = new Object();
    //filters
    $("div.taxonomy span.filterItem").each(function (index, item){
        var $input = $("input", item);
        var newFilter = new Object();
        
        if ($input.attr("type") == "text"){
            eval("queryData.taxonomy.filters." 
                + $input.attr("name") + " = '" 
                + $input.val() + "'");
        }
    });
    
    return queryData;
}

/**
 * Generate the table and pagination html elements
 */
function showTable(searchContainer, data){
    
    if (data == null)
        return;
    
    //$("div.results", searchContainer).remove();
    //check if the div exists
    var existDiv = $("div.results").length > 0? true: false ;

    if(existDiv){   //change just the tbody section when table is already generate
        $("div.results table tbody").html(generatedTableRows(data));
    }
    else{   //create buttons for pagination and table elements
        var divResults = "<div class=\"results\">";
        //paginated elements
        divResults += "<span class=\"firstControl paginate_button\">Inicio</span>";
        divResults += "<span class=\"previousControl paginate_button\">Anterior</span>";
        divResults += "<span class=\"nextControl paginate_button\">Siguiente</span>";
        divResults += "<span class=\"lastControl paginate_button\">Último</span>";

       //table
        divResults += "<table class=\"tablePanel\">";
        divResults += "<thead><tr>";

        //iterate throw the columns
        $("input.columnItem:checked").each(function (index, elem){
            divResults += "<th scope=\"col\">" + $(elem).attr("name") + "</th>";
        });

        divResults += "</tr></thead>";
        
        //append the html code for all data rows
        divResults += generatedTableRows(data);
        
        $(searchContainer).append(divResults);
        
        
        //events for pagination
        $("span.firstControl").click(function(){
            startIndex = 0;
            searchOccurrences(startIndex, itemsPerPage);
        });

        $("span.previousControl").click(function(){
            if((startIndex - itemsPerPage) >= 0 ){
                startIndex -= itemsPerPage;
                searchOccurrences(startIndex, itemsPerPage);
            }
        });

        $("span.lastControl").click(function(){
            startIndex = totalItems - (totalItems % itemsPerPage);
            searchOccurrences(startIndex, itemsPerPage);
        });

        $("span.nextControl").click(function(){
            if(startIndex + itemsPerPage < totalItems){
                startIndex += itemsPerPage;
                searchOccurrences(startIndex, itemsPerPage);
            }
        });
    }
    
}

/**
*
*/
function searchOccurrences(startIndex, results){
    var searchContainer = $("div.search");
    var searchData = generatedQueryData();
    var searchUrl = occurrencesUrl + "?startIndex=" + startIndex + "&results=" + results;
    
    var postCall = {type: 'post',
      url: searchUrl,
      data: JSON.stringify(searchData),
      success: function(data){
          debugger;
          //alert("sent request");

          //show data paginated
          showTable(searchContainer, data);
      },
      dataType: 'json',
      contentType: 'application/json; charset=utf-8'
    };
    $.ajax(postCall);
}

/**
* receive the data and return the html for the rows
* this function does not include the table tag or thead
* just tr's for each row
*/
function generatedTableRows(data){
    var columnList = $("input.columnItem:checked");
    var rows = "";
    for(i=0; i < data.length; i++){
        rows += "<tr>";
        for(j=0; j < columnList.length; j++){
            //debugger;
            rows += "<td>" + data[i][$(columnList[j]).attr("name")] + "</td>";
        }
        rows += "</tr>";
    }
    
    return rows;
}