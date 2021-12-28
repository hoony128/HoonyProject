<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

</head>
<body>
	
	<div id="list" style="padding-bottom: 50px;">
      <b>공지사항</b>
    </div>


	<table class="table" >
		<tr style="background-color: #e6ecff">
			<th>no</th>
			<th>제목</th>
			<th>작성부서</th>
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
					<span style="cursor: pointer;"onclick="post_to_url('noticeview', {'idx':'${vo.idx }','p':'${paging.currentPage }','s':'${paging.pageSize }','b':'${paging.blockSize }','m':'1'});">
						<c:out value="${vo.subject }"/>
					</span>
				</td>
				<td>${vo.dname}</td>
				<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></td>
				<td>${vo.hit}</td>
			</tr>
			</c:forEach>
		</c:if>
		</c:if>
		<tr>
			<td align="center" colspan="5" style="border: none;">
				<form action="notice" method="get">
				<select id="field" name="field">
					<option value="dname" <c:if test="${field eq 'dname'}">  selected= "selected" </c:if>>부서</option>
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
					<div align="center">${paging.pageList }</div>
					<div align="right"><input type="button" class="btn btn-primary" value="작성" onclick="post_to_url('noticewrite', {'p':'${paging.currentPage }','s':'${paging.pageSize }','b':'${paging.blockSize }'});"></div>

</body>
</html>