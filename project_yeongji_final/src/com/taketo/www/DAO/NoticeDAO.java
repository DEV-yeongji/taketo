package com.taketo.www.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taketo.www.DTO.NoticeDTO;

public class NoticeDAO {
	private static NoticeDAO noticeDAO = new NoticeDAO();
	public static NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}
	DataSource dataSource;
	private int numOfPage = 5;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "noticeEventBoard";
	private final String INSERT_NOTICE_BOARD_SQL = "insert into " + TABLE_NAME + " (subject,title,id,img,term,contents) values (?,?,?,?,?,?)";
	private final String SELECT_NOTICE_LIST_SQL = " select * from " + TABLE_NAME + " where term > now() order by no desc limit ?,?";
	private final String DELETE_NOTICE_BOARD_SQL = " delete from " + TABLE_NAME + " where no = ?";
	private final String SELECT_NOTICE_VIEW_SQL = " select * from " + TABLE_NAME + " where no = ?";
	private final String UPDATE_NOTICE_BOARD_SQL = "update " + TABLE_NAME + " set title = ? , term = ? , img = ? , contents =? , writeTime = now() where no =?";
	private final String UPDATE_NOTICE_HIT_SQL = "update " + TABLE_NAME + " set hit = hit+1 where no =?";
	private final String SELECT_NOTICE_SEAECH_RESULT_SQL = " select * from " + TABLE_NAME + " where term > now() and subject like ? order by no desc limit ?,?";
	private final String SELECT_NOTICE_EVENT_BOARD_CNT = " select count(*) from " + TABLE_NAME;
	private final String SELECT_NOTICE_BOARD_CNT = " select count(*) from " + TABLE_NAME + " where subject like ?";
	
	private NoticeDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(Connection conn,PreparedStatement pstmt, ResultSet rs) {
		try {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null)rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(Connection conn,PreparedStatement pstmt) {
		try {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void NoticeEventBoardWriteOK(NoticeDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_NOTICE_BOARD_SQL);
//			subject,title,id,img,term,contents
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getImg());
			pstmt.setString(5, ""+dto.getTerm()+"");
			pstmt.setString(6, dto.getContents());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public ArrayList<NoticeDTO> noticeListDAO(int curPage) {
		ArrayList<NoticeDTO> notice = new ArrayList<NoticeDTO>();
		Connection conn = getConnection();
		ResultSet rs= null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_LIST_SQL);
			pstmt.setInt(1, curPage * numOfPage);
			pstmt.setInt(2, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setId(rs.getString("id"));
				dto.setImg(rs.getString("img"));
				dto.setSubject(rs.getString("subject"));
				dto.setHit(rs.getInt("hit"));
				dto.setTerm(rs.getString("term"));
				dto.setWriteTime(rs.getString("writeTime"));
				notice.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return notice;
	}
	public void noticeDeleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(DELETE_NOTICE_BOARD_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public void updateHitNotice(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt =conn.prepareStatement(UPDATE_NOTICE_HIT_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public NoticeDTO noticeBoardViewDAO(int no) {
		updateHitNotice(no);
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDTO dto = new NoticeDTO();
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_VIEW_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setWriteTime(rs.getString("writeTime"));
				dto.setTitle(rs.getString("title"));
				dto.setTerm(rs.getString("term"));
				dto.setSubject(rs.getString("subject"));
				dto.setNo(rs.getInt("no"));
				dto.setImg(rs.getString("img"));
				dto.setId(rs.getString("id"));
				dto.setHit(rs.getInt("hit"));
				dto.setContents(rs.getString("contents"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	public void noticeBoardModifyDAO(NoticeDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_NOTICE_BOARD_SQL);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getTerm());
			pstmt.setString(3, dto.getImg());
			pstmt.setString(4, dto.getContents());
			pstmt.setInt(5, dto.getNo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<NoticeDTO> noticeSearchListDAO(String subject, int curPage) {
		ArrayList<NoticeDTO> notice = new ArrayList<NoticeDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_SEAECH_RESULT_SQL);
			pstmt.setString(1, "%"+subject+"%");
			pstmt.setInt(2, curPage * numOfPage);
			pstmt.setInt(3, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setId(rs.getString("id"));
				dto.setImg(rs.getString("img"));
				dto.setSubject(rs.getString("subject"));
				dto.setHit(rs.getInt("hit"));
				dto.setTerm(rs.getString("term"));
				dto.setWriteTime(rs.getString("writeTime"));
				notice.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	public int getCnt() {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_EVENT_BOARD_CNT);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		return cnt;
	}
	public int getMaxPage(int cnt) {
		int maxPage = cnt/numOfPage;
		maxPage = cnt%numOfPage==0?maxPage-1:maxPage;
		return maxPage;
	}
	public int getSearchCnt(String subject) {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_BOARD_CNT);
			pstmt.setString(1, "%"+subject+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		return cnt;
	}
}
