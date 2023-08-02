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
		location.href = "${root}/user/login";
	  }
  	function mypage(){
  		location.href = "${root}/mypage/mypage";
 	 }
  
 	function main(){
  		location.href = "${root}/main/main";
 	 }

   	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "${root}/user/logout";
	 	}
	}
  <!-- 마이페이지 --> 
  function favoritep(){
	  	location.href = "${root}/fa/favoritep";
	  }
  function favorites(){
	  	location.href = "${root}/fa/favorites";
	  }
  function favoritew(){
	  	location.href = "${root}/fa/favoritew";
	  }
  function upmypage(){
	  	location.href = "${root}/mypage/upmypage";
	  }
  function updatecheck(){
	  	location.href = "${root}/mypage/updatecheck";
	  }
  
  function mypaper(){
		let nickname=document.getElementsByName("nickname").value;
		location.href = "${root}/community/myclist";
		  return true;	  
	  }
  function kick(){
	  	location.href = "${root}/user/userlist";
	  }

  </script>
  
<style>
		.table tr th {
		width : 300px;
		height : 80px;
		background-color : #fff6c2;
		text-align : center;
		}
      .deleteMember{
         color : black;
         text-align : right;
      }
       tr {
          text-align : center;
       }
       td {
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
         /* ------------ */
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
		.btn-gradient {
			  text-decoration: none;
			  color: white;
			  padding: 10px 30px;
			  display: inline-block;
			  position: relative;
			  border: 1px solid rgba(0,0,0,0.21);
			  border-bottom: 4px solid rgba(0,0,0,0.21);
			  border-radius: 4px;
			  text-shadow: 0 1px 0 rgba(0,0,0,0.15);
			}
			
			.btn-gradient.red{ 
			  background: rgba(250,90,90,1);
			  background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(250,90,90,1)), to(rgba(232,81,81,1)));
			  background: -webkit-linear-gradient(rgba(250,90,90,1) 0%, rgba(232,81,81,1) 100%);
			  background: -moz-linear-gradient(rgba(250,90,90,1) 0%, rgba(232,81,81,1) 100%);
			  background: -o-linear-gradient(rgba(250,90,90,1) 0%, rgba(232,81,81,1) 100%);
			  background: linear-gradient(rgba(250,90,90,1) 0%, rgba(232,81,81,1) 100%);
			  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fa5a5a', endColorstr='#e85151', GradientType=0 );
			}
			
		/* ------------ */
			
          
</style>
</head>
 <body class="sb-nav-fixed bgcolor" > 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
              <div class="input-group">
              <% String id = (String)session.getAttribute("SESS_EMAIL"); %>
			
           	 <% if( id != null) { %>
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
         <a class="mainlogo" onclick= "main();" >
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
         <div class="container-fluid px-10 pt-5 ps-4" >
            <h2 class="mt-1 mb-3" style ="margin-left:77px; font"><b>마이페이지</b></h2>
            </div>
      

            <div class="card mb-4" style="margin-left:100px; margin-right:100px;">
               <div class="card-header">
                   개인정보조회
               </div>
              
               <div class="card-body" style="padding: 50px">
            
                  <table class = "table">
                 <c:forEach items="${membersDTO}" var="mdto">
						
                        <tr>
                           <th>닉네임</th>                        
                           <td>${mdto.nickname}</td>                               
                        </tr>                    
                      
                        <tr>         
                          <th>비밀번호</th>
                          <td >*******</td>                         
                        </tr>                                         
                       
                        <tr>
                         <th>이메일</th>                 
                         <td>${mdto.email}</td>
                        </tr>
                     
                        <tr>
                          <th>이름</th>
                          <td>${mdto.name}</td>       
                        </tr>                       
                       
                        <tr>
                           <th>전화번호</th>
                           <td>${mdto.phone}</td>    
                        </tr>
                </c:forEach>
               </table>
            
               <div align="center"> 
              		 <form action="${root}/community/myclist" method="post">            
                     <button type="submit" class ="btn btn-warning" onclick="mypaper();" >내가쓴글</button>&nbsp;
                      <input type="hidden" name="nickname" value="${mdto.nickname}"> 
                     </form>
                     <button type="button" class ="btn btn-warning" onclick="favorites();">보호소 즐겨찾기</button>&nbsp; 
                      <button type="button" class ="btn btn-warning" onclick="favoritew();">위드펫 즐겨찾기</button>&nbsp;
                      <button type="button" class ="btn btn-warning" onclick="favoritep();">공고 즐겨찾기</button>&nbsp;
                      <button type="button" class ="btn btn-warning" onclick="updatecheck();">정보 수정</button>&nbsp;
                      <c:if test = "${sessionScope.SESS_NICKNAME=='관리자'}">
                      <button type="button" class ="btn-3d red" onclick="kick();">누르지마시오</button>&nbsp;
                      </c:if>
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