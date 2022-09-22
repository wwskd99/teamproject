<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/shopping.css" />">
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="<c:url value="/js/product.js" />"></script>
</head>
<body>
<main>
<div id="wrap" align="center">
	<h1>상품 수정 - 관리자 페이지</h1>
	<form method="post" enctype="multipart/form-data" name="frm" action="update.do">
	<!-- hidden 사용자에게 보이지 않고 서버로 전송할 목적으로 만든다. -->
	    <input type="hidden" name="code" value="${product.code}">
	    <!-- 이미지를 변경할 경우, 기존 이미지 파일의 위치를 서버로 전송(서버에서 어떻게 ?)-->
    	<input type="hidden" name="nonmakeImg" value="${product.pictureUrl}">
	    <table>
			<tr>
				<td>
			<c:choose>
				<c:when test="${empty product.pictureUrl}">
					<img src="../upload/noimage.gif">
				</c:when>
				<c:otherwise>
					<img src="../upload/${product.pictureUrl}">
				</c:otherwise>
			</c:choose>
			<!-- 상대경로를 이용해서 파일을 저장할 경우, 이미지 파일을 보여주기 편하다.
				 단점은 tomcat에서 프로젝트를 삭제하면 파일도 삭제가 된다.
				 데이터베이스와 파일의 정보 사이에 불일치가 발생
				절대경로를 이용해서 파일을 저장할 경우는 별도로 서버에서 브라우저로
				 	다운로드 기능을 구현해 주어야 한다.
				 	tomcat에서 프로젝트를 삭제하더라도 절대경로에 저장된 파일은 유지됨
			 -->
				</td>
		    	<td>
					<table>
		    			<tr>
		    				<th style="width: 80px">상품명</th>
							<td><input type="text" name="name" value="${product.name}" size="80"></td>
			    		</tr>
			    		<tr>
			    			<th>가 격</th>
							<td><input type="text" name="price" value="${product.price}"> 원</td>
						</tr>
						<tr>
						<th>카테고리 id</th>
							<td><input type="text" name="category_id" value="${product.category_id}" size="80"></td>
						</tr>
						<th>카테고리 name</th>
							<td><input type="text" name="category_name" value="${product.category_name}" size="80"></td>
						</tr>
						<tr>
							<th>재 고</th>
							<td><input type="text" name="stock" value="${product.stock}" size="40">개</td>
						</tr>
			    		<tr>
			    			<th>사 진</th>
							<td><input type="file" name="pictureUrl"><br>
								(주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
						</tr>
			    		<tr>
							<th>설 명</th>
							<td><textarea cols="90" rows="10" name="description">${product.description}</textarea></td>
						</tr>
					</table>
		    	</td>
			</tr>
	    </table>
	    <br>
	    <input type="submit" value="수정" onclick="return productCheck()">
    	<input type="reset" value="다시작성">
    	<input type="button" value="목록" onclick="location.href='list.do'">
	</form>
</div>
</main>
</body>
</html>