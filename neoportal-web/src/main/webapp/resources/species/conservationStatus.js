/**
 * 
 */

function RL(species,ne,dd,lc,nt,vu,en,cr,ew,ex)
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
					 $("#specie_category").get(0).innerHTML = ne;	
					
				 }
				 if(row[0].category == "DD")
				 {					 
					 $("#specie_category").get(0).innerHTML = dd;	
				 }
				 if(row[0].category == "LC")
				 {
					 $("#specie_category").get(0).innerHTML = lc;	
				 }
				 if(row[0].category == "NT")
				 {
					 $("#specie_category").get(0).innerHTML = nt;						
				 }
				 if(row[0].category == "VU")
				 {
					 $("#specie_category").get(0).innerHTML = vu;	 
				 }
				 if(row[0].category == "EN")
				 {
					 $("#specie_category").get(0).innerHTML = en;
				 }
				 if(row[0].category == "CR")
				 {
					 $("#specie_category").get(0).innerHTML = cr;
				 }
				 if(row[0].category == "EW")
				 {
					 $("#specie_category").get(0).innerHTML = ew;
				 }
				 if(row[0].category == "EX")
				 {
					 $("#specie_category").get(0).innerHTML = ex;
				 }
				 $("#specie_category").get(0).href = 'http://www.iucnredlist.org/details/'+id+"/0";                                        
			  },
			  failure: function () {
			    alert("No species found.");
				$("#result").get(0).innerHTML = "---";
			  }
			});
 	      }
