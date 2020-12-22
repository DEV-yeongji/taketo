package com.taketo.www.Command.ReviewBoard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ReviewBoardDAO;
import com.taketo.www.DTO.ReviewBoardDTO;

public class ReviewBoardModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path= request.getSession().getServletContext().getRealPath("img/reviewBoard");
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
		ReviewBoardDAO dao = ReviewBoardDAO.getReviewBoardDAO();
		ReviewBoardDTO dto = new ReviewBoardDTO();
		if(img != null) 
			dto.setImg(img);
		else
			dto.setImg(multi.getParameter("beforeImg"));
		dto.setContents(multi.getParameter("contents"));
		dto.setTitle(multi.getParameter("title"));
		dto.setNo(Integer.parseInt(multi.getParameter("no")));

		dao.reviewBoardModifyOKDAO(dto);
	}

}
