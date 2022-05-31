<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%-- <%@ include file="admin_id_session.jsp" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String field=request.getParameter("field"); %>
<% String keyword=request.getParameter("keyword"); %>
<% String pageScale=request.getParameter("pageScale"); %>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Member</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://<%=application.getInitParameter("domain") %>/css/styles.css" rel="stylesheet" />
        <!-- jQeury CDN -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="js/datatables-simple-demo.js"></script>
     	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
      hr {width:200px; margin: 0px auto; margin-top:10px;}
      #member_tab{ text-align:center;}
      .memberTitle{font-weight:bold}
</style>

<script type="text/javascript">
$(function(){
     $("#searchBtn").click(function(){
        chkNull();
    })
    $("#keyword").keydown(function(e){
    	if(e.which==13){
    		chkNull();
    	}//end if
    });//keydown
    $("#pageScale").change(function(){
    	$("#searchFrm").submit();
    })
    
 $("#memberDetail").on('show.bs.modal',function(e){
	 var userid = $(e.relatedTarget).data('id');
	$.ajax({
		url:"detailMember.do",
		data:{
			userid: userid
		},
		dataType:"json",
		type:"get",
		contentType:"application/json",
		async:false,
		success:function(jsonObj){
			var id = jsonObj.userid.split('@');
	  		$("#id").val(id[0]);
	  		$("#server").val(id[1]);
	  		$("#userName").val(jsonObj.name);
	  		$("#tel").val(jsonObj.tel);
	  		$("#zipcode").val(jsonObj.zipcode);
	  		$("#address1").val(jsonObj.address1);
	  		$("#address2").val(jsonObj.address2);
	  		/* $("#subDate").val(jsonObj.subscribe_date); */
		},
		error:function(request, status, error){
			alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error:"+error);
		}
	});
  })//detailMember
	$(".exit").click(function(){
  		$("#confirmExit").modal('show');
  	});
		$("#exitOk").click(function(){
  		$("#confirmExit").modal('hide');
			$("#memberDetail").modal('hide');
		}); 
});//ready
function chkNull(){
	if($("#keyword").val()==""){
		alert("검색어를 입력해주세요.");
		return;
	}//end if
	$("#searchFrm").submit(); 
}//chkNull


</script>
    </head>
    	
    <body class="sb-nav-fixed">
   
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="http://<%=application.getInitParameter("domain") %>/main/index.jsp">Exhibition Admin</a>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="http://<%=application.getInitParameter("domain") %>/main/settings.jsp">Settings</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="http://<%=application.getInitParameter("domain") %>/main/logout.jsp">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <jsp:include page="../commons/navigation.jsp"/>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4" style="width:90%">
                        <h1 class="mt-4">회원 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp" style="text-decoration:none; color:#333;">Dashboard</a></li>
                            <li class="breadcrumb-item active">회원 관리</li>
                        </ol>
                        <!-- 검색창 -->
							<div id="searchDiv" >
                            	<form action="http://<%=application.getInitParameter("domain") %>/admin/member.do" name="searchFrm" id="searchFrm">
			                         <div class="input-group mb-3" style="width:300px;float:left;">
										 <select class="form-select" name="field" style="height:35px;">
											  <option value="1" ${param.field eq'1'?"selected":"" }>이름</option>
											  <option value="2" ${param.field eq '2'?"selected":"" }>아이디</option>
											  <option value="3" ${param.field eq '3'?"selected":"" }>주소</option>
										  </select>
										  <input type="text" class="form-control" value="${param.keyword}" name="keyword" id="keyword"style="width:100px;height:35px; margin-right:10px;">
										  <input type="text" style="display:none"/><!-- 엔터 submit 방지용 -->
										  <button type="button" class="btn btn-outline-dark btn-sm" id="searchBtn" style="height: 35px;">
                                 			<i class="fa-solid fa-magnifying-glass"></i>
                               			  </button>
									</div>
								     <select class="form-select" name="pageScale" id="pageScale"style="width:85px;height:35px;float:right">
									      <option value="5" selected>5개</option>
									      <option value="10" ${param.pageScale eq "10"?"selected":"" }>10개</option>
									      <option value="30" ${param.pageScale eq "30"?"selected":"" }>30개</option>
									      <option value="50" ${param.pageScale eq "50"?"selected":"" }>50개</option>
								      </select>
							       </form> 
                        	</div> 
                        
                            
                            <div class="card-body">
                             <!-- 테이블 정의 -->
                          <table class="table table-hover" id="member_tab">
                            	<thead> 
							   		<tr>
                                    	<th>ID</th>
                                    	<th>이름</th>
                                    	<th>주소</th>
                                    	<th>가입일</th>
                                    </tr>
						  	</thead> 
						  	<tbody> 
						  		<c:if test="${empty memberList}">
						  		<tr>
						  			<td colspan="4">조회 결과가 없습니다.</td>
						  		</tr>
						  		</c:if>
						  		<c:if test="${not empty memberList }">
	    						 		<c:forEach var="member" items="${memberList}" >
                                    	<tr style="cursor:pointer"data-bs-target="#memberDetail" data-bs-toggle="modal"data-id='${member.userid}')>
	    						 			<td><c:out value="${member.userid }"/></td>
	    						 			<td><c:out value="${member.name}"/></td>
	    						 			<td><c:out value="${member.address1}"/></td>
	    						 			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${member.subscribe_date}"/></td>
	    						 		</tr>
	    						 		</c:forEach>
	    						 </c:if>
						  	</tbody> 
						  </table> 
						  <div style="float:left;color:#333;">
 								전체 : <c:out value="${totalCnt }"/>건                    
                            </div>
		
                            </div>
                                <div id="pageNavigation">
								<ul class="pagination justify-content-center"> 
								<c:if test="${not empty memberList}">
									<c:if test="${endPage gt pageCnt }">
										<c:set var="endPage" value="${pageCnt}"/>
									</c:if>
									
									<c:if test="${startPage gt pageBlock }">
									<li>
									<a style="margin-right:10px;text-decoration:none;"class="text-secondary page-item" 
							href="member.do?currentPage=${startPage-5}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale  : ""%>">
									이전
									</a>
									</li>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<c:choose>
										<c:when test="${i eq currentPage}">
											<li>
											<a style="margin-right:10px;"class="text-secondary" href="#void">
												<c:out value="${i}"/>
											</a>
											</li> 
										</c:when>
										<c:otherwise>
											<li>
											<a style="margin-right:10px;text-decoration:none;"class="text-secondary" id="pNum" 
											href="member.do?currentPage=${i}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale : ""%>">
											<c:out value="${i}"/>
											</a>
											</li> 
										</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${endPage lt pageCnt }">
									<li>
										<a style="margin-right:10px;text-decoration:none;"class="text-secondary" 
										href="member.do?currentPage=${startPage+5}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale  : ""%>">
										다음
										</a>
										</li> 
									
									</c:if>
								</c:if>
								</ul> 
							</div>
                        </div>
                </main>
                <%-- 	<jsp:include page="admin_footer.html"/> --%>
	               <!-- 회원 수정 modal  -->
	                <div class="modal fade" tabindex="-1" id="memberDetail" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false" >
					  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title">회원 수정</h5>
					        <button type="button" class="btn-close exit" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      	<div class="memberTitle">ID(Email)</div>
					      	<div class="input-group input-group-sm mb-3" style="width:450px">
							  <input type="text" class="form-control" id="id" placeholder="Username" readonly/>
							  <span class="input-group-text">@</span>
							  <input type="text" class="form-control" id="server" placeholder="Server" readonly/>
							</div>
					      	<div class="memberTitle">이름</div>
					      	<div class="input-group input-group-sm mb-3" style="width:200px">
					      	<input type="text" class="form-control" id="userName" placeholder="이름" readonly/>
							</div>
					      	<div class="memberTitle">전화번호</div>
					      	<div class="input-group input-group-sm mb-3" style="width:200px">
							  <input type="text" class="form-control" id="tel"placeholder="전화번호"/>
							</div>
					      	<div class="memberTitle">우편번호</div>
					      	<div class="input-group input-group-sm mb-3" style="width:250px">
							  <input type="text" class="form-control" id="zipcode" placeholder="우편번호" />
							   <button class="btn btn-outline-info" type="button" id="searchZipcode">우편번호검색</button>
							</div>
					      	<div class="row"><div class="memberTitle col-6">주소</div> <div class="memberTitle col-6">상세주소</div></div>
					      	<div class="row">
					      	<div class="col-6">
					      	<div class="input-group input-group-sm mb-3" style="width:250px">
							  <input type="text" class="form-control" id="address1" placeholder="주소"/>
							</div>
					      	 </div>
					      	<div class="col-6">
					      	<div class="input-group input-group-sm mb-3" style="width:350px">
							  <input type="text" class="form-control" id="address2" placeholder="상세주소"/>
							</div>
					      	</div> 
					      	 </div>
					      	<div class="memberTitle">가입일</div>
					      	<div class="input-group input-group-sm mb-3" style="width:200px">
							<input type="text" class="form-control" id="subDate" placeholder="가입일"readonly/>
							</div>
					      </div>
					      <div class="modal-footer">
							        <button type="button" class="btn btn-outline-dark exit">돌아가기</button>
							        <button type="button" class="btn btn-outline-danger" id="deleteBtn">회원 삭제</button>
							        <button type="button" class="btn btn-outline-info" id="modifyBtn">회원 수정</button>
					      </div>
					    </div>
					  </div>
					</div>
				<!-- modal -->
            </div>
        </div>
				<!-- 확인 modal -->
		 		<div class="modal fade" id="confirm" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"  aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <span id="confirmMsg"></span>
				        <input type="hidden" id="confirmHid" value=""/>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" id="modifyOkBtn" onclick="modifyMember()">Ok</button>
				      </div>
				    </div>
				  </div>
				</div> 
				<!--  -->
             <!-- 종료 확인 modal -->
				<div class="modal fade" id="confirmExit" tabindex="-1"data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        지금 종료하면 작성했던 내용은 저장되지 않습니다.<br>
				        종료하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" id="exitOk">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- modal -->
      
    </body>
</html>
