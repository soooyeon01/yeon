<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload-action</title>

</head>
<body>
   ${msg }
   <br>
   <a href="${pageContext.servletContext.contextPath }/upload/uploadForm">파일업로드 계속하기</a>
   <br>
   업로드된 이미지 파일
   <div>
      <img src="${pageContext.servletContext.contextPath }/display/attach?fileName=${originalFileName}"
         style="border: 1px solid red" />
   </div>
</body>
</html>