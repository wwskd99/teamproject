<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
function check(frm){

	if (frm.lens.disabled == true){
			frm.lens.disabled = false
	} else {
			frm.lens.disabled = true
	}
}
</script>
<meta charset="UTF-8">
<title>상품 등록</title>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/shopping.css" />">
<script type="text/javascript" src="<c:url value="/js/product.js" />"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
</head>
<body>
<main>
	<div id="wrap" align="center">
		<h1>상품 등록 - 관리자 페이지</h1>
		<!-- action이 생략됨 : 폼을 요청한 url을 default로 사용 한다. /productList.do -->
		<!-- action="productList.do" -->
		<form method="post" enctype="multipart/form-data" name="frm" action="write.do">
			<table>
				<tr>
					<th>상 품 명</th>
					<td><input type="text" name="name" size="80"></td>
				</tr>
				<tr>
					<th>가 격</th>
					<td><input type="text" name="price"> 원</td>
				</tr>
				<tr>
					<th>카테고리 id</th>
					<td><input type="text" name="category_id" size="80"></td>
				</tr>
				<tr>
					<th>카테고리 name</th>
					<td><input type="text" name="category_name" size="80"></td>
				</tr>
				<tr>
					<th>재 고</th>
					<td><input type="text" name="stock" size="40">개</td>
				</tr>
				<tr>
					<th>사 진</th>
					<td><input type="file" name="pictureUrl"><br>
						(주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
					</tr>
				<tr>
					<th>설 명</th>
					<td><textarea cols="80" rows="10" name="description"></textarea></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="등록" onclick="return productCheck()">
			<input type="reset" value="다시작성">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</form>
	</div>
</main>
</body>
</html>

