package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.ReplyVO;

public class ReplyUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reply_no = Integer.parseInt(request.getParameter("no"));
		String url = "/board/replyUpdate.jsp";
		BoardDAO bDao = BoardDAO.getInstance();
		ReplyVO rVo = bDao.selectReply(reply_no);
		
		request.setAttribute("reply", rVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
