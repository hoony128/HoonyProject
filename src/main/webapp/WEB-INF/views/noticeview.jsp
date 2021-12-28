<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function deleteCheck(idx) {
	if(confirm('삭제 하시겠습니까?')){
		post_to_url('noticedeleteOk',{'idx':'${vo.idx}', 'Id_emp':'${vo.id_emp}','ip':'${ip}'})
	}
}
</script>
<title>공지사항 - ${vo.subject}</title>
</head>
<body>	

	
			<div align="left" style="font-size: 30px;">[공지사항] ${vo.subject }</div>
			<br>
			<div align="left" style="font-size: 10px;">작성부서:${vo.dname }</div>
			<div align="left" style="font-size: 10px; color: silver;"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd hh:mm:ss"/> | 조회${vo.hit }</div>
			<hr>
			<c:set var="c" value="${vo.content }"/>			
			<c:url value="helpdeskdown" var="url">
				<c:param name="o" value="${flist.ofile }"/>
				<c:param name="s" value="${flist.sfile }"/>
			</c:url>			
			<a href="${url }">${flist.ofile }</a>	
					<br>
					<br>${c }
			<hr>					
			<div align="right" style="size: 15px;">
				<c:if test="${vo.id_emp eq list.id_emp }">
					<input type="button" value="수정하기"  class="btn btn-secondary" onclick="post_to_url('noticeupdate', {'idx':'${vo.idx }','m':'0','p':'${p }','s':'${s }','b':'${b }'});">					
					<input type="button" value="삭제하기"  class="btn btn-secondary" onclick="deleteCheck(${vo.idx});">					
				</c:if>
					<input type="button" value="돌아가기"  class="btn btn-secondary" onclick="post_to_url('notice', {'p':'${p }','s':'${s }','b':'${b }'});">										
			</div>

</body>
</html>