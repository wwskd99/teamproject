<%@page import="member.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
img {
	width: 200px;
	height: 200px;
}
</style>
<script>

function amountPlus(){
	if (cb.amount.value >= ${product.stock}){
		cb.amount.value = ${product.stock}
	} else {
		cb.amount.value++;
	}
	
}

function amountMinus(){
	if (cb.amount.value <= 1){
		cb.amount.value = 0;	
	} else {
		cb.amount.value--;
	}
	
}

function cartCheck(){ 
	
	var uid = '<%= (MemberVO)session.getAttribute("loginUser") %>';

    if( uid == "null"){ 
    	location.replace("../member/login.do")
    }
    else{
    	 if(reduSubmitCheck()){ return; }
    	document.cb.action = "cart.do";
    	document.cb.submit();

	}
}

function nowBuyCheck(){ 
	
	var uid = '<%= (MemberVO)session.getAttribute("loginUser") %>';

    if( uid == "null"){ 
    	location.replace("../member/login.do")
    }
    else{
    	
    	document.cb.action = "buy.do";
    	document.cb.submit();
	}
}
var reduSubmit = false;
    function reduSubmitCheck(){
        if(reduSubmit){
            return reduSubmit;
        }else{
            doubleSubmit = true;
            return false;
        }
    }


</script>


<script type="text/javascript" src="../js/member.js"></script>
<%@ include file="../header.jsp"%>
<meta charset="UTF-8">
<title>dd</title>
</head>
<body>
<main>

<center>
	<div>
		<table>
			<tr>
				<th>이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>상품설명</th>
				<th>남은재고</th>
		</tr>

			<tr>
				<td><img src="../${product.pictureurl}"></td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.description}</td>
				<td>${product.stock}</td>
			</tr>


			<tr>
		</table>
	</div>
	
	<form name="cb" method = "post">
		<input type="text" name="amount" value="0"> 
		<input type="button" value="+1" onClick="amountPlus()">
		<input type="button" value="-1" onClick="amountMinus()">
		
		<input type="hidden" name="code" value="${product.code}">
		
		<input type="button" value="장바구니" onClick="cartCheck()">
		<input type="button" value="바로 구매하기" onClick="nowBuyCheck()">
	</form>

</center>
</main>
</body>
</html>