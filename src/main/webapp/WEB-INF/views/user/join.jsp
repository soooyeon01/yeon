<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>옥독캣 - 회원가입</title>

        <link href="${root}/resources/bootstrap/css/styles.css" rel="stylesheet" />
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>     
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        
          <script>
        function verifyField(){
            let element = document.getElementById("name");
            let msg = "이름을 입력하세요";
            if( !isValid (element,msg) ){
                return false;
            }
            element  = document.getElementById("email");
            msg = "이메일을 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            }
            if( !emailCheck() ){  // 중복 체크 추가
                return false;
            }
       		element  = document.getElementById("nickname");
            msg = "닉네임을 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            if( !nicknameCheck() ){  // 중복 체크 추가
                return false;
            }
            element  = document.getElementById("phone");
            msg = "핸드폰 번호를 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            if( !phoneCheck() ){  // 중복 체크 추가
                return false;
            }
            element  = document.getElementById("pwd");
            msg = "비밀번호를 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            }
            element  = document.getElementById("pwd-double-check");
            msg = "비밀번호를 한 번 더 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            // 전송하기전에 불일치를 확인하여야함.
            let originObj = document.getElementById("pwd");
            let checkObj = document.getElementById("pwd-double-check");
            if(originObj.value != checkObj.value){
                alert("비밀번호가 불일치 합니다.");
                checkObj.focus();
                return false;
            }             
            element  = document.getElementById("phone");
            msg = "숫자로만 핸드폰 번호를 입력하세요.";
            if( !number (element,msg) ){
                return false;
            } 
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
            return result;
        }
        

        function doubleCheck(value){
            let origin = document.getElementById("pwd").value;
            let boxSpan = document.getElementById("box-span");
            
            if(origin == value){
                //일치함
                boxSpan.className = "box-span-on";
                boxSpan.textContent = "일치함";
                
            }else{
                //불일치
                boxSpan.className = "box-span-off";
                boxSpan.textContent = "불일치함";
            }

        }

        function emailCheck(){
            var email = $('#email').val();
            var result = true;
            
            $.ajax({
                url:'./emailCheck', //Controller에서 요청 받을 주소
                type:'post', //POST 방식으로 전달
                data:{email:email},
                dataType:'json',
                async: false,
                success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
                    if(cnt==1){ // cnt가 1일 경우 -> 이미 존재하는 아이디 
                    	alert("이미 사용 중인 이메일입니다.");
                    	result = false;
                    }
                },
                error:function(){
                  
                }
            });
            return result;
            };
            function checkEmail() {
                if (emailCheck()) {
                    alert("사용 가능한 이메일입니다.");
                }
            }
            
            function nicknameCheck(){
                var nickname = $('#nickname').val();
                var result = true;
                
                $.ajax({
                    url:'./nicknameCheck', //Controller에서 요청 받을 주소
                    type:'post', //POST 방식으로 전달
                    data:{nickname:nickname},
                    dataType:'json',
                    async: false,
                    success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
                    	console.log("ajax cnt : "+cnt);
                        if(cnt==1){ // cnt가 1일 경우 -> 이미 존재하는 아이디 
                        	alert("이미 사용 중인 닉네임입니다.");
                        	result = false;
                        }
                    },
                    error:function(){
      
                    }
                });
                return result;
                };
                function checkNickname() {
                    if (nicknameCheck()) {
                        alert("사용 가능한 닉네임입니다.");
                    }
                }
                
                function phoneCheck(){
                    var phone = $('#phone').val();
                    var result = true;
                    
                    $.ajax({
                        url:'./phoneCheck', //Controller에서 요청 받을 주소
                        type:'post', //POST 방식으로 전달
                        data:{phone:phone},
                        dataType:'json',
                        async: false,
                        success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
                        	console.log("ajax cnt : "+cnt);
                            if(cnt==1){ // cnt가 1일 경우 -> 이미 존재하는 아이디 
                            	alert("이미 사용 중인 번호입니다.");
                            	result = false;
                            }
                        },
                        error:function(){
                       
                        }
                    });
                    return result;
                    };
                    function checkPhone() {
                        if (phoneCheck()) {
                            alert("사용 가능한 번호입니다.");
                        }
                    }
   

    </script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3></div>
                                    <div class="card-body">
                                        <form action="${root}/user/join" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="name" id="name" type="text" />
                                                <label for="name">이름</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="email" id="email" type="email">                                                                                              
                                                <label for="email">이메일</label>
                                                <button type="button" id="emailBtn" name="emailBtn" onclick="checkEmail();">중복</button>                                              
                                                
                                            </div>
                                            <div class="form-floating mb-3">
                                                 <input class="form-control" name="nickname" id="nickname" type="text" />
                                                <label for="nickname">닉네임</label>
                                                  <button type="button" id="nicknameBtn" name="nicknameBtn" onclick="checkNickname();">중복</button>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="phone" id="phone" type="tel" />
                                                <label for="phone">핸드폰 (-없이 숫자만 입력하세요)</label>
                                                  <button type="button" id="phoneBtn" name="phoneBtn" onclick="checkPhone();">중복</button>
                                            </div>
                                            
                                            
                                            
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="pwd" name="pwd" type="password"  />
                                                        <label for="pwd">비밀번호</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="pwd-double-check" type="password"  onkeyup="doubleCheck(this.value);"/>
                                                        <span id="box-span" class="box-span-on"></span>
                                                        <label for="pwd-double-check">비밀번호 확인</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid">
                                                <input class="btn btn-primary btn-block" type="submit" value="전송" onclick="return verifyField();">
                                                </div>
                                            </div>
                                         </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="${root}/user/login">로그인 하러 가기</a></div>
                                    </div>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>
