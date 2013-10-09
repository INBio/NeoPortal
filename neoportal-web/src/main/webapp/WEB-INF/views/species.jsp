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
                	
        <!-- 	FIXME: change home.js for a basic search script -->
        <script type="text/javascript" src="<c:url value="/resources/species/home.js" />"></script>
        
        <script type="text/javascript" src="<c:url value="/resources/species/species.js" />"></script>
                	
	</jsp:attribute>
	<jsp:body>
        
        <div class="data_wrapper species_page">
        	
        	<c:if test="${taxon.taxonomicalRangeId < 13}">
			<h2>${taxon.defaultName}</h2>
			</c:if>
			<c:if test="${taxon.taxonomicalRangeId >= 13}">
			<h2 class="scientific-name">${taxon.defaultName}</h2>
			</c:if>
			
			<nav><ul>
				<li><a href="${taxonUrl}"><fmt:message key="tab_taxonDescription" /></a></li>
				<li><a href="${taxonUrl}/images"><fmt:message key="tab_images" /></a></li>
				<li><a href="${taxonUrl}/occurrences"><fmt:message key="tab_occurrences" /></a></li>
			</ul></nav>
			
			<div class="">
				<c:choose>
					<c:when test="${context == 'taxonDescription'}">
						<n:taxonDescription></n:taxonDescription>	
					</c:when>
					<c:when test="${context == 'images'}">
					    <n:imageGallery images="${images}"></n:imageGallery>
					</c:when>
					<c:when test="${context == 'occurrences'}">
					<c:if test="${not empty scientificName })">
					<div class="controls">
						<form method="post" action="${pageContext.request.contextPath}/api/species/${scientificName}/occurrences/export">
							<button name="export" type="submit" >Download</button>
						</form>
					</div>
					</c:if>
					    <n:occurrenceTable occurrences="${occurrences}"></n:occurrenceTable>
					</c:when>
				</c:choose>
				
			</div>
			
        </div>


	</jsp:body>
</n:base>