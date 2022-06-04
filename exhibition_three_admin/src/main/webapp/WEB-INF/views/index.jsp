
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
       <title>Exhibition Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://localhost/exhibitionThreeAdmin/css/styles.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        
	<style>
		hr {width:200px; margin: 0px auto; margin-top:10px;}
       	#member_tab{ text-align:center;}
	</style>
    </head>
    
    <body class="sb-nav-fixed">
   
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="http://localhost/exhibitionThreeAdmin/admin/index.do">Exhibition Admin</a>
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
                	<jsp:include page="commons/navigation.jsp"/>
                </nav>
            </div>
           <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>                        
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        회원 관리
                                    </div>
                                  
                                    <div class="card-body" onclick="location.href='member.do'">
                                     <table class="table table-hover">
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
                                    		<th>총 회원수</th>
                                    		<th>오늘 가입 회원수</th>
                                    	</tr>
                                    	<tr>
                                    		<td>
                                    		<c:out value="${cntAllMembers}"/>
                                    		</td>
                                    		
                                    		<td>
                                    		<c:out value="${cntTodayMember}"/>
                                    		</td>
                                    	</tr>
                                    </table>
                                    <canvas id="myAreaChart" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6" onclick="location.href='board.do'">
                                <div class="card mb-4">
                                <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                       게시판 관리
                                    </div>
                                    <div class="card-body">
                                      <table class="table table-hover" >
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
                                    		<th>새 글수</th>
                                    	</tr>
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
<%--                                     		<td><%=cntNewContext %></td>
 --%>                                    	</tr>
                                    </table>
                                    <canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        <div class="row">
                            <div class="col-xl-6" onclick="location.href='exhibitions.do'">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                       전시 관리
                                    </div>
                                    
                                    <%
     /*                                	AdminExhibitionDAO aed = new AdminExhibitionDAO();
                                    	int allEx=aed.selectAllEx();
                                    	int ended = aed.endedEx();
                                    	int endTomorrow= aed.endsTomorrow(); */
                                    %>
                                    <div class="card-body">
                                      <table class="table table-hover" >
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
                                    		<th>공개된 전시 수</th>
                                    		<th>마감된 전시 일정</th>
                                    		<th>내일 마감 전시</th>
                                    	</tr>
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
                                    	<%--
                                    	 	<td></td>
                                    		<td><%=ended %>건</td>
                                    		<td><%=endTomorrow %>건</td> --%>
                                    	</tr>
                                    </table>
                                    <canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6" onclick="location.href='reservation.do'">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        예약관리
                                    </div>
                                    <%
                                    	/* ReservationManagerDAO rmd=new ReservationManagerDAO();
                                    	int cntReserve= rmd.countReservation();
                                    	int cntTodayRez = rmd.countTodaysReservation(); */
                                    %>
                                    <div class="card-body">
                                     <table class="table table-hover" >
                                    	<tr style="cursor:pointer" data-bs-toggle="modal" data-bs-target="#myModal">
                                    		<th>확인된 예약 건수</th>
                                    		<th>예약 수</h>
                                    		<th>오늘의 예약건수</th>
                                    	</tr>
                                    	<tr>
                                    	
                                    	<td width="30%"><c:out value="${cntShownRez}건"/></td>
                                    	<td width="30%"><c:out value="${cntAllRez}건"/></td>
                                    	<td width="30%"><c:out value="${cntTodayRez}건"/></td>
                                    	
                                    </table>
                                    <canvas id="myAreaChart" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                            
                            </div>
                            </div>
                    </div>
                </main>
               	<jsp:include page="commons/admin_footer.html"/> 
            </div>
        </div>
             
      
    </body>
</html>