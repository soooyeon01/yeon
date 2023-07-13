<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.mapper.MypageMapper" %>  
<%@ page import="com.spring.domain.MembersDTO"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>커뮤니티 상세</title>
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
     	<script>  
	       	function toListPage() {
	    		location.href="${pageContext.servletContext.contextPath}/community/clist";
	    		}
	       	function moveCommUp() {
	       		let c_no=document.getElementsByName("c_no").value;
	       		let title=document.getElementsByName("title").value;
				let content=document.getElementsByName("content").value;
	       		/* location.href="${pageContext.servletContext.contextPath}/community/commuUp?c_no=${selectone.c_no}"; */
	       	}
    	</script>  
    	<script>
			function confirmDelete() {
	    		if (confirm("정말로 삭제하시겠습니까?")) {
	        	// 사용자가 Yes를 선택한 경우 삭제 동작을 수행할 코드 작성
	        	location.href = 'commuDel?c_no=${selectone.c_no}'; // 삭제 동작 예시
	    		} else {
	        // 사용자가 No를 선택한 경우 아무 동작도 수행하지 않음
	    		}
			}
		</script>
    	<style>
    	#cont {
    		width: 70rem;
    		height: 20rem;
    	}
    	</style>
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
         .bt {
         width: 100%;
         text-align:center;
         }
         .my{
         /* padding: 1rem;
     	 margin-left: 5rem;
     	 margin-right: 5rem;
     	 width: 10rem;
     	 height: 2rem; */
     	 width: 100px;
    	margin: auto;
    	display: block;
         }
        </style>
        
    </head>
   <body class="sb-nav-fixed"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
                 <div class="input-group">
                <% String email = (String)session.getAttribute("SESS_EMAIL"); %>
              <%System.out.println(email);%>
         <%  if( email != null) { %>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="location.href='${root}/mypage'" style="font-size: 14px;">마이페이지</button>                  
            <%} else{%>
                <button type="button" class="btn" onclick="location.href='${root}/login'" style="font-size: 14px;">로그인</button>                 
            <%}  %>
                </div>
            </form>      
            </nav>
            <script>
	            function logout() {
	    		if (confirm("로그아웃 하시겠습니까?")) {
	    		location.href = "${root}/logout";
	   		 	}
			}

            </script>
         <!-- 로고 -->              
        <nav class="main bg-white" >
         <a class="mainlogo" href="${root}/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
         <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >

             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link active" href="${root}/petnotice"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shelter"><b>보호소</b></a>
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/withpet"><b>위드펫</b></a>
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link " href="${root}/community"><b>커뮤니티</b></a>
         <a class=" pt-3 pb-3 flex-sm-fill text-sm-center nav-link " href="${root}/notice"><b>공지사항</b></a>

            </nav>
<div class="container mt-3">
  <h2 style="text-align: center;">게시글</h2>  
	<form action="${pageContext.servletContext.contextPath}/community/commuSel">
	<input type="hidden" name="c_no" value="${selectone.c_no}">
	<div class="row">
        <div class="mt-3 col p-3">
            <label for="reg_date">작성일:</label>
            <p class="form-control" name="reg_date">${selectone.reg_date}</p>
		</div>
        <div class="mb-3 mt-3 col p-3">
          	<label for="title">글제목:</label>
            <p class="form-control" name="title">${selectone.title}</p>
        </div>
        <div class="mb-3 mt-3 col p-3">
            <label for="nickname">작성자:</label>
            <p class="form-control" name="nickname">${selectone.nickname}</p>
            <%-- <div class="form-control" id="nickname" name="nickname">${ requestScope.communityDTO.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.communityDTO.nickname }</div> --%>
        </div> 
		<div class="mb-3 mt-3">
        	<label for="content">글내용:</label>
        	<p id="cont" class="form-control" name="content">${selectone.content}</p>
     	</div>
     </div>
	</form>
    	<%-- <input type="hidden" name="method" value="${param.method }">
        <input type="hidden" name="c_no" value="${param.c_no }">
		<input type="hidden" name="nickname" value="${communityDTO.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.communityDTO.nickname  }"> --%>
	<div class="container bt">
  	<button type="button" class="register col p-3 btn btn-warning my" onclick="toListPage();">목록으로</button>
	<button type="submit" class="register col p-3 btn btn-warning my" onclick="location.href='commuUp1?c_no=${selectone.c_no}'">수정</button> 
	<button type="submit" class="register col p-3 btn btn-warning my" onclick="confirmDelete();">삭제</button>  
	</div>
</div>
</body>
</html>