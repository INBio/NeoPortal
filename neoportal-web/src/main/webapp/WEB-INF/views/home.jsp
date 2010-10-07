<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/holamundo.css" />" rel="stylesheet"  type="text/css" />		
	<script type="text/javascript" src="<c:url value="/resources/holamundo.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js" />"></script>
</head>

<body onload="initPage('${pageContext.request.contextPath}');">

<h1>
	Hola Mundo!
</h1>


<input id="txt" type="text" value="">
<a id="simple" class="textLink" href="javascript:holaMundo();">GET /simple with YUI javascript</a>

<div id="txtFromServer"></div>

</body>
</html>
