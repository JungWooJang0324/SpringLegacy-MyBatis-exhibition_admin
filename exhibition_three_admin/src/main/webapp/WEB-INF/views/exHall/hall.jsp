<%@page import="javax.imageio.plugins.tiff.ExifGPSTagSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%-- <%@include file="admin_id_session.jsp" %> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="전시장"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Exhibition Hall</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://<%=application.getInitParameter("domain") %>/css/styles.css" rel="stylesheet" />
        <!-- jQeury CDN -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="http://<%=application.getInitParameter("domain") %>/js/datatables-simple-demo.js"></script>
        <!-- 다음 우편번호 api -->
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
 	hr {width:200px; margin: 0px auto; margin-top:10px;}
 	#buttonDiv{height: 50px; }
</style>
<script  type="text/javascript">
$(function(){

	//전시장 상세보기 
	$(".trDetail").click(function(){
			
		var hallNum = $(this).children().find("input:hidden[name=exHallNum]").val();//클릭된 rnum 번호
		
		$("#hallDetail").modal('show');
		
		$.ajax({
			url:"../admin/exHallDetail.do",
			type:"get",
			data:{"exHallNum":hallNum},
			dataType:"json",
			traditional : true,
			error:function(xhr){
				console.log(xhr.status+" / "+xhr.statusText);
				//location.href="admin_error.html";
			},
			success:function(jsonObj){
				$("#exName_de").val(jsonObj.exName);
				$("#exNum_de").val(jsonObj.exNum);
				$("#addr1_de").val(jsonObj.addr1);
				$("#addr2_de").val(jsonObj.addr2);
				$("#zipcode_de").val(jsonObj.zipcode);
				$("#lat_de").val(jsonObj.latitude);
				$("#long_de").val(jsonObj.longitude);
				$("#mgrName_de").val(jsonObj.mgrName);
				$("#mgrTel_de").val(jsonObj.mgrTel);
				$("#exTel_de").val(jsonObj.exTel);
			}
		});
	});
	
	//전시 추가
	 $("#btnAdd").click(function(){
		
		$("#addHall").modal('show');
		
		checkAdd();//값 형
			
		 $("#add").click(function(){
			//null 체크
			if($("#exName_add").val()==""|| $("#exLoc_add").val()=="" || $("#addr1_add").val()==""||
					$("#addr2_add").val()==""||  $("#zipcode_add").val()==""|| $("#lat_add").val()==""||
					$("#long_add").val()==""||	$("#mgrName_add").val()==""|| $("#mgrTel_add").val()==""||
					$("#exTel_add").val()==""){
						
			$("#nullChk").modal('show');
			return;
			}  
			
			$("#confirmAdd").modal('show');
			
			$("#addOk").click(function(){
				$("#confirmAdd").modal('hide'); 
				
				$.ajax({
					url:"../admin/hallAdd.do",
					type:"get",
					dataType: "text",
					async: false,
					data:{
						exName : $("#exName_add").val(), 
						exLoc : $("#exLoc_add").val(), 
						addr1 : $("#addr1_add").val(),
						addr2 : $("#addr2_add").val(),
						zipcode : $("#zipcode_add").val(),
						lat : $("#lat_add").val(),
						longi : $("#long_add").val(),
						mgrName: $("#mgrName_add").val(),
						mgrTel : $("#mgrTel_add").val(),
						exTel : $("#exTel_add").val()
					},
					error:function(xhr, request){
						console.log(xhr.status+" / "+xhr.statusText);
						alert("실패"+xhr.status+"\n"
								+"code : "+request.status+"\n"
								+"msg : "+request.responseText+"\n"
								+"error : "+ error);
						/* location.href="admin_error.html"; */
					},
					success:function(flag){
						if(flag=="true"){
							alert("전시추가 성공");
							return;
						}
							alert("전시추가 실패");
						location.reload();
					}
				});
			});//$("#addOk").click
		});//$("#add").click 
		
		$("#exName_add").val()=="";	$("#exLoc_add").val()=="" ;	$("#addr1_add").val()=="" ;
		$("#addr2_add").val()=="";  $("#zipcode_add").val()==""; $("#lat_add").val()=="";
		$("#long_add").val()=="";	$("#mgrName_add").val()==""; $("#mgrTel_add").val()==""; $("#exTel_add").val()=="" 
	 
	 }); // $("#btnAdd").click
	
	 
	 
	//전시장 수정 버튼 클릭 시 
	 $("#modifyBtn").click(function(){
		 checkUpdate();
		 
		 $("#modifyOk").click(function(){
			 $("#confirmModify").modal('hide');
			 
			$.ajax({
				url:"../admin/hallModify.do",
				type:"get",
				datatype:"json",
				data:{
					"exName" : $("#exName_de").val(),
					"hallNum": $("#exNum_de").val(),
					"addr1": $("#addr1_de").val(),
					"addr2": $("#addr2_de").val(),
					"zipcode" : $("#zipcode_de").val(),
					"lat" : $("#lat_de").val(),
					"lat" : $("#long_de").val(),
					"mgrName" : $("#mgrName_de").val(),
					"mgrTel" : $("#mgrTel_de").val(),
					"exTel" : $("#exTel_de").val()				
				},
				error:function(xhr){
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(flag){
					if(flag=="true"){
						alert("전시 수정 성공");
						location.reload();
						return;
					}
					alert("전시 수정 실패");
				}
			}); 
		});// $("#modifyOk") .click 
	});// $("#modifyBtn").click
	 
	 
	//전시장 삭제
	$("#deleteBtn").click(function(){
		 
		 $("#confirmDelete").modal('show');
		 
		 $("#deleteOk").click(function(){
			
			 $("#confirmDelete").modal('hide');
			 
			$.ajax({
				url:"../admin/hallRemove.do",
				type:"get",
				data:{ "exHallNum": $("#exNum_de").val()},
				dataType: "text",
				async: false,
				traditional : true,
				error:function(xhr){
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(flag){
					if(flag=="true"){
						alert("전시 삭제 성공");
						$("#hallDetail").modal('hide');
						location.reload();
						return;
					}
					alert("전시 삭제 실패");
				}
			}); 
		});// $("#removeOk") .click 
	});// $("#removeBtn").click
	
	
	
	//전시장 추가 팝업에서 주소 찾기 (daum 주소찾기 api)
	$("#addr1_add").click(function(){
		 new daum.Postcode({
		        oncomplete: function(data) {
		        	 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	 			$('#zipcode_add').val(data.zonecode);      // 우편번호(5자리)
	 			$('#addr1_add').val(data.address);       // 기본주소 
		        }
	    }).open();
	});
	
	//전시장 상세 팝업에서 주소 찾기 (daum 주소찾기 api)
	$("#addr1_de").click(function(){
		 new daum.Postcode({
		        oncomplete: function(data) {
		        	 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	 			$('#zipcode_de').val(data.zonecode);      // 우편번호(5자리)
	 			$('#addr1_de').val(data.address);       // 기본주소 
		        }
	    }).open();
	});
	
	$("#searchBtn").click(function(){
		document.searchFrm.submit();		
	});
	
});//ready


function checkAdd(){
	
	$(document).on("keyup", ".addChk", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
	});

	
	var coordinate = ["#lat_add","#long_add"];
 	var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/; //폰 번호 검증
	var regTel = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;  //일반전화 검증
	var reg =  /^[+-]?(\d{1,3})?[.](\d{0,6})?$/; //좌표검증
	
	
	$("#mgrTel_add").change(function(){
	     if (!regPhone.test($("#mgrTel_add").val())) {
	         alert('-를 넣어 폰 번호를 정확히 입력해주세요.');
	         return false;
	     }
	});
	
	$("#exTel_add").change(function(){
	     if (!regTel.test($("#exTel_add").val())) {
	         alert('-를 넣어 전화번호를 정확히 입력해주세요.');
	         return false;
	     }
	});
	
 	$.each(coordinate, function(idx, c){
		$(c).change(function(){
		     if (!reg.test($(c).val())) {
		         alert('좌표를 정확히 입력해주세요. 정수부분 3자리, 소수부분 6자리까지 입력가능합니다.');
		         return false;
		     }
		});
	});   
	
	  
}


function checkUpdate(){
	
	var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/; //폰 번호 검증
	var regTel = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;  //일반전화 검증
	var reg =  /^[+-]?(\d{1,3})?[.](\d{0,6})?$/;; //좌표 검증
	
	
     if (!regPhone.test($("#mgrTel_de").val())) {
         alert('-를 넣어 폰 번호를 정확히 입력해주세요.');
         return;
     }
	
     if (!regTel.test($("#exTel_de").val())) {
         alert('-를 넣어 전화번호를 정확히 입력해주세요.');
         return;
     }
     
     if (!reg.test($("#lat_de").val())) {
         alert('위도를 정확히 입력해주세요. +-, 정수부분 3자리, 소수부분 6자리까지 입력가능합니다.');
         return;
     }
     
     if (!reg.test($("#long_de").val())) {
         alert('경도를 정확히 입력해주세요. +-, 정수부분 3자리, 소수부분 6자리까지 입력가능합니다.');
         return;
     }
     
     $("#confirmModify").modal('show');
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
	    alert("한글 "+ Math.floor(maxByte/3)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
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
                        <h1 class="mt-4">전시장 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp" style="text-decoration:none; color:#333;">Dashboard</a></li>
                            <li class="breadcrumb-item active">전시장 관리</li>
                        </ol>
                        <!-- 검색 div -->
                         <div  style="width: 400px; float: right; margin-bottom: 10px">
                            <form id = "searchFrm" class="d-flex" name="searchFrm" action="http://localhost/exhibitionThreeAdmin/admin/hall.do">
		                        	 <select name = "option" id="option" class="form-select" aria-label=".form-select-sm example"  >
										  <option ${param.option =="0"? "selected":""} value="0">전시장명</option>
										  <option ${param.option =="1"? "selected":""} value="1">전시 위치</option>
									</select>
		                        	<input type="text" name="keyword" class="form-control" style="margin-right: 10px">
		                        	<button type="button" id="searchBtn" class="btn btn-outline-dark btn-sm" style="height: 35px;">
		                        		<i class="fa-solid fa-magnifying-glass"></i>
		                       		</button>
						      </form>
                        </div>
                        <!-- 전시장 테이블 -->
                        <div class="card-content" style="margin: 0px auto; clear:both;">
                            <div class="table-responsive">
                            	<form>
                            	<table class="table table-hover" id="hallTab" style="text-align: center">
                                    <thead>
                                        <tr>
                                            <th style="width: 300px">전시장 번호</th>
                                            <th>전시장 명</th>
                                            <th style="width: 400px">전시 위치</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                  	<c:forEach var="exHallVO" items="${exHallList}">
                                        <tr style="cursor:pointer" class = "trDetail" >
                                            <td><c:out value="${exHallVO.ex_hall_num}"/></td>
                                            <td><c:out value="${exHallVO.ex_hall_name}"/></td>                                          	
                                            <td><c:out value="${exHallVO.ex_loc}"/></td>
                                            <td id="hiddenTd" style="padding: 0px;">
                                        		<input id="exHallNum" class="exHallNum" name="exHallNum" type="hidden" value="${exHallVO.ex_hall_num}"/>
                                        	</td>
                                        </tr>
                                     </c:forEach>
                                   	<c:if test="${empty exHallList}">
                              			<tr><td colspan="3">저장된 값이 없습니다</td></tr>
                           			</c:if>
                                     </tbody>
                                  </table>
                                  <div>
                                  </div>
                                  </form>
                                </div>
                              	<!-- 버튼 Div -->
                              	<div id="buttonDiv">
						  			<input type="button" id="btnAdd" class="btn btn-dark" style="float:right;" value="전시장 추가"/>
						  		</div>
                            </div>
                             </div>
                            <!-- 페이지 이동 -->    
                            <div id="pageNavigation">
							 	<ul class="pagination justify-content-center">
							 	<%
									String option = request.getParameter("option");
									String keyword = request.getParameter("keyword");
								%>
							 	<c:choose>
							 	<c:when test="${prev }">
									<li><a href="http://localhost/exhibitionThreeAdmin/admin/hall.do?currentPage=${prevNum}<%=!"".equals(keyword) && keyword!=null?"&option="+option+"&keyword="+keyword:""%>" 
												style="margin-right:10px;text-decoration:none;"class="text-secondary">
										<c:out value="이전"/>
									</a></li>
								</c:when>
							 	</c:choose>
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
									<li><a href="http://localhost/exhibitionThreeAdmin/admin/hall.do?currentPage=${i}<%=!"".equals(keyword) && keyword!=null?"&option="+option+"&keyword="+keyword:""%>"
											style="margin-right:10px;text-decoration:none;"class="text-secondary">
										<c:out value="${i}"/>
									</a></li>
								</c:forEach>
								<c:if test="${next }">
								 	<li><a href="http://localhost/exhibitionThreeAdmin/admin/hall.do?currentPage=${nextNum}<%=!"".equals(keyword) && keyword!=null?"&option="+option+"&keyword="+keyword:""%>" 
												style="margin-right:10px;text-decoration:none;"class="text-secondary">
											<c:out value="다음"/>
									</a></li>
							 	</c:if>
								</ul> 
							</div>
						</main>
                <!-- footer -->
               	<jsp:include page="../commons/admin_footer.html"/> 
				<!-- 전시장 추가 Modal -->
				<div class="modal fade" id="addHall" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
				 <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable" >
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">전시장 추가</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body" >
				      	<form id="hallAddFrm" action="admin_">
					 	<table class="modalTab" style="margin: 0px auto; " >
					 		<tr>
					 			<th colspan="2" >전시장</th>
					 		</tr>
					 		<tr>
					 			<td>
					 				<input id="exName_add" type="text" name="exHall" placeholder="전시장명" class="form-control" style="margin-bottom: 20px" onKeyUp="javascript:chkByte(this,'100')"/>
				 				</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2">지역</th>
					 		</tr>
					 		<tr>
					 			<td  style="padding-bottom: 20px">
									<select  id="exLoc_add" class="form-select" name="exLoc">
										<option value="서울">서울</option>
										<option value="경기">경기</option>
										<option value="강원">강원</option>
										<option value="충북">충북</option>
										<option value="충남">충남</option>
										<option value="경북">경북</option>
										<option value="경남">경남</option>
										<option value="전북">전북</option>
										<option value="전남">전남</option>
										<option value="제주">제주</option>
									</select>
								</td>
					 		</tr>
					 		<tr>
					 			<th style="width: 250px;" >주소</th><th style="padding-left: 20px">우편번호</th>
					 		</tr>

					 		<tr>
					 			<td >
					 				<input type="text" id="addr1_add" name="addr" class="form-control"  placeholder="주소" />
				 				</td>
				 				<td style="padding-left: 20px">
				 					<input id="zipcode_add"type="text" name="zipcode" class="form-control" placeholder="우편번호" style="padding-left: 20px"/>
			 					</td>
					 		</tr>
					 		<tr >
					 			<td colspan="2" style="padding-bottom: 20px;" >
					 				<input type="text" id="addr2_add" name="addr" placeholder="상세주소" class="form-control" style="width: 250px; "  onKeyUp="javascript:chkByte(this,'100')" />
				 				</td>
					 		</tr>
					 		<tr>
					 			<th>위도</th><th style="padding-left: 20px" >경도</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input id="lat_add"  type="text" name="latitude" placeholder="위도"  class="form-control"  style="width: 150px; " onKeyup="this.value=this.value.replace(/[^0-9.-]/g,'');"/>
				 				</td>
			 					<td style="padding-bottom: 20px; padding-left: 20px">
			 						<input id="long_add" type="text" name="longitutde" placeholder="경도" class="form-control" style="width: 150px; " onKeyup="this.value=this.value.replace(/[^0-9.-]/g,'');"/>
		 						</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시장 담당자</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input id="mgrName_add" type="text" name="mgrName" placeholder="담당자명" class="form-control" onKeyUp="javascript:chkByte(this,'50')" />	
				 				</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시 담당자 번호</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px" >
					 				<input id="mgrTel_add" type="text" name="mgrTel" placeholder="담당자 번호" class="addChk form-control" />
					 			</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시장 번호</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 10px">
				 					<input id="exTel_add" type="text" name="exTel" placeholder="전시장 번호" class="addChk form-control"/>
				 				</td>
					 		</tr>
					 	</table>
					 	</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">돌아가기</button>
				        <button id="add" type="button" class="btn btn-outline-info" data-bs-dismiss="modal" >전시 추가</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 전시장 상세 Modal -->
				<div class="modal fade" id="hallDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">전시장 상세</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					 	<table class="modalTab">
					 		<tr>
					 			<th colspan="2" >전시장 명</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input type="text" class="form-control" id="exName_de" style="width: 250px; margin-right: 30px" onKeyUp="javascript:chkByte(this,'100')"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2">전시장 번호</th>
					 		</tr>
					 		<tr>
					 			<td  style="padding-bottom: 10px" >
					 				<input type="text" id="exNum_de" name="exNum_de" class="form-control" readonly="readonly" style="width:80px;height:30px;margin-bottom:10px;"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th >상세주소</th>
					 			<th style="width: 200px">우편번호</th>
					 		</tr>
					 		<tr>
					 			<td >
					 				<input type="text" id="addr1_de" class="form-control"  style="width: 250px; margin-right: 30px"/>
					 			</td>
					 			<td >
					 				<input type="text" id="zipcode_de" class="form-control"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px" colspan="2">
					 				<input type="text" id="addr2_de" class="form-control" style="width: 250px; margin-right: 30px" onKeyUp="javascript:chkByte(this,'100')"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th>위도</th>
					 			<th>경도</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input type="text" id="lat_de" class="form-control" style="width: 100px; margin-right: 30px" onKeyup="this.value=this.value.replace(/[^0-9.-]/g,'');"/>
					 			</td>
					 			<td style="padding-bottom: 20px">
					 				<input type="text" id="long_de"  class="form-control" style="width: 100px; margin-right: 30px"  onKeyup="this.value=this.value.replace(/[^0-9.-]/g,'');"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시장 담당자</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input type="text" id="mgrName_de" class="form-control" style="width: 250px; margin-right: 30px" onKeyUp="javascript:chkByte(this,'50')"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시 담당자 번호</th>
					 		</tr>
					 		<tr>
					 			<td style="padding-bottom: 20px">
					 				<input type="text" id="mgrTel_de"  class="form-control" style="width: 250px; margin-right: 30px"/>
					 			</td>
					 		</tr>
					 		<tr>
					 			<th colspan="2" >전시장 전화번호</th>
					 		</tr>
					 		<tr>
					 			<td  style="padding-bottom: 10px">
					 				<input type="text" id="exTel_de" class="form-control" style="width: 250px; margin-right: 30px"/>
					 			</td>
					 		</tr>
					 	</table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">돌아가기</button>
				        <button id="deleteBtn" type="button" class="btn btn-outline-danger">전시장 삭제</button>
				        <button id="modifyBtn" type="button" class="btn btn-outline-info">전시장 수정</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 전시장 삭제 확인 모달  -->
				<div class="modal fade" id="confirmDelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body" style="text-align: center">
				        전시 내용을 삭제하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="button" class="btn btn-primary" id="deleteOk">OK</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 전시장 수정 확인 모달  -->
				<div class="modal fade" id="confirmModify" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        전시 내용을 수정하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button id="modifyOk" type="button" class="btn btn-primary">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 전시장 수정 확인 모달  -->
				<div class="modal fade" id="confirmAdd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        전시장을 추가하시겠습니까?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button id="addOk" type="button" class="btn btn-primary">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- 빈칸 알림  -->
				<div class="modal fade" id="nullChk" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        데이터를 모두 입력해주세요.
				      </div>
				      <div class="modal-footer">
				        <button id="addOk" type="button" class="btn btn-primary" data-bs-dismiss="modal">Ok</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!--  모달 끝 -->
			</div>
		</div>
 	</body>
</html>
