package com.taketo.www.Command.BoardCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CommentDAO;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ThemeBoardListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int cnt = dao.getCnt();
		int maxPage = dao.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		ArrayList<ThemeBoardDTO> list = dao.themeBoardListDAO(curPage);
		request.setAttribute("list", list);
		request.setAttribute("maxPage", maxPage);
		
	}

}
