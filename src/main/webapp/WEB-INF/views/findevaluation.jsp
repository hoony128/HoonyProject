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
	<form action="findevaluation" method="post">
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

	
	<table class="table">

		<tr style="border: none;">
			<td style="font-style:normal;">인사고과 누락표</td>
			<td colspan="4" align="right"> <input type="button" class="btn btn-primary" onclick="post_to_url('excelfindevaluation', {'depart':'${depart}'});" value="엑셀"></td>
		</tr>
		<tr align="center" style="background-color: #e6ecff">
			<th>소속부서</th>
			<th>사번</th>
			<th>직급</th>
			<th>이름</th>			
		</tr>
	
		<c:if test="${empty flist}">
			<tr>
				<td colspan="4" align="center">해당부서에는 인사고과 누락대상자가 없습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${not empty flist }">
			<c:forEach var="vo" items="${flist }">
				<tr align="center">
					<td>${vo.depart_d }</td>
					<td>${vo.id_emp}</td>
					<td>${vo.lev_l}</td>
					<td>${vo.name}</td>				
				</tr>		
			</c:forEach>
		</c:if>
	</table>
		
</body>
</html>