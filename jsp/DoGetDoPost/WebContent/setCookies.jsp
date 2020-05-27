<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Cookie c = new Cookie("id","pinksung");
c.setMaxAge(365*24*60*60);
response.addCookie(c);

//memoery쿠키. 브라우저가 종료되면 소멸
response.addCookie(new Cookie("age","20"));
%>
</body>
</html>