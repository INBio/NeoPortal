<%@ tag description="Include javascript files based on params" dynamic-attributes="files" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${files}" var="file">
	<script type="text/javascript" src="<c:url value="/resources/species/${file.value}" />"></script>
</c:forEach>