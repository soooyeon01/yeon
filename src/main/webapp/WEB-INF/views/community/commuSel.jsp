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
					/* "/community/replyList/" + c_no, */
				function(data) {
					if(data.total > 0){
						var list = data.list;
						
						var reply_html = "<div>";
						
						$('#count').html(data.total);
						for(i = 0;i < list.length;i++){
							console.log(data.total);
							console.log(list[i].r_no);
							var rcontent = list[i].rcontent;
							var r_no = list[i].r_no;
							var nickname = list[i].nickname;
							
							reply_html += "<div><span id='nickname'><strong>" + nickname + "</strong></span><br/>";
							//reply_html += "<span id='Rno_"+Rno+"' >" + Rno + "</span><br>";
							reply_html += "<span id='rcontent'>" + rcontent + "</span><br>";

							if(nickname === "${sessionScope.SESS_NICKNAME}"){
<<<<<<< HEAD
								reply_html += "<span id='delete' style='cursor:pointer;' data-id ="+rcontent+" data-rno="+r_no+">[삭제]</span><br></div><hr>";

							if(nickname === "${selectone.nickname}"){
								reply_html += "<span id='delete' style='cursor:pointer;' data-id ="+rcontent+">[삭제]</span><br></div><hr>";

								 
=======
								reply_html += "<span id='delete' style='cursor:pointer;' data-id ="+rcontent+" data-rno="+r_no+">[삭제]</span><br></div><hr>"; 
>>>>>>> c6a395076871536820072c12fd7969cfe0caabf5
							}
							else{
								reply_html += "</div><hr>";
							}
						}
						
						$(".reply_Box").html(reply_html);
						
						
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
		// function test(){
		// 	console.log("clicked");
		// 	$.ajax({
		// 		type :'post',
		// 		url : '<c:url value="/community/likeDown"/>',
		// 		contentType: 'application/json',
		// 		data : JSON.stringify(
		// 				{
		// 					"c_no" : c_no,
		// 					"nickname" : nickname
		// 				}		
		// 			),
		// 		success : function(data) {
		// 			alert('추천 취소 성공');
		// 		},
		// 		error : function() {
		// 			alert( "error" );
		// 		}

		// 	})// 아작스 끝
		// }
		$(document).ready(function() {
		var likeval = ${ldto};
		
		let c_no = ${selectone.c_no};
		let nickname = '${sessionScope.SESS_NICKNAME}';
		if(likeval > 0){
			console.log(likeval + "좋아요 누름");
			$('#btnLike').html("추천하기 취소");
			$('#btnLike').click(function() {
				console.log("clicked");
				$.ajax({
					type :'post',
					url : '<c:url value="/community/likeDown"/>',
					contentType: 'application/json',
					data : JSON.stringify(
							{
								"c_no" : c_no,
								"nickname" : nickname
							}		
						),
					success : function(data) {
						alert('추천 취소 성공');
					},
					error : function() {
						alert( "error" );
					}

				})// 아작스 끝
			})

		}else{
			console.log(likeval + "좋아요 안누름");
			console.log(nickname);
			$('#btnLike').click(function() {
				$.ajax({
					type :'post',
					url : '<c:url value="/community/likeUp"/>',
					contentType: 'application/json',
					data : JSON.stringify(
							{
								"c_no" : c_no,
								"nickname" : nickname
							}		
						),
					success : function(data) {
						alert('추천하기 성공');
					}
				})// 아작스 끝
			});
		}
	})
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
        	<p id="like_cnt" class="form-control" id="like_cnt" name="like_cnt">${selectone.like_cnt}</p>
     	</div>
     </div>
	</form>
	<div class="container bt">
  	<button type="button" class="register col p-3 btn btn-warning my" onclick="toListPage();">목록으로</button>
	<c:if test="${sessionScope.SESS_NICKNAME==selectone.nickname}">
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

						<form method="post">
							<div>
		                        <c:if test = "${sessionScope.nickname== null and sessionScope.SESS_NICKNAME!=selectone.nickname}">
	            					<button type ="button" class="btn btn-warning btnLike" id="btnLike">추천하기</button>    <!-- onclick="test()" -->
	    						</c:if>
	    					</div>		
	                    </form>
   		                 </div>
						 <div class="reply-count">댓글 <span id="count">
   		                 <c:out value="${map.total}"/></span>
   		                 </div>	
   		                 	   <!-- <span class="c-icon"><i class="fa-solid fa-user"></i>  -->
   		                 <div class="reply-name">
	                        <span class="anonym">작성자 : 
	                        <input type="text" class="form-control" id="nickname" name ="nickname" value='${sessionScope.SESS_NICKNAME}' readonly  style="width: 100px; border:none;">
	                        </span>
	                      </div>   
	                   
	                        <!-- </span> -->
                     <!--<img src="/익명.jpg" width ="50px" alt="My Image"><!-->
                    <div class="reply-sbox">
                        <textarea class="reply-input" id="rcontent" cols="80" rows="2" name="rcontent" ></textarea>
                        <!-- <span class="com-function-btn" type="hidden">
                            
                            <a href="#"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="#"><i class="fa-solid fa-trash-can"></i></a>
                         </span> -->
                    </div>
                    	<div class="regBtn">
           					<button id="Reply_regist" class="btn btn-warning"> 댓글등록</button>
    					</div>
    <div class="reply_Box" style="border:1px solid gray;"> 
    <!-- 댓글이 들어갈 박스 -->
	</div>
   </div>
		
</div>
</body>
</html>