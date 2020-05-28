<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>댓글 수정</title>
        <link rel="stylesheet" href="css/shopping.css">
		<script src="script/board.js"></script>
		<script>
			console.log("되냐?");
		</script>
	</head>
    <body>
    	<div align="center">
    		<h1>댓글 수정</h1>
    		<form name="frm" action="BoardServlet" method="post">
    		<input type="hidden" name="pNum" value="${reply.pNum}">
    		<input type="hidden" name="command" value="reply_update">
    			<table style="width:80%">
    				<tr>
    					<td>
    						<input type="text" name="name" placeholder="name" value="${reply.name}">
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="password" name="password" placeholder="password" value="${reply.password}">
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<textarea name="content" rows="5" cols="100" placeholder="content" >${reply.content}</textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="button" value="수정" onclick="return replyCheck('update')">
    						<input type="button" value="삭제" onclick="return replyCheck('delete')">
    					</td>
    				</tr>    			
    			</table>


    		</form>
    	</div>


    </body>
</html>