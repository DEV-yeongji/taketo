package com.taketo.www.Command.BoardCommand;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.ThemeBoardDAO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ThemeBoardWriteOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.getSession().getServletContext().getRealPath("/");
		String path= request.getSession().getServletContext().getRealPath("img");
		int size =1024*1024*20;
		String fileName1 = "";
		String fileName2 = "";
		String fileName3 = "";
		String fileName4 = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {
			
			
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				if(str.equals("fileName1"))
					fileName1 = multi.getFilesystemName(str);
				else if(str.equals("fileName2"))
					fileName2 = multi.getFilesystemName(str);
				else if(str.equals("fileName3"))
					fileName3 = multi.getFilesystemName(str);
				else if(str.equals("fileName4"))
					fileName4 = multi.getFilesystemName(str);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		ThemeBoardDAO dao = ThemeBoardDAO.getThemeBoardDAO();
		ThemeBoardDTO dto = new ThemeBoardDTO();
		String hashtagArr[];
		String hashtag="";
		dto.setTitle(multi.getParameter("title"));
		dto.setId(multi.getParameter("id"));
		dto.setNickName(multi.getParameter("nickName"));
		dto.setContents(multi.getParameter("contents"));
		dto.setInteresting(multi.getParameter("interesting"));
		dto.setAddress1(multi.getParameter("address1"));
		dto.setAddress2(multi.getParameter("address2"));
		dto.setLocationName(multi.getParameter("locationName"));
		dto.setLocationHoliday(multi.getParameter("locationHoliday"));
		dto.setLocationPay(multi.getParameter("locationPay"));
		dto.setLocationTime(multi.getParameter("locationTime"));
		hashtagArr = multi.getParameterValues("hashTag");
		for(int i=0;i<hashtagArr.length;i++) {
			hashtag +=(hashtagArr[i]+" ");
		}
		

		dto.setFileName1(fileName1);
		dto.setFileName2(fileName2);
		dto.setFileName3(fileName3);
		dto.setFileName4(fileName4);
		dto.setHashTag(hashtag);
		dao.themeboardWriteDAO(dto);

	}

}


//hashTag text,

////////////파일추가시............................작성필수........................////////
//filePath text,					#파일경로
//fileSize varchar(20),			#파일사이즈
//fileName1 varchar(20),  		#파일이름1
//fileName2 varchar(20),			#파일이름2
//fileName3 varchar(20),			#파일이름3
//fileName4 varchar(20),			#파일이름4



