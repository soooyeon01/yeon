<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>옥독캣 - 회원가입</title>
        <link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/styles.css" rel="stylesheet" />
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
       		element  = document.getElementById("nickname");
            msg = "닉네임을 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            element  = document.getElementById("phone");
            msg = "핸드폰 번호를 입력하세요.";
            if( !isValid (element,msg) ){
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
        function checkEmail(){
            var email = $('#emial').val(); //id값이 "id"인 입력란의 값을 저장
            $.ajax({
                url:'./emailCheck', //Controller에서 요청 받을 주소
                type:'post', //POST 방식으로 전달
                data:{id:id},
                success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
                    if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                        $('.id_ok').css("display","inline-block"); 
                        $('.id_already').css("display", "none");
                    } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                        $('.id_already').css("display","inline-block");
                        $('.id_ok').css("display", "none");
                        alert("아이디를 다시 입력해주세요");
                        $('#id').val('');
                    }
                },
                error:function(){
                    alert("에러입니다");
                }
            });
            };
        
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
                                        <form action="${pageContext.servletContext.contextPath}/user/join" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="name" id="name" type="text" />
                                                <label for="name">이름</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="email" id="email" type="email" oninput="checkEmail()"/>                                                                                              
                                                <label for="email">이메일</label>
                                                
                                                <span class="emailOn">사용 가능한 아이디입니다.</span>
												<span class="emailOff">누군가 이 아이디를 사용하고 있어요.</span>
                                                
                                            </div>
                                            <div class="form-floating mb-3">
                                                 <input class="form-control" name="nickname" id="nickname" type="text" />
                                                <label for="nickname">닉네임</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="phone" id="phone" type="tel" />
                                                <label for="phone">핸드폰 (-없이 숫자만 입력하세요)</label>
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
                                        <div class="small"><a href="${pageContext.servletContext.contextPath}/user/login">로그인 하러 가기</a></div>
                                    </div>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
