$(document).ready(function(){
    if($("#map").length > 0) {
		//Initialize open layers map
	    initMap2('map');
	    
	  //get data
	    //Prepare URL for XHR request:
	    var homeUrl = $("#homeUrl");
	    
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
    
});
