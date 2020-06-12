<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%!//선언부는 첫 방문자에 의해서 단 한번 수행합니다.

	Connection conn = null;

	PreparedStatement stmt = null;

	ResultSet rs = null;

	String url = "jdbc:oracle:thin:@localhost:1521:myoracle";

	String uid = "ora_user";

	String pass = "hong";

	String search;

	String sql;

	

	%>



		<%

			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");

				conn = DriverManager.getConnection(url, uid, pass);

				

				//Post방식으로 넘어오는 패러미터값을 받음.

				String userid=request.getParameter("id");

				String userpw=request.getParameter("pw");

				

				sql= "select userid, name, pwd from member where userid=? and pwd=?";

				stmt = conn.prepareStatement(sql);

				stmt.setString(1, userid);

				stmt.setString(2, userpw);

				rs = stmt.executeQuery();

				String strJson="";

				strJson="{\"result\": [";

			

				if(rs.next()) {	

					strJson+="{\"msg\":\"ok\"}";

				}else{

					strJson+="{\"msg\":\"no\"}";

				}

				//strJson=strJson.substring(0,strJson.length()-1);//마지막 쉼표 제거

				strJson+="]}";	

				out.println(strJson);

			} catch (Exception e) {

				out.println(e.getMessage());

				//e.printStackTrace();

			} finally {

				try {

					if (rs != null)

						rs.close();

					if (stmt != null)

						stmt.close();

					if (conn != null)

						conn.close();

				} catch (Exception e) {

					e.printStackTrace();

				}

			}//finally의 끝

		%>