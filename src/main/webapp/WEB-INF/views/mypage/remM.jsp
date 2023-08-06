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
<title>회원정보확인</title>
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet"> <!-- 폰트 -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css"   rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"   crossorigin="anonymous"></script>
<script   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"   crossorigin="anonymous"></script>
<script   src="${root}/resources/bootstrap/js/scripts.js"></script>
<script   src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
 
 
  
  <script>
  

   	function logout() {
		if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "${pageContext.servletContext.contextPath}/user/logout";
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

  </script>
  <!-- 폰트 -->
	     <style type="text/css">
		.nanum{ font-family: 'NanumSquareNeo'; }
		.nanumB{font-family: 'NanumSquareNeoBold';}						
		</style>
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
            
   <div id="layoutSidenav_content">   
      <main>
         <div class="container-fluid px-10 pt-5 ps-4">
            <h2 class="mt-1 mb-3" style ="margin-left:127px; font"><b>회원 탈퇴</b></h2>
            </div>

            <div class="card mb-4" style="margin-left:150px; margin-right:155px;">
               <div class="card-header">
                 회원정보확인
               </div>
              
               <div class="card-body" style="padding:40px 0px">
               <div align="center">
                
                 <form action = "${root}/mypage/remMC" id="form" method ="post">
                  <div>
                     회원 탈퇴 시 회원만의 서비스를 받을 수 없습니다.<br>
                     탈퇴 하시겠습니까?
                 <br>
                 <br>
                 <div>
                 	비밀번호 입력 : <input class="form-control" type="password" name ="inputpwd" id="inputpwd" placeholder="Password" style="display:inline; width:300px;"/>                             		
                </div>                                                    
                </div>             
         		<br>
                     <button type="button" class ="btn btn-warning" onclick="location.href='${root}/mypage/mypage'" >이전</button>&nbsp;    	 
                    <button type="submit" class ="btn btn-warning" onclick="location.href=${root}/mypage/remM;" style="font-size:15px">탈퇴하기</button>&nbsp;
                </form>        
                      <input type="hidden" name="pwd" value="${mdto.pwd}">                   
				</div>
            </div>
         </div>
         
      </main>  
      </div>  
        <!-- 푸터 고정 -->
   <footer class="bgcolor" style="position : absolute; padding-bottom:100px">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">            
          </div>          
      </div>                
	</footer>
  

</body>
</html>