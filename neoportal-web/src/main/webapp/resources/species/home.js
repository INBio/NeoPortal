//ContextPath
var contextPath;
//
var url = "/neoportal-web/";

$(document).ready(function(){
	initSearch();
});

/*
 * To initialize the page
 */
function initSearch(context){

    //associated enter keypress
    $("#searchInput").keypress(function(event){
        if(event.which == 13){
            $("#simple").trigger('click');
        }
    });
    //Sets the focus over search input
    $("#searchInput").focus();
    //Set the variable that contains the context path
    contextPath = context;
    
    $("#simple").click(function(){
        //homeSearch();
        //Get the input search string from textbox
        var searchString = $('#searchInput').val();
        //Replace the blank spaces for underscores (Requiered for search engine)
        var clearInput = cleanInputSearch(searchString);
        //var input = clearInput.replace(/ /g,"_");
        
        //put input on url
        
        var newUrl = appUrl() + '?q=' + clearInput;
        window.location = newUrl;
    });
    
    var bestPictures = new Bloodhound({
        datumTokenizer: function(d) {
                  return Bloodhound.tokenizers.whitespace(d.value);
                      },
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: { 
            url: appUrl() + 'api/search/taxon?term=%QUERY',
            wildcard: '%QUERY',
            transform: function(response) {
                return $.map(response, function(str) { return { value: str }; });
            }
        }  
      });

      $('#searchInput').typeahead({
        hint: true,
        highlight: true,
        minLength: 2
        }, {
        display: "value",
        source: bestPictures 

      });
    
}

/** 
 * Return the home app url 
 * @returns {String}
 */
function appUrl() {
    //window.location.origin comes with webkit browsers
    if (!window.location.origin)
    	window.location.origin = window.location.protocol+"//"+window.location.host;
    
    return window.location.origin + url ;

}

/*
 * This function creates a specific url for a specific service
 */
function createServiceUrl(url,cssClass,title){
    return '<a href="'+url+'" class="'+cssClass+'">'+title+'</a><br><br>';
}
function createExternalUrl(url,cssClass,title){
    return '<a href="'+url+'" class="'+cssClass+'" target="_blank">'+
        title+'</a><br><br>';
}

/*
 * Functions to create the correct url's of external services
 */
function occurrencesUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for underscores
    var strSearch = cleanedInput.replace(" ","_");
    return '/occurrences/'+strSearch;
}

function speciesUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for underscores
    var strSearch = cleanedInput.replace(" ","_");
    return '/species/'+strSearch+'/';
}

function picasaUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://picasaweb.google.com/lh/view?q='+strSearch+'#slideshow';
}
function flickrUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://www.flickr.com/search/show/?q='+strSearch;
}
function gbifUrl(scName){
    return 'http://data.gbif.org/search/' + scName;
}
function eolUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","+");
    return 'http://www.eol.org/search?q='+strSearch;
}
function wikiUrl(scName){
    var cleanedInput = cleanInputSearch(scName);
    //Finally replace blank spaces for plus
    var strSearch = cleanedInput.replace(" ","_");
    return 'http://species.wikimedia.org/wiki/'+strSearch;
}
function cleanInputSearch(input){
    //Trim the string
    var strTrimed = trim(input);
    //If the user types a couple of blank spaces amoung words
    return strTrimed.replace("  "," ");
}

/*
 * String Trim function
 */
function trim(string)
{
    var str = string.replace(/^\s*|\s*$/g,"");
    return str;
}
