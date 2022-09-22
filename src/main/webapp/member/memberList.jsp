<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>회원 목록</title>
<link rel="stylesheet" type="text/css" href="../css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>회원 리스트</h1>
		<table class="list">
			<tr>
				<th>이 름</th>
				<th>아이디</th>
				<th>암 호</th>
				<th>이메일</th>
				<th>주 소</th>
				<th>전화번호</th>
				<th>회원등급</th>
				<th>성 별</th>
			</tr>
			<c:forEach var="member" items="${memberList}">
				<tr class="record">
					<td>${member.name}</td>
					<td>${member.userid}</td>
					<td>${member.pwd}</td>
					<td>${member.email}</td>
					<td>${member.address}</td>
					<td>${member.phone}</td>
					<td>${member.grade}</td>
					<td>${member.gender}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
					