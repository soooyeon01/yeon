<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.spring.domain.MembersDTO"%>
<%@ page import="com.spring.mapper.MypageMapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>마이페이지</title>
<link
   href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css"
   rel="stylesheet">
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"
   rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
   crossorigin="anonymous"></script>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/scripts.js"></script>
<script
   src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
   crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
   integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
   crossorigin="anonymous"></script>


<script>
   function submitForm(category3) {
        document.getElementById('category3').value = category3;
        document.getElementById('myForm').submit();
    }   
    </script>
<script>

      function logout() {
      if (confirm("로그아웃 하시겠습니까?")) {
      location.href = "${pageContext.servletContext.contextPath}/user/logout";
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
  <!-- inputpwd값 보내기 -->
  function remM() {
      var data = $("form").serialize(); // form 데이터 직렬화
      $.ajax({
         url: "${pageContext.servletContext.contextPath}/mypage/remMC",
         data: "data", // 수정: 직렬화된 form 데이터 전달
         type: "post",
         
         success: function(data, textStatus) {
            console.log(data);
         },
         error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
         }
      });
   }
  <!-- 카테고리 버튼 -->
  $('.fun-btn').on('click', function(event) {
     $(this).toggleClass('start-fun');
     var $page = $('.page');
     $page.toggleClass('color-bg-start')
       .toggleClass('bg-animate-color');

     //change text when when button is clicked

     $(this).hasClass('start-fun') ?
       $(this).text('stop the fun') :
       $(this).text('start the fun');

   });
  </script>
<style>
.nanumB{
   font-family: NanumSquareNeoBold;
}
.deleteMember {
   color: darkgray;
   text-align: right;
}

tr {
   text-align: center;
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
   font-family: 'NanumSquareNeo';
}
.page {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
}


/* add default color for animation start  */


/* toggle this class */

.color-bg-start {
  background-color: salmon;
}

/* toggle class bg-animate-color */

.bg-animate-color {
  animation: random-bg .5s linear infinite;
}

/* add animation to bg color  */

@keyframes random-bg {
  from {
    filter: hue-rotate(0);
  }
  to {
    filter: hue-rotate(360deg);
  }
}

.fun-btn {
  /* change bg color to get different hues    */
  background-color: #feeaa5;
  color: black;
  padding: 1.5em 1em;
  border:none;
  transition: all .3s ease;
  border-radius: 5px;
  letter-spacing: 2px;
  text-transform: uppercase;
  outline: none;
  align-self: center;
  cursor: pointer;
  font-size: 15px;
}

.fun-btn:hover {
  animation: linear infinite, grow 1300ms ease infinite;
  background-color: #ffe27d;
}

.start-fun {
  background-color: #fff !important;
  /* change color of button text when fun is started   */
  color: salmon !important;
}

/* pulsating effect on button */
@keyframes grow {
  0% {
    transform: scale(1);
  }
  14% {
    transform: scale(1.3);
  }
  28% {
    transform: scale(1);
  }
  42% {
    transform: scale(1.3);
  }
  70% {
    transform: scale(1);
  }
}
.container{
   display: grid;
    grid-template-columns: repeat(3, 1fr); /* 3열 그리드 */
    gap: 10px;
}
/* a태그 스타일 */
          a {
         text-decoration-line: none;
         color: inherit;
         }
</style>
</head>
 <body class="sb-nav-fixed bgcolor nanum" > 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
           
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
            
         <!-- 로고 -->              
        <nav class="main bg-white" >
           <a class="mainlogo" onclick="location.href='${root}/main/main'" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
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
          <div class="container-fluid px-10 pt-5 ps-4" style="width:80%;" >
            <h2 class="mt-1 mb-3"><b>위드펫</b></h2>
            </div>

         <div class="card-body">
            <div align="center">
               <form id="myForm" action="${root}/with/withall" method="get">
                  <input type="hidden" id="category3" name="category3">
                  <div class="container nanumB" style="padding:0px 80px">
                     <button class="fun-btn" type="submit" value="카페"
                        onclick="submitForm(this.value)">
                        <i class="fa-solid fa-mug-saucer" style="color: #180c01; weight:30px; height:30px;"></i><br>카페</button>                        
                     <button class="fun-btn" type="submit" value="식당"
                        onclick="submitForm(this.value)">
                        <i class="fa-solid fa-utensils" style="color: #180c01; weight:30px; height:30px;"></i><br>식당</button>
                     <button class="fun-btn" type="submit" value="여행지"
                        onclick="submitForm(this.value)">
                      <i class="fa-solid fa-umbrella-beach" style="color: #180c01; weight:30px; height:30px;"></i><br>여행지</button>
                     <button class="fun-btn" type="submit" value="박물관"
                        onclick="submitForm(this.value)">
                      <i class="fa-solid fa-building-columns" style="color: #180c01; weight:30px; height:30px;"></i><br>박물관</button>
                     <button class="fun-btn" type="submit" value="펜션"
                        onclick="submitForm(this.value)">
                      <i class="fa-solid fa-hotel" style="color: #180c01; weight:30px; height:30px;"></i><br>펜션</button>
                     <button class="fun-btn" type="submit" value="호텔"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-square-h" style="color: #180c01; weight:30px; height:30px;"></i><br>호텔</button>
                     <button class="fun-btn" type="submit" value="동물병원"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-hospital" style="color: #180c01; weight:30px; height:30px;"></i><br>동물병원</button>
                     <button class="fun-btn" type="submit" value="미술관"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-palette" style="color: #180c01; weight:30px; height:30px;"></i><br>미술관</button>
                     <button class="fun-btn" type="submit" value="문예회관"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-school" style="color: #180c01; weight:30px; height:30px;"></i><br>문예회관</button>
                     <button class="fun-btn" type="submit" value="동물약국"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-pills" style="color: #180c01; weight:30px; height:30px;"></i><br>동물약국</button>
                     <button class="fun-btn" type="submit" value="위탁관리"
                        onclick="submitForm(this.value)">
                       <i class="fa-solid fa-bell-concierge" style="color: #180c01; weight:30px; height:30px;"></i><br>위탁관리</button>
                     <button class="fun-btn" type="submit" value="미용"
                        onclick="submitForm(this.value)">
                      <i class="fa-solid fa-bath" style="color: #180c01; weight:30px; height:30px;"></i><br>미용</button>
                  </div>
               </form>
            </div>
         </div>
      </main>
        <footer class="bgcolor" style="position : absolute; bottom :20px;">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
               <div class="text-muted" style="padding-top:20%;">Website 2023 &copy; Happy OkDogCat</div>
            </div>
         </div>
      </footer>
   </div>
   
</body>
</html>