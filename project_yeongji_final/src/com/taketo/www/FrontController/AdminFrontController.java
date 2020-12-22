package com.taketo.www.FrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taketo.www.Command.Command;
import com.taketo.www.Command.AdminCommand.AdminLocalBoardSetDeleteCommand;
import com.taketo.www.Command.AdminCommand.AdminLocalBoardSettingCommand;
import com.taketo.www.Command.AdminCommand.AdminLoginCommand;
import com.taketo.www.Command.AdminCommand.AdminMainPage;
import com.taketo.www.Command.AdminCommand.AdminMemberDeleteForCommand;
import com.taketo.www.Command.AdminCommand.AdminMemberModifyCommand;
import com.taketo.www.Command.AdminCommand.AdminMemberRegisterForAdminCommand;
import com.taketo.www.Command.AdminCommand.AdminReviewBoardSetDeleteCommand;
import com.taketo.www.Command.AdminCommand.AdminReviewBoardSettingCommand;
import com.taketo.www.Command.AdminCommand.AdminSiteEditFORBannerCommand;
import com.taketo.www.Command.AdminCommand.AdminSiteViewCommand;
import com.taketo.www.Command.AdminCommand.AdminThemeBoardSetDeleteCommand;
import com.taketo.www.Command.AdminCommand.AdminThemeBoardSettingCommand;
import com.taketo.www.Command.AdminCommand.ContactEditCommand;
import com.taketo.www.Command.AdminCommand.ContactEditOKCommnad;
import com.taketo.www.Command.AdminCommand.ContactTermUSerUpdateCommand;
import com.taketo.www.Command.Alliance.AllianceBannerDeleteCommand;
import com.taketo.www.Command.Alliance.AllianceBannerOKCommand;
import com.taketo.www.Command.Alliance.AllianceDeleteOkCommand;
import com.taketo.www.Command.Alliance.AllianceListCommand;
import com.taketo.www.Command.Alliance.AllianceSuccessCommand;
import com.taketo.www.Command.Alliance.AllianceViewCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventDeleteOKCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventListCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventModifyCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventSearchCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventViewCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventWriteOKCommand;
import com.taketo.www.Command.UserCommand.LoginCommand;

@WebServlet("*.admin")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminFrontController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Command command = null;
		String viewPage = "";
		String commandPath = request.getServletPath();
		boolean flag = false;
		
		if(commandPath.equals("/adminPage.admin")){
			command = new AdminMainPage();
			command.execute(request, response);
			viewPage = "adminPage.jsp";
		}else if(commandPath.equals("/adminLoginOK.admin")){
			command = new AdminLoginCommand();
			command.execute(request, response);
			viewPage = "adminPage.admin";
			flag = true;
		}else if(commandPath.equals("/adminLogOut.admin")) {
			HttpSession session = request.getSession();
			session.removeAttribute("id");
			session.invalidate();
			viewPage = "adminPage.jsp";
		}else if(commandPath.equals("/memberRegisterForAdmin.admin")) {		//유저관리페이지
			command = new AdminMemberRegisterForAdminCommand();
			command.execute(request, response);
			viewPage = "/admin/memberRegisterForAdmin.jsp";
		}else if(commandPath.equals("/memberRegisterModify.admin")) {		//유저 포인트 수정
			command = new AdminMemberModifyCommand();
			command.execute(request, response);
			viewPage = "memberRegisterForAdmin.admin";
			flag = true;
		}else if(commandPath.equals("/memberDeleteForAdmin.admin")) {		//유저 삭제
			command = new AdminMemberDeleteForCommand();
			command.execute(request, response);
			viewPage = "memberRegisterForAdmin.admin";
			flag = true;
		}else if(commandPath.equals("/editBannerView.admin")) {		//배너이미지 관리 View페이지
			command = new AdminSiteViewCommand();
			command.execute(request, response);
			viewPage = "/admin/EditBannerView.jsp";
		}else if(commandPath.equals("/EditBanner.admin")) {		//배너이미지 업로드 Command
			command = new AdminSiteEditFORBannerCommand();
			command.execute(request, response);
			viewPage = "editBannerView.admin";	
			flag = true;
		}else if(commandPath.equals("/editContact.admin")) {		//회사정보 관리 view페이지
			command = new ContactEditCommand();
			command.execute(request, response);
			viewPage = "/admin/EditContatct.jsp";
		}else if(commandPath.equals("/contactEditOK.admin")) {		//회사정보 관리 수정OK Command
			command = new ContactEditOKCommnad();
			command.execute(request, response);
			viewPage="editContact.admin";
			flag = true;
		}else if(commandPath.equals("/termUserForm.admin")) {			//개인정보 관리 view페이지
			command = new ContactEditCommand();
			command.execute(request, response);
			viewPage ="/admin/EditTermUserForm.jsp";
		}else if(commandPath.equals("/termUserFormOK.admin")) {			//개인정보 관리 수정OK commnad
			command = new ContactTermUSerUpdateCommand();
			command.execute(request, response);
			viewPage="termUserForm.admin";
			flag = true;	
		}else if(commandPath.equals("/NoticeEventListAdmin.admin")) {			// 공지사항및 이벤트 게시물 목록 (관리자한정CSS)
			command = new NoticeEventListCommand();
			command.execute(request, response);
			viewPage ="/admin/NoticeEventListForAdmin.jsp";
		}else if(commandPath.equals("/noticeWrite.admin")) {					//공지사항 및 이벤트 게시물 쓰기 JSP
			viewPage = "/admin/NoticeWrite.jsp";
		}else if(commandPath.equals("/NoticeEventWriteOK.admin")) {				//공지사항 및 이벤트 게시물 쓰기Command
			command = new NoticeEventWriteOKCommand();
			command.execute(request, response);
			viewPage = "NoticeEventListAdmin.admin";
			flag = true;
		}else if(commandPath.equals("/NoticeBoardDelete.admin")) {				//공지사항 및 이벤트 삭제 Command
			command = new NoticeEventDeleteOKCommand();
			command.execute(request, response);
			viewPage = "NoticeEventListAdmin.admin";
			flag = true;
		}else if(commandPath.equals("/NoticeBoardView.admin")) {				//공지사항 및 이벤트 게시글 뷰 Command
			command = new NoticeEventViewCommand();
			command.execute(request, response);
			viewPage ="./NoticeEvent/NoticeEventView.jsp";
		}else if(commandPath.equals("/NoticeBoardModify.admin")) {				//공지사항 및 이벤트 게시물 수정 Command
			command = new NoticeEventModifyCommand();
			command.execute(request, response);
			viewPage ="NoticeEventListAdmin.admin";
			flag = true;
		}else if(commandPath.equals("/NoticeEventListForAdminSearch.admin")) {		//공지사항 및 이벤트 게시글 검색Commnad
			command = new NoticeEventSearchCommand();
			command.execute(request, response);
			viewPage = "./admin/NoticeEventListForAdminSearchVersion.jsp";
		}else if(commandPath.equals("/allianceSetting.admin")) {					//제휴게시판 
			command = new AllianceListCommand();
			command.execute(request, response);
			viewPage ="./admin/AllianceList.jsp";
		}else if(commandPath.equals("/allianceView.admin")) {						//제휴게시판 뷰
			command = new AllianceViewCommand();
			command.execute(request, response);
			viewPage="./admin/AllianceView.jsp";
		}else if(commandPath.equals("/allianceSuccessOK.admin")) {					//제휴 수락시 Command
			command = new AllianceSuccessCommand();
			command.execute(request, response);
			viewPage="allianceSetting.admin";
			flag = true;
		}else if(commandPath.equals("/allianceDeleteOK.admin")) {				//제휴매장삭ㅈㅔ
			command = new AllianceDeleteOkCommand();
			command.execute(request, response);
			viewPage="allianceSetting.admin";
			flag = true;
		}else if(commandPath.equals("/allianceBannerOK.admin")) {			// 제휴매장 배너등록
			command = new AllianceBannerOKCommand();
			command.execute(request, response);
			viewPage ="allianceSetting.admin";
			flag = true;
		}else if(commandPath.equals("/allianceBannerListDelete.admin")) {   //제휴매장 배너삭제
			command = new AllianceBannerDeleteCommand();
			command.execute(request, response);
			viewPage ="allianceSetting.admin";
			flag = true;
		}else if(commandPath.equals("/ThemeBoardSetting.admin")) {        //테마별게시판 관리
			command = new AdminThemeBoardSettingCommand();
			command.execute(request, response);
			viewPage ="./admin/ThemeBoardSetting.jsp";
		}else if(commandPath.equals("/themeBoardSetDelete.admin")) { 	//테마별게시판 삭제
			command = new AdminThemeBoardSetDeleteCommand();
			command.execute(request, response);
			viewPage ="ThemeBoardSetting.admin";
			flag = true;
		}else if(commandPath.equals("/ReviewBoardSetting.admin")) {  //리뷰게시판 관리
			command = new AdminReviewBoardSettingCommand();
			command.execute(request, response);
			viewPage = "./admin/ReviewBoardSetting.jsp";
		}else if(commandPath.equals("/reviewBoardSetDeleteOK.admin")) { 	//리뷰게시판 삭제
			command = new AdminReviewBoardSetDeleteCommand();
			command.execute(request, response);
			viewPage ="ReviewBoardSetting.admin";
			flag = true;
		}else if(commandPath.equals("/LocalBoardSetting.admin")) {			//지역게시판 관리
			command = new AdminLocalBoardSettingCommand();
			command.execute(request, response);
			viewPage ="./admin/LocalBoardSetting.jsp";
		}else if(commandPath.equals("/LocalBoardSetDeleteOK.admin")) { 	//지역게시판 삭제
			command = new AdminLocalBoardSetDeleteCommand();
			command.execute(request, response);
			viewPage ="LocalBoardSetting.admin";
			flag = true;
		}
		
		if(flag) {
			response.sendRedirect(viewPage);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}
