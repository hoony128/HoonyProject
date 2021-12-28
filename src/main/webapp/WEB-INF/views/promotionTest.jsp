<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function a(formName){
	var f = document.getElementById(formName);
	f.action="promotionTesting";
	f.submit();
}
</script>

<title> 진급 정기 평가 </title>
</head>
<body>
	<br>
	<form action="promotionTest" method="post">
		<select name="depart2">
			<option value="">-전체-</option>
				<c:forEach var="vo" items="${dlist }">
					<option value="${vo.idx }">
						${vo.depart }
					</option>
				</c:forEach>
		</select>
		<input  class="btn btn-secondary" type="submit" value="찾기" >
	</form>	
	<hr>
	
	
	
	
		<div id="list" style="padding-bottom: 50px;">
    	  <b>진급평가</b>
    	</div>
	<table  class="table">


		<tr style="background-color: #e6ecff" align="center">
			<th>부서</th>
			<th>직급</th>
			<th>직책</th>
			<th>사번</th>
			<th>이름</th>
			<th>평가시작</th>
		</tr>
		<c:if test="${empty alist}">
			<tr>
				<td  colspan="6" align="center">평가할 대상이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty alist }"> 
			
			<c:forEach var="vo" items="${alist }" varStatus="vs"> 
				
				<tr align="center">
					<td>${vo.dname }</td>
					<td>${vo.lev_l}</td>
					<td>${vo.duty_d}</td>
					<td>${vo.id_emp }</td>
					<td>${vo.name }</td>
					


					<td>
					<form name="formName${vs.count }" id="formName${vs.count }" method="post">
						<input type="hidden" name="duty" value="${vo.duty}"> 						
						<input type="hidden" name="depart" value="${vo.depart}">
						<input type="hidden" name="lev" value="${vo.lev}">
						<input type="hidden" name="dname" value="${vo.dname }" > 
						<input type="hidden" name="lev_l" value="${vo.lev_l }" >
						<input type="hidden" name="duty_d" value="${vo.duty_d }" >
						<input type="hidden" name="name_v" value="${vo.name }" >
						<input type="hidden" name="Id_emp_v" value="${vo.id_emp }" >
						
						
						<c:if test="${vo.rt==1}">
						<input type="button" id="none" class="btn btn-danger" value="평가종료" onclick="a('formName${vs.count}');">
						</c:if>
						<c:if test="${vo.rt==0}">
						<input type="button" class="btn btn-primary" value="평가시작" onclick="a('formName${vs.count}');">
						</c:if>
					
					</form>
					</td>				
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>

	</table>
	
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert('이미 평가한 사원입니다.\n 변경사항이 있을경우 인사팀에 문의바랍니다.');
		</script>
	</c:if>
</body>
</html>