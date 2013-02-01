/* 
 * Manage behavior of the taxon-description.jsp page
 * Includes xml to xhtml functionality
 * 
 * Author: avargas
 * 
 */
var isInfoLoaded = false;

/*
 * 
 */
$(document).ready(function (){
    //get provider if it comes on url
    hash = window.location.hash;

    //showTab(hash.substr(1));

    //$("#tabsContainer li").click(function(){
    //   showTab($('a', this)[0].hash.substr(1) );
    //});
});



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

function showImages(){
    var sUrl = contextPath+"/api/species/"+scientificName+"/images";

    $divImages = $("#images");
    
    if($divImages.length > 0){
        $divImages.show();
        return;
    }else{
        $divImages = $("<div/>",{id: 'images'}).addClass("tab_wrapper data_wrapper");
        $("#content").append($divImages);
    }

    $.ajax({
        type: "get",
        url: sUrl,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        success: function(data){
            debugger;
            $("<div/>", {id: 'imageInner'}).appendTo("#images");
            for(i=0; i < data.length; i++){
                $("<div/>").addClass("imageContainer").html(
                $("<a/>", 
                    {
                        href: "http://multimedia.inbio.ac.cr/m3sINBio/getImage?size=big&id=" + data[i].m3sImageId,
                        title: data[i].author + " - " + data[i].rights
                    }).html($("<img/>",
                        {
                            src: "http://multimedia.inbio.ac.cr/m3sINBio/getImage?size=thumb&id=" + data[i].m3sImageId
                        }).width(120))).appendTo("#imageInner");
                    
            }
            
            $("#imageInner a").lightBox({
                imageLoading: contextPath + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-ico-loading.gif",
                imageBtnClose: contextPath + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-close.gif",
                imageBtnPrev: contextPath + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-prev.gif",
                imageBtnNext: contextPath + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-next.gif",
                imageBlank: contextPath + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-blank.gif"
            });
            
            $("#imageInner").imagesLoaded(function(){
                $(this).masonry({
                    itemSelector: '.imageContainer',
                    isFitWidth: true
                });
            });
        }
    });
    
}