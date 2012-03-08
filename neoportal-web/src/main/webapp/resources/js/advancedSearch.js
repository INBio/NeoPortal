/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var filtersUrl = "/neoportal-web/api/advancedSearch/getColumnList";

$(document).ready(function(){
    
    var searchContainer = $("<div></div>").addClass("search");
    addSearchPanel(searchContainer);
    
    $("#content").append(searchContainer);
    
});

/**
 *
 */
function addSearchPanel(searchContainer){
    //var searchContainerLocal = searchContainer;
    $.getJSON(filtersUrl, function(data){
        debugger;
        
        var columnList = data.columnList;
        var columnDefault = data.columnDefault;
        var filterList = data.filterList;
        
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
            
            $(searchContainer).append(filterDiv);
            
            //hide expand option before resume filterDiv
            $("span.expand-button", filterDiv).hide();
            
        }
        
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
    debugger;
    
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
    debugger;
    
    //hide resume elements
    $("p.resume", filterGroup).remove();

    //show columns
    $("span.columnItem", filterGroup).show();


    $(filterGroup).width(500);
    
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