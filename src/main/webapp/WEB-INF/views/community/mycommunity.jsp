<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>내가 쓴 글</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"
	rel="stylesheet" />
<link
	href="<c:url value='${root}/resources/bootstrap/css/mypageStyles.css' />"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/scripts.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
	crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css"
	rel="stylesheet">

<script>
	function back() {
		window.location.href = "${root}/mypage/mypage";
	}
	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "${root}/user/logout";
		}
	}
</script>
<style>
a:hover {
	background-color: #feeaa5;
}

.main {
	padding-top: 0.7cm;
	padding-left: 1.0cm;
	padding-right: 1.5cm;
	padding-bottom: 3cm;
	height: 120px;
}

.bg-yellow { -
	-bs-bg-opacity: 1;
	background-color: #feeaa5 !important;
}

.main1 {
	border-bottom: 1px solid #645326;
	padding-bottom: 2px;
	padding-top: 2px;
}

.tab {
	padding-bottom: 0;
	padding-top: 0;
	border-bottom: 1px solid #645326;
	border-top: 1px solid #645326;
}

.img_main {
	width: 60%;
	margin: 0px auto;
	display: block;
	width: 250px;
	height: 90px;
}

.bgcolor {
	background-color: #f9f8f3;
}

.btn {
	width: 6rem;
	height: 2.5rem;
	float: left;
}
</style>
<style type="text/css">
.nanum {
	font-family: 'NanumSquareNeo';
}

.nanumB {
	font-family: 'NanumSquareNeoBold';
}
</style>

<style>
/* a태그 스타일 */
a {
	text-decoration-line: none;
	color: inherit;
}
</style>
</head>
<body class="sb-nav-fixed bgcolor nanum">
	<nav
		class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow">

		<!-- 로그인 로그아웃 마이페이지 반응형 -->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
			<div class="input-group">
				<%
				String id = (String) session.getAttribute("SESS_EMAIL");
				%>
				<%
				String nickname = (String) session.getAttribute("SESS_NICKNAME");
				%>

				<%
				if (id != null) {
				%>
				<div style="padding: 6px 10px; font-size: 14px;">
					♡<b>${sessionScope.SESS_NICKNAME}</b>님 환영합니다♡
				</div>
				<a type="button" onclick="logout();"
					style="font-size: 14px; padding: 6px 5px;">로그아웃</a> <a
					href="${root}/mypage/mypage" type="button"
					style="font-size: 14px; padding: 6px 5px;">마이페이지</a>
				<%
				} else {
				%>
				<a href="${root}/user/login" type="button"
					style="font-size: 14px; padding: 6px 5px;">로그인</a>
				<%
				}
				%>
			</div>
		</form>
	</nav>

	<!-- 로고 -->
	<nav class="main bg-white">
		<a class="mainlogo" onclick="location.href='${root}/main/main'"> <img
			class="img_main" src="../resources/image/logo.png"
			style="width: 250px; height: 90px;" />
		</a>
	</nav>

	<!-- 상단바 메뉴 -->
	<nav class="tab sb-topnav2 navbar navbar-expand; bg-white">
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/pet/petall"><b>공고</b></a> <a
			class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/shel/shelall"><b>보호소</b></a> <a
			class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/with/withca"><b>위드펫</b></a> <a
			class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/community/clist"><b>커뮤니티</b></a> <a
			class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/notice/nlist"><b>공지사항</b></a>
	</nav>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-10 pt-5 ps-4" style="width: 80%;">
				<h2 class="mt-1 mb-3">
					<b>내가쓴글</b>
				</h2>

				<div class="card mb-4">
					<div class="card-header">
						<b>내가 쓴 글 목록</b>
					</div>
					<div class="card-body" style="padding: 40px 50px 30px 50px;">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>제목</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="myCommu" items="${requestScope.myCommuList}"
									varStatus="status">
									<c:set var="fixdate"
										value="${fn:substring(myCommu.reg_date, 0, 11)}" />
									<tr>
										<td><a
											href="${pageContext.servletContext.contextPath}/community/commuSel?c_no=${myCommu.c_no}">${myCommu.title}</a></td>
										<td>${fixdate}</td>
										<td>${myCommu.view_cnt}</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
						<button type="button" class="btn btn-warning" onclick="back();">이전</button>
					</div>
				</div>

			</div>
		</main>
		<!-- 푸터 고정 -->
		<footer class="bgcolor"
			style="position: absolute; padding-bottom: 100px">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
				</div>
			</div>
		</footer>
	</div>
</body>
</html>