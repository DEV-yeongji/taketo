package com.taketo.www.Command.AdminCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AdminDAO;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class AdminThemeBoardSettingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getAdminDAO();
		ThemeBoardDAO tbDAO = ThemeBoardDAO.getThemeBoardDAO();
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int maxPage = dao.getMaxPage();
		if(maxPage < 0) {
			maxPage =0;
		}
		ArrayList<ThemeBoardDTO> list = tbDAO.themeBoardListDAO(curPage);
		request.setAttribute("list", list);
		request.setAttribute("maxPage", maxPage);

	}

}
