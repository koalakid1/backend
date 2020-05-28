<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP</title>
        <link rel="stylesheet" type="text/css" href="css/shopping.css">
		<script src="script/board.js"></script>

    </head>
    <body>
    	<div id="wrap" align="center">
    		<h1>게시글 상세 보기</h1>
    		<table>
    			<tr>
    				<th>작성자</th>
    				<td>${board.name}</td>
    				<th>이메일</th>
    				<td>${board.email}</td>
    			</tr>
    			<tr>
    				<th>작성일</th>
    				<td><fmt:formatDate value="${board.writedate}"/></td>
    				<th>조회수</th>
    				<td>${board.readcount}</td>
    			</tr>
    			<tr>
    				<th>제목</th>
    				<td colspan="3">${board.title}</td>
    			</tr>
    			<tr>
    				<th>내용</th>
    				<td colspan="3"><pre>${board.content}</pre></td>
    			</tr>
    		</table>
    		<br><br>
    		<input type="button" value="수정" onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}','update')">
    		<input type="button" value="삭제" onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}','delete')">
    		<input type="button" value="목록" onclick="location.href='BoardServlet?command=board_list'">
    		<input type="button" value="등록" onclick="location.href='BoardServlet?command=board_write_form'">

    		<br><br>
    		<!-- 댓글목록 ---------------------------------------->
    		<table class="list">    			
    			<c:forEach var="reply" items="${replyList}">
    			<tr class="record" >    	
    				<td style="border:none;">
    					${reply.name}<br>
    					<fmt:formatDate value="${reply.writedate}"/><br><br>
    					<pre>${reply.content}</pre>    					
    				</td> 
    				<td width="20%" style="border:none;">
    					<input type="button" value="수정" onclick="open_win2(${reply.no})">
    				</td>   				
    			</tr>
    			<tr>
    				<td style="border:none;" colspan="2">
    					<hr>
    				</td>
    			</tr>
    			</c:forEach>
    		</table>    		
<!--     		댓글목록.끝. -------------------------------------- -->


<!--     		댓글 입력 폼----------------------------------------- -->
    		<form name="frm" action="BoardServlet" method="post">
    		<input type="hidden" name="pNum" value="${board.num}">
    		<input type="hidden" name="command" value="reply_write">
    			<table>
    				<tr>
    					<td>
    						<input type="text" name="name" placeholder="name">
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="password" name="password" placeholder="password">
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<textarea name="content" rows="5" cols="100" placeholder="content"></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="submit" value="댓글등록" onclick="return replyCheck()">
    					</td>
    				</tr>    			
    			</table>


    		</form>


    		<!-- 댓글 입력 폼.끝.---------------------------------------->
    	</div>


    </body>
</html>