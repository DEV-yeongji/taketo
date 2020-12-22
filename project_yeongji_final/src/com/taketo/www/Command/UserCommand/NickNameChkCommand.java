package com.taketo.www.Command.UserCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;

public class NickNameChkCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		UserDAO dao = UserDAO.getUserDAO();
		
		boolean flag = dao.chkNickNameEffectDAO(nickName);
		request.setAttribute("flag", flag);
		request.setAttribute("chk", nickName);

	}

}
