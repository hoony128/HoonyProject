<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="vo" items="${allList.lists }">
		${vo.name } - ${vo.depart_d }<br>
	</c:forEach>
	<select>
		<c:forEach var="vo" items="${dlist }">
			<option value="${vo.idx }">
				${vo.depart }
			</option>
		</c:forEach>
	</select>
</body>
</html>