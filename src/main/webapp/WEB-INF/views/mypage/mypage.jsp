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

<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet"> <!-- 폰트 -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"   rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"  crossorigin="anonymous"></script>
<script   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"   crossorigin="anonymous"></script>
<script   src="${root}/resources/bootstrap/js/scripts.js"></script>
<script   src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<style type="text/css">* {cursor: url(https://cur.cursors-4u.net/nature/nat-10/nat977.ani), url(https://cur.cursors-4u.net/nature/nat-10/nat977.gif), auto !important;}</style><a href="https://www.cursors-4u.com/cursor/2011/12/15/corgi-dog-bark.html" target="_blank" title="Corgi Dog Bark"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Corgi Dog Bark" style="position:absolute; top: 0px; right: 0px;" /></a>
 
  <script>
   	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "${root}/user/logout";
	 	}
	}
 
  function mypaper(){
		let nickname=document.getElementsByName("nickname").value;
		location.href = "${root}/community/myclist";
		  return true;	  
	  }

  </script>
    
		<!-- 폰트 -->
	     <style type="text/css">
		.nanum{ font-family: 'NanumSquareNeo'; }					
		</style>
	
<style>		
		.table tr th{
		width : 280px;
		height : 80px;
		background-color : #fff4bd;
		text-align : center;
		padding :30px 0px;
		color:#787878;
		font-size:15px;
		}
		
		.table tr td{
		 text-align : center;
		 padding: 30px 0px;
		 width : 560px;
		 font-size:15px;
		}
		
      .deleteMember{
         color : black;
         text-align : right;
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
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
            </nav>  
    
    <!-- 메인 시작 -->       
   <div id="layoutSidenav_content">
      <main class = "nanum">
         <div class="container-fluid px-10 pt-5 ps-4" >
            <h2 class="mt-1 mb-3" style ="margin-left:165px;"><b>마이페이지</b></h2>
            </div>
      
            <div class="card mb-4"style="margin:0px 138px 100px 150px;">
               <div class="card-header">개인정보조회</div>              
            <div>               		 
                     <!-- 드롭버튼 -->
                     <div class="dropdown" style="padding-top:50px; padding-bottom:10px; padding-right:100px; float:right;">
                     		
						  <button class="btn btn-warning dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" Style="font-size:14px">
						    즐겨찾기
						  </button>&nbsp;
						  <ul class="dropdown-menu" style="font-size:15px;">
						    <li><a class="dropdown-item" href="${root}/fa/favoritep">공고</a></li>
						    <li><a class="dropdown-item" href="${root}/fa/favorites">보호소</a></li>
						    <li><a class="dropdown-item" href="${root}/fa/favoritew">위드펫</a></li>
						  </ul>
						 
						  <form action="${root}/community/myclist" method="post" style="width:100px; display:inline;">            
		                     <button type="submit" class ="btn btn-warning" onclick="mypaper();" Style="font-size:14px" >내가쓴글</button>&nbsp;
		                      <input type="hidden" name="nickname" value="${mdto.nickname}"> 
		                  </form>
		                  <button type="button" class ="btn btn-warning" onclick="location.href='${root}/mypage/updatecheck';" Style="font-size:14px">정보수정</button>&nbsp;
						</div>            
                 </div>              
 
               <div class="card-body" style ="padding: 0px 100px; padding-top:0px; padding-bottom : 50px">
            
               <table class = "table">
                 <c:forEach items="${membersDTO}" var="mdto">
						
                        <tr style="border-top:2px gray solid;">
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
                    <c:if test = "${sessionScope.SESS_NICKNAME=='관리자'}">
                      <button type="button" class ="btn-3d red" onclick="location.href='${root}/user/userlist';">회원관리</button>&nbsp;
                      </c:if>
                    
                    <!-- 회원탈퇴 -->
                    <button type="button" class="btn" onclick="location.href='${root}/mypage/remM'" style="font-size: 14px; float:right;">회원 탈퇴</button>
            </div>             
         </div>
      </main>   
  
 <!-- 푸터 고정 -->
   <footer class="bgcolor" style="position : absolute; padding-bottom:100px">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">            
          </div>          
      </div>                
	</footer>
       </div>
</body>
</html>