package com.taketo.www.Command.LocalBoard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.CourseBoardDAO;
import com.taketo.www.DTO.CourseBoardDTO;

public class LocaleBoardModifyOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseBoardDAO dao = CourseBoardDAO.getCourseBoardDAO();
		CourseBoardDTO dto = new CourseBoardDTO();
		
		String path= request.getSession().getServletContext().getRealPath("img/localBoard");
		int size =1024*1024*10;
		String location_img1 = "";
		String location_img2 = "";
		String location_img3 = "";
		String location_img4 = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				if(str.equals("location_img1"))
					location_img1 = multi.getFilesystemName(str);
				else if(str.equals("location_img2"))
					location_img2 = multi.getFilesystemName(str);
				else if(str.equals("location_img3"))
					location_img3 = multi.getFilesystemName(str);
				else if(str.equals("location_img4"))
					location_img4 = multi.getFilesystemName(str);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		dto.setNo(Integer.parseInt(multi.getParameter("no")));
		dto.setTitle(multi.getParameter("title"));
		dto.setLocation_contents(multi.getParameter("location_contents"));
		dto.setLocation_Name(multi.getParameter("location_Name"));
		dto.setWhyRecom(multi.getParameter("whyRecom"));
		//사진 변경안햇을시...
		String location_img4_before = multi.getParameter("location_img4_before");
		String location_img3_before = multi.getParameter("location_img3_before");
		String location_img2_before = multi.getParameter("location_img2_before");
		String location_img1_before = multi.getParameter("location_img1_before");
		if(location_img1 != null) {
			dto.setLocation_img1(location_img1);
			dto.setLocation_img2(location_img2);
			dto.setLocation_img3(location_img3);
			dto.setLocation_img4(location_img4);
		}else {
			dto.setLocation_img1(location_img1_before);
			dto.setLocation_img2(location_img2_before);
			dto.setLocation_img3(location_img3_before);
			dto.setLocation_img4(location_img4_before);
		}
		dto.setLocation_address(multi.getParameter("location_address"));
		
		dao.courseBoardModifyOKDAO(dto);
	}

}
