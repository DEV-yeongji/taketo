package com.taketo.www.Command.AdminCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AdminDAO;
import com.taketo.www.DTO.EditSiteBannerDTO;

public class AdminSiteViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getAdminDAO();
		EditSiteBannerDTO banner = dao.getBennerLatestDAO();
		request.setAttribute("banner", banner);
		
	}

}
