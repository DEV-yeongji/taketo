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

import com.taketo.www.DTO.LocalListDTO;

public class LocalListDAO {
	private static LocalListDAO localListDAO = new LocalListDAO();
	public static LocalListDAO getLocalListDAO() {
		return localListDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="localList";
	private final String SELECT_LOCAL_LIST_SQL = "select * from " + TABLE_NAME + " where city = ?";
	private final String SELECT_LOCAL_ALL_LIST_SQL = "select * from " + TABLE_NAME;
	private final String SELECT_LOCAL_LIST_CITY = "select distinct city from " + TABLE_NAME ;
	

	private LocalListDAO() {
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
	public ArrayList<LocalListDTO> selectLocalDAO(String city) {		//도시에 해당하는 시군구 가져오기
		ArrayList<LocalListDTO> list = new ArrayList<LocalListDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LOCAL_LIST_SQL);
			pstmt.setString(1, city);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LocalListDTO dto = new LocalListDTO();
				dto.setCity(rs.getString("city"));
				dto.setCounty(rs.getString("County"));
				dto.setDistrict(rs.getString("District"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public ArrayList<LocalListDTO> allLocalDAO() {		//모든 지역구와 시군구 가져오기
		ArrayList<LocalListDTO> list = new ArrayList<LocalListDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LOCAL_ALL_LIST_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LocalListDTO dto = new LocalListDTO();
				dto.setCity(rs.getString("city"));
				dto.setCounty(rs.getString("County"));
				dto.setDistrict(rs.getString("District"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public ArrayList<LocalListDTO> localListForCityDAO() {			//도시만가져오기
		ArrayList<LocalListDTO> city = new ArrayList<LocalListDTO>();
		Connection conn =getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LOCAL_LIST_CITY);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LocalListDTO dto = new LocalListDTO();
				dto.setCity(rs.getString("city"));
				city.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return city;
	}
}
