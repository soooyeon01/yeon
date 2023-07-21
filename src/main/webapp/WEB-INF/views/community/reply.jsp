<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>댓글</title>
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> -->
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
		<script>
			$('#Reply_regist').click(function() {
			
   			//Json으로 전달할 파라미터 변수선언
   			let c_no = $('#c_no').val();
   			let nickname = $('#nickname').val();
   			let rcontent = $('#rcontent').val();
   			
   			console.log(c_no);
   			console.log(nickname);
   			console.log(rcontent);
   		
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
   				success:function(data){
   					console.log('통신성공' + data);
   					if(data === 'newSuccess') {
   						alert('댓글 등록이 완료되었습니다.');
   						console.log('댓글 등록 완료');
   						$('#nickname').val(nickname);
   	   					$('#rcontent').val('');
   	   					getList();
   					} else {
   						alert('로그인 이후 이용해주시기 바랍니다.');
   						console.log('댓글 등록 실패');
   					}
   				},
   				error:function(){
   					alert('통신실패');
   				}
   			});// 댓글 비동기 끝
   			
		});// 댓글등록 이벤트 끝
		
		getList();
		
		function getList() {
			
			var c_no = $('#c_no').val();
			const nickname = $('#nickname').val();
   			const rcontent = $('#rcontent').val();
   			/* const com_no = ${com}; */
			$.getJSON(
					"<c:url value='/Comment/CommentList/'/>" + c_no,
				function(data) {
					if(data.total > 0){
						var list = data.list;
						
						var reply_html = "<div>";
						
						$('#count').html(data.total);
						for(i = 0;i < list.length;i++){
							var rcontent = list[i].rcontent;
							var nickname = list[i].nickname;
							reply_html += "<div><span id='nickname'><strong>" + nickname + "</strong></span><br/>";
							reply_html += "<span id='rcontent'>" + rcontent + "</span><br>";
							if(nickname === $("#nickname").val()){
								reply_html += "<span id='delete' style='cursor:pointer;' data-id ="+rcontent+">[삭제]</span><br></div><hr>";
								 
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
		</script>
	</head>
<body>
	<div class="reply-box">
                    <input type="hidden" id="c_no" name="c_no" value="${selectone.c_no}">
   		                 <div class="reply-count">댓글 <span id="count">0</span></div>

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
           			<button id="Reply_regist"> 댓글등록</button>
    </div>
    <div class="reply_Box" style="border:1px solid gray;"> 
    <!-- 댓글이 들어갈 박스 -->
	</div>
   
   </div>
 
</body>
</html>