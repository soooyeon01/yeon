<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.NoticeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.mapper.MypageMapper"%>
<%@ page import="com.spring.domain.MembersDTO"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>공지 수정</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"
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
<script type="text/javascript">
<!--
	/*=============================================================================
	画像１個の後追いマウスストーカー　参考サイト : http://www5e.biglobe.ne.jp/~purest/javascript/index.html
	★古いブラウザ対応分削除し、CSSのtransition追加で、後追いに改造。　 2016/09/22
	=============================================================================*/
	(function() { //即時関数で囲んでグローバル変数を消すため、この行はこのままで

		var mga = "https://tistory1.daumcdn.net/tistory/4694508/skin/images/hai1.gif"; //★画像指定
		var hx = 15; //★表示位置 x座標。マウスと画像の横位置。マイナスで左
		var hy = 15; //★表示位置 y座標。マウスと画像の縦位置。マイナスで上
		var TRANS = "transition:all 1s ease-in;";//★マウス画像の後追い方法。css指定。速度や速度変化。わからないならこのままで

		//指定ここまで--------------------------------------------------------------
		document.onmousemove = function(e) {
			var ob = document.getElementById("foo").style;
			ob.left = e.pageX + hx + "px";
			ob.top = e.pageY + hy + "px";
		}
		document
				.write("<img src="+mga+" id='foo' style='position:absolute; "+TRANS+"'>");
	}());//即時関数終了
// -->
</script>
<script>
	function upNot() {
		let notice_no = document.getElementsByName("notice_no").value;
		let notice_title = document.getElementsByName("notice_title").value;
		let notice_content = document.getElementsByName("notice_content").value;

		return true;
	}
	function toListPage() {
		location.href = "${pageContext.servletContext.contextPath}/notice/nlist";
	}
	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "${root}/user/logout";
		}
	}
</script>
<style>
#cont {
	width: 100%;
	height: 30rem;
}
</style>
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
	width: 15rem;
	height: 5rem;
	margin-left: 1rem;
	margin-right: 1rem;
	margin-bottom: 2rem;
}

.contentBox {
	border-radius: 6px;
	margin: auto;
}

.contentHeader {
	position: relative;
	margin-bottom: 20px;
	padding-bottom: 20px;
	font-size: 20px;
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
			<div class="container mt-3">
				<h2 style="text-align: center;">공지 수정</h2>
				<div class="contentBox">
					<form
						action="${pageContext.servletContext.contextPath}/notice/notUp?notice_no=${selectone.notice_no}"
						method="post">
						<input type="hidden" name="notice_no"
							value="${selectcone.notice_no}">
						<div class="contentHeader">
							<div style="font-size: 30px;">
								제목 : <input class="form-control" type="text"
									placeholder="Enter Title" name="notice_title"
									value="${selectone.notice_title}">
							</div>
							<div class="mt-3 p-3">
								닉네임 :
								<p class="form-control" type="text" name="nickname">${selectone.nickname}</p>
							</div>
						</div>
						<div class="mt-3 col p-3">
							내용 :
							<textarea id="cont" class="form-control" rows="5"
								name="notice_content">${selectone.notice_content}</textarea>
						</div>
						<button type="button" class="get col p-3 btn btn-warning"
							style="float: left;" onclick="history.back();">이전</button>
						<button type="submit" class="register col p-3 btn btn-warning"
							style="float: right;" onclick="upNot();">수정하기</button>
					</form>
				</div>

			</div>
		</main>
	</div>
	<footer class="py-4 bg-light mt-auto">
		<div class="container-fluid px-4">
			<div class="d-flex align-items-center justify-content-between small">
				<div class="text-muted" style="padding-top: 120px;">Website
					2023 &copy; Happy OkDogCat</div>

				<div></div>
			</div>
		</div>
	</footer>
</body>
</html>