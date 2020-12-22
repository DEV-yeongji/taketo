package com.taketo.www.Command.NoticeEventCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.NoticeDAO;
import com.taketo.www.DTO.NoticeDTO;

public class NoticeEventSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		NoticeDAO dao = NoticeDAO.getNoticeDAO();
		
		int curPage = 0;
		if(request.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		int cnt = dao.getSearchCnt(subject);
		int maxPage = dao.getMaxPage(cnt);
		if(maxPage < 0) {
			maxPage =0;
		}
		
		
		ArrayList<NoticeDTO> notice = dao.noticeSearchListDAO(subject,curPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("notice", notice);
		request.setAttribute("subject", subject);
		
	}

}
