<%@ page language="java" contentType="application/vnd.ms-excel; name='excel', text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>다운로드</title>

</head>
<body>




	<table>
		<tr>
			<th>부서명</th>
			<th>직급</th>
			<th>직책</th>
			<th>사번</th>
			<th>이름</th>
			<th>메일</th>
			<th>전화번호</th>
		</tr>
		<c:if test="${empty paging.lists}">
			<tr>
				<td colspan="8" align="center">기록이 분명히 있음 에러이다!</td>
			</tr>
		</c:if>
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty paging.lists }"> 
			<c:forEach var="vo" items="${paging.lists }" varStatus="vs"> 
				<tr align="center">
					<td> ${vo.depart_d }</td>
					<td> ${vo.lev_l}</td>
					<td> ${vo.duty_du}</td>
					<td> <c:out value="${vo.id_emp}"/> </td>
					<td> ${vo.name }</td>
					<td> ${vo.email }</td>
					<td> ${vo.first_phone}-${vo.mid_phone}-${vo.last_phone} </td>
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>
		<tr>
			<td colspan="8" align="right" style="border: none;">
				<input type="button" onclick="location.href='excel.jsp'" value="엑셀">
			</td>
		</tr>
	</table>
	
	
	<%
		response.setHeader("Content-Disposition","attachment;filename=file.xls");
	    response.setHeader("Content-Description", "JSP Generated Data"); 
	    response.setContentType("application/vnd.excel");
	%>



</body>
</html>