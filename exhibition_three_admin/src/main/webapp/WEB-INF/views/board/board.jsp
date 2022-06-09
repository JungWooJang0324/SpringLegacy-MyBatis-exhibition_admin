<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@include file="../commons/admin_session.jsp" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="게시판"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- /exhibitionThreeAdmin/admin/
	/admin/admin/	 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Board</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://<%=application.getInitParameter("domain") %>/css/styles.css" rel="stylesheet" />
        <!-- jQeury CDN -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/assets/demo/chart-area-demo.js"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/js/datatables-simple-demo.js"></script>
     
        <style>
        	hr {width:200px; margin: 0px auto; margin-top:10px;}
        	#searchDiv{ margin-bottom: 30px; text-align: right}
       		#buttonDiv{height: 50px; }
        </style>
        
<script type="text/javascript">

$(function(){
	
	$("#searchBtn").click(function(){
		document.searchFrm.submit();		
	});
	 
	
	//글쓰기버튼 클릭
	$("#btnAdd").click(function(){
		location.href="http://localhost/exhibitionThreeAdmin/admin/formBoardAdd.do";
	});
	
	//게시글 상세보기 
	$(".trDetail").click(function(){
			
		var bdId = $(this).children().find("input:hidden[name=bdId]").val();
		
		$("#boardDetail").modal('show'); 
		
		$.ajax({
			url:"../admin/boardDetail.do",
			type:"get",
			data:{ "bdId":bdId	},
			dataType:"json",
			error:function(xhr){
				console.log("※에러"+xhr.status);
			},
			success:function(jsonObj){
				$("#bdId_de").val(jsonObj.bdId);
				$("#inputDate_de").val(jsonObj.inputDate);
				$("#title_de").val(jsonObj.title);
				$("#id_de").val((jsonObj.userId==""||jsonObj.userId==null)? jsonObj.adminId:jsonObj.userId );
				$("#catName_de").val(jsonObj.catNum); 
				$("#description_de").val(jsonObj.description.replaceAll("br", "\n"));
				$("#imgFile_de").val(jsonObj.imgFile);
			}
		}); 
	});
	
	 
	//게시글 수정 버튼 클릭 시 
	 $("#modifyBtn").click(function(){
		 
		 $("#confirmModify").modal('show');
		 
		 $("#modifyOk").click(function(){
			 $("#confirmModify").modal('hide');
			$.ajax({
				url:"../admin/boardModify.do",
				type:"post",
				dataType:"json",
				data:{
					"catNum" : $("#catName_de").val(),
					"title": $("#title_de").val(),
					"description": $("#description_de").val(),
					"bdId" : $("#bdId_de").val()
				},
				error:function(xhr){
					alert("※에러"+xhr.status);
				},
				success:function(jsonObj){
					if(jsonObj.flag==true){
						alert("업데이트 성공!");
						location.reload();
					}else{
						alert("업데이트 실패!");
					}	
				}
			}); 
		});// $("#modifyOk") .click 
	});// $("#").click
	
	
});


//게시글 삭제
function deletePost( bdId ){
	//tr클릭 게시글 상세 팝업 막기
	event.stopPropagation(); 
	
	 $("#confirmDelete").modal('show');
	 
	 $("#deleteOk").click(function(){
		$("#confirmDelete").modal('hide');
		$.ajax({
			url:"../admin/postRemove.do",
			type:"post",
			data:{ "bdId": bdId },
			error:function(xhr){
				alert("※에러"+xhr.status);
			},
			dataType : "json",
			success:function(jsonObj){
				if(jsonObj.flag==true){
					alert("삭제 성공!");
					location.reload();
				}else{
					alert("삭제 실패!");
				}	 
			}
		}); 
	});// $("#modifyOk") .click 
}

function chkByte(obj, maxByte){
	var str = obj.value;
	var str_len = str.length;

	var rbyte = 0;
	var rlen = 0;
	var one_char = "";
	var str2 = "";

	for(var i=0; i<str_len; i++){
		one_char = str.charAt(i);
		
		if(escape(one_char).length > 4){
		    rbyte += 3;                                         //한글3Byte
		}else{
		    rbyte++;                                            //영문 등 나머지 1Byte
		}
	
		if(rbyte <= maxByte){
		    rlen = i+1;                                          //return할 문자열 갯수
		}
	}

	if(rbyte > maxByte){
	    alert("한글 "+Math.floor(maxByte/3)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
	    str2 = str.substr(0,rlen);                                  //문자열 자르기
	    obj.value = str2;
	    fnChkByte(obj, maxByte);
	    return;
	}
}
</script> 
 </head>
 <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="http://localhost/exhibitionThreeAdmin/admin/index.do">Exhibition Admin</a>
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
                        <h1 class="mt-4">게시판 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp" style="text-decoration:none; color:#333;">Dashboard</a></li>
                            <li class="breadcrumb-item active">게시판 관리</li>
                        </ol>
                        <!-- 검색 div -->
                        <div class="card-body" style="width: 400px; float: right;">
                            <form class="d-flex" id = "searchFrm" class="d-flex" name="searchFrm" action="http://localhost/exhibitionThreeAdmin/admin/board.do">
	                        	 <select name = "optNum" id="optNum" class="form-select" aria-label=".form-select-sm example"   >
									  <option ${param.optNum =="0"? "selected":""} value="0">제목 </option>
									  <option ${param.optNum =="1"? "selected":""} value="1">작성자</option>
									  <option ${param.optNum =="2"? "selected":""} value="2">작성일</option>
									  <option ${param.optNum =="3"? "selected":""} value="3">카테고리</option>
								</select>
	                        	<input type="text" id="search"  name="keyword" value="${param.keyword}" class="form-control" style="margin-right: 10px">
	                        	<button type="button" id="searchBtn" class="btn btn-outline-dark btn-sm" style="height: 35px;">
	                        		<i  class="fa-solid fa-magnifying-glass"></i>
	                       		</button>
						      </form>
                        </div>
                       
                        <!-- 전시장 테이블 -->
                        <div class="card-content" style=" margin: 0px auto; clear:both;">
                            <div class="table-responsive">
                            	<table class="table table-hover" id="boardTab" style="text-align: center">
                                    <thead>
                                        <tr>
                                            <th>글번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>카테고리</th>
                                            <th>관리</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                  	<c:forEach var="bDomain" items="${boardList}">
                                  	<!-- 검색했을 때 6번 나오는 거 해결필요 -->
                                  	<c:set var="i" value='${i+1 }'/>
									<c:set var="cnt" value='${totalCnt-(currentPage-1)*pageScale-i+1 }'/>
                                         <tr class="trDetail" style="cursor:pointer">
                                            <td><c:out value="${cnt}"/></td>
                                            <td><c:out value="${bDomain.title}"/></td>                                          	
                                            <td>
                                            	<c:choose>
                                            	<c:when test="${bDomain.userid !=null }">
                                           			 <c:out value="${bDomain.userid}"/>
												</c:when>
                                            	<c:when test="${bDomain.adminid !=null}">
                                           			 <c:out value="${bDomain.adminid}"/>
												</c:when>
                                            	</c:choose>
                                           	</td>
                                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${bDomain.input_date}"/></td>
                                            <td><c:out value="${bDomain.cat_name}"/></td>
                                   	 		<td><button id="deleteBtn" type="button" class="btn btn-secondary btn-sm" onclick="deletePost(${bDomain.bd_id})">삭제</button></td>
	                                   	 	<td id="hiddenTd" style="padding: 0px;">
                                        		<input id="bdId" class="bdId" name="bdId" type="hidden" value="${bDomain.bd_id}"/>
                                        	</td> 
		                                   	 <c:set var="cnt" value="${cnt-1}"></c:set>
	                                   	 </tr>
                                   	</c:forEach>
                                   	<c:if test="${empty boardList}">
                              			<tr>
                              				<td colspan="6">등록된 글이 없습니다</td>
                              			</tr>
                          			</c:if>
                                      </tbody>
                                   </table>
                                 </div>
                                <!-- 글쓰기 버튼 -->
                                <div id="buttonDiv">
						  			<button type="button" class="btn btn-dark" style="float:right;" id="btnAdd" data-bs-target="#addModal" data-bs-toggle="modal">글쓰기</button>
						  		</div>
                               </div>
                              </div>
               		 <!-- 페이지 이동 -->
                   	 <div id="pageNavigation">
							<ul class="pagination justify-content-center"> 
							<%
								String optNum = request.getParameter("optNum");
								String keyword = request.getParameter("keyword");
							%>
							<c:if test="${prev }">
								 	<li><a href="http://localhost/exhibitionThreeAdmin/admin/board.do?currentPage=${prevNum}<%=!"".equals(keyword) && keyword!=null?"&optNum="+optNum+"&keyword="+keyword:""%>" 
												style="margin-right:10px;text-decoration:none;"class="text-secondary">
											<c:out value="이전"/>
									</a></li>
							 	</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
									<li><a href="http://localhost/exhibitionThreeAdmin/admin/board.do?currentPage=${i}<%=!"".equals(keyword) && keyword!=null?"&optNum="+optNum+"&keyword="+keyword:""%>" 
											style="margin-right:10px;text-decoration:none;"class="text-secondary">
										<c:out value="${i}"/>
									</a></li>
								</c:forEach>
								<c:if test="${next }">
								 	<li><a href="http://localhost/exhibitionThreeAdmin/admin/board.do?currentPage=${nextNum}<%=!"".equals(keyword) && keyword!=null?"&optNum="+optNum+"&keyword="+keyword:""%>" 
												style="margin-right:10px;text-decoration:none;"class="text-secondary">
											<c:out value="다음"/>
									</a></li>
							 	</c:if>
							</ul>
						</div>
                	</main>
                	<jsp:include page="../commons/admin_footer.html"/> 
               		</div>
                	</div>
                <!-- 게시글 상세 Modal -->
				<div class="modal fade" id="boardDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			 	<div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">게시글 내용</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					 	<table id="modalTab">
					 	<thead>
					 		<tr>
					 			<th style="width: 300px">글 번호</th>
					 			<th style="width: 300px">수정/작성 일자</th>
					 		</tr>
				 		</thead>
				 		<tbody>
					 		<tr>
					 			<td>
					 				<input type="text" id="bdId_de"  name="bdId_de" class="form-control" readonly="readonly" style="width:100px;height:30px;margin-bottom:20px;"/>
					 			</td>
					 			<td>
					 				<input type="text" id="inputDate_de" name="inputDate_de" class="form-control" readonly="readonly" style="width:120px;height:30px;margin-bottom:20px;"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th >제목</th>
					 			<th>카테고리</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px;" >
					 				<input type="text" id="title_de" class="form-control" style="width: 200px" onKeyUp="javascript:chkByte(this,'100')"/>
				 				</td>
					 			<td style="padding-bottom: 20px"> 
									<select id="catName_de" name="catName_de" class="form-select">
										<c:forEach var = "abDomain" items="${categoryList}">
											<option value="${abDomain.cat_num}"><c:out value="${abDomain.cat_name}"/></option>
										</c:forEach>
									</select>
								</td>
					 		</tr>
					 		<tr>
					 			<th>작성자</th>
					 			<th>이미지</th>
					 		</tr>
					 		<tr>
					 			<td >
				 					<input type="text" id="id_de"  name="id_de" class="form-control" readonly="readonly" style="width:200px; height:30px;margin-bottom:20px;"/>
				 				</td>
								<td >
									<input type="text" id="imgFile_de"  name="imgFile_de" class="form-control" readonly="readonly" style="width:300px;height:30px;margin-bottom:20px;"/>
								</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2">글 내용</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px" colspan="2">
					 				<textarea id="description_de" class="form-control" style="overflow-y:scroll;width: 760px; height: 200px ">
					 				</textarea>
				 				</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2"></th>
					 		</tr>
					 		</tbody>
					 	</table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal" >돌아가기</button>
				        <button id="modifyBtn" type="button" class="btn btn-outline-info">게시글 수정</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 게시글 수정 확인 모달  -->
				<div class="modal fade" id="confirmModify" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        게시글을 수정하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button id="modifyOk" type="button" class="btn btn-primary">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 게시글 삭제 확인 모달  -->
				<div class="modal fade" id="confirmDelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        게시글을 삭제하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button id="deleteOk" type="button" class="btn btn-primary">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
    </body>
</html>
