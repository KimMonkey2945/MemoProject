<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl코어 라이브러리 -->   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">
	
<title>로그인</title>
</head>
<body>

	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		
		<section class="d-flex justify-content-center">
		
			<form id="loginForm"> <!-- 폼은 그냥 껍데기일뿐 이것을 이용해서 이벤트만 발생 -->
				<div class="login-box my-5"> <!-- 위아래 마진은  my로하기 -->
					<input type="text" class="form-control mt-3" placeholder="아이디" id="loginIdInput"> <!-- 폼컨트롤의 특징은 width를 100으로 잡음 -->
					<input type="password" class="form-control mt-3" placeholder="비밀번호" id="passwordInput">
					<button type="submit" class="btn btn-info btn-block mt-3">로그인</button>
					
					<div class="text-center mt-3">
						<a href="/user/signup_view">회원가입</a>
					</div>
				</div>
			</form>
		
		</section>
	
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>

	<script>
		$(document).ready(function(){
			$("#loginForm").on("submit", function(e){
				// return false; 도 가능하지만...
				e.preventDefault(); //이렇게 하면 됨.
				
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				
				if(loginId == ""){
					alert("아이디를 입력하세요!");
					return;
				}
				if(password == ""){
					alert("비밀번호를 입력하세요!");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_in",
					data:{"loginId":loginId, "password":password},
					success:function(data){
						if(data.result == "success"){
							location.href="/post/list_view";
						}else{
							alert("아이디/비밀번호를 확인하세요!!")
						}
						
					},
					error:function(){
						alert("에러 발생!!!!")
					}
					
				});
				
				
				
				
				
			});
			
		});
	
	</script>










</body>
</html>