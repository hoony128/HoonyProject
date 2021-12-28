<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>익명게시판 - ${vo.subject}</title>

</head>
<body>
			
			
			
			<div align="left" style="font-size: 30px;">[익명게시글] ${vo.subject }</div>
			<br>
			<div align="left" style="font-size: 10px;">작성자:${vo.id }</div>
			<div align="left" style="font-size: 10px; color: silver;"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd hh:mm:ss"/> | 조회${vo.hit }</div>
			
			<hr>
					<c:set var="c" value="${vo.content }"/>			
					${c }
					<div align="right" style="size: 15px;">
					<input type="button" class="btn btn-secondary" value="수정" onclick="post_to_url('anonymousupdate', {'idx':'${vo.idx }','m':'0','p':'${p }','s':'${s }','b':'${b }'});">					
					<input type="button" class="btn btn-secondary" value="삭제" onclick="post_to_url('anonymousdelete', {'idx':'${vo.idx }','m':'0','p':'${p }','s':'${s }','b':'${b }'});">					 
					<input type="button" class="btn btn-secondary" value="목록" onclick="post_to_url('anonymous', {'p':'${p }','s':'${s }','b':'${b }'});">								
					</div>
			<hr>


			<%-- 댓글 목록 --%>
			<%-- --%>
 		<div style="float: left;">전체 리플</div>  <div style="color: red; float: left;">${vo.commentCount}</div><div style="float: left;">개</div>	
 		<br>
 		<div class="dropdown-divider"></div>
		<c:if test="${not empty vo.commentList }">
			<c:forEach var="com" items="${vo.commentList }" varStatus="vs">
					
					
					<div style=" float: left; width: 33%">
						${com.id}						
					</div>
					<c:set var="cc" value="${com.content }"/>
					<div  style=" float: left; width:33%;" id="content${vs.count }">
						${cc}
					</div>
					<div style="float: left; width: 33%">(<fmt:formatDate value="${com.regdate }" pattern="yyyy-MM-dd hh:mm"/>) 
 
					<a  class="btn btn-primary"  href="#" data-toggle="modal" data-target="#deleteModal">삭제</a>					

					</div>

					



			<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">로그아웃</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
							<form action="anonymousdelete" method="post">
						<div class="modal-body">정말 삭제하시겠습니까?</div>
						<div align="center" >비밀번호<input class="form-control" type="password" name="password"> </div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
								<input type="hidden" name="idx" value="${com.idx}">
								<input type="hidden" name=Id_emp value="${list.id_emp }" >		
								<input type="hidden" name="p" value="${p}">
								<input type="hidden" name="s" value="${s}">
								<input type="hidden" name="b" value="${b}">
								<input type="hidden" name=anonymous_idx value="${vo.idx }" >		
								<input type="hidden" name="prevUrl" value="${prevUrl }">																									
								<input type="hidden" name="mode" value="0">
								<input type="submit" class="btn btn-primary" value="삭제">
							<%--
							<a class="btn btn-primary" href="">삭제</a>
							 --%>

						</div>
							</form>
					</div>
				</div>
			</div>
					<br>
					<hr>
			</c:forEach>
		</c:if>
		
		<form action="anonymouscommentInsertOk" method="post" id="commentForm">
			<input type="hidden" name=Id_emp value="${list.id_emp }" >						
			<input type="hidden" name=anonymous_idx value="${vo.idx }" >												
			<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}">
			<input type="hidden" name="mode" value="0">
			<input type="hidden" name="p" value="${p}">
			<input type="hidden" name="s" value="${s}">
			<input type="hidden" name="b" value="${b}">

			<div class="form-group">
         				<div class="form-row">
					<div><input width="15%" class="form-control" type="text" placeholder="닉네임" name="id" id="id" required="required"></div>
					&nbsp;&nbsp;<div><input width="15%" class="form-control" placeholder="비밀번호"type="password" name="password" id="password" required="required"></div>
				</div>
			</div>
							
				<div style="float: left;"><textarea class="form-control" rows="2" cols="60" name="content" id="content" placeholder="내용을입력하세요" required="required"></textarea></div>
				&nbsp;&nbsp;<div style="float: left;"><input type="submit" class="btn btn-primary" value="등록" id="sendBtn" style="height: 40px;"></div>

		</form>
	<c:if test="${not empty result and result==1 }">
		<script type="text/javascript">
			alert("비밀번호가 틀립니다.");
		</script>
	</c:if>
</body>
</html>