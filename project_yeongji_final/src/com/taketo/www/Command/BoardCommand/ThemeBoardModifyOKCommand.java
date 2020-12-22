package com.taketo.www.Command.BoardCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ThemeBoardModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		ThemeBoardDTO dto = new ThemeBoardDTO();
		String hashtagArr[];
		String hashtag="";
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		dto.setInteresting(request.getParameter("interesting"));
		dto.setLocationHoliday(request.getParameter("locationHoliday"));
		dto.setLocationPay(request.getParameter("locationPay"));
		dto.setLocationTime(request.getParameter("locationTime"));
		hashtagArr = request.getParameterValues("hashTag");
		for(int i=0;i<hashtagArr.length;i++) {
			hashtag +=(hashtagArr[i]+" ");
		}
		dto.setHashTag(hashtag);
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dao.themeBoardModifyOKDAO(dto);
	}

}
