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
        <title>위드펫</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
		<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/scripts.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
		<script src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/datatables-simple-demo.js"></script>
		<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
		<script>
		    $(document).ready(function () {
		        $("#region-select").on("change", function () {
		            const region = $(this).val();
		            // 서버로 데이터를 보낼 Ajax 호출을 작성합니다
		            $.ajax({
		                url: "${pageContext.servletContext.contextPath}/with/withselect", // Replace this with your server endpoint
		                type: "POST",
		                data: {
		                    region: region
		                },
		                dataType:"json",
		                success:function (data, textStatus) {
		                    console.log("succedss");
							console.log(data.withList.length);
							if (data.withList && data.withList.length > 0) {
							    const withs = data.withList;

							    // 테이블 레이아웃 먼저 생성
							    let withHtml = `
							        <table class="table">
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
							            <tbody>`;

							    // 데이터를 테이블 로우로 추가
							    for (let i = 0; i < 10; i++) {
							        let withpet = withs[i];

							        withHtml += `
							            <tr>
							                <td><a href="withdetail?with_pet_no=`+withpet.with_pet_no+`">`+withpet.building+`</a></td>
							                <td><a href="withdetail?&with_pet_no=`+withpet.with_pet_no+`">`+withpet.road+`</a></td>
							                <td><a href="withdetail?&with_pet_no=`+withpet.with_pet_no+`">`+withpet.tel+`</a></td>
							                <td><a href="withdetail?&with_pet_no=`+withpet.with_pet_no+`">`+withpet.hour+`</a></td>
							                <td><a href="withdetail?&with_pet_no=`+withpet.with_pet_no+`">`+withpet.with_pet_info+`</a></td>
							                <td><a href="withdetail?&with_pet_no=`+withpet.with_pet_no+`">`+withpet.only_pet_info+`</a></td>
							            </tr>`;
							    }

							    // 테이블 태그 닫기
							    withHtml += `</tbody></table>`;

							    // 생성한 테이블 HTML을 with_container로 설정된 div 요소에 추가
							   document.getElementById('with-container').innerHTML = withHtml;
							
			                    // 지역 선택에 따른 동물 목록을 업데이트합니다.
			                   // $("#with-container .container").html(withHtml);
			                } else {
			                    // 검색된 동물이 없는 경우에는 '검색된 동물이 없습니다.' 메시지를 출력합니다.
			                    $("#with-container .container").html("<p>검색된 동물이 없습니다.</p>");
			                }
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                console.log(jqXHR);
			                console.log(textStatus);
			                console.log(errorThrown);
			            }
			        });
			    });
			});
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
                <button type="button" class="btn" onclick="location.href='${root}/user/login'"  style="font-size: 14px;">로그인</button>                 
             
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
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/pet/petall"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/shel/shelall"><b>보호소</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/with/withall"><b>위드펫</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/community/clist"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/notice"><b>공지사항</b></a>
   
            </nav>
        
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-3 pt-3">
                        <h1 class="mt-1">위드펫</h1>
                      
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                              
                            </div>
                            <div class="card-body">
                            <select id="region-select">
							  <option value="">지역 선택</option>
							  <option value="서울특별시">서울특별시</option>
							  <option value="부산광역시">부산광역시</option>
							  <option value="대구광역시">대구광역시</option>
							  <option value="인천광역시">인천광역시</option>
							  <option value="광주광역시">광주광역시</option>
							  <option value="대전광역시">대전광역시</option>
							  <option value="울산광역시">울산광역시</option>
							  <option value="세종특별자치시">세종특별자치시</option>
							  <option value="경기도">경기도</option>
							  <option value="강원도">강원도</option>
							  <option value="충청북도">충청북도</option>
							  <option value="충청남도">충청남도</option>
							  <option value="전라북도">전라북도</option>
							  <option value="전라남도">전라남도</option>
							  <option value="경상북도">경상북도</option>
							  <option value="경상남도">경상남도</option>
							  <option value="제주특별자치도">제주특별자치도</option>
							</select>
                            
                            
                            
                           	<div id="with-container">
                           		 <table class="table">
	                                    <thead>
	                                        <tr>
	                                            
	                                            <th>문화시설 이름</th>
	                                            <th>도로명 주소</th>
	                                            <th>전화번호 </th>
	                                            <th>운영시간 </th>
	                                            <th>반려동물 동반 가능정보 </th>
	                                            <th>반려동물 전용 정보 </th>
	                                            
	                                            <!-- <th>조회수</th> -->
	                                        </tr>
	                                    </thead>
	                                   
	                                    <tbody>
	                                    	<c:forEach var="W_DTO" items="${withList}" >
											<tr>
												<!-- pageScope에 vo가 생성되었다.  -->
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.building}
												</a>
												</td>
												
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?&with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.road}
												</a>
												</td>
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?&with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.tel}
												</a>
												</td>
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?&with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.hour}
												</a>
												</td>
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?&with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.with_pet_info}
												</a>
												</td>
												<td>
												<a href ="${pageContext.servletContext.contextPath}/with/withdetail?&with_pet_no=${W_DTO.with_pet_no}" >
												${W_DTO.only_pet_info}
												</a>
												</td>
												
												
											
											</tr>
											</c:forEach>
	                                    </tbody>
	                                </table>
	                              </div>
	                              
	                            </div>
	                            
						<%@ include file="../import/page-with_pet.jsp" %>
                        </div>
                    </div>
                </main>
		


					
				</div>
			
		

	

</body>
</html>