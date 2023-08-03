<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ page import="com.spring.controller.CommunityController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.spring.mapper.MypageMapper" %>  
<%@ page import="com.spring.domain.MembersDTO"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
     	<script src="https://kit.fontawesome.com/1163c2c278.js" crossorigin="anonymous"></script>
     	<style type="text/css">* {cursor: url(https://cur.cursors-4u.net/nature/nat-2/nat186.cur), auto !important;}</style><a href="https://www.cursors-4u.com/cursor/2006/03/05/nat186.html" target="_blank" title="Kitty Cat 19"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Kitty Cat 19" style="position:absolute; top: 0px; right: 0px;" /></a>
     	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet">
     	<script type="text/javascript">
    // <![CDATA[
                var colours=new Array('#f00', '#f06', '#f0f', '#f6f', '#f39', '#f9c'); // colours of the hearts
                var minisize=16; // smallest size of hearts in pixels
                var maxisize=28; // biggest size of hearts in pixels
                var hearts=66; // maximum number of hearts on screen
                var over_or_under="over"; // set to "over" for hearts to always be on top, or "under" to allow them to float behind other objects

                /*****************************
                *JavaScript Love Heart Cursor*
                *  (c)2013+ mf2fm web-design *
                *   http://www.mf2fm.com/rv  *
                *  DON'T EDIT BELOW THIS BOX *
                *****************************/
                var x=ox=400;
                var y=oy=300;
                var swide=800;
                var shigh=600;
                var sleft=sdown=0;
                var herz=new Array();
                var herzx=new Array();
                var herzy=new Array();
                var herzs=new Array();
                var kiss=false;

            if (typeof('addRVLoadEvent')!='function') function addRVLoadEvent(funky) {
            var oldonload=window.onload;
            if (typeof(oldonload)!='function') window.onload=funky;
            else window.onload=function() {
                if (oldonload) oldonload();
                funky();
            }
            }

            addRVLoadEvent(mwah);

            function mwah() { if (document.getElementById) {
            var i, heart;
            for (i=0; i<hearts; i++) {
                heart=createDiv("auto", "auto");
                heart.style.visibility="hidden";
                heart.style.zIndex=(over_or_under=="over")?"1001":"0";
                heart.style.color=colours[i%colours.length];
                heart.style.pointerEvents="none";
                if (navigator.appName=="Microsoft Internet Explorer") heart.style.filter="alpha(opacity=75)";
                else heart.style.opacity=0.75;
                heart.appendChild(document.createTextNode(String.fromCharCode(9829)));
                document.body.appendChild(heart);
                herz[i]=heart;
                herzy[i]=false;
            }
            set_scroll();
            set_width();
            herzle();
            }}

            function herzle() {
            var c;
            if (Math.abs(x-ox)>1 || Math.abs(y-oy)>1) {
                ox=x;
                oy=y;
                for (c=0; c<hearts; c++) if (herzy[c]===false) {
                herz[c].firstChild.nodeValue=String.fromCharCode(9829);
                herz[c].style.left=(herzx[c]=x-minisize/2)+"px";
                herz[c].style.top=(herzy[c]=y-minisize)+"px";
                herz[c].style.fontSize=minisize+"px";
                herz[c].style.fontWeight='normal';
                herz[c].style.visibility='visible';
                herzs[c]=minisize;
                break;
                }
            }
            for (c=0; c<hearts; c++) if (herzy[c]!==false) blow_me_a_kiss(c);
            setTimeout("herzle()", 40);
            }

            document.onmousedown=pucker;
            document.onmouseup=function(){clearTimeout(kiss);};

            function pucker() {
            ox=-1;
            oy=-1;
            kiss=setTimeout('pucker()', 100);
            }

            function blow_me_a_kiss(i) {
            herzy[i]-=herzs[i]/minisize+i%2;
            herzx[i]+=(i%5-2)/5;
            if (herzy[i]<sdown-herzs[i] || herzx[i]<sleft-herzs[i] || herzx[i]>sleft+swide-herzs[i]) {
                herz[i].style.visibility="hidden";
                herzy[i]=false;
            }
            else if (herzs[i]>minisize+2 && Math.random()<.5/hearts) break_my_heart(i);
            else {
                if (Math.random()<maxisize/herzy[i] && herzs[i]<maxisize) herz[i].style.fontSize=(++herzs[i])+"px";
                herz[i].style.top=herzy[i]+"px";
                herz[i].style.left=herzx[i]+"px";
            }
            }

            function break_my_heart(i) {
            var t;
            herz[i].firstChild.nodeValue=String.fromCharCode(9676);
            herz[i].style.fontWeight='bold';
            herzy[i]=false;
            for (t=herzs[i]; t<=maxisize; t++) setTimeout('herz['+i+'].style.fontSize="'+t+'px"', 60*(t-herzs[i]));
            setTimeout('herz['+i+'].style.visibility="hidden";', 60*(t-herzs[i]));
            }

            document.onmousemove=mouse;
            function mouse(e) {
            if (e) {
                y=e.pageY;
                x=e.pageX;
            }
            else {
                set_scroll();
                y=event.y+sdown;
                x=event.x+sleft;
            }
            }

            window.onresize=set_width;
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
            swide=sw_min;
            shigh=sh_min;
            }

            window.onscroll=set_scroll;
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

            function createDiv(height, width) {
            var div=document.createElement("div");
            div.style.position="absolute";
            div.style.height=height;
            div.style.width=width;
            div.style.overflow="hidden";
            div.style.backgroundColor="transparent";
            return (div);
            }
            // ]]>
    </script>
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
	    	
    	</script>
    	<script>
    	$(document).ready(function() {
    	$('#Reply_regist').click(function() {
			
   			//Json으로 전달할 파라미터 변수선언
   			let c_no = ${selectone.c_no};
   			let nickname = "${sessionScope.SESS_NICKNAME}";
   			let rcontent = $('#rcontent').val();
   		
   			if(nickname == ''){
   				alert('로그인 후 이용해주세요');
   				return;
   			}else if(rcontent == '') {
   				alert('내용을 입력하세요');
   			}
   			
   			$.ajax({
   				type:'post',
   				url:'<c:url value="/community/newR"/>',
   				data: JSON.stringify(
   					{
   						"c_no":c_no,
   						"nickname":nickname,
   						"rcontent":rcontent
   					}		
   				),
   				contentType: 'application/json',
   				dataType: "json",
   				success:function(data){
   					//console.log('통신성공' + data);
   					if(data.result === 'newSuccess') {
   						alert('댓글 등록이 완료되었습니다.');
   						//console.log('댓글 등록 완료');
   						$('#nickname').val(nickname);
   	   					$('#rcontent').val('');
   	   					getList();
   					} else {
   						alert('로그인 이후 이용해주시기 바랍니다.');
   						//console.log('댓글 등록 실패');
   					}
   				},
   				error:function(data,error){
   					console.log(error);
   					console.log(data);
   					//alert('da');
   				}
   			});// 댓글 비동기 끝
   			
		});// 댓글등록 이벤트 끝
		
		getList();
    	
		function updateReply(r_no, nickname, replyElement) {
		    console.log("수정시작");
		    var nickname = "${sessionScope.SESS_NICKNAME}";
		    // 기존의 rcontent 값 초기화를 삭제하고 아래와 같이 해당 요소에서 댓글 내용을 가져옵니다.
		    var rcontent = $(replyElement).siblings("#rcontent").text(); 
    		
		    var reUp = "";
    		reUp+="<div><span id='nickname'><strong>" + nickname + "</strong></span><br/>";
    		reUp+="<textarea id='uprcontent' rows='5'>"+rcontent+"</textarea><br>";
    		reUp+="<button type='button' class='btn btn-warning' id='updating' data-rno='" + r_no + "'>";
    		reUp+="댓글 수정</button>";
    		reUp+="<button type='button' class='btn btn-warning' id='cancelReply'>취소</button>";
    		reUp+="</div><hr>";

    		$(replyElement).parent().html(reUp);
    		$("#cancelReply").click(cancelReply);

    	};
    	
    	function cancelReply() {
    	    getList();
    	}
		
		$(document).on("click", "#updating", function() {
			const r_no = $(this).data("rno");
			const uprcontent = $('#uprcontent').val();
		    console.log("up내용 "+uprcontent);   
		    
		        $.ajax({
		            type:'post',
		            url:'<c:url value="/community/upR"/>',
		            data:JSON.stringify({
		                "r_no":r_no,
		                "rcontent": uprcontent
		            }),
		   
		            contentType: 'application/json',
		            
		            success:function(data){
		                console.log('통신성공'+data);
		                alert('댓글이 수정되었습니다');
		                getList();
		            },
		            error:function(){
		                alert('통신실패');
		            }
		        }); //댓글 수정 비동기
		});
		
		
		$(document).on("click", "#delete", function() {
			const r_no = $(this).data("rno");
		    const rcontent =$(this).data("id");
		    if(confirm('댓글을 삭제하시겠습니까?')){
		        console.log('댓글삭제');
		        $.ajax({
		            type:'post',
		            url:'<c:url value="/community/delR"/>',
		            data:JSON.stringify({
		                "r_no":r_no
		            }),
		            contentType: 'application/json',
		            success:function(data){
		                console.log('통신성공'+data);
		                alert('댓글이 삭제되었습니다');
		                getList();
		            },
		            error:function(){
		                alert('통신실패');
		            }
		        }); //댓글 삭제 비동기
		    }else{
		        return false;
		    }
		});
   			
		function getList() {
			const c_no = ${selectone.c_no};
			const nickname = "${selectone.nickname}";
   			const rcontent = $('#rcontent').val();
   			/* const com_no = ${com}; */
			$.getJSON(
					"<c:url value='/community/reply/'/>" + c_no,
				function(data) {
					if(data.total > 0){
						var list = data.list;
						
						var reply_html = "<div style=margin:15px;>";
						
						$('#count').html(data.total);
						for(i = 0;i < list.length;i++){
							console.log("댓글 총 갯수 : "+data.total);
							console.log("r_no : "+list[i].r_no);
							var rcontent = list[i].rcontent;
							var r_no = list[i].r_no;
							var nickname = list[i].nickname;
							var reg_date= list[i].reg_date;
							
							reply_html += "<div><span id='nickname'><strong>" + nickname + "</strong></span><br/>";
							//reply_html += "<span id='Rno_"+Rno+"' >" + Rno + "</span><br>";
							reply_html += "<span id='rcontent'>" + rcontent + "</span><br>";
							reply_html += "<span id='reg_date' style='font-size:3px;'>" + reg_date + "</span><br>";
							
							if(nickname === "${sessionScope.SESS_NICKNAME}" || "${sessionScope.SESS_NICKNAME}" === "관리자"){

								reply_html += "<span id='updateReply' class='updateBtn' style='cursor:pointer;' data-rno="+r_no+" data-rcontent='" + rcontent + "' data-nickname='" + nickname + "'>[수정]</span><span id='delete' style='cursor:pointer;' data-id ="+rcontent+" data-rno="+r_no+">[삭제]</span><br></div><hr>";
							
							}
							else{
								reply_html += "</div><hr>";
							}
						}
						
						$(".reply_Box").html(reply_html);
						
						$(".updateBtn").click(function() {
						    const r_no = $(this).data("rno");
						    const nickname = $(this).data("nickname");

						    updateReply(r_no, nickname, this);
						});
						
					}
					else{
						var reply_html = "<div>등록된 댓글이 없습니다.</div>";
						$(".reply_Box").html(reply_html);
					}
				}
				);//getJson
		}
    	}) ;//jquery
    	
		</script>
		<script>
		
			$(document).ready(function() {
				var likeval = ${islike};
				let c_no = ${selectone.c_no};
				let nickname = '${sessionScope.SESS_NICKNAME}';
				var btnLike = $('#btnLike');
				var likeCnt = "${getLikeCnt}";
				console.log(likeCnt + "뿡");
				
				
				function updateButton() {
					if (likeval > 0) {
						console.log(likeval + "좋아요 누름");
						btnLike.html("추천하기 취소");
					} else {
						console.log(likeval + "좋아요 안누름");
						console.log(nickname);
					}
				}
				
				function updateLikeCount() {
				    $.ajax({
				        type: 'post',
				        url: '<c:url value="/community/getLikeCnt"/>',
				        contentType: 'application/json',
				        dataType: 'json',  // 응답을 JSON 형식으로 해석합니다.
				        data: JSON.stringify({
				            c_no: c_no,
				            likeCnt: likeCnt
				        }),
				        success: function (data) {
				            $('#like_cnt').text(data.likeCnt);  // 서버에서 반환된 좋아요 개수를 사용합니다.
				        },
				        error: function () {
				            alert("error");
				        }
				    });
				}

				updateButton();
				updateLikeCount();

				btnLike.click(function() {
					if (likeval > 0) { // 이미 좋아요를 누른 경우
						$.ajax({
							type : 'post',
							url : '<c:url value="/community/likeDown"/>',
							contentType : 'application/json',
							data : JSON.stringify({
								"c_no" : c_no,
								"nickname" : nickname
							}),
							success : function(data) {
								btnLike.html("추천하기");
								likeval = 0;
								alert('추천 취소 성공');
								updateLikeCount();
							},
							error : function() {
								alert("error");
							}
						});
					} else { // 좋아요를 누르지 않은 경우
						$.ajax({
							type : 'post',
							url : '<c:url value="/community/likeUp"/>',
							contentType : 'application/json',
							data : JSON.stringify({
								"c_no" : c_no,
								"nickname" : nickname
							}),
							success : function(data) {
								btnLike.html("추천하기 취소");
								likeval = 1;
								alert('추천하기 성공');
								updateLikeCount();
							}
						});
					}
				});
			});
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
         display : flex
         }
         .my{
         
     	 width: 100px;
    	margin: auto;
    	display: block;
         }
       
         #replyBox{
         	background-color:white;
         	width: 50rem;
         }
        </style>
        <style type="text/css">
		  .nanum{ font-family: 'NanumSquareNeo'; }
		  .nanumB{font-family: 'NanumSquareNeoBold';}      
		</style>
        
    </head>
   <body class="sb-nav-fixed bgcolor nanum"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
                 <div class="input-group">
                <% String email = (String)session.getAttribute("SESS_EMAIL"); %>
              <%System.out.println(email);%>
              
         <%  if( email != null) { %>
         		<div style="margin-top:5px;">♡${sessionScope.SESS_NICKNAME}님 환영합니다♡</div>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="location.href='${root}/mypage/mypage'" style="font-size: 14px;">마이페이지</button>                  
            <%} else{%>
                <button type="button" class="btn" onclick="location.href='${root}/user/login'" style="font-size: 14px;">로그인</button>                 
            <%}  %>
                </div>
            </form>      
            </nav>
            <script>
	            function logout() {
	    		if (confirm("로그아웃 하시겠습니까?")) {
	    		location.href = "${root}/user/logout";
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
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withca"><b>위드펫</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
			<a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
        </nav>
<div class="container mt-3">
  <h2 style="text-align: center;">게시글</h2>  
	<form action="${pageContext.servletContext.contextPath}/community/commuSel">
	<input type="hidden" name="c_no" value="${selectone.c_no}">
	<div class="row">
        <div class="mt-3 col p-3">
            <label>작성일:</label>
            <p class="form-control" id="reg_date" name="reg_date">${selectone.reg_date}</p>
		</div>
        <div class="mb-3 mt-3 col p-3">
          	<label>글제목:</label>
            <p class="form-control" id="title" name="title">${selectone.title}</p>
        </div>
        <div class="mb-3 mt-3 col p-3">
            <label>작성자:</label>
            <p class="form-control" id="nickname" name="nickname">${selectone.nickname}</p>
            <%-- <div class="form-control" id="nickname" name="nickname">${ requestScope.communityDTO.nickname == null ? sessionScope.SESS_NICKNAME : requestScope.communityDTO.nickname }</div> --%>
        </div> 
         <div class="mt-3 col p-3">
            <label>조회수:</label>
            <p class="form-control" id="view_cnt" name="view_cnt">${selectone.view_cnt}</p>
		</div>
		<div class="mb-3 mt-3">
        	<label>글내용:</label>
        	<p id="cont" class="form-control" id="content" name="content">${selectone.content}</p>
     	</div>
     	
     	<div class="mb-3 mt-3">
        	<label>추천수:</label>
        	<p id="like_cnt" class="form-control" name="like_cnt"></p>
     	</div>
     </div>
	</form>
	
	<div class="container bt">
  	<button type="button" class="register col p-3 btn btn-warning my" onclick="toListPage();">목록으로</button>
	<c:if test="${sessionScope.SESS_NICKNAME==selectone.nickname || sessionScope.SESS_NICKNAME=='관리자'}">
	<button type="submit" class="register col p-3 btn btn-warning my" onclick="location.href='commuUp1?c_no=${selectone.c_no}'">수정</button> 
	<button type="submit" class="register col p-3 btn btn-warning my" onclick="confirmDelete();">삭제</button>  
	</c:if>
	<br>
	</div>
	<%-- <%@ include file="../community/reply.jsp" %> --%>
	<div class="reply-box">
                    <input type="hidden" id="c_no" name="c_no" value="${selectone.c_no}">
   		                 <div>
   		                 <span>
   		                 <input type="hidden" id="r_no" name="r_no" value="${list2.r_no}"></span>
						<br>
						<form method="post">
							<div>
		                        <c:if test = "${sessionScope.SESS_NICKNAME!= null and sessionScope.SESS_NICKNAME!=selectone.nickname}">
	            					<button type ="button" class="btn btn-warning btnLike" id="btnLike" >추천하기</button>
	            					<i class="fa-solid fa-face-kiss-wink-heart fa-2x" style="color: #f00000;"></i>
	            					<i class="fa-solid fa-heart fa-3x" style="color: #ff0000;"></i>
	            					<i class="fa-solid fa-heart fa-5x" style="color: #ff0000;"></i>
	            					<i class="fa-solid fa-heart fa-10x" style="color: #ff0000;"></i>
	    						</c:if>
	    					</div>		
	                    </form>
   		                 </div>
						 <div class="reply-count">댓글 <span id="count">
   		                 <c:out value="${map.total}"/></span>
   		                 </div>	
   		                 	   <!-- <span class="c-icon"><i class="fa-solid fa-user"></i>  -->
   		                 <div class="reply-name">
	                        <span class="anonym">작성자 : ${sessionScope.SESS_NICKNAME}
	               			<%-- <input type="text" class="form-control" id="nickname" name ="nickname" value='${sessionScope.SESS_NICKNAME}' readonly  style=" width: 200px; border:none;"> --%>
	                        </span>
	                      </div>   
                    <div class="reply-sbox">
                        <textarea class="reply-input" id="rcontent" cols="100" rows="3" name="rcontent" style="margin-top:1rem;"></textarea>
                    </div>
                    	<div class="regBtn">
           					<button id="Reply_regist" class="btn btn-warning"> 댓글등록</button>
    					</div>
    					<br>
    <div class="reply_Box" style="border:1px solid gray;" id="replyBox"> 
    <!-- 댓글이 들어갈 박스 -->
	</div>
   </div>
		
</div>
</body>
</html>