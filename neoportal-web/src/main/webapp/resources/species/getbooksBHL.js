/**
 * 
 */

 function BHL(species)
  {

       species = species.replace(' ','+');
       $('#myPleaseWait').modal('show');
            $.getJSON('http://www.biodiversitylibrary.org/api2/httpquery.ashx?op=NameGetDetail&name='+species+"&apikey=5986c80b-52f2-4660-8c0c-d0f0ef661739&format=json", function(data) 
            {                 
                  var output="<div class=\"bs-example\"> <div class=\"list-group\"> ";
                  for (var i in data.Result.Titles) 
                  {
                     output+="<a href="+ data.Result.Titles[0].Items[0].ItemUrl  +" class=\"list-group-item\">";   
                     output+="<h4 class=\"list-group-item-heading\">"+ data.Result.Titles[i].ShortTitle +"</h4>";
                     output+="</a>";  
                     
                     output+="<div class=\"page-thumbnail\"";
                     output+="<p class=\"list-group-item-text\"> <a href="+ data.Result.Titles[0].Items[0].Pages[0].PageUrl+ "> <img src="+ data.Result.Titles[0].Items[0].Pages[0].ThumbnailUrl +" width=\"60px\" height=\"100px\" > </a> </p>";                    
                     output+="</div>";                        
                  }
                  output+="</div> </div>";
                  $("#BHLInfo").get(0).innerHTML=output;
                  $('#myPleaseWait').modal('hide');
            });
  }
 
 function CallServerFunction() {
     $('#myPleaseWait').modal('show');
     $.ajax({
         url: "test.html",
         success: function (data) {
             $('#myPleaseWait').modal('hide');
             console.log('The page has been successfully loaded');
         },
         error: function () {
             $('#myPleaseWait').modal('hide');
             console.log('An error occurred');
         }
     });
 }


	