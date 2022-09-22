<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../header.jsp"%>
<title>상품 목록</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/shopping.css" />">
</head>
<body>
	<main>
		<div id="wrap" align="center">
			<h1>상품 리스트</h1>
			<table class="list">
				<c:if test="${loginUser.grade eq '0'}">
					<tr>
						<td colspan="5" style="border: white; text-align: right"><a
							href="write.do">상품 등록</a></td>
					</tr>
				</c:if>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>가격</th>
					<th>재고</th>
					<th>카테고리 id</th>
					<th>카테고리 이름</th>
					<c:if test="${loginUser.grade eq '0'}">
						<th>수정</th>
						<th>삭제</th>
					</c:if>
				</tr>
				<c:choose>
					<c:when test="${empty productList}">
				검색 결과가 없습니다.
			 </c:when>
					<c:otherwise>
						<c:forEach var="product" items="${productList}">
							<tr class="record">
								<td>${product.code}</td>
								<td>${product.name}</td>
								<td>${product.price}원</td>
								<td>${product.stock}개</td>
								<td>${product.category_id}</td>
								<td>${product.category_name}</td>
								<c:if test="${loginUser.grade eq '0'}">
									<td><a href="update.do?code=${product.code}">상품 수정</a></td>
									<td><a href="delete.do?code=${product.code}">상품 삭제</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</main>
</body>
</html>