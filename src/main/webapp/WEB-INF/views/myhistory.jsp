<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<form action="myhistory" method="post">
	<select name="type">
		<option <c:if test="${type eq null }">  selected= "selected" </c:if> value="">-전체-</option>
		<option <c:if test="${type eq 1}">  selected= "selected" </c:if>   value="1">승진</option>
		<option <c:if test="${type eq 3}">  selected= "selected" </c:if>   value="3">부서이동</option>
	</select>
	<input class="btn btn-secondary"  type="submit" value="찾기" >
	</form>	
		<hr>
	
	
	
	<form action="excelmyhistory" method="post">
		<input type="hidden" name="type" value="${type}">
		
		<div id="list" style="padding-bottom: 50px;">
    	  <b>조회결과</b>
    	</div>
	<table class="table">


		<tr style="background-color: #e6ecff">
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
		<tr>
			<td colspan="6" align="right" style="border: none;">
				<input  class="btn btn-primary" type="submit" value="엑셀">
				<%--
				<input type="button" onclick="location.href='exlefindemp'" value="엑셀">
				 --%>
			</td>
		</tr>
	</table>
	</form>
	
	
	
	
	
				
</body>
</html>