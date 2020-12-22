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

import com.taketo.www.DTO.AdminDTO;
import com.taketo.www.DTO.EditSiteBannerDTO;
import com.taketo.www.DTO.ThemeBoardDTO;
import com.taketo.www.DTO.TotalVisitedDTO;
import com.taketo.www.DTO.UserDTO;

public class AdminDAO {

	private static AdminDAO adminDAO = new AdminDAO();
	public static AdminDAO getAdminDAO() {
		return adminDAO;
	}
	DataSource dataSource;
	private final int numOfPage = 10;
	private final String TABLE_NAME = "admin";
	private final String BOARD_TABLE_NAME = "mainBoard";
	private final String TOTAL_VISITED_TABLE_NAME = "todayvisited";
	private final String USER_TABLE_NAME = "user";
	private final String BANNER_TABLE_NAME = "editBanner";
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String SELECT_GET_SESSION = " select id,password from " + TABLE_NAME ;
	private final String SELECT_LATEST_BOARD_SQL = "select title, id, fileName1 from " + BOARD_TABLE_NAME + " where wtime - now() < 1 limit 5";
	private final String SELECT_TOTAL_VISITED = "select date_format(day,'%Y-%m-%d') as daily, count(visited) as visit from " + TOTAL_VISITED_TABLE_NAME + " group by daily limit 10";
	private final String SELECT_MEMBER_REGISTER_SQL = " select * from " + USER_TABLE_NAME + " order by joinDate desc limit ?,?" ;
	private final String UPDATE_USER_MODIFY_SQL =" update " + USER_TABLE_NAME + " set point = ? where id = ?";
	private final String DELETE_USER_SQL = " delete from " + USER_TABLE_NAME + " where id =? "; 
	private final String INSERT_INTO_BANNER_EDIT = "insert into " + BANNER_TABLE_NAME + " (bannerImg1,bannerImg2,bannerImg3,bannerImg4,bannerImg5) values(?,?,?,?,?)";
	private final String SELECT_LATEST_BANNER_SQL = " select * from " + BANNER_TABLE_NAME + " order by no desc limit 1";
	private final String SELECT_HOW_MANY_USER_SQL ="select count(*) from " + USER_TABLE_NAME ;
	private AdminDAO() {
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
	public boolean getAdminSession(AdminDTO dto) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(SELECT_GET_SESSION);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getId().matches(rs.getString("id"))&& dto.getPassword().matches(rs.getString("password"))){
					flag = true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		
		return flag;
	}
	public ArrayList<ThemeBoardDTO> latestGetBoard() {
		ArrayList<ThemeBoardDTO> latest_board = new ArrayList<ThemeBoardDTO>();
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LATEST_BOARD_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ThemeBoardDTO dto = new ThemeBoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setFileName1(rs.getString("fileName1"));
				latest_board.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(conn, pstmt);
		}
		return latest_board;
	}
	public ArrayList<TotalVisitedDTO> totalVisited() {
		ArrayList<TotalVisitedDTO> totalvisited = new ArrayList<TotalVisitedDTO>();
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(SELECT_TOTAL_VISITED);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TotalVisitedDTO dto = new TotalVisitedDTO();
				dto.setDay(rs.getString(1));
				dto.setVisited(rs.getInt(2));
				totalvisited.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return totalvisited;
	}
	public ArrayList<UserDTO> memberRegisterDAO(int curPage) {
		ArrayList<UserDTO> memberRegisterForAdmin = new ArrayList<UserDTO>();
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_MEMBER_REGISTER_SQL);
			pstmt.setInt(1, curPage*numOfPage);
			pstmt.setInt(2, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setmTelecom(rs.getString("mTelecom"));
				dto.setMobile(rs.getString("mobile"));
				dto.setCertifiCode(rs.getString("certifiCode"));
				dto.setEmail(rs.getString("email"));
				dto.setbYear(rs.getString("bYear"));
				dto.setbMonth(rs.getString("bMonth"));
				dto.setbDay(rs.getString("bDay"));
				dto.setPostCode(rs.getString("postCode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setGender(rs.getString("gender"));
				dto.setSnsId(rs.getString("snsId"));
				dto.setPoint(rs.getInt("point"));
				dto.setInteresting(rs.getString("interesting"));
				dto.setIntroduce(rs.getString("introduce"));	
				dto.setProfile(rs.getString("profile"));
				dto.setJoinDate(rs.getString("joinDate"));
				memberRegisterForAdmin.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return memberRegisterForAdmin;
	}
	public void modifyMemberForAdminDAO(String id,int point) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
				//유저포인트 갱신
			pstmt = conn.prepareStatement(UPDATE_USER_MODIFY_SQL);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public void deleteMemberForAdmin(String id) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_USER_SQL);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public void siteEditBanner(EditSiteBannerDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(INSERT_INTO_BANNER_EDIT);
			pstmt.setString(1, dto.getBannerImg1());
			pstmt.setString(2, dto.getBannerImg2());
			pstmt.setString(3, dto.getBannerImg3());
			pstmt.setString(4, dto.getBannerImg4());
			pstmt.setString(5, dto.getBannerImg5());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public EditSiteBannerDTO getBennerLatestDAO() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EditSiteBannerDTO dto = new EditSiteBannerDTO();
		try {
			
			pstmt = conn.prepareStatement(SELECT_LATEST_BANNER_SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setBannerImg1(rs.getString("bannerImg1"));
				dto.setBannerImg2(rs.getString("bannerImg2"));
				dto.setBannerImg3(rs.getString("bannerImg3"));
				dto.setBannerImg4(rs.getString("bannerImg4"));
				dto.setBannerImg5(rs.getString("bannerImg5"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	public int getCnt() {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_HOW_MANY_USER_SQL);
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
	
}
