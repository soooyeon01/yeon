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
<title>보호소</title>
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css"
	rel="stylesheet">
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link
	href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/mypageStyles.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script
	src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/scripts.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
	crossorigin="anonymous"></script>
<script
	src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
<script>
	          $(document).ready(function () {
	              $("#region-select").on("change", function () {
	                  const region = $(this).val();
	                  location.href="${pageContext.servletContext.contextPath}/shel/shelall?region=" + region; 
	              })
	          });
	          function checkLoginAndRedirect(shelter_no) {
			        var email = '${ sessionScope.SESS_EMAIL }';

			        if (email != null && email != "") {
			            window.location.href ="${pageContext.servletContext.contextPath}/shel/sheldetail?method=get&shelter_no="+shelter_no;
			        } else {
			            alert("로그인 후 이용하실 수 있습니다 ^~^");
			            window.location.href = "${pageContext.servletContext.contextPath }/main/main";
			        }
			    }
	          function moveC() {
	 	    	 var email = '${ sessionScope.SESS_EMAIL }';

	 		        if (email != null && email != "") {
	 		            window.location.href ="${root}/community/clist";
	 		        } else {
	 		            alert("로그인 후 이용하실 수 있습니다 ^~^");
	 		        }
	 		    }
	          
	       </script>
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
</style>
</head>
<body class="sb-nav-fixed bgcolor">
	<nav
		class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow">
		<!-- 로그인 로그아웃 마이페이지 반응형 -->
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
	
	<!-- 상단바 메뉴 -->
	<nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
	          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/pet/petall"><b>공고</b></a> 
	          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shel/shelall"><b>보호소</b></a>
	          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withca"><b>위드펫</b></a>
	          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="javascript:void(0)" onclick="moveC();"><b>커뮤니티</b></a>
	          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
          </nav>

	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-10 pt-5 ps-4" style="width: 80%;">
				<h2 class="mt-1 mb-3">
					<b><a href="${root}/shel/shelall">보호소</a></b>
				</h2>

				<div class="card mb-4">
					<div class="card-body" style="padding:45px 70px 10px 70px;">
						<select id="region-select">
							<option value="">지역 선택</option>
							<option value="서울특별시"
								<c:if test='${ param.region eq "서울특별시" }'>selected="selected"</c:if>>서울특별시</option>
							<option value="부산광역시"
								<c:if test='${ param.region eq "부산광역시" }'>selected="selected"</c:if>>부산광역시</option>
							<option value="대구광역시"
								<c:if test='${ param.region eq "대구광역시" }'>selected="selected"</c:if>>대구광역시</option>
							<option value="인천광역시"
								<c:if test='${ param.region eq "인천광역시" }'>selected="selected"</c:if>>인천광역시</option>
							<option value="광주광역시"
								<c:if test='${ param.region eq "광주광역시" }'>selected="selected"</c:if>>광주광역시</option>
							<option value="대전광역시"
								<c:if test='${ param.region eq "대전광역시" }'>selected="selected"</c:if>>대전광역시</option>
							<option value="울산광역시"
								<c:if test='${ param.region eq "울산광역시" }'>selected="selected"</c:if>>울산광역시</option>
							<option value="세종특별자치시"
								<c:if test='${ param.region eq "세종특별자치시" }'>selected="selected"</c:if>>세종특별자치시</option>
							<option value="경기도"
								<c:if test='${ param.region eq "경기도" }'>selected="selected"</c:if>>경기도</option>
							<option value="강원도"
								<c:if test='${ param.region eq "강원도" }'>selected="selected"</c:if>>강원도</option>
							<option value="충청북도"
								<c:if test='${ param.region eq "충청북도" }'>selected="selected"</c:if>>충청북도</option>
							<option value="충청남도"
								<c:if test='${ param.region eq "충청남도" }'>selected="selected"</c:if>>충청남도</option>
							<option value="전라북도"
								<c:if test='${ param.region eq "전라북도" }'>selected="selected"</c:if>>전라북도</option>
							<option value="전라남도"
								<c:if test='${ param.region eq "전라남도" }'>selected="selected"</c:if>>전라남도</option>
							<option value="경상북도"
								<c:if test='${ param.region eq "경상북도" }'>selected="selected"</c:if>>경상북도</option>
							<option value="경상남도"
								<c:if test='${ param.region eq "경상남도" }'>selected="selected"</c:if>>경상남도</option>
							<option value="제주특별자치도"
								<c:if test='${ param.region eq "제주특별자치도" }'>selected="selected"</c:if>>제주특별자치도</option>
						</select>
						<div>
							<br>
						</div>

						<div id="shel-container">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>보호소 이름</th>
										<th>보호소 주소</th>
										<th>보호소 전화번호</th>
										<th>평일운영시작시간</th>
										<th>평일운영종료시간</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="S_DTO" items="${ response.shelList }">
										<tr onclick="checkLoginAndRedirect(${S_DTO.shelter_no});"
											style="cursor: pointer">
											<!-- pageScope에 vo가 생성되었다.  -->
											<td>${S_DTO.careNm}</td>
											<td>${S_DTO.careAddr}</td>
											<td>${S_DTO.careTel}</td>
											<td>${S_DTO.weekOprStime}</td>
											<td>${S_DTO.weekOprEtime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div style="padding:0px 70px 25px 0px;">
					<%@ include file="../import/page-shelter.jsp"%>
					</div>
				</div>
			</div>
		</main>
	</div>
<!-- 푸터 고정 -->
   <footer class="bgcolor" style="position : absolute;">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
               <div class="text-muted" style="padding-top:50px; padding-bottom:20px;">Website 2023 &copy; Happy OkDogCat</div>
            </div>
         </div>
    </footer>

</body>
</html>