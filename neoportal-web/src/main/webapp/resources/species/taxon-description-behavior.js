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
YAHOO.util.Event.onDOMReady(function (){
   //get provider if it comes on url
   hash = window.location.hash;
   
   if(hash != '')
   {
       urlParts = hash.split('/');
       provider = urlParts[urlParts.length - 1];
   }
   
   //create providers tab list, show taxon description for provider
   createProviderTabs(showTaxonDescription);
   
});


function createProviderTabs(taxonDescription) {
    //get providers list
    //Prepare URL for XHR request:
    var sUrl = contextPath+"/search/species?searchString="+scientificName+"&format=xml&startIndex=0&results=20";

    //Prepare our callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;           
            //taxon description
            var rootChildNodes = xmlDoc.getElementsByTagName("response-elements");
            
//            debugger;
            
//            var divTabs = document.createElement("div");
//            divTabs.id = "tabsContainer";
            
            var ulContent = document.createElement("ul");
            
            //get provider in case no one was indicate
            if(provider == '')
            {
                provider = rootChildNodes[0].childNodes[0].getElementsByTagName("inst")[0].textContent;
            }
            
            var classCurrentTab = "";
            
            //create tabs
            var totalProviders = rootChildNodes[0].childElementCount;
            for(i = 0; i < totalProviders; i++) {
                actualValue = rootChildNodes[0].childNodes[i].getElementsByTagName("inst")[0].textContent;
                
                if(actualValue == provider)
                    classCurrentTab = "class=\"currentTab\"";
                else
                    classCurrentTab = "";
                
                ulContent.innerHTML += "<li " + classCurrentTab + ">"
                    + "<a href=\"#/" + actualValue + "\" onclick=\"javascript:changeTaxonDescription(this)\">"
                    + actualValue
                    + "</a>"
                    + "</li>";
            }
                        
            //insert into dom
            document.getElementById("tabsContainer").appendChild(ulContent);
            
            //get taxon description info
            taxonDescription(provider);
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);

}

function showTaxonDescription(provider) {
    
    //get taxon description by provider
    //Prepare URL for XHR request:
    var sUrl = contextPath+"/api/species/"+scientificName+"/" + provider;

    //Prepare our callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;           
            //taxon description
            var rootChildNodes = xmlDoc.getElementsByTagName("taxon-description")[0];
            
            //debugger;
            
            var divTd = document.getElementById("taxonDescription");
            
            if(!divTd){
                divTd = document.createElement("div");
                divTd.id = "taxonDescription";
            }
            else
                divTd.innerHTML = "";
            
            //combine with xsl template
            xsl = loadXMLDoc(contextPath + "/resources/species/" + provider + ".xsl");
            
            if(!xsl) {
                xsl = loadXMLDoc(contextPath + "/resources/species/base.xsl");
            }
            
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
            document.getElementById("content"). appendChild(divTd);
                       
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
    
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