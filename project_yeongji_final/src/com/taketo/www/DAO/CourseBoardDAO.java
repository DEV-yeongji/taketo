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
import com.taketo.www.DTO.CourseBoardDTO;
import com.taketo.www.DTO.UserDTO;

public class CourseBoardDAO {
	private static CourseBoardDAO courseBoardDAO = new CourseBoardDAO();
	public static CourseBoardDAO getCourseBoardDAO() {
		return courseBoardDAO;
	}
	DataSource dataSource;
	private int numOfPage = 6;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="localBoard";
	private final String USER_TABLE ="user";
	private final String INSERT_INTO_COURSE_TABLE_SQL = "insert into "+ TABLE_NAME + " (nickName,id,title,location_Name,location_contents,location_img1,location_img2,location_img3,location_img4,whyRecom,city,place,location_address)"
														+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String SELECT_COURSEBOARD_LIST_SQL = " select * from " + TABLE_NAME + " order by no desc limit ?, ?";
	private final String SELECT_COURSEBOARD_SEARCH_LIST = " select * from " + TABLE_NAME + " where city=? order by no desc limit ?,?";
	private final String UPDATE_BOARD_HIT_SQL = " update " + TABLE_NAME + " set hit = hit+1 where no =?"; 
	private final String SELECT_COURSEBOARD_WHERE_NO = " select * from " + TABLE_NAME + " where no =?";
	private final String SELECT_USER_PROFILE = "select profile from " + USER_TABLE + " where id = ?";
	private final String SELECT_COURSEBOARD_WRITE_CNT = " select count(*) from " + TABLE_NAME + " where id=?";
	private final String DELETE_COURSEBOARD_SQL = " delete from " + TABLE_NAME + " where no =?"; 
	private final String MODIFY_COURSEBOARD_SQL = " update " + TABLE_NAME + " set wtime=now(), title=?,location_Name=?,location_contents=?,location_img1=?,location_img2=?,location_img3=?,location_img4=?,whyRecom=?,location_address=? where no =?";
	private final String SELECT_LOCALBOARD_CNT = " select count(*) from " + TABLE_NAME;
	private final String SELECT_LOCALBOARD_CNT_SEARCH_CITY = " select count(*) from " + TABLE_NAME + " where  city=? ";
	private final String SELECT_USER_INTRODUCE = "select introduce from " + USER_TABLE + " where id = ?";
	private final String SELECT_USER_INTERESTING = "select interesting from " + USER_TABLE + " where id = ?";
	private CourseBoardDAO() {
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
	public void courseBoardWriteOKDAO(CourseBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt =conn.prepareStatement(INSERT_INTO_COURSE_TABLE_SQL);
			pstmt.setString(1, dto.getNickName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getLocation_Name());
			pstmt.setString(5, dto.getLocation_contents());
			pstmt.setString(6, dto.getLocation_img1());
			pstmt.setString(7, dto.getLocation_img2());
			pstmt.setString(8, dto.getLocation_img3());
			pstmt.setString(9, dto.getLocation_img4());
			pstmt.setString(10, dto.getWhyRecom());
			pstmt.setString(11, dto.getCity());
			pstmt.setString(12, dto.getPlace());
			pstmt.setString(13, dto.getLocation_address());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public ArrayList<CourseBoardDTO> courseListDAO(int curPage) {
		ArrayList<CourseBoardDTO> list= new ArrayList<CourseBoardDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_COURSEBOARD_LIST_SQL);
			pstmt.setInt(1, curPage * numOfPage);
			pstmt.setInt(2, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CourseBoardDTO dto = new CourseBoardDTO();
				dto.setCity(rs.getString("city"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setLocation_contents(rs.getString("location_contents"));
				dto.setLocation_img1(rs.getString("location_img1"));
				dto.setLocation_img2(rs.getString("location_img2"));
				dto.setLocation_img3(rs.getString("location_img3"));
				dto.setLocation_img4(rs.getString("location_img4"));
				dto.setLocation_Name(rs.getString("location_name"));
				dto.setWhyRecom(rs.getString("whyRecom"));
				dto.setNickName(rs.getString("nickName"));
				dto.setNo(rs.getInt("no"));
				dto.setPlace(rs.getString("place"));
				dto.setTitle(rs.getString("title"));
				dto.setWtime(rs.getString("wtime"));
				dto.setLocation_address(rs.getString("location_address"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public ArrayList<CourseBoardDTO> courseListSearch(String city,int curPage) {
		ArrayList<CourseBoardDTO> list = new ArrayList<CourseBoardDTO>();
		Connection conn =getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_COURSEBOARD_SEARCH_LIST);
			pstmt.setString(1, city);
			pstmt.setInt(2, curPage * numOfPage);
			pstmt.setInt(3, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CourseBoardDTO dto = new CourseBoardDTO();
				dto.setCity(rs.getString("city"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setLocation_contents(rs.getString("location_contents"));
				dto.setLocation_img1(rs.getString("location_img1"));
				dto.setLocation_img2(rs.getString("location_img2"));
				dto.setLocation_img3(rs.getString("location_img3"));
				dto.setLocation_img4(rs.getString("location_img4"));
				dto.setLocation_Name(rs.getString("location_name"));
				dto.setWhyRecom(rs.getString("whyRecom"));
				dto.setNickName(rs.getString("nickName"));
				dto.setNo(rs.getInt("no"));
				dto.setPlace(rs.getString("place"));
				dto.setTitle(rs.getString("title"));
				dto.setWtime(rs.getString("wtime"));
				dto.setLocation_address(rs.getString("location_address"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public void updateBoardHit(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_BOARD_HIT_SQL);
			pstmt.setInt(1, no);
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public CourseBoardDTO getCourseBoardDTO(int no) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		Connection conn = getConnection();
		CourseBoardDTO dto = new CourseBoardDTO();
		try {
			pstmt = conn.prepareStatement(SELECT_COURSEBOARD_WHERE_NO);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setCity(rs.getString("city"));
				dto.setHit(rs.getInt("hit"));
				dto.setId(rs.getString("id"));
				dto.setLocation_contents(rs.getString("location_contents"));
				dto.setLocation_img1(rs.getString("location_img1"));
				dto.setLocation_img2(rs.getString("location_img2"));
				dto.setLocation_img3(rs.getString("location_img3"));
				dto.setLocation_img4(rs.getString("location_img4"));
				dto.setLocation_Name(rs.getString("location_name"));
				dto.setWhyRecom(rs.getString("whyRecom"));
				dto.setNickName(rs.getString("nickName"));
				dto.setNo(rs.getInt("no"));
				dto.setPlace(rs.getString("place"));
				dto.setTitle(rs.getString("title"));
				dto.setWtime(rs.getString("wtime"));
				dto.setLocation_address(rs.getString("location_address"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
		
	}
	public CourseBoardDTO courseBoardViewDAO(int no) {
		updateBoardHit(no);
		CourseBoardDTO dto = getCourseBoardDTO(no);
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
	public int courseBoardWriteCNT(String id) {
		int cnt = 0;
		Connection conn =getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_COURSEBOARD_WRITE_CNT);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return cnt;
	}
	public void courseBoardDeleteOKDAO(int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		try {
			pstmt = conn.prepareStatement(DELETE_COURSEBOARD_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public void courseBoardModifyOKDAO(CourseBoardDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(MODIFY_COURSEBOARD_SQL);
//			 title=?,location_Name=?,location_contents=?,location_img1=?,location_img2=?,
			//location_img3=?,location_img4=?,whyRecom=?,location_address where no =?";
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getLocation_Name());
			pstmt.setString(3, dto.getLocation_contents());
			pstmt.setString(4, dto.getLocation_img1());
			pstmt.setString(5, dto.getLocation_img2());
			pstmt.setString(6, dto.getLocation_img3());
			pstmt.setString(7, dto.getLocation_img4());
			pstmt.setString(8, dto.getWhyRecom());
			pstmt.setString(9, dto.getLocation_address());
			pstmt.setInt(10, dto.getNo());
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
			pstmt = conn.prepareStatement(SELECT_LOCALBOARD_CNT);
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
	public int getSearchCnt(String city) {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_LOCALBOARD_CNT_SEARCH_CITY);
			pstmt.setString(1, city);
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
