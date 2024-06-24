<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
   	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex justify-content-center">
			<div class="join-box my-5">
				<h2 class="text-center">회원가입</h2>
				<input type="text" placeholder="아이디" id="idInput" class="form-control mt-3">
				<input type="password" placeholder="비밀번호" id="passwordInput" class="form-control mt-3">
				<input type="password" placeholder="비밀번호 확인" id="passwordConfirmInput" class="form-control mt-3">
				<input type="text" placeholder="이름" id="nameInput" class="form-control mt-3">
				<input type="text" placeholder="이메일" id="emailInput" class="form-control mt-3">
				<button type="button" id="joinBtn" class="btn btn-primary btn-block mt-3">가입</button>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
	
		function valueCheck(value, alertString){
			if(value.val() == ""){
				alert(alertString + "을(를) 입력하세요.");
				value.focus();
				return false;
			}
			return true;
		}
	
		$(document).ready(function() {
			$("#joinBtn").on("click", function() {
				let loginId = $("#idInput").val();
				let password = $("#passwordInput").val();
				let passwordConfirm = $("#passwordConfirmInput").val();
				let name = $("#nameInput").val();
				let email = $("#emailInput").val();
				
				if(!valueCheck($("#idInput"), "아이디")){
					return;
				}
				
				if(!valueCheck($("#passwordInput"), "패스워드")){
					return;
				}
				
				if(!valueCheck($("#passwordConfirmInput"), "패스워드확인")){
					return;
				}
				
				if(!valueCheck($("#nameInput"), "이름")){
					return;
				}
				
				if(!valueCheck($("#emailInput"), "이메일")){
					return;
				}
				
				if(password != passwordConfirm){
					alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					passwordConfirm.val("");
					passwordConfirm.focus();
					return;
				}
				
				$.ajax({
					type: "post"
					, url : "/user/signup"
					, data: {"loginId":loginId, "password":password, "name":name, "email":email}
					, success:function(data) {
						if(data.result == "success"){
							alert("가입이 완료되었습니다.");
							location.href="/user/signin/view";
						} else {
							alert("가입 오류");
						}
					}
					, error:function() {
						alert("입력 오류");
					}
				});
			});
		});
	</script>
</body>
</html>