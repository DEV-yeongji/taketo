package com.taketo.www.Command.BoardCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ThemeBoardSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		String search = request.getParameter("search");
		int cnt = dao.getSearchCnt(search);
		int maxPage = dao.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		ArrayList<ThemeBoardDTO>searchResult =  dao.getSearchResult(search,curPage);
		request.setAttribute("searchResult", searchResult);
		request.setAttribute("search", search);
		request.setAttribute("maxPage", maxPage);
	}

}
