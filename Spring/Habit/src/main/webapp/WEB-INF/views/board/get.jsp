<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<script></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<meta property="og:url" content="https://www.daum.net/">
			<td><c:out value="${board.b_num}"/></td>
			
			<td><c:out value="${board.b_title}"/></td>
			<td><c:out value="${board.m_num}"/></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.b_writedate}"/></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.b_updatedate}"/></td>
			<td><c:out value="${board.b_content}"/></td>
			<td><c:out value="${board.b_category}"/></td>
			<td><c:out value="${board.b_readcount}"/></td>
</body>
</html>