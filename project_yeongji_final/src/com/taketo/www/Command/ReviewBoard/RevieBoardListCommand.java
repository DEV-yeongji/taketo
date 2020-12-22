package com.taketo.www.Command.ReviewBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ReviewBoardDAO;
import com.taketo.www.DTO.ReviewBoardDTO;

public class RevieBoardListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewBoardDAO dao = ReviewBoardDAO.getReviewBoardDAO();
		//페이징처리해줘야함..
		//최대페이지크기랑 커렌트페이지 필요함.
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int maxPage = dao.getMaxPage();
		if(maxPage < 0) {
			maxPage =0;
		}
		request.setAttribute("maxPage", maxPage);
		ArrayList<ReviewBoardDTO> list = dao.reviewBoardListDAO(curPage);
		request.setAttribute("list", list);
		 
	}

}
