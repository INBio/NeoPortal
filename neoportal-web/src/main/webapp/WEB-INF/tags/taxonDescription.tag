<%@ tag description="Generate the taxon description home page" language="java" 
	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<div id="info-taxon" class="taxon-content">

	 <!--Idioma del contenido -->
	<c:if test="${not empty listLanguaje && fn:length(listLanguaje) > 1  }">
	<h3><fmt:message key="versions"/></h3>		
	<div class="container">
	<div class="row">	
    	<c:forEach var="listLanguaje" items="${listLanguaje}">						
			  <div class="col-xs-6 col-sm-3">
			  		<c:if test="${not empty listLanguaje.version }">
			  			<c:choose>
			  				<c:when test="${action == listLanguaje.version}">
	  							  	<div class="thumbnail-active thumbnail">		
			  						<div class="caption">   
					        		<c:if test="${not empty listLanguaje.language }">
								    <p>
							    		<strong><fmt:message key="taxonDes.language"/></strong>: ${listLanguaje.language}
							   		</p>
							    	</c:if>
							    	<c:if test="${not empty listLanguaje.individualName }">
							   		<p>
							   			<strong><fmt:message key="taxonDes.author"/></strong>: ${listLanguaje.individualName}
							 		</p>
							    	</c:if>
							    	<c:if test="${not empty listLanguaje.dateIssued }">
							    	<p>
							   			<strong><fmt:message key="taxonDes.dateCreated"/></strong>: ${listLanguaje.dateIssued}
									</p>
							    	</c:if>  
			  						<p><a href="${taxonUrl}/${listLanguaje.version}" class="btn btn-default disabled" role="button" >Version ${listLanguaje.version}</a></p>
					  				  </div>
			  				  		  </div>	
			  				</c:when>
			  				<c:otherwise>
			  						<div class="thumbnail">		
			  						<div class="caption">  
					        		<c:if test="${not empty listLanguaje.language }">
								    <p>
							    		<strong><fmt:message key="taxonDes.language"/></strong>: ${listLanguaje.language}
							   		</p>
							    	</c:if>
							    	<c:if test="${not empty listLanguaje.individualName }">
							   		<p>
							   			<strong><fmt:message key="taxonDes.author"/></strong>: ${listLanguaje.individualName}
							 		</p>
							    	</c:if>
							    	<c:if test="${not empty listLanguaje.dateIssued }">
							    	<p>
							   			<strong><fmt:message key="taxonDes.dateCreated"/></strong>: ${listLanguaje.dateIssued}
									</p>
							    	</c:if>  
			  						<p><a href="${taxonUrl}/${listLanguaje.version}" class="btn btn-primary">Version ${listLanguaje.version}</a></p>
				  				  	 </div>
			  				 		 </div>	
			  				</c:otherwise>
			  			</c:choose>
			  		</c:if>	
			  </div>  	
		</c:forEach>
	</div>	
	</div>		
	</c:if>

	
	<c:if test="${not empty taxonDescription }">
	<nav>
		<ul>
			<c:if test="${ 
				not empty taxonDescription.reproductionUnstructure ||
				not empty taxonDescription.feedingUnstructure ||
				not empty taxonDescription.behaviorUnstructure ||
				not empty taxonDescription.annualCyclesUnstructure ||
				not empty taxonDescription.lifeCycleUnstructure 
			}">
	    	<li><a href="#naturalHistory"><fmt:message key="taxonDes.naturalHistory"/></a></li>
	    	</c:if>
	    	
 			<c:if test="${not empty taxonDescription.habitatUnstructure ||
						  not empty taxonDescription.crDistribution ||
						  not empty taxonDescription.regionalDistribution
			}">
		    <li><a href="#habitatDistribution"><fmt:message key="taxonDes.habitatDistribution"/></a></li>
		    </c:if>
		    
		    <c:if test="${not empty taxonDescription.usesUnstructure }">
		    <li><a href="#usesManagement"><fmt:message key="taxonDes.usesManagement"/></a></li>
		    </c:if>
		    
   		    <c:if test="${not empty taxonDescription.threatUnstructure ||
				    	  not empty taxonDescription.territoryUnstructure ||
				    	  not empty taxonDescription.populationBiologyUnstructure 
    	     }">
		    <li><a href="#demographyConservation"><fmt:message key="taxonDes.demographyConservation"/></a></li>
		    </c:if>
		    
		    <c:if test="${not empty taxonDescription.fullDescriptionUnstructured }">
	    		<li><a href="#description"><fmt:message key="taxonDes.description"/></a></li>
	    	</c:if>
		    
		    <c:if test="${not empty taxon }">
				<li><a href="#Taxonomic"><fmt:message key="taxonDes.taxonomy"/></a></li>	
			</c:if>
		    
		    <c:if test="${not empty taxonDescription}">
			    <li><a href="#information"><fmt:message key="taxonDes.information"/></a></li>	
		    </c:if>
		</ul>
	</nav>
	</c:if>
	
	<c:if test="${fn:length(listLanguaje) > 1}">
	<h3><fmt:message key="Version"/> ${taxonDescription.version}</h3>
	</c:if>
	<div id="shareThis"></div>
<!-- 	<div id="species_images"></div> -->
	<n:imageGallery images="${images }"></n:imageGallery>
	
	<div id="taxonDescription">
		<div id="naturalHistory">
			<c:if test="${ 
				not empty taxonDescription.reproductionUnstructure ||
				not empty taxonDescription.feedingUnstructure ||
				not empty taxonDescription.behaviorUnstructure ||
				not empty taxonDescription.annualCyclesUnstructure ||
				not empty taxonDescription.lifeCycleUnstructure 
			}">
	    	<h3><fmt:message key="taxonDes.naturalHistory"/></h3>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.reproductionUnstructure }">
	    		<h4><fmt:message key="taxonDes.reproduction"/></h4>
	    		<div>${taxonDescription.reproductionUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.feedingUnstructure }">
	    		<h4><fmt:message key="taxonDes.feeding"/></h4>
	    		<div>${taxonDescription.feedingUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.behaviorUnstructure }">
	    		<h4><fmt:message key="taxonDes.behavior"/></h4>
	    		<div>${taxonDescription.behaviorUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.annualCyclesUnstructure }">
	    		<h4><fmt:message key="taxonDes.annualCycle"/></h4>
	    		<div>${taxonDescription.annualCyclesUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.lifeCycleUnstructure }">
	    		<h4><fmt:message key="taxonDes.lifeCycle"/></h4>
	    		<div>${taxonDescription.lifeCycleUnstructure}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="habitatDistribution">
			<c:if test="${not empty taxonDescription.habitatUnstructure ||
						  not empty taxonDescription.crDistribution ||
						  not empty taxonDescription.regionalDistribution
			}">
		    <h3><fmt:message key="taxonDes.habitatDistribution"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.habitatUnstructure }">
	    		<h4><fmt:message key="taxonDes.habitat"/></h4>
	    		<div>${taxonDescription.habitatUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.crDistribution }">
	    		<h4><fmt:message key="taxonDes.distribution"/></h4>
	    		<div>${taxonDescription.crDistribution}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.regionalDistribution }">
	    		<h4><fmt:message key="taxonDes.regionalDistribution"/></h4>
	    		<div>${taxonDescription.regionalDistribution}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="usesManagement">
			<c:if test="${not empty taxonDescription.usesUnstructure }">
		    <h3><fmt:message key="taxonDes.usesManagement"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.usesUnstructure }">
	    		<h4><fmt:message key="taxonDes.uses"/></h4>
	    		<div>${taxonDescription.usesUnstructure}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="demographyConservation">
		    <c:if test="${not empty taxonDescription.threatUnstructure ||
		    	not empty taxonDescription.territoryUnstructure ||
		    	not empty taxonDescription.populationBiologyUnstructure }">
		    <h3><fmt:message key="taxonDes.demographyConservation"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.threatUnstructure }">
	    		<h4><fmt:message key="taxonDes.threatStatus"/></h4>
	    		<div>${taxonDescription.threatUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.territoryUnstructure }">
	    		<h4><fmt:message key="taxonDes.territory"/></h4>
	    		<div>${taxonDescription.territoryUnstructure}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.populationBiologyUnstructure }">
	    		<h4><fmt:message key="taxonDes.populationBiology"/></h4>
	    		<div>${taxonDescription.populationBiologyUnstructure}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="description">
		    <c:if test="${not empty taxonDescription.fullDescriptionUnstructured }">
		    	<h3><fmt:message key="taxonDes.description"/></h3>
	    		<h4><fmt:message key="taxonDes.scientificDescription"/></h4>
	    		<div>${taxonDescription.fullDescriptionUnstructured}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="Taxonomic">
			<c:if test="${not empty taxon }">
				<n:taxonomy taxon="${taxon}"></n:taxonomy>
			</c:if>
		</div>

		<div id="information">
			<c:if test="${not empty taxonDescription}">
			    <h3><fmt:message key="taxonDes.information"/></h3>			    
   	    	
			    <c:if test="${not empty taxonDescription.language }">
			    <p>
		    		<strong><fmt:message key="taxonDes.language"/></strong>: ${taxonDescription.language}
		   		</p>
		    	</c:if>
		    	<c:if test="${not empty taxonDescription.individualName }">
		   		<p>
		   			<strong><fmt:message key="taxonDes.author"/></strong>: ${taxonDescription.individualName}
		 		</p>
		    	</c:if>
		    	<c:if test="${not empty taxonDescription.colaborator1 }">
		    	<p>
		   			<strong><fmt:message key="taxonDes.contributors"/></strong>: ${taxonDescription.colaborator1}
				</p>
		    	</c:if>
		    	<c:if test="${not empty taxonDescription.taxonRecordId }">
		    	<p>
		   			<strong><fmt:message key="taxonDes.taxonRecordId"/></strong>: ${taxonDescription.taxonRecordId}
		   		</p>
		    	</c:if>
		    	<c:if test="${not empty taxonDescription.modificationdate }">
		    	<p>
		   			<strong><fmt:message key="taxonDes.dateLastModified"/></strong>: ${taxonDescription.modificationdate}
				</p>
		    	</c:if>
		    	<c:if test="${not empty taxonDescription.creationdate }">
		    	<p>
		   			<strong><fmt:message key="taxonDes.dateCreated"/></strong>: ${taxonDescription.creationdate}
				</p>
		    	</c:if>  
		    </c:if>  	
		</div>
	
		
<!-- 		<div id="externalSource"> -->
<%-- 		    TODO: get external sources from database --%>
<%-- 		    <h3><fmt:message key="taxonDes.externalSource"/></h3> --%>
<%-- 		    <p><a href="http://www.biodiversitylibrary.org/name/${scientificName}">BHL</a></p> --%>
<%-- 		    <p><a href="http://ara.inbio.ac.cr/SSTN-IABIN/search/${scientificName}">IABIN</a></p> --%>
<%-- 		    <p><a href="http://cro.ots.ac.cr/rdmcnfs/datasets/exsrch.phtml?words=${scientificName}&ds=binabitrop">OET Binabitrop</a></p> --%>
<%-- 		    <p><a href="http://species.wikimedia.org/wiki/${scientificName}">Wikispecies</a></p> --%>
<%-- 		    <p><a href="http://www.boldsystems.org/views/taxbrowser.php?taxon=${scientificName}">Barcode of life data systems</a></p> --%>
<!-- 		</div> -->
	</div> <!-- close #taxonDescription -->
</div>