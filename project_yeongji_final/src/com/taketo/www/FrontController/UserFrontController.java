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
import com.taketo.www.Command.AdminCommand.AdminSiteViewCommand;
import com.taketo.www.Command.AdminCommand.ContactEditCommand;
import com.taketo.www.Command.Alliance.AllianceOKCommand;
import com.taketo.www.Command.BoardCommand.CommentDeleteCommand;
import com.taketo.www.Command.BoardCommand.CommentModifyCommand;
import com.taketo.www.Command.BoardCommand.CommentWriteOKCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardDeleteOKCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardInterestingSearchCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardListCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardModifyCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardModifyOKCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardSearchCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardViewCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardWriteCommand;
import com.taketo.www.Command.BoardCommand.ThemeBoardWriteOKCommand;
import com.taketo.www.Command.LocalBoard.CourseBoardWriteOKCommand;
import com.taketo.www.Command.LocalBoard.LocalBoardDeleteCommand;
import com.taketo.www.Command.LocalBoard.LocalBoardModifyCommand;
import com.taketo.www.Command.LocalBoard.LocalBoardViewCommand;
import com.taketo.www.Command.LocalBoard.LocalBoardWriteCommand;
import com.taketo.www.Command.LocalBoard.LocalListCommand;
import com.taketo.www.Command.LocalBoard.LocalListSearchCommand;
import com.taketo.www.Command.LocalBoard.LocaleBoardModifyOKCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventListCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventSearchCommand;
import com.taketo.www.Command.NoticeEventCommand.NoticeEventViewCommand;
import com.taketo.www.Command.ReviewBoard.RevieBoardListCommand;
import com.taketo.www.Command.ReviewBoard.ReviewBoardDeleteOKCommand;
import com.taketo.www.Command.ReviewBoard.ReviewBoardModifyOKCommand;
import com.taketo.www.Command.ReviewBoard.ReviewBoardViewCommand;
import com.taketo.www.Command.ReviewBoard.ReviewBoardWriteCommand;
import com.taketo.www.Command.ReviewBoard.ReviewBoardWriteOKCommand;
import com.taketo.www.Command.UserCommand.IdChkCommand;
import com.taketo.www.Command.UserCommand.LoginCommand;
import com.taketo.www.Command.UserCommand.LostIDCommand;
import com.taketo.www.Command.UserCommand.LostPWCommand;
import com.taketo.www.Command.UserCommand.MypageCommand;
import com.taketo.www.Command.UserCommand.NickNameChkCommand;
import com.taketo.www.Command.UserCommand.UserDeleteCommand;
import com.taketo.www.Command.UserCommand.UserJoinCommand;
import com.taketo.www.Command.UserCommand.UserModifyOKCommand;


@WebServlet("*.do")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFrontController() {
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
		
		String viewPage = "";
		String commandPath = request.getServletPath();
		Command command = null;
		boolean flag = false;
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI " + requestURI);
		System.out.println("servletPath " +  commandPath);
		
		if(commandPath.equals("/index.do")) {							// 메인화면
			command = new AdminSiteViewCommand();
			command.execute(request, response);
			viewPage ="index.jsp";	
		}else if(commandPath.equals("/loginOK.do")) {					// 로그인 성공 Command
			command = new LoginCommand();
			command.execute(request, response);
			viewPage = "index.do";
			flag = true;
		}else if(commandPath.equals("/logout.do")) {					// 로그아웃 Command
			HttpSession session = request.getSession();
			session.removeAttribute("id");
			session.invalidate();
			viewPage = "/index.do";
		}else if(commandPath.equals("/TermsofUse.do")) {				// 회원가입(약관)View 이동
			command = new ContactEditCommand();
			command.execute(request, response);
			viewPage="/user/TermsofUse.jsp";
		}else if(commandPath.equals("/userJoin.do")) {					// 회원가입View 이동
			viewPage ="/user/userJoin.jsp";
		}else if(commandPath.equals("/joinOK.do")) {					// 가입성공 Command
			command = new UserJoinCommand();
			command.execute(request, response);
			viewPage="/user/successJoin.jsp";
		}else if(commandPath.equals("/mypage.do")) {					// 마이페이지 Command
			command = new MypageCommand();
			command.execute(request, response);
			viewPage = "/user/mypage.jsp";
		}else if(commandPath.equals("/userModifyOK.do")) {				//회원정보 수정완료 Command
			command = new UserModifyOKCommand();
			command.execute(request, response);
			viewPage = "index.do";	
			flag = true;
		}else if(commandPath.equals("/idchkOK.do")) {					//아이디체크 Command
			command = new IdChkCommand();
			command.execute(request, response);
			viewPage = "/user/idchkSuccess.jsp";
		}else if(commandPath.equals("/NickNameChk.do")) {				//닉네임체크 Command
			command = new NickNameChkCommand();
			command.execute(request, response);
			viewPage = "/user/nickChkSuccess.jsp";
		}else if(commandPath.equals("/deleteOK.do")) {
			command = new UserDeleteCommand();
			command.execute(request, response);
			viewPage = "/logout.do";
		}else if(commandPath.equals("/idLoastOK.do")) {					//아이디찾기 Command
			command = new LostIDCommand();
			command.execute(request, response);
			viewPage = "/user/findMyId.jsp";
		}else if(commandPath.equals("/pwLostOK.do")) {
			command = new LostPWCommand();
			command.execute(request, response);
			viewPage ="/user/findMyPW.jsp";
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}else if(commandPath.equals("/ThemeBoard.do")) {				//테마별 게시판 Command
			command = new ThemeBoardListCommand();
			command.execute(request, response);
			viewPage = "/themeboard/Themeboard.jsp";
		}else if(commandPath.equals("/ThemeboardWrite.do")) {			//테마별 게시판 글쓰기 링크
			command = new ThemeBoardWriteCommand();
			command.execute(request, response);
			viewPage = "/themeboard/ThemeboardWrite.jsp";
		}else if(commandPath.equals("/ThemeboardWriteOK.do")) {			//테마별 게시판 글쓰기 완료 Command
			command = new ThemeBoardWriteOKCommand();
			command.execute(request, response);
			viewPage = "ThemeBoard.do";
			flag = true;
		}else if(commandPath.equals("/ThemeBoardView.do")) {			//테마별 게시판 뷰 페이지 Command
			command = new ThemeBoardViewCommand();
			command.execute(request, response);
			viewPage = "/themeboard/ThemeboardView.jsp";
		}else if(commandPath.equals("/ThemeBoardModify.do")) {			//테마별 게시판 수정 페이지 Command
			command = new ThemeBoardModifyCommand();
			command.execute(request, response);
			viewPage = "/themeboard/ThemeBoardModify.jsp";
		}else if(commandPath.equals("/ThemeboardModifyOK.do")) {
			command = new ThemeBoardModifyOKCommand();
			command.execute(request, response);
			viewPage = "ThemeBoard.do";
			flag = true;
		}else if(commandPath.equals("/ThemeBoardDeleteOK.do")) {		//테마별 게시판 삭제 페이지 Command
			command = new ThemeBoardDeleteOKCommand();
			command.execute(request, response);
			viewPage = "ThemeBoard.do";	
			flag = true;
		}else if(commandPath.equals("/search.do")) {					//테마별 게시판 검색Command
			command = new ThemeBoardSearchCommand();
			command.execute(request, response);
			viewPage = "/themeboard/ThemeBoardSearch.jsp";
		}else if(commandPath.equals("/searchInteresting.do")) {			//테마별 게시판 테마별검색
			command = new ThemeBoardInterestingSearchCommand();
			command.execute(request, response);
			viewPage = "/themeboard/ThemeBoardInteresingSearch.jsp";
		}else if(commandPath.equals("/userSeeNoticeBoard.do")) {			//유저들이보는 공지사항 및 이벤트 페이지 Command
			command = new NoticeEventListCommand();
			command.execute(request, response);
			viewPage = "/NoticeEvent/NoticeEventList.jsp";
		}else if(commandPath.equals("/NoticeBoardView.do")) {
			command = new NoticeEventViewCommand();
			command.execute(request, response);
			viewPage = "/NoticeEvent/NoticeEventView.jsp";
		}else if(commandPath.equals("/NoticeEventListForUserSearch.do")) {		//공지사항 및 이벤트 페이지 검색처리 Command
			command = new NoticeEventSearchCommand();
			command.execute(request, response);
			viewPage = "./NoticeEvent/NoticeEventListForUserSearchVersion.jsp";	
		}else if(commandPath.equals("/contactInfo.do")) {						//사이트 및 제휴 페이지 연결
			command = new ContactEditCommand();
			command.execute(request, response);
			viewPage = "/ContactInfo/contactInfo.jsp";
		}else if(commandPath.equals("/allianceOK.do")) {						//제휴 입력 Command
			command = new AllianceOKCommand();
			command.execute(request, response);
			viewPage = "contactInfo.do";
			flag = true;
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		else if(commandPath.equals("/localcourse.do")) {					 //지역별코스 게시판리스트
			command = new LocalListCommand();
			command.execute(request, response);
			viewPage ="./LocalCourse/localcourseList.jsp";
		}else if(commandPath.equals("/localCourseSearch.do")) {				//지역별코스 게시판리스트메인에서 지역별눌렀을때겟방식으로 해당값받아오기(검색)
			command = new LocalListSearchCommand();
			command.execute(request, response);
			viewPage ="./LocalCourse/localcourseSearch.jsp";
		}else if(commandPath.equals("/courseBoardWrite.do")) {				//지역별코스 게시판 작성을위해서 유저정보랑 도시가져오기.
			command = new LocalBoardWriteCommand();
			command.execute(request, response);
			viewPage = "./LocalCourse/courseBoardWrite.jsp";
		}else if(commandPath.equals("/courseBoardWriteOK.do")) {			//지역별코스 게시판 작성완료 Commnad
			command = new CourseBoardWriteOKCommand(); 
			command.execute(request, response);
			viewPage = "localcourse.do";
			flag = true;
		}else if(commandPath.equals("/localBoardView.do")) {				//지역별코스 게시판 뷰
			command = new LocalBoardViewCommand();
			command.execute(request, response);
			viewPage ="./LocalCourse/courseBoardView.jsp";
		}else if(commandPath.equals("/localBoardDeleteOK.do")) {			// 지역별코스 삭제
			command = new LocalBoardDeleteCommand();
			command.execute(request, response);
			viewPage ="localcourse.do";
			flag = true;
		}else if(commandPath.equals("/localBoadrModify.do")) {
			command = new LocalBoardModifyCommand();
			command.execute(request, response);
			viewPage ="./LocalCourse/localBoardModify.jsp";
		}else if(commandPath.equals("/courseBoardModifyOK.do")) {			//지역게시판 수정완료
			command = new LocaleBoardModifyOKCommand();
			command.execute(request, response);
			viewPage ="localcourse.do";
			flag = true;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if(commandPath.equals("/commentWriteOK.do")) {					//테마게시판에 댓글작성
			command = new CommentWriteOKCommand();
			command.execute(request, response);
			viewPage="ThemeBoardView.do";
			
		}else if(commandPath.equals("/modifyComment.do")) {					//테마게시판에 댓글수정
			command = new CommentModifyCommand();
			command.execute(request, response);
			viewPage="ThemeBoardView.do";
			
		}else if(commandPath.equals("/commentDeleteOK.do")) {				//테마게시판에 댓글삭제
			command = new CommentDeleteCommand();
			command.execute(request, response);
			viewPage="ThemeBoardView.do";
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if(commandPath.equals("/reviewBoard.do")) {					//리뷰게시판
			command = new RevieBoardListCommand();
			command.execute(request, response);
			viewPage="./ReviewBoard/reviewBoardList.jsp";
		}else if(commandPath.equals("/ReviewBoardWrite.do")) {				//리뷰게시판 글 작성하려면 유저정보얻어야되서 얻어오는Command
			command = new ReviewBoardWriteCommand();
			command.execute(request, response);
			viewPage="./ReviewBoard/reviewBoardWrite.jsp";
		}else if(commandPath.equals("/reviewBoardWriteOK.do")) {		//리뷰게시판 글 작성완료
			command = new ReviewBoardWriteOKCommand();
			command.execute(request, response);
			viewPage="reviewBoard.do";
			flag = true;
		}else if(commandPath.equals("/ReviewBoardView.do")) {		//리뷰게시판 뷰
			command = new ReviewBoardViewCommand();
			command.execute(request, response);
			viewPage ="./ReviewBoard/reviewBoardView.jsp";
		}else if(commandPath.equals("/reviewBoardDeleteOK.do")) {	//리뷰게시판 삭제
			command = new ReviewBoardDeleteOKCommand();
			command.execute(request, response);
			viewPage = "reviewBoard.do";
			flag = true;
		}else if(commandPath.equals("/reviewBoardModifyOK.do")) {
			command = new ReviewBoardModifyOKCommand();
			command.execute(request, response);
			viewPage ="reviewBoard.do";
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
