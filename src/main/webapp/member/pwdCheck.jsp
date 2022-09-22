<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
	String userid = request.getParameter("userid");
	String pwd = (String)request.getAttribute("pwd");
	String message = (String)request.getAttribute("message");

	String result = "{\"userid\":\"" + userid + 
					"\", \"name\":\"" + name + 
					"\", \"message\":\"" + message + 
					"\", \"pwd\":\"" + pwd + "\"}";
	out.println(result);
%>
