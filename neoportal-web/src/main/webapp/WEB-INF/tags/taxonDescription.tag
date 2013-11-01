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
			<c:if test="${not empty taxonDescription.habit || 
				not empty taxonDescription.reproduction ||
				not empty taxonDescription.feeding ||
				not empty taxonDescription.behavior ||
				not empty taxonDescription.annualCycle ||
				not empty taxonDescription.lifeCycle 
			}">
	    	<h3><fmt:message key="taxonDes.naturalHistory"/></h3>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.habit }">
	    		<h4><fmt:message key="taxonDes.habit"/></h4>
	    		<div>${taxonDescription.habit}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.reproduction }">
	    		<h4><fmt:message key="taxonDes.reproduction"/></h4>
	    		<div>${taxonDescription.reproduction}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.feeding }">
	    		<h4><fmt:message key="taxonDes.feeding"/></h4>
	    		<div>${taxonDescription.feeding}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.behavior }">
	    		<h4><fmt:message key="taxonDes.behavior"/></h4>
	    		<div>${taxonDescription.behavior}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.annualCycle }">
	    		<h4><fmt:message key="taxonDes.annualCycle"/></h4>
	    		<div>${taxonDescription.annualCycle}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.lifeCycle }">
	    		<h4><fmt:message key="taxonDes.lifeCycle"/></h4>
	    		<div>${taxonDescription.lifeCycle}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="habitatDistribution">
			<c:if test="${not empty taxonDescription.habitat ||
				not empty taxonDescription.distribution }">
		    <h3><fmt:message key="taxonDes.habitatDistribution"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.habitat }">
	    		<h4><fmt:message key="taxonDes.habitat"/></h4>
	    		<div>${taxonDescription.habitat}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.distribution }">
	    		<h4><fmt:message key="taxonDes.distribution"/></h4>
	    		<div>${taxonDescription.distribution}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="usesManagement">
			<c:if test="${not empty taxonDescription.theUses ||
				not empty taxonDescription.theManagement}">
		    <h3><fmt:message key="taxonDes.usesManagement"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.theUses }">
	    		<h4><fmt:message key="taxonDes.uses"/></h4>
	    		<div>${taxonDescription.theUses}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.theManagement }">
	    		<h4><fmt:message key="taxonDes.management"/></h4>
	    		<div>${taxonDescription.theManagement}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="demographyConservation">
		    <c:if test="${not empty taxonDescription.threatStatus ||
		    	not empty taxonDescription.territory ||
		    	not empty taxonDescription.populationBiology }">
		    <h3><fmt:message key="taxonDes.demographyConservation"/></h3>
		    </c:if>
		    <c:if test="${not empty taxonDescription.threatStatus }">
	    		<h4><fmt:message key="taxonDes.threatStatus"/></h4>
	    		<div>${taxonDescription.threatStatus}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.territory }">
	    		<h4><fmt:message key="taxonDes.territory"/></h4>
	    		<div>${taxonDescription.territory}</div>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.populationBiology }">
	    		<h4><fmt:message key="taxonDes.populationBiology"/></h4>
	    		<div>${taxonDescription.populationBiology}</div>
	    	</c:if>
	    	
		</div>
		
		<div id="description">
		    <c:if test="${not empty taxonDescription.scientificDescription }">
		    	<h3><fmt:message key="taxonDes.description"/></h3>
	    		<h4><fmt:message key="taxonDes.scientificDescription"/></h4>
	    		<div>${taxonDescription.scientificDescription}</div>
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
	    	<c:if test="${not empty taxonDescription.creators }">
	   		<p>
	   			<strong><fmt:message key="taxonDes.author"/></strong>: ${taxonDescription.creators}
	 		</p>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.contributors }">
	    	<p>
	   			<strong><fmt:message key="taxonDes.contributors"/></strong>: ${taxonDescription.contributors}
			</p>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.taxonRecordId }">
	    	<p>
	   			<strong><fmt:message key="taxonDes.taxonRecordId"/></strong>: ${taxonDescription.taxonRecordId}
	   		</p>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.dateLastModified }">
	    	<p>
	   			<strong><fmt:message key="taxonDes.dateLastModified"/></strong>: ${taxonDescription.dateLastModified}
			</p>
	    	</c:if>
	    	<c:if test="${not empty taxonDescription.dateCreated }">
	    	<p>
	   			<strong><fmt:message key="taxonDes.dateCreated"/></strong>: ${taxonDescription.dateCreated}
			</p>
	    	</c:if>
		</div>
		</c:if>
		
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