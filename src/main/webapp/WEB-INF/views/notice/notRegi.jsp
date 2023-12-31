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
<title>공지 등록</title>
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
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css"
	rel="stylesheet">
<script type="text/javascript">
				// <![CDATA[
						var sparks=75; // how many sparks per clicksplosion
						var speed=33; // how fast - smaller is faster
						var bangs=5; // how many can be launched simultaneously (note that using too many can slow the script down)
						var colours=new Array('#03f', '#f03', '#0e0', '#93f', '#0cf', '#f93', '#f0c'); 
						//                     blue    red     green   purple  cyan    orange  pink
						
						/****************************
						*   Clicksplosion Effect    *
						*(c)2012-3 mf2fm web-design *
						*  http://www.mf2fm.com/rv  *
						* DON'T EDIT BELOW THIS BOX *
						****************************/
						var intensity=new Array();
						var Xpos=new Array();
						var Ypos=new Array();
						var dX=new Array();
						var dY=new Array();
						var stars=new Array();
						var decay=new Array();
						var timers=new Array();
						var swide=800;
						var shigh=600;
						var sleft=sdown=0;
						var count=0;
						
						function addLoadEvent(funky) {
						  var oldonload=window.onload;
						  if (typeof(oldonload)!='function') window.onload=funky;
						  else window.onload=function() {
						    if (oldonload) oldonload();
						    funky();
						  }
						}
						
						addLoadEvent(clicksplode);
						
						function clicksplode() { if (document.getElementById) {
						  var i, j;
						  window.onscroll=set_scroll;
						  window.onresize=set_width;
						  document.onclick=eksplode;
						  set_width();
						  set_scroll();
						  for (i=0; i<bangs; i++) for (j=sparks*i; j<sparks+sparks*i; j++) {
						    stars[j]=createDiv('*', 13);
						    document.body.appendChild(stars[j]);
						  }
						}}
						
						function createDiv(char, size) {
						  var div, sty;
						  div=document.createElement('div');
						  sty=div.style;
						  sty.font=size+'px monospace';
						  sty.position='absolute';
						  sty.backgroundColor='transparent';
						  sty.visibility='hidden';
						  sty.zIndex='101';
						  div.appendChild(document.createTextNode(char));
						  return (div);
						}
						
						function bang(N) {
						  var i, Z, A=0;
						  for (i=sparks*N; i<sparks*(N+1); i++) { 
						    if (decay[i]) {
						      Z=stars[i].style;
						      Xpos[i]+=dX[i];
						      Ypos[i]+=(dY[i]+=1.25/intensity[N]);
						      if (Xpos[i]>=swide || Xpos[i]<0 || Ypos[i]>=shigh+sdown || Ypos[i]<0) decay[i]=1;
							  else {
						        Z.left=Xpos[i]+'px';
						        Z.top=Ypos[i]+'px';
							  }
						      if (decay[i]==15) Z.fontSize='7px';
						      else if (decay[i]==7) Z.fontSize='2px';
						      else if (decay[i]==1) Z.visibility='hidden';
							  decay[i]--;
							}
							else A++;
						  }
						  if (A!=sparks) timers[N]=setTimeout('bang('+N+')', speed);
						}
						
						function eksplode(e) { 
						  var x, y, i, M, Z, N;
						  set_scroll();
						  y=(e)?e.pageY:event.y+sdown;
						  x=(e)?e.pageX:event.x+sleft;
						  N=++count%bangs;
						  M=Math.floor(Math.random()*3*colours.length);
						  intensity[N]=5+Math.random()*4;
						  for (i=N*sparks; i<(N+1)*sparks; i++) {
						    Xpos[i]=x;
						    Ypos[i]=y-5;
						    dY[i]=(Math.random()-0.5)*intensity[N];
						    dX[i]=(Math.random()-0.5)*(intensity[N]-Math.abs(dY[i]))*1.25;
						    decay[i]=16+Math.floor(Math.random()*16);
						    Z=stars[i].style;
						    if (M<colours.length) Z.color=colours[i%2?count%colours.length:M];
						    else if (M<2*colours.length) Z.color=colours[count%colours.length];
						    else Z.color=colours[i%colours.length];
						    Z.fontSize='13px';
						    Z.visibility='visible';
						  }
						  clearTimeout(timers[N]);
						  bang(N);
						} 
						
						function set_width() {
						  var sw_min=999999;
						  var sh_min=999999;
						  if (document.documentElement && document.documentElement.clientWidth) {
						    if (document.documentElement.clientWidth>0) sw_min=document.documentElement.clientWidth;
						    if (document.documentElement.clientHeight>0) sh_min=document.documentElement.clientHeight;
						  }
						  if (typeof(self.innerWidth)=='number' && self.innerWidth) {
						    if (self.innerWidth>0 && self.innerWidth<sw_min) sw_min=self.innerWidth;
						    if (self.innerHeight>0 && self.innerHeight<sh_min) sh_min=self.innerHeight;
						  }
						  if (document.body.clientWidth) {
						    if (document.body.clientWidth>0 && document.body.clientWidth<sw_min) sw_min=document.body.clientWidth;
						    if (document.body.clientHeight>0 && document.body.clientHeight<sh_min) sh_min=document.body.clientHeight;
						  }
						  if (sw_min==999999 || sh_min==999999) {
						    sw_min=800;
						    sh_min=600;
						  }
						  swide=sw_min-7;
						  shigh=sh_min-7;
						}
						
						function set_scroll() {
						  if (typeof(self.pageYOffset)=='number') {
						    sdown=self.pageYOffset;
						    sleft=self.pageXOffset;
						  }
						  else if (document.body && (document.body.scrollTop || document.body.scrollLeft)) {
						    sdown=document.body.scrollTop;
						    sleft=document.body.scrollLeft;
						  }
						  else if (document.documentElement && (document.documentElement.scrollTop || document.documentElement.scrollLeft)) {
						    sleft=document.documentElement.scrollLeft;
						    sdown=document.documentElement.scrollTop;
						  }
						  else {
						    sdown=0;
						    sleft=0;
						  }
						}
					// ]]>
		</script>
<style type="text/css">
* {
	cursor: url(https://cur.cursors-4u.net/holidays/hol-4/hol336.cur), auto
		!important;
}
</style>
<style type="text/css">
.adfit__swipeable {
	-webkit-tap-highlight-color: transparent;
	cursor: default;
	height: 100%;
	left: 0;
	outline: none;
	position: absolute;
	top: 0;
	width: 100%
}
</style>
	<script>  
	 	function logout() {
			if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "${root}/user/logout";
		 	}
		}
       	function addNot() {
			let nickname=document.getElementsByName("nickname").value;
			let notice_title=document.getElementsByName("notice_title").value;
			let notice_content=document.getElementsByName("notice_content").value;

			return true;
		}
       	function toListPage() {
    		location.href="${pageContext.servletContext.contextPath}/notice/nlist";
    	}
    </script>
	<script>
	  function checkForm() {
	    var titleElement = document.getElementsByName("notice_title")[0];
	    var contentElement = document.getElementsByName("notice_content")[0];
	
	    if (titleElement.value.trim() === "" || contentElement.value.trim() === "") {
	      alert("제목과 내용을 입력해주세요.");
	      return false;
	    }
	    
	    return true;
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
				<h2 style="text-align: center;">공지 작성</h2>
				<div class="contentBox">
					<form
						action="${pageContext.servletContext.contextPath}/notice/newNot"
						method="post" onsubmit="return checkForm();">
						<div class="contentHeader">
							<div style="font-size: 30px;">
								제목 : <input class="form-control" type="text"
									placeholder="Enter Title" name="notice_title">
							</div>
							<div class="mt-3 p-3">
								닉네임 :
								<div class="form-control" id="nickname" name="nickname"
									value="${sessionScope.SESS_NICKNAME}">${sessionScope.SESS_NICKNAME}</div>
							</div>
						</div>
						<div class="mt-3 col p-3">
							내용 :
							<textarea id="cont" class="form-control" cols="100" rows="30"
								name="notice_content"></textarea>
						</div>
						<button type="button" class="get col p-3 btn btn-warning"
							style="float: left;" onclick="history.back();">이전</button>
						<button type="submit" class="register col p-3 btn btn-warning"
							style="float: right;">전송</button>
						<input type="hidden" name="nickname"
							value="${noti.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.noti.nickname}">
					</form>
				</div>
			</div>
		</main>
		 <!-- 푸터 고정 -->
   <footer class="bgcolor" style="position : absolute; padding-bottom:100px">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">            
          </div>          
      </div>                
	</footer>
	</div>
</body>
</html>