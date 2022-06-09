<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <title>Exhibition Schedule</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://<%=application.getInitParameter("domain") %>/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- jQuery CDN -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
 		<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/coin-slider/1.0.0/coin-slider.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/coin-slider/1.0.0/coin-slider-styles.css" type="text/css" />
<style>
	hr {width:200px; margin: 0px auto; margin-top:10px;}
	.exTitle{font-weight:bold}
	#memberTab{text-align:center;}
</style>
	<script type="text/javascript">
	$(function(){
		 $(".plusIcon").on("click",function(){ 
			  var obj = $(this);
			  if( obj.hasClass("glyphicon-plus") ){
			    obj.hide();
			    obj.next().show();            
			    obj.parent().parent().next().show();
			  }else{
			     obj.hide();
			     obj.prev().show();
			     obj.parent().parent().next().hide();
			  }
			});
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
	    $("#modifyModal").on('show.bs.modal',function(e){
	    	 var ex_num = $(e.relatedTarget).data('num');
	    	 $.ajax({
	    		 data:{"ex_num":ex_num},
	    		 url:"exDetail.do",
	    		 dataType:"json",
	    		 type:"post",
	    		 async:false,
	    		 success:function(jsonObj){
	    			 $("#exNum").val(ex_num);
					 	$("#startDate").val(jsonObj.exhibitDate);
						$("#endDate").val(jsonObj.deadline); 
						$("#exIntro").val(jsonObj.exIntro);
						$("#exName").val(jsonObj.exName);
						$("#exInfo").val(jsonObj.exInfo);
						$("#totalCount").val(jsonObj.totalCount);
						$("#watchCount").val(jsonObj.watchCount); 
						$("#adult").val(jsonObj.adult); 
						$("#teen").val(jsonObj.teen); 
						$("#child").val(jsonObj.child); 
						$("#exHall").val(jsonObj.exHallNum);
						$("#posterImg").attr("src",jsonObj.exPosterUrl).width(400);//포스터 보이기
						$("#addImage").attr("src",jsonObj.addImgUrl).width(400);//추가이미지 보이기 
						$("#exStatus").val(jsonObj.exStatus);
						$("#hidPoster").val(jsonObj.exPoster);
						$("#hidAddImg").val(jsonObj.addImg);
	    		 },
	    		 error:function(request, status, error){
	    				alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error:"+error);
	    		 }//error
	    	 });//end ajax
	    });//end modifyModal
	    $("#updateExhibition").click(function(){
	    	var title =["전시명","전시시작일","전시마감일","전시간단소개","전시소개","가격","가격","가격"];
			var value=[$("#exName"),$("#startDate"),$("#endDate"),$("#exIntro"),$("#exInfo"),$("#adult"),$("#teen"),$("#child")];
			for(var i = 0; i < title.length; i++){
				if(value[i].val().trim()==""){
					alert(title+"을 입력해 주세요");
					value[i].focus();
					return;
				}//end if
			}//end for
		    
			var nowDate = new Date();
			var endDate = new Date($("#endDate").val());
			if(endDate.getTime() < nowDate.getTime()){
				alert("마감일을 현재날짜 이후로 설정해주세요");
				$("#endDate").focus();
				return;
			}//end if
		
			if(startDate > endDate){
				alert("마감일은 시작일 이후로 설정해주세요");
				$("#endDate").focus();
				return;
			}//end if 
	    	$("#confirmModify").modal('show');
	    })
	    $(".exit").click(function(){
			$("#confirmExit").modal('show');
		});
		$("#exitOk").click(function(){
			$("#confirmExit").modal('hide');
			$("#modifyModal").modal('hide');
			$("#addModal").modal('hide');
		})
		$("#confirmRelease").on('show.bs.modal',function(e){
			$("#exhibitionNameId").text($(e.relatedTarget).data('name'));
			$("#exhibitionNumHid").val($(e.relatedTarget).data('num'));
			$("#exStatusHid").val($(e.relatedTarget).data('status'));
		});//on
		$("#confirmDelete").on('show.bs.modal',function(e){ 
			$("#deleteExhibitionName").text($(e.relatedTarget).data('name'));
			$("#hidDeleteExNum").val($(e.relatedTarget).data('num'));
			$("#hidDeleteAdd").val($(e.relatedTarget).data('add'));
			$("#hidDeletePoster").val($(e.relatedTarget).data('poster'));
		});//on
		$("#addExBtn").click(function(){
			var title =["전시명","전시시작일","전시마감일","전시간단소개","전시소개","허용인원","가격","가격","가격"];
			var value=[$("#addExName"),$("#addStartDate"),$("#addEndDate"),$("#addIntro"),$("#addInfo"),$("#addTotalNum"),$("#addAdult"),$("#addTeen"),$("#addChild")];
			for(var i = 0; i < title.length; i++){
				if(value[i].val().trim()==""){
					alert(title[i]+"을 입력해 주세요");
					value[i].focus();
					return;
				}//end if
			}//end for
			if($("#addExHall").val()==""){
				alert("전시장을 입력해주세요");
				$("#addExHall").focus();
				return;
			}
			var poster=$("#addExPoster").val();
			if(poster.trim()==""){
				alert("포스터를 입력해주세요");
				$("#addExPoster").focus();
				return;
			}
			var addImg = $("#addAddImg").val();
			if(addImg.trim()==""){
				alert("추가 이미지를 입력해주세요");
				$("#addAddImg").focus();
				return;
			}
			var nowDate = new Date();
			var startDate = new Date($("#addStartDate").val());
			var endDate = new Date($("#addEndDate").val());
			
			if(startDate.getTime() < nowDate.getTime()){
				alert("시작일을 현재날짜 이후로 설정해주세요");
				$("#addStartDate").focus();
				return;
			}//end if
			if(startDate > endDate){
				alert("마감일은 시작일 이후로 설정해주세요");
				$("#addEndDate").focus();
				return;
			}//end if 
		
			var pattern_num= /^[0-9]+$/;
			
			if(!pattern_num.test(value[5].val())){
				alert("허용인원은 숫자로 입력해주세요");
				value[5].focus();
				return;
			}//end if
			if(!pattern_num.test(value[6].val())||!pattern_num.test(value[7].val())||!pattern_num.test(value[8].val())){
				alert("가격은 숫자로 입력해주세요");
				value[7].focus();
				return;
			}//end if
			$("#confirmAdd").modal('show');
		});//click
	});//ready
	function chkNull(){
		if($("#keyword").val()==""){
			alert("검색어를 입력해주세요.");
			return;
		}//end if
		$("#searchFrm").submit(); 
	}//chkNull
	
	function releaseExhibition(){
		var exNum = $("#exhibitionNumHid").val();
		var status =$("#exStatusHid").val();
		if(status == 'p'){
			alert("이미 사용자 페이지에 노출된 전시입니다.");
			$("#confirmRelease").modal('hide');
			return;
		}//end if
		$.ajax({
			url:"exUpdate.do",
			data:{
				"ex_num":exNum,
				"ex_status":'p'
			},
			type:"post",
			dataType:"json",
			async:false,
			error:function(xhr){
				alert(xhr.status+" / "+xhr.statusText);
			},//error
			success:function(jsonObj){
				if(jsonObj.cnt > 0){
					alert("사용자 페이지에 업데이트 되었습니다.");
					location.reload();
				}else{
					alert("사용자 페이지 업데이트 실패하였습니다.");
				}//end else				
			}//success
		});//end ajax 
	}//releaseExhibition
	
	function deleteExhibition(){
		var num= $("#exNum").val();
	 	var poster=$("#hidDeletePoster").val();
		var addImg=$("#hidDeleteAdd").val();
		 $.ajax({
				url:"exUpdate.do",
				data: {
					"ex_num":num,
					"ex_status":'n',
					"add_img":addImg,
					"exhibition_poster":poster
				},
				type: "post",
				dataType:"json",
				async:false,
				error:function(xhr){
					alert("cancelAjax : "+xhr.status+", "+xhr.statusText);
				//	location.href="401.html";
				},
				success:function(jsonObj){
					if(jsonObj.cnt > 0){
						alert("전시가 삭제되었습니다.");
						location.reload();
					}else{
						alert("전시 삭제 실패하였습니다.");
					}//end else
				}//success  
			}); //ajax
	}//deleteExhibition
	
	function setThumbnail(event,id,inputId){//미리보기
	    let file = event.target.files[0];
	    if (!file.type.match("image.png")&&!file.type.match("image.jpg")&&!file.type.match("image.jpeg")) {
	        alert("jpg/jpeg/png 이미지 파일만 업로드 가능합니다.");
	        $(inputId).val("");
	        return;
	    }//end if
		if(file){
			var reader = new FileReader();
			reader.onload = function(event){
				$(id).attr("src",event.target.result).width(400);//포스터 보이기
			}//onload
			reader.readAsDataURL(file);
		}//end if
	}//setThumbnail
	
	function addExhibition(){
		var formData = new FormData();
		formData.append("mulPoster",$("#addExPoster").get(0).files[0]);
		formData.append("mulAdd",$("#addAddImg").get(0).files[0]);
		formData.append("ex_name",$("#addExName").val());
		formData.append("ex_hall_num",$("#addExHall").val());
		formData.append("ex_info",$("#addInfo").val());
		formData.append("ex_intro",$("#addIntro").val());
		formData.append("exhibit_date",$("#addStartDate").val());
		formData.append("deadline",$("#addEndDate").val());
		formData.append("total_count",$("#addTotalNum").val());
		formData.append("adult",$("#addAdult").val());
		formData.append("teen",$("#addTeen").val());
		formData.append("child",$("#addChild").val());
		/* formData.append("watch_count",$("#addWatchNum").val()); */
		
		$.ajax({
			url:"exAdd.do",
			type: "post",
			processData:false,
			contentType: false,
			data: formData,
			dataType:"text",
			error:function(xhr){
				alert("addAjax : "+xhr.status+", "+xhr.statusText);
			},
			success:function(data){
				if(data > 0){
					alert("전시가 등록되었습니다.");
					location.reload();
				}else{
					alert("전시 등록 실패하였습니다.");
				}//end else
			}//success  
			
		});//ajax
	}//addEx
	
	function updateExhibition(){
		var formData = new FormData();
		if($("#modifyExPoster").get(0).files[0]!=undefined){
			formData.append("mulPoster",$("#modifyExPoster").get(0).files[0]);
		}//end if
		if($("#modifyAddImg").get(0).files[0] != undefined){
			formData.append("mulAdd",$("#modifyAddImg").get(0).files[0]);
		}//end if
		formData.append("ex_name",$("#exName").val());
		formData.append("ex_num",$("#exNum").val());
		formData.append("ex_hall_num",$("#exHall").val());
		formData.append("ex_info",$("#exInfo").val());
		formData.append("ex_intro",$("#exIntro").val());
		formData.append("exhibit_date",$("#startDate").val());
		formData.append("deadline",$("#endDate").val());
		formData.append("adult",$("#adult").val());
		formData.append("teen",$("#teen").val());
		formData.append("child",$("#child").val());
		formData.append("add_img",$("#hidAddImg").val());
		formData.append("exhibition_poster",$("#hidPoster").val());
		
		$.ajax({
			url:"exUpdate.do",
			type: "post",
			processData:false,
			contentType: false,
			data: formData,
			dataType:"json",
			error:function(xhr){
				alert("addAjax : "+xhr.status+", "+xhr.statusText);
			},
			success:function(jsonObj){
				if(jsonObj.cnt > 0){
					alert("전시가 수정되었습니다.");
					location.reload();
				}else{
					alert("전시 수정 실패하였습니다.");
				}//end else
			}//success  
		});//ajax
	}//updateExhibition
	</script>  
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="http://<%=application.getInitParameter("domain") %>/admin/index.do"">Exhibition Admin</a>
            <!-- <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
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
                        <li><a class="dropdown-item" href="http://<%=application.getInitParameter("domain") %>/admin/settings.do">Settings</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="http://<%=application.getInitParameter("domain") %>/admin/logout.do">Logout</a></li>
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
                        <h1 class="mt-4">전시 일정관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="http://<%=application.getInitParameter("domain") %>/admin/index.do" class="breadcrumb-item active" style="text-decoration:none">Dashboard</a></li>
                            <li class="breadcrumb-item active">전시 일정관리</li>
                        </ol>
                        <!-- 검색창 -->
                        <div id="serachDiv">
                             <form action="http://<%=application.getInitParameter("domain") %>/admin/exhibitions.do" id="searchFrm"name="searchFrm">
			                         <div class="input-group mb-3" style="width:350px;float:left;">
											 <select class="form-select" style="height:35px;" name="field" >
											  <option ${(param.dataSearchItem=="1")?"selected":""} value="1">전시명</option>
											  <option ${(param.dataSearchItem=="2")?"selected":""} value="2">전시번호</option>
											  <option ${(param.dataSearchItem=="3")?"selected":""} value="3">입력일</option>
											</select>
										  <input type="text" class="form-control" value="${param.keyword}" name="keyword" id="keyword" style="width:100px;height:35px; margin-right:10px;" >
										  <input type="text" style="display:none"/>
										      <button type="button" class="btn btn-outline-dark btn-sm" style="height: 35px;" id="searchBtn">
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
						<div class="card-body">
                            <!-- 테이블 정의 -->
                            <!--  <span class="glyphicon glyphicon-plus plusIcon"><i class='fas fa-angle-right' style='font-size:20px'></i></span>
      						 <span class="glyphicon glyphicon-minus plusIcon" style="display:none"><i class='fas fa-angle-down' style='font-size:20px;'></i></span>
                               -->
                               <table class="table table-hover" id="memberTab" >
                            	<thead> 
								   <tr>
	                                    <th>전시번호</th>
	                                    <th>전시명</th>
	                                    <th>입력일</th>
	                                    <th>전시상태</th>
	                                    <th>관리</th>
	                               </tr>
						  		</thead> 
						  		<tbody> 
						  			<c:if test="${not empty exhibitionList }">
	    						 		<c:forEach var="exhibition" items="${exhibitionList }">
                                    	<tr style="cursor:pointer;" class="detailTab" data-bs-target="#modifyModal" data-bs-toggle="modal" data-num="${ exhibition.ex_num}">
 											<td>
 											   <c:out value="${exhibition.ex_num }"/>
 											</td>
 											<td>
	 											<c:out value="${exhibition.ex_name }"/>
	 											<c:set var="today" value="<%=new java.util.Date()%>"/>
	 											<fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy-MM-dd"/>
	 											<c:if test="${exhibition.input_date eq now}">
	 											<span id="newBadge" class="badge rounded-pill bg-danger" style="width:20px;font-size:10px;">N</span>
 												</c:if>
 											</td>
 											<td>
 												<fmt:formatDate pattern="yyyy-MM-dd" value="${exhibition.input_date }"/>
 											</td>
 											<td>
 											<c:choose>
 												<c:when test="${exhibition.ex_status eq 'y' }">
 												<span class="badge rounded-pill bg-secondary">pending</span>
 												</c:when>
 												<c:otherwise>
 												<span class="badge rounded-pill bg-success" >expose</span>
 												</c:otherwise>
 											</c:choose>
 											</td>
 											<td>
	 											<button type="button" class="btn btn-secondary btn-sm" data-bs-target="#confirmDelete" data-bs-toggle="modal" 
	 											data-num='${exhibition.ex_num}'data-name="${exhibition.ex_name }"data-add="${exhibition.add_img}"data-poster="${exhibition.exhibition_poster}"
	 											>삭제</button>
	 											<button type="button" class="btn btn-primary btn-sm" data-bs-target="#confirmRelease" data-bs-toggle="modal" 
	 											data-num='${exhibition.ex_num}'data-name="${exhibition.ex_name }"data-status="${exhibition.ex_status }">노출</button>
 											</td>
                                    	</tr>
	    						 		</c:forEach>
						  			</c:if>
						  			<c:if test="${empty exhibitionList }">
	                                 <tr>
	                                 	<td colspan="6" style="text-align:center">조회된 데이터가 없습니다.</td>
	                                 </tr>
						  			</c:if>
						  	</tbody> 
						  </table> 
						  <div>
						  <button type="button" class="btn btn-dark" style="float:right;" data-bs-target="#addModal" data-bs-toggle="modal">전시 추가</button>
						  </div>
						  <!-- 페이지 -->
						    <div style="float:left;color:#333;">
 								전체 : <c:out value="${totalCnt }"/>건                    
                            </div>
		
                            </div>
                                <div id="pageNavigation">
								<ul class="pagination justify-content-center"> 
								<c:if test="${not empty exhibitionList}">
									<c:if test="${endPage gt pageCnt }">
										<c:set var="endPage" value="${pageCnt}"/>
									</c:if>
									
									<c:if test="${startPage gt pageBlock }">
									<li>
									<a style="margin-right:10px;text-decoration:none;"class="text-secondary page-item" 
							href="exhibitions.do?currentPage=${startPage-5}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale  : ""%>">
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
											href="exhibitions.do?currentPage=${i}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale : ""%>">
											<c:out value="${i}"/>
											</a>
											</li> 
										</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${endPage lt pageCnt }">
									<li>
										<a style="margin-right:10px;text-decoration:none;"class="text-secondary" 
										href="exhibitions.do?currentPage=${startPage+5}<%=!"".equals(pageScale)&&pageScale != null?"&field="+field+"&keyword="+keyword+"&pageScale="+pageScale  : ""%>">
										다음
										</a>
										</li> 
									
									</c:if>
								</c:if>
								</ul> 
							</div>
                        </div>
						  <!-- 페이지 끝 -->
                            </div>
                		</main>	
             		 <jsp:include page="../commons/admin_footer.html"/> 
                 <!-- 전시 추가 modal  -->
	                <div class="modal fade" tabindex="-1" id="addModal" data-bs-backdrop="static" data-bs-keyboard="false">
					  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title">전시 추가</h5>
					        <button type="button" class="btn-close exit"  aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
						      	<div class="mb-3">
								  <label for="exampleFormControlInput1" class="exTitle">전시명</label>
								  <input type="email" class="form-control" id="addExName" name="addExName" placeholder="전시명"  style="width:200px">
								</div>
						      	<div class="mb-3">
								  <label for="exampleFormControlInput1" class="exTitle">전시장</label>
							  	<select class="form-select" id="addExHall" name="addExHall" style="width:400px">
									  <option selected>전시장을 선택해주세요</option>
										 <c:forEach var="exHall" items="${exHallList}">
											<option value="${exHall.ex_hall_num}"><c:out value="${exHall.ex_hall_name }"/> </option>
										</c:forEach> 
									</select> 
								</div>
					      	<div class="mb-3">
					      	<div class="row">
					      	<div class="col-6">
					      	<label class="exTitle">시작일</label>
					      	<input type="date" id="addStartDate" name="addStartDate" class="form-control" placeholder="시작 일자" style="width:200px">
					      	</div>
					      	<div class="col-6">
					      	<label class="exTitle">마감일</label>
					      	<input type="date" id="addEndDate" name="addEndDate"class="form-control" placeholder="마감 일자" style="width:200px">
					      	</div>
					      	</div>
					      	</div>
					      	<div class="mb-3" >
						      	<label class="exTitle">전시 포스터</label>
						      	<div class="input-group mb-3" style="width:500px">
								  <input type="file" class="form-control" id="addExPoster" name="addExPoster" accept="image/jpg,image/jpeg,image/png" onchange="setThumbnail(event,'#posterThumbnail','#addExPoster')">
								</div>
						    		<img id="posterThumbnail" src="" style="margin-top:10px;margin-bottom:10px;"/><br>
								<label class="exTitle">추가 이미지</label>
						      	<div class="input-group mb-3" style="width:500px">
								  <input type="file" class="form-control" id="addAddImg" name="addAddImg" accept="image/jpg,image/jpeg,image/png"  onchange="setThumbnail(event,'#addImgThumbnail','#addAddImg')">
								</div>
						    		<img id="addImgThumbnail" src=""/>
					      	</div>
						    <div class="mb-3">
								<label for="exampleFormControlInput1" class="exTitle">전시 간단 소개</label>
								  <textarea class="form-control" id="addIntro"name="addIntro"  rows="3" style="width:500px"></textarea>
							</div>
						   
						      	<div class="mb-3">
								  <label for="exampleFormControlTextarea1" class="exTitle">전시 내용</label>
								  <textarea class="form-control" id="addInfo" name="addInfo" rows="10"></textarea>
								</div>
					      	<div class="row">
						      	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">허용인원</label>
								<input type="text" class="form-control" id="addTotalNum" name="addTotalNum" placeholder="100"  style="width:100px">
								</div>
						      <!-- 	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">관람인원</label>
								<input type="text" class="form-control" id="addWatchNum" name="addWatchNum" placeholder="0"  style="width:100px">
								</div> -->
					      	</div>
							
							<div class="row">
							<label class="exTitle">전시 가격</label>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">성인</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" aria-label="성인" id="addAdult" name="addAdult">
								  <span class="input-group-text" >원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">청소년</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" aria-label="청소년" id="addTeen"name="addTeen">
								  <span class="input-group-text" >원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">유아/65세 이상</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" aria-label="유아/65세 이상"id="addChild"name="addChild" >
								  <span class="input-group-text" >원</span>
								</div>
							</div>
							</div>
					      </div>
					      <!-- modal body end -->
					      <div class="modal-footer">
					        <div class="container-fluid">
					      <div class="row">
					      	<div class="col-6 text-center">
					        <a type="button" class="btn btn-outline-dark exit">돌아가기</a>
					      	</div>
					      	<div class="col-6 text-center">
					        <button type="button" class="btn btn-outline-info" id="addExBtn">전시 추가</button>
					      	</div>
					      </div>
					      </div>
					      </div>
					    </div>
					  </div>
					</div>
				<!-- modal -->
               <!-- 전시 수정&상세 modal  -->
	               <div class="modal fade" tabindex="-1" id="modifyModal" data-bs-backdrop="static" data-bs-keyboard="false"aria-hidden="true" >
					  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title">전시 조회</h5>
					        <button type="button" class="btn-close exit" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      <div class="row">
						      <div class="col-6">
							      <label class="exTitle">전시 번호 </label>
								  <input type="text" id="exNum" name="exNum" class="form-control" readonly="readonly" style="width:70px;height:30px;margin-bottom:20px;text-align:center;"/>	
						      </div>
					      </div>
						      	<div class="mb-3">
								  <label for="전시명" class="exTitle">전시명</label>
								  <input type="text" class="form-control" id="exName" name="exName" style="width:200px"/>
								</div>
						      	<div class="mb-3">
								  <label for="exampleFormControlInput1" class="exTitle">전시장</label>
							      	 	<select class="form-select" id="exHall" name="exHall" style="width:400px">
								 		<c:forEach var="exHall" items="${exHallList}">
											<option value="${exHall.ex_hall_num}"><c:out value="${exHall.ex_hall_name }"/></option>
										</c:forEach>
									</select>
								</div>
					      	<div class="mb-3">
					      	<div class="row">
					      	<div class="col-6">
					      	<label class="exTitle">시작일</label>
					      	<input type="date" id="startDate" name="startDate" class="form-control" placeholder="시작 일자"  style="width:200px">
					      	</div>
					      	<div class="col-6">
					      	<label class="exTitle">마감일</label>
					      	<input type="date" id="endDate"name="endDate" class="form-control" placeholder="마감 일자" style="width:200px">
					      	</div>
					      	</div>
					      	</div>
					      	<div class="mb-3" >
						      	<label class="exTitle">전시 포스터</label>
						      	<div class="input-group mb-3" style="width:500px">
						      	 <input type="file" class="form-control" id="modifyExPoster" name="modifyExPoster"  accept="image/jpg,image/jpeg,image/png" onchange="setThumbnail(event,'#posterImg','#modifyExPoster');">
						      	 <input type="hidden" id="hidPoster"/>
								</div>
								  <img id="posterImg"/>
					      	</div>
						    <div class="mb-3">
								<label for="exampleFormControlInput1" class="exTitle">전시 간단 소개</label>
								  <textarea class="form-control" id="exIntro" name="exIntro" rows="3" style="width:500px"></textarea>
							</div>
						    <div class="mb-3">
								<label for="exampleFormControlInput1" class="exTitle">추가 이미지</label>
						      	<div class="input-group mb-3" style="width:500px">
						      	<input type="file" class="form-control" id="modifyAddImg" accept="image/jpg,image/jpeg,image/png" name="modifyAddImg"onchange="setThumbnail(event,'#addImage','#modifyAddImg')">
						      	 <input type="hidden" id="hidAddImg"/>
								</div>
								  <img id="addImage"/>
							</div>
						      	<div class="mb-3">
								  <label for="exampleFormControlTextarea1" class="exTitle">전시 내용</label>
								  <textarea class="form-control" id="exInfo"name="exInfo" rows="10"></textarea>
								</div>
					      	<!-- <div class="row">
						      	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">허용인원</label>
								<input type="text" class="form-control" id="totalCount"name="totalCount" placeholder="100"  style="width:100px">
								</div>
						      	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">관람인원</label>
								<input type="text" class="form-control" id="watchCount"name="watchCount" placeholder="0"  style="width:100px">
								</div>
					      	</div> -->
							
							<div class="row">
							<label class="exTitle">전시 가격</label>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">성인</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" id="adult" name="adult">
								  <span class="input-group-text">원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">청소년</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" id="teen" name="teen">
								  <span class="input-group-text">원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">유아/65세 이상</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control"id="child" name="child">
								  <span class="input-group-text">원</span>
								</div>
							</div>
							</div>
					      </div>
					      <div class="modal-footer">
					      			<button type="button" class="btn btn-outline-dark exit">돌아가기</button>
							        <button type="button" class="btn btn-outline-info" id="updateExhibition">전시 수정</button>
					      </div>
					    </div>
					  </div>
					</div> 
           <%--  <!-- 전시 상세 modal  -->
	                <div class="modal fade" tabindex="-1" id="modifyModal" data-bs-backdrop="static" data-bs-keyboard="false"aria-hidden="true" >
					  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title">전시 조회</h5>
					        <button type="button" class="btn-close exit" aria-label="Close"></button>
					      </div>
					      <div class="modal-body" style="text-align:center;">
					      <form id="modifyExhibition"action="http://<%=application.getInitParameter("domain") %>/main/ajax/exhibition_update.jsp" method="post" enctype="multipart/form-data" target='blankifr'>
								  <h2>
						      	<span id="exName" name="exName" >
								</span>
								  </h2>
								  <h5>
								  <span id="exIntro"></span>
								  </h5>
							<div>
								<div class="">
							  <img src='http://localhost/exhibitionThreeAdmin/images/%EA%B0%80%EB%A9%B4%EB%AC%B4%EB%8F%84%ED%9A%8C.jpg' >        
								</div>
								<div>
							  <img src='http://localhost/exhibitionThreeAdmin/images/%EA%B0%80%EB%A9%B4%EB%AC%B4%EB%8F%84%ED%9A%8C.jpg' >        
								</div>
							</div>
					      <div class="row">
						      <div class="col-6">
							      <label class="exTitle">전시 번호 </label>
								  <input type="text" id="exNum" name="exNum" class="form-control" readonly="readonly" style="width:70px;height:30px;margin-bottom:20px;text-align:center;"/>	
						      </div>
						      <div class="col-6">
							      <label class="exTitle">전시 노출 여부 </label>
								  <input type="text" id="exStatus" name="exStatus" class="form-control" readonly="readonly" style="width:70px;height:30px;margin-bottom:20px;text-align:center;"/>	
						      </div>
					      </div>
						      	<div class="mb-3">
								  <label for="exampleFormControlInput1" class="exTitle">전시장 / 담당자</label>
							      	 	<select class="form-select" id="exHall" name="exHall"aria-label=".form-select-sm example" style="width:400px">
									  <%
									  	try{
									 	List<ExHallVO> exNameList = aeDAO.selectExhibitionHall();
									  	for(ExHallVO eVO : exNameList){
									  %>
									   <option value='<%=eVO.getExHallNum()%>'><%=eVO.getExName()%> / 담당자 : <%=eVO.getMgrName() %></option>
									   <%
									  	}//end for
									  	}catch(SQLException e){
									  		e.printStackTrace();
									  	}
									   %>
									</select>
								</div>
					      	<div class="mb-3">
					      	<div class="row">
					      	<div class="col-6">
					      	<label class="exTitle">시작일</label>
					      	<input type="date" id="startDate" name="startDate" class="form-control" placeholder="시작 일자"  style="width:200px">
					      	</div>
					      	<div class="col-6">
					      	<label class="exTitle">마감일</label>
					      	<input type="date" id="endDate"name="endDate" class="form-control" placeholder="마감 일자" style="width:200px">
					      	</div>
					      	</div>
					      	</div>
					      	<div class="mb-3" >
						      	<label class="exTitle">전시 포스터</label>
						      	<div class="input-group mb-3" style="width:500px">
						      	 <input type="file" class="form-control" id="modifyExPoster" name="modifyExPoster">
						      	 <input type="hidden" id="hidPoster" name ="hidPoster"/>
								</div>
								  <img id="posterImg"/>
					      	</div>
						   
						    <div class="mb-3">
								<label for="exampleFormControlInput1" class="exTitle">추가 이미지</label>
						      	<div class="input-group mb-3" style="width:500px">
						      	<input type="file" class="form-control" id="modifyAddImg" name="modifyAddImg">
						      	<input type="hidden" id="hidAddImg" name="hidAddImg"/>
								</div>
								  <img id="addImage"/>
							</div>
						      	<div class="mb-3">
								  <label for="exampleFormControlTextarea1" class="exTitle">전시 내용</label>
								  <textarea class="form-control" id="exInfo"name="exInfo" rows="10"></textarea>
								</div>
					      	<div class="row">
						      	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">허용인원</label>
								<input type="text" class="form-control" id="totalCount"name="totalCount" placeholder="100"  style="width:100px">
								</div>
						      	<div class="mb-3 col-6">
								  <label for="exampleFormControlTextarea1" class="exTitle">관람인원</label>
								<input type="text" class="form-control" id="watchCount"name="watchCount" placeholder="0"  style="width:100px">
								</div>
					      	</div>
							
							<div class="row">
							<label class="exTitle">전시 가격</label>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">성인</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" id="adult" name="adult">
								  <span class="input-group-text">원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">청소년</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control" id="teen" name="teen">
								  <span class="input-group-text">원</span>
								</div>
							</div>
						    <div class="mb-3 col-4">
						    	<label class="exTitle">유아/65세 이상</label>
								<div class="input-group" style="width:150px">
								  <input type="text" class="form-control"id="child" name="child">
								  <span class="input-group-text">원</span>
								</div>
							</div>
							</div>
								
					      </form>
					      </div>
					      <div class="modal-footer">
					      			<button type="button" class="btn btn-outline-dark exit">돌아가기</button>
							        <button type="button" class="btn btn-outline-danger" id="deleteBtn">전시 삭제</button>
							        <button type="button" class="btn btn-outline-info" id="modifyBtn">전시 수정</button>
							        <button type="button" class="btn btn-outline-success" id="statusBtn">전시 노출</button>
					      </div>
					    </div>
					  </div>
					</div> --%>
				<!-- 전시 수정 확인 modal -->
				<div class="modal fade" id="confirmModify" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        전시를 수정하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" onclick="updateExhibition()">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- modal -->
				
				<!-- 전시 삭제 확인 modal -->
				<div class="modal fade" id="confirmDelete" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       <span id="deleteExhibitionName"></span> 전시를 삭제하시겠습니까?
				       <input type="hidden" id="hidDeleteExNum"/>
				       <input type="hidden" id="hidDeleteAdd"/>
				       <input type="hidden" id="hidDeletePoster"/>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" id="confirmDeleteOk" onclick="deleteExhibition()">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- modal -->
				<!-- 전시 추가 확인 modal -->
				<div class="modal fade" id="confirmAdd" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        전시를 추가하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" id="addExhibitionOk" onclick="addExhibition()">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- modal -->
				
				<!-- 전시 노출 확인 modal -->
				<div class="modal fade" id="confirmRelease" tabindex="-1"data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        "<span id="exhibitionNameId"></span>" 전시를 사용자에게 보여주겠습니까?
				        <input type="hidden" id="exhibitionNumHid"/>
				        <input type="hidden" id="exStatusHid"/>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" onclick="releaseExhibition()">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- modal -->
				
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
				
            </div>
        </div>
      
    </body>
    <iframe name='blankifr' style='display:none;'></iframe>
</html>
