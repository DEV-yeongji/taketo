package com.taketo.www.Command.Alliance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;

public class AllianceBannerDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		AllianceDAO dao = AllianceDAO.getAllianceDAO();
		dao.allianceBannerDeletdDAO(no);
		
	}

}
