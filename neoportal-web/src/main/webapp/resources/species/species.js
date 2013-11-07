$(document).ready(function(){
    // logo home dir to build other links
	var homeUrl = $("#homeUrl");
	if($("#map").length > 0) {
		//Initialize open layers map
	    initMap2('map');
	    
	  //get data
	    //Prepare URL for XHR request:
	    var sUrl = $(homeUrl).attr("href") + "/api/search/occurrences?searchString=scientificName:\""+scientificName+"\""+
	        "&format=xml&sort=scientificname&dir=asc&startIndex="+0+"&results=2000";

	    $.get(sUrl, function(xmlDoc){
	        showSpecimenPoints(xmlDoc);
	        
	        // TODO: crear función para las vistas, este código es copia de 
	        // el código al darle click a la vista 'Mapa'
	        if(map)
	            map.updateSize();
	    });
    }

    if($("div.grid-layout").length > 0) {
    	$("div.item-list").imagesLoaded(function(){
    		$(this).masonry({
        		itemSelector: ' div.item',
        		isFitWidth: true
        	});
    	}); 
    	
    	$("div.image-item a").lightBox({
            imageLoading: $(homeUrl).attr("href") + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-ico-loading.gif",
            imageBtnClose: $(homeUrl).attr("href") + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-close.gif",
            imageBtnPrev: $(homeUrl).attr("href") + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-prev.gif",
            imageBtnNext: $(homeUrl).attr("href") + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-btn-next.gif",
            imageBlank: $(homeUrl).attr("href") + "/resources/plugins/jquery-lightbox-0.5/images/lightbox-blank.gif"
        });
    }
    
});
