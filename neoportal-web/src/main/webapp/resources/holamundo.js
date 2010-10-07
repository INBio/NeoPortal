var urlContext = 'http://localhost:8082/neoportal-web';

/*
 * Sets the initial values for the page
 */
function initPage(urlContextValue){

 if(urlContextValue != "")
  urlContext = urlContextValue;
}
/**
 * Gets an XML text
 * 
 * @param oResponse
 * @return
 */
var handleCallbackSuccess = function(oResponse){

    window.alert("todo bien: "+oResponse.responseText);
}


/**/
var handleCallbackFailure = function(o){
	window.alert("Ha ocurrido un error, por favor intentelo de nuevo");
}
/*
 * the async callback with the resources associated to a given user
 */
var yuiCallback = {
  success:handleCallbackSuccess,
  failure:handleCallbackFailure
};


function holaMundo(){
	
  //get all the dc elements
  YAHOO.util.Connect.asyncRequest('GET', 
    urlContext+'/simple', 
    yuiCallback);
}