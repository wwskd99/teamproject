<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty loginUser}">
	<jsp:forward page="login.do"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 전용 페이지</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
	<main>
	<c:if test="${loginUser.grade eq '0'}">
		<h2>관리자 전용 페이지</h2>
	</c:if>
	<c:if test="${loginUser.grade eq '1'}">
		<h2>회원 전용 페이지</h2>
	</c:if>
	<form action="logout.do">
		<table>
			<tr>
				<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td>
			</tr>
			<tr>
				<c:url var="uurl1" value="/member/update.do?userid=${loginUser.userid}"/>
				<c:url var="uurl2" value="/member/grant.do"/>
				<c:url var="burl" value="/notice/list.do"/>
				<c:url var="pUrl1" value="/product/list.do" />
				<c:url var="pUrl2" value="/product/cus_productlist.do"/>
				<c:url var="cUrl" value="/product/paymentlist.do"/>
				<c:url value="/product/popsearch.do" var="surl1"/>
				<td colspan="2" align="center">
					<input type="submit" value="로그아웃"> 
					&nbsp;&nbsp; 
					<input type="button" value="회원정보변경" onclick="location.href='${uurl1}'">
					&nbsp;&nbsp;             
              		<input type="button" value="검색어" onclick="location.href='${surl1}'"> <!-- 인기 검색어 -->             
             		&nbsp;&nbsp;				
					<input type="button" value="게시글 리스트" onclick="location.href='${burl}'">
					&nbsp;&nbsp;
					<c:choose>
					<c:when test="${loginUser.grade eq '0'}">	<%-- 관리자 --%>
					<input type="button" value="상품리스트" onclick="location.href='${pUrl1}'">
					&nbsp;&nbsp; 
					<input type="button" value="유저카트정보 조회" onclick="location.href='${cUrl}'">
					&nbsp;&nbsp; 
					<input type="button" value="권한 부여" onclick="location.href='${uurl2}'"> 
					</c:when>
					<c:otherwise>
					<input type="button" value="상품리스트" onclick="location.href='${pUrl2}'">
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form>
	<div class="search">
      <form method="get" action="../product/search.do" name="frm" >
            <table style="text-align:right">
            <tr>
               <td><input type="search" class="form-control"
                  placeholder="검색어 입력(특수문자 제외)" name="search" maxlength="100"></td>
               <td><button type="submit" class="btn btn-success"  onclick="return keywordCheck()">검색</button></td>
            </tr>
         </table>
      </form>
   </div>
	</main>
</body>
</html>
