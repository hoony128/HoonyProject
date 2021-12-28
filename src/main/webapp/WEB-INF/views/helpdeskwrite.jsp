<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무도움방 작성</title>
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
      <b>업무도움 작성</b>
    </div>
	<form action="helpdeskwriteOk" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="p" value="${p}">
		<input type="hidden" name="s" value="${s}">
		<input type="hidden" name="b" value="${b}">
		<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
		<input type="hidden" name="Id_emp" value="${list.id_emp }">
		<input type="hidden" name="dname" value="${list.depart_d }">
		<input type="hidden" name="lev" value="${list.lev_l }">
		<input type="hidden" name="name" value="${list.name }"> 
		
		<div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">
		
		<table class="table table-striped table-bordered">
            
            <tr>
              <th style="padding-top: 15px">제목</th>
              <td><input type="text" name= "subject" id="subject" required="required" class="form-control" ></td>
            </tr>
            <tr>
              <th style="padding-top: 15px">파일첨부</th>
              <td><input type="file" name="files[0].file" class="form-control" ></td>
            </tr>
	            <tr>
	              <td colspan="2">
	                <textarea class="form-control" rows="20" name="content" required="required"></textarea>
	              </td>
	            </tr>

			<tr>
				<td  colspan="4" align="right" style="border: none;">
						<input type="submit" class="btn btn-primary"  value="글쓰기">
						<input type="button" class="btn btn-secondary" value="목록" onclick="post_to_url('helpdesk', {'p':'${p}','s':'${s}','b':'${b}'});">
				</td>  
			</tr>
		</table>	
		</div>
		</div>
	</form>

</body>
</html>