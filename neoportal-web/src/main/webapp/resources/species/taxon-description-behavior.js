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
$(document).ready(function (){
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
    var sUrl = contextPath+"/api/search/species?searchString="+scientificName+"&format=xml&startIndex=0&results=20";

    $.get(sUrl, function(data){

        //taxon description
        
        var ulContent = "<ul>";

        //get provider in case no one was indicate
        if(provider == '')
        {
            provider = $(data).find("inst")[0].textContent;
        }

        var classCurrentTab = "";

        //create tabs
        $(data).find('element').each(function(){
            actualValue = $(this).find("inst").text();

            if(actualValue == provider)
                classCurrentTab = "class=\"currentTab\"";
            else
                classCurrentTab = "";

            ulContent += "<li " + classCurrentTab + ">"
                + "<a href=\"#/" + actualValue + "\" onclick=\"javascript:changeTaxonDescription(this)\">"
                + actualValue
                + "</a>"
                + "</li>";
        });

        ulContent += "</ul>";

        //insert into dom
        $("#tabsContainer").append(ulContent);

        //get taxon description info
        taxonDescription(provider);
        
    }, 'xml');

    //Make our XHR call using Connection Manager's
    //YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);

}

function showTaxonDescription(provider) {
    
    //get taxon description by provider
    //Prepare URL for XHR request:
    var sUrl = contextPath+"/api/species/"+scientificName+"/" + provider;

    $.get(sUrl, function(data) {
        //root element -> response
        var xmlDoc = data.documentElement;           
        //taxon description
        var rootChildNodes = xmlDoc.getElementsByTagName("taxon-description")[0];

        //debugger;

        var divTd = $("#taxonDescription").remove();

        divTd = document.createElement("div");
        divTd.id = "taxonDescription";

        //combine with xsl template
        xsl = loadXMLDoc(contextPath + "/resources/species/" + provider.toLowerCase() + ".xsl");

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
        $("#content"). append(divTd);
                       
    }, 'xml');

    //Make our XHR call using Connection Manager's
    //YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
    
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
