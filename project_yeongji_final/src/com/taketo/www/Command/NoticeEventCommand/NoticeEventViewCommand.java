package com.taketo.www.Command.NoticeEventCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.NoticeDAO;
import com.taketo.www.DTO.NoticeDTO;

public class NoticeEventViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		NoticeDAO dao = NoticeDAO.getNoticeDAO();
		NoticeDTO dto = dao.noticeBoardViewDAO(no);
		request.setAttribute("dto", dto);
	}

}
