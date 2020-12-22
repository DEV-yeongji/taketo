package com.taketo.www.Command.Alliance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DTO.AllianceDTO;

public class AllianceSuccessCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int state;
		
		if(request.getParameter("state") !=null) {
			state = Integer.parseInt(request.getParameter("state"));
		}else {
			state = 0;
		}
		
		AllianceDAO dao = AllianceDAO.getAllianceDAO();
		AllianceDTO dto = new AllianceDTO();
		dao.allianceSuccess(no,state);
		
	}

}
