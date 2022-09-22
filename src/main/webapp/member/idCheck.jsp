<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
	<h2>아이디 중복확인</h2>
	<form action="idCheck.do" method="get" name="frm">
		아이디 <input type="text" name="userid" value="${userid}"> <input
			type="submit" value="중복 체크"> <br>
		<!-- 아이디가 중복된 경우  -->
		<c:if test="${result == 1}">
			<script type="text/javascript">
				opener.document.frm.userid.value = ""; // opener : 부모창(joinForm.jsp)
			</script>
			${userid}는 이미 사용 중인 아이디입니다.
		</c:if>
		<!-- 사용 가능한 아이디인 경우 -->
		<c:if test="${result==-1}">
		${userid}는 사용 가능한 아이디입니다.
		<input type="button" value="사용" class="cancel"
				onclick="idok('${userid}')">
		</c:if>
	</form>
</body>
</html>
