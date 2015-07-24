<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>
<n:base>
	<jsp:attribute name="head">
		<script type="text/javascript">
	            
		    //Variables to internacionalize the service titles
		    var serviceTitleT = "<fmt:message key="service_title"/>";
		    var seeOccurrencesT = '<fmt:message key="see_occurrences"/>';
		    var seeMultimediaT = '<fmt:message key="see_multimedia"/>';
		    var seeSpeciesT = '<fmt:message key="see_species"/>';
		    var externalTitleT = '<fmt:message key="external_services"/>';
		    var seeOnFlickrT = '<fmt:message key="see_on_flickr"/>';
		    var seeOnPicasaT = '<fmt:message key="see_on_picasa"/>';
		    var seeOnGbifT = '<fmt:message key="see_on_gbif"/>';
		    var seeOnEolT = '<fmt:message key="see_on_eol"/>';
		    var seeOnWikiST = '<fmt:message key="see_on_wiki"/>';            
		    var commonNameT = '<fmt:message key="common_name"/>';
		    var scientificNameT = '<fmt:message key="scientific_name"/>';
		    var imageT = '<fmt:message key="image"/>';
		    
		    //internacionalize the table pagination 
		    var tableInfo = '<fmt:message key="table_pagination_info"/>';
		    var tableFirst = '<fmt:message key="table_pagination_first"/>';
		    var tableLast = '<fmt:message key="table_pagination_last"/>';
		    var tableNext = '<fmt:message key="table_pagination_next"/>';
		    var tablePrevious = '<fmt:message key="table_pagination_previous"/>';
		    
		    var noResultsFoundT = '<fmt:message key="noResultsFound"/>';
	            
		</script>
	
	</jsp:attribute>
	<jsp:body>
        
        <c:if test="${state=='home'}">
        <div class="grid_wrapper">
        	<n:groupNav style="${style}" groupNavList="${group_nav}"></n:groupNav>
       	</div>
        </c:if>
        
        <c:if test="${state=='search'}">
        <div class="data_wrapper">
        	<n:taxonFeature taxonFeature="${taxonFeature}" />
        	<!-- taxon list -->
			<n:taxonList taxonList="${taxonList}"></n:taxonList>
        </div>
        </c:if>

	</jsp:body>
</n:base>