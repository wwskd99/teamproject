<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
#pro {
	width: 200px;
	height: 200px;
}

#camera_top{
width:100%;
height: 500px;


}

#camera_top_img{
width: 105%;
height: 500px;
display:block;
vertical-align: top;
margin-top : -8px;
margin-left : -8px;
margin-right: 16px;
}

#camera_text{
position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
color : white;
font-size : 50px;
font-weight : bold;

}
#camera_all{
float : left;
 margin: auto;
  width: 200px;
  height : 100px;
}
#camera_ren{
float : left;
  margin: auto;
  width: 200px;
  height : 100px;

}
#camera_com{
float : left;
 margin: auto;
width: 200px;
height: 100px;

}

</style>


<%@ include file="../header.jsp"%>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>

<body>
<main>
<div id = camera_top>
	<div id = camera_text>카메라</div>
	<img id = camera_top_img src="../images/bg_cat_camera.jpg">
	<div id = top_center>
	<div id = camera_all>전체보기</div>
	<div id = camera_ren><img src = "../images/m-category-cam-01.png">렌즈교환식 카메라</div>
	<div id = camera_com><img src = "../images/m-category-cam-02.png">컴팩트 카메라</div>

</div>
	<div id="list_wrap" align="center">
		<div class="product_top">
			<h1>제품</h1>
			<div class="product_top_sort">
				<button class="btn-sort" id="SortPriceHigh" onClick="SortPriceHigh()">높은가격순</button>
				<button class="btn-sort" id="SortPriceLow">낮은가격순</button>
				<button class="btn-sort" id="SortPriceNew">최신순</button>
			</div>
		</div>

		<div class="product_body">
			<table class="list">
				<c:forEach var="pro" items="${productList}">
					
					<tr>
						<td><a href="cus_productview.do?code=${pro.code}"><img id="pro" src="../${pro.pictureurl}"></a></td>
						<td>${pro.name}</td>
						<td>${pro.description}</td>
						<td>${pro.price}</td>

					</tr>
					
				</c:forEach>
			</table>

		</div>

	</div>
	</main>
</body>
</html>