<%@page import="java.net.URLEncoder"%>
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

	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th><c:if test="${type eq 1}">승진 후 직급</c:if><c:if test="${type ne 1}">직급</c:if></th>
			<th>직책</th>
			<th><c:if test="${type eq 3}">이동 부서</c:if><c:if test="${type ne 1}">부서</c:if></th>
			<th>일자</th>
			<c:if test="${type eq 2 }"><th>재직여부</th></c:if>

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
					<c:if test="${type eq 2 }"><td>${vo.state_s}</td></c:if>					
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>
	</table>

	
	<c:if test="${type eq 1}">
	<%
		response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("승진이력","UTF-8")+".xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>	
	</c:if>
	<c:if test="${type eq 2}">
	<%
		response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("퇴사이력","UTF-8")+".xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>
	</c:if>
	<c:if test="${type eq 3}">
	<%
		response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("부서이동이력","UTF-8")+".xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>
	</c:if>
		<c:if test="${type eq null}">
	<%
		response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("회사전체이력","UTF-8")+".xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>
	</c:if>
</body>
</html>