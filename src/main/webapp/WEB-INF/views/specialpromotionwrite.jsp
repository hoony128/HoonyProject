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
		if($('#lev_after').val()-$('#nowlev').val()  >2){
			alert("2직급 초과 특진은 불가능합니다.");
			return false;
		}
		if($('#lev_after').val()-$('#nowlev').val() <= 0){
			alert("강등할 수 있는 권한이 없습니다.");
			return false;
		}
		if($('#reason').val().trim().length<=50){
			alert('최소 50자는 입력해야합니다.');
			return false;
		}
		
		
		return true;


}


</script>
  <style>
	th{background-color: #e6ecff}
  </style>
<title> 특진 신청서 작성</title>
</head>
<body>
<div align="center" id="list" style="padding-bottom: 25px;">
      <b>${name}의 특진 신청서</b>
    </div>
<form  name="formName" action="specialpromotionOk" id="formName" method="post" onsubmit="return formCheck();">


<input type="hidden"  name="dname"  value="${dname }">
<input type="hidden"  name="lev_now" value="${lev_now }">
<input type="hidden"  name="duty" value="${duty }">
<input type="hidden"  name="name" value="${name }">
<input type="hidden"  name="Id_emp" value="${Id_emp}">
<input type="hidden" name="url" value="${prevUrl }">
<input type="hidden" id="nowlev"  name="nowlev" value="${lev}">
		
		 <div id="contentForm">
			
        <div class="input-group input-group-sm" role="group" aria-label="...">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th style="background-color: #e6ecff" width="30%">소속부서</th>
              <td width="70%">${dname }</td>
            </tr>
            <tr>
              <th width="30%">현직급</th>
              <td width="70%">${lev_now}</td>
            </tr>
            <tr>
              <th width="30%">직책</th>
              <td width="70%">${duty} </td>
            </tr>
            <tr>
              <th width="30%">특진사원명(사번) </th>
              <td width="70%">${name}(${Id_emp})</td>
            </tr>

            <tr>
              <th width="30%" >특진 직급 </th>
              	<td>
		            <select id="lev_after" name="lev_after">
						<c:forEach var="vo"  items="${llist}">
							<option value="${vo.idx }">${vo.lev}</option>
						</c:forEach>
					</select>
				</td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="2">
                등급선정이유:<textarea class="form-control" rows="20" name="reason" id="reason" required="required">${reason}</textarea>
              </td>
            </tr>
          </tbody>
          	<tr>
				<td  colspan="4" align="right" style="border: none;">
							<input type="submit" class="btn btn-danger" value="특진신청">
							<input type="button" class="btn btn-secondary" value="돌아가기" onclick="location.href='specialpromotion'">				
					</td>  
			</tr>
        </table>
      </div>
    </div>
</form>



<br>
	
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert("2직급을 초과하는 특진은 할 수 없습니다.");
		</script>
	</c:if>
	<c:if test="${not empty result and result==2 }">
		<script type="text/javascript">
			alert("현재직급보다 같거나 내릴 수는 없습니다.");
		</script>
	</c:if>

</body>
</html>