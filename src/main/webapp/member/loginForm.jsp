<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
header {
	display: flex;
	justify-content: center;
}

form {
	padding: 10px;
}

.input-box {
	position: relative;
	margin: 10px 0;
}

.input-box>input {
	background: transparent;
	border: none;
	border-bottom: solid 1px #ccc;
	padding: 20px 0px 5px 0px;
	font-size: 14pt;
	width: 100%;
}

input::placeholder {
	color: transparent;
}

input:placeholder-shown+label {
	color: #aaa;
	font-size: 14pt;
	top: 15px;
}

input:focus+label, label {
	color: #8aa1a1;
	font-size: 10pt;
	pointer-events: none;
	position: absolute;
	left: 0px;
	top: 0px;
	transition: all 0.2s ease;
	-webkit-transition: all 0.2s ease;
	-moz-transition: all 0.2s ease;
	-o-transition: all 0.2s ease;
}

input:focus, input:not(:placeholder-shown) {
	border-bottom: solid 1px #8aa1a1;
	outline: none;
}

input[type=submit] {
	background-color: #8aa1a1;
	border: none;
	color: white;
	border-radius: 5px;
	width: 100%;
	height: 35px;
	font-size: 14pt;
	margin-top: 100px;
}

input[type=button] {
	background-color: #8aa1a1;
	border: none;
	color: white;
	border-radius: 5px;
	width: 100%;
	height: 35px;
	font-size: 14pt;
	margin-top: 100px;
}

#forgot {
	text-align: right;
	font-size: 12pt;
	color: rgb(164, 164, 164);
	margin: 10px 0px;
}

.login {
	padding: 120px 0;
	width: 60%;
	max-width: 100%;
}
</style>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
<meta charset="UTF-8">

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<%@ include file="../header.jsp"%>

<!-- 쿠키값으로 id정보 받아오기 -->
<%
String cookie = "";
Cookie[] cookies = request.getCookies(); //쿠키생성
if (cookies != null && cookies.length > 0) {
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals("userId")) {	// 맞는것을 가지고오면
			cookie = cookies[i].getValue();		// 저장
%>
<script>
			$(function() {	// 아래 스크립트 전부 실행후에 실행하는 함수
		
				$("#checkbox").prop("checked", true);	// 체크박스를 체크로 표시
		
			});	
</script>

<%
		}
	}
}
%>

<title>로그인 폼</title>
<!-- <link rel="stylesheet" type="text/css" href="../css/main.css"> -->


</head>

<body>
<main>
	<center>
		<div class=login>
			<header>
				<h2>Login</h2>
			</header>
			<form action="login.do" method="post" name="frm">

				<table>
					<tr>
						<div class="input-box">
							<input id="userid" type="text" name="userid" value="<%=cookie%>"
								placeholder="아이디"> <label for="userid">아이디</label>
						</div>
					</tr>

					<tr>
						<div class="input-box">
							<input id="pwd" type="password" name="pwd" placeholder="비밀번호">
							<label for="pwd">비밀번호</label>
						</div>
					</tr>
					<tr>
						<input id="checkbox" type="checkbox" name="checkbox" value="email_save">이메일 아이디 저장
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="로그인" onclick="return loginCheck()">
							<input type="button" value="회원 가입" onclick="location.href='join.do'">
							<input type="button" value="비밀번호 찾기" onclick="location.href='./find.do'">
						</td>
					</tr>
					<c:if test="${!empty message}">
						<script>
							alert("회원가입이 완료되었습니다.")
						</script>
					</c:if>			

					<c:if test="${!empty cartlogin}">
						<script>
							alert("로그인 정보가 필요합니다.")
						<%
							session.removeAttribute("cartlogin");
						%>
						</script>
					</c:if>
				</table>
			</form>
		</main>
</body>
</html>