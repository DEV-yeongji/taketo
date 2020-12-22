package com.taketo.www.Command.BoardCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CommentDAO;
import com.taketo.www.DTO.CommentDTO;

public class CommentModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		CommentDTO dto = new CommentDTO();
		CommentDAO dao = CommentDAO.getCommentDAO();
		dto.setCommentNo(commentNo);
		dto.setContents(request.getParameter("contents"));
		dao.commentModifyDAO(dto);
		
		
	}

}
