package com.taketo.www.Command.ReviewBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ReviewBoardDAO;
import com.taketo.www.DTO.ReviewBoardDTO;

public class ReviewBoardViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		ReviewBoardDAO dao = ReviewBoardDAO.getReviewBoardDAO();
		ReviewBoardDTO dto = dao.reviewBoardViewDAO(no);
		request.setAttribute("dto", dto);
		
		//프로필사진가져오기..
		String getProfile = dao.getProfileImg(request.getParameter("id"));
		request.setAttribute("getProfile", getProfile);
		String introduce = dao.getIntroduce(request.getParameter("id"));
		request.setAttribute("introduce", introduce);
		String userInteresting = dao.getUserInteresting(request.getParameter("id"));
		request.setAttribute("userInteresting", userInteresting);
		
	}

}
