<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>로그인</title>

<!-- Bootstrap core CSS-->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="resources/css/sb-admin.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	


	// 폼유효성검사하기
	function formCheck() {

		if ($("#Id_emp").val().length == 0) {
			alert("사번을 입력하지않았습니다.");
			$("#Id_emp").focus();
			return false
		}
		// 숫자체크
		if (!numberCheck($("#userid"))) {
			alert("올바른 사번형식이 아닙니다.")
			return false // 사용자아이디가 숫자형식인지를 판단.
		}
		if ($("#password").val().length == 0) {
			alert("비밀번호를입력하지않았습니다.");
			$("#password").focus();
			return false
		}
		return true
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

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Login</div>
			<div class="card-body">
				<form action="loginOk" method="post" onsubmit="return formCheck();">
					<div class="form-group">
						<div class="form-label-group">
							사번<input type="text" id="Id_emp" name="Id_emp" class="form-control"  onkeypress="onlyNumber(this);"> 
								<input type="hidden" name="url" value="${prevUrl }">
						</div>
					</div>
					
					<div class="form-group">
						<div class="form-label-group">
							password<input type="password" id="password" name="password"
								class="form-control">
						</div>
					</div>

					<input type="submit" class="btn btn-primary btn-block"
						value="Login">
				</form>
				<div class="text-center">
					<a class="d-block small" href="/project/resources/findid">아이디찾기</a> 
					<a class="d-block small" href="/project/resources/findpw">비밀번호찾기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert('사원번호가 올바르지 않습니다.');
		</script>
	</c:if>
	<c:if test="${not empty result and result==2 }">
		<script type="text/javascript">
			alert('비밀번호가 올바르지 않습니다.');
		</script>
	</c:if>
	<c:if test="${not empty result and result==3 }">
		<script type="text/javascript">
			alert('권한이 없는 사원입니다.');
		</script>
	</c:if>

</body>

</html>
