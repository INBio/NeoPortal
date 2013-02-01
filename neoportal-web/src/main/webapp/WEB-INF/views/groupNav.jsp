<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags" %>
<n:base>
	<jsp:attribute name="head">
		<script type="text/javascript">
	            
	            
		</script>
		<script type="text/javascript" src="<c:url value="/resources/js/groupNav.js" />"></script>
		
	</jsp:attribute>
	<jsp:body>
        <div class="data_wrapper">
  			<n:groupNav style="aside" groupNavList="${groupNavList}"></n:groupNav>
  			
  			<!-- taxon list -->
  			<div class="content_column_right">
				<n:taxonList taxonList="${taxonList}"></n:taxonList>
			</div>
 		</div>

	</jsp:body>
</n:base>