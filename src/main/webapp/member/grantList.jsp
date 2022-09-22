<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/shopping.css">
<title>Insert title here</title>
</head>
<body>
	<main>
		<form action="grantuser.do" method="post" name="frm">
			<div id="wrap" align="center">
				<h1>권한 부여</h1>
				<table class="list">
					<tr>
						<th>이 름</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>회원등급</th>
						<th>성 별</th>
					</tr>
					<c:forEach var="grantlist" items="${grantlist}" varStatus="status">
						<tr class="record">
							<td class="name">${grantlist.name}</td>
							<td class="userid"><input type="text" name="userid"
								size="10" value="${grantlist.userid}" readonly></td>
							<td class="email">${grantlist.email}</td>
							<td class="grade"><c:if
									test="${grantlist.userid != loginUser.userid}">
									<c:choose>
										<c:when test="${grantlist.grade==0}">
											<input type="radio" name="grantcheck${status.index}"
												value="0" checked="checked">관리자
									<input type="radio" name="grantcheck${status.index}" value="1">사용자
						</c:when>
										<c:otherwise>
											<input type="radio" name="grantcheck${status.index}"
												value="0">관리자
									<input type="radio" name="grantcheck${status.index}" value="1"
												checked="checked">사용자
								</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${grantlist.userid == loginUser.userid}">
									본인
									</c:if></td>

							<td class="gender">${grantlist.gender}</td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="등록">
			</div>
		</form>
	</main>
</body>
</html>