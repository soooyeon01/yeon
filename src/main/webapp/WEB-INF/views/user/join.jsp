<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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
            if (!isValid(element, msg) || !emailCheck()) {
                return false;
            }
       		element  = document.getElementById("nickname");
            msg = "닉네임을 입력하세요.";
            if (!isValid(element, msg) || !nicknameCheck()) {
                return false;
            } 
            element  = document.getElementById("phone");
            msg = "핸드폰 번호를 입력하세요.";
            if (!isValid(element, msg) || !phoneCheck()) {
                return false;
            } 
            element  = document.getElementById("pwd");
            msg = "비밀번호를 입력하세요.";
            if(!isValid (element,msg) || !isPasswordValid(element.value)){
                return false;
            }
            element  = document.getElementById("pwd-double-check");
            msg = "비밀번호를 한 번 더 입력하세요.";
            if( !isValid (element,msg) ){
                return false;
            } 
            // 전송하기 전 불일치를 확인
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
            element = document.getElementById("emailAuth");
            msg = "인증번호를 입력하세요.";
            if (!isValid(element, msg)) {
              return false;
            }

            if (!auth) {
              alert("이메일 인증번호가 일치하지 않습니다.");
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
        

        // 비밀번호와 확인 비밀번호 확인
        function checkPassword() {
          const password = document.getElementById("pwd").value;
          const confirmPassword = document.getElementById("pwd-double-check").value;
          const passwordStatus = document.getElementById("password-status");

          if (password !== "" && confirmPassword !== "") {
            if (password === confirmPassword) {
              passwordStatus.style.color = "green";
              passwordStatus.innerHTML = "일치합니다";
            } else {
              passwordStatus.style.color = "red";
              passwordStatus.innerHTML = "불일치합니다";
            }
          } else {
            passwordStatus.innerHTML = "";
          }
        }
        
        //비밀번호 lenght
        function isPasswordValid(password){
            if(password.length < 8){
                alert("비밀번호는 8자 이상이어야 합니다.");
                return false;
            }
            return true;
        }
		// 중복체크
            function nicknameCheck(){
                var nickname = $('#nickname').val(); //nickname 변수 선언
                var result = true;
                
                if (nickname.trim() === '') {
                    $('#non').css("display", "none");
                    $('#noff').css("display", "none");
                    return;
                }
                $.ajax({
                    url:'./nicknameCheck', //Controller에서 요청 받을 주소
                    type:'post', //POST 방식으로 전달
                    data:{nickname:nickname},
                    dataType:'json',
                    async: false,
                    success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
                        if(cnt==1){ // cnt가 1일 경우 -> 이미 존재하는 아이디 
                          $('#non').css("display", "none");
                          $('#noff').css("display","inline-block");
                            result = false;
                        } else {
                          $('#non').css("display","inline-block");
                          $('#noff').css("display", "none");
                        }
                    },
                    error:function(){
                    }
                });
                return result;
            };
                
            function phoneCheck() {
            	  var phone = $("#phone").val();
            	  var result = true;

            	  if (phone.trim() === "") {
            	    $("#pon").css("display", "none");
            	    $("#poff").css("display", "none");
            	    return;
            	  }
            	  if (!/^\d+$/.test(phone)) {
            	    alert("숫자로만 핸드폰 번호를 입력하세요.");
            	    result = false;
            	    return result;
            	  }

            	  $.ajax({
            	    url: "./phoneCheck", // Controller에서 요청 받을 주소
            	    type: "post", // POST 방식으로 전달
            	    data: { phone: phone },
            	    dataType: "json",
            	    async: false,
            	    success: function (cnt) {// 컨트롤러에서 넘어온 cnt값을 받는다
            	      if (cnt == 1) { // cnt가 1일 경우 -> 이미 존재하는 아이디
            	        $("#pon").css("display", "none");
            	        $("#poff").css("display", "inline-block");
            	        result = false;
            	      } else {
            	        $("#pon").css("display", "inline-block");
            	        $("#poff").css("display", "none");
            	      }
            	    },
            	    error: function () {
            	    },
            	  });

            	  return result;
            	}

            	$(document).ready(function () {
            	  $("#phone").on("keyup", function () {
            	    phoneCheck();
            	  });
            	});

                    function emailCheck(){
                        var email = $('#email').val();
                        var result = true;
                        
                        if (email.trim() === '') {
                            $('#eon').css("display", "none");
                            $('#eoff').css("display", "none");
                            return;
                        }
                        $.ajax({
                            url:'./emailCheck', //Controller에서 요청 받을 주소
                            type:'post', //POST 방식으로 전달
                            data:{email:email},
                            dataType:'json',
                            async: false,
                            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
                                if(cnt==1){ // cnt가 1일 경우 -> 이미 존재하는 아이디 
                      				$('#eon').css("display", "none");
                     				$('#eoff').css("display","inline-block");
                                	result = false;
                                } else {
                    				$('#eon').css("display","inline-block");
                    				$('#eoff').css("display", "none");
                                }
                                toggleBtn('emailNum', email, result);
                            },
                            error:function(){
                            }
                        });
                        return result;
                        };
                        
                        
                        
                    // 버튼 잠금
                    function toggleBtn(btnId, inputValue, isEnabled) {
                        const btn = document.getElementById(btnId);
                        if (inputValue.trim() === '' || !isEnabled) {
                            btn.disabled = true;
                        } else {
                            btn.disabled = false;
                        }
                    }
                    
     
                    
                 // 인증번호 유효여부 체크 변수
                    var authNumValid = false;

                 // 타이머 관련 변수
                    var timer;
                    var remainingTime = 0;

                    // 타이머 시작 함수
                    function startTimer() {
                        remainingTime = 180; // 3분 (3분 * 60초)
                        timer = setInterval(function () {
                            remainingTime--;
                            var minutes = Math.floor(remainingTime / 60);
                            var seconds = remainingTime % 60;
                            document.getElementById("time").innerHTML = minutes + "분 " + seconds + "초";
                            if (remainingTime <= 0) {
                                clearInterval(timer);
                                document.getElementById("time").innerHTML = "";
                                alert("인증 시간이 지났습니다. 다시 인증 번호를 받아주세요.");
                                authNumValid = false;
                                document.getElementById("emailAuth").value = "";
                            }
                        }, 1000);
                    }

                    var auth = false;

                 // 이메일 인증번호 발송
                    function sendAuthNum() {
                        var email = $("#email").val();
                        $.ajax({
                            url: "./sendAuthNum",
                            type: "post",
                            data: { email: email },
                            dataType: "text",
                            success: function (msg) {
                                alert("메일이 발송되었습니다.");
                                authNumValid = true;
                                $("#emailAuthBtn").prop("disabled", false);

                                startTimer();
                            },
                            error: function () {
                            }
                        });
                    }

                    function showAuthCompleted() {
                        clearInterval(timer);
                        document.getElementById("time").innerHTML = "인증 완료";
                    }
                    
                    // 이메일 인증번호 확인
                    function checkAuthNum() {     
                        if (!authNumValid) {
                            alert("인증번호 유효 시간이 경과했습니다. 인증 번호를 다시 받아주세요.");
                            return;
                        }
                        
                        var inputNum = $("#emailAuth").val();
                        $.ajax({
                            url: "./checkAuthNum",
                            type: "post",
                            data: { inputNum: inputNum },
                            dataType: "json",
                            success: function (authStatus) {
                                if (authStatus) {
                                    alert("인증번호가 일치합니다.");
                                    auth = true;
                                    
                                    showAuthCompleted();
                                } else {
                                    alert("인증번호가 일치하지 않습니다.");
                                    auth = false;
                                }
                            },
                            error: function () {
                                alert("에러입니다");

                            }
                        });
                    }
                    
                    function removeAuthCompleted() {
                    	  if ($("#emailAuth").val().trim() === "") {
                    	    clearInterval(timer);
                    	    document.getElementById("time").innerHTML = "";
                    	  }
                    	}

    </script>
    
		<style type="text/css">
		/* 중복아이디 존재하지 않는경우 */
		.on{
			color : green;
			display : none;
		}
		/* 중복아이디 존재하는 경우 */
		.off{
			color : red;
			display : none;
		}
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
						<div class="col-lg-7">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">
									   <!-- 로고 -->              
								         <a class="mainlogo" href="${root}/main/main" >
								         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
								         </a>
									</h3>
								</div>
								<div class="card-body">
									<form action="${root}/user/join" method="post">
										<div class="form-floating mb-3">
											<input class="form-control" name="name" id="name" type="text" />
											<label for="name">이름</label>
										</div>
										
										<div class="form-floating mb-3">
											<input class="form-control" name="email" id="email"
												type="email" onkeyup="emailCheck();">
											<label for="email">이메일</label>
											<button class="btn btn-warning" type="button" id="emailNum" name="emailNum"
												disabled="disabled" onclick="sendAuthNum();">인증 번호 받기</button>
												
												<span class="on" id = "eon">사용 가능한 이메일입니다.</span>
												<span class="off" id="eoff" >이메일이 이미 존재합니다.</span>
										</div>


										<div class="form-floating mb-3">
 										 <input class="form-control" name="emailAuth" id="emailAuth" type="text" oninput="removeAuthCompleted();" />
 										 <label for="emailAuth">이메일 인증번호</label>
											<button class="btn btn-warning" type="button" id="emailAuthBtn" name="emailAuthBtn"
												disabled="disabled" onclick="checkAuthNum();">인증번호 확인</button>
												<span id="time" style="padding-left: 10px;"></span>
										</div>

										<div class="form-floating mb-3">
											<input class="form-control" name="nickname" id="nickname" type="text" onkeyup="nicknameCheck();" />
											<label for="nickname">닉네임</label>
											
												<span class="on" id = "non">사용 가능한 닉네임입니다.</span>
												<span class="off" id="noff" >닉네임이 이미 존재합니다.</span>
										</div>
										
										<div class="form-floating mb-3">
											<input class="form-control" name="phone" id="phone" type="tel" onchange="phoneCheck();" />
												<label for="phone">핸드폰 (-없이 숫자만 입력하세요)</label>
												
												<span class="on" id = "pon">사용 가능한 번호입니다.</span>
												<span class="off" id="poff" >번호가 이미 존재합니다.</span>
										</div>

										<div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3 mb-md-0">
											    <input class="form-control" id="pwd" name="pwd" type="password" onkeyup="checkPassword();" />
											    <label for="pwd">비밀번호</label>
											    <span style="font-size : 9pt; color : #8C8C8C;">8자 이상 입력해주세요</span>
											  </div>
											</div>
											
											<div class="col-md-6">
											  <div class="form-floating mb-3 mb-md-0">
											    <input class="form-control" id="pwd-double-check" type="password" onkeyup="checkPassword();" />
											    <label for="pwd-double-check">비밀번호 확인</label>
											    <span id="password-status"></span>
											  </div>
											</div>
										</div>
										<div class="mt-4 mb-0">
											<div class="d-grid">
												<input class="btn btn-warning btn-block" type="submit" value="전송" onclick="return verifyField();">
											</div>
										</div>
									</form>
								</div>
								<div class="card-footer text-center py-3">
									<div class="small">
										<a href="${root}/user/login">로그인 하러 가기</a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
</body>
</html>
