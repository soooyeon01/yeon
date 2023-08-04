<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.domain.CommunityDTO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>커뮤니티</title>
        <!-- <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" /> -->
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
        <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet">
     	<script>
    	function logout() {
    		if (confirm("로그아웃 하시겠습니까?")) {
    		location.href = "${root}/user/logout";
    	 	}
    	}
     	</script>
        <style> 
		.link:hover {cursor: url(https://cur.cursors-4u.net/nature/nat-2/nat186.cur), auto !important;}
       a:hover{
                background-color: #feeaa5;
            }
            a {
			   text-decoration-line: none;
			   color: inherit;
			}
            .main{
            padding-top: 0.7cm;
            padding-left: 1.0cm;
            padding-right : 1.5cm;
            padding-bottom : 3cm;
            height: 120px;
            }         
            .bg-yellow {
              --bs-bg-opacity: 1;
              background-color: #feeaa5 !important;
         }
         .main1{
         border-bottom : 1px solid #645326;
          padding-bottom : 2px;
          padding-top : 2px;
         }
         .tab{
             padding-bottom : 0;
             padding-top : 0;
            border-bottom : 1px solid #645326;
            border-top : 1px solid #645326;
         }
         
         .img_main{
         width: 60%;
          margin: 0px auto;
          display: block;
          width: 250px; height: 90px;
          }
          .bgcolor{
         background-color: #f9f8f3;
          }
          #tp5 {
          border: 1px;
          border-bottom: 1px;
          }
          .mytb {
    		width: 50%;
			}
		#tptp {
		display : flex;
		}
		.ttt th{
			background-color:#feeaa5;
		}
		#sht{
			width: 15rem;
		}
		#lng {
			width: 50rem;
		}
		#toosht {
			width: 8rem;
		}
		#tooosht {
			width: 4rem;
		}
		 .btn{
				width: 15rem;
				height: 5rem;
				margin-left:1rem;
				margin-right:1rem;
		}
        </style>
        <style type="text/css">
		  .nanum{ font-family: 'NanumSquareNeo'; }
		  .nanumB{font-family: 'NanumSquareNeoBold';}      
		</style>
<style>
/* a태그 스타일 */
          a {
   text-decoration-line: none;
   color: inherit;
   }
</style>
</head>
 <body class="sb-nav-fixed bgcolor nanum" > 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
           
           <!-- 로그인 로그아웃 마이페이지 반응형 -->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
             <div class="input-group">
             <% String id = (String)session.getAttribute("SESS_EMAIL"); %>
             <% String nickname = (String)session.getAttribute("SESS_NICKNAME"); %>
              
            <%  if( id != null) { %>
            <div style="padding:6px 10px;  font-size:14px;">
               ♡<b>${sessionScope.SESS_NICKNAME}</b>님 환영합니다♡
            </div>
                   <a type="button" onclick="logout();" style="font-size: 14px; padding: 6px 5px;">로그아웃</a>
                   <a href="${root}/mypage/mypage" type="button" style="font-size: 14px; padding: 6px 5px;">마이페이지</a>                          
            <%} else{%>
                <a href="${root}/user/login" type="button" class="btn" style="font-size: 14px; padding: 6px 5px;">로그인</a>                                         
            <%}  %> 
                </div>
            </form>     
            </nav>         
            
         <!-- 로고 -->              
        <nav class="main bg-white" >
           <a class="mainlogo" onclick="location.href='${root}/main/main'" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav>
        
        <!-- 상단바 메뉴 -->
         <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/pet/petall"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/shel/shelall"><b>보호소</b></a>
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/with/withca"><b>위드펫</b></a>
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/community/clist"><b>커뮤니티</b></a>
          <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${root}/notice/nlist"><b>공지사항</b></a>
            </nav>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-10 pt-5 ps-4" style="width:80%;">
				<h2 class="mt-1 mb-3"><b>커뮤니티 페이지</b></h2>
			<div id="tptp">
				<div class="card mb-4  mytb">
					<div class="card-header">
						<i class="fas fa-table me-1">조회수 top 5</i>
					</div>
					<div class="card-body">
						<table class="datatable-table ttt"> 
							<thead>
								<tr>
									<th id="sht">제목</th>
									<th>작성자</th>
									<th id="toosht">작성일</th>
									<th id="tooosht">조회수</th>
								</tr>

							</thead>

							<tbody>
								<c:forEach var="viewCntList" items="${requestScope.viewCntList}"
									varStatus="status">
									<c:set var="fixdate" value="${fn:substring(viewCntList.reg_date, 0, 11)}" />
									<tr>
										<%-- <td>${communityDTO.c_no}</td> --%>
										<td><a
											href="${pageContext.servletContext.contextPath}/community/commuSel?c_no=${viewCntList.c_no}">${viewCntList.title} (${viewCntList.reply_cnt})</a></td>
										<td>${viewCntList.nickname}</td>
										<td>${fixdate}</td>
										<td>${viewCntList.view_cnt}</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
					</div>
				</div>
				<div class="card mb-4  mytb">
					<div class="card-header">
						<i class="fas fa-table me-1">추천수 top 5</i>

					</div>
					<div class="card-body">
						<table class="datatable-table ttt">
							<thead>
								<tr>
									<th id="sht">제목</th>
									<th>작성자</th>
									<th id="toosht">작성일</th>
									<th id="tooosht">추천수</th>
								</tr>

							</thead>

							<tbody>
								<c:forEach var="likeCntList" items="${requestScope.likeCntList}"
									varStatus="status">
									<c:set var="fixdate" value="${fn:substring(likeCntList.reg_date, 0, 11)}" />
									<tr>
										<%-- <td>${communityDTO.c_no}</td> --%>
										<td><a
											href="${pageContext.servletContext.contextPath}/community/commuSel?c_no=${likeCntList.c_no}">${likeCntList.title} (${likeCntList.reply_cnt})</a></td>
										<td>${likeCntList.nickname}</td>
										<td>${fixdate}</td>
										<td>${likeCntList.like_cnt}</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
					</div>
				</div>
			</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1">전체 글 목록</i>

					</div>
					<div class="card-body">
						<table id="datatablesSimple" class="ttt">
							<thead>
								<tr>
									<!-- <th>글번호</th> -->
									<th id="lng">제목</th>
									<th>작성자</th>
									<th id="toosht">작성일</th>
									<th id="tooosht">조회수</th>
									<th id="tooosht">추천수</th>
								</tr>

							</thead>

							<tbody>
								
								<c:forEach var="communityDTO"
									items="${requestScope.communityList}" varStatus="status">
									<c:set var="fixdate" value="${fn:substring(communityDTO.reg_date, 0, 11)}" />
									<tr>
										<%-- <td>${communityDTO.c_no}</td> --%>
										<td><a
											href="${pageContext.servletContext.contextPath}/community/commuSel?c_no=${communityDTO.c_no}">${communityDTO.title} (${communityDTO.reply_cnt})</a></td>
										<td>${communityDTO.nickname}</td>
										<td>${fixdate}</td>
										<td>${communityDTO.view_cnt}</td>
										<td>${communityDTO.like_cnt}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<c:if test="${sessionScope.SESS_NICKNAME!=null}">
				<input type="button" class="btn btn-warning" style="float:right" value="글쓰기" onclick="location.href='newCommu'">
				</c:if>
				<%-- <%@ include file="../import/page-community.jsp" %> --%>
				<%--  <jsp:include page="/view/import/page-nation.jsp"></jsp:include> 
                        	<C:import url="/view/import/page-nation.jsp"></C:import> --%>

	</div>
		</main>
	  <footer class="py-4 bg-light mt-auto">
         <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
               <div class="text-muted" style="padding-top:120px;">Website 2023 &copy; Happy OkDogCat</div>

               <div></div>
            </div>
         </div>
      </footer>
	</div>
</body>
</html>