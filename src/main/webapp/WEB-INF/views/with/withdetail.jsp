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
<title>반려동물 동반가능 업체정보 목록 상세</title>
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
	function getInitialFavoriteStatus() {
		$.ajax({
			url : "${pageContext.servletContext.contextPath}/fa/favoritew",

			type : "POST",
			success : function(data) {

				applyImageDisplayStatus(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
				alert("오류가 발생했습니다. 즐겨찾기 상태를 가져오는 데 실패했습니다.");
			}
		});
	}
	function applyImageDisplayStatus(favoriteStatus) {
		$('.img_fa1, .img_fa2').each(
				function() {
					var img_fa1 = $(this).closest('label').find('.img_fa1');
					var img_fa2 = $(this).closest('label').find('.img_fa2');
					var key = parseInt($(this).closest('label')
							.find('.img_fa1').attr('data-value'));

					if (favoriteStatus.indexOf(key) >= 0) { // 좋아요 정보가 있는 경우
						// 좋아요 이미지(img_fa1)를 숨기고 좋아요 취소 이미지(img_fa2)를 표시
						img_fa1.hide();
						img_fa2.show();
					} else { // 좋아요 정보가 없는 경우
						// 좋아요 이미지(img_fa1)를 표시하고 좋아요 취소 이미지(img_fa2)를 숨김
						img_fa1.show();
						img_fa2.hide();
					}
				});
	}
	function applyImageCheckboxStyle() {
		$('.img_fa1, .img_fa2').on('click', function() {
			var img_fa1 = $(this).closest('label').find('.img_fa1');
			var img_fa2 = $(this).closest('label').find('.img_fa2');

			img_fa1.toggle();
			img_fa2.toggle();

			setImageDisplayStatus(img_fa1, img_fa2);
		});
	}

	function setImageDisplayStatus(img_fa1, img_fa2) {
		var key = img_fa1.data('value');
		var visible = img_fa2.is(':visible');
		localStorage.setItem(key, visible);
	}
	$(document).ready(function() {
		applyImageCheckboxStyle();
		getInitialFavoriteStatus();

		$(".img_fa1, .img_fa2").on("click", function() {
			var img_fa1 = $(this).closest('label').find('.img_fa1');
			var img_fa2 = $(this).closest('label').find('.img_fa2');
			var isChecked = img_fa2.is(':visible');

			if (isChecked) {
				// 체크가 선택된 경우
				sendFavoritew(img_fa1);
			} else {
				// 체크가 해제된 경우
				removeFavoritew(img_fa2);
			}
		});
	});
	function sendFavoritew(img_fa1) {
		var favoritew = img_fa1.data("value");

		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/with/registerwith",
					type : "POST",
					data : {
						with_pet_no : favoritew
					},
					dataType : "json",
					success : function(data) {
						if (data.result === 1) {
							alert("등록되었습니다.");

						} else {
							alert("등록되었습니다.");

						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR);
						console.log(textStatus);
						console.log(errorThrown);
						alert("오류가 발생했습니다. 다시 시도해주세요.");
					}
				});
	}
	function removeFavoritew(img_fa2) {
		var favoritew = img_fa2.data("value");

		$.ajax({
			url : "${pageContext.servletContext.contextPath}/with/removewith",
			type : "POST",
			data : {
				with_pet_no : favoritew
			},
			dataType : "json",
			success : function(data) {
				if (data.result === 1) {
					alert("삭제되었습니다.");

				} else {
					alert("삭제되었습니다.");

				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
				alert("오류가 발생했습니다. 다시 시도해주세요.");
			}
		});
	}
	function back() {
		window.location = document.referrer;
	}
</script>

<style>
.nanum {
	font-family: 'NanumSquareNeo';
}

.nanumB {
	font-family: 'NanumSquareNeoBold';
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

.bgcolor {
	background-color: #f9f8f3;
}

.img_fa1 {
	width: 100px;
	height: 100px;
	border: 0;
}

.img_fa2 {
	width: 100px;
	height: 100px;
	display: none;
	margin: 0;
	padding: 0;
	border: none;
	background: none;
}

table.table.table-bordered {
	width: 50%;
	margin-top: 50px;
	margin-left: 350px;
}
table.table.table-bordered th{
	background-color:#feeaa5;
}
</style>
</head>
<body class="sb-nav-fixed nanum">
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
	<div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-3 pt-3">
					<h1 class="mt-1">위드펫 상세</h1>

					<div class="card mb-4">
						<div class="card-header"></div>
						<div class="card-body">

							<button type="button" class="btn btn-warning" onclick="back();">목록</button>
							<table class="table table-bordered">
								<c:forEach var="W_DTO" items="${ withdetailList }">
									<div style="float: right;">
										<c:set var="isLiked" value="false" />
										<c:forEach var="likedId" items="${fawList}">
											<c:if
												test="${!isLiked and likedId.with_pet_no == W_DTO.with_pet_no}">
												<c:set var="isLiked" value="true" />
											</c:if>
										</c:forEach>
										<label> <input type="checkbox" class="image-checkbox"
											id="fa" name="favorite"
											style="transform: scale(4); margin: 5px; display: none;"
											value="${W_DTO.with_pet_no}"> <img class="img_fa1"
											name="favorite" data-value="${W_DTO.with_pet_no}"
											src="../resources/image/fa1.png"
											style="${isLiked ? 'display:none;' : ''}"> <img
											class="img_fa2" name="favorite"
											data-value="${W_DTO.with_pet_no}"
											src="../resources/image/fa3.gif"
											style="${!isLiked ? 'display:none;' : ''}">
										</label>
									</div>
									<tr>
										<th>문화시설 이름</th>
										<c:set var="addressNm" value="${W_DTO.building}" />
										<td>${W_DTO.building}</td>
									</tr>
									<tr>
										<th>문화시설 유형</th>
										<td>${W_DTO.category3}</td>
									</tr>
									<tr>
										<th>도로명 주소</th>
										<c:set var="address" value="${W_DTO.road}" />
										<td>${W_DTO.road}</td>
									</tr>
									<tr>
										<th>전화번호</th>
										<td>${W_DTO.tel}</td>
									</tr>
									<tr>
										<th>홈페이지</th>
										<td>${W_DTO.homepage}</td>
									</tr>
									<tr>
										<th>휴무일</th>
										<td>${W_DTO.day_off}</td>
									</tr>
									<tr>
										<th>운영시간</th>
										<td>${W_DTO.hour}</td>
									</tr>
									<tr>
										<th>주차가능여부</th>
										<td>${W_DTO.parking}</td>
									</tr>
									<tr>
										<th>반려동물 동반 가능정보</th>
										<td>${W_DTO.with_pet_info}</td>
									</tr>
									<tr>
										<th>반려동물 전용 정보</th>
										<td>${W_DTO.only_pet_info}</td>
									</tr>
									<tr>
										<th>입장 가능 동물 크기</th>
										<td>${W_DTO.pet_size}</td>
									</tr>
									<tr>
										<th>반려동물 제한사항</th>
										<td>${W_DTO.pet_limit}</td>
									</tr>
									<tr>
										<th>장소(실내)여부</th>
										<td>${W_DTO.inside}</td>
									</tr>
									<tr>
										<th>장소(실외)여부</th>
										<td>${W_DTO.outside}</td>
									</tr>
									<tr>
										<th>애견동반추가요금</th>
										<td>${W_DTO.extra}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<p style="margin-top: -12px">
					<em class="link"> <a href="javascript:void(0);"
						onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
							혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요. </a>
					</em>
				</p>
				<div id="map" style="width: 500px; height: 350px;"></div>

				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db38443adad424d348cb3fedd60e5b26&libraries=services"></script>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};

					// 지도를 생성
					var map = new kakao.maps.Map(mapContainer, mapOption);

					// 주소-좌표 변환 객체를 생성
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색
					geocoder
							.addressSearch(
									'${address}',
									function(result, status) {

										// 정상적으로 검색이 완료됐으면 
										if (status === kakao.maps.services.Status.OK) {

											var coords = new kakao.maps.LatLng(
													result[0].y, result[0].x);

											// 결과값으로 받은 위치를 마커로 표시
											var marker = new kakao.maps.Marker(
													{
														map : map,
														position : coords
													});

											// 인포윈도우로 장소에 대한 설명을 표시
											var infowindow = new kakao.maps.InfoWindow(
													{
														content : '<div style="width:150px;text-align:center;padding:6px 0;">${addressNm}</div>'
													});
											infowindow.open(map, marker);

											// 지도의 중심을 결과값으로 받은 위치로 이동
											map.setCenter(coords);
										}
									});
				</script>
			</main>
			<footer class="py-4 bg-light mt-auto"> </footer>
		</div>
	</div>

</body>
</html>

