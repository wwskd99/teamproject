<%@page import="java.util.ArrayList"%>
<%@page import="product.dto.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>카트</title>
<style>
img {
	width: 200px;
	height: 200px;
}
</style>
<script></script>
<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>

<script type="text/javascript" src="../js/product.js"></script>

<script>
function deleteProduct() {
	var chk_obj = document.getElementsByName("rowCheck");    
	var chk_leng = chk_obj.length;    
	var checked = 0;    
	for (i=0; i < chk_leng; i++) {        
		if (chk_obj[i].checked == true) {            
			checked += 1;       
		}    
	}
	if(checked>0){
		document.db.action = "cus_delete.do";
		document.db.submit();
	} else if(checked<1){
		alert("한 개이상의 항목을 선택해주세요.");
	}
}
function buyProduct() {
	var chk_obj = document.getElementsByName("rowCheck");    
	var chk_leng = chk_obj.length;    
	var checked = 0;    
	for (i=0; i < chk_leng; i++) {        
		if (chk_obj[i].checked == true) {            
			checked += 1;       
		}    
	}
	if(checked>0){
		document.db.action = "buy.do" ;
		document.db.submit();
	} else if(checked<1){
		alert("한 개이상의 항목을 선택해주세요.");
	}
	
}
</script>


</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>장바구니</h1>


		<form method="get" name="db">
			<table class="list">
				<tr>
					<td><input type="checkbox" name="allCheck" onClick="allCheck()"></td>
					<td>이름</td>
					<td>상품</td>
					<td>가격</td>
					<td>수량</td>
					<c:forEach var="ca" items="${cart}">
						<tr id=cartline>
							<td><input type="checkbox" name="rowCheck" value="${ca.cart_id}"></td>
							<td>${ca.name}</td>
							<td><img src="../${ca.pictureurl}"></td>
							<td>${ca.price}</td>
							<td>${ca.amount}</td>
							<td><input type="button" value="삭제" onClick="deleteProduct()"></td>
						</tr>
						<tr>
					</c:forEach>
					<td><input type="button" value="주문하기" onClick="buyProduct()" ></td>
			</table>
		</form>
	</div>
	</main>
</body>
</html>