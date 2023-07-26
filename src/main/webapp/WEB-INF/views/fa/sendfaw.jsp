<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>이메일 전송</title>
          <link href="${root}/resources/bootstrap/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
             <script>
         	var msg = '<%= request.getParameter("msg")%>';
        	console.log("Dsa");
        	window.onload = function(){
        		showMsg();
        	}
        	
        	function showMsg(){
    	    	if( msg != null && msg != 'null' && msg != '' ){
    	    		alert(msg);
    	    	}
        	}
             
        function verifyField(){
        	element  = document.getElementById("email");
            msg = "이메일을 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            }
            console.log("1");
            element  = document.getElementById("pwd");
            msg = "비밀번호를 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            //form serialize 사용해서 입력값 전부 ajax로 서버에 보내기
            return true;
        }
   
        
        function number(element, msg) {
            let result = false;
            if (isNaN(element.value) || element.value.trim() === '') {
                alert(msg);
                element.focus();
            } else {
                result = true;
            }
            console.log("3");
            return result;
        }
        
        function isValid(element, msg){
            let result = false;
            if(element.value == ''){
                alert(msg);
                element.focus();
                result = false;
            }else{
                result = true;
            }
            console.log("4");
            return result;
        }
        
        
    </script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">이메일 전송</h3></div>
                                    <div class="card-body">
                                        <div class="small mb-3 text-muted">정보를 입력해 주세요</div>
                                        <form name="form" id="form" action="${root}/fa/sendfaw" method="post">
                                        	<div class="form-floating mb-3">
                                                <input class="form-control" id="email" name="email" type="email"  />
                                                <label for="email">이메일</label>
                                            </div>
                                        
                                            
                                            
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <div class="small"><a href="${root}/fa/favoritew">뒤로가기</a></div>
                                                
                                                <input class="btn btn-warning btn-block" type="submit" value="전송" onclick="return verifyField();">
                                                </div>
                                                </form>
                                            </div>
                                        
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                     </main>
                    </div>
               
            </div>
            <div id="layoutAuthentication_footer">
               
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
    </body>
</html>
