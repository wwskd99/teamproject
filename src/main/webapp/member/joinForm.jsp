<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>회원가입 폼</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
<main>
	<h2>회원 가입</h2>
	'*' 표시 항목은 필수 입력 항목입니다.
	<form action="join.do" method="post" name="frm">
		<table>
			<tr>
				<td>이 름</td>
				<td><input type="text" name="name" size="20">*</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20" id="userid">*
					<input type="hidden" name="reid" size="20"> <input
					type="button" value="중복 체크" onclick="idCheck()"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" size="20">*</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20">*</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>주 소</td>
				<td><input type="text" name="address" size="20"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>
			<tr>
				<td>성 별</td>
				<td><input type="radio" id=”male” name="gender" value="1"
					checked="checked"> <label for=”male”>남성</label> <input
					type="radio" id=”female” name="gender" value="2"> <label
					for=”female”>여성</label></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="확인"
					onclick="return joinCheck()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="취소">
				</td>
			</tr>
			<tr>
				<td colspan="2">${message}</td>
			</tr>
		</table>
	</form>
	</main>
</body>
</html>
