<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />

  <style>
    #container {
      width: 70%;
      margin: 0 auto;     /* 가로로 중앙에 배치 */
      padding-top: 10%;   /* 테두리와 내용 사이의 패딩 여백 */
    }

     

    #list {
      text-align: center;
    }

    #write {
      text-align: right;
    }

     

    /* Bootstrap 수정 */
	tr.nohover:hover{background-color: white;}
	
	
    .table > thead {
      background-color: #b3c6ff;
    }

    .table > thead > tr > th {
      text-align: center;
    }

    .table > tbody > tr > #title {
      text-align: left;
    }
  
    div > #paging {
      text-align: center;
    }
    .hit {
      animation-name: blink;
      animation-duration: 1.5s;
      animation-timing-function: ease;
      animation-iteration-count: infinite;
    }
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
    }

  </style>
<title>임직원조회</title>
</head>


<body>

				<form  method="post" id="test">
					<select name="depart2" onchange="$('#test').submit();" class="form-control" style="width: 180px; float: left;" >
						<option value="">-전체-</option>
							<c:forEach var="vo" items="${dlist }">
								<option <c:if test="${depart == vo.idx}">  selected= "selected" </c:if> value="${vo.idx }"> ${vo.depart } </option>
							</c:forEach>
					</select>
				</form>	
				<br>
				<br>	
	<form action="excelfindemp">
	<input type="hidden" name="depart2" value="${depart}">
	
		<div id="list" style="padding-bottom: 50px;">
    	  <b>조회결과</b>
    	</div>
	<table class="table">

		<tr style="background-color: #e6ecff">
			<th>부서명</th>
			<th>직급</th>
			<th>직책</th>
			<th>사번</th>
			<th>이름</th>
			<th>메일</th>
			<th>전화번호</th>
		</tr>
		<c:if test="${empty paging}">
			<tr>
				<td colspan="7" align="center">기록이 분명히 있음 에러이다!</td>
			</tr>
		</c:if>
		
		
		<%-- 있을 경우 시작 --%>
		<c:if test="${not empty paging}"> 
			<c:forEach var="vo" items="${paging }" varStatus="vs"> 
				<tr align="center">
					<td> ${vo.depart_d }</td>
					<td> ${vo.lev_l}</td>
					<td> ${vo.duty_du}</td>
					<td> <c:out value="${vo.id_emp}"/> </td>
					<td> ${vo.name }</td>
					<td> ${vo.email }</td>
					<td> ${vo.first_phone}-${vo.mid_phone}-${vo.last_phone} </td>
				</tr>
			</c:forEach>

		</c:if> <%-- 있을 경우 종료 --%>
		<tr class="nohover">
			<td colspan="8" align="right" style="border: none;">
				<input class="btn btn-primary" type="submit" value="엑셀">
				<%--
				<input type="button" onclick="location.href='exlefindemp'" value="엑셀">
				 --%>
			</td>
		</tr>
	</table>
	</form>		



</body>
</html>