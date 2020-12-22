package com.taketo.www.Command.UserCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;


public class LostIDCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		UserDAO dao = UserDAO.getUserDAO();
		String id = dao.getUserIDDAO(name,email);
		request.setAttribute("id", id);
		
	}

}
