<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 삭제</title>
  <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <style>
	th{background-color: #e6ecff}
  </style>
<script type="text/javascript">
$(function(){
	$("#name").focus();
});

// 폼
</script>
</head>
<body>
	<div align="center" id="list" style="padding-bottom: 25px;">
      <b>공지사항 삭제</b>
    </div>	
	<form action="noticedeleteOk" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="p" value="${p}">
		<input type="hidden" name="s" value="${s}">
		<input type="hidden" name="b" value="${b}">
		<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
		<input type="hidden" name="Id_emp" value="${list.id_emp }">
		<input type="hidden" name="dname" value="${list.depart_d }">
		<input type="hidden" name="idx" value="${idx }">
		
		<div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">
		
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
	              <th style="padding-top: 15px">제목</th>
	              <td><input type="text" name= "subject" id="subject" class="form-control" readonly="readonly" value="${dto.subject }" aria-describedby="basic-addon1"></td>
	            </tr>
			</thead>
			<tbody>
			<tr>
              <td colspan="2">
                <textarea class="form-control" rows="20" name="content" readonly="readonly">${dto.content }</textarea>
              </td>
			</tr>
			</tbody>
			<tr>
				<td  colspan="4" align="right" style="border: none;">
						<input type="submit" class="btn btn-primary"  value="삭제하기">				
						<input type="button" class="btn btn-secondary"  value="돌아가기" onclick="post_to_url('notice', {'p':'${p}','s':'${s}','b':'${b}'});">
					</td>  
			</tr>
		</table>
		</div>
		</div>	
	</form>

</body>
</html>