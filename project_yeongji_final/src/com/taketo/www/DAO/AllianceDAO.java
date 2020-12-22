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

import com.taketo.www.DTO.AllianceBannerDTO;
import com.taketo.www.DTO.AllianceDTO;

public class AllianceDAO {
	private static AllianceDAO allianceDAO = new AllianceDAO();
	public static AllianceDAO getAllianceDAO() {
		return allianceDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="alliance";
	private final String ALLIANCE_BANNER_TABLE_NAME = "allianceBanner";
	private final String INSERT_ALLIANCE_SQL = "insert into " + TABLE_NAME + " (storeName,local,mobile,askForm,email) values(?,?,?,?,?)";
	private final String SELCET_ALLIANCELIST_SQL = "select * from " + TABLE_NAME + " order by wtime desc"; 
	private final String SELCET_ALLIANCE_VIEW_SQL = "select * from " + TABLE_NAME + " where no = ?";
	private final String UPDATE_READSTATE_SQL = "update " + TABLE_NAME + " set readNotice = 1 where no =?";
	private final String UPDATE_STATE_SQL ="update " + TABLE_NAME +" set state = ? where no =?";
	private final String SELECT_NO_READ_ALLIANCE_SQL = " select count(*) from " + TABLE_NAME + " where readNotice = 0";
	private final String DELETE_ALLIANCE_SQL = " delete from " + TABLE_NAME + " where no = ?";
	
	private final String INSERT_ALLIANCEBANNER_SQL = "insert into " + ALLIANCE_BANNER_TABLE_NAME + " (allianceInfo,allianceName,allianceBanner) values(?,?,?)";
	private final String SELECT_ALLIANCE_BANNER_SQL = "select * from " + ALLIANCE_BANNER_TABLE_NAME + " order by no desc";
	private final String DELETE_ALLIANCEBANNER_SQL = " delete from " + ALLIANCE_BANNER_TABLE_NAME + " where no = ?";
	private AllianceDAO() {
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
	public void AllianceOK(AllianceDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_ALLIANCE_SQL);
			//(storeName,local,mobile,askForm,email)
			pstmt.setString(1, dto.getStoreName());
			pstmt.setString(2, dto.getLocal());
			pstmt.setString(3, dto.getMobile());
			pstmt.setString(4, dto.getAskForm());
			pstmt.setString(5, dto.getEmail());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public ArrayList<AllianceDTO> allianceList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AllianceDTO> list = new ArrayList<AllianceDTO>();
		try {
			pstmt = conn.prepareStatement(SELCET_ALLIANCELIST_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AllianceDTO dto = new AllianceDTO();
				dto.setAskForm(rs.getString("askForm"));
				dto.setEmail(rs.getString("email"));
				dto.setLocal(rs.getString("local"));
				dto.setMobile(rs.getString("mobile"));
				dto.setNo(rs.getInt("no"));
				dto.setStoreName(rs.getString("storeName"));
				dto.setWtime(rs.getString("wtime"));
				dto.setState(rs.getInt("state"));
				dto.setReadNotice(rs.getInt("readNotice"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public AllianceDTO allianceView(int no) {
		readNoticeCnt(no);
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		AllianceDTO dto = new AllianceDTO();
		try {
			pstmt = conn.prepareStatement(SELCET_ALLIANCE_VIEW_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setAskForm(rs.getString("askForm"));
				dto.setEmail(rs.getString("email"));
				dto.setLocal(rs.getString("local"));
				dto.setMobile(rs.getString("mobile"));
				dto.setNo(rs.getInt("no"));
				dto.setStoreName(rs.getString("storeName"));
				dto.setWtime(rs.getString("wtime"));
				dto.setState(rs.getInt("state"));
				dto.setReadNotice(rs.getInt("readNotice"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	public void readNoticeCnt(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_READSTATE_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public void allianceSuccess(int no, int state) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_STATE_SQL);
			pstmt.setInt(1, state);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public int noReadAllianceDAO() {
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int readNotice = 0;
		try {
			pstmt = conn.prepareStatement(SELECT_NO_READ_ALLIANCE_SQL);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				readNotice = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		return readNotice;
	}
	public void allianceDeleteOKDAO(int no) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_ALLIANCE_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(conn, pstmt);
	}
	public void allianceBannerOKDAO(AllianceBannerDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_ALLIANCEBANNER_SQL);
//			allianceInfo,allianceName,allianceBanner
			pstmt.setString(1, dto.getAllianceInfo());
			pstmt.setString(2, dto.getAllianceName());
			pstmt.setString(3, dto.getAllianceBanner());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<AllianceBannerDTO> allianceBanneList() {
		ArrayList<AllianceBannerDTO> allianceBList = new ArrayList<AllianceBannerDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALLIANCE_BANNER_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AllianceBannerDTO dto = new AllianceBannerDTO();
				dto.setNo(rs.getInt("no"));
				dto.setAllianceBanner(rs.getString("allianceBanner"));
				dto.setAllianceInfo(rs.getString("allianceInfo"));
				dto.setAllianceName(rs.getString("allianceName"));
				allianceBList.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return allianceBList;
	}
	public void allianceBannerDeletdDAO(int no) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_ALLIANCEBANNER_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
}
