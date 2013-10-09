/*
 * groupNav.js
 * Author: avargas
 * Creation date: 20120806
 * 
 * Description: behavior for groupNav page
 * 
 */

var groupNavId;
var itemsPerPage = 10;
var speciesUrl = "/api/groupNav/species";

$("document").ready(function(){
	//prepare submenus
	$("li.gn-node:not(.current) > ul").hide();
	$("li.gn-node.current").parent().show();
	//add click function
	$("li.gn-node > a").click(function(){
		//hide current and remove class
		$("li.gn-node.current ul").hide();
		$("li.gn-node.current").removeClass("current");
		
		//show selected and set current class
		$(this).siblings("ul").show();
		$(this).parent().addClass("current");
	});
	
	
});
	
function loadGroupNavSpecies(){
	groupNavId = $("li.current a").attr('id');
	groupNavId = groupNavId.split("_");
	groupNavId = groupNavId[1];
	
	
	
	//get data
	$.get(speciesUrl + "?gni=" + groupNavId, function(){
		

		//draw pagination info if necessary
		
	});
	
	
}

function getPageData(page){
	
}