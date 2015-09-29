<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>
<n:base>
	<jsp:attribute name="head">  
	 
		        <!-- Open Layers and Google Maps API-->
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3.8&sensor=false"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/openlayers/style.css"/>">
        <script type="text/JavaScript" src="<c:url value="/resources/openlayers/OpenLayers.js" />"></script>
        
        <!--   jQuery LightBox     -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/jquery-lightbox-0.5/js/jquery.lightbox-0.5.min.js" />"></script>
   		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"/>"/>
        
        <!--   jQuery Masonry plugin - dynamic layout for images  -->
        <script type="text/javascript" src="<c:url value="/resources/plugins/jquery.masonry.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/species/getbooksBHL.js" />"></script>
        
        <!-- Util javaScript -->
        <script type="text/javascript" >
            var scientificName = "${scientificName}";
            var provider = "";
            var contextPath = '${pageContext.request.contextPath}';
            
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

            var ne = '<fmt:message key="NotEvaluated"/>';
            var dd = '<fmt:message key="DataDeficient"/>';
            var lc = '<fmt:message key="LeastConcern"/>';
            var nt = '<fmt:message key="NearThreatened"/>';
            var vu = '<fmt:message key="Vulneable"/>';
            var en = '<fmt:message key="Endangered"/>';
            var cr = '<fmt:message key="CriticallyEndangered"/>';
            var ew = '<fmt:message key="Extinctinthewild"/>';
            var ex = '<fmt:message key="Extinct"/>';
        </script>
        
        <script type="text/javascript" src="<c:url value="/resources/occurrences/map-stuff.js" />"></script>
                	
        
        <script type="text/javascript" src="<c:url value="/resources/species/species.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/species/conservationStatus.js" />"></script>
        <title>INBio - ${taxon.defaultName}</title>

        <!-- meta description -->
        <c:if test="${(context == 'taxonDescription') && (not empty taxonDescription.fullDescriptionUnstructured )}">
        <meta name="description" content="<c:out value="${taxonDescription.fullDescriptionUnstructured}" />">
        </c:if>
	</jsp:attribute>
	      
	<jsp:body>
        <script>RL("${taxon.defaultName}",ne,dd,lc,nt,vu,en,cr,ew,ex);</script>
        <div class="data_wrapper species_page">
        	
        	<c:if test="${taxon.taxonomicalRangeId < 13}">
			<h2>${taxon.defaultName}</h2>
			</c:if>
			<c:if test="${taxon.taxonomicalRangeId >= 13}">
			<h2 class="scientific-name">${taxon.defaultName}</h2>
			<c:if test="${not empty taxon.commonNames}">
			<p class="common-names">${taxon.commonNames}</p>
			</c:if>
			</c:if>
			
			<div id="menu">
			<ul class="nav nav-tabs" role="tablist">
		        <li role="presentation" <c:if test="${context == 'taxonDescription'}">class="active"</c:if> ><a href="${taxonUrl}"><fmt:message key="tab_taxonDescription" /></a></li>
				<li role="presentation" <c:if test="${context == 'images'}">class="active"</c:if> ><a href="${taxonUrl}/images"><fmt:message key="tab_images" /></a></li>
				<li role="presentation" <c:if test="${context == 'occurrences'}">class="active"</c:if> ><a href="${taxonUrl}/occurrences"><fmt:message key="tab_occurrences" /></a></li>
				<li role="presentation" <c:if test="${context == 'map'}">class="active"</c:if> ><a href="${taxonUrl}/map"><fmt:message key="tab_map" /></a></li>
				<li role="presentation" <c:if test="${context == 'BHL'}">class="active"</c:if> ><a href="${taxonUrl}/BHL"><fmt:message key="tab_bhl" /></a></li>
      		</ul>
      		</div>

            <div>
			<c:choose>
				<c:when test="${context == 'taxonDescription'}">
					<n:taxonDescription></n:taxonDescription>	
				</c:when>
				<c:when test="${context == 'images'}">
				<div class="grid-layout">
				    <n:imageGallery images="${images}"></n:imageGallery>
			    </div>
				</c:when>
				<c:when test="${context == 'occurrences'}">
				<c:if test="${not empty scientificName }">
				<div class="controls">
					<form method="post" action="${pageContext.request.contextPath}/api/species/${scientificName}/occurrences/export">
						<button name="export" type="submit">Download</button>
					</form>
				</div>
				</c:if>
				    <n:occurrenceTable occurrences="${occurrences}"></n:occurrenceTable>
				</c:when>
				<c:when test="${context == 'map'}">
				<c:choose>
					<c:when test="${taxon.taxonomicalRangeId >= 13}">
					<div id="map"></div>
					<script type="text/javascript">
						var scientificName = '${scientificName}';
					</script>
					</c:when>
					<c:otherwise>
					<p><fmt:message key="noMapFeature" /></p>
					</c:otherwise>
				</c:choose>
				</c:when>
				<c:when test="${context == 'BHL'}">			
					<n:BHLTable></n:BHLTable> 						
				</c:when>
			</c:choose>
				
			</div>
			
        </div>


	</jsp:body>
</n:base>
