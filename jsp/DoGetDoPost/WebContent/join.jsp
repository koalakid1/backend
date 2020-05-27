<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="ParamServlet">
		아이디 : <input type="text" name="id"> <br>
		나 &nbsp; 이 : <input type="text" name="age"> <br>
		favorites <input type="checkbox" name="item" value="신발"> 신발
		<input type="checkbox" name="item" value="가방"> 가방
		<input type="checkbox" name="item" value="벨트"> 벨트
		<input type="submit" value="전송">
	</form>
</body>
</html>