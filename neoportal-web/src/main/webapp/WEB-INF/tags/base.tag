<%@ tag description="Base layout for entire website" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>

<!-- Attributes -->
<%@ attribute name="head" description="Fragment code to add staff to the head" required="false" fragment="true" %>
<!doctype html>
<html>
	<head>
        <meta charset="utf-8">

        <!-- neoportal js and css -->
         <link rel="stylesheet" type="text/css" href="<c:url value="/themes/bootstrap/css/bootstrap.min.css" /> "/>
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code="styleSheet_3"/>" />
        <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='jquery-ui'/>" />
        
        <!-- jquery -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.inbPaginate-0.1.js" />" ></script>
        
        <!-- jquery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.xslt.js" />" ></script>	
         <link  type="text/javascript" href="<c:url value="/themes/bootstrap/js/bootstrap.min.js" /> "/>
		<script type="text/javascript" src="<c:url value="/themes/bootstrap/js/typeahead.bundle.js" />"></script>
		
		<!-- 	FIXME: change home.js for a basic search script -->
        <script type="text/javascript" src="<c:url value="/resources/species/home.js" />"></script>
		
		<jsp:invoke fragment="head"/>
		
		<title><fmt:message key="app_name"/></title>

        <script>
          (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
           (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
           m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
           })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  
          ga('create', 'UA-2292385-14', 'auto');
          ga('send', 'pageview');

        </script>
    </head>

    <body>
        <!-- Header -->
        <%@include file="/WEB-INF/jspf/header_inner.jspf" %>
        
        <jsp:doBody/>
        
        <!-- Footer -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>

    </body>
</html>
