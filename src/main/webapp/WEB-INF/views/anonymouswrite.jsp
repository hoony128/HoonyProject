<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>익명게시판 작성</title>
  <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">

  <style>
	th{background-color: #e6ecff}
  </style>
</head>
<body>
	<div align="center" id="list" style="padding-bottom: 25px;">
      <b>익명게시판 작성</b>
    </div>
	<form action="anonymouswriteOk" method="post" >
		<input type="hidden" name="p" value="${p}">
		<input type="hidden" name="s" value="${s}">
		<input type="hidden" name="b" value="${b}">
		<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
		<input type="hidden" name="Id_emp" value="${list.id_emp }">
	
		 <div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">

        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th style="background-color: #e6ecff" width="30%">아이디</th>
              <td width="70%"><input type="text" class="form-control" name= "id" id="subject" size="70" required="required"></td>
            </tr>
            <tr>
              <th width="30%">비밀번호</th>
              <td width="70%"><input type="password" class="form-control" name= "password" id="subject" size="70" required="required"></td>
            </tr>
            <tr>
              <th style="padding-top: 15px">제목</th>
              <td><input type="text" name= "subject" id="subject" class="form-control" aria-describedby="basic-addon1"></td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="2">
                <textarea class="form-control" rows="20" name="content" required="required"></textarea>
              </td>
            </tr>
          </tbody>
          	<tr>
				<td  colspan="4" align="right" style="border: none;">
						<input type="submit" class="btn btn-primary" value="등록">						
						<input type="button" class="btn btn-secondary" value="목록" onclick="post_to_url('anonymous', {'p':'${p}','s':'${s}','b':'${b}'});">
					</td>  
			</tr>
        </table>
      </div>
    </div>
	</form>

</body>
</html>