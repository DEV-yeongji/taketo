package com.taketo.www.Command.Alliance;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DTO.AllianceBannerDTO;
import com.taketo.www.DTO.AllianceDTO;

public class AllianceListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AllianceDAO dao = AllianceDAO.getAllianceDAO();
		
		ArrayList<AllianceDTO> Alliancelist = dao.allianceList();
		request.setAttribute("Alliancelist", Alliancelist);
		
		int readNotice = dao.noReadAllianceDAO();
		request.setAttribute("readNotice", readNotice);
		
		ArrayList<AllianceBannerDTO> bannerDTO = dao.allianceBanneList();
		request.setAttribute("bannerDTO", bannerDTO);
		
	}

}
