<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.domain.MembersDTO" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>옥독캣</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <link href="${root}/resources/bootstrap/js/bootstrap.min.js"/>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
     	<script>
	     function login(){
	    	  location.href = "${pageContext.servletContext.contextPath}/user/login";
	    	  }
      
	      function mypage(){
	      		location.href = "${pageContext.servletContext.contextPath}/mypage/mypage";
	      }
	      
	      function logout() {
	  		if (confirm("로그아웃 하시겠습니까?")) {
	  		location.href = "${pageContext.servletContext.contextPath}/user/logout";
	  	 	}
	  	}
	      
	      function main(){
	    		location.href = "${pageContext.servletContext.contextPath}/main/main";
	   	 }
	      function logplz() {
	    	  var uid ='<%=(String)session.getAttribute("SESS_EMAIL")%>';
	   
	   			console.log(uid+"뿡");
	    	  if(uid==null){
	    	  alert("로그인해라 조은말로할때...");
	    	  }
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
          .mainpage{
         background-color: #f9f8f3;
          }
          
        </style>
        
    </head>
   <body class="sb-nav-fixed"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
                 <div class="input-group">
                <% String id = (String)session.getAttribute("SESS_EMAIL"); %>
              <%System.out.println(id);%>
              
        <%  if( id != null) { %>
        <div style="margin-top:5px;">♡${sessionScope.SESS_NICKNAME}님 환영합니다♡</div>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="mypage();" style="font-size: 14px;">마이페이지</button>                  
                   
            <%} else{%>
                <button type="button" class="btn" onclick="login();" style="font-size: 14px;">로그인</button>                 
             
            <%}  %>
                </div>
            </form>      
            </nav>
           

           
         <!-- 로고 -->              
       <nav class="main bg-white" >
         <a class="mainlogo" href="${root}/main/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav> 
         <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/pet/petall"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/shel/shelall"><b>보호소</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/with/withca"><b>위드펫</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/community/clist" onclick="logplz();"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/notice/nlist"><b>공지사항</b></a>
            </nav> 
           <!-- 메인 이미지 -->
      <!--  <div id="photomain">             
           		 <img class = "mainbanner" src="../resources/image/mainimg.png" style="width: 1427px;"/>
            </div> -->
               
                
    <main class = "mainpage">

  <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel" data-bs-theme="light">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2" class="active" aria-current="true"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
    </div>
    <div class="carousel-inner" >
      <div class="carousel-item" >
      <img class = "mainbanner" src="../resources/image/mainbanner8.png" style="width: 2000px; height:500px;"/>
     
        <div class="container">        
          <div class="carousel-caption text-start">                                  
          </div>
        </div>
      </div>
      <div class="carousel-item active">
       <img class = "mainbanner" src="../resources/image/mainbanner1.png" style="width: auto; height:500px;"/>
      </div>
      <div class="carousel-item">
      <img class = "mainbanner" src="../resources/image/mainbanner7.png" style="width: auto; height:500px;"/>
        <div class="container">
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
      </button>
  </div>


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <!--  <div class="row">
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>Some representative placeholder content for the three columns of text below the carousel. This is the first column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
      <div class="col-lg-4">
      	
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>Another exciting bit of representative placeholder content. This time, we've moved on to the second column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>And lastly this, the third column of representative placeholder content.</p>
        <p>And lastly this, the third column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
    </div> -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">매년 전국적으로 10만 마리 이상의 유기동물들이 보호소로 구조되고 있습니다.<span class="text-body-secondary"></span></h2>
        <p class="lead">안타깝게도 이 중 절반에 가까운 동물들은 다시 가족을 만나지 못하고 안락사되거나 자연사하고 있습니다.</p>
      </div>
      <div class="col-md-5">
      <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5001.png" style="width: 100%; height:100%;"/>      
<!--         <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500px" height="500px" src="../resources/image/dog1.png" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)"></rect><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
 -->      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading fw-normal lh-1"> 유기동물 입양 정보 <span class="text-body-secondary"></span></h2>
        <p class="lead">전국 유기동물 보호소에 구조된 유기동물 알림 서비스를 통해 입양을 돕고 있습니다.</p>
      </div>
      <div class="col-md-5 order-md-1">
      <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5002.png" style="width: 100%; height:100%;"/>            
<!--         <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)"></rect><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
 -->      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">입양한 유기동물의 건강검진 지원 <span class="text-body-secondary"></span></h2>
        <p class="lead"> 유기동물 입양 후 건강하고 행복한 생활을 위해 다양한 혜택을 지원하고 있습니다.</p>
      </div>
      <div class="col-md-5">
            <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5003.png" style="width: 100%; height:100%;"/>               
<!--         <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)"></rect><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
 -->      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
  <footer class="container">
    <p class="float-end"><a href="#">Back to top</a></p>
    <p>© 2017–2023 Company, Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a></p>
  </footer>
</main>
     
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">OkDogCat &copy; Happy Project</div>

                        </div>
                    </div>
                </footer>
    </body>
</html>