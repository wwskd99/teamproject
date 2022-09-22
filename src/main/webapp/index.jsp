<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈 페이지</title>
</head>
<body>
<!--  방법1 
	<a href="login.do"> 메인으로 이동 </a> -->
<!--  방법2 -->	
<%
	response.sendRedirect("common/main.do");
%>
</body>
</html>
