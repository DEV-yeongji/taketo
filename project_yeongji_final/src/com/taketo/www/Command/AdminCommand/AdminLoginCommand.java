package com.taketo.www.Command.AdminCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AdminDAO;
import com.taketo.www.DTO.AdminDTO;

public class AdminLoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean flag = false;
		AdminDAO dao = AdminDAO.getAdminDAO();
		AdminDTO dto = new AdminDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		flag  = dao.getAdminSession(dto);
		
		if(flag) {
			HttpSession session = request.getSession();
			session.setAttribute("id",dto.getId());
		}
		
	}

}
