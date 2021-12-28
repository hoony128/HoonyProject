<%@ page language="java" contentType="application/vnd.ms-excel; name='excel', text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이력조회</title>

</head>
<body>
			<td class="title" colspan="8"><c:if test="${type eq null}">전체이력조회</c:if><c:if test="${type eq 1}">승진이력조회</c:if><c:if test="${type eq 3}">부서이동이력조회</c:if></td>

	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직급</th>
			<th>직책</th>
			<th>부서</th>
			<th>일자</th>

		</tr>
		<c:if test="${empty hlist}">
			<tr>
				<td colspan="6" align="center">기록이 없습니다.</td>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty hlist }"> 
			
			<c:forEach var="vo" items="${hlist }" varStatus="vs"> 
				<tr align="center">
					<td>${vo.id_emp }</td>
					<td>${name }</td>
					<td>${vo.lev_l }</td>
					<td> ${vo.duty_du}</td>
					<td> ${vo.depart_d}</td>
					<td><fmt:formatDate value="${vo.updatedate }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>
	</table>

	
		
	<%
		response.setHeader("Content-Disposition","attachment;filename=history.xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>
	
	
				
</body>
</html>