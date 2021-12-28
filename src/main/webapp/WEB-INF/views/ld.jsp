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
	f.action="updatelev";
	f.submit();
}
function b(formName){
	var f = document.getElementById(formName);
	f.action="deletelev";
	f.submit();
}
function c(formNameD){
	var f = document.getElementById(formNameD);
	f.action="updatedep";
	f.submit();
}
function d(formNameD){
	var f = document.getElementById(formNameD);
	f.action="deletedep";
	f.submit();
}
function e(formNameS){
	var f = document.getElementById(formNameS);
	f.action="updatest";
	f.submit();
}
function f(formNameS){
	var f = document.getElementById(formNameS);
	f.action="deletest";
	f.submit();
}
function g(formNameH){
	var f = document.getElementById(formNameH);
	f.action="updateh";
	f.submit();
}
function h(formNameH){
	var f = document.getElementById(formNameH);
	f.action="deleteh";
	f.submit();
}
function i(formNameDu){
	var f = document.getElementById(formNameDu);
	f.action="updatedu";
	f.submit();
}
function j(formNameDu){
	var f = document.getElementById(formNameDu);
	f.action="deletedu";
	f.submit();
}

</script>
<title>추가하는 곳</title>
</head>
<body>
	<form action="insertldOk">
		<table>
			<tr>
				<td>
					직급<input type="text" name="lev" id="lev">
					<input type="submit" value="추가"> 
				</td>
			</tr>
		</table>
	</form>
	<hr>	
	<c:if test="${empty Llist }">
		등록된정보가 없습니다.
	</c:if>
	
	<c:if test="${not empty Llist }">
	
		<c:forEach var="vo" items="${Llist }" varStatus="vs">
			<form name="formName${vs.count }" id="formName${vs.count }" method="post">
				<input  type="text"  id="lev" name="lev" value="${vo.lev}" >
				<input type="hidden" id="idx" name="idx" value="${vo.idx}">
				<input type="button" value="수정" onclick="a('formName${vs.count}');">
				<input type="button" value="삭제" onclick="b('formName${vs.count}');">
				<br>
			</form>
		</c:forEach>
	</c:if>
	<hr>
	
	
		<form action="insertdutyOk">
		<table>
			<tr>
				<td>
					직책<input type="text" name="duty" id="duty">
					<input type="submit" value="추가"> 
				</td>
			</tr>
		</table>
	</form>
	<hr>	
	<c:if test="${empty Dulist }">
		등록된정보가 없습니다.
	</c:if>
	
	<c:if test="${not empty Dulist }">
	
		<c:forEach var="vo" items="${Dulist }" varStatus="vs">
			<form name="formNameDu${vs.count }" id="formNameDu${vs.count }" method="post">
				<input  type="text"  id="duty" name="duty" value="${vo.duty}" >
				<input type="hidden" id="idx" name="idx" value="${vo.idx}">
				<input type="button" value="수정" onclick="i('formNameDu${vs.count}');">
				<input type="button" value="삭제" onclick="j('formNameDu${vs.count}');">
				<br>
			</form>
		</c:forEach>
	</c:if>	


<hr>	
		<form action="insertdeptOk">
		<table>
			<tr>
				<td>
					부서<input type="text" name="depart" id="depart">
					<input type="submit" value="추가"> 
				</td>
			</tr>
		</table>
	</form>
	<hr>	
	<c:if test="${empty Dlist }">
		등록된정보가 없습니다.
	</c:if>
	
	<c:if test="${not empty Dlist }">
	
		<c:forEach var="vo" items="${Dlist }" varStatus="vs">
			<form name="formNameD${vs.count }" id="formNameD${vs.count }" method="post">
				<input  type="text"  id="depart" name="depart" value="${vo.depart}" >
				<input type="hidden" id="idx" name="idx" value="${vo.idx}">
				<input type="button" value="수정" onclick="c('formNameD${vs.count}');">
				<input type="button" value="삭제" onclick="d('formNameD${vs.count}');">
				<br>
			</form>
		</c:forEach>
	</c:if>
		<hr>	
	
		<form action="insertstateOk">
		<table>
			<tr>
				<td>
					상태<input type="text" name="state" id="state">
					<input type="submit" value="추가"> 
				</td>
			</tr>
		</table>
	</form>
	<hr>	
	<c:if test="${empty Slist }">
		등록된정보가 없습니다.
	</c:if>
	
	<c:if test="${not empty Slist }">
	
		<c:forEach var="vo" items="${Slist }" varStatus="vs">
			<form name="formNameS${vs.count }" id="formNameS${vs.count }" method="post">
				<input  type="text"  id="state" name="state" value="${vo.state}" >
				<input type="hidden" id="idx" name="idx" value="${vo.idx}">
				<input type="button" value="수정" onclick="e('formNameS${vs.count}');">
				<input type="button" value="삭제" onclick="f('formNameS${vs.count}');">
				<br>
			</form>
		</c:forEach>
	</c:if>
		<hr>	
	
		<form action="inserthirestateOk">
		<table>
			<tr>
				<td>
					입사형태<input type="text" name="hirestate" id="hirestate">
					<input type="submit" value="추가"> 
				</td>
			</tr>
		</table>
	</form>
	<hr>	
	<c:if test="${empty Hlist }">
		등록된정보가 없습니다.
	</c:if>
	
	<c:if test="${not empty Hlist }">
	
		<c:forEach var="vo" items="${Hlist }" varStatus="vs">
			<form name="formNameH${vs.count }" id="formNameH${vs.count }" method="post">
				<input  type="text"  id="hirestate" name="hirestate" value="${vo.hirestate}" >
				<input type="hidden" id="idx" name="idx" value="${vo.idx}">
				<input type="button" value="수정" onclick="g('formNameH${vs.count}');">
				<input type="button" value="삭제" onclick="h('formNameH${vs.count}');">
				<br>
			</form>
		</c:forEach>
	</c:if>	
	
	
	
	
</body>
</html>