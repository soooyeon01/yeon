<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>보호소 즐겨찾기</title>
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet">
<link href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script>
	function back() {
		window.location.href = "${root}/mypage/mypage";
	}
</script>
<style type="text/css">
		.nanum{ font-family: 'NanumSquareNeo'; }					
</style>
<style>
a:hover {
	background-color: #feeaa5;
}
a {
	text-decoration-line: none;
	color: inherit;
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
	font-family: 'NanumSquareNeo';
}
table.table.table-bordered th {
	background-color: #feeaa5;
}
th{text-align:center;}
</style>
</head>
<body class="sb-nav-fixed bgcolor">
	<nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow">
		<form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
             <div class="input-group">
             <% String id = (String)session.getAttribute("SESS_EMAIL"); %>
             <% String nickname = (String)session.getAttribute("SESS_NICKNAME"); %>
              
            <%  if( id != null) { %>
            <div style="padding:6px 10px;  font-size:14px;">
            	♡<b>${sessionScope.SESS_NICKNAME}</b>님 환영합니다♡
            </div>
                   <a type="button" onclick="logout();" style="font-size: 14px; padding: 6px 5px;">로그아웃</a>
                   <a href="${root}/mypage/mypage" type="button" style="font-size: 14px; padding: 6px 5px;">마이페이지</a>                          
            <%} else{%>
                <a href="${root}/user/login" type="button" style="font-size: 14px; padding: 6px 5px;">로그인</a>                                         
            <%}  %> 
                </div>
            </form>     
            </nav>
	<script>
		function logout() {
			if (confirm("로그아웃 하시겠습니까?")) {
				location.href = "${root}/user/logout";
			}
		}
	</script>
	<!-- 로고 -->
	<nav class="main bg-white">
		<a class="mainlogo" href="${root}/main/main"> <img
			class="img_main" src="../resources/image/logo.png"
			style="width: 250px; height: 90px;" />
		</a>
	</nav>

	<nav class="tab sb-topnav2 navbar navbar-expand; bg-white">
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${pageContext.servletContext.contextPath}/pet/petall"><b>공고</b></a>
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${pageContext.servletContext.contextPath}/shel/shelall"><b>보호소</b></a>
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${pageContext.servletContext.contextPath}/with/withca"><b>위드펫</b></a>
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${pageContext.servletContext.contextPath}/community/clist"><b>커뮤니티</b></a>
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${pageContext.servletContext.contextPath}/notice/nlist"><b>공지사항</b></a>
	</nav>

	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-10 pt-5 ps-4" style="width:80%;">
				<h2 class="mt-1 mb-3"><b>공고 즐겨찾기</b></h2>

				<div class="card mb-4">
					<div class="card-body" style="padding:45px 70px;">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>즐겨찾기번호</th>
									<th>닉네임</th>
									<th>이미지</th>
									<th>보호소 이름</th>
									<th>보호소 주소</th>
									<th>보호소 전화번호</th>
									<th>즐겨찾기 등록일</th>
									<!-- <th>조회수</th> -->
								</tr>
							</thead>

							<tbody>
								<c:forEach var="F_P_DTO" items="${favoritep}" varStatus="status">
									<tr
										onclick="location.href='${pageContext.servletContext.contextPath}/pet/petdetail?method=get&amp;pet_notice_no=${F_P_DTO.pet_notice_no}'"
										style="cursor: pointer">
										<td>${F_P_DTO.favoritep_no}</td>
										<td>${F_P_DTO.nickname}</td>
										<td><img src="${F_P_DTO.popfile}" alt="펫이미지"
											style="height: 100px" /></td>
										<td>${F_P_DTO.careNm}</td>
										<td>${F_P_DTO.careAddr}</td>
										<td>${F_P_DTO.careTel}</td>
										<td>${F_P_DTO.favoritep_reg_date}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
		
					<%@ include file="../import/page-favoritep.jsp"%>	
							
					<div style="text-align: center;">				
					<button type="button" class="btn btn-warning" onclick="back();">이전</button>	
					<button class="btn btn-warning" type="submit" onclick="location.href='${root}/fa/sendfap'">이메일 전송</button>					
				</div>					
				</div>											
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