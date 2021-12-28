<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>아이디찾기</title>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</head>

<body class="bg-dark">
	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">조회결과</div>
			<div class="card-body">
				<form action="login" onsubmit="return formCheckE();" method="post">
						<div>	
							<c:if test="${id eq -1}">
								조회하신 정보의 사번은 존재하지 않습니다.
							</c:if>
							<c:if test="${id ne -1}">
								조회하신 사번은 <br>
								${id }<br>
								입니다.
							</c:if>
						</div>	
						<div class="form-label-group" >
						<br>
						<div align="center">
							<c:if test="${id ne -1}">
								<input type="button" class="btn btn-primary" value="로그인하기" onclick="location.href='/project/'">
							</c:if>	
							<c:if test="${id eq -1}">
								<input type="button" class="btn btn-primary" value="뒤로가기" onclick="location.href='/project/resources/findid'">
								<input type="button" class="btn btn-primary" value="비밀번호 찾기" onclick="location.href='/project/resources/findpw'">
								<input type="button" class="btn btn-primary" value="로그인하기" onclick="location.href='/project/'">
							</c:if>	
						</div>

		</div>
		</form>
	</div>
</div>
</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>


</body>

</html>
