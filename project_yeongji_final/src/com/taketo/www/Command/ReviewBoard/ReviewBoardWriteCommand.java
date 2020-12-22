package com.taketo.www.Command.ReviewBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.UserDTO;

public class ReviewBoardWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDAO dao = UserDAO.getUserDAO();		//글쓰기위해 유저정보를가져온다
		UserDTO dto = dao.getUserDTO(id);
		
		request.setAttribute("dto", dto);

	}

}
