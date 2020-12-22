package com.taketo.www.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taketo.www.DTO.TotalVisitedDTO;
import com.taketo.www.DTO.UserDTO;

public class UserDAO {
	private static UserDAO userDAO = new UserDAO();
	public static UserDAO getUserDAO() {
		return userDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="user";
	private final String TODAY_TOTAL_TABLE_NAME ="todayVisited";
	private final String INSERT_USER_SQL = "insert into " + TABLE_NAME + " (id,nickName ,name,password,mTelecom, mobile,certifiCode,email,bYear,bMonth, bDay ,postCode  , address1 , address2 ,detailAddress, gender ,snsId,interesting ,introduce ) "
											+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String GET_SESSION_FOR_LOGIN_SQL ="select id,password from " + TABLE_NAME;
	private final String SELECT_USER_INFO_SQL = "select * from " + TABLE_NAME + " where id = ?";
	private final String UPDATE_USER_INFO_MODIFY_SQL = "update " + TABLE_NAME + " set password=?,mTelecom=?,mobile=?,certifiCode=?,email=?,bYear=?,bMonth=?,bDay=?,postCode=?,address1=?,address2=?,detailAddress=?,gender=?,snsId=?,interesting=?,introduce=? , profile=? where id = ?";
	private final String CHECK_ID_EFFECTIVENESS_SQL = "select * from " + TABLE_NAME + " where id = ?";
	private final String CHECK_NICKNAME_EFFECTIVENESS_SQL = "select * from " + TABLE_NAME + " where nickName = ?";
	private final String DELETE_USER_SQL = "delete from " + TABLE_NAME + " where id = ?";
	private final String TODAY_VISITED_GET_SQL = "insert into " + TODAY_TOTAL_TABLE_NAME + " (visited,ipAddress) values (?,?)"; 
	private final String SELECT_EQUALS_IP = "select ipAddress from " + TODAY_TOTAL_TABLE_NAME + " where ipAddress = ? and date_format(curdate( ), '%Y-%m-%d' ) = date_format(day, '%Y-%m-%d' )";
	
	private final String SELECT_USER_POINT_SQL = " select point from " + TABLE_NAME + " where id = ?";
	private final String LOST_MY_ID_SQL =" select id from " + TABLE_NAME + " where name=? and email= ?";
	private final String LOST_MY_PW_SQL = " select password from " + TABLE_NAME + " where name=? and email=? and id=?";
	
	private UserDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/"+CONNECTION_POOL_NAME);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return conn;
	}
	public void close(PreparedStatement pstmt, Connection conn, ResultSet rs) {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs!=null) rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public UserDTO getUserDTO(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = new UserDTO();
		try {
			pstmt = conn.prepareStatement(SELECT_USER_INFO_SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return dto;
	}
	public void createUserDAO(UserDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getNickName());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPassword());
			pstmt.setString(5, dto.getmTelecom());
			pstmt.setString(6, dto.getMobile());
			pstmt.setString(7, dto.getCertifiCode());
			pstmt.setString(8, dto.getEmail());
			pstmt.setString(9, dto.getbYear());
			pstmt.setString(10, dto.getbMonth());
			pstmt.setString(11, dto.getbDay());
			pstmt.setString(12, dto.getPostCode());
			pstmt.setString(13, dto.getAddress1());
			pstmt.setString(14, dto.getAddress2());
			pstmt.setString(15, dto.getDetailAddress());
			pstmt.setString(16, dto.getGender());
			pstmt.setString(17, dto.getSnsId());
			pstmt.setString(18, dto.getInteresting());
			pstmt.setString(19, dto.getIntroduce());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt, conn);
		}
	}
	public boolean getSessionDAO(UserDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(GET_SESSION_FOR_LOGIN_SQL);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				if(dto.getId().matches(rs.getString("id")) && dto.getPassword().matches(rs.getString("password")))
					flag = true;
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return flag;
	}
	public UserDTO mypageDAO(String id) {
		UserDTO dto = getUserDTO(id);
		return dto;
	}
	public void userModifyOK(UserDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//Command에서 point추가시 pstmt다시세팅.!!쿼리도!!!!!!!
			pstmt = conn.prepareStatement(UPDATE_USER_INFO_MODIFY_SQL); 
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getmTelecom());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getCertifiCode());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getbYear());
			pstmt.setString(7, dto.getbMonth());
			pstmt.setString(8, dto.getbDay());
			pstmt.setString(9, dto.getPostCode());
			pstmt.setString(10, dto.getAddress1());
			pstmt.setString(11, dto.getAddress2());
			pstmt.setString(12, dto.getDetailAddress());
			pstmt.setString(13, dto.getGender());
			pstmt.setString(14, dto.getSnsId());
			pstmt.setString(15, dto.getInteresting());
			pstmt.setString(16, dto.getIntroduce());
			pstmt.setString(17, dto.getProfile());
			pstmt.setString(18, dto.getId());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	public boolean chkIdEffectDAO(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(CHECK_ID_EFFECTIVENESS_SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}else {
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return flag;
		
	}
	public boolean chkNickNameEffectDAO(String nickName) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(CHECK_NICKNAME_EFFECTIVENESS_SQL);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}else {
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return flag;
		
	}
	public void deleteUserDAO(String id) {
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
			close(pstmt, conn);
		}
		
	}
	public void todayVisited(TotalVisitedDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(TODAY_VISITED_GET_SQL);
			pstmt.setInt(1, dto.getVisited());
			pstmt.setString(2, dto.getIpAddress());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	public boolean selectEqualsDAO(String getIP) {
		boolean flag = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_EQUALS_IP);
			pstmt.setString(1, getIP);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).matches(getIP)) {
					flag = true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int getPoint(String id) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs= null;
		int point =0;
		try {
			pstmt = conn.prepareStatement(SELECT_USER_POINT_SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return point;
	}
	public String getUserIDDAO(String name, String email) {
		String id = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(LOST_MY_ID_SQL);
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public String getUserPWDAO(String name, String email, String id) {
		String pw = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(LOST_MY_PW_SQL);
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			pstmt.setString(3,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pw;
	}
}
