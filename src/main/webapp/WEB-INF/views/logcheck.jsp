<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 기록</title>
<script type="text/javascript">

function onlyNumber(obj){
		if(event.keyCode >= 48 && event.keyCode<=57){
			return true;
		}else{
			event.returnValue=false;
		}	
	}
</script>
</head>
<body>
		<div align="center" style="padding-bottom: 50px;">
    	  <b>로그인 기록</b>
    	</div>
		<div class="subtitle"  align="right">
			${paging.pageInfo }
		</div>
	
	<form >		
		<div class="form-row" align="right">	
			<div class="subtitle" align="right"  style="width: 30%">
				<input type="text" class="form-control" onkeypress="onlyNumber(this)" name="i" placeholder="사번으로 검색" style=" float: left: ;" >
			</div>
			&nbsp;&nbsp;<div align="right">
				<input type="submit" class="btn btn-primary"   value="검색" style="float: left;">
			</div>	
		</div>
		<br>	
	</form>


	<table class="table">
		<tr style="background-color: #e6ecff" align="center">
			<th>번호</th>
			<th style="width: 25%">사번</th>
			<th>로그인형태</th>
			<th>시간</th>
			<th>IP</th>
		</tr>
		<c:if test="${paging.totalCount==0 }">
			<tr>
				<td colspan="5" align="center">로그인 전무 에러의심!</td>
			</tr>
		</c:if>
		<c:if test="${paging.totalCount>0 }"> <%-- 글이 있다면 --%>
			<c:if test="${not empty paging.lists }"> <%-- 리스트가 비워지있지 않다면 --%>
				<%-- 번호는 계산해서 출력 --%>
				<%-- 시작번호 = 전체개수 - (현재페이지-1)*페이지사이즈 --%>
				<c:set var="no" value="${paging.totalCount - (paging.currentPage-1)*paging.pageSize +1}"/>
				<c:forEach var="vo" items="${paging.lists }" varStatus="vs"> <%-- 글만큼 반복 --%>
					<tr align="center">
						<td>${no - vs.count }</td>
						<td align="center">
							<c:out value="${vo.id_emp}"/>
						</td>
						<td>
							<c:if test="${vo.logType eq 1}">
								로그인
							</c:if>							
							<c:if test="${vo.logType eq 2}">
								로그아웃
							</c:if>							
						</td>
						<td><fmt:formatDate value="${vo.logDate }" pattern="yyyy-MM-dd HH:mm"/> </td>
						<td>${vo.ip }</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:if>
		<tr>
			<td colspan="5" align="right" style="border: none;">
				<input type="hidden" name="Id_emp" value="${ie }">
				<input type="button" class="btn btn-primary" onclick="post_to_url('excellogCheck2', {'Id_emp':'${ie}'});" value="엑셀">
			</td>
		</tr>
		<tr>
			<td colspan="5" align="center" style="border: none;">
					<c:if test="${ie eq null }">
					${paging.pageList}
					</c:if>
					<c:if test="${ie ne null }">
					${paging.pageListid }
					</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="5" align="right" style="border: none;">
				<input type="hidden" name="p" value="${paging.currentPage }">
				<input type="hidden" name="s" value="${paging.pageSize }">
				<input type="hidden" name="b" value="${paging.blockSize }">
				<input type="hidden" name="ip" value="${pageContext.request.remoteAddr }">
				<input type="hidden" name="idx" value="0" id="idx">
			</td>
		</tr>
	</table>
</body>
</html>