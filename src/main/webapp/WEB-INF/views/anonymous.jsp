e<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>익명게시판</title>
</head>
<body>
	<div style="font-size: 30px;" id="list" >
      <b>익명게시판</b>
    </div>
	<div align="right" style="padding-bottom: 25px;"><b>(${paging.pageInfo })</b></div>

	<table class="table" >
		<tr style="background-color: #e6ecff">
			<th>no</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성시간</th>
			<th>조회수</th>
		</tr>
		<c:if test="${empty paging.lists }">
			<tr>
				<td colspan="5" align="center">글이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<c:if test="${paging.totalCount>0 }"> <%-- 글이 있다면 --%>
			<c:if test="${not empty paging.lists }"> <%-- 리스트가 비워지있지 않다면 --%>
				
			<c:set var="no" value="${paging.totalCount - (paging.currentPage-1)*paging.pageSize }"/>
			<c:forEach var="vo" items="${paging.lists }" varStatus="vs">
			<tr>
				<td>${no - vs.count + 1 }</td>
				<td width="30%">
					<span style="cursor: pointer;"onclick="post_to_url('anonymousview', {'idx':'${vo.idx }','p':'${paging.currentPage }','s':'${paging.pageSize }','b':'${paging.blockSize }','m':'1'});">
						<c:out value=" ${vo.subject } "/>
						-(${vo.commentCount })
					</span>
				</td>
				<td>${vo.id}</td>
				<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></td>
				<td>${vo.hit}</td>
			</tr>
			</c:forEach>
		</c:if>
		</c:if>
		<tr>
			<td align="center" colspan="5" style="border: none;">
				<script type="text/javascript">
					function search(){
						var searchText = $("#searchText").val();
						if(searchText==null || searchText.trim().length==0){
							alert('검색내용은 반드시 입력해야 합니다.');
							$("#searchText").val("");
							$("#searchText").focus();
							return false;
						}
						var searchField = $("#searchField").val();
						location.href='searchList.jsp?p=${paging.currentPage }&b=${paging.blockSize }&s=${paging.pageSize }&searchField='
								      +searchField+'&searchText='+encodeURIComponent(searchText); 
					}
				</script>
				<form action="anonymous" method="get">
				<select id="field" name="field">
					<option value="id" <c:if test="${field eq 'id'}">  selected= "selected" </c:if>>작성자</option>
					<option value="subject" <c:if test="${field eq 'subject'}">  selected= "selected" </c:if> >제목</option>
					<option value="content" <c:if test="${field == 'content'}">  selected= "selected" </c:if> >내용</option>
					<option value="" <c:if test="${field == ''}">  selected= "selected" </c:if> >전체</option>
				</select> 
				<input type="text" name="text" size="40" required="required">
				<input type="submit" value="검색" >
				</form>
			</td>
		</tr>		
	</table>
			<div align="center">
				${paging.pageList }
			</div>
			
			<div align="right"><input class="btn btn-primary" type="button" value="작성" onclick="post_to_url('anonymouswrite', {'p':'${paging.currentPage }','s':'${paging.pageSize }','b':'${paging.blockSize }'});"></div>
			
			
	
</body>
</html>