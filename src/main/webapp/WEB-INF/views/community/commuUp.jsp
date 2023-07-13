<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ page import="com.spring.domain.MembersDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.mapper.MypageMapper" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
<title>Insert title here</title>
		<link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/page-nation.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/datatables-simple-demo.js"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
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
			let title=document.getElementsByName("title").value;
			let content=document.getElementsByName("content").value;

			return true;
		}
        
        </script>
</head>
<body>
	<div class="container mt-3">
  <h2 style="text-align: center;">게시글 수정</h2>  
  <form action="${pageContext.servletContext.contextPath}/community/commuUp" method="post">
  
    <div class="row">
        <div class="mb-3 mt-3 col p-3">
        제목 : <input class="form-control" type="text" placeholder="Enter Title" name="title" value="${selectone.title}">
        </div>
        <div class="mb-3 mt-3 col p-3">
        닉네임 : <p class="form-control" type="text" name="nickname">${selectone.nickname}</p>
        </div> 
    	<div class="mb-3 mt-3 col p-3">
        내용 : <textarea class="form-control" rows="5" name="content" value="${selectone.content}"></textarea>
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