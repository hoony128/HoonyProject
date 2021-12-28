<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
function a(formName){
	var f = document.getElementById(formName);
	f.action="specialpromotionwrite";
	f.submit();
}
function none() {
	alert("결재중입니다.")
}


</script>
<title>비정기 승진 신청</title>
</head>
<body>

	
	<br>
	<form action="specialpromotion" method="post">
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
    	  <b>특진신청서</b>
    	</div>
	<table  class="table">


		<tr style="background-color: #e6ecff" align="center">
			<th>부서</th>
			<th>직급</th>
			<th>직책</th>
			<th>사번</th>
			<th>이름</th>
			<th>작성</th>
		</tr>
		<c:if test="${empty alist}">
			<tr>
				<td  colspan="5" align="center">해당부서에는 사원이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty alist }"> 
			
			<c:forEach var="vo" items="${alist }" varStatus="vs"> 
				
				<tr align="center">
					<td>${vo.depart_d}</td>
					<td>${vo.lev_l}</td>
					<td>${vo.duty_du}</td>
					<td>${vo.id_emp}</td>
					<td>${vo.name} </td>
					
					<td>
					
					<form name="formName${vs.count }" id="formName${vs.count }" method="post">
						<input type="hidden" name="dname" value="${vo.depart_d}"> 
						<input type="hidden" name="lev_now"  value="${vo.lev_l}"> 
						<input type="hidden"  name="lev" value="${vo.lev}">
						<input type="hidden" name="duty"  value="${vo.duty_du}"> 
						<input type="hidden" name="Id_emp"  value="${vo.id_emp}"> 
						<input type="hidden" name="name"  value="${vo.name}">
						<c:if test="${vo.rw eq 0 }">
						<input type="button" value="특진신청" class="btn btn-primary" onclick="a('formName${vs.count}');">				
						</c:if>
						<c:if test="${vo.rw eq 1 }">
						<input type="button" id="no" value="신청완료" class="btn btn-danger" onclick="none();">				
						</c:if>
					</form>
					</td>				
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>

	</table>
		
		
		
		
		
		

		
		
		
</body>
</html>