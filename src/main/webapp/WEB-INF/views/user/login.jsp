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
    	console.log("dsadsa");
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
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">로그인</h3></div>
                                    <div class="card-body">
                                        <form action="${root}/user/login" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" />
                                                <label for="email">이메일</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="pwd" name="pwd" type="password" placeholder="pwd" />
                                                <label for="pwd">비밀번호</label>
                                            </div>
                                            <!-- <div class="form-check mb-3">
                                                <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
                                                <label class="form-check-label" for="inputRememberPassword">Remember Password</label>
                                            </div> -->
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="${root}/user/join">회원가입</a> 
                                                <%-- <a class="small" href="${pageContext.servletContext.contextPath}/view/findEmail.jsp">이메일 찾기</a> --%>
                                                
                                                <input class="btn btn-primary" type="submit" value="로그인" onclick="return verifyField();">
                                                <!-- <a class="btn btn-primary" type="submit" onclick="return verifyField();">로그인</a> -->
                                                

                                                
                                                
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small">
                                        <a href="${root}/user/findEmail">이메일 찾기</a>
                                        <a href="${root}/user/findPwd">비밀번호 찾기</a>
                                        </div>
                                        
                                        <%-- <div class="small"><a href="${pageContext.servletContext.contextPath}/view/join.jsp">회원가입 하러 가기</a></div> --%>
                                     
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <!-- <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div> -->
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
    </body>
</html>
