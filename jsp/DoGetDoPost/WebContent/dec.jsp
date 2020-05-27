<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
	String str = "안녕하세요!";
	int a=5, b=-5;
	public int abs(int n){
		if(n<0){
			n=-n;
		}
		return n;
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println(str+"<br>");
	out.println(a + "의 절대값 : " + abs(a) + "<br>");
	out.println(b + "의 절대값 : " + abs(b) + "<br>");
%>
<br>
<%=str %>
<br>
<%=a %>의 절대값 : <%= abs(a) %>
<br>
<%=b %>의 절대값 : <%= abs(b) %>
<br>
<%
	Calendar date = Calendar.getInstance();
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
	
%>
<%= today.format(date.getTime())%>
</body>
</html>