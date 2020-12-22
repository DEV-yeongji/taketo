package com.taketo.www.Command.AdminCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AdminDAO;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DTO.ThemeBoardDTO;
import com.taketo.www.DTO.TotalVisitedDTO;

public class AdminMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getAdminDAO();
		AllianceDAO alliance = AllianceDAO.getAllianceDAO();
		//최근게시물 가져오기
		ArrayList<ThemeBoardDTO>latest_board = dao.latestGetBoard();
		request.setAttribute("latest_board", latest_board);
		//최근방문자 가져오기
		
		ArrayList<TotalVisitedDTO>totalVisited = dao.totalVisited();

		request.setAttribute("totalVisited", totalVisited);
		
		
		int readNotice = alliance.noReadAllianceDAO();
		request.setAttribute("readNotice", readNotice);
		
	}

}
