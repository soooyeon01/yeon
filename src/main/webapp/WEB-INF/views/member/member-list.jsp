<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Member-list</title>
</head>
<body>
<h1>
	Member-list
</h1>
	
<P>  MEMBER-LIST </P>
<p>\${memberDTO} : ${memberDTO}</p>
<p>\${memberDTO.id} : ${memberDTO.id}</p>
<p>\${memberDTO.pwd} : ${memberDTO.pwd}</p>
<p>\${memberDTO.name} : ${memberDTO.name}</p>
<p>\${memberDTO.jumin} : ${memberDTO.jumin}</p>


<p>${memberList}.</p>
<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>주민번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		
	</tr>
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>주민번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		
	</tr>
	<c:forEach var="member" items="${memberList }">
	<tr>
		<td>${member.m_no }</td>
		<td>${member.name }</td>
		<td>${member.jumin }</td>
		<td>${member.id }</td>
		<td>${member.pwd }</td>
		
	</tr>
	
	</c:forEach>
	
</table>
</body>
</html>


