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

import com.taketo.www.DTO.CommentDTO;

public class CommentDAO {
	private static CommentDAO commentDAO = new CommentDAO();
	public static CommentDAO getCommentDAO() {
		return commentDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="comment";
	private final String INSERT_COMMENT_SQL ="insert into "+ TABLE_NAME + " (no,writeUser,contents,writeUserID) values(?,?,?,?)"; 
	private final String SELECT_COMMENT_LIST = "select * from " + TABLE_NAME + " order by no desc";
	private final String MODIFY_COMMENT_SQL = "update " + TABLE_NAME + " set wtime=now(), contents=? where commentNo =?";
	private final String DELETE_COMMENT_SQL = " delete from " + TABLE_NAME + " where commentNo=?";
	private final String SELECT_COMMENT_CNT = " select count(*) "+ TABLE_NAME + "";

	private CommentDAO() {
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
	public void commentWriteOK(CommentDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(INSERT_COMMENT_SQL);
//			(boardNumber,writeUser,contents)
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getWriteUser());
			pstmt.setString(3, dto.getContents());
			pstmt.setString(4, dto.getWriteUserID());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<CommentDTO> commentListDAO() {
		Connection conn= getConnection();
		ArrayList<CommentDTO> comment = new ArrayList<CommentDTO>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_COMMENT_LIST);
			rs =pstmt.executeQuery();
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCommentNo(rs.getInt("commentNo"));
				dto.setNo(rs.getInt("no"));
				dto.setContents(rs.getString("contents"));
				dto.setWriteUser(rs.getString("writeUser"));
				dto.setWtime(rs.getString("wtime"));
				dto.setWriteUserId(rs.getString("writeUserID"));
				comment.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return comment;
	}
	public void commentModifyDAO(CommentDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(MODIFY_COMMENT_SQL);
			pstmt.setString(1, dto.getContents());
			pstmt.setInt(2, dto.getCommentNo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public void commentDeleteDAO(int commentNo) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_COMMENT_SQL);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	
}
