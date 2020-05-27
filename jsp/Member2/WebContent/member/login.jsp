<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="script/member.js"></script>
</head>
<body>
	<form method="post" name="frm" action="login.do">
		<input type="text" placeholder="id" name="userid" value="${userid}" id="userid"><br>
		<input type="password" placeholder="pwd" name="pwd" id="userpwd"><br>
		<input type="submit" value="로그인" onclick="return loginCheck()">
		<input type="reset" value="취소">
		<input type="button" value="회원가입" onclick="location.href='join.do'">
	</form>
	<div>${message}</div>
</body>
</html>