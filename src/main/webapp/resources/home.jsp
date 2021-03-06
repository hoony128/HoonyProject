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
							<a class="dropdown-item" href="updateInfo">??????????????????</a> <a
								class="dropdown-item" href="changepassword">????????????????????????</a>
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
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>????????????</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">????????????:</h6>
							<a class="dropdown-item" href="updateInfo">??????????????????</a>
							<a class="dropdown-item" href="findemp"> ??????????????? </a> 
							<a class="dropdown-item" href="myhistory">????????????</a>
						<c:if test="${'??????' eq list.depart_d}">
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="signIn">??????????????????</a>
							<a class="dropdown-item" href="history">??????????????????</a>
						</c:if>							
						</div></li>
					<c:if test="${'??????' eq list.depart_d || '?????????' eq temp || '?????????' eq list.duty_du }">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> 
						<c:if test="${'??????' eq list.depart_d}"><span>?????? ??????</span></c:if>
						<c:if test="${'??????' ne list.depart_d }"><span>?????? ??????</span></c:if>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">????????????:</h6>
							
								<%-- ?????? ????????????????????? ???????????? ??? --%>
								<jsp:useBean id="now" class="java.util.Date" />
								<fmt:parseDate value="201901010000" pattern="yyyyMMddHHmm" var="startDate" />
								<fmt:parseDate value="201901292359" pattern="yyyyMMddHHmm" var="endDate" />
								<fmt:formatDate value="${now}" pattern="yyyyMMddhhmm" var="nowDate" />        <%-- ???????????? --%>
								<fmt:formatDate value="${startDate}" pattern="yyyyMMddHHmm" var="openDate"/>  <%-- ???????????? --%>
								<fmt:formatDate value="${endDate}" pattern="yyyyMMddHHmm" var="closeDate"/>   <%-- ???????????? --%>
 
								<c:if test="${openDate < nowDate && closeDate > nowDate}">
								<c:if test="${'?????????' eq list.duty_du }">
									<a class="dropdown-item" href="evaluation">??????????????????</a>
								</c:if>
								</c:if>
								<c:if test="${'??????' eq list.depart_d}">
									<a class="dropdown-item" href="findevaluation">???????????????????????????</a>
								</c:if>
								<div class="dropdown-divider"></div>
								<c:if test="${'??????' eq list.depart_d && '?????????' eq list.duty_du }">
								<a class="dropdown-item" href="promotionadmin">?????????????????????</a>								
								</c:if>
								<c:if test="${'??????' eq list.depart_d }">
								<a class="dropdown-item" href="promotionTester">?????????????????????</a>
								</c:if>
								<c:if test="${'?????????' eq list.temp1}">
								<a class="dropdown-item" href="promotionTest">????????????</a>
								</c:if>
								<c:if test="${'??????' eq list.depart_d }">
								<a class="dropdown-item" href="testresultcheck">??????????????????</a>
								</c:if>								
								<c:if test="${'??????' eq list.depart_d }">
								<a class="dropdown-item" href="promotioncheck">?????????????????????</a>
								</c:if>
								<c:if test="${'??????' eq list.lev_l || 'CEO' eq list.duty_du }">
								<a class="dropdown-item" href="promotionapproval">??????????????????</a>
								</c:if>								 
								<c:if test="${'??????' eq list.depart_d && '?????????' eq list.duty_du }">
								<a class="dropdown-item" href="specialpromotion">???????????????????????????</a>
								</c:if>
								<c:if test="${'??????' eq list.lev_l || 'CEO' eq list.duty_du }">											
								<a class="dropdown-item" href="specialpromotionapproval">???????????????????????????</a>
								</c:if>
								<div class="dropdown-divider"></div>
							
						</div></li>
					</c:if>	
					<c:if test="${'?????????' eq list.duty_du || '??????' eq list.depart_d ||'??????' eq list.lev_l }">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>????????????</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">????????????:</h6>
							<c:if test="${'?????????' eq list.duty_du }">
							<a class="dropdown-item" href="resignation">????????????</a> 
							</c:if>
							<c:if test="${'??????' eq list.depart_d }">
							<a class="dropdown-item" href="resigner">?????????????????????</a> 
							</c:if>
							<c:if test="${'??????' eq list.lev_l }">
							<a class="dropdown-item" href="resigneapproval">????????????</a>
							</c:if>
							<div class="dropdown-divider"></div>
						</div></li>
						</c:if>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>???????????????</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">???????????????:</h6>
							<a class="dropdown-item" href="helpdesk">???????????????</a> <a
								class="dropdown-item" href="notice">????????????</a> <a
								class="dropdown-item" href="anonymous">???????????????</a>
							<div class="dropdown-divider"></div>
						</div></li>
					
					<c:if test="${'??????' eq list.depart_d }">
					
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>?????????</span>
						</a>
							<div class="dropdown-menu" aria-labelledby="pagesDropdown">
								<h6 class="dropdown-header">??????:</h6>
								<a class="dropdown-item" href="ld">??????,????????????</a> <a
									class="dropdown-item" href="logCheck">???????????????</a> <a
									class="dropdown-item" href="boardCheckList">???????????????</a>
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
							<h5 class="modal-title" id="exampleModalLabel">????????????</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">??</span>
							</button>
						</div>
						<div class="modal-body">?????????????????????????????????</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">??????</button>
							<a class="btn btn-primary" href="logOut">????????????</a>

						</div>
					</div>
				</div>
			</div>
	</div>

	</c:if>

	<c:if test="${empty sessionScope.vo }">
		????????? ????????? ????????????.<br>
		???????????? ?????? ???????????????.
	</c:if>
</body>
</html>
