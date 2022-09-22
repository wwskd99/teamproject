<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>검색창</title>
</head>
<body>

<main>
	<div class="search">
		<form method="get" name="search" action="../product/search.do">
				<table style="text-align:right">
				<tr>
					<td><input type="search" class="form-control"
						placeholder="검색어 입력(특수문자 제외)" name="search" maxlength="100"></td>
					<td><button type="submit" class="btn btn-success">검색</button></td>
				</tr>
			</table>
		</form>
	</div>
	</main>
</body>
</html>