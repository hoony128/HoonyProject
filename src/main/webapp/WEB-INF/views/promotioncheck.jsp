<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>정기 진급 평가자 목록</title>
</head>
<body>
	<br>
	<form action="promotioncheck" method="post">
		<select name="check">
			<option value="1">-전체-</option>
			<option value="2">승진대상자</option>
			<option value="3">승진탈락</option>
		</select>
		<input class="btn btn-secondary" type="submit" value="찾기" >
	</form>	
	<hr>
	
		<div id="list" style="padding-bottom: 50px;">
    	  <b>정기승진평가 결과</b>
    	</div>
	<table class="table"> 

		<tr align="center" style="background-color: #e6ecff">
			<th>소속부서</th>
			<th>사번</th>
			<th>직급</th>
			<th>직책</th>
			<th>이름</th>
			<th>인사고과평균점수</th>
			<th>승진평가점수</th>
			<th>합계점수</th>
			
		</tr>
	
		<c:if test="${empty alist}">
			<tr>
				<td colspan="8" align="center">대상이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${not empty alist }">
			<c:forEach var="vo" items="${alist }">
				<tr align="center">
					<td>${vo.dname}</td>
					<td>${vo.id_emp}</td>
					<td>${vo.lev_l}</td>
					<td>${vo.duty_d}</td>				
					<td>${vo.name}</td>				
					<td>${vo.score}</td>				
					<td>${vo.testscore}</td>				
					<td>${vo.finalscore}</td>				
				</tr>		
			</c:forEach>
		</c:if>

	</table>

		
</body>
</html>