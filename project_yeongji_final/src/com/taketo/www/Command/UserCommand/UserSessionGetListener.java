package com.taketo.www.Command.UserCommand;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.http.HttpRequest;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.SessionEvent;

import com.taketo.www.DAO.UserDAO;
import com.taketo.www.DTO.TotalVisitedDTO;

@WebListener
public class UserSessionGetListener implements HttpSessionListener{
	int sessionCnt;
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		// TODO Auto-generated method stub
		InetAddress ip;
		String getIP="";
			try {
				ip = InetAddress.getLocalHost();
				String ipAddress = ip.getHostAddress();
				System.out.print("ipaddress :" + ipAddress);
				getIP = ipAddress;
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
		HttpSession session = sessionEvent.getSession();
		UserDAO dao = UserDAO.getUserDAO();
		
		//아이피 다른것만 추가시키기
		boolean flag = dao.selectEqualsDAO(getIP);
		if(!flag) {
			++sessionCnt;
			
			TotalVisitedDTO dto = new TotalVisitedDTO();
			dto.setIpAddress(getIP); 
			dto.setVisited(sessionCnt);
			dao.todayVisited(dto);
		}
		System.out.print("오늘 총 방문횟수 " + sessionCnt);
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionDestroyed(session);
	}
	

}
