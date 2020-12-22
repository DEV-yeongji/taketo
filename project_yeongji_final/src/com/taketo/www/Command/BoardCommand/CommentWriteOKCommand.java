package com.taketo.www.Command.BoardCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CommentDAO;
import com.taketo.www.DTO.CommentDTO;

public class CommentWriteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		CommentDAO dao = CommentDAO.getCommentDAO();
		CommentDTO dto = new CommentDTO();
		dto.setNo(no);
		dto.setContents(request.getParameter("contents"));
		dto.setWriteUser(request.getParameter("writeUser"));
		dto.setWriteUserId(request.getParameter("writeUserID"));
		dao.commentWriteOK(dto);
		
	}

}
