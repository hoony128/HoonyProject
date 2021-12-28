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

if($('#testscore1').val()==""){
	alert(' "업무능력"의 점수를 입력해주세요');
	return false;
}
if($('#testscore2').val()==""){
	alert('"소통능력"의 점수를 입력해주세요');
	return false;
}
if($('#testscore3').val()==""){
	alert('"리더십/팔로우십"의 점수를 입력해주세요');
	return false;
}
if($('#testscore4').val()==""){
	alert('"창의적사고"의 점수를 입력해주세요');
	return false;
}

if($('#reason').val().trim().length<=100){
	alert('최소 100자는 입력해야합니다.');
	return false;
}
return true;

</script>
  <style>
	th{background-color: #e6ecff; width: 30%}
  </style>
<title> 승진평가 심사표</title>
</head>
<body>
<div  id="list" >
      <b>정기진급 평가서</b>
      
    </div>
    
	<form  name="formName" action="TestingSubmit"  id="formName" method="post" onsubmit="return formCheck();">
		<input type="hidden" name="Id_emp_p" value="${vo.id_emp }">
		<input type="hidden" name="name_p" value="${vo.name }">
		
		<input type="hidden"  name="Id_emp_v" value="${Id_emp_v}"><br>
		<input type="hidden"  name="dname" value="${dname }"><br>
		<input type="hidden"  name="name_v" value="${name_v }"><br>
		<input type="hidden"  name="duty_d" value="${duty_d }"><br>
		<input type="hidden"  name="lev_l" value="${lev_l }"><br>
		
		 
		 <div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">
		-인적사항<br>
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th style="background-color: #e6ecff" width="30%">소속부서</th>
              <td width="70%">${dname }</td>
            </tr>
            <tr>
              <th width="30%">직급</th>
              <td width="70%">${lev_l }</td>
            </tr>
            <tr>
              <th width="30%">직책</th>
              <td width="70%">${duty_d} </td>
            </tr>
            <tr>
              <th width="30%">평가대상 </th>
              <td width="70%">${name_v }(${Id_emp_v })</td>
            </tr>
			</thead>
		</table>
		-평가항목<br>
		<table class="table table-striped table-bordered">
	
            <tr>
              <th width="30%" >업무능력(이해도+기여도 고려)</th>
	              <td>
		              <select name="testscore1" id="testscore1" required="required" style="width: 40px; height: 20px;" >
							<option value="">선택</option>
							<option value="30">A</option> 
							<option value="25">B</option> 
							<option value="20">C</option> 
							<option value="10">D</option>  
							<option value="0">F</option> 
					</select><br>
				</td>
            </tr>
            <tr>
              <th width="30%" >소통능력(팀원간 의사소통 중점)</th>
	              <td>
		              <select name="testscore2" id="testscore2" required="required" style="width: 40px; height: 20px;" >
							<option value="">선택</option>
							<option value="20">A</option> 
							<option value="15">B</option> 
							<option value="10">C</option> 
							<option value="5">D</option>  
							<option value="0">F</option> 
					</select><br>
				</td>
            </tr>
            <tr>
              <th width="30%" >리더십/팔로우십</th>
	              <td>
		              <select name="testscore3" id="testscore3" required="required" style="width: 40px; height: 20px;" >
							<option value="">선택</option>
							<option value="20">A</option> 
							<option value="15">B</option> 
							<option value="10">C</option> 
							<option value="5">D</option>  
							<option value="0">F</option> 
					</select><br>
				</td>
            </tr>
            <tr>
              <th width="30%" >창의적사고(근무내에 창의적으로 일을 처리한 결과중심)</th>
	              <td>
		              <select name="testscore4" id="testscore4" required="required" style="width: 40px; height: 20px;" >
						<option value="">선택</option>
						<option value="30">A</option> 
						<option value="25">B</option> 
						<option value="20">C</option> 
						<option value="10">D</option>  
						<option value="0">F</option> 
					</select><br>
				</td>
            </tr>
  
  		 <tbody>
            <tr>
              <td colspan="2">
                점수선정이유:<textarea class="form-control" rows="20" name="reason" id="reason" required="required">${reason}</textarea>
              </td>
            </tr>
          </tbody>
          	<tr>
				<td  colspan="4" align="right" style="border: none;">
							
							<input type="submit"  class="btn btn-primary" value="평가제출" >
							<input type="button" class="btn btn-secondary" value="돌아가기" onclick="location.href='promotionTest'">				
					</td>  
			</tr>
        </table>
      </div>
    </div>
</form>



</body>
</html>