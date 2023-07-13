<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나가</title>
<script type="text/javascript">
	function getout(){
			
			var data=$("form").serialize();
			$.ajax(	
				{
					url:"removeM",
					type:"post",
					//contentType:"application/x-www-form-urlencoded;charset=UTF-8",
					data:"data",
					
					success:function(data,textStatus){
						if
						alert("success");
					},error:function(jqHXR, textStatus, errorThrown){
						console.log(jqHXR);
						console.log(textStatus);
						console.log(errorThrown);
					}
				}
			);
			return true;
		

	
		/* var email = document.getElementsByName("email").value;
		var pwd = document.getElementsByName("pwd").value;	
		
		 String id = (String)session.getAttribute("SESS_EMAIL"); 
		 String pw = (String)session.getAttribute("SESS_PWD"); 
		
		if(id == email && pw == pwd){
			if (confirm("정말로 탈퇴하시겠습니까?")) {
	        	// 사용자가 Yes를 선택한 경우 삭제 동작을 수행할 코드 작성
				location.href = "${pageContext.servletContext.contextPath}/main/main";
	    		} else {
	        // 사용자가 No를 선택한 경우 아무 동작도 수행하지 않음
	     		  alert("그냥 나갓");
	     		 location.href = "${pageContext.servletContext.contextPath}/mypage/removeM";
	    		}
		}
		return false; */
	}	
</script>


</head>
<body>
	<form action="${pageContext.servletContext.contextPath }/mypage/remove" method = "get">
				<div class="form-group has-feedback">
					<label class="control-label" for="email">이메일</label>
					<input class="form-control" type="text" id="email" name="email" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="pwd">비밀번호</label>
					<input class="form-control" type="text" id="pwd" name="pwd" />
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" onclick="getout();" >나가</button>
				</div>
	</form>
	
	
</body>
</html>