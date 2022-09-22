<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>" %>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<style>
img {
	width: 200px;
	height: 200px;
}
</style>
<script>
IMP.init('imp12536676');
function requestPay() {
	
	 IMP.request_pay({
		 pg:"kakaopay.TC0ONETIME",
		//pg: 'html5_inicis.INIpayTest',
		 pay_method: 'card',
		 merchant_uid: '123',
		name: 'book',
		amount: 1001, 
		buyer_email: 'iamport@siot.do',          
		 buyer_name: '구매자이름',          
		 buyer_tel: '010-1234-5678',  
	 }, function (rsp){
		 if (rsp.success) {
			 var msg = '결제가 완료되었습니다.';   
			 msg += '고유ID : ' + rsp.imp_uid;      
			 msg += '상점 거래ID : ' + rsp.merchant_uid;       
			 msg += '결제 금액 : ' + rsp.paid_amount;         
			 msg += '카드 승인번호 : ' + rsp.apply_num;
			 
			 document.info.submit();
			 
	 }else {
		 var msg = '결제에 실패하였습니다.';          
		 msg += '에러내용 : ' + rsp.error_msg;
		 alert(msg);
		 
		 document.info.submit();
	 }
	 });
}
		 

		
		
		

	
</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>구매페이지</h1>

		<table class="list">
			<tr>
				<td>이름</td>
				<td>상품</td>
				<td>가격</td>
				<td>수량</td>
			</tr>
			<c:forEach var="bu" items="${buy}">
				<tr>
					<td>${bu.name}</td>
					<td><img src="../${bu.pictureurl}"></td>
					<td>${bu.price}</td>
					<td>${bu.amount}</td>
				</tr>
			</c:forEach>
		</table>
		<form method="post" action="success.do" name="info" accept-charset="utf-8">
			<c:forEach var="bu" items="${buy}">
				<input type="hidden" name="cart_id" value="${bu.cart_id}">
			</c:forEach>
		
			<table>
				<tr>
					<th>주문자정보</th>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="order_name"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="order_email"></td>
				</tr>
				<tr>
					<th>휴대폰 번호</th>
					<td><input type="tel" name="order_phone"></td>
				</tr>


				<tr>
					<th>배송지 정보</th>
				</tr>

				<tr>
					<th>수령인 이름</th>
					<td><input type="text" name="del_name"></td>
				</tr>
				<tr>
					<th>휴대폰 번호</th>
					<td><input type="tel" name="del_phone"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="del_address"></td>
				</tr>
				<tr>
					<th>배송 요청 사항</th>
					<td><input type="text" name="del_message"></td>
				</tr>
				<tr>
					<th>배송일 선택</th>
					<td><input type="date" name="del_date"></td>
				</tr>



				<tr>
					<th>배송지 정보</th>
				</tr>
				<tr>
					<th>결제 수단 선택</th>
					<td><input type="radio" name="paymethod" value="card">카드
						<input type="radio" name="paymethod" value="account">가상계좌
						<input type="radio" name="paymethod" value="naverpay">카카오페이</td>
				</tr>


				<tr>
					<th>결제 예정 금액</th>
					<td>XX</td>
				</tr>
				<tr>
					<th>주문 금액</th>
					<td>XX</td>
				</tr>
				<tr>
					<th>제품 금액</th>
					<td>XX</td>
				</tr>
				<tr>
					<th>구매 수량</th>
					<td>XX</td>
				</tr>

				<tr>
					<th>[필수] 주문할 제품의 거래조건을 확인 하였으며, 구매에 동의하시겠습니까?(전자상거래법 제8조 제2항)
					</th>
				<tr>
					<td><input type="button" value="결제하기" onclick="requestPay()"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>