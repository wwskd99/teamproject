<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="../css/shopping.css">
<title>Insert title here</title>
</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>주문 리스트</h1>
		<table class="list">
			<tr>
				<th>딜리버리코드</th>
				<th>주문자</th>
				<th>유저아이디</th>
			</tr>
			<c:forEach var="pay" items="${paymentlist}">
				<tr class="record">
					<td class="delivery"><a href="view.do?delivery_num=${pay.delivery_num}">${pay.delivery_num}</a></td>
					<td class="name">${pay.order_name}</td>
					<td class="userid">${pay.userid}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</main>
</body>
</html>