package com.taketo.www.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taketo.www.DTO.ContactDTO;

public class ContactDAO {
	private static ContactDAO contactDAO = new ContactDAO();
	public static ContactDAO getContactDAO() {
		return contactDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="contact";
	private final String UPDATE_CONTACT_INFO_SQL = "update "+ TABLE_NAME + " set officeInfoTitle = ? ,officeInfo =? ,contactHost=?,contactAddress=?,contactTelefon=?, contactMobile=?,contactEmail=?";
	private final String SELECT_CONTACT_INFO_SQL = "select * from " + TABLE_NAME;
	private final String UPDATE_TERMUSER_SQL = "update " + TABLE_NAME + " set termsOfUse = ? , personalInfo = ?";
	private ContactDAO() {
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
	public void updateContactDAO(ContactDTO dto) {
		Connection conn = getConnection();
		int result= 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE_CONTACT_INFO_SQL);
			pstmt.setString(1, dto.getOfficeInfoTitle());
			pstmt.setString(2, dto.getOfficeInfo());
			pstmt.setString(3, dto.getContactHost());
			pstmt.setString(4, dto.getContactAddress());
			pstmt.setString(5, dto.getContactTelefon());
			pstmt.setString(6, dto.getContactMobile());
			pstmt.setString(7, dto.getContactEmail());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public void updateTermUserForm(ContactDTO dto) {
		Connection conn = getConnection();
		int result=0;
		PreparedStatement pstmt = null;
		try {
//			termsOfUse = ? , personalInfo = ?";
			pstmt = conn.prepareStatement(UPDATE_TERMUSER_SQL);
			pstmt.setString(1, dto.getTermsOfUse());
			pstmt.setString(2, dto.getPersonalInfo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ContactDTO selectContactInfo() {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		ContactDTO dto = new ContactDTO();
		try {
			pstmt = conn.prepareStatement(SELECT_CONTACT_INFO_SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setTermsOfUse(rs.getString("termsOfUse"));
				dto.setPersonalInfo(rs.getString("personalInfo"));
				dto.setOfficeInfoTitle(rs.getString("officeInfoTitle"));
				dto.setOfficeInfo(rs.getString("officeInfo"));
				dto.setContactTelefon(rs.getString("contactTelefon"));
				dto.setContactMobile(rs.getString("contactMobile"));
				dto.setContactHost(rs.getString("contactHost"));
				dto.setContactEmail(rs.getString("contactEmail"));
				dto.setContactAddress(rs.getString("contactAddress"));
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
}
