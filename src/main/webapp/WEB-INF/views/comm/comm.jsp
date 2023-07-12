<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comm Main</title>
</head>
<body>
	<h1>게쉬판 목록</h1>
		<table border="1">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>닉네임</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="comm" items="${communityList}">
			<tr>
				<td>${comm.c_no}</td>
				<td>${comm.title}</td>
				<td>${comm.nickname}</td>
				<td>${comm.reg_date}</td>
			</tr>
			</c:forEach>
			<input type=submit value="글쓰기" onclick="">
		</table>
</body>
</html>

