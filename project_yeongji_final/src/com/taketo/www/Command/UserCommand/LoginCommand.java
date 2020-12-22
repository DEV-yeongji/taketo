package com.taketo.www.Command.UserCommand;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.UserDTO;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = UserDAO.getUserDAO();
		UserDTO dto = new UserDTO();
		String id = request.getParameter("id");
		dto.setId(id);
		dto.setPassword(request.getParameter("password"));
		HttpSession session = request.getSession();

		boolean flag = dao.getSessionDAO(dto);
		if(flag) {
			session.setAttribute("id", dto.getId());
		}
		int point = dao.getPoint(id);
		session.setAttribute("point", point);

	}

}
