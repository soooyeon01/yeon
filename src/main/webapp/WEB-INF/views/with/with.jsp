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
<title>위드펫</title>
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
			//지역+카테고리
	          $(document).ready(function () {
	              $("#region-select").on("change", function () {
	                  const region = $(this).val();
	                  const category3 = '${param.category3}';
	                  const typeSelect = document.getElementById("form-control"); 
	                  const type = typeSelect.options[typeSelect.selectedIndex].value; 
	                  const keyword = '${param.keyword}';
	                  location.href="${root}/with/withall?region=" + region + "&category3="+category3 + "&type="+type+"&keyword="+keyword; 	                 
	              })
	          });  	          
	        		
	          function getSearchList() {
	        	const keyword = $("input[name='keyword']").val(); //keyword 값 호출
	        	   if (keyword == null || keyword.trim().length == 0) { //keyword 값 유무확인
	        	        alert("검색어를 입력하세요.");
	        	        return false; // 함수를 종료하여 폼 제출 방지(새로고침 x)
	        	    }
	        	    document.forms["search-form"].submit(); //keyword 값 있을 시 폼 제출
	       		}
	          function checkLoginAndRedirect(with_pet_no) {
			        var email = '${ sessionScope.SESS_EMAIL }';

			        if (email != null && email != "") {
			            window.location.href ="${root}/with/withdetail?with_pet_no="+with_pet_no;
			        } else {
			            alert("로그인 후 이용해주세요");
			            window.location.href = "${pageContext.servletContext.contextPath }/main/main";
			        }
			    }
	       </script>
<style>


a {
	text-decoration-line: none;
	color: inherit;
}

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

table.table.table-bordered th {
	background-color: #feeaa5;
}

.bgcolor {
	font-family: 'NanumSquareNeo';
	background-color: #f9f8f3;
}

</style>
</head>
<body class="sb-nav-fixed bgcolor">
	<nav
		class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow">
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
			<div class="input-group">
				<%
				String email = (String) session.getAttribute("SESS_EMAIL");
				%>
				<%
				System.out.println(email);
				%>

				<%
				if (email != null) {
				%>
				<div style="margin-top: 5px;">♡${sessionScope.SESS_NICKNAME}님
					환영합니다♡</div>
				<button type="button" class="btn" onclick="logout();"
					style="font-size: 14px;">로그아웃</button>
				<button type="button" class="btn"
					onclick="location.href='${root}/mypage/mypage'"
					style="font-size: 14px;">마이페이지</button>
				<%
				} else {
				%>
				<button type="button" class="btn"
					onclick="location.href='${root}/user/login'"
					style="font-size: 14px;">로그인</button>

				<%
				}
				%>
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
			<div class="container-fluid px-10 pt-5 ps-4" style="width: 88%;">
				<h2 class="mt-1">
					<b><a href="${root}/with/withall?category3=${param.category3}">${param.category3}</a></b>
				</h2>

				<div class="card mb-4">
					<div class="card-body">
						<select id="region-select" name="region-select">
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

						<div id="with-container">
							<table class="table table-bordered">

								<thead>
									<tr>
										<th>문화시설 이름</th>
										<th>도로명 주소</th>
										<th>전화번호</th>
										<th>운영시간</th>
										<th>반려동물 동반 가능정보</th>
										<th>반려동물 전용 정보</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="W_DTO" items="${response.withList}">
										<tr onclick="checkLoginAndRedirect(${W_DTO.with_pet_no});"
											style="cursor: pointer">
											<!-- pageScope에 vo가 생성되었다.  -->

											<td>${W_DTO.building}</td>
											<td>${W_DTO.road}</td>
											<td>${W_DTO.tel}</td>
											<td>${W_DTO.hour}</td>
											<td>${W_DTO.with_pet_info}</td>
											<td>${W_DTO.only_pet_info}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty response.withList}">
										<tr>
											<td colspan="6" style="text-align: center;">검색된 결과가
												없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>

							<div class="container">
								<div class="row">
									<div class="col-md-6">
									<form class="form" method="get" name="search-form"
										action="${root}/with/withall" autocomplete="off" >
										<table id="table">
											<tr>
												<td><select id="form-control" class="form-control"
													name="type">
														<option value="allsearch"
															<c:if test='${ param.type eq "allsearch" }'>selected="selected"</c:if>>전체검색</option>
														<option value="building"
															<c:if test='${ param.type eq "building" }'>selected="selected"</c:if>>건물명</option>
														<option value="road"
															<c:if test='${ param.type eq "road" }'>selected="selected"</c:if>>주소</option>
												</select></td>

												<td><input type="text" class="form-control"
													placeholder="검색어 입력" name="keyword"
													value="${SearchCriteria.keyword}"></td>

												<td><button type="submit"
														onclick="return getSearchList();" class="btn btn-success">검색</button></td>
											</tr>
										</table>
					
										<input type="hidden" name="category3"
											value="${param.category3}">
									</form>
								</div>
								 <div class="col-md-6 d-flex justify-content-end">
								 	<%@ include file="../import/page-with_pet.jsp"%>
								 </div>
							</div>
							
						</div>
					</div>
						
					</div>
				</div>
			</div>
		</main>
	</div>

</body>
</html>