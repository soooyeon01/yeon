<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>유기동물 공고 상세</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
		<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/scripts.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
		<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/datatables-simple-demo.js"></script>
		<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
		<script>
		function getInitialFavoriteStatus() {
		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/pet/get_initial_favorite_status",
		        type: "GET",
		        dataType: "json",
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
		    $('.img_fa1').each(function () {
		        var img_fa1 = $(this);
		        var img_fa2 = $(this).closest('label').find('.img_fa2');
		        var key = img_fa1.data('value');

		        if (favoriteStatus[key]) {
		            img_fa1.hide();
		            img_fa2.show();
		        } else {
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

		function getImageDisplayStatus() {
		    $('.img_fa1').each(function () {
		        var img_fa1 = $(this);
		        var img_fa2 = $(this).closest('label').find('.img_fa2');
		        var key = img_fa1.data('value');
		        var storedStatus = localStorage.getItem(key);

		        if (storedStatus === 'true') {
		            img_fa1.hide();
		            img_fa2.show();
		        } else {
		            img_fa1.show();
		            img_fa2.hide();
		        }
		    });
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
		            sendFavoritep(img_fa1);
		        } else {
		            // 체크가 해제된 경우
		            removeFavoritep(img_fa2);
		        }
		    });
		});
		
		function sendFavoritep(img_fa1) {
		    var favoritep = img_fa1.data("value");
		    console.log(favoritep);
		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/pet/registerpet",
		        type: "POST",
		        data: {
		           pet_notice_no: favoritep
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
		function removeFavoritep(img_fa2) {
		    var favoritep = img_fa2.data("value");

		    $.ajax({
		        url: "${pageContext.servletContext.contextPath}/pet/removepet",
		        type: "POST",
		        data: {
		            pet_notice_no: favoritep
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
				
		<style>
		
		  a:hover{
		                background-color: #feeaa5;
		            }
		            .main{
		            padding-top: 0.7cm;
		            padding-left: 1.0cm;
		            padding-right : 1.5cm;
		            padding-bottom : 3cm;
		            height: 120px;
		            }         
		            .bg-yellow {
						  --bs-bg-opacity: 1;
						  background-color: #feeaa5 !important;
					}
					.main1{
					border-bottom : 1px solid #645326;
					 padding-bottom : 2px;
					 padding-top : 2px;
					}
					.tab{
						 padding-bottom : 0;
						 padding-top : 0;
						border-bottom : 1px solid #645326;
						border-top : 1px solid #645326;
					}
					
					.img_main{
					width: 60%;
				    margin: 0px auto;
				    display: block;
				    width: 250px; height: 90px;
				    }
				    .bgcolor{
				   background-color: #f9f8f3;
				    }
				    .img_fa1 {
				    	width: 200px; 
				    	height: 200px;
				    	border:0;
				    }
				    .img_fa2{
				    	width: 200px; 
				    	height: 200px;
				    	display:none;
				    	margin: 0;
						padding: 0;
						border: none;
						background: none;
				    }
				    table.table.table-bordered {
					    width: 40%;
					    margin-top:50px;
					   	margin-left:350px;
					    
					  }
				    #image-container {
					  
					  text-align:center;
					 
					  
					}
					.img{
						
						display:inline;
						
					}
		</style>
		</head>
    <body class="sb-nav-fixed bgcolor"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
              <div class="input-group">
                <% String email = (String)session.getAttribute("SESS_EMAIL"); %>
              <%System.out.println(email);%>
            <%  if( email != null) { %>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="location.href='${root}/mypage/mypage'" style="font-size: 14px;">마이페이지</button>                  
            <%} else{%>
                <button type="button" class="btn" onclick="location.href='${root}/user/login'" style="font-size: 14px;">로그인</button>                 
             
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
        <nav class="main bg-white" >
         <a class="mainlogo" href="${root}/main/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
        <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/pet/petall"><b>공고</b></a> 
            <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shel/shelall"><b>보호소</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withca"><b>위드펫</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
        </nav>
        
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-3 pt-3">
                        <h1 class="mt-1">유기동물 공고 상세</h1>
                      
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                              
                            </div>
                            <div class="card-body">
                      			
						
                           		  
	                                    	<c:forEach var="P_DTO" items="${ petdetailList }">
												<div style="float:right;">
				                      				 <label>
														 <input type="checkbox" class="image-checkbox" id="fa" name="favorite" style="transform:scale(4); margin:5px; display:none;" value="${P_DTO.pet_notice_no}">
														 <img class="img_fa1" name="favorite" data-value="${P_DTO.pet_notice_no}" src="../resources/image/fa1.png">
														 <img class="img_fa2" name="favorite" data-value="${P_DTO.pet_notice_no}" src="../resources/image/fa3.gif" style="display:none;">
													</label>
				                      			
				                      			</div>
				                      			<p><button type="button" class="btn btn-warning" onclick="back();">목록</button></p>	
				                      			<div id="image-container">
				                      				<img class="img" src="${P_DTO.popfile}" alt="펫이미지" style="width:500px;height:500px;"/>
												</div>
												<table class="table table-bordered" >
												<tr >
													<th style="font-size:15px; ">접수일</th>
													<td style="font-size:20px; ">${P_DTO.happenDt}</td>
												</tr>
												<tr>
													<th style="font-size:15px; ">발견장소</th>
													<td style="font-size:20px; ">${P_DTO.happenPlace}</td>
													
												</tr>
												<tr>
													<th style="font-size:15px; ">공고번호</th>
													<td style="font-size:20px; ">${P_DTO.noticeNo}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">공고시작일</th>		
													<td style="font-size:20px;">${P_DTO.noticeSdt}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">공고종료일</th>		
													<td style="font-size:20px;">${P_DTO.noticeEdt}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">품종</th>
													<td style="font-size:20px;">${P_DTO.kindCd}</td>
												</tr>
												<tr>
												
													<th style="font-size:15px;">상태</th>
													<td style="font-size:20px;">${P_DTO.processState}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">색상</th>
													<td style="font-size:20px;">${P_DTO.colorCd}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">성별</th>
													<td style="font-size:20px;">${P_DTO.sexCd} </td>
												</tr>	
												<tr>
													<th style="font-size:15px;">중성화여부</th>
													<td style="font-size:20px;">${P_DTO.neuterYn}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">나이</th>		
													<td style="font-size:20px;">${P_DTO.age}</td>
												</tr>
												<tr>	
													<th style="font-size:15px;">특징</th>
													<td style="font-size:20px;">${P_DTO.specialMark}</td>
												</tr>
												<tr>			
													<th style="font-size:15px;">체중</th>
													<td style="font-size:20px;">${P_DTO.weight}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">보호소이름</th>
													<td style="font-size:20px;">${P_DTO.careNm}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">보호장소</th>
													<td style="font-size:20px;">${P_DTO.careAddr}</td>
												</tr>
												<tr>
													<th style="font-size:15px;">보호소 전화번호</th>
													<td style="font-size:20px;">${P_DTO.careTel}</td>
												</tr>
												
												
												</table>
												
											
											</c:forEach>
	                                </div>
                        </div>
                    </div>
                </main>
            </div>
    </body>
</html>

