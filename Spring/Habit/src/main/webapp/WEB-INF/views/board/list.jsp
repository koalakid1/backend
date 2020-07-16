<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table">
	<thead>
		<tr>
			<th>#번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>내용</th>
			<th>카테고리</th>
			<th>조회수</th>
		</tr>
	</thead>
	
	<c:forEach items="${list}" var="board">
		
		<tr>
			<td><c:out value="${board.b_num}"/></td>
			
			<td><a href='/controller/board/get?b_num=<c:out value="${board.b_num}"/>'><c:out value="${board.b_title}"/></a></td>
			<td><c:out value="${board.m_num}"/></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.b_writedate}"/></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.b_updatedate}"/></td>
			<td><c:out value="${board.b_content}"/></td>
			<td><c:out value="${board.b_category}"/></td>
			<td><c:out value="${board.b_readcount}"/></td>
		</tr>
	</c:forEach>
	
	
	
</table>
</body>
</html>