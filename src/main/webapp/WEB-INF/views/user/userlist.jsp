<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.spring.domain.MembersDTO"%>
<%@ page import="com.spring.mapper.MypageMapper"%>
<%@ page import="com.spring.controller.UserController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>회원 리스트</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
<link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
   function poobkick(){
       console.log("리스트를 불러옵니다.");
       var checkedEmails = [];
  
        if (confirm("회원을 삭제하시겠습니까?")) {
            console.log("회원 삭제");
        
            $("input[name='byebye']:checked").each(function() {
               
               var userEmail = $(this).val();
                checkedEmails.push(userEmail);
                console.log("체크이메일 " + checkedEmails.length);
            });

            $.ajax({
                type: "post",
                dataType : "json",
                url: "<c:url value='/user/kick'/>",
                //data: checkedEmails,
                
                data:{ 
                   userEmail: checkedEmails.join(",") },
                success: function(data) {
                    if(data.result===1){
                       console.log("통신성공" + data);
                        alert("회원이 삭제되었습니다");
                        location.href = "${root}/user/userlist";
                    }else {
                       alert("삭제할 회원을 선택하세요.");
                    }
                
                },
                error: function(jqHXR, textStatus, errorThrown) {
                   console.log(jqHXR);
                   console.log(textStatus);
                   console.log(errorThrown);
                   alert("통신실패");
                }
            }); //회원 삭제 비동기
        } else {
            return false;
        }
    }
</script> 
<style>
.deleteMember {
   color: darkgray;
   text-align: right;
}

tr {
   text-align: center;
}

a:hover {
   background-color: #feeaa5;
}

.main {
   padding-top: 0.7cm;
   padding-left: 1.0cm;
   padding-right: 1.5cm;
   padding-bottom: 3cm;
   height: 120px;
}

.bg-yellow { -
   -bs-bg-opacity: 1;
   background-color: #feeaa5 !important;
}

.main1 {
   border-bottom: 1px solid #645326;
   padding-bottom: 2px;
   padding-top: 2px;
}

.tab {
   padding-bottom: 0;
   padding-top: 0;
   border-bottom: 1px solid #645326;
   border-top: 1px solid #645326;
}

.img_main {
   width: 60%;
   margin: 0px auto;
   display: block;
   width: 250px;
   height: 90px;
}

.bgcolor {
   background-color: #f9f8f3;
}

</style>
</head>
<body class="sb-nav-fixed bgcolor"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
                 <div class="input-group">
                <% String email = (String)session.getAttribute("SESS_EMAIL"); %>
              <%System.out.println(email);%>
              <div style="margin-top:5px;">♡${sessionScope.SESS_NICKNAME}님 환영합니다♡</div>
         <%  if( email != null) { %>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="location.href='${root}/mypage/mypage'" style="font-size: 14px;">마이페이지</button>                  
            <%} else{%>
                <button type="button" class="btn" onclick="location.href='${root}/user/login'" style="font-size: 14px;">로그인</button>                 
            <%}  %>
                </div>
            </form>      
            </nav>
            <script>
               function logout() {
             if (confirm("로그아웃 하시겠습니까?")) {
             location.href = "${root}/user/logout";
                }
         }

            </script>
         <!-- 로고 -->              
        <nav class="main bg-white" >
         <a class="mainlogo" href="${root}/main/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
        <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
         <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/pet/petall"><b>공고</b></a> 
            <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shel/shelall"><b>보호소</b></a>
         <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withca"><b>위드펫</b></a>
         <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
         <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
        </nav>
   <div id="layoutSidenav_content">
      <main>
         <div class="container-fluid px-3 pt-3">
            <div class="card mb-4 ">
               <div class="card-header">
                  <i class="fas fa-table me-1">회원 리스트</i>
               </div>
               <div class="card-body">
                  <table class="datatable-table">
                     <thead>
                        <tr>
                           <th></th>
                           <th>닉네임</th>
                           <th>이메일</th>
                           <th>이름</th>
                           <th>전화번호</th>
                           
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${userList}" var="userList">
                           <tr>
                              <td><input type="checkbox" class="userCheckbox" name="byebye" value="${userList.email}"></td>
                              <td>${userList.nickname}</td>
                              <td id="userEmail">${userList.email}</td>
                              <td>${userList.name}</td>
                              <td>${userList.phone}</td>
                              
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  <button class="btn btn-warning kickBtn" id="kickBtn" onclick="poobkick();">회원 삭제</button>
               </div>
            </div>
            
         </div>
      </main>
      <footer class="py-4 bg-light mt-auto">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
               <div class="text-muted">Copyright &copy; Your Website 2023</div>
            </div>
         </div>
      </footer>
   </div>

</body>
</html>