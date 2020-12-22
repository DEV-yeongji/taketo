package com.taketo.www.Command.AdminCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CourseBoardDAO;
import com.taketo.www.DTO.CourseBoardDTO;

public class AdminLocalBoardSettingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseBoardDAO dao = CourseBoardDAO.getCourseBoardDAO();
		
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int cnt = dao.getCnt();
		int maxPage = dao.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		ArrayList<CourseBoardDTO> cBoardList = dao.courseListDAO(curPage);
		request.setAttribute("list", cBoardList);
		request.setAttribute("maxPage", maxPage);

	}

}
