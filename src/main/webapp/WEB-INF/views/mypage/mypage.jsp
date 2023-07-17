<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.spring.domain.MembersDTO" %>
<%@ page import="com.spring.mapper.MypageMapper" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"   rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"   crossorigin="anonymous"></script>
<script   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"   crossorigin="anonymous"></script>
<script   src="${root}/resources/bootstrap/js/scripts.js"></script>
<script   src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
 
  <script>
  <!-- 최상단 -->

 	function login(){
		location.href = "${pageContext.servletContext.contextPath}/user/login";
	  }
  	function mypage(){
  		location.href = "${pageContext.servletContext.contextPath}/mypage/mypage";
 	 }
  
 	function main(){
  		location.href = "${pageContext.servletContext.contextPath}/main/main";
 	 }

   	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "${pageContext.servletContext.contextPath}/user/logout";
	 	}
	}
  
  <!-- 마이페이지 -->
  
  function favoritep(){
	  	location.href = "${pageContext.servletContext.contextPath}/fa/favoritep";
	  }
  function favorites(){
	  	location.href = "${pageContext.servletContext.contextPath}/fa/favorites";
	  }
  function favoritew(){
	  	location.href = "${pageContext.servletContext.contextPath}/fa/favoritew";
	  }
  function upmypage(){
	  	location.href = "${pageContext.servletContext.contextPath}/mypage/upmypage";
	  }
  function mypaper(){
		let nickname=document.getElementsByName("nickname").value;
		location.href = "${pageContext.servletContext.contextPath}/community/myclist";
		  return true;
	  
	  }

  </script>
<style>
      .deleteMember{
         color : darkgray;
         text-align : right;
      }
       tr {
          text-align : center;
       }
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
          .bgcolor {
         background-color: #f9f8f3;
          }
          
</style>
</head>
 <body class="sb-nav-fixed bgcolor" > 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
              <div class="input-group">
              <% String id = (String)session.getAttribute("SESS_EMAIL"); %>

           	 <% if( id != null) { %>
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
         <a class="mainlogo" onclick= "main();" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
         <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/pet/petall"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/shel/shelall"><b>보호소</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/with/withall"><b>위드펫</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/community/clist"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/notice/nlist"><b>공지사항</b></a>
   
            </nav>
   <div id="layoutSidenav_content">
   
      <main>
         <div class="container-fluid px-10 pt-5 ps-4">
            <h1 class="mt-1"><b>마이페이지</b></h1>
            </div>
            <ol class="breadcrumb mb-4 pt-3">

            </ol>

            <div class="card mb-4">
               <div class="card-header">
                  <i class="fas fa-table me-1"></i> 개인정보조회
               </div>
              
               <div class="card-body">
            
                  <table id="datatablesSimple" >
                 <c:forEach items="${membersDTO}" var="mdto">

                        <tr>
                           <td>닉네임</td>                        
                            <td>${mdto.nickname}</td>
                                
                        </tr>                    
                      
                        <tr>         
                           <td >비밀번호</td>
                            <td >*******</td>                         
                        </tr>                                         
                       
                        <tr>
                           <td>이메일</td>                 
                         <td>${mdto.email}</td>
                        </tr>
                     
                        <tr>
                           <td>이름</td>
                           <td>${mdto.name}</td>       
                        </tr>                       
                       
                        <tr>
                           <td>전화번호</td>
                           <td>${mdto.phone}</td>    
                        </tr>
                </c:forEach>
               </table>
            
               <div align="center"> 
              		 <form action="${pageContext.servletContext.contextPath}/community/myclist" method="post">            
                     <button type="submit" class ="btn btn-warning" onclick="mypaper();" >내가쓴글</button>&nbsp;
                      <input type="hidden" name="nickname" value="${mdto.nickname}"> 
                     </form>
                     <button type="button" class ="btn btn-warning" onclick="favorites();">보호소 즐겨찾기</button>&nbsp; 
                      <button type="button" class ="btn btn-warning" onclick="favoritew();">위드펫 즐겨찾기</button>&nbsp;
                      <button type="button" class ="btn btn-warning" onclick="favoritep();">공고 즐겨찾기</button>&nbsp;
                      <button type="button" class ="btn btn-warning" onclick="upmypage();">정보 수정</button>&nbsp;
                    </div>
                    
                     <button type="button" class="btn" onclick="location.href='${root}/mypage/remM'" style="font-size: 14px;">회원 탈퇴</button>
            </div>
         </div>
      </main>
      <footer class="py-4 bg-light mt-auto">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
               <div class="text-muted">Copyright &copy; Your Website 2023</div>

               <div></div>
            </div>
         </div>
      </footer>
   </div>

</body>
</html>