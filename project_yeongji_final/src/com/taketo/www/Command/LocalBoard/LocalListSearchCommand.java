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

public class LocalListSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		
		LocalListDAO dao = LocalListDAO.getLocalListDAO();
		ArrayList<LocalListDTO> searchLocalList = dao.selectLocalDAO(city);
		request.setAttribute("searchLocalList", searchLocalList);		//겟방식으로 받은 도시에 매칭하는값 받아오기
		
		ArrayList<LocalListDTO> localCity = dao.localListForCityDAO();
		request.setAttribute("city", localCity);						//도시목록들
			
		CourseBoardDAO cBoard = CourseBoardDAO.getCourseBoardDAO();
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int cnt = cBoard.getSearchCnt(city);
		int maxPage = cBoard.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		
		
		ArrayList<CourseBoardDTO> cBoardList = cBoard.courseListSearch(city,curPage);
		request.setAttribute("cBoardList", cBoardList);
		request.setAttribute("maxPage", maxPage);
		//게시글가져오기 메소드 추가해야함.
		
		AllianceDAO allianceDAO = AllianceDAO.getAllianceDAO();
		ArrayList<AllianceBannerDTO> bannerDTO = allianceDAO.allianceBanneList();
		request.setAttribute("bannerDTO", bannerDTO);
	}

}
