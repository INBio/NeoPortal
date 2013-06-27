<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>
<n:base>
	<jsp:attribute name="head">
	            
        <!-- Open Layers and Google Maps API-->
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3.8&sensor=false"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="http://openlayers.org/api/2.12-rc7/OpenLayers.js"></script>
        
        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.xslt.js" />" ></script>

        <!--   jQuery LightBox     -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/jquery-lightbox-0.5/js/jquery.lightbox-0.5.min.js" />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"/>"/>
        
        <!--   jQuery Masonry plugin - dynamic layout for images  -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/jquery.masonry.min.js" />"></script>
        
        <!-- Util javaScript -->
        <script type="text/javascript" >
            var scientificName = "${scientificName}";
            var provider = "";
            var contextPath = "${pageContext.request.contextPath}";
            
            //labels
            var scientificNameT = '<fmt:message key="scientific_name"/>';
            var institutionT = '<fmt:message key="institution"/>';
            var catalogT = '<fmt:message key="catalog_number"/>';
            var countryT = '<fmt:message key="country"/>';
            var provinceT = '<fmt:message key="province"/>';
            var countyT = '<fmt:message key="county"/>';
            var latitudeT = '<fmt:message key="latitude"/>';
            var longitudeT = '<fmt:message key="longitude"/>';
                
            var noDataFound = '<fmt:message key="noDataFound"/>';
        </script>
        
        <script type="text/javascript" src="<c:url value="/resources/occurrences/map-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/occurrences/dataTable-stuff.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/species/taxon-description-behavior.js" />"></script>
                	
	</jsp:attribute>
	<jsp:body>
        
        <div class="data_wrapper species_page">
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
			<h2>${scientificName}</h2>
			
			<div class="content_left_column">
				<div id="shareThis"></div>
				<div id="species_images"></div>
				
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
					
					<n:taxonomy taxonDescription="${taxonDescription}"></n:taxonomy>
					
					<div id="information">
					    <h3><fmt:message key="taxonDes.information"/></h3>
					    <c:if test="${not empty taxonDescription.language }">
				    		<h4><fmt:message key="taxonDes.language"/></h4>
				    		<div>${taxonDescription.language}</div>
				    	</c:if>
				    	<c:if test="${not empty taxonDescription.creators }">
				    		<h4><fmt:message key="taxonDes.author"/></h4>
				    		<div>${taxonDescription.creators}</div>
				    	</c:if>
				    	<c:if test="${not empty taxonDescription.contributors }">
				    		<h4><fmt:message key="taxonDes.contributors"/></h4>
				    		<div>${taxonDescription.contributors}</div>
				    	</c:if>
				    	<c:if test="${not empty taxonDescription.taxonRecordId }">
				    		<h4><fmt:message key="taxonDes.taxonRecordId"/></h4>
				    		<div>${taxonDescription.taxonRecordId}</div>
				    	</c:if>
				    	<c:if test="${not empty taxonDescription.dateLastModified }">
				    		<h4><fmt:message key="taxonDes.dateLastModified"/></h4>
				    		<div>${taxonDescription.dateLastModified}</div>
				    	</c:if>
				    	<c:if test="${not empty taxonDescription.dateCreated }">
				    		<h4><fmt:message key="taxonDes.dateCreated"/></h4>
				    		<div>${taxonDescription.dateCreated}</div>
				    	</c:if>
					</div>
					
					<div id="externalSource">
					    <%-- TODO: get external sources from database --%>
					    <h3><fmt:message key="taxonDes.externalSource"/></h3>
					    <p><a href="http://www.biodiversitylibrary.org/name/${scientificName}">BHL</a></p>
					    <p><a href="http://ara.inbio.ac.cr/SSTN-IABIN/search/${scientificName}">IABIN</a></p>
					    <p><a href="http://cro.ots.ac.cr/rdmcnfs/datasets/exsrch.phtml?words=${scientificName}&ds=binabitrop">OET Binabitrop</a></p>
					    <p><a href="http://species.wikimedia.org/wiki/${scientificName}">Wikispecies</a></p>
					    <p><a href="http://www.boldsystems.org/views/taxbrowser.php?taxon=${scientificName}">Barcode of life data systems</a></p>
					</div>
				</div>
			</div>
			
			<div class="sidebar_right">
                <div id="occurrences" class="tab_wrapper data_wrapper">
                    <ul id="view_control">
                        <li><a rel="map" href="#"><fmt:message key="occurrences_map_label" /></a></li>
                        <li><a rel="data" href="#"><fmt:message key="occurrences_data_label" /></a></li>
                        <li><a rel="split" href="#"><fmt:message key="occurrences_split_label" /></a></li>
                    </ul>
                    <!-- Table Panel -->
                    <div id="tablePanelContainer">
                        <!--<h4><fmt:message key="occurrences_table"/></h4>-->

                    </div>
                    <!-- Map Panel -->
                    <div id="mapPanel" class="extendMap">
                        <div id="map"></div>
<!--                        <div id="wrapper">
                            <div id="location"></div>
                            <div id="scale"></div>
                        </div>-->
                    </div>

                    <!-- Extra Panel -->
                    <div id="extraPanel"></div>
                </div>
            </div> <!-- end sidebar right -->
        </div>


	</jsp:body>
</n:base>