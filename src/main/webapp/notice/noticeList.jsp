<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="../css/shopping.css">
</head>
<body>
<%@ include file="../header.jsp"%>" %>
<main>
	<div id="wrap" align="center">
		<h1>공지 사항</h1>
		<h4>소니스토어에 많이 물어보시는 질문과 새로운 소식을 만나보세요.</h4>
		<table class="list">
			<tr>
				<c:if test="${loginUser.grade eq '0'}">
					<td colspan="5" style="border: white; text-align: right">
						<a href="write.do">게시글 등록</a>
					</td>
				</c:if>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="notice" items="${noticeList }">
				<tr class="record">
					<td class="num">${notice.num }</td>
					<td class="title"><a href="view.do?num=${notice.num}">
							${notice.title } </a></td>
					<td class="date"><fmt:formatDate value="${notice.writedate}" /></td>
					<td class="count">${notice.readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</main>
</body>
</html>
				