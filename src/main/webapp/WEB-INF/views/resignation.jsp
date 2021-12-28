<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function a(formName){
	var f = document.getElementById(formName);
	f.action="resignationInput";
	f.submit();
}
</script>

<title>${dname }퇴사요청</title>
</head>
<body>


	<div id="list" style="padding-bottom: 50px;">
    	  <b>${dname}부서 퇴사요청</b>
    	</div>
	<table  class="table">


		<tr style="background-color: #e6ecff" align="center">
			<th>사번</th>
			<th>이름</th>
			<th>직급</th>
			<th>직책</th>
			<th>평가시작</th>
		</tr>
		<c:if test="${empty alist}">
			<tr>
				<td  colspan="5" align="center">기록이 없습니다.</td>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty alist }"> 
			
			<c:forEach var="vo" items="${alist }" varStatus="vs"> 
				
				<tr align="center">
					<td>${vo.id_emp}</td>
					<td>${vo.name }</td>
					<td>${vo.lev_l}</td>
					<td>${vo.duty_du}</td>
					<td>
					<form name="formName${vs.count }" id="formName${vs.count }" method="post">

						<input type="hidden"  name="dname" readonly="readonly"   value="${dname }">
						<input type="hidden" name="name_p" value="${name }">
						<input type="hidden" name="Id_emp_p" value="${Id_emp }">
						<input type="hidden" name="lev" value="${vo.lev }">
						<input type="hidden" name="duty" value="${vo.duty }">


						<input type="hidden"  name="name_v" readonly="readonly"    value="${vo.name }"> 
						<input  type="hidden" name="Id_emp_v"   readonly="readonly"   value="${vo.id_emp }">
						<input  type="hidden" name="lev_l"   readonly="readonly"   value="${vo.lev_l}">
						<input  type="hidden" name="duty_d"   readonly="readonly"   value="${vo.duty_du}">
						

					
						<c:if test="${vo.rw==1}">
						<input type="button" id="none" class="btn btn-danger" value="요청완료" onclick="a('formName${vs.count}');">
						</c:if>
						<c:if test="${vo.rw==0}">
						<input type="button" class="btn btn-primary" value="퇴사요청" onclick="a('formName${vs.count}');">
						</c:if>
					
					
					</form>
					</td>				
				</tr>
			</c:forEach>
		</c:if> <%-- 있을 경우 종료 --%>

	</table>


		


	
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert('이미 퇴사를 요청하였습니다.');
		</script>
	</c:if>
</body>
</html>