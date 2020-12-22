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

import com.taketo.www.DTO.ReviewBoardDTO;
import com.taketo.www.DTO.ThemeBoardDTO;

public class ReviewBoardDAO {
	private static ReviewBoardDAO reviewBoardDAO = new ReviewBoardDAO();
	public static ReviewBoardDAO getReviewBoardDAO() {
		return reviewBoardDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "reviewBoard";
	private final String USER_TABLE = "user";
	private final int numOfPage = 5;
	private final String INSERT_REVIEW_BOARD_SQL = " insert into " + TABLE_NAME + " (id,nickName,contents,title,img) values(?,?,?,?,?)";
	private final String SELECT_REVIEW_LIST_SQL = "select * from " + TABLE_NAME + " order by no desc limit ?,?";
	private final String SELECT_USER_PROFILE = " select profile from " + USER_TABLE + " where id = ?";
	private final String UPDATE_REVIEW_BOARD_HIT_SQL = " update " + TABLE_NAME + " set hit = hit+1 where no = ?";
	private final String SELECT_REVIEW_VIEW_SQL =" select * from " + TABLE_NAME + " where no = ?";
	private final String DELETE_REVIEW_SQL = " delete  from " + TABLE_NAME + " where no =?";
	private final String UPDATE_REVIEW_BOARD_SQL = "update " + TABLE_NAME + " set wtime=now(), title=?,contents=?,img=? where no =?";
	private final String SELECT_REVIEW_CNT = "select count(*) from " + TABLE_NAME ; 
	private final String SELECT_USER_INTRODUCE = "select introduce from " + USER_TABLE + " where id =?";
	private final String SELECT_USER_INTERESTING = "select interesting from " + USER_TABLE + " where id =?";
	private ReviewBoardDAO() {
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
	public void reviewBoardWriteOKDAO(ReviewBoardDTO dto) {
		Connection conn = getConnection();
		int result =0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_REVIEW_BOARD_SQL);
//			 (id,nickName,contents,title,img)
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getNickName());
			pstmt.setString(3, dto.getContents());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getImg());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<ReviewBoardDTO> reviewBoardListDAO(int curPage) {
		ArrayList<ReviewBoardDTO> list = new ArrayList<ReviewBoardDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_REVIEW_LIST_SQL);
			pstmt.setInt(1, curPage*numOfPage);
			pstmt.setInt(2, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBoardDTO dto = new ReviewBoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setContents(rs.getString("contents"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setImg(rs.getString("img"));
				dto.setNickName(rs.getString("nickName"));
				dto.setTitle(rs.getString("title"));
				dto.setWtime(rs.getString("wtime"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public void updateHitBoard(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_REVIEW_BOARD_HIT_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(conn, pstmt);
	}
	public ReviewBoardDTO getReviewBoardDTO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBoardDTO dto = new ReviewBoardDTO();
		try {
			pstmt = conn.prepareStatement(SELECT_REVIEW_VIEW_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setContents(rs.getString("contents"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setImg(rs.getString("img"));
				dto.setNickName(rs.getString("nickName"));
				dto.setTitle(rs.getString("title"));
				dto.setWtime(rs.getString("wtime"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
		
	}
	public ReviewBoardDTO reviewBoardViewDAO(int no) {
		updateHitBoard(no);
		ReviewBoardDTO dto = getReviewBoardDTO(no);
		return dto;
	}
	public String getProfileImg(String id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String getProfile= null;
		try {
			pstmt = conn.prepareStatement(SELECT_USER_PROFILE);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				getProfile = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return getProfile;
	}
	public void reviewBoardDeleteOKDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(DELETE_REVIEW_SQL);
			pstmt.setInt(1, no);
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public void reviewBoardModifyOKDAO(ReviewBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(UPDATE_REVIEW_BOARD_SQL);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setString(3, dto.getImg());
			pstmt.setInt(4, dto.getNo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public int getCnt() {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_REVIEW_CNT);
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
	public int getMaxPage() {
		int cnt = getCnt();
		int maxPage = cnt/numOfPage;
		maxPage = cnt%numOfPage==0?maxPage-1:maxPage;
		return maxPage;
		
	}
	public String getIntroduce(String id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String introduce= null;
		try {
			pstmt = conn.prepareStatement(SELECT_USER_INTRODUCE);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				introduce = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return introduce;
	}
	public String getUserInteresting(String id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String interesting= null;
		try {
			pstmt = conn.prepareStatement(SELECT_USER_INTERESTING);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				interesting = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return interesting;
	}
}
