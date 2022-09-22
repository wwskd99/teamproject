<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="../css/shopping.css">
<script type="text/javascript" src="../js/notice.js"></script>
<%@ include file="../header.jsp"%>
</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>게시글 수정</h1>
		<form name="frm" method="post" action="update.do">
			<input type="hidden" name="num" value="${notice.num}">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" size="70" name="title"
						value="${notice.title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="70" rows="15" name="content">${notice.content}</textarea></td>
				</tr>
			</table>
			<br>
			<br> <input type="submit" value="등록"
				onclick="return noticeCheck()"> <input type="reset"
				value="다시 작성"> <input type="button" value="목록"
				onclick="location.href='list.do'">
		</form>
	</div>
	</main>
</body>
</html>
