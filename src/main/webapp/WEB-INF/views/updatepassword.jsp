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

<title>비밀번호 찾기</title>

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
<script type="text/javascript">

	// 폼유효성검사하기
	function formCheck() {
		if($("#password").val()!==$("#password2").val()){
			alert("비밀번호가 일치하지 않습니다.\n다시 확인해주시기 바랍니다.");
			
			$("#password").val("");
			$("#password2").val("");
			$("#password").focus();
			return false;
		}
		
		if($("#password").val()==$("#rpw").val()){
			alert("이전의 비밀번호와는 같을 수 없습니다.");
			
			$("#password").val("");
			$("#password2").val("");
			$("#password").focus();
			return false;
		}
		
			return true;
		}


</script>
</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">비밀번호 변경하기</div>
			<div class="card-body">
				<form action="updatepasswordOk" onsubmit="return formCheck();" method="post">
					<input type="hidden" value="${Id_emp}" name="Id_emp">
					<input type="hidden" id="rpw" value="${rpw }">
					
					<div class="form-row">
						변경 패스워드 &nbsp;&nbsp; <div style="width: 50%"><input type="password" id="password" name="password" class="form-control"  onkeypress="onlyNumber(this);"></div><br>
					</div>	
					<br>
					<div class="form-row">
						패스워드 확인 &nbsp;&nbsp; <div style="width: 50%"> <input class="form-control" id="password2" name="password2" width="50%" type="password"></div><br>
					</div>	
					<br>
						<div class="form-label-group" >
						<br>
							<div align="center"><input type="submit" class="btn btn-primary" value="변경하기"></div>

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