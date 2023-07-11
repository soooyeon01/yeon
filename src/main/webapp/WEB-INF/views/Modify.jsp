<%@ page import="com.java.servlet.vo.MembersVO" %>
<%@ page import="com.java.servlet.vo.MembersVO"%>
<%@ page import="com.java.servlet.dao.MypageDAO" %> 
<%@ page import="com.java.servlet.dao.impl.MypageDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	request.setCharacterEncoding("UTF-8");  %>
<%	
           String id = (String)session.getAttribute("SESS_EMAIL"); 
		    // 세션에 저장된 아이디를 가져와서
		    // 그 아이디 해당하는 회원정보를 가져온다.
		    MypageDAO dao = MypageDAOImpl.getInstance();
		    MembersVO vo = dao.selectMypage(id);
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<script src="http://code.jquery.com/jquery.js" ></script>	
	<script>
	function form_check() {						
		if($('#pwd').val().length == 0) {
			alert("비밀번호를 입력하세요");
			$('#pwd').focus();
			return;
		}
		
		if($('#pwd').val() != $('#pwd_check').val()) {
			alert("비밀번호가 일치하지 않습니다");
			$('#pwd').focus();
			return;
		}

		submit_ajax();
	}
	
	function submit_ajax() {
		var queryString = $("#reg_frm").serialize();
		$.ajax({
            url: '/Jsp21-HW-DAO-Ajax/ModifyProcess',  
			//url: '/Jsp21-HW-DAO-Ajax/check/modifyOk.jsp',  
            type: 'POST',
            data: queryString,
            dataType: 'text',
            success: function(json) {  //json은 걍 변수명
                var result = JSON.parse(json);
            	if(result.code == "success") {
            		alert(result.desc)
            		window.location.replace("main.jsp"); 
            	} else {
            		alert(result.desc);
            	}            	
            }
        });
	}
	</script></head>
<body>
	<form id="reg_frm"> 
        아이디 : <%=vo.getEmail() %><br>
        비밀번호 : <input type="password" id="pw" name="pw" size="20"/><br/>
        비밀번호확인 : <input type="password" id="pw_check" name="pw_check" size="20"/><br/>
        이름 : <%= vo.getName() %><br>
        전화번호 : <input type="text" id="address" name="address" size="50" value="<%=vo.getPhone() %>"/><br/><p>
        <input type="button" value="수정" onclick="form_check()">&nbsp;&nbsp;&nbsp;
        <input type="reset" value="취소" onclick="${root}/mypage">
    </form>
</body>
</html>
