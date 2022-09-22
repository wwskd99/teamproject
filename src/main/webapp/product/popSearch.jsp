<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색 순위</title>
<link rel="stylesheet" type="text/css" href="<c:url value="" />">
</head>
<script type="text/javascript" src="<c:url value="" />"></script>	
<body>
<main>
	<h1>인기검색어</h1>
 	<c:forEach begin="0" end= "4" var="pop" items="${popList}">
			<tr class="record">
				<td>${pop.popword}</td>
				<td>${pop.count}</td>
				<br>
			</tr>
		</c:forEach>
	<h2>추천 제품</h2>
 	<c:forEach begin="0" end= "4" var="recomm" items="${recommList}">
			<tr class="record">
				<td>${recomm.category_name}</td>
				<td>${recomm.name}</td>
				<td>${recomm.price}</td>
				<td>${recomm.stock}</td>
				<br>
			</tr>
	</c:forEach>
</main>
</body>
</html>