<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload-form</title>

</head>
<body>
   <form action="${pageContext.servletContext.contextPath }/upload/uploadFormAction" method="post" enctype="multipart/form-data">
      <input type="file" name="uploadFile" accept="image/*" /> <!-- multiple="multiple" -->
      <input type="submit" ></input>
   </form> 
</body>
</html>