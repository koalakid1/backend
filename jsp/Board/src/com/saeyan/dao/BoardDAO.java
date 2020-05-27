package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.ReplyVO;

import util.DBManager;

public class BoardDAO {
	//기본 생성자
	private BoardDAO(){}
	
	private static BoardDAO instance=new BoardDAO();
	
	public static BoardDAO getInstance(){
		
		return instance;
	}
	
	//board 전체 레코드를 조회
//	public List<BoardVO> selectAllBoards() {
//		String sql = "select * from board order by num desc";
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBManager.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				BoardVO bVo = new BoardVO();
//				bVo.setNum(rs.getInt("num"));
//				bVo.setName(rs.getString("name"));
//				bVo.setEmail(rs.getString("email"));
//				bVo.setPass(rs.getString("pass"));
//				bVo.setTitle(rs.getString("title"));
//				bVo.setContent(rs.getString("content"));
//				bVo.setReadcount(rs.getInt("readcount"));
//				bVo.setWritedate(rs.getTimestamp("writedate"));
//				list.add(bVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, stmt, rs);
//		}
//		return list;
//	}
	
	public List<BoardVO> selectAllBoards(int pageno){
//		String sql="select * from board order by num desc";
		System.out.println(pageno);
		String sql="select  X.*"
				+ "	from ( "
				+ "	    select rownum as rnum, A.*"
				+ "	    from ("
				+ " 	        select *"
				+ "		        from board"
				+ "		        order by num"
				+ "	        ) A"
				+ "	    where rownum <= ?) X"
				+ "	where X.rnum >= ?";
		
		List<BoardVO> list=new ArrayList<BoardVO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pageno*10);
			pstmt.setInt(2, (pageno-1)*10+1);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//VO에 먼저 저장
				BoardVO bVo=new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				//List에 추가
				list.add(bVo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
				
		return list;
	}
	
	
	//board 테이블에 게시글 등록
	public void inserBoard(BoardVO bVo){
		String sql="insert into board(num,name,email,pass,title,content) "
				+"values(board_seq.nextval,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			
			pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
	//조회수 증가
	public void updateReadCount(String num){
		String sql="update board set readcount=readcount+1 where num=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
	//글번호로 게시글 상세내용 조회하기
	public BoardVO selectOneBoardByNum(String num){
		String sql="select * from board where num=?";
		
		BoardVO bVo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bVo=new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setTitle(rs.getString("title"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readCount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return bVo; //VO를 리턴
		
	}
	
	//게시글 update
	public void updateBoard(BoardVO bVo){
		String sql="update board set name=?,email=?,pass=?,title=?,"
				+ " content=? where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			
			pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		
	}

	//게시글 삭제.delete
	public void deleteBoard(String num){
		String sql="delete from board where num=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
	
	//replay 전체 레코드를 조회
	public List<ReplyVO> selectAllReplys(int pNum) {
			String sql = "select * from reply where pNum=? order by no";
			List<ReplyVO> list = new ArrayList<ReplyVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pNum);				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ReplyVO rVo = new ReplyVO();
					rVo.setNo(rs.getInt("no"));
					rVo.setpNum(rs.getInt("pNum"));
					rVo.setName(rs.getString("name"));
					rVo.setPassword(rs.getString("password"));
					rVo.setContent(rs.getString("content"));
					rVo.setWritedate(rs.getTimestamp("writedate"));
					list.add(rVo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
	
	// 댓글 등록.insert
	public void insertReply(ReplyVO rVo){
		String sql="insert into reply(no,pNum,name,password,content) "
				+"values(reply_seq.nextval,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);			
			
			pstmt.setInt(1, rVo.getpNum());
			pstmt.setString(2, rVo.getName());
			pstmt.setString(3, rVo.getPassword());
			pstmt.setString(4, rVo.getContent());
			
			pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
	//전체레코드 갯수 조회하기
	public int selectCountBoard(){
		String sql="select count(*) as recordCount from board";
		
		BoardVO bVo=null;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		int recordCount=0;
		try{
			conn=DBManager.getConnection();
			stmt=conn.createStatement();
			
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				recordCount=rs.getInt("recordCount");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, stmt, rs);
		}
		
		return recordCount; //VO를 리턴
		
	}
	
	
	
	
	
}
