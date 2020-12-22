package com.taketo.www.Command.Alliance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DTO.AllianceDTO;

public class AllianceOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AllianceDTO dto = new AllianceDTO();
		AllianceDAO dao = AllianceDAO.getAllianceDAO();
		dto.setStoreName(request.getParameter("storeName"));
		dto.setLocal(request.getParameter("local"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setAskForm(request.getParameter("askForm"));
		dto.setEmail(request.getParameter("email"));
		
		dao.AllianceOK(dto);
		
	}

}
