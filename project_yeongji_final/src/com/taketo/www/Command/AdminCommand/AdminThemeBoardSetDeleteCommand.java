package com.taketo.www.Command.AdminCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ThemeBoardDAO;

public class AdminThemeBoardSetDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		dao.deleteThemeBoardDAO(Integer.parseInt(request.getParameter("no")));
	}

}
