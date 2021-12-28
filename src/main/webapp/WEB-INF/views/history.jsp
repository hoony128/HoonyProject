<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<title>전사원이력조회</title>

</head>
<body>
	<form action="history" method="post">
	<select name="type">
		<option <c:if test="${type eq null }">  selected= "selected" </c:if> value="">-전체-</option>
		<option <c:if test="${type eq 1}">  selected= "selected" </c:if>   value="1">승진</option>
		<option <c:if test="${type eq 2}">  selected= "selected" </c:if>   value="2">퇴사</option>
		<option <c:if test="${type eq 3}">  selected= "selected" </c:if>   value="3">부서이동</option>
	</select>
	<input class="btn btn-secondary"  type="submit" value="찾기" >
	</form>	
		<hr>
	
	
	
	<form action="excelhistory" method="post">
		<input type="hidden" name="type" value="${type}">
			<div id="list" style="padding-bottom: 50px;">
    	  <b><c:if test="${type eq null}">전체이력조회</c:if><c:if test="${type eq 1}">승진이력조회</c:if><c:if test="${type eq 2}">퇴사이력조회</c:if><c:if test="${type eq 3}">부서이동이력조회</c:if></b>
    	</div>

	<table class="table">

		<tr style="background-color: #e6ecff" align="center">
			<th>사번</th>
			<th>이름</th>
			<th><c:if test="${type eq 1}">승진 후 직급</c:if><c:if test="${type ne 1}">직급</c:if></th>
			<th>직책</th>
			<th><c:if test="${type eq 3}">이동 부서</c:if><c:if test="${type ne 3}">부서</c:if></th>
			<th>일자</th>
			<c:if test="${type eq 2 }"><th>재직여부</th></c:if>

		</tr>
		<c:if test="${empty hlist}">
			<tr>
				<c:if test="${type eq 2}">
				<td  colspan="7" align="center">기록이 없습니다.</td>
				</c:if>
				<c:if test="${type ne 2}">
				<td  colspan="6" align="center">기록이 없습니다.</td>
				</c:if>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty hlist }"> 
			
			<c:forEach var="vo" items="${hlist }" varStatus="vs"> 
				<tr align="center">
					<td>${vo.id_emp }</td>
					<td>${vo.name }</td>
					<c:if test="${type ne 1 }">
						<td>${vo.lev_l }</td>
					</c:if>						
					<c:if test="${type eq 1 }">
						<td>${vo.lev_l } -> ${vo.afterlev}</td>
					</c:if>						
					<td> ${vo.duty_du}</td>
					<td> ${vo.depart_d}</td>
					<td><fmt:formatDate value="${vo.updatedate }" pattern="yyyy-MM-dd"/></td>
					<c:if test="${type eq 2 }"><td>${vo.state_s}</td></c:if>					
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>
		<tr>
			<c:if test="${type eq 2}">
			<td colspan="7" align="right" style="border: none;">
				<input class="btn btn-primary"  type="submit" value="엑셀">
			</td>
			</c:if>
			<c:if test="${type ne 2}">
			<td colspan="6" align="right" style="border: none;">
				<input class="btn btn-primary" type="submit" value="엑셀">
			</td>
			</c:if>
		</tr>
	</table>
	</form>

</body>
</html>