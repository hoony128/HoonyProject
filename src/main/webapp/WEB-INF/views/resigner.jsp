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
	f.action="resignerNo";
	f.submit();
}
function b(formName){
	var f = document.getElementById(formName);
	f.action="resignerOk";
	f.submit();
}
function checkAllFunction() {
    $("input[id^='wwcheck']").prop("checked",$("#checkAll").is(":checked"));
 }
</script>	
<title>퇴사자 요청 리스트</title>
</head>
<body>

	<form action="resigner" method="post">
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
    	  <b>퇴사 요청</b>
    	</div>
	<form method="post" id="resignerform">
	<table  class="table">
		<tr align="center" style="background-color: #e6ecff" >
			<td width="10%">
			<input type="checkbox"  id="checkAll" onclick="checkAllFunction();" checked="checked" >
			</td>
			<td>부서</td>
			<td>직급</td>
			<td>직책</td>
			<td>사번</td>
			<td>이름</td>
			<td>이유</td>
		</tr>

		<c:if test="${empty alist}">
			<tr>
				<td  colspan="7" align="center">기록이 없습니다.</td>
			</tr>
		</c:if>
		<%-- 있을 경우 시작 --%>

			<c:if test="${not empty alist }"> 
			<c:forEach var="vo" items="${alist }" varStatus="vs">
				<tr align="center">
					<td>
						<input type="checkbox" id="wwcheck${vs.index}" name="check${vs.index}" checked="checked" value="Y">
						
						
						<input type="hidden" name="checkList[]" value="${vs.index}">
						<input type="hidden" name="checkId_v[${vs.index}]" value="${vo.id_emp_v}">
						<input type="hidden" name="checkName_v[${vs.index}]" value="${vo.name_v}">
						<input type="hidden" name="checkId_p[${vs.index}]" value="${vo.id_emp_p}">
						<input type="hidden" name="checkName_p[${vs.index}]" value="${vo.name_p}">
						<input type="hidden" name="checkReason[${vs.index}]" value="${vo.reason}">
						<input type="hidden" name="checkDep[${vs.index}]" value="${vo.dname}">
						<input type="hidden" name="checkDuty_d[${vs.index}]" value="${vo.duty_d}">
						<input type="hidden" name="checkDuty[${vs.index}]" value="${vo.duty}">
						<input type="hidden" name="checkLev_l[${vs.index}]" value="${vo.lev_l}">
						<input type="hidden" name="checkLev[${vs.index}]" value="${vo.lev}">
						
													
					</td>
					<td>${vo.dname }</td>
					<td>${vo.lev_l }</td>
					<td>${vo.duty_d }</td>
					<td>${vo.id_emp_v}</td>
					<td>${vo.name_v }</td>
					<td>${vo.reason }</td>
									
				</tr>
			</c:forEach>
				<tr>
					<td colspan="7" align="right">
						<c:if test="${duty eq '파트장' }">
						<input class="btn btn-danger"  type="button" value="요청철회" onclick=" ch_form('resignerNo');">								
						<input class="btn btn-primary" type="button" value="승인요청"onclick=" ch_form('resignerOk');">	
						</c:if>
					</td>
				</tr>
			</c:if> <%-- 있을 경우 종료 --%>
	</table>
			</form>
			<script type="text/javascript">
			function ch_form(type) {
				$('#resignerform').prop("action" ,type);
				$('#resignerform').submit();
			}
			</script>
					

		
</body>
</html>