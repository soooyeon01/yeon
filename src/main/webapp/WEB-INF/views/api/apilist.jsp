<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.spring.domain.MembersDTO"%>
<%@ page import="com.spring.mapper.MypageMapper"%>
<%@ page import="com.spring.controller.UserController"%>
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
<title>회원 리스트</title>
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet"> <!-- 폰트 -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "${root}/user/logout";
		}
	}
</script>

		<!-- 폰트 -->
	     <style type="text/css">
		.nanum{ font-family: 'NanumSquareNeo'; }					
		</style>

<style>
.ttt th{
	background-color: #feeaa5;
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
}
.btn-3d {
			  position: relative;
			  display: inline-block;
			  font-size: 22px;
			  padding: 20px 60px;
			  color: white;
			  margin: 20px 10px 10px;
			  border-radius: 6px;
			  text-align: center;
			  transition: top .01s linear;
			  text-shadow: 0 1px 0 rgba(0,0,0,0.15);
			}
		.btn-3d.red {
		  background-color: #e74c3c;
		  box-shadow: 0 0 0 1px #c63702 inset,
		        0 0 0 2px rgba(255,255,255,0.15) inset,
		        0 8px 0 0 #C24032,
		        0 8px 0 1px rgba(0,0,0,0.4),
		        0 8px 8px 1px rgba(0,0,0,0.5);
		}	
		.btn-3d.red:active {
		  box-shadow: 0 0 0 1px #c63702 inset,
		        0 0 0 2px rgba(255,255,255,0.15) inset,
		        0 0 0 1px rgba(0,0,0,0.4);
		}
/* a태그 스타일 */
          a {
			text-decoration-line: none;
			color: inherit;
			}
/* API 버튼 스타일 */
@keyframes ring {
    0% {
        width: 30px;
        height: 30px;
        opacity: 1;
    }
    100% {
        width: 300px;
        height: 300px;
        opacity: 0;
    }
}
@keyframes gradient1 {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}
.btn-3d.red {
    position: relative;
    border: none;
    min-width: 200px;
    min-height: 50px;
   	background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
    border-radius: 1000px;
    color: white;
    cursor: pointer;
    box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
    font-weight: 700;
    transition: 0.3s;
}

.btn-3d.red:hover {
    transform: scale(1.2);
}

.btn-3d.red:hover::after {
    content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 6px solid #ff99cc;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1.5s infinite;
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
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
            </nav> 
            
            
   <!-- 메인시작  -->        
   <div id="layoutSidenav_content">
      <main>
         <div class="container-fluid px-10 pt-5 ps-4" style="width: 80%;">
				<h2 class="mt-1 mb-3"><b>API 관리</b></h2>
				<div class="card mb-4">
					<div class="card-header">api관리</div>
               <div class="card-body"  style="text-align:center; padding:40px 50px 30px 50px;">
                  
                  <c:if test = "${sessionScope.SESS_NICKNAME=='관리자'}">
                      <button style="float:left;" type="button" class ="btn-3d red" onclick="location.href='${root}/api/petdata';">유기동물 공고<br> 데이터 관리</button>
                     
                      	<button type="button" class ="btn-3d red" onclick="location.href='${root}/api/sheldata';">보호소 <br> 데이터 관리</button>
                    
                      <button style="float:right;" type="button" class ="btn-3d red" onclick="location.href='${root}/api/withdata';">위드펫 <br> 데이터 관리</button>
                      </c:if>
                     
               </div>
               <button style="width:60px;" type="button" class ="btn btn-warning" onclick="location.href='${root}/mypage/mypage'">이전</button>&nbsp;
            </div>
         </div>
      </main>
     <footer class="bgcolor" style="position : absolute; padding-bottom:100px">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">            
          </div>          
      </div>                
	</footer>
   </div>

</body>
</html>