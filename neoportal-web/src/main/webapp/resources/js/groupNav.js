/*
 * groupNav.js
 * Author: avargas
 * Creation date: 20120806
 * 
 * Description: behavior for groupNav page
 * 
 */

$("document").ready(function(){
	//prepare submenus
	$("li.gn-firstLevel:not(.current) > ul").hide();
	//add click function
	$("li.gn-firstLevel > a").click(function(){
		//hide current and remove class
		$("li.gn-firstLevel.current ul").hide();
		$("li.gn-firstLevel.current").removeClass("current");
		
		//show selected and set current class
		$(this).siblings("ul").show();
		$(this).parent().addClass("current");
	});
	
	$("li.gn-child").click(function(){
		
	});
});
		