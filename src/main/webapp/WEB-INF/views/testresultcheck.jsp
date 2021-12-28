<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>정기승진 결과</title>

</head>
<body>

	<form action="testresultcheckOk" method="post">

		<div id="list" style="padding-bottom: 50px;">
    	  <b>정기승진평가 결과</b>
    	</div>
	
	<table class="table" >

		<tr align="center" style="background-color: #e6ecff">
			<th>소속부서</th>
			<th>사번</th>
			<th>직급</th>
			<th>직책</th>
			<th>이름</th>
			<th>승진평가점수</th>
			<th>평가자 이름</th>
			<th>평가자 사번</th>
			
			
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
					<td>${vo.id_emp_v}</td>
					<td>${vo.lev_l}</td>
					<td>${vo.duty_d}</td>
					<td>${vo.name_v}</td>
					<td>${vo.testscore1} / ${vo.testscore2} /${vo.testscore3} /${vo.testscore4}</td>					
					<td>${vo.name_p}</td>
					<td>${vo.id_emp_p}</td>
				</tr>		

			</c:forEach>
			<tr>
			<td colspan="8" align="right" style="border: none;">
				<input class="btn btn-primary" type="submit" value="평가종료">
			</td>			
		</tr>
		</c:if>

	</table>
		</form>


</body>
</html>