<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><sitemesh:write property='title'/></title>

<!-- Bootstrap core CSS-->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="resources/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="resources/css/sb-admin.css" rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="resources/vendor/datatables/jquery.dataTables.js"></script>
<script src="resources/vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="resources/js/demo/datatables-demo.js"></script>
<script type="text/javascript" src="resources/js/comm.js"></script>
<sitemesh:write property='head'/>
</head>
<body>

	<c:if test="${not empty sessionScope.vo}">

		

			<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

				<a class="navbar-brand mr-1" href="index">Project</a>



				<!-- Navbar Search -->

				<!-- Navbar -->
				<ul class="navbar-nav ml-auto ml-md-0"
					style="position: absolute;  right: 0; margin-bottom: 10px">
					<div style="color: white;" align="right">${list.depart_d}(${list.duty_du}):${list.name }</div>
					

					<li class="nav-item dropdown no-arrow"><a
						class="nav-link dropdown-toggle" href="#" id="userDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
					</a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="userDropdown">
							<a class="dropdown-item" href="updateInfo">정보수정하기</a> <a
								class="dropdown-item" href="changepassword">비밀번호변경하기</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#" data-toggle="modal"
								data-target="#logoutModal">Logout</a>
						</div></li>
				</ul>

			</nav>

		<div id="wrapper">				
		<!-- Sidebar -->
				<ul class="sidebar navbar-nav">

					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>정보관리</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">정보관리:</h6>
							<a class="dropdown-item" href="updateInfo">개인정보수정</a>
							<a class="dropdown-item" href="findemp"> 임직원조회 </a> 
							<a class="dropdown-item" href="myhistory">이력기록</a>
						<c:if test="${'인사' eq list.depart_d}">
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="signIn">사원정보등록</a>
							<a class="dropdown-item" href="history">사원이력조회</a>
						</c:if>							
						</div></li>
					<c:if test="${'인사' eq list.depart_d || '평가자' eq temp || '파트장' eq list.duty_du }">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> 
						<c:if test="${'인사' eq list.depart_d}"><span>승진 관리</span></c:if>
						<c:if test="${'인사' ne list.depart_d }"><span>평가 관리</span></c:if>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">인사관리:</h6>
							
								<%-- 인사 고과등록시간을 설정하는 곳 --%>
								<jsp:useBean id="now" class="java.util.Date" />
								<fmt:parseDate value="201901010000" pattern="yyyyMMddHHmm" var="startDate" />
								<fmt:parseDate value="201901292359" pattern="yyyyMMddHHmm" var="endDate" />
								<fmt:formatDate value="${now}" pattern="yyyyMMddhhmm" var="nowDate" />        <%-- 오늘날짜 --%>
								<fmt:formatDate value="${startDate}" pattern="yyyyMMddHHmm" var="openDate"/>  <%-- 시작날짜 --%>
								<fmt:formatDate value="${endDate}" pattern="yyyyMMddHHmm" var="closeDate"/>   <%-- 마감날짜 --%>
 
								<c:if test="${openDate < nowDate && closeDate > nowDate}">
								<c:if test="${'파트장' eq list.duty_du }">
									<a class="dropdown-item" href="evaluation">인사고과등록</a>
								</c:if>
								</c:if>
								<c:if test="${'인사' eq list.depart_d}">
									<a class="dropdown-item" href="findevaluation">인사고과미등록현황</a>
								</c:if>
								<div class="dropdown-divider"></div>
								<c:if test="${'인사' eq list.depart_d && '파트장' eq list.duty_du }">
								<a class="dropdown-item" href="promotionadmin">평가자권한부여</a>								
								</c:if>
								<c:if test="${'인사' eq list.depart_d }">
								<a class="dropdown-item" href="promotionTester">승진평가대상자</a>
								</c:if>
								<c:if test="${'평가자' eq list.temp1}">
								<a class="dropdown-item" href="promotionTest">승진평가</a>
								</c:if>
								<c:if test="${'인사' eq list.depart_d }">
								<a class="dropdown-item" href="testresultcheck">평가시험확인</a>
								</c:if>								
								<c:if test="${'인사' eq list.depart_d }">
								<a class="dropdown-item" href="promotioncheck">정기승진대상자</a>
								</c:if>
								<c:if test="${'사장' eq list.lev_l || 'CEO' eq list.duty_du }">
								<a class="dropdown-item" href="promotionapproval">정기승진승인</a>
								</c:if>								 
								<c:if test="${'인사' eq list.depart_d && '파트장' eq list.duty_du }">
								<a class="dropdown-item" href="specialpromotion">비정기승진인사등록</a>
								</c:if>
								<c:if test="${'사장' eq list.lev_l || 'CEO' eq list.duty_du }">											
								<a class="dropdown-item" href="specialpromotionapproval">비정기승진인사승인</a>
								</c:if>
								<div class="dropdown-divider"></div>
							
						</div></li>
					</c:if>	
					<c:if test="${'파트장' eq list.duty_du || '인사' eq list.depart_d ||'사장' eq list.lev_l }">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>퇴사관리</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">퇴사관리:</h6>
							<c:if test="${'파트장' eq list.duty_du }">
							<a class="dropdown-item" href="resignation">퇴사요청</a> 
							</c:if>
							<c:if test="${'인사' eq list.depart_d }">
							<a class="dropdown-item" href="resigner">퇴사요청리스트</a> 
							</c:if>
							<c:if test="${'사장' eq list.lev_l }">
							<a class="dropdown-item" href="resigneapproval">퇴사승인</a>
							</c:if>
							<div class="dropdown-divider"></div>
						</div></li>
						</c:if>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>사내게시판</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">사내게시판:</h6>
							<a class="dropdown-item" href="helpdesk">업무도움방</a> <a
								class="dropdown-item" href="notice">공지사항</a> <a
								class="dropdown-item" href="anonymous">익명게시판</a>
							<div class="dropdown-divider"></div>
						</div></li>
					
					<c:if test="${'개발' eq list.depart_d }">
					
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>관리자</span>
						</a>
							<div class="dropdown-menu" aria-labelledby="pagesDropdown">
								<h6 class="dropdown-header">관리:</h6>
								<a class="dropdown-item" href="ld">직급,부서등록</a> <a
									class="dropdown-item" href="logCheck">로그인기록</a> <a
									class="dropdown-item" href="boardCheckList">게시판기록</a>
								<div class="dropdown-divider"></div>
							</div></li>
				
					</c:if>
					
				</ul>
				
					
			      <div id="content-wrapper">
			        <div class="container-fluid">
			        
			        
			       <sitemesh:write property='body'/>
			        
			</div>
				
			</div>
				
			<!-- /#wrapper -->
				

			<!-- Logout Modal-->
			<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
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
						<div class="modal-body">로그아웃을하시겠습니까</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">취소</button>
							<a class="btn btn-primary" href="logOut">로그아웃</a>

						</div>
					</div>
				</div>
			</div>
	</div>

	</c:if>

	<c:if test="${empty sessionScope.vo }">
		올바른 접근이 아닙니다.<br>
		로그인후 다시 이용하세요.
	</c:if>
</body>
</html>
