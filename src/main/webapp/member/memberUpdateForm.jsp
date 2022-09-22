<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정 폼</title>
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
<main>
	<h2>회원 수정</h2>
	<form action="update.do" method="post" name="frm">
		<table>
			<tr>
				<td>이 름</td>
				<td><input type="text" name="name" size="20"
					value="${mVo.name}" readonly></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20"
					value="${mVo.userid}" readonly></td>
			</tr>
			<tr>
				<td>암 &nbsp; 호</td>
				<td><input type="password" name="pwd" size="20">*</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20">*</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"
					value="${mVo.email}"></td>
			</tr>
			<tr>
				<td>주 소</td>
				<td><input type="text" name="address" size="20"
					value="${mVo.address}"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"
					value="${mVo.phone}"></td>
			</tr>
			<tr>
				<td>성 별</td>
				<td><c:choose>
						<c:when test="${mVo.gender==1}">
							<input type="radio" id=”male” name="gender" value="1" checked="checked"> 남성
							<input type="radio" id=”female” name="gender" value="2"> 여성
					</c:when>
					<c:otherwise>
						<input type="radio" id=”male” name="gender" value="1"> 남성
						<input type="radio" id=”female” name="gender" value="2" checked="checked"> 여성
					</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="확인"
					onclick="return joinCheck()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
	</main>
</body>
</html>
