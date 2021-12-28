<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({
		    dateFormat: 'yy/mm/dd'
		  });
	});
	// 폼 유효성 검사하기

	function formCheck(){

		if($("#useridMessage").text()==="사용 불가능"){
			alert("사용할 수 없는 사번입니다.\n다시 확인해주시기 바랍니다.");
			return false;
		}
		// 비번이 같은지 판단
		if($("#password").val()!==$("#password2").val()){
			alert("비밀번호가 일치하지 않습니다.\n다시 확인해주시기 바랍니다.");
			$("#password2").val("");
			$("#password2").focus();
			return false;
		}
		return true;
	}
	// 아이디 중복 확인하기
	function userIdCheck(){
		
		// 사용자 아이디가 ?자 이상일때만 서버에서 중복확인을 한다.
		var value = $("#userId").val();
		if(value.length>=5){
			// 여기에서 AJax를 호출한다.
			$.ajax({
					url:"useridCount",
					data:"Id_emp="+value,
					success : function(data){
						if(data==='0' || data==0){
							$("#useridMessage").html("사용 가능").css('color','blue');
						}else{
							$("#useridMessage").html("사용 불가능").css('color','red');							
						}
					} //success
				}); // ajax
		}else{
			$("#useridMessage").html("");	
		}
	}
	
	function onlyNumber(obj){

		if(event.keyCode >= 48 && event.keyCode<=57){
			return true;
		}else{
			event.returnValue=false;
		}
		
		}
</script>
</head>
<body>
	<div class="card-header">사원등록</div>
    	<hr>   
	<form action="signInOk" method="post" onsubmit="return formCheck();" >
	
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">사번</div> 
 					<input class="form-control" type="text" name="Id_emp" id="userId" onkeypress="onlyNumber(this)" style="width:280px;" onkeyup="userIdCheck()"  required="required">
					<span id="useridMessage"></span>
					<input type="hidden" name="prevUrl" value="${prevUrl }">
 			</div>
 		</div>
		<div class="dropdown-divider"></div> 			
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">비밀번호</div> 
					<input class="form-control" type="password" name="password" id="password" style="width:280px;" required="required">
 			</div>
 		</div>
 		<div class="dropdown-divider"></div>
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">비밀번호확인</div> 
					<input class="form-control" type="password" name="password2" id="password2" style="width:280px;" required="required">
 			</div>
 		</div>
 		<div class="dropdown-divider"></div>
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">이름</div> 
				<input class="form-control" type="text" name="name" id="name" style="width:280px;" required="required"> 			
			</div>
 		</div>
 		<div class="dropdown-divider"></div>
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">부서</div> 
					<select class="form-control" name="depart" style="width: 140px;" required="required" >
						<c:forEach var="v" items="${dlist }">
						<option value="${v.idx}" >${v.depart}</option>
						</c:forEach>
					</select>
			</div>
 		</div>
 		<div class="dropdown-divider"></div>
		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">입사형태</div> 
					<select class="form-control" name="hirestate" style="width: 140px;" required="required" >
						<c:forEach var="Y" items="${hlist }">
						<option value="${Y.idx}" >${Y.hirestate}</option>
						</c:forEach>
					</select>
			</div>
 		</div>
 		<div class="dropdown-divider"></div>
 		<div class="form-group">
            <div class="form-row">
 				<div style="width: 23%;" align="center">고용일</div> 
				<input class="form-control" type="text" style="width: 280px;" name="hiredate" id="datepicker1" required="required"> 			
			</div>
 		</div>
 		<div class="dropdown-divider"></div>
 		<div class="form-group">
            <div class="form-row">		 	
				<div style="width: 23%;" align="center"></div>
					<input class="btn btn-primary" type="submit" value="사원등록">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-secondary"  value="다 시 쓰 기">				
			</div>
		</div>	
		<div class="dropdown-divider"></div>
	</form>


	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert("이미 존재하는 사번입니다.");
		</script>
	</c:if>


</body>
</html>