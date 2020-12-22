package com.taketo.www.Command.UserCommand;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.UserDTO;

public class UserModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = UserDAO.getUserDAO();
		UserDTO dto = new UserDTO();
		HttpSession session = request.getSession();
		//포인트 충전하기는 mypage.jsp에서 추후 구상예정.(추가되면 파라미터값 넘겨주기!!!!!!)
		
		String path= request.getSession().getServletContext().getRealPath("profile");
		int size =1024*1024*10; 
		String profile = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {

			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				profile = multi.getFilesystemName(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String [] interestingArr = new String[4];
		String interesting = "";
		
		
		dto.setId(multi.getParameter("id"));
		dto.setPassword(multi.getParameter("password"));
		dto.setmTelecom(multi.getParameter("mTelecom"));
		dto.setMobile(multi.getParameter("mobile"));
		dto.setCertifiCode(multi.getParameter("certifiCode"));
		dto.setEmail(multi.getParameter("email"));
		dto.setbYear(multi.getParameter("bYear"));
		dto.setbMonth(multi.getParameter("bMonth"));
		dto.setbDay(multi.getParameter("bDay"));
		dto.setPostCode(multi.getParameter("postCode"));
		dto.setAddress1(multi.getParameter("address1"));
		dto.setAddress2(multi.getParameter("address2"));
		dto.setDetailAddress(multi.getParameter("detailAddress"));
		dto.setGender(multi.getParameter("gender"));
		dto.setSnsId(multi.getParameter("snsId"));
		interestingArr = multi.getParameterValues("interesting");
		for(int i=0;i<interestingArr.length;i++) {
			interesting += (interestingArr[i]+" ");
		}
		dto.setInteresting(interesting);
		if(profile != null) 
			dto.setProfile(profile);
		else
			dto.setProfile(multi.getParameter("beforeProfile"));
		dto.setIntroduce(multi.getParameter("introduce"));
		dao.userModifyOK(dto);
		
		
	}

}
