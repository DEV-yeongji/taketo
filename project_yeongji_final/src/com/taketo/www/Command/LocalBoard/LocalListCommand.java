package com.taketo.www.Command.LocalBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DAO.CourseBoardDAO;
import com.taketo.www.DAO.LocalListDAO;
import com.taketo.www.DTO.AllianceBannerDTO;
import com.taketo.www.DTO.CourseBoardDTO;
import com.taketo.www.DTO.LocalListDTO;

public class LocalListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalListDAO dao = LocalListDAO.getLocalListDAO();
		ArrayList<LocalListDTO> localList = dao.allLocalDAO();
		request.setAttribute("localList", localList);				//모든지역가져오기
		
		ArrayList<LocalListDTO> city = dao.localListForCityDAO();
		request.setAttribute("city", city);							//도시만 가져오기
		
		CourseBoardDAO cBoard = CourseBoardDAO.getCourseBoardDAO();
		//게시글가져오기 메소드 추가해야함.
		
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int cnt = cBoard.getCnt();
		int maxPage = cBoard.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		ArrayList<CourseBoardDTO> cBoardList = cBoard.courseListDAO(curPage);
		request.setAttribute("cBoardList", cBoardList);
		request.setAttribute("maxPage", maxPage);
		
		AllianceDAO allianceDAO = AllianceDAO.getAllianceDAO();
		ArrayList<AllianceBannerDTO> bannerDTO = allianceDAO.allianceBanneList();
		request.setAttribute("bannerDTO", bannerDTO);

	}

}
