package com.taketo.www.Command.LocalBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.LocalListDAO;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.LocalListDTO;
import com.taketo.www.DTO.UserDTO;

public class LocalBoardWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDAO userDao = UserDAO.getUserDAO();		
		UserDTO userDto = userDao.getUserDTO(id); 
		request.setAttribute("dto", userDto);         //글쓰기위해 유저정보를가져온다
		
		
		LocalListDAO local = LocalListDAO.getLocalListDAO();
		ArrayList<LocalListDTO> city = local.localListForCityDAO();
		request.setAttribute("city", city);			   //도시 가져오기
		
		ArrayList<LocalListDTO> localList = local.allLocalDAO();
		request.setAttribute("localList", localList);	//시군구 가져오기
		
		
		
	}

}
