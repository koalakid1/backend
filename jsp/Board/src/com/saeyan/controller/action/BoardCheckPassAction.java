package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardCheckPassAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url=null;
		
		//湲�踰덊샇�� 鍮꾨�踰덊샇瑜� 諛쏆쓬
		String num=request.getParameter("num");
		String pass=request.getParameter("pass");
		
		//湲�踰덊샇濡� 湲��긽�꽭 �궡�슜 議고쉶
		BoardDAO bDao=BoardDAO.getInstance();
		BoardVO bVo=bDao.selectOneBoardByNum(num);
		
		//鍮꾨�踰덊샇 鍮꾧탳
		if(bVo.getPass().equals(pass)){
			//�씪移섑븯�뒗 寃쎌슦
			url="/board/checkSuccess.jsp";
		}else{
			//�씪移섑븯吏� �븡�뒗 寃쎌슦
			url="/board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		//�룷�썙�뵫
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
				
				
		
	}

}
