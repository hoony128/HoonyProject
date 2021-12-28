<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function checkAllFunction() {
    $("input[id^='wwcheck']").prop("checked",$("#checkAll").is(":checked"));
 }
</script>
<title>정기승진인사 승인</title>

</head>
<body>
	<form action="promotionapproval" method="post">
		<select name="depart2">
			<option value="">-전체-</option>
				<c:forEach var="vo" items="${dlist }">
					<option value="${vo.idx }">
						${vo.depart }
					</option>
				</c:forEach>
		</select>
		<input type="submit" class="btn btn-secondary" value="찾기" >
	</form>	
	<hr>	
	<form action="promtionapprovalOk" method="post">
	<table class="table">

		<tr style="background-color: #e6ecff" align="center">
			<th width="15%" >
			<input type="checkbox"  id="checkAll" onclick="checkAllFunction();" checked="checked" >
			</th>
			<th> 이름 </th>
			<th> 사번</th>
			<th> 부서</th>
			<th> 직급</th>
			<th> 직책</th>
			<th> 점수</th>
		</tr>
	
	<c:if test="${empty alist }">
		<tr>
			<td colspan="7" align="center">
			 	진급대상이 존재하지 않습니다.
			</td>
		</tr>
	</c:if>
	
	
	<c:if test="${not empty alist }">
	<c:forEach var="vo" items="${alist}" varStatus="vs">
		<tr>
			<td>
				<input type="hidden" name="checkList[]" value="${vs.index}">
				<input type="checkbox" id="wwcheck${vs.index}" name="check${vs.index}" checked="checked" value="Y">
				<input type="hidden" name="checkId[${vs.index}]" value="${vo.id_emp}">
				<input type="hidden" name="checkLev[${vs.index}]" value="${vo.lev}">
				<input type="hidden" name="checkDep[${vs.index}]" value="${vo.depart}">
				<input type="hidden" name="checkState[${vs.index}]" value="${vo.state}">
				<input type="hidden" name="checkDuty[${vs.index}]" value="${vo.duty}">
			 </td>

			<td>${vo.name }</td>
			<td>${vo.id_emp}</td>
			<td>${vo.dname }</td>
			<td>${vo.lev_l }</td>
			<td>${vo.duty_d }</td>
			<td>${vo.finalscore }</td>
		</tr>
	</c:forEach>
	
		<tr>
			<td colspan="7" align="right" style="border: none;"><input  type="submit" class="btn btn-primary" value="권한부여"></td>
		<tr>
	</c:if>
	</table>
	</form>


</body>
</html>