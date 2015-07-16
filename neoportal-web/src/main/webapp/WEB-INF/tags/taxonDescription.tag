<%@ tag description="Generate the taxon description home page" language="java" 
	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<div id="info-taxon" class="taxon-content">
	<c:if test="${not empty taxonDescription }">
	<nav>
		<ul>
		    <li><a href="#naturalHistory"><fmt:message key="taxonDes.naturalHistory"/></a></li>
		    <li><a href="#habitatDistribution"><fmt:message key="taxonDes.habitatDistribution"/></a></li>
		    <li><a href="#demographyConservation"><fmt:message key="taxonDes.demographyConservation"/></a></li>
		    <li><a href="#usesManagement"><fmt:message key="taxonDes.usesManagement"/></a></li>
		    <li><a href="#description"><fmt:message key="taxonDes.description"/></a></li>
		    <li><a href="#information"><fmt:message key="taxonDes.information"/></a></li>
		    <li><a href="#externalSource"><fmt:message key="taxonDes.externalSource"/></a></li>
		</ul>
	</nav>
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
				not empty taxonDescription.crDistribution }">
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
	    	<c:if test="${not empty taxonDescription.crDistribution }">
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
		
		<c:if test="${not empty taxon }">
		<n:taxonomy taxon="${taxon}"></n:taxonomy>
		</c:if>
		
		<c:if test="${not empty taxonDescription}">
		<div id="information">
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
		</div>
		</c:if>
		
		<p>
			<!--     		Red List Endemico -->
			<h3><fmt:message key="ConservationStatus"/></h3>	

			<a>
				<input type="image"  onclick="RLPage('${taxon.defaultName}')" alt="" src="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='images_path'/>logos/IUCN.jpg" >							
				<output id="specie_category" onclick="RLPage('${taxon.defaultName}')" ><fmt:message key="notInfo"/></output>			
			</a>
	
		</p>
		
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