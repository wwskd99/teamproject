<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>header 태그</title>
<style type="text/css">
.parent{
	height: 100px;
	width: 100%;
	margin-left: -8px;
	margin-top: -8px;
	position: fixed;			
	background-color: black;
	color: white;
	display: flex;
	justify-content : center;	
	align-items: center;
	white-space:nowrap;	
}
.logo{  
	flex: 1;
}
.menu{  
  	flex: 3;
}
.menu ul{
	height:100px;
	list-style:none;
	margin:0;
	padding:0;
}
.menu li{
	float:left;
	padding:5%;
}
.menu li a{
	display:block;
	line-height:100px;
	margin:0px;
	padding:0px 25px;
	text-align:center;
	text-decoration:none;
	color: white;
}
.menu li a:hover {
	color: #5865f5;
	text-decoration:none;
}
.menu li ul{
	color : white;
	background: gray;
	display:none;
	height:auto;
	border:0px;
	position:absolute;
	width:200px;
	z-index:200;
}
.menu li:hover ul{
	display:block
}
.menu li li{
	background: gray;
	display:block;
	float:none;
	margin:0px;
	padding:0px;
	width:200px;
}
.menu li:hover li a{
	background:none;
}
.menu li ul a{
	display:block;
	height:80px;
	margin:0px;
	padding:0px 10px 0px 15px;
	text-align:left;
}
.menu li ul a:hover, .menu li ul li:hover a{
	border:0px;
	color: #5865f5;;
	text-decoration:none;
}
.menu p{
	clear:left;
}
.usermenu{
	flex: 1;
}
.usermenu img {
	width: 50px; 
	height: 50px; 
}
main {
  padding-top: 100px;		
}
div.logo img {
	width: 100px; /* 이미지 너비(가로) */
	height: 100px; /* 이미지 높이(세로) */
}
a {
	text-decoration: none; /* 링크 밑줄 없애기 */
	color: black; /* 글 색상 */
}

</style>
</head>
<body>
<header>
<div class="parent">
<div class='logo'>
	<a href="../common/main.do">
	<img src="../images/sony.png"/>
	</a>
</div>
<div class='menu'>
<ul>
	<li><a href="../product/cus_productlist.do">제품</a>
		<ul>
			<li><a href="#">카메라</a></li>
			
			<li><a href="#">비디오 카메라</a></li>
			
			<li><a href="#">오디오</a></li>
			
			<li><a href="#">액세서리</a></li>
		</ul>
	</li>
	<li><a href="#">멤버십</a>
		<ul>
			<li><a href="#">등급&혜택 안내</a></li>
		</ul>
	</li>
	
	<li><a href="../notice/list.do">공지 사항</a>

	</li>
</ul>
</div>
<div class='usermenu'>
	<span>
		<a href="../member/login.do">
			<img src="../images/mypage.png"/>
		</a>
	</span>
	<span>
		<a href="../product/cartsession.do">
			<img src="../images/cart.png"/>
		</a>
	</span>
</div>
</div>
</header>
</body>
</html>