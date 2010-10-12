<%-- 
    Document   : header
    Created on : 12/01/2010, 03:51:55 PM
    Author     : esmata
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div style="-rave-layout: grid">
            <form:form>

                <div id="banner-rep">
                    <div id="banner">
                        <div id="title">
                            <h1>
                                <fmt:message key="applicationName"/>
                            </h1>
                        </div>
                    </div>
                </div>
                <!--<div id="header_links">
                    <a class="link_home" href="welcome.htm"><fmt:message key="home_title"/></a>
                    <a class="link_about" href="about.htm"><fmt:message key="about_title"/></a>
                </div>-->

            </form:form>
        </div>

