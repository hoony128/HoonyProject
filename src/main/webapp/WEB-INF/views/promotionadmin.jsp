<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>평가자 설정</title>

</head>
<body>

	<form action="promotionadminOk" method="post" >
		<div id="list" style="padding-bottom: 50px;">
      <b>승진평가자 평가 권한 설정</b><br>
  	  
      
    </div>
	<table  class="table" style="width: 50%;">
		<tr style="background-color: #e6ecff">
			<th width="25%"> 선택</th>
			<th> 부서 </th>
			<th> 직책</th>
			<th> 직급</th>
			<th> 이름</th>
		</tr>

	<c:forEach var="vo" items="${alist }" varStatus="vs">
		<tr>
		<td>
		<input type="hidden" name="checkList[]" value="${vs.index}">
		<input type="hidden" name="checkId[${vs.index}]" value="${vo.id_emp}">
			<input type="checkbox"  name="check${vs.index}" <c:if test="${vo.temp1 eq'평가자' }">checked="checked"</c:if> value="Y">
		 </td>
		<!-- 
		<c:if test="${vo.temp1 eq'평가자' }">
		<input type="checkbox" name="check" checked="checked" value="${vo.id_emp}">
		</c:if>
		<c:if test="${vo.temp1 ne'평가자' }">
		<input type="checkbox" name="check"  value="${vo.id_emp}">
		</c:if>
		 -->
		<td>${ vo.depart_d}</td>
		<td>${ vo.duty_du}</td>
		<td>${ vo.lev_l }</td>
		<td>${ vo.name}</td>

		</tr>
	</c:forEach>
	
		<tr>
			<td colspan="5" align="right" style="border: none;"><input class="btn btn-primary" type="submit" value="권한부여"></td>
		<tr>
	</table>
	</form>
</body>
</html>