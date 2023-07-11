<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spring.mapper.MypageDAO" %> 
<%@ page import="com.spring.mapper.MypageMapper" %> 
<%@ page import="com.spring.domain.MembersVO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>커뮤니티 상세</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${pageContext.servletContext.contextPath}/bootstrap/css/styles.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/bootstrap/css/page-nation.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.servletContext.contextPath}/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.servletContext.contextPath}/bootstrap/js/datatables-simple-demo.js"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script type="text/javascript">
				// <![CDATA[
						var sparks=75; // how many sparks per clicksplosion
						var speed=33; // how fast - smaller is faster
						var bangs=5; // how many can be launched simultaneously (note that using too many can slow the script down)
						var colours=new Array('#03f', '#f03', '#0e0', '#93f', '#0cf', '#f93', '#f0c'); 
						//                     blue    red     green   purple  cyan    orange  pink
						
						/****************************
						*   Clicksplosion Effect    *
						*(c)2012-3 mf2fm web-design *
						*  http://www.mf2fm.com/rv  *
						* DON'T EDIT BELOW THIS BOX *
						****************************/
						var intensity=new Array();
						var Xpos=new Array();
						var Ypos=new Array();
						var dX=new Array();
						var dY=new Array();
						var stars=new Array();
						var decay=new Array();
						var timers=new Array();
						var swide=800;
						var shigh=600;
						var sleft=sdown=0;
						var count=0;
						
						function addLoadEvent(funky) {
						  var oldonload=window.onload;
						  if (typeof(oldonload)!='function') window.onload=funky;
						  else window.onload=function() {
						    if (oldonload) oldonload();
						    funky();
						  }
						}
						
						addLoadEvent(clicksplode);
						
						function clicksplode() { if (document.getElementById) {
						  var i, j;
						  window.onscroll=set_scroll;
						  window.onresize=set_width;
						  document.onclick=eksplode;
						  set_width();
						  set_scroll();
						  for (i=0; i<bangs; i++) for (j=sparks*i; j<sparks+sparks*i; j++) {
						    stars[j]=createDiv('*', 13);
						    document.body.appendChild(stars[j]);
						  }
						}}
						
						function createDiv(char, size) {
						  var div, sty;
						  div=document.createElement('div');
						  sty=div.style;
						  sty.font=size+'px monospace';
						  sty.position='absolute';
						  sty.backgroundColor='transparent';
						  sty.visibility='hidden';
						  sty.zIndex='101';
						  div.appendChild(document.createTextNode(char));
						  return (div);
						}
						
						function bang(N) {
						  var i, Z, A=0;
						  for (i=sparks*N; i<sparks*(N+1); i++) { 
						    if (decay[i]) {
						      Z=stars[i].style;
						      Xpos[i]+=dX[i];
						      Ypos[i]+=(dY[i]+=1.25/intensity[N]);
						      if (Xpos[i]>=swide || Xpos[i]<0 || Ypos[i]>=shigh+sdown || Ypos[i]<0) decay[i]=1;
							  else {
						        Z.left=Xpos[i]+'px';
						        Z.top=Ypos[i]+'px';
							  }
						      if (decay[i]==15) Z.fontSize='7px';
						      else if (decay[i]==7) Z.fontSize='2px';
						      else if (decay[i]==1) Z.visibility='hidden';
							  decay[i]--;
							}
							else A++;
						  }
						  if (A!=sparks) timers[N]=setTimeout('bang('+N+')', speed);
						}
						
						function eksplode(e) { 
						  var x, y, i, M, Z, N;
						  set_scroll();
						  y=(e)?e.pageY:event.y+sdown;
						  x=(e)?e.pageX:event.x+sleft;
						  N=++count%bangs;
						  M=Math.floor(Math.random()*3*colours.length);
						  intensity[N]=5+Math.random()*4;
						  for (i=N*sparks; i<(N+1)*sparks; i++) {
						    Xpos[i]=x;
						    Ypos[i]=y-5;
						    dY[i]=(Math.random()-0.5)*intensity[N];
						    dX[i]=(Math.random()-0.5)*(intensity[N]-Math.abs(dY[i]))*1.25;
						    decay[i]=16+Math.floor(Math.random()*16);
						    Z=stars[i].style;
						    if (M<colours.length) Z.color=colours[i%2?count%colours.length:M];
						    else if (M<2*colours.length) Z.color=colours[count%colours.length];
						    else Z.color=colours[i%colours.length];
						    Z.fontSize='13px';
						    Z.visibility='visible';
						  }
						  clearTimeout(timers[N]);
						  bang(N);
						} 
						
						function set_width() {
						  var sw_min=999999;
						  var sh_min=999999;
						  if (document.documentElement && document.documentElement.clientWidth) {
						    if (document.documentElement.clientWidth>0) sw_min=document.documentElement.clientWidth;
						    if (document.documentElement.clientHeight>0) sh_min=document.documentElement.clientHeight;
						  }
						  if (typeof(self.innerWidth)=='number' && self.innerWidth) {
						    if (self.innerWidth>0 && self.innerWidth<sw_min) sw_min=self.innerWidth;
						    if (self.innerHeight>0 && self.innerHeight<sh_min) sh_min=self.innerHeight;
						  }
						  if (document.body.clientWidth) {
						    if (document.body.clientWidth>0 && document.body.clientWidth<sw_min) sw_min=document.body.clientWidth;
						    if (document.body.clientHeight>0 && document.body.clientHeight<sh_min) sh_min=document.body.clientHeight;
						  }
						  if (sw_min==999999 || sh_min==999999) {
						    sw_min=800;
						    sh_min=600;
						  }
						  swide=sw_min-7;
						  shigh=sh_min-7;
						}
						
						function set_scroll() {
						  if (typeof(self.pageYOffset)=='number') {
						    sdown=self.pageYOffset;
						    sleft=self.pageXOffset;
						  }
						  else if (document.body && (document.body.scrollTop || document.body.scrollLeft)) {
						    sdown=document.body.scrollTop;
						    sleft=document.body.scrollLeft;
						  }
						  else if (document.documentElement && (document.documentElement.scrollTop || document.documentElement.scrollLeft)) {
						    sleft=document.documentElement.scrollLeft;
						    sdown=document.documentElement.scrollTop;
						  }
						  else {
						    sdown=0;
						    sleft=0;
						  }
						}
					// ]]>
		</script>
     <style type="text/css">* {cursor: url(https://cur.cursors-4u.net/holidays/hol-4/hol336.cur), auto !important;}</style>   
     <style type="text/css">.adfit__swipeable{-webkit-tap-highlight-color:transparent;cursor:default;height:100%;left:0;outline:none;position:absolute;top:0;width:100%}</style>
        <script>
        window.onload = function(){
  		initClass();
  	}
  	
  	function adoptMethod(type){
  		$("input[name='method']").val(type);
  		initClass();
  	}
  	
  	function initClass(){
  		
  		// modify
  		$("textarea").removeClass("modify");
  		$("input[type='text']").removeClass("modify");
  		
  		// register
  		$("textarea").removeClass("register");
  		$("input[type='text']").removeClass("register");
  		
  		// get
  		$("textarea").removeClass("get");
  		$("input[type='text']").removeClass("get");
  		
  		const method = $("input[name='method']").val();
  		$("textarea").addClass(method);
  		$("input[type='text']").addClass(method);
  		
  		switch(method){
  			case "get" : 
  				$(".get").attr("readonly", true);
  				break;
  			case "modify" : 
  				$(".modify").attr("readonly", false);
  				break;
  			case "register" : 
  				$(".register").attr("readonly", false);
  				break;
  		}
  		
  	}
  	function goSubmit(){
  		 //const data = { opKey : 123 };
  		const data = $("form").serialize();
  		console.log(data);
         $.ajax(
                 {
                     url: "/4jo/api/communitycontent"
                     ,async:true // 비동기 쓰레드,false : 동기식(응답 받을때까지 대기함)
                     ,contentType: 'application/x-www-form-urlencoded; charset=UTF-8' // 전송타입
                     ,type:"POST" //method
                     ,data : data
                     ,dataType:"json" // 수신타입
                     ,success:function(data, textStatus){
                     	console.log(data);
                     	
                     	if(data.result == 1){
                     		var msg ='';
                     		switch(data.method){
                     		case 'modify': msg = data.result +'건 수정되었습니다.'; break;
                     		case 'register': msg = data.result +'건 등록되었습니다.';break;
                     		case 'remove': msg = data.result +'건 삭제되었습니다.';
                     			location.href = "${pageContext.servletContext.contextPath }/community";
                     			break;
                     		}
                     		alert( msg );
                     	}else{
                     		alert(data.result + '건 실패입니다. \n다시시도하세요.');
                     	}
                     }
                     ,error:function(jqXHR, textStatus, errorThrown){
                         console.log(jqXHR);
                         console.log(textStatus);
                         console.log(errorThrown);
                     }
                 }
             );
			  		
  		
  		return false;
  	}
  </script>
</head>
<body>
<%	
           		String nickname = (String)session.getAttribute("SESS_NICKNAME"); 
		        // 세션에 저장된 아이디를 가져와서
		        // 그 아이디 해당하는 회원정보를 가져온다.
		        MypageDAO dao = MypageDAOImpl.getInstance();
		        MembersVO vo = dao.selectMypage(nickname);
		    %>
<div class="container mt-3">
  <h2 style="text-align: center;">게시글 작성</h2>  
  <form action="${pageContext.servletContext.contextPath}/communitycontent" method="post">
  
    <div class="row">
        <div class="col"></div>
        <div class="col"></div>
        <div class="mt-3 col p-3">
            <label for="reg_date">작성일:</label>
            <p class="form-control" id="reg_date" name="reg_date">${ requestScope.communityVO.reg_date }</p>
        </div>
        
    </div>
    
    <div class="row">
        <div class="mb-3 mt-3 col p-3">
           <label for="title">글제목:</label>
           <input type="text" class="form-control" id="title" placeholder="Enter Title" name="title" value="${ requestScope.communityVO.title }">
        </div>
        <div class="mb-3 mt-3 col p-3">
            <label for="title">작성자:</label>
            <div class="form-control" id="nickname" name="nickname">${ requestScope.communityVO.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.communityVO.nickname }</div>
        </div> 
    </div>
    <div class="mb-3 mt-3">
        <label for="content">글내용:</label>
        <textarea class="form-control" rows="5" id="content" name="content" >${ communityVO.content }</textarea>
     	</div>
    	<div class="row">
       	<button type="button" class="get col p-3 btn btn-warning" onclick="history.back();">뒤로</button>
       	<div class="col p-3"></div>
       	<button type="submit" class="register col p-3 btn btn-warning" onclick="adoptMethod('register'); return goSubmit();">전송</button>
         	<div class="col p-3"></div>
       	<button type="button"  class="modify col p-3 btn btn-warning" onclick="adoptMethod('modify'); return goSubmit();">수정</button>
       		<div class="col p-3"></div>
	    <button type="button"  class="remove col p-3 btn btn-warning" onclick="adoptMethod('remove'); return goSubmit();">삭제</button>
	    	<div class="col p-3"></div>
   			
    </div>
    	<input type="hidden" name="method" value="${param.method }">
        <input type="hidden" name="c_no" value="${param.c_no }">
		<input type="hidden" name="nickname" value="${communityVO.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.communityVO.nickname  }">
  </form>
</div>

</body>
</html>