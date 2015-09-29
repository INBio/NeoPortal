/**
 * 
 */
!function(t)
 {t.fn.buttonLoader=function(a)
     {var s=t(this);"start"==a&&("disabled"==t(s).attr("disabled")&&e.preventDefault(),t(".has-spinner").attr("disabled","disabled"),t(s).attr("data-btn-text",t(s).text()),t(s).html('<span class="spinner"><i class="fa fa-spinner fa-spin"></i></span>Loading'),t(s).addClass("active")),"stop"==a&&(t(s).html(t(s).attr("data-btn-text")),t(s).removeClass("active"),t(".has-spinner").removeAttr("disabled"))}}(jQuery);


 function BHL(species)
  {
	 jQuery(function(){
		 jQuery('.has-spinner').click();
	 } );

	 
	 $('.has-spinner').click(function()
	 {
		   var btn = $(this);
	       $(btn).buttonLoader('start');
	
	       species = species.replace(' ','+');
	       $(btn).buttonLoader('start');
            $.getJSON('http://www.biodiversitylibrary.org/api2/httpquery.ashx?op=NameGetDetail&name='+species+"&apikey=5986c80b-52f2-4660-8c0c-d0f0ef661739&format=json", function(data) 
            {                 
                  var output="<div class=\"bs-example\"> <div class=\"list-group\"> ";
                  for (var i in data.Result.Titles) 
                  {               
            		  output+="<a href="+ data.Result.Titles[i].Items[0].Pages[0].PageUrl +" target=\"_blank\" class=\"list-group-item\">";                  		  
            		  output+="<h4 class=\"list-group-item-heading\">"+ data.Result.Titles[i].ShortTitle +"</h4>"; 
            		  if(data.Result.Titles[i].Items[0].Contributor != null)
            		  {
            			  output+="<p class=\"list-group-item-text\">"+ data.Result.Titles[i].Items[0].Contributor +"</p>"; 
            		  }
            		  if(data.Result.Titles[i].PublicationDate != null)
            		  {
            			  output+="<p class=\"list-group-item-text\">"+ data.Result.Titles[i].PublicationDate +"</p>"; 
            		  }
            		  //output+="<a href="+ data.Result.Titles[i].Items[0].Pages[0].PageUrl+ "> <img src="+ data.Result.Titles[i].Items[0].Pages[0].ThumbnailUrl +" width=\"60px\" height=\"100px\" > </a> ";                          
            		  output+="</a>";                                              
                  }
                  output+="</div> </div>";
                  $("#BHLInfo").get(0).innerHTML=output;
                  $(btn).hide();
//                  $(btn).buttonLoader('stop');
            });
            
	 });
  }
 
 
 (function ($) {
     $.fn.buttonLoader = function (action) 
     {
         var self = $(this);
         //start loading animation
         if (action == 'start') {
             if ($(self).attr("disabled") == "disabled") {
//                 e.preventDefault();
             }
             //disable buttons when loading state
             $('.has-spinner').attr("disabled", "disabled");
             $(self).attr('data-btn-text', $(self).text());
             //binding spinner element to button and changing button text
             $(self).html('<span class="spinner"><i class="fa fa-spinner fa-spin"></i></span>Loading');
             $(self).addClass('active');
         }
         //stop loading animation
         if (action == 'stop') {
             $(self).html($(self).attr('data-btn-text'));
             $(self).removeClass('active');
             //enable buttons after finish loading
             $('.has-spinner').removeAttr("disabled");
         }
     }
 })(jQuery);
 



// class=\"list-group-item-text\"

	