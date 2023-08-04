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
<title>보호소 목록 상세</title>
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
	font-family: 'NanumSquareNeo';
	background-color: #f9f8f3;
}

.img_fa1 {
	width: 200px;
	height: 200px;
	border: 0;
	margin-top:-60px;
}

.img_fa2 {
	width: 200px;
	height: 200px;
	display: none;
	margin-top:-60px;
	padding: 0;
	border: none;
	background: none;
}

table.table.table-bordered {
	width: 55%;
	margin-top: 50px;
	margin-left: 280px;
}
table.table.table-bordered th {
	width: 30%;
	margin-top: 50px;
	margin-left: 350px;
	background-color: #feeaa5;
	text-align:center;
}
/* 모달 스타일 */
.modal_wrap {
	display: none;
	position: fixed;
	z-index: 2000;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #f8de67;
	padding: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.black_bg {
	display: none;
	position: absolute;
	content: "";
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	top: 0;
	left: 0;
	z-index: 1;
}

.modal_close {
	width: 26px;
	height: 26px;
	position: absolute;
	top: -30px;
	right: 0;
}

.modal_close>a {
	display: block;
	width: 100%;
	height: 100%;
	background: url(https://img.icons8.com/metro/26/000000/close-window.png);
	text-indent: -9999px;
}
</style>
<script>
		function getInitialFavoriteStatus() {
		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/fa/favorites",
		        async:false,
		        type: "POST",
		        success: function(data) {
		            applyImageDisplayStatus(data);
		            
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            console.log(jqXHR);
		            console.log(textStatus);
		            console.log(errorThrown);
		            alert("오류가 발생했습니다. 즐겨찾기 상태를 가져오는 데 실패했습니다.");
		        }
		    });
		}
		  function applyImageDisplayStatus(favoriteStatus) {
		        $('.img_fa1, .img_fa2').each(function () {
		            var img_fa1 = $(this).closest('label').find('.img_fa1');
		            var img_fa2 = $(this).closest('label').find('.img_fa2');
		            var key = parseInt($(this).closest('label').find('.img_fa1').attr('data-value'));
		            
		            if (favoriteStatus.indexOf(key) >= 1) { // 좋아요 정보가 있는 경우
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
		    $('.img_fa1, .img_fa2').on('click', function () {
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

		

		$(document).ready(function () {
		    applyImageCheckboxStyle();
		    getInitialFavoriteStatus(); // Load stored status on page load

		    $(".img_fa1, .img_fa2").on("click", function () {
		        var img_fa1 = $(this).closest('label').find('.img_fa1');
		        var img_fa2 = $(this).closest('label').find('.img_fa2');
		        var isChecked = img_fa2.is(':visible');

		        if (isChecked) {
		            // 체크가 선택된 경우
		            sendFavorites(img_fa1);
		        } else {
		            // 체크가 해제된 경우
		            removeFavorites(img_fa2);
		        }
		    });
		});
		
		function sendFavorites(img_fa1) {
		    var favorites = img_fa1.data("value");
		    console.log(favorites);
		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/shel/registershel",
		        type: "POST",
		        data: {
		           shelter_no: favorites
		        },
		        dataType: "json",
		        success: function(data) {
		        	console.log(data);
		            if (data.result === 1) {
		                alert("등록되었습니다.");
		                
		            }else{
		            	alert("등록되었습니다.");
		            	
		            }
		          
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            console.log(jqXHR);
		            console.log(textStatus);
		            console.log(errorThrown);
		            alert("오류가 발생했습니다. 다시 시도해주세요.");
		        }
		    });
		}
		function removeFavorites(img_fa2) {
		    var favorites = img_fa2.data("value");

		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/shel/removeshel",
		        type: "POST",
		        data: {
		            shelter_no: favorites
		        },
		        dataType: "json",
		        success: function(data) {
		            if (data.result === 1) {
		                alert("삭제되었습니다.");
		                console.log(data.result);
		            } else {
		                alert("삭제되었습니다.");
		              
		            }
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            console.log(jqXHR);
		            console.log(textStatus);
		            console.log(errorThrown);
		            alert("오류가 발생했습니다. 다시 시도해주세요.");
		        }
		    });
		}
		function back(){
			window.location = document.referrer;
		}
			  </script>

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

	<nav class="tab sb-topnav2 navbar navbar-expand; bg-white">
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/pet/petall"><b>공고</b></a> 
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/shel/shelall"><b>보호소</b></a> 
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/with/withca"><b>위드펫</b></a> 
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/community/clist"><b>커뮤니티</b></a> 
		<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link"
			href="${root}/notice/nlist"><b>공지사항</b></a>
	</nav>

	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-3 pt-3">
				<h1 class="mt-1">보호소 목록 상세</h1>

				<div class="card mb-4">
					


					<div class="card-body">
						<p>
							<button type="button" class="btn btn-warning" onclick="back();">목록</button>
						</p>
						<table class="table table-bordered">
							<c:forEach var="S_DTO" items="${sheldetailList}">

								<div style="float: right;">
									<c:set var="isLiked" value="false" />
									<c:forEach var="likedId" items="${fasList}">
										<c:if
											test="${!isLiked and likedId.shelter_no == S_DTO.shelter_no}">
											<c:set var="isLiked" value="true" />
										</c:if>
									</c:forEach>

									<label> <input type="checkbox" class="image-checkbox"
										id="fa" name="favorite"
										style="transform: scale(4); margin: 5px; display: none;"
										value="${S_DTO.shelter_no}"> <img class="img_fa1"
										name="favorite" data-value="${S_DTO.shelter_no}"
										src="../resources/image/fa1.png"
										style="${isLiked ? 'display:none;' : ''}"> <img
										class="img_fa2" name="favorite"
										data-value="${S_DTO.shelter_no}"
										src="../resources/image/fa3.gif"
										style="${!isLiked ? 'display:none;' : ''}">
									</label>
								</div>
								
								<tr>
									<th>보호소 이름</th>
									<c:set var="addressNm" value="${S_DTO.careNm}" />
									<td>${S_DTO.careNm}</td>
								</tr>
								<tr>
									<th>보호소 유형</th>
									<td>${S_DTO.divisionNm}</td>
								</tr>
								<tr>
									<th>구조대상동물</th>
									<td>${S_DTO.saveTrgtAnimal}</td>
								</tr>
								<tr>
									<th>보호소 주소</th>
									<c:set var="address" value="${S_DTO.careAddr}" />
									<td>${S_DTO.careAddr}
										<button type="button" class="btn btn-warning" id="modal_btn">지도보기</button>
										<div class="black_bg"></div>
										<div class="modal_wrap" style="display: block;">
											<div class="modal_close">
												<a href="#">close</a>
											</div>
											<div>
												<div id="map" style="width: 700px; height: 550px;"></div>

												<script type="text/javascript"
													src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db38443adad424d348cb3fedd60e5b26&libraries=services"></script>
												<script>
		                                                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		                                                    mapOption = {
		                                                        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		                                                        level: 3 // 지도의 확대 레벨
		                                                    };  
		                                             
		                                                // 지도를 생성
		                                                var map = new kakao.maps.Map(mapContainer, mapOption); 
		                                                
		                                                setTimeout(function() {
		                                                   console.log('Works!');
		                                                   map.relayout();
		                                                }, 3000);
		                                                
		                                                // 주소-좌표 변환 객체를 생성
		                                                var geocoder = new kakao.maps.services.Geocoder();
		                                                
		                                                
		                                                // 주소로 좌표를 검색
		                                                geocoder.addressSearch('${address}', function(result, status) {
		                                                
		                                                    // 정상적으로 검색이 완료됐으면 
		                                                     if (status === kakao.maps.services.Status.OK) {
		                                                
		                                                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		                                                
		                                                        // 결과값으로 받은 위치를 마커로 표시
		                                                        var marker = new kakao.maps.Marker({
		                                                            map: map,
		                                                            position: coords
		                                                        });
		                                                
		                                                        // 인포윈도우로 장소에 대한 설명을 표시
		                                                        var infowindow = new kakao.maps.InfoWindow({
		                                                        	
		                                                            content: '<div style="font-size:15px; width:150px; text-align:center; padding-top:6px 0;">${addressNm}</div>',
		                                                           
		                                                        });
		                                                        
		                                                        infowindow.open(map, marker);
		                                                        
		                                                        // 지도의 중심을 결과값으로 받은 위치로 이동
		                                                        map.setCenter(coords);
		                                                
		                                                        
		                                                        
		                                                    } 
		                                                });   
		                                                
		                                                var modalBtn = document.getElementById('modal_btn');
		                                                var modalWrap = document.querySelector('.modal_wrap');
		                                                var modalClose = document.querySelector('.modal_close');
		                                                var blackBg = document.querySelector('.black_bg');
		                                                
		
		                                                modalBtn.addEventListener('click', function () {
		                                                  modalWrap.style.display = 'block';
		                                                  blackBg.style.display = 'block';
		                                                });
		
		                                                modalClose.addEventListener('click', function (e) {
		                                                  e.preventDefault();
		                                                  modalWrap.style.display = 'none';
		                                                  blackBg.style.display = 'none';
		                                                });
		
		                                                blackBg.addEventListener('click', function () {
		                                                  modalWrap.style.display = 'block';
		                                                  blackBg.style.display = 'block';
		                                                });
		                                                
		                                                modalWrap.style.display = 'none';
		                                                
		                                                
		                                             </script>
											</div>
										</div>


									</td>
								</tr>
								<tr>
									<th>보호소 전화번호</th>
									<td>${S_DTO.careTel}</td>
								</tr>
								<tr>
									<th>평일운영시작시간</th>
									<td>${S_DTO.weekOprStime}</td>
								</tr>
								<tr>
									<th>평일운영종료시간</th>
									<td>${S_DTO.weekOprEtime}</td>
								</tr>
								<tr>
									<th>평일분양시작시간</th>
									<td>${S_DTO.weekCellStime}</td>
								</tr>
								<tr>
									<th>평일분양종료시간</th>
									<td>${S_DTO.weekCellEtime}</td>
								</tr>
								<tr>
									<th>주말운영시작시간</th>
									<td>${S_DTO.weekendOprStime}</td>
								</tr>
								<tr>
									<th>주말운영종료시간</th>
									<td>${S_DTO.weekendOprEtime}</td>
								</tr>
								<tr>
									<th>주말분양시작시간</th>
									<td>${S_DTO.weekendCellStime}</td>
								</tr>
								<tr>
									<th>주말분양종료시간</th>
									<td>${S_DTO.weekendCellEtime}</td>
								</tr>
								<tr>
									<th>휴무일</th>
									<td>${S_DTO.closeDay}</td>
								</tr>
							</c:forEach>

						</table>




					</div>
				</div>
			</div>

		</main>

	</div>


</body>
</html>

