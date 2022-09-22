<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<th>주문자</th>
					<th>배송지</th>
					<th>배송일</th>
					<th>배송 요청 사항</th>
				</tr>
				<tr>
					<td class="name">${payment[1].order_name}</td>
					<td class="address">${payment[1].del_address}</td>
					<td class="date">${payment[1].del_date}</td>
					<td class="request">${payment[1].del_message}</td>
				</tr>
				<tr>
					<th>제품</th>
					<th>이미지</th>
					<th>주문</th>
					<th>재고</th>
				</tr>
				<c:forEach var="payVo" items="${payment}">
					<tr class="record">
						<td class="product">${payVo.cus_productVO.name}</td>
						<td class="img"><img
							src="../${payVo.cus_productVO.pictureurl}"></td>
						<td class="amount">${payVo.amount}</td>
						<td class="stock">${payVo.cus_productVO.stock}</td>
					</tr>
				</c:forEach>
				
			</table>
			<input type="button" value="배송 완료" onclick="location.href='deletelist.do?delivery_num=${payment[1].delivery_num}'">
		</div>
	</main>
</body>
</html>