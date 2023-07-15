<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$.ajax({
    url: "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic",
    data: {
        pageNo: 1,
        numOfRows: 1000,
        serviceKey: "8mI5YJHYDClBCO0nVGTefXN%2FNRNDL4R68OP9EmufvlXPqdTKQSDm%2BsFUOYWKMuHHs%2Bi%2B1wxPQXr5HDnyjtr%2B8A%3D%3D"
    },
    type: "GET",
    dataType: "xml",
    success: function(response) {
        // 데이터 처리를 생략하고 그냥 로그에 출력
        console.log(response);
    },
    error: function(xhr, status, error) {
        // 에러 처리
        console.log("Error: " + error);
    }
});
</script>
</head>
<body>

</body>
</html>