<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무도움방 - ${vo.subject}</title>

</head>
<body>
			<div align="left" style="font-size: 30px;">[업무도움]-[${vo.dname}] ${vo.subject }</div>
			<br>
			<div align="left" style="font-size: 10px;">작성자:${vo.dname}_${vo.name}(${vo.lev })</div>
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
					<div align="right" style="size: 15px;">
					<c:if test="${vo.id_emp eq list.id_emp }">
					<input type="button" class="btn btn-secondary" value="수정" onclick="post_to_url('helpdeskupdate', {'idx':'${vo.idx }','m':'0','p':'${p }','s':'${s }','b':'${b }'});">					
					<input type="button" class="btn btn-secondary" value="삭제" onclick="post_to_url('helpdeskdelete', {'idx':'${vo.idx }','m':'0','p':'${p }','s':'${s }','b':'${b }'});">
					</c:if>					 
					<input type="button" class="btn btn-secondary" value="목록" onclick="post_to_url('helpdesk', {'p':'${p }','s':'${s }','b':'${b }'});">								
					</div>
			<hr>
			
			
			<hr>
			
			<div style="float: left;">전체 리플</div>  <div style="color: red; float: left;">${vo.commentcount}</div><div style="float: left;">개</div>
			<%-- 댓글 목록 --%>
 		<br>
 		<div class="dropdown-divider"></div>			
		<c:if test="${not empty vo.commentlist }">
			<c:forEach var="com" items="${vo.commentlist }" varStatus="vs">
					<form action="helpcommentDeleteOk" method="post">
					<div style=" float: left; width: 33%">
						[${com.dname}_${com.lev}]${com.name}
						<input type="hidden" name="idx" value="${com.idx}">
						<input type="hidden" name="p" value="${p}">
						<input type="hidden" name="s" value="${s}">
						<input type="hidden" name="b" value="${b}">
						<input type="hidden" name="c" value="${vo.idx }" >

					</div>
					<c:set var="cc"  value="${com.subject }"/>
					<div style=" float: left; width:33%;" id="content${vs.count }">
						${cc}
					</div>
					<div style="float: left; width: 33%">(<fmt:formatDate value="${com.regdate }" pattern="yyyy-MM-dd hh:mm"/>) 						
					<c:if test="${com.id_emp eq list.id_emp }"><input type="submit"  value="삭제"  ></c:if></div>					
					</form>
			</c:forEach>
		</c:if>
			<br>
		<hr>
					<form action="helpcommentInsertOk" method="post" id="commentForm">
						<input type="hidden" name=Id_emp value="${list.id_emp }" >						
						<input type="hidden" name=helpdesk_idx value="${vo.idx }" >						
						<input type="hidden" name="name" value="${list.name }">
						<input type="hidden" name="dname" value="${list.depart_d }">
						<input type="hidden" name="lev" value="${list.lev_l }">
						<input type="hidden" name="mode" value="0">
						<input type="hidden" name="p" value="${p}">
						<input type="hidden" name="s" value="${s}">
						<input type="hidden" name="b" value="${b}">
						<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
					<div style="float: left;"><textarea class="form-control" rows="2" cols="150" name="subject" id="subject" style=" resize: none;" required="required" placeholder="댓글 입력"></textarea></div>
					<div style="float: left;"><input type="submit" class="btn btn-primary" value="등록" id="sendBtn" style="height: 40px;"></div>

					</form>


			
</body>
</html>