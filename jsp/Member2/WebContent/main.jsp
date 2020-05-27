<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}">
	<jsp:forward page="login.do"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="member.js"></script>
</head>
<body>
	<form action="logout.do">
		안녕하세요? ${loginUser.name}(${loginUser.userid})님<br>
		<input type="submit" value="로그아웃">
		<input type="button" value="회원정보변경" 
		onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'"> 
	</form>
</body>
</html>