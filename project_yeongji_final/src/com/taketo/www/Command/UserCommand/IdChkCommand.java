package com.taketo.www.Command.UserCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;

public class IdChkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		UserDAO dao = UserDAO.getUserDAO();
		
		boolean flag = dao.chkIdEffectDAO(id);
		request.setAttribute("flag", flag);
		request.setAttribute("chk", id);
	}

}
