<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.mapper.MypageMapper"%> 
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
        <title>커뮤니티 수정</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
     	<script type="text/javascript"><!--
        /*=============================================================================
        画像１個の後追いマウスストーカー　参考サイト : http://www5e.biglobe.ne.jp/~purest/javascript/index.html
        ★古いブラウザ対応分削除し、CSSのtransition追加で、後追いに改造。　 2016/09/22
        =============================================================================*/
        (function (){  //即時関数で囲んでグローバル変数を消すため、この行はこのままで
        
        var mga="https://tistory1.daumcdn.net/tistory/4694508/skin/images/hai1.gif"; //★画像指定
        var hx=15;          //★表示位置 x座標。マウスと画像の横位置。マイナスで左
        var hy=15;          //★表示位置 y座標。マウスと画像の縦位置。マイナスで上
        var TRANS="transition:all 1s ease-in;";//★マウス画像の後追い方法。css指定。速度や速度変化。わからないならこのままで
        
        //指定ここまで--------------------------------------------------------------
        document.onmousemove=function (e){ var ob=document.getElementById("foo").style; ob.left=e.pageX+hx+"px"; ob.top=e.pageY+hy+"px";}
        document.write("<img src="+mga+" id='foo' style='position:absolute; "+TRANS+"'>");
        }());//即時関数終了
        // --></script>
        <script>
        function upCommu() {
        	let c_no=document.getElementsByName("c_no").value;
			let title=document.getElementsByName("title").value;
			let content=document.getElementsByName("content").value;

			return true;
		}
       	function toListPage() {
    		location.href="${pageContext.servletContext.contextPath}/community/clist";
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
         <a class="mainlogo" href="${root}/main/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
        <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/pet/petall"><b>공고</b></a> 
            <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shel/shelall"><b>보호소</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withall"><b>위드펫</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice"><b>공지사항</b></a>
        </nav>
<div class="container mt-3">
  <h2 style="text-align: center;">게시글 수정</h2>  
  <form action="${pageContext.servletContext.contextPath}/community/commuUp?c_no=${selectone.c_no}" method="post">
  	<input type="hidden" name="c_no" value="${selectcone.c_no}">
  
    <div class="row">
        <div class="mb-3 mt-3 col p-3">
        제목 : <input class="form-control" type="text" placeholder="Enter Title" name="title" value="${selectone.title}">
        </div>
        <div class="mb-3 mt-3 col p-3">
        닉네임 : <p class="form-control" type="text" name="nickname">${selectone.nickname}</p>
        </div> 
    	<div class="mb-3 mt-3 col p-3">
        내용 : <textarea class="form-control" rows="5" name="content">${selectone.content}</textarea>
     	</div>
    	<div class="row">
       	<button type="button" class="get col p-3 btn btn-warning" onclick="history.back();">뒤로</button>
       	<div class="col p-3"></div>
       	<button type="submit" class="register col p-3 btn btn-warning" onclick="upCommu();">전송</button>
    </div>
  </form>
</div>

</body>
</html>