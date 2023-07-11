<%@ page import="com.java.servlet.vo.MembersVO" %>
<%@ page import="com.java.servlet.vo.MembersVO"%>
<%@ page import="com.java.servlet.dao.MypageDAO" %> 
<%@ page import="com.java.servlet.dao.impl.MypageDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	request.setCharacterEncoding("UTF-8");  %>

<jsp:useBean id="vo" class="com.java.servlet.vo.MembersVO" />
<jsp:setProperty name="dto" property="*" />

<%
	String email = (String)session.getAttribute("SESS_EMAIL");
	vo.setEmail(email);
	
	MypageDAO dao = MypageDAOImpl.getInstance();
	String json_data = "";

	
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>
