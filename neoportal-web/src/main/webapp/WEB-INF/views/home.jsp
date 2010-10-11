<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Home</title>
  <!-- neoportal js and css -->
  <link href="<c:url value="/resources/holamundo.css"/>" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<c:url value="/resources/holamundo.js" />"></script>

  <!-- yui js and css -->
  <link rel="stylesheet" type="text/css" href="resources/yui/datatable/assets/skins/sam/datatable.css">
  <link rel="stylesheet" type="text/css" href="resources/yui/fonts/fonts-min.css">
  <script type="text/javascript" src="<c:url value="/resources/yui/yahoo-dom-event/yahoo-dom-event.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/yui/connection/connection-min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/yui/element/element-min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/yui/datasource/datasource-min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/yui/datatable/datatable-min.js"/>"></script>


</head>

<body onload="initPage('${pageContext.request.contextPath}');"
	class="yui-skin-sam">

  <h1>Search Home Page [Esto es un hola mundo!]</h1>

  <div id="searchCriteriaPanel" class="searchPanel">
    <input id="searchInput" type="text" value=""> 
      <a id="simple" class="textLink" href="javascript:holaMundo();">Search</a>
  </div>


  <div id="resultsPanel" class="content">
    <h3>Resultados</h3>
    <div id="tablePanel"></div>
  </div>

</body>
</html>
