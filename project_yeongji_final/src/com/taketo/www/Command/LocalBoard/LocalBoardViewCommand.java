package com.taketo.www.Command.LocalBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CourseBoardDAO;
import com.taketo.www.DTO.CourseBoardDTO;

public class LocalBoardViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		CourseBoardDAO dao = CourseBoardDAO.getCourseBoardDAO();
		CourseBoardDTO dto = dao.courseBoardViewDAO(no);
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
