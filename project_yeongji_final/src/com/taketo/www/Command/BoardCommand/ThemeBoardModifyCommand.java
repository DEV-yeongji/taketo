package com.taketo.www.Command.BoardCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ThemeBoardModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		ThemeBoardDTO dto = dao.themeBoardModifyDAO(no);
		request.setAttribute("dto", dto);
	}

}
