<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%-- <%@ include file="admin_id_session.jsp" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Reservations</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://localhost/exhibitionThreeAdmin/css/styles.css" rel="stylesheet" />
        <!-- jQeury CDN -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="http://localhost/exhibitionThreeAdmin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
     	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
      hr {width:200px; margin: 0px auto; margin-top:10px;}
      #member_tab{ text-align:center;}
      .memberTitle{font-weight:bold}
</style>

<script type="text/javascript">
$(function(){
	 $("#findNamesBtn").click(function() {
	    	document.dataSearchFrm.submit();
		});//findNamesBtn
		
 	$("#rezDetail").on("show.bs.modal", function(e) {		
	   	var num= $(e.relatedTarget).data('num');
	 	$.ajax({
			url:"http://localhost/exhibitionThreeAdmin/admin/rezDetail.do",
			data: {"rezNum":num},
			async:false,
			type: "get",
			dataType:"json",
			error:function(xhr){
				console.log("rezDetail"+xhr.status+", "+xhr.statusText);
			},
			success:function(jsonObj){
				$("#exName").val(jsonObj.exName);				
				$("#exNum").val(jsonObj.exNum);				
				$("#resNum").val(num);				
				$("#userName").val(jsonObj.name);				
				$("#rezCount").val(jsonObj.rezCount);				
				$("#rezDate").val(jsonObj.rezDate);				
				$("#userId").val(jsonObj.userId);				
				$("#visitDate").val(jsonObj.visitDate);				
				$("#price").val(jsonObj.price);	
			}
		});//ajax		
 	});//rezDetail
 	
 	$("#confirmModify").click(function() {
		$("#confirmModal").modal('show');
	});
});//ready

function confirmModify() {
	var num= $("#resNum").val();
	var rezCount=$("#rezCount").val();
	var visitDate=$("#visitDate").val();
		
	var now= new Date();
	
	if(rezCount<1){
		alert("인원은 1명 이상이어야합니다.")
		return;
	}
	
	if(now > new Date(visitDate)){
		alert("예약할 수 없는 날짜입니다.");
		return;
			
	}
	if(rezCount=="" || visitDate==""){
		alert("예약인원과 방문날짜는 비어있을 수 없습니다.");
		return;
	}
	 $.ajax({
		url:"http://localhost/exhibitionThreeAdmin/admin/rezModify.do",
		data: {"rez_count":rezCount, "visit_date":visitDate, "rez_num":num},
		async:false,
		type: "get",
		dataType:"json",
		error:function(xhr){
			console.log("confirmModify : "+xhr.status+", "+xhr.statusText);
		},
		success:function(jObj){
			if(jObj.msg=="성공"){
				alert("수정되었습니다.");
				location.href="http://localhost/exhibitionThreeAdmin/admin/reservation.do";
			}
		}
	}); //ajax
} //confirmModify
</script>
    </head>
   <body class="sb-nav-fixed">
   
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp">Exhibition Admin</a>
<!--             Sidebar Toggle
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button> -->
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
                       <li><a class="dropdown-item" href="http://localhost/exhibitionThreeAdmin/admin/settings.do">Settings</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="http://localhost/exhibitionThreeAdmin/admin/logout.do">Logout</a></li>
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
                        <h1 class="mt-4">예약 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="http://localhost/exhibitionThreeAdmin/admin/index.do" style="text-decoration:none; color:#333;">Dashboard</a></li>
                            <li class="breadcrumb-item active">예약 관리</li>
                        </ol>
                        <!-- 검색창 -->
						<div id="searchDiv">
                            <form class="d-flex" action="http://localhost/exhibitionThreeAdmin/admin/reservation.do" name="dataSearchFrm">
                            	<div class="input-group mb-3" style="width:300px; height:40px; margin-top:10px;" >
								  <span class="input-group-text" id="addon-wrapping">방문날짜</span>
					      		  <input type="date" name="vDate" class="form-control" placeholder="방문 일자" style="width:200px;" id="vDate" value="${param.vDate}">
								</div>
	                         <div class="input-group mb-3" style="width:500px;margin-top:10px; margin-left: 100px;">
						  		<span class="input-group-text" id="addon-wrapping">항목검색</span>
									 <select class="form-select" aria-label=".form-select-sm example" name="nameSelection" id="nameSel">
									  <option value="findUserName" ${(param.nameSelection =="findUserName")?"selected":"" } >사용자 이름</option>
									  <option value="findExName" ${(param.nameSelection =="findExName")?"selected":""} >전시 이름</option>
									</select>
								  <input type="text" class="form-control" style="width:100px" name="findCatName" id="findCatName" value="${param.findCatName}">
								  <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal" id="findNamesBtn">검색</button>
								</div> 
						      </form>
                        	</div>
                        	
                        	<div class="form-check form-check-inline">
							</div>
						<div class="card-body">
                            <!-- 테이블 정의 -->
                              <table class="table table-hover" id="member_tab">
                            	<thead> 
							   <tr>
                                    	<th>예약번호</th>
                                    	<th>전시명</th>
                                    	<th>예약자</th>
                                    	<th>방문일시</th>
                                    	<th>처리현황</th>
                                    	<th>관리</th>
                                   </tr>
						  	</thead> 
						  	<tbody>
						  	<tbody id="reservationBody"> 
						  	<c:choose>
						  	<c:when test="${empty rezList}">
						  		<tr>
								<td colspan="6">조회 데이터가 없습니다.</td>
								</tr>	
							</c:when>
							<c:otherwise>
							  	<c:forEach var="rezList" items="${rezList}">
								<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#rezDetail" data-num="${rezList.rez_num}"  class="rezList">
									<td><c:out value="${rezList.rez_num}"/></td>
									<td><c:out value="${rezList.ex_name}"/></td>
									<td><c:out value="${rezList.name}"/></td>
									<td><fmt:formatDate value="${rezList.visit_date}" pattern="yyyy-MM-dd"/></td>
									<td id="rezStatus"><c:out value="${rezList.rez_status}"/></td>
									<td>
									 	<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#confirmCancel" >예약 취소</button>
				        				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmOk">예약 확인</button>
				        			</td>
							  	</c:forEach>
							</c:otherwise>
						  	</c:choose>
						  	</tbody> 
						  </table>
						  
                            </div>
                               <div id="pageNavigation">
								<ul class="pagination justify-content-center"> 
									<li>
									<%--  
									<a style="margin-right:10px;text-decoration:none;"class="text-secondary page-item" href="booking.jsp?pageNum=<%=startPage-5%>&vDate=${param.vDate}&vDate=${param.vDate}&nameSelection=${param.nameSelection}&findCatName=${param.findCatName}">
									--%>
									<a>
									이전
									</a>
									</li>
									
											<li>
											<a style="margin-right:10px;"class="text-secondary" href="#">
											<!-- <c:out value="${i}"/> -->
											</a>
											</li>
											<li>
											<a>
											</a>
											</li> 
									
									<li>
										<a style="margin-right:10px;text-decoration:none;"class="text-secondary" href="booking.jsp?pageNum=${startPage+5}pageNum=${i}&vDate=${param.vDate}&nameSelection=${param.nameSelection}&findCatName=${param.findCatName}">
										다음
										</a>
										</li> 
								
								</ul> 
							</div>
                        </div>
                </main>
                <jsp:include page="../commons/admin_footer.html"/> 
               <!-- 예약 관리 상세 모달  -->
              
				<div class="modal fade" id="rezDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">예약 관리 상세</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					 	<table class="modalTab" style="width:98%;" cellpadding="5">
					 		<tr>
					 			<th class="modalTh">전시명</th>
					 			<th class="modalTh">전시 번호</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input type="text" id="exName" class="form-control" readonly/></td>
					 			<td class="modalTd"><input type="text" id="exNum" class="form-control" style="width:100px" readonly/></td>
					 		</tr>
					 		<tr>
					 			<th class="modalTh">예약 번호</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input type="text" id="resNum" class="form-control" style="width:100px" readonly/></td>
					 		</tr>
					 		<tr>
					 			<th class="modalTh">예약자명</th>
					 		</tr>
					 		
					 		<tr>
					 			<td class="modalTd"><input type="text" id="userName" class="form-control" style="width:100px" readonly/></td>
					 		</tr>
					 		
					 		<tr>
					 			<th class="modalTh">예약자 ID</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input type="text" id="userId" class="form-control" readonly/></td>
					 		</tr>
					 		
					 		<tr>
					 			<th class="modalTh">예약인원</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input id="rezCount" type="number" class="inputBox"/>
					 			<span id="countWarning" style="font-size:10px; color:#ff0000;"></span></td>
					 		</tr>
					 		<tr>
					 			<th class="modalTh">예약일자</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input type="date" id="rezDate" class="form-control" readonly/></td>
					 		</tr>
					 		<tr>
					 			<th class="modalTh">방문기간</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd"><input id="visitDate" type="date" class="inputBox"/>
					 			<span id="visitWarning" style="font-size:10px; color:#ff0000;"></span></td>
					 		</tr>
					 		<tr>
					 			<th class="modalTh">예약가격</th>
					 		</tr>
					 		<tr>
					 			<td class="modalTd" ><input type="text" id="price" class="form-control" readonly/></td>
					 		</tr>
					 	</table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">돌아가기</button><!-- id="confirmModify"  -->
				        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" id="confirmModify">예약 수정</button>
				      </div>
				    </div>
				  </div>
				</div>
	
				<!-- 예약취소 확인 modal -->
				<div class="modal fade" id="confirmCancel" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        예약을 취소하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-outline-info" onclick="cancelRez(${res.rezNum})">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 예약 확인 modal -->
				<div class="modal fade" id="confirmOk" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        예약을 확인하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-outline-info" onclick="rezConfirm(${res.rezNum})">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 수정 확인 modal -->
				<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        수정하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-outline-info" onclick="confirmModify(${res.rezNum})">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!--  -->
            </div>
        </div>
             
      
    </body>
</html>
