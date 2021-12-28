<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>수정하기</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<!-- 다음 우편번호 API사용하기 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var fullAddr = ''; // 최종 주소 변수
	            var extraAddr = ''; // 조합형 주소 변수
	
	            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                fullAddr = data.roadAddress;
	
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                fullAddr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
	            document.getElementById('addr1').value = fullAddr;
	
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById('addr2').focus();
	        }
	    }).open();
	}
	function onlyNumber(obj){

		if(event.keyCode >= 48 && event.keyCode<=57){
			return true;
		}else{
			event.returnValue=false;
		}
		
		}
</script>
<!-- 다음 우편번호 API사용하기 -->
<script type="text/javascript">

$(function() {
	  $( "#datepicker1" ).datepicker({
	    dateFormat: 'yy/mm/dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    showMonthAfterYear: true,
	    changeMonth: true,
	    changeYear: true,
	    yearSuffix: '년'
	  });
	});
	
	function formCheck(){
		// 비번이 같은지 판단
		if($("#password").val()!= $("#password2").val()){
			alert("비밀번호가 일치하지 않습니다.\n다시 확인해주시기 바랍니다.");
			$("#password").val("");
			$("#password2").val("");
			$("#password").focus();
			return false;
		}
		if($("#password").val()!= $("#realpassword").val()){
			alert("비밀번호가 틀립니다..\n다시 확인해주시기 바랍니다.");
			$("#password").val("");
			$("#password2").val("");
			$("#password").focus();
			return false;
		}
		// 이메일체크
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
	return false
	}
	return true
	
	}
	// 이메일검증함수
	function validateEmail(email) {
	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
	return re.test(email);
	}
</script>	
</head>
<body class="bg-dark">
        <div class="card-header">정보수정</div>
    	<hr>    
	<form action="updateInfoOk" method="post" onsubmit="return formCheck();"  >
	
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">
		 				<div style="width: 23%;" align="center">부서</div> 
		 				<div><input class="form-control" type="text" name="Id_emp" id="Id_emp" style="width:280px; color: silver" required="required" value="${list.id_emp }" readonly="readonly"></div>
		 		</div>
		 	</div>
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<input class="form-control" type="hidden" name="prevUrl" value="${prevUrl }">
					<div style="width: 23%;" align="center">비밀번호</div>
					<input class="form-control" type="password" name="password" id="password" style="width:280px;" required="required" >
					<input type="hidden" id="realpassword" value="${list.password }">
				</div>
			</div>
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">비밀번호확인</div>
					<input class="form-control" type="password" name="password2" id="password2" style="width:280px;" required="required">					
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">이름</div>
					<input class="form-control"  type="text" name="name" id="name" value="${list.name }"  style="width:280px; color:silver;" required="required" readonly="readonly">
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">직급</div>
					<input class="form-control" type="text" name="lev" id="lev" value="${list.lev_l}" style="width: 140px; color: silver;" required="required" readonly="readonly">				
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">소속부서</div>
					<input class="form-control" type="text" name="depart" id="depart" value="${list.depart_d}" style="width: 140px; color: silver;" required="required" readonly="readonly">				
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">입사일</div>
					<input class="form-control" type="text" name="hiredate" id="datepicker" style="width:280px; color: silver;" value='<fmt:formatDate value="${list.hiredate }"/>' readonly="readonly"  required="required">
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">전화번호</div>
					<input class="form-control" type="text" onkeypress="onlyNumber(this)" name="first_phone" id="first_phone" value="${list.first_phone }" style="width:90px; " required="required">
					&nbsp;&nbsp;<input class="form-control" onkeypress="onlyNumber(this)" type="text" name="mid_phone" id="mid_phone" value="${list.mid_phone }" style="width:90px;"  required="required">
					&nbsp;&nbsp;<input class="form-control" onkeypress="onlyNumber(this)" type="text" name="last_phone" id="last_phone" value="${list.last_phone }" style="width:90px;" required="required">
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">주소</div>
					<input class="form-control" type="text" name="zipcode" id="zipcode" style="width:170px;" value="${list.zipcode }" readonly="readonly" required="required"  onclick="execDaumPostcode()">
					<input type="button" class="form-control" style="width: 170px;"  onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
				</div>	
				<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">
              	<div style="width: 23%;" align="center"></div>
					<input class="form-control" type="text" name="addr1" value="${list.addr1 }" id="addr1" style="width:280px;" readonly="readonly" required="required">
					&nbsp;&nbsp;<input class="form-control" type="text" name="addr2" value="${list.addr2 }" id="addr2" style="width:280px;" required="required">
				</div>
			</div>		
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">생일</div>
					<input class="form-control" type="text" name="birth" id="datepicker1" value='<fmt:formatDate value="${list.birth }" pattern="yyyy/MM/dd"/>'  style="width:280px;"  required="required" >				
				</div>
			</div>
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">E-mail</div>
					<input class="form-control" type="text" name="email" value="${list.email }" id="email" style="width:280px;"  required="required">
				</div>
			</div>	
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">은행</div>
					<input class="form-control" type="text" name="bank" value="${list.bank }" id="bakn"  style="width:140px;"  required="required">
				</div>
			</div>	
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center">계좌번호</div>
					<input class="form-control" type="text" onkeypress="onlyNumber(this)" name="account" value="${list.account }" id="account" style="width:140px;"  required="required">
				</div>
			</div>	
			<div class="form-group"><div class="dropdown-divider"></div>
              	<div class="form-row">		 	
					<div style="width: 23%;" align="center"></div>
					<input class="btn btn-primary" type="submit" value="수정하기">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-secondary"  value="다 시 쓰 기">				
					</div>
			</div>	
	</form>
	
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert("비밀번호가 올바르지 않습니다.");
		</script>
	</c:if>
	

</body>
</html>