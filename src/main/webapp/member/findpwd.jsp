<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="../js/member.js"></script>
<title>Insert title here</title>
<script src="../js/jquery.js"></script>
<script>
	$(function() {
		var $frm = $(".password_f");
		$frm.on("submit", function(e) {
			e.preventDefault();
			var myData = $frm.serialize();

			$.ajax({
				type : "POST",
				url : $frm.attr("action"),
				data : myData,
				success : function(res) {
					if (res) {
						var jsonData = JSON.parse(res);
						
						var test = jsonData.message;
						alert(test);
						var pwd = jsonData.pwd;
						if(pwd != "null"){
							var message= "찾으신 비밀번호는 " + pwd + "입니다.";
							alert(message);
						}
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<main>
		<h2>비밀번호 찾기</h2>
		<form class=password_f method="post" action="pwdCheck.do">
			<table>
				<tr>
					<td>이 름</td>
					<td><input type="text" name="name" size="20" value="test" ></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userid" size="20" value="test"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="확인" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>

	</main>
</body>
</html>