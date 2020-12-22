package com.taketo.www.Command.AdminCommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ContactDAO;
import com.taketo.www.DTO.ContactDTO;

public class ContactEditOKCommnad implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactDAO dao = ContactDAO.getContactDAO();
		ContactDTO dto = new ContactDTO();
		dto.setOfficeInfoTitle(request.getParameter("officeInfoTitle"));
		dto.setOfficeInfo(request.getParameter("officeInfo"));
		dto.setContactHost(request.getParameter("contactHost"));
		dto.setContactAddress(request.getParameter("contactAddress"));
		dto.setContactEmail(request.getParameter("contactEmail"));
		dto.setContactMobile(request.getParameter("contactMobile"));
		dto.setContactTelefon(request.getParameter("contactTelefon"));
		dao.updateContactDAO(dto);
	}

}
