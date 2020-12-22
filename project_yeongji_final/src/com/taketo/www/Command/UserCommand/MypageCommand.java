package com.taketo.www.Command.UserCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.UserDTO;

public class MypageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		UserDAO dao = UserDAO.getUserDAO();
		UserDTO dto = dao.mypageDAO(id);
		request.setAttribute("dto", dto);
		
	}

}
