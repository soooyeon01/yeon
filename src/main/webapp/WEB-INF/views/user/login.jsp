<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>옥독캣 - 로그인</title>
      	<link href="${root}/resources/bootstrap/css/styles.css" rel="stylesheet" /> 
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
         <script>
    	var msg = '<%= request.getParameter("msg")%>';
    	window.onload = function(){
    		showMsg();
    	}
    	
    	function showMsg(){
	    	if( msg != null && msg != 'null' && msg != '' ){
	    		alert(msg);
	    	}
    	}

        function verifyField(){
            let element = document.getElementById("email");
            let msg = '이메일을 입력하세요.';
            if( !isValid (element,msg) ){
                return false;
            }
            element  = document.getElementById("pwd");
            msg = "비밀번호를 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            }
           
            return true;
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
            return result;
        }
        // capslock 알림
        function capsLockCheck(event) {
          var charCode = event.which || event.keyCode;
          var shiftKey = event.shiftKey ? event.shiftKey : ((charCode == 16) ? true: false);
          var caps_lock_warning = document.getElementById('caps-lock-warning');

          if ((charCode >= 65 && charCode <= 90 && !shiftKey) || (charCode >= 97 && charCode <= 122 && shiftKey)) {
            caps_lock_warning.style.display = "block";
          } else {
            caps_lock_warning.style.display = "none";
          }
        }
    </script>
    
		<style type="text/css">
          .bgcolor{
         background-color: #f9f8f3;
          }
          
          a {
		   text-decoration-line: none;
		   color: inherit;
			}
				
		</style>
	    </head>
	<body class="bgcolor">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">
                                     <!-- 로고 -->              
								         <a class="mainlogo" href="${root}/main/main" >
								         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
								         </a>
                                    </h3>
                                    </div>
                                    <div class="card-body">
                                        <form action="${root}/user/login" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" />
                                                <label for="email">이메일</label>
                                            </div>
                                            <div class="form-floating mb-3">
											    <input class="form-control" id="pwd" name="pwd" type="password" placeholder="pwd" onkeypress="capsLockCheck(event);" />
											    <label for="pwd">비밀번호</label>
											    <div id="caps-lock-warning" style="display: none; color: red; font-size: 9pt;">CAPS LOCK이 켜져있습니다.</div>
											</div>
                                     	<div class="mt-4 mb-0">
											<div class="d-grid">
												<input class="btn btn-warning btn-block" type="submit" value="로그인" onclick="return verifyField();">
											</div>
										</div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small">
                                        <a href="${root}/user/join">회원가입</a>
                                        <a>|</a>
                                        <a href="${root}/user/findEmail">이메일 찾기</a>
                                        <a>|</a>
                                        <a href="${root}/user/findPwd">비밀번호 찾기</a>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
    </body>
</html>
