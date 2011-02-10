/**
 * This mehtod returns the total count of species that match with a specified
 * searchString. It is used on species pagination
 */
function getTotalSpecies(contextPath,searchString)  {

    //Prepare URL for XHR request:
    var sUrl = contextPath+"/search/countSpecies?searchString="+searchString+"&format=xml";

    //Prepare our callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;           
            //count
            var rootChildNodes = xmlDoc.getElementsByTagName("count");
            //Get count value
            var cv = rootChildNodes[0].childNodes[0].nodeValue;
            totalcount = cv;
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}