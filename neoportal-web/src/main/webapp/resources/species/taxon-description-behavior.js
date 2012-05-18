/* 
 * Manage behavior of the taxon-description.jsp page
 * Includes xml to xhtml functionality
 * 
 * Author: avargas
 * 
 */

/*
 * 
 */
$(document).ready(function (){
    //get provider if it comes on url
    hash = window.location.hash;

    showTab(hash.substr(1));

    $("#tabsContainer li").click(function(){
       showTab($('a', this)[0].hash.substr(1) );
    });
});

function showTab(tab){
    if (tab == "")
        tab = "info";
    
    //clear current tab
    $('li.currentTab').removeClass('currentTab');
    //hide current section, specific function show his own div
    $('div.tab_wrapper').hide();
    
    switch (tab) {
        case 'images':

            break;
        case 'occurrences':
            showOccurrences();
            break;
        //show the species information, based on INB provider
        case 'info':
        default:
            //showTaxonDescription_old('INB');
            showTaxonDescription('INB');
            break;
    }    
    //set currentTab style
    $('li:has(a[href="#' + tab + '"])').addClass('currentTab');
}


function showTaxonDescription(provider){
    $("#taxonDescription").show();
    //
    //get taxon description by provider
    //Prepare URL for XHR request:
    var sUrl = contextPath+"/api/species/"+scientificName+"/" + provider;

    $.ajax({
        type: "get",
        url: sUrl,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            debugger;
            
            // Natural history
            var nhContent = "";
            if(data.naturalHistory.habit != ""){
                nhContent += "<h4>" + habitT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.habit + "</div>";
            }
            if(data.naturalHistory.reproduction != ""){
                nhContent += "<h4>" + reproductionT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.reproduction + "</div>";
            }
            if(data.naturalHistory.feeding != ""){
                nhContent += "<h4>" + feedingT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.feeding + "</div>";
            }
            if(data.naturalHistory.behavior != ""){
                nhContent += "<h4>" + behaviorT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.behavior + "</div>";
            }
            if(data.naturalHistory.annualCycle != ""){
                nhContent += "<h4>" + annualCycleT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.annualCycle + "</div>";
            }
            if(data.naturalHistory.lifeCycle != ""){
                nhContent += "<h4>" + lifeCycleT + "</h4>";
                nhContent += "<div>" + data.naturalHistory.lifeCycle + "</div>";
            }
            
            // Habitat and Distribution
            var hdContent = "";
            if(data.habitatDistribution.habitat != ""){
                hdContent += "<h4>" + habitatT + "</h4>";
                hdContent += "<div>" + data.habitatDistribution.habitat + "</div>";
            }
            if(data.habitatDistribution.distribution != ""){
                hdContent += "<h4>" + distributionT + "</h4>";
                hdContent += "<div>" + data.habitatDistribution.distribution + "</div>";
            }
            
            // Demography and Conservation
            var dcContent = "";
            if(data.demographyConservation.threatStatus != ""){
                dcContent += "<h4>" + threatStatusT + "</h4>";
                dcContent += "<div>" + data.demographyConservation.threatStatus + "</div>";
            }
            if(data.demographyConservation.territory != ""){
                dcContent += "<h4>" + territoryT + "</h4>";
                dcContent += "<div>" + data.demographyConservation.territory + "</div>";
            }
            if(data.demographyConservation.populationBiology != ""){
                dcContent += "<h4>" + populationBiologyT + "</h4>";
                dcContent += "<div>" + data.demographyConservation.populationBiology + "</div>";
            }
            
            // Uses and management
            var umContent = "";
            if(data.usesManagement.uses != ""){
                umContent += "<h4>" + usesT + "</h4>";
                umContent += "<div>" + data.usesManagement.uses + "</div>";
            }
            if(data.usesManagement.management != ""){
                umContent += "<h4>" + managementT + "</h4>";
                umContent += "<div>" + data.usesManagement.management + "</div>";
            }
            
            // Description
            var dContent = "";
            if(data.description.scientificDescription != ""){
                dContent += "<h4>" + scientificDescriptionT + "</h4>";
                dContent += "<div>" + data.description.scientificDescription + "</div>";
            }
            
            // Information
            var iContent = "";
            if(data.information.language != ""){
                iContent += "<h4>" + languageT + "</h4>";
                iContent += "<div>" + data.information.language + "</div>";
            }
            if(data.information.author != ""){
                iContent += "<h4>" + authorT + "</h4>";
                iContent += "<div>" + data.information.author + "</div>";
            }
            if(data.information.contributors != ""){
                iContent += "<h4>" + contributorsT + "</h4>";
                iContent += "<div>" + data.information.contributors + "</div>";
            }
            if(data.information.taxonRecordId != ""){
                iContent += "<h4>" + taxonRecordIdT + "</h4>";
                iContent += "<div>" + data.information.taxonRecordId + "</div>";
            }
            if(data.information.dateLastModified != ""){
                iContent += "<h4>" + dateLastModifiedT + "</h4>";
                iContent += "<div>" + data.information.dateLastModified + "</div>";
            }
            if(data.information.dateCreated != ""){
                iContent += "<h4>" + dateCreatedT + "</h4>";
                iContent += "<div>" + data.information.dateCreated + "</div>";
            }
            
            // add content to DOM
            $("div.naturalHistory").append(nhContent);
            $("div.habitatDistribution").append(hdContent);
            $("div.demographyConservation").append(dcContent);
            $("div.usesManagement").append(umContent);
            $("div.description").append(dContent);
            $("div.information").append(iContent);
         
            // configure menu items
            if(nhContent == ""){
                $("#item-naturalHistory").addClass("menu-disable");
            }
            if(hdContent == ""){
                $("#item-habitatDistribution").addClass("menu-disable");
            }
            if(dcContent == ""){
                $("#item-demographyConservation").addClass("menu-disable");
            }
            if(umContent == ""){
                $("#item-usesManagement").addClass("menu-disable");
            }
            if(dContent == ""){
                $("#item-description").addClass("menu-disable");
            }
            if(iContent == ""){
                $("#item-information").addClass("menu-disable");
            }
            
            $("div.data-panel").hide();
            $("div.naturalHistory").show();
            
            $("#menu-species li").click(function(){
                var itemName = $(this).attr('id').split('-')[1];
                
                $("div.data-panel:visible").fadeOut('fast',function(){
                    $("div." + itemName).fadeIn(800);
                });
                
            });
            
        }
        
    });
}


function changeTaxonDescription(elem){
    if(elem.parentNode.className == "currentTab")
        return;

    //remove the previous selected
    var ulElem = elem.parentNode.parentNode;
        
    for (i = 0; i < ulElem.childNodes.length; i++){
        if(ulElem.childNodes[i].className == "currentTab"){
            ulElem.childNodes[i].className = "";
            break;
        }
    }

    //asign currentTab class to selected element
    elem.parentNode.className = 'currentTab';

    showTaxonDescription(elem.textContent);
}

/*
 * Get and display occurrences
 */
function showOccurrences(){
    $("#occurrences").show();
    
    if(map)
        return;

    //prepare view options
    $(document).ready(function(){
        $("#view_control a").click(function(){
            switch ($(this).attr('rel')) {
                case 'data':
                    $("#mapPanel").hide();
                    $("#resultTableHalf").hide();
                    $("#resultTable").show();
                    $("#tablePanelContainer").show().removeClass('half');
                    break;
                case 'split':
                    //$("#mapPanel").show().css('margin-left', '50%');
                    $("#mapPanel").show().attr('class', 'halfMap');
                    $("#resultTableHalf").show();
                    $("#resultTable").hide();
                    $("#tablePanelContainer").show().attr('class', 'half');
                    if(map)
                        map.updateSize();
                    break;
                case 'map':
                default:
                    $("#tablePanelContainer").hide();
                    //$("#mapPanel").show().css('margin', '0');
                    $("#mapPanel").show().attr('class', 'extendMap');
                    if(map)
                        map.updateSize();
                    break;
            }
            //evita sobreescribir el hash del url
            return false;
        });
    });
    
    //Initialize open layers map
    initMap2('map');
    
    //get data
    //Prepare URL for XHR request:
    var sUrl = contextPath + "/api/search/occurrences?searchString=scientificName:\""+scientificName+"\""+
        "&format=xml&sort=scientificname&dir=asc&startIndex="+0+"&results=2000";

    $.get(sUrl, function(xmlDoc){
        showSpecimenPoints(xmlDoc);
        
        //Initialize ocurrences table
        createTable(xmlDoc, '#tablePanelContainer');
        
        // TODO: crear función para las vistas, este código es copia de 
        // el código al darle click a la vista 'Mapa'
        $("#tablePanelContainer").hide();
        //$("#mapPanel").show().css('margin', '0');
        $("#mapPanel").show().attr('class', 'extendMap');
        if(map)
            map.updateSize();
    });
}