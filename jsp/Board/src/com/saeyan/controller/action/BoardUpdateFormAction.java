package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/board/boardUpdate.jsp";
		//글번호를 받음.
		String num=request.getParameter("num");		
		//글상세 내용 조회
		BoardDAO bDao=BoardDAO.getInstance();
		BoardVO bVo=bDao.selectOneBoardByNum(num);
		//포워딩
		request.setAttribute("board", bVo);
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
