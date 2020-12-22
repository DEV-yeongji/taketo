package com.taketo.www.Command.LocalBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CourseBoardDAO;
import com.taketo.www.DTO.CourseBoardDTO;

public class LocalBoardModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		CourseBoardDAO dao = CourseBoardDAO.getCourseBoardDAO();
		CourseBoardDTO dto = dao.getCourseBoardDTO(no);
		request.setAttribute("dto", dto);

	}

}
