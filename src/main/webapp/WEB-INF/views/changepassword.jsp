<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
$(function(){
	$("#datepicker1").datepicker({
	    dateFormat: 'yy/mm/dd'
	  });
});
// 폼 유효성 검사하기

function formCheck(){
	// 현재 비밀번호를 틀리게 입력할 경우!~
	if($("#realpassword").val()!==$("#nowpassword").val()){
		alert("비밀번호를 잘못 입력 하였습니다.");
		$("#nowpassword").val("");
		$("#password").val("");
		$("#password2").val("");
		$("#nowpassword").focus();
		return false;
	}
	// 비번이 같은지 판단
	if($("#password").val()!==$("#password2").val()){
		alert("비밀번호가 일치하지 않습니다.\n다시 확인해주시기 바랍니다.");
		$("#nowpassword").val("");
		$("#password").val("");
		$("#password2").val("");
		$("#nowpassword").focus();
		return false;
	}
	if($("#password").val()==$("#nowpassword").val()){
		alert("동일한 비밀번호로는 변경이 불가합니다.");
		$("#nowpassword").val("");
		$("#password").val("");
		$("#password2").val("");
		$("#nowpassword").focus();
		return false;
	}
	return true;
}
</script>
<style>
	th{background-color: #e6ecff}
</style>
<title>비밀번호 변경~!</title>
</head>
<body>
	<form action="changepasswordOk" method="post" onsubmit="return formCheck();" >		
		<input type="hidden" name="Id_emp" value="${list.id_emp }">
		<input type="hidden" name="realpassword" id="realpassword" value="${list.password}">
		<div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="..." style="width: 50%">
		
		<table class="table table-striped table-bordered" >
            
            <tr >
              <th style="padding-top: 15px; width: 140px;">현재비밀번호 변경</th>
              <td style="width: 280px;"><input type="password" width="40%" name= "nowpassword" id="nowpassword" required="required" class="form-control" ></td>
            </tr>
            <tr >
              <th style="padding-top: 15px; width: 140px;">비밀번호 변경</th>
              <td style="width: 280px;"><input type="password" width="40%" name= "password" id="password" required="required" class="form-control" ></td>
            </tr>
            <tr>
              <th style="padding-top: 15px; width: 140px;">비밀번호 확인</th>
              <td style="width: 280px;"><input type="password" width="40%" name= "password2" id="password2" required="required" class="form-control" ></td>
            </tr>
		</table>
		<table class="table">
			<tr>	
            	<td  colspan="2" align="right">
					<input type="submit" class="btn btn-primary"  value="글쓰기">	
				</td>
			</tr>	 
		</table>
	
		</div>
		</div>
	</form>
</body>
</html>