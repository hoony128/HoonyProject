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
<title>비정기승진인사 승인</title>
</head>
<body>
	<form action="resigneapproval" method="post">
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
	
	
	
	
		<form id="resignerform" method="post">				
		<table class="table">
	
			<tr style="background-color: #e6ecff" align="center">
				<th width="5%"> 
				<input type="checkbox"  id="checkAll" onclick="checkAllFunction();" checked="checked" >
				</th>
				<th> 이름 </th>
				<th> 사번</th>
				<th> 부서</th>
				<th> 직책</th>
				<th> 현재 직급</th>
				<th> 진급 후 직급</th> 
	
			</tr>
		
		<c:if test="${empty slist }">
			<tr>
				<td colspan="7" align="center">
				 	진급대상이 존재하지 않습니다.
				</td>
			</tr>
		</c:if>
		
		
		<c:if test="${not empty slist }">
		<c:forEach var="vo" items="${slist }" varStatus="vs">
			<tr align="center">
				<td>
	
					<input type="hidden" name="checkList[]" value="${vs.index}">
					<input type="checkbox" id="wwcheck${vs.index}" name="check${vs.index}" checked="checked" value="Y">
					<input type="hidden" name="checkId[${vs.index}]" value="${vo.id_emp}">
					<input type="hidden" name="checkLev[${vs.index}]" value="${vo.lev_after}">
					<input type="hidden" name="checkDep[${vs.index}]" value="${vo.depart}">
					<input type="hidden" name="checkState[${vs.index}]" value="${vo.state}">
					<input type="hidden" name="checkDuty[${vs.index}]" value="${vo.duty_d}">
					<input type="hidden" name="checkLevAfter[${vs.index}]" value="${vo.duty_d}">
					<input type="hidden" name="checknowlev[${vs.index}]" value="${vo.nowlev}">
				 </td>
	
				<td>${vo.name }</td>
				<td>${vo.id_emp}</td>
				<td>${vo.dname }</td>
				<td>${vo.duty }</td>
				<td>${vo.lev_now }</td>
				<td>${vo.lev }</td>
			</tr>
		</c:forEach>
		
			<tr>
				<td colspan="7" align="right" style="border: none;">
					<input class="btn btn-primary" type="button" value="승인" onclick="ch_form('specialpromotionapprovalOk');">
					<input class="btn btn-danger" type="button" value="거절" onclick="ch_form('specialpromotionapprovalNo');">
				</td>							
			<tr>
		</c:if>
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