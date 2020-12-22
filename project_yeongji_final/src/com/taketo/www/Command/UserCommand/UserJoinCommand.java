package com.taketo.www.Command.UserCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.UserDTO;

public class UserJoinCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] interestingArr = new String[4];
		String interesting = "";
		UserDAO dao = UserDAO.getUserDAO();
		UserDTO dto = new UserDTO();
		dto.setId(request.getParameter("id"));
		dto.setNickName(request.getParameter("nickName"));
		dto.setName(request.getParameter("name"));
		dto.setPassword(request.getParameter("password"));
		dto.setmTelecom(request.getParameter("mTelecom"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setCertifiCode(request.getParameter("certifiCode"));
		dto.setEmail(request.getParameter("email"));
		dto.setbYear(request.getParameter("bYear"));
		dto.setbMonth(request.getParameter("bMonth"));
		dto.setbDay(request.getParameter("bDay"));
		dto.setPostCode(request.getParameter("postCode"));
		dto.setAddress1(request.getParameter("address1"));
		dto.setAddress2(request.getParameter("address2"));
		dto.setDetailAddress(request.getParameter("detailAddress"));
		dto.setGender(request.getParameter("gender"));
		dto.setSnsId(request.getParameter("snsId"));
		interestingArr = request.getParameterValues("interesting");
		for(int i=0;i<interestingArr.length;i++) {
			interesting += (interestingArr[i]+" ");
		}
		dto.setInteresting(interesting);
		dto.setIntroduce(request.getParameter("introduce"));
		dao.createUserDAO(dto);
	}

}

