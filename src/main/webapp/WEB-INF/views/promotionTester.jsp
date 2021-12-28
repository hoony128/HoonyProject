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
	<form action="promotionTester" method="post">
		<select name="depart2">
			<option value="">-전체-</option>
				<c:forEach var="vo" items="${dlist }">
					<option value="${vo.idx }">
						${vo.depart }
					</option>
				</c:forEach>
		</select>
		<input class="btn btn-secondary" type="submit" value="찾기" >
	</form>	
	<hr>
		<div id="list" style="padding-bottom: 50px;">
    	  <b>진급 시험 평가 대상자</b><br>
    	  <b>(2년 동안 인사고과점수 총합이 300점이 이상인 사람)</b><br>
    	  <b>(*승진시 기존의 인사고과점수는 초기화 됩니다.)</b>
    	</div>
	
	<%--엑셀화 시키기 --%>
	<table class="table">

		<tr align="center" style="background-color: #e6ecff">
			<th>소속부서</th>
			<th>이      름</th>
			<th>사원번호</th>
			<th>평균점수</th>
			<th>이메일</th>
		</tr>
	
		<c:if test="${empty alist}">
			<tr>
				<td colspan="5" align="center">해당부서에는 진급 평가 대상자가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${not empty alist }">
			<c:forEach var="vo" items="${alist }">
				<tr align="center">
					<td>${vo.dname }</td>
					<td>${vo.name}</td>
					<td>${vo.id_emp}</td>
					<td>${vo.score}</td>
					<td>
						<c:if test="${vo.email.trim().length() eq 0  }">등록된 이메일이 없습니다.</c:if>
						<c:if test="${vo.email.trim().length() ne 0 }">${vo.email }</c:if>
					</td> 
				</tr>		
			</c:forEach>
		</c:if>
	</table>
		
</body>
</html>