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
            showTaxonDescription('INB');
            break;
    }    
    //set currentTab style
    $('li:has(a[href="#' + tab + '"])').addClass('currentTab');
}

function showTaxonDescription(provider) {
    
    //get taxon description by provider
    //Prepare URL for XHR request:
    var sUrl = contextPath+"/api/species/"+scientificName+"/" + provider;

    $.get(sUrl, function(data) {
        //root element -> response
        var xmlDoc = data.documentElement;           
        //taxon description
        var rootChildNodes = xmlDoc.getElementsByTagName("taxon-description")[0];

        //debugger;

        var divTd = $("#taxonDescription").remove();

        divTd = document.createElement("div");
        divTd.id = "taxonDescription";
        divTd.className = "tab_wrapper";

        //deprecated, multiple providers support
        //combine with xsl template
        //xsl = loadXMLDoc(contextPath + "/resources/species/" + provider.toLowerCase() + ".xsl");

       // if(!xsl) {
            xsl = loadXMLDoc(contextPath + "/resources/species/base.xsl");
        //}

//            debugger;

        // code for IE
        if (window.ActiveXObject) {
            ex=xmlDoc.transformNode(xsl);
            //document.getElementById("example").innerHTML=ex;
            divTd.innerHTML=ex;
        }
        // code for Mozilla, Firefox, Opera, etc.
        else if (document.implementation && document.implementation.createDocument) {
            xsltProcessor=new XSLTProcessor();
            xsltProcessor.importStylesheet(xsl);
            resultDocument = xsltProcessor.transformToFragment(xmlDoc,document);
            divTd.appendChild(resultDocument);
        }

        divTd.innerHTML = divTd.innerHTML.replace(/\&lt;/g, '<').replace(/\&gt;/g, '>');

        //insert into dom
        $("#content").append(divTd);
        
        $("div.data-panel").hide();
        $("div.data-panel").each(function(){
            if($(this).children().size() < 2)
                $(this).remove();
        });
        
        $("div.data-panel:first").fadeIn(1500);
        //create side menu
        createMenu(data);

                       
    }, 'xml');
}

function loadXMLDoc(dname) {
    if (window.XMLHttpRequest) {
        xhttp=new XMLHttpRequest();
    }
    else {
        xhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhttp.open("GET",dname,false);
    xhttp.send("");
    return xhttp.responseXML;
}

function createMenu(xml_data){
    
    $(xml_data).find("element").each(function(){
        if($(this).find("institutionCode").text() == 'INB'){
            
            // -- look for Natural History Elements
            var hasNaturalHistory = false;
            var naturalHistory = "<li id='item-naturalHistory' class='menu-top'>" 
                + naturalHistoryT + "</li>";
            
            if($(this).find("reproduction").length > 0 
                    && $(this).find("reproduction").text() != '')
                hasNaturalHistory = true;
            
            if($(this).find("annualCycle").length > 0
                    && $(this).find("annualCycle").text() != '')
                hasNaturalHistory = true;
            
            if($(this).find("feeding").length > 0
                    && $(this).find("feeding").text() != '')
                hasNaturalHistory = true;
            
            if($(this).find("behavior").length > 0
                    && $(this).find("behavior").text() != '')
                hasNaturalHistory = true;
            
            if($(this).find("habit").length > 0
                    && $(this).find("habit").text() != '')
                hasNaturalHistory = true;
            
            // -- Habitat Distribution menu item 
            var hasHabitatDistribution = false;
            var habitatDistribution = "<li id='item-habitatDistribution' class='menu-top'>" 
                + habitatDistributionT + "</li>";
            
            if($(this).find("habitat").length > 0)
                hasHabitatDistribution = true;
            
            if($(this).find("distribution").length > 0)
                hasHabitatDistribution = true;
            
            // -- Uses Management menu item 
            var hasUsesManagement = false;
            var usesManagement = "<li id='item-usesManagement' class='menu-top'>" 
                + usesManagementT + "</li>";
            
            if($(this).find("theUses").length > 0 && $(this).find("theUses").text() != '')
                hasUsesManagement = true;
            
            if($(this).find("theManagement").length > 0 && $(this).find("theManagement").text())
                hasUsesManagement = true;
            
            // -- Demography Conservation menu item 
            var hasDemographyConservation = false;
            var demographyConservation = "<li id='item-demographyConservation' class='menu-top'>" 
                + demographyConservationT + "</li>";
            
            if($(this).find("threatStatus").length > 0
                    && $(this).find("threatStatus").text() != '')
                hasDemographyConservation = true;
            
            if($(this).find("populationBiology").length > 0
                    && $(this).find("populationBiology").text() != '')
                hasDemographyConservation = true;
            
            // -- Description menu item 
            var hasDescription = false;
            var description = "<li id='item-description' class='menu-top'>" 
                + descriptionT + "</li>";
            
            if($(this).find("scientificDescription").length > 0)
                hasDescription = true;
            
            // -- Documentation menu item 
            var hasDocumentation = false;
            var documentation = "<li id='item-documentation' class='menu-top'>" 
                + documentationT + "</li>";
            
            if($(this).find("theReferences").length > 0 && $(this).find("theReferences").text() != '')
                hasDocumentation = true;
            
            // -- Information menu item 
            var hasInformation = false;
            var information = "<li id='item-information' class='menu-top'>" 
                + informationT + "</li>";
            
            if($(this).find("language").length > 0)
                hasInformation = true;
            
            if($(this).find("creators").length > 0)
                hasInformation = true;
            
            if($(this).find("contributors").length > 0)
                hasInformation = true;
            
            if($(this).find("taxonRecordId").length > 0)
                hasInformation = true;
            
            if($(this).find("synonyms").length > 0)
                hasInformation = true;
            
            if($(this).find("dateCreated").length > 0)
                hasInformation = true;
            
            if($(this).find("dateLastModified").length > 0)
                hasInformation = true;
            
            
            //generated the menu list
            var menuContent = '';
            
            if (hasNaturalHistory)
                menuContent += naturalHistory;
            
            if (hasHabitatDistribution)
                menuContent += habitatDistribution;
            
            if (hasDemographyConservation)
                menuContent += demographyConservation;
            
            if (hasUsesManagement)
                menuContent += usesManagement;
            
            if (hasDescription)
                menuContent += description;
            
            if (hasDocumentation)
                menuContent += documentation;
            
            if (hasInformation)
                menuContent += information;

            var ul = $("<ul id='menu-species' class='menu'></ul>")
                .html(menuContent);
                
            $("li", ul).hide();
            
            $(ul).appendTo("#menu-panel");
            
            //showItems();
            $("#menu-species li").each(function(idx){
                $(this).delay(idx * 350).fadeIn( 900 )});
        
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