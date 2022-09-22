<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
<link rel="stylesheet" href="../css/shopping.css">
<script type="text/javascript" src="../js/notice.js"></script>
<%@ include file="../header.jsp"%>
</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>게시글 상세보기</h1>
		<table>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${notice.writedate}" /></td>
				<th>조회수</th>
				<td>${notice.readcount }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${notice.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${notice.content }</pre></td>
			</tr>
		</table>
		<!-- 게시글 수정/삭제를 위해 본인 확인이 필요하고, 이를 팝업창을 띄워서 하고 있음
			2. Ajax(javascript/jQuery)를 이용해서 서버와 통신하여 비밀번호 확인을 할 수 있음.
 -->
 		<c:if test="${loginUser.grade eq '0'}">
		<br> <br> <input type="button" value="게시글 수정"
			onclick="open_win('check_pass.do?num=${notice.num}', 'update')">
		<input type="button" value="게시글 삭제"
			onclick="open_win('check_pass.do?num=${notice.num}', 'delete')">
		<input type="button" value="게시글 리스트" onclick="location.href='list.do'">
		<input type="button" value="게시글 등록" onclick="location.href='write.do'">
		</c:if>
	</div>
	</main>
</body>
</html>

