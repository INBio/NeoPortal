/**
 * 
 */

function RL(species)
 	      {
	         var id = 1;
		    species = species.replace(' ','-');
			$.ajax({
			  dataType: "jsonp",
			  url: 'http://api.iucnredlist.org/index/species/'+species+".js",
			  success: function (row) {
				 id = row[0].species_id	
				 
				 if(row[0].category == "NE")
				 {
					 $("#specie_category").get(0).innerHTML = "Not Evaluated";	
				 }
				 if(row[0].category == "DD")
				 {
					 $("#specie_category").get(0).innerHTML = "Data Deficient";	
				 }
				 if(row[0].category == "LC")
				 {
					 $("#specie_category").get(0).innerHTML = "Least Concern";	
				 }
				 if(row[0].category == "NT")
				 {
					 $("#specie_category").get(0).innerHTML = "Near Threatened";	
				 }
				 if(row[0].category == "VU")
				 {
					 $("#specie_category").get(0).innerHTML = "Vulneable";	
				 }
				 if(row[0].category == "EN")
				 {
					 $("#specie_category").get(0).innerHTML = "Endangered";	
				 }
				 if(row[0].category == "CR")
				 {
					 $("#specie_category").get(0).innerHTML = "Critically Endangered";	
				 }
				 if(row[0].category == "EW")
				 {
					 $("#specie_category").get(0).innerHTML = "Extinct in tge wild";	
				 }
				 if(row[0].category == "EX")
				 {
					 $("#specie_category").get(0).innerHTML = "Extinct";	
				 }
				 $("#RL").get(0).innerHTML = 'http://www.iucnredlist.org/details/'+id+"/0";                                        
			  },
			  failure: function () {
			    alert("No species found.");
				$("#result").get(0).innerHTML = "---";
			  }
			});
 	      }
 
 	      function RLPage(species)
 	      {
	         var id = 1;
		    species = species.replace(' ','-');
			$.ajax({
			  dataType: "jsonp",
			  url: 'http://api.iucnredlist.org/index/species/'+species+".js",
			  success: function (row) {
				 id = row[0].species_id				                      
				 window.location = 'http://www.iucnredlist.org/details/'+id+"/0";                  
			  },
			  failure: function () {
			    alert("No species found.");
				$("#result").get(0).innerHTML = "---";
			  }
			});
 	      }