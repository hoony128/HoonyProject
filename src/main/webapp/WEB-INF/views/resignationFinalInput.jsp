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

function formCheck(){
	if($('#reason').val().trim().length<=10){
		alert('최소 10자는 입력해야합니다.');
		return false;
	}

	
	return true;
	
}

</script>
<title>퇴사요청서 작성</title>
</head>
<body>


<div align="center" id="list" style="padding-bottom: 25px;">
      <b>${dname}부서 ${name_v}님의 퇴사요청</b>
    </div>
	<form  name="formName" action="resignationSubmit"  id="formName" method="post" onsubmit="return formCheck();">
		<input type="hidden" name="name_p" value="${name_p }">
		<input type="hidden" name="Id_emp_p" value="${Id_emp_p }">
		<input type="hidden"  name="name_v" value="${name_v }">
		<input type="hidden"  name="Id_emp_v" value="${Id_emp_v}">
		<input type="hidden" name="duty_d" value="${duty_d }">
		<input type="hidden"  name="lev_l" value="${lev_l }">
		<input type="hidden"  name="dname" value="${dname }">
		<input type="hidden" value="${duty }" name="duty">
		<input type="hidden" value="${lev }" name="lev">
		 <div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">

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
              <th width="30%">퇴사신청인 </th>
              <td width="70%">${name_v }(${Id_emp_v })</td>
            </tr>

          </thead>
          <tbody>
            <tr>
              <td colspan="2">
                퇴사이유:<textarea class="form-control" rows="20" name="reason" id="reason" required="required" style="resize: none;" >${reason}</textarea>
              </td>
            </tr>
          </tbody>
          	<tr>
				<td  colspan="4" align="right" style="border: none;">
							<input type="submit" class="btn btn-primary" value="퇴사요청"  >
							<input type="button" class="btn btn-secondary" value="돌아가기" onclick="location.href='resignation'">				
					</td>  
			</tr>
        </table>
      </div>
    </div>
</form>

</body>
</html>