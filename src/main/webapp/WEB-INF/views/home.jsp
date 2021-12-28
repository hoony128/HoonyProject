<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Home</title>
</head>
<body>
		<c:if test="${not empty sessionScope.vo }">
			<script type="text/javascript">
				location.href='${pageContext.request.contextPath}/index'	
			</script>
		</c:if>
		<c:if test="${empty sessionScope.vo }">
			<script type="text/javascript">
				location.href='${pageContext.request.contextPath}/login'	
			</script>
		</c:if>
		
</body>
</html>
