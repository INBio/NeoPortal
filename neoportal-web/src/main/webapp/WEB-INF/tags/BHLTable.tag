<%@ tag description="Display the images for species page" language="java" 
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>	 -->
<div class="BHLTable">

<p class="center">
<a class="btn btn-warning has-spinner">Button4</a>
<p>
<script>BHL('${scientificName}');</script>
<h3><fmt:message key="referencesFound"/></h3>
<div id=BHLInfo></div>	

</div>
