<%-- 
    Document   : header_inner
    Created on : 12/10/2011
    Author     : avargas
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div id="header">
            <div id="header_inner" class="data_wrapper">
                <a href="/<c:out value="${pageContext.request.contextPath}"/>/">
                    <img alt="logo atta" src="<c:out value="${pageContext.request.contextPath}"/><spring:theme code='images_path'/>logo-atta.png"></img>
                </a>
                <h1><fmt:message key="app_name"/></h1>
                <div id="menu">
                    <div id="menu_inner" class="data_wrapper">
                        <ul>
                            <li><a href="<c:out value="${pageContext.request.contextPath}"/>"><fmt:message key="menu_home"/></a></li>
                        </ul>
                    </div>
                </div>  
            </div>
        </div>
        
