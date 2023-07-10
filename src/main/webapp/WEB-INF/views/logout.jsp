
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.swing.JOptionPane" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<script>
<%-- function logout() {
    if (confirm("로그아웃 하시겠습니까?")) {
        location.href = "${root}/logoutProcess";
    }
}

	var msg = '<%= request.getParameter("msg")%>';
  		console.log(msg);
    	window.onload = function(){
    		showMsg();
    	}
    	
    	function showMsg(){
	    	if( msg != null && msg != 'null' && msg != '' ){
	    		alert(msg);
	    		console.log("완료");
	    	}
    	} --%>
</script>
</head>
<body>
<<<<<<< HEAD
	<%
	session.removeAttribute("email");
	
	response.sendRedirect("/view/main.jsp");


	 response.sendRedirect(request.getContextPath() + "/main");
	%>
</body>
</html>