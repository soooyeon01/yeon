<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나가</title>
<script type="text/javascript">
	function getout(){
		var email = document.getElementsByName("email").value;
		var pwd = document.getElementsByName("pwd").value;
		var msg='일치하지 않습니다';
		if(email != #{email} ){
			alert(msg);
			return false;
		}else{
		return true;
		}
	}



</script>

</head>
<body>
	<form action="${pageContext.servletContext.contextPath }/mypage/getout" method = "post">
				<div class="form-group has-feedback">
					<label class="control-label" for="email">이메일</label>
					<input class="form-control" type="text" id="email" name="email" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="pwd">비밀번호</label>
					<input class="form-control" type="text" id="pwd" name="pwd" />
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit" onclick="getout();">나가</button>
				</div>
	</form>
	
	
</body>
</html>