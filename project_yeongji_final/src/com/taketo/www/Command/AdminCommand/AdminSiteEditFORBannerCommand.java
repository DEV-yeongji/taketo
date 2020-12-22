package com.taketo.www.Command.AdminCommand;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taketo.www.Command.Command;
import com.taketo.www.DAO.AdminDAO;
import com.taketo.www.DTO.EditSiteBannerDTO;

public class AdminSiteEditFORBannerCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getAdminDAO();
		EditSiteBannerDTO dto = new EditSiteBannerDTO();
		
		String path= request.getSession().getServletContext().getRealPath("bannerImg");
		int size =1024*1024*10; 
		String bannerImg1 = "";
		String bannerImg2 = "";
		String bannerImg3 = "";
		String bannerImg4 = "";
		String bannerImg5 = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {

			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String img1 = (String)files.nextElement();
				bannerImg1 = multi.getFilesystemName(img1);
				String img2 = (String)files.nextElement();
				bannerImg2 = multi.getFilesystemName(img2);
				String img3 = (String)files.nextElement();
				bannerImg3 = multi.getFilesystemName(img3);
				String img4 = (String)files.nextElement();
				bannerImg4 = multi.getFilesystemName(img4);
				String img5 = (String)files.nextElement();
				bannerImg5 = multi.getFilesystemName(img5);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		dto.setBannerImg1(bannerImg1);
		dto.setBannerImg2(bannerImg2);
		dto.setBannerImg3(bannerImg3);
		dto.setBannerImg4(bannerImg4);
		dto.setBannerImg5(bannerImg5);
		dao.siteEditBanner(dto);
	}
}
