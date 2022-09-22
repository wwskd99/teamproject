<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 제품</title>
<link rel="stylesheet" type="text/css" href="<c:url value="" />">
</head>
<script type="text/javascript" src="<c:url value="" />"></script>	
<body>
<h1>추천 제품</h1>
 	<c:forEach begin="0" end= "4" var="pop" items="${recomList}">
			<tr class="record">
				<td>${recom.popword}</td>
				<td>${recom.count}</td>
				<br>
			</tr>
	</c:forEach>
</body>
</html>