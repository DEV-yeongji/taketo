package com.taketo.www.Command.Alliance;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AllianceDAO;
import com.taketo.www.DTO.AllianceBannerDTO;

public class AllianceBannerOKCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path= request.getSession().getServletContext().getRealPath("bannerImg");
		int size =1024*1024*10; 
		String allianceBanner = "";
		
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {

			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				allianceBanner = multi.getFilesystemName(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		AllianceDAO dao = AllianceDAO.getAllianceDAO();
		AllianceBannerDTO dto = new AllianceBannerDTO();
		dto.setAllianceBanner(allianceBanner);
		dto.setAllianceInfo(multi.getParameter("allianceInfo"));
		dto.setAllianceName(multi.getParameter("allianceName"));
		dao.allianceBannerOKDAO(dto);

	}

}
