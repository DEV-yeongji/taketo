package com.taketo.www.Command.AdminCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ContactDAO;
import com.taketo.www.DTO.ContactDTO;

public class ContactTermUSerUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactDAO dao = ContactDAO.getContactDAO();
		ContactDTO dto = new ContactDTO();
		dto.setPersonalInfo(request.getParameter("personalInfo"));
		dto.setTermsOfUse(request.getParameter("termsOfUse"));
		dao.updateTermUserForm(dto);
		
	}

}
