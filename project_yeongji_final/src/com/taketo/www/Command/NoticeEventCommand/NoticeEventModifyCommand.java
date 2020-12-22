package com.taketo.www.Command.NoticeEventCommand;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.NoticeDAO;
import com.taketo.www.DTO.NoticeDTO;

public class NoticeEventModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO dao = NoticeDAO.getNoticeDAO();
		NoticeDTO dto = new NoticeDTO();
		
		HttpSession session = request.getSession();
		
		String path= request.getSession().getServletContext().getRealPath("img/NoticeEventImg");
		int size =1024*1024*10; 
		String img = "";
		
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {

			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				img = multi.getFilesystemName(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		dto.setContents(multi.getParameter("contents"));
		dto.setTerm(multi.getParameter("term"));
		dto.setTitle(multi.getParameter("title"));
		if(img != null) 
			dto.setImg(img);
		else
			dto.setImg(multi.getParameter("beforeImg"));
		
		int no = Integer.parseInt(multi.getParameter("no"));
		dto.setNo(no);
		dao.noticeBoardModifyDAO(dto);
	}

}
