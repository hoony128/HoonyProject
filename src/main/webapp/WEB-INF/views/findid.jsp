<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">

	// 폼유효성검사하기
	function formCheckE() {
		if(! emailCheck($("#email"))) {
			return false // 사용자아이디가이메일형식인지리를판단.
			}
			return true;
		}

	//이메일형식체크!
	function emailCheck(obj) {
	var email = obj.val();
	if(!validateEmail(email)){
	//이메일형식이알파벳+숫자@알파벳+숫자.알파벳+숫자형식이아닐경우
	alert("이메일형식이올바르지않습니다.");
	$("#email").val("");
	$("#email").focus();
	return false;
	}
	return true;
	}
	
	// 이메일검증함수
	function validateEmail(email) {
	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
	return re.test(email);
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
			<div class="card-header">전화번호로 찾기</div>
			<div class="card-body">
				<form action="findidresult" onsubmit="return formCheckP();" method="post">
					<div class="form-row">
						이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 &nbsp;&nbsp; <div style="width: 50%"> <input class="form-control" width="50%" type="text" name="name" required="required"></div><br>
					</div>	
					<br>
		         <div class="form-row">		 	
					전화번호 &nbsp;&nbsp;
					<input class="form-control" type="text" onkeypress="onlyNumber(this);" name="first_phone" id="first_phone"  style="width:90px; " required="required">
					&nbsp;&nbsp;<input class="form-control" onkeypress="onlyNumber(this);" type="text" name="mid_phone" id="mid_phone"  style="width:90px;"  required="required">
					&nbsp;&nbsp;<input class="form-control" onkeypress="onlyNumber(this);" type="text" name="last_phone" id="last_phone"  style="width:90px;" required="required">
				</div><br>
						<div class="form-label-group" >
							<div align="center"><input type="submit" style="align-content: center;" class="btn btn-primary" value="아이디 찾기"></div>
		
		</div>
		</form>
	</div>
</div>
</div>


	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">메일로 찾기</div>
			<div class="card-body">
				<form action="findidresult" onsubmit="return formCheckE();" method="post">
					<div class="form-row">
						이 &nbsp;&nbsp;&nbsp;름 &nbsp;&nbsp; <div style="width: 50%"> <input class="form-control" name="name" width="50%" type="text"></div><br>
					</div>	
					<br>
					<div class="form-row">
						이메일 &nbsp;&nbsp; <div style="width: 70%"> <input class="form-control" name="email" id="email"  type="text"></div><br>
					</div>	
						<div class="form-label-group" >
						<br>
							<div align="center"><input type="submit" class="btn btn-primary" value="아이디 찾기"></div>

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