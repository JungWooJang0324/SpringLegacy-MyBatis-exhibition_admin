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
        <title>Member</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="http://<%=application.getInitParameter("domain") %>/css/styles.css" rel="stylesheet" /> 
        <!-- jQeury CDN -->
		 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> 
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
		
<script type="text/javascript">
function sendMail(){
	var toAddress = $("#toAddress").val();
	var subject = $("#subject").val();
	var message = $("#message").val();
	$.ajax({
		url: "mail_send.do",
		type: "post",
		data:{
			"toAddress":toAddress,
			"subject":subject,
			"message":message
		},
		dataType:"text",
		error:function(xhr){
			alert("addAjax : "+xhr.status+", "+xhr.statusText);
		},
		success:function(data){
			if(data > 0){
				alert("메일 전송 완료");
				location.reload();
			}else{
				alert("메일 전송 실패하였습니다.");
			}//end else
		}//success  
	});
	
}//sendMail
$(function(){
	
$("#keyword").keyup(function(e){
	//if(e.which==13){
		var keyword = $("#keyword").val();
		var field = $("#field").val();
		var manager="";
		if($("#manager").is(":checked")){
			manager=$("#manager").val();
		}//end if
			$("#tabBody tr").remove();
			$("#selectAll").prop("checked",false);
	 	$.ajax({
			url:"search.do",
			data:{
				keyword:keyword,
				field:field,
				manager:manager
			},
			type:"post",
			dataType:"json",
			success:function(jsonArr){
				var tab="";
				for(var i = 0; i < jsonArr.length; i++){
					tab+="<tr><td><input type='checkbox'name='userid' value='"
					+jsonArr[i].userid+"'/></td><td>"+jsonArr[i].userid+"</td><td>"+jsonArr[i].name+"</td></tr>";
				}				
					$("#tabBody").html(tab);
			},
			error:function(xhr){
				alert(xhr.status);
			}//error
		}); 
	//}//end if
});//click

$("#addAddress").click(function(){
	var addr = "";
	$("input[name='userid']:checked").each(function(i){
		if($(this).val() != "on"){
		addr += ($(this).val())+" ";	
		}//end if
	})
	$("#toAddress").val(addr);
});

});//ready
function selectAll(selectAll){
	const checkboxes
		=document.getElementsByName('userid');
	checkboxes.forEach((checkbox)=>{
		checkbox.checked=selectAll.checked;
	})
}
function sendMail(){
	if($("#toAddress").val()==""){
		alert("이메일을 선택해주세요");
		return;
	}
	if($("#subject").val()==""){
		alert("제목을 입력해주세요");
		$("#subject").focus();
		return;
	}
	if($("#message").val()==""){
		alert("내용을 입력해주세요");
		$("#message").focus();
		return;
		
	}
	var toAddress = $("#toAddress").val();
	var subject = $("#subject").val();
	var message = $("#message").val();
	$.ajax({
		url: "mail_send.do",
		type: "post",
		data:{
			"toAddress":toAddress,
			"subject":subject,
			"message":message
		},
		dataType:"text",
		error:function(xhr){
			alert("addAjax : "+xhr.status+", "+xhr.statusText);
		},
		success:function(data){
				alert(data);
				location.reload();
		}//success  
	});
	
}//sendMail
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
                        <h1 class="mt-4">메일전송</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.do" style="text-decoration:none; color:#333;">Dashboard</a></li>
                            <li class="breadcrumb-item active">메일전송</li>
                        </ol>
                        <div class="card-body">
                        	<div style="background-color:#E7E7DF; width:300px; height:600px; float:left; margin-left:200px;border-radius:10px;">
	                        <div class="input-group mb-3" style="width:300px;margin-left:5px;margin-top:10px; ">
							<select class="form-select" name="field" id="field"style="height:35px;">
								<option value="userid">아이디</option>
								<option value="name">이름</option>
							</select>
							<input type="text" class="form-control"name="keyword" id="keyword"style="width:150px;height:35px;margin-right:10px;">
							</div>
	                        <!-- <input type="text" class="form-control" id="field" style="width:150px; margin:0 auto; margin-top:10px;"> -->
	                        <input type="checkbox" id="manager"value="n"style="margin-left:20px;"/> 일반회원만 보기
	                        <div style="overflow:auto;background-color:#FFFFFF;width:280px;height:450px;margin:0 auto;margin-top:5px;">
	                        <table id="tab" style="text-align:center;">
	                        <thead>
	                        	<tr>
	                        		<th><input type="checkbox"name='userid'onclick='selectAll(this)' id="selectAll"/></th>
	                        		<th>전체선택</th>
	                        		<th></th>
	                        	</tr>
	                        </thead>
	                        <tbody id="tabBody">
	                        <c:if test="${not empty idList}">
	                     	<c:forEach var="id" items="${idList}">
	                        	<tr>
	                        		<td><input type="checkbox"name='userid'value="${id.userid}"/></td>
	                        		<td><c:out value="${id.userid}"/></td>
	                        		<td><c:out value="${id.name}"/></td>
	                        	</tr>
	                     	</c:forEach>
	                        </c:if>
	                        <c:if test="${empty idList }">
	                        	<tr>
	                        		<td colspan="3">회원이 존재하지 않습니다.</td>
	                        	</tr>
	                        </c:if>
	                        </tbody>
	                        </table>
	                        </div>
	                        <div style="margin-left:120px; margin-top:15px;" >
	                        <button class="btn btn-secondary" id="addAddress" >추가</button>
	                        </div>
                        </div>
	                        <div id="wrap" style="width:800px;float:right;">
								<div class="row">	
									<div class="col-1">
									<label>
									To
									</label>
									</div>
									<div class="col-11">
									<input id="toAddress" name="toAddress" class="form-control" type="text" readonly="readonly" style="margin-bottom: 5px;" placeholder=""/>
									</div>
								</div>
								<div class="row">
									<div class="col-1">
									<label>
										Subject
									</label>
									</div>	
									<div class="col-11">
										 <input id="subject" name="subject" class="form-control" type="text"  style="margin-bottom: 5px;" placeholder=""/>
									</div>	
								</div>
								<div>
									Message
									<textarea id="message" name="message" class="form-control" style="height:600px"></textarea>
								</div>
								<div id="btnDiv" style="margin-top: 30px">
									<button type="button" id="backBtn" class="btn btn-outline-dark" onclick="javascript:history.back()"style="float: left;margin-left: 10px">뒤로가기</button> 
									<button type="button" id ="addBtn" class="btn btn-outline-info" 
										style="float: right; margin-right: 10px" onclick="sendMail()"><i class="far fa-arrow-alt-circle-right"></i>SEND</button>
								</div>
							</div>
						
                        </div>
                        </div>
                </main>
              </div>
           </div>
    </body>
</html>
