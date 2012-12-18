/*
 * taxonList.js
 * Author: avargas
 * Creation date: 20121206
 * 
 * Description: Code for taxon list results, include pagination, html generation
 * 
 */

/**
 * Generate the html code that show the taxon list
 * @param {json} taxonList	The array of taxon objects
 * @return {String}	String value containing the html code with the list
 */
function getTaxonList(taxonList){
	var outputHtml = "";
	outputHtml += "<ul>";
	
	for ( var i = 0; i < taxonList.length; i++) {
		outputHtml += "<li>";
		if(taxonList[i]["imageURL"] != "")
			outputHtml += "<img alt=\"image of" + taxonList[i]["scientificName"] + "\" src=\"" + taxonList[i]["imageURL"] + "\" />";
		outputHtml += "<h2>" + taxonList[i]["scientificName"] + "</h2>";
		outputHtml += "<p>: " + taxonList[i]["commonName"] + "</p>";
		outputHtml += "</li>";
	}
	
	outputHtml += "<ul>";
}

