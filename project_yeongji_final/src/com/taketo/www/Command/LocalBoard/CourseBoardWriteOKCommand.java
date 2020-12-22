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

public class CourseBoardWriteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseBoardDTO dto = new CourseBoardDTO();
		CourseBoardDAO dao = CourseBoardDAO.getCourseBoardDAO();
		
		String path= request.getSession().getServletContext().getRealPath("img/localBoard");
		int size =1024*1024*20;
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
//				String file1 = (String)files.nextElement();
//				location1_img = multi.getFilesystemName(file1);
//				String file2 = (String)files.nextElement();
//				location2_img = multi.getFilesystemName(file2);
//				String file3 = (String)files.nextElement();
//				location3_img = multi.getFilesystemName(file3);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		dto.setTitle(multi.getParameter("title"));
		dto.setId(multi.getParameter("id"));
		dto.setNickName(multi.getParameter("nickName"));
		dto.setPlace(multi.getParameter("place"));
		dto.setCity(multi.getParameter("city"));
		dto.setLocation_contents(multi.getParameter("location_contents"));
		dto.setLocation_Name(multi.getParameter("location_Name"));
		dto.setWhyRecom(multi.getParameter("whyRecom"));
		
		dto.setLocation_img1(location_img1);
		dto.setLocation_img2(location_img2);
		dto.setLocation_img3(location_img3);
		dto.setLocation_img4(location_img4);
		dto.setLocation_address(multi.getParameter("location_address"));
		
		dao.courseBoardWriteOKDAO(dto);
		
	}

}
