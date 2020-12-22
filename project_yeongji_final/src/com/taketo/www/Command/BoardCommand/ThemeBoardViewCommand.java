package com.taketo.www.Command.BoardCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CommentDAO;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.CommentDTO;
import com.taketo.www.DTO.ThemeBoardDTO;
import com.taketo.www.DTO.UserDTO;

public class ThemeBoardViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		ThemeBoardDTO dto = dao.themeBoardViewDAO(no);
		request.setAttribute("dto", dto);
		
		//글쓴사람 아이디를 통해 프로필 사진 및 소개말,관심항목가져오기\
		String getProfile = dao.getProfileImg(request.getParameter("id"));
		request.setAttribute("getProfile", getProfile);
		String introduce = dao.getIntroduce(request.getParameter("id"));
		request.setAttribute("introduce", introduce);
		String userInteresting = dao.getUserInteresting(request.getParameter("id"));
		request.setAttribute("userInteresting", userInteresting);
		
		//댓글쓴이 정보 가져오기.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		UserDAO userDAO = UserDAO.getUserDAO();
		UserDTO userDTO = userDAO.mypageDAO(id);
		request.setAttribute("userDTO", userDTO);
		
		//댓글창 리스트 가져오기.
		CommentDAO commentDAO = CommentDAO.getCommentDAO();
		ArrayList<CommentDTO> comment = commentDAO.commentListDAO();
		request.setAttribute("comment", comment);
		
	}

}
