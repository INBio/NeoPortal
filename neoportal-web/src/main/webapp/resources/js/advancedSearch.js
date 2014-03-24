/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var filtersUrl = "/neoportal-web/api/advancedSearch/getColumnList";
var occurrencesUrl = "/neoportal-web/api/advancedSearch/getOccurrences";
var countOccurrenceUrl = "/neoportal-web/api/advancedSearch/countOccurrences";
var exportUrl = "/neoportal-web/api/advancedSearch/exportOccurrences";
var baseAPIUrl = "/neoportal-web/api/";

//pagination info
var startIndex = 0; //offset for paginated search
var itemsPerPage = 20; // number of items per every call
var totalItems = 0; //total number of regs based on filters

$(document).ready(function(){
    
    var searchContainer = $("<div></div>").addClass("search");
    addSearchPanel(searchContainer);
    
    $("#content").append(searchContainer);
    
    $("#searchBtn").click(function(){
        var searchData = generatedQueryData();
        
        $("div.results").remove();
        
        startIndex = 0;
        //get total count of regs
        $.ajax({
            type: 'post',
            url: countOccurrenceUrl,
            data: JSON.stringify(searchData),
            success: function(countData){
                //set count variable
                totalItems = countData.count;
                //change label
                $("#countLabel").html("Cerca de " + totalItems + " registros");
                
                if(totalItems == 0)
                    return;
                
                //call main data 
                searchOccurrences(0, itemsPerPage);
            },
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        });
        
        
    });
    
    $('#exportBtn').click(function(){
    	var searchData = generatedQueryData();
        exportOccurrences(searchData);
    });
    
});

/**
 *
 */
function addSearchPanel(searchContainer){
    //var searchContainerLocal = searchContainer;
    $.getJSON(filtersUrl, function(data){
        //debugger;
        
        var leftPanel = $("div.leftPanel");
        
        for(i = 0; i < data.length; i++){
            var filterDiv = $("<div/>").addClass(data[i].key);
            filterDiv.addClass("filterGroup");
         
            //add expand button
            filterDiv.append("<span class='expand-button filter-group-button'>expand</span>");
            //add hide button
            filterDiv.append("<span class='reduce-button filter-group-button'>hide</span>");
         
            //filter group header
            filterDiv.append("<h4>" + data[i].label + "</h4>");
            
            /**
                Generating column list
            **/
            for(j=0; j < data[i].searchColumnList.length; j++ ){
                var inputString = "<span class='columnItem'><input type='checkbox' class='columnItem' ";
                inputString += " name='" + data[i].searchColumnList[j].columnKey + "' />";
                inputString += "<label for='" + data[i].searchColumnList[j].columnKey + "' class='columnItem'>";
                inputString += data[i].searchColumnList[j].label + "</label></span>";

                $(filterDiv).append(inputString);
            }
            
            //check default columns
            for(n=0; n < data[i].columnDefaultList.length; n++){
                $("input[name=" + data[i].columnDefaultList[n].columnKey + "]", filterDiv).attr('checked', true);
            }

            /**
                    Generating filter list
            **/
            for(j=0; j < data[i].searchFilterList.length; j++){
                var filterString = "";
                
                var filter = data[i].searchFilterList[j];
                
                //search if the filter has a column
                var columnItem = $(".columnItem input[name=" + filter.filterKey + "]", filterDiv);
                
                //filter types...
                switch (filter.type) {
                    case "text":
                        filterString = "<span class='filterItem filterText'>";
                        //add label if there's no 
                        if(columnItem.length == 0)
                        	filterString += filter.label + ": ";
                        filterString += "<input type='text' class='filterText' ";
                        filterString += " name='" + filter.filterKey + "' />";
                        filterString += "</span>";
                        break;
                    case "combo":
                        filterString = "<span class='filterItem filterCombo'>";
                        filterString += "<select class='filterCombo' name='" + 
                            filter.filterKey + "'>";
                        
                        for(n = 0; n < filter.values.length; n++){
                            filterString += "<option value='" + filter.values[n].key + "'>";
                            filterString += filter.values[n].label;
                            filterString += "</option>";
                        }
                        
                        filterString += "</select>";
                        filterString += "</span>";
                        break;
                        
                    case "date":
                    	filterString += "<span class='filterItem filterDate'>";
                    	//filterString += "<label for='from'>from</label>";
                    	filterString += "<input type='text' id='" + filter.filterKey + "_from' name='from' class='datePicker' />";
                    	filterString += "<label for='to'>to</label>";
                    	filterString += "<input type='text' id='" + filter.filterKey + "_to' name='to' class='datePicker' />";
                    	filterString += "<input type='hidden' name='" + filter.filterKey + "' />";
                    	filterString += "</span>";
                    	break;
                    default:
                        break;
                }
                                
                //search if the filter has a column
                if(columnItem.length > 0){
                    //insert filter like last child of column parent
                    $(columnItem).parent().append(filterString);
                }
                else{
                	if(filter.type == "text")
                		filterString = "<div>" + filterString + "</div>";
                    $(filterDiv).append(filterString);
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
        
        //asign events
        $(".expand-button").click(function(){
            expandFilterGroup($(this).parent());
        });
        
        $(".reduce-button").click(function(){
            resumeFilterGroup($(this).parent());
        });
        
        
        //special filters properties
        $("input.datePicker").datepicker({
        	dateFormat: "dd/mm/yy",
        	altFormat: "yymmdd",
        	onSelect: function( selectedDate ) {
				$(this).nextAll("input").datepicker( "option", "minDate", selectedDate );
				//$(this).prevAll("input").datepicker( "option", "maxDate", selectedDate );
				
        		//$( "#to" ).datepicker( "option", "minDate", selectedDate );
			}
        });
        
        /*$('input[name$="taxon"]').autocomplete({
            source: baseAPIUrl + "search/taxon",
            minLength: 2
        });*/
        
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
 * Create the object with all the columns and filters needed to return 
 * the appropiate results
 */
function generatedQueryData(){
    var filterGroups = ["taxonomic_information", "geographic_information", "specimen_information", "event_information", "identification_information"];
    var queryData = new Object();
        queryData.filterGroups = new Array();
    
    for(i = 0; i < filterGroups.length; i++){
        //get the actual div to improve search items performance
        var $actualFilterGroup = $("div." + filterGroups[i]);
        var newFilterGroup = new Object();
        newFilterGroup.key = filterGroups[i].split("_")[0];
        
        //columns
        newFilterGroup.columns = new Array();
        $("input.columnItem:checked", $actualFilterGroup).each(function (index, item){
            newFilterGroup.columns[index] = $(item).attr("name");
        });
        
        //filters
        newFilterGroup.filters = new Array();
        $("span.filterItem", $actualFilterGroup).each(function (index, item){
            var newFilter = new Object();

            var $filter;
            
            if($(item).hasClass('filterText')) {
            	$filter = $("input[type=text]", item);
            	newFilter.key = $filter.attr("name");
                newFilter.value = $filter.val();
            }
            
            else if($(item).hasClass('filterCombo')) {
            	$filter = $("select", item);
            	newFilter.key = $filter.attr("name");
                newFilter.value = $filter.val();
            }
            
            else if($(item).hasClass('filterDate')) {
            	newFilter.key = $("input[type=hidden]", item).attr('name');
            	newFilter.value = $("input#" + newFilter.key + "_from", item).val() +
            				"|" + $("input#" + newFilter.key + "_to", item).val();
            	
            }

            newFilterGroup.filters[index] = newFilter;
            
            /*
            if($("input[type='text']", item).length > 0)
                $filter = $("input[type='text']", item);
            else if($("select", item).length > 0)
                $filter = $("select", item);
                */

        });
    
        queryData.filterGroups[i] = newFilterGroup;
    }
    
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
        var divResults = "<div class=\"contentColumn\">";
        divResults += "<div class=\"results filterGroup\">";
        
        divResults += "<span class=\"showingControl\"></span>";
        //paginated elements
        divResults += "<div class=\"paginate_group\">";
        divResults += "<span class=\"firstControl paginate_button\">Inicio</span>";
        divResults += "<span class=\"previousControl paginate_button\">Anterior</span>";
        divResults += "<span class=\"nextControl paginate_button\">Siguiente</span>";
        divResults += "<span class=\"lastControl paginate_button\">Último</span>";
        divResults += "</div>";
        

       //table
        divResults += "<div class=\"wrapper\">";
        divResults += "<table class=\"tablePanel\">";
        divResults += "<thead><tr>";

        //iterate throw the columns
        $("input.columnItem:checked").each(function (index, elem){
            divResults += "<th scope=\"col\">" + $(elem).siblings('label').text() + "</th>";
        });

        divResults += "</tr></thead>";
        
        //append the html code for all data rows
        divResults += generatedTableRows(data);
        
        divResults += "</table>";
        divResults += "</div>";	//close wrapper
        
        //paginated elements bottom
        divResults += "<div class=\"paginate_group\">";
        divResults += "<span class=\"firstControl paginate_button\">Inicio</span>";
        divResults += "<span class=\"previousControl paginate_button\">Anterior</span>";
        divResults += "<span class=\"nextControl paginate_button\">Siguiente</span>";
        divResults += "<span class=\"lastControl paginate_button\">Último</span>";
        divResults += "</div>"; //close div.paginate_group 
        divResults += "</div>"; //close div.results
        divResults += "</div>"; //close div.contentColumn
        
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
    
    //update pagination
    var showingMsg = startIndex + 1;
    showingMsg += " - " + (startIndex + itemsPerPage > totalItems? totalItems : startIndex + itemsPerPage);
    showingMsg += " de " + (totalItems);
    $("span.showingControl").html(showingMsg);
    
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
            debugger;
            //if(data[i][$(columnList[j]).attr("name")] != undefined){
                rows += "<td>" + 
                	(!data[i][$(columnList[j]).attr("name")]?"":data[i][$(columnList[j]).attr("name")]) + 
                	"</td>";
            //}
            //else
            //    rows += "<td>" + data[i]['properties'][$(columnList[j]).attr("name")] + "</td>";
        }
        rows += "</tr>";
    }
    
    return rows;
}


function exportOccurrences(searchData){
    $("form#exportForm input").val(JSON.stringify(searchData));
    
    $("form#exportForm").submit();
	
    return;
    
	//get total count of regs
    $.ajax({
        type: 'post',
        url: exportUrl,
        data: JSON.stringify(searchData),
        success: function(data){
            //set count variable
            
        },
        contentType: 'application/json; charset=utf-8'
    });
}