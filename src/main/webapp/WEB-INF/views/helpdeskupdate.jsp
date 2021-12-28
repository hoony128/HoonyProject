<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무도움 수정</title>
<script type="text/javascript">
function deletefile() {
	$("#beforefile").val("");		
	$("#ori").hide(); 
	$("#deleteb").hide(); 
}
</script>
</head>
<body>
	<div align="center" id="list" style="padding-bottom: 25px;">
      <b>업무도움 수정</b>
    </div>
	<form action="helpdeskupdateOk" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="p" value="${p}">
		<input type="hidden" name="s" value="${s}">
		<input type="hidden" name="b" value="${b}">
		<input type="hidden" name="idx" value="${idx}">		
		<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
		<input type="hidden" name="Id_emp" value="${list.id_emp }"> 
		<c:if test="${not empty flist}">
		<input type="hidden" name="ofile" value="${flist.ofile}">
		<input type="hidden" name="sfile" value="${flist.sfile}">
		<input type="hidden" name="beforefile" id="beforefile" readonly="readonly" width="280px;" style="color: silver;" value="1">
		
		</c:if>
		
		
		
		<div id="contentForm">

        <div class="input-group input-group-sm" role="group" aria-label="...">
		
		<table class="table table-striped table-bordered">
            
            <tr>
              <th style="padding-top: 15px">제목</th>
              <td><input type="text" name= "subject" id="subject" required="required" class="form-control" value="${vo.subject}" ></td>
            </tr>
            <tr>
              <th style="padding-top: 15px">파일첨부</th>
              <td>
              	<input type="file" name="files[0].file" class="form-control" >
              	<c:if test="${not empty flist }">
              	<div id="ori" style="float: left;">${flist.ofile }</div>
              	<input type="button" class="btn btn-primary" id="deleteb" value="삭제" onclick="deletefile()">
              	</c:if>
              </td>
            </tr>
	            <tr>
	              <td colspan="2">
	                <textarea class="form-control" rows="20" name="content" required="required" >${vo.content}</textarea>
	              </td>
	            </tr>

			<tr>
				<td  colspan="4" align="right" style="border: none;">
						<input type="submit" class="btn btn-primary"  value="수정">
						<input type="button" class="btn btn-secondary" value="목록" onclick="post_to_url('helpdesk', {'p':'${p}','s':'${s}','b':'${b}'});">
				</td>  
			</tr>
		</table>	
		</div>
		</div>
	</form>

</body>
</html>