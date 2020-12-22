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

import com.taketo.www.DTO.ThemeBoardDTO;
import com.taketo.www.DTO.UserDTO;

public class ThemeBoardDAO {
	private static ThemeBoardDAO themeBoardDAO = new ThemeBoardDAO();
	public static ThemeBoardDAO getThemeBoardDAO() {
		return themeBoardDAO;
	}
	DataSource dataSource;
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME ="mainBoard";
	private final String USER_TABLE = "user";
	private int numOfPage = 5;
	private final String INSERT_INTO_THEMEBOARD_WRITE_SQL = "insert into " + TABLE_NAME + " (title,id,nickName,contents,interesting,address1,address2,locationName,locationHoliday,locationPay,locationTime,hashtag,fileName1,fileName2,fileName3,fileName4) "
															+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String SELECT_THEMEBOARD_LIST_SQL = "select * from " + TABLE_NAME + " order by no desc limit ?,?";
	private final String SELECT_CNT_LIST = "select count(*) from " + TABLE_NAME;
	private final String UPDATE_THEMEBOARD_HIT_SQL = " update " + TABLE_NAME + " set hit = hit+1 where no = ?";
	private final String SELECT_THEMEBOARD_GET_NO_SQL = " select * from " + TABLE_NAME + " where no = ?";
	private final String SELECT_USER_PROFILE = " select profile from " + USER_TABLE + " where id = ?";
	private final String SELECT_USER_INTRODUCE = " select introduce from " + USER_TABLE + " where id = ?";
	private final String SELECT_USER_INTERESTING = " select interesting from " + USER_TABLE + " where id = ?";
	private final String DELETE_THEME_BOARD_SQL = " delete from " + TABLE_NAME + " where no = ?";
	private final String SEARCH_THEMEBOARD_SQL = " select * from " + TABLE_NAME + " where hashTag like ? order by no desc limit ?,?";
	private final String SEARCH_THEMEBOARD_INTERESTING_SQL = " select * from " + TABLE_NAME + " where interesting like ? order by no desc limit ?,?";
	private final String UPDATE_THEMEBOARD_MODIFY = "update " + TABLE_NAME + " set wtime=now(), title=?,contents=?,interesting=?,locationHoliday=?,locationPay=?,locationTime=?, hashTag=? where no =?";
	private final String SELECT_THEMEBOARD_CNT = "select count(*) from " + TABLE_NAME ; 
	private final String SELECT_THEMEBOARD_CNT_WHERE_HASHTAG = "select count(*) from " + TABLE_NAME + " where hashTag like ?" ; 
	private final String SELECT_THEMEBOARD_CNT_WHERE_CATEGORY = "select count(*) from " + TABLE_NAME + " where interesting like ?"; 
	private ThemeBoardDAO() {
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
	public int cntOfList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cntOfList=0;
		try {
			pstmt = conn.prepareStatement(SELECT_CNT_LIST);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cntOfList = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return cntOfList;
	}

	public ArrayList<ThemeBoardDTO> themeBoardListDAO(int curPage){
		ArrayList<ThemeBoardDTO> list = new ArrayList<ThemeBoardDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_THEMEBOARD_LIST_SQL);
			pstmt.setInt(1, curPage * numOfPage);
			pstmt.setInt(2, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ThemeBoardDTO dto = new ThemeBoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setContents(rs.getString("contents"));
				dto.setWtime(rs.getString("wtime"));
				dto.setHit(rs.getInt("hit"));
				dto.setInteresting(rs.getString("interesting"));
				dto.setFileName1(rs.getString("fileName1"));
				dto.setFileName2(rs.getString("fileName2"));
				dto.setFileName3(rs.getString("fileName3"));
				dto.setFileName4(rs.getString("fileName4"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setLocationName(rs.getString("locationName"));
				dto.setLocationHoliday(rs.getString("locationHoliday"));
				dto.setLocationPay(rs.getString("locationPay"));
				dto.setLocationTime(rs.getString("locationTime"));
				dto.setHashTag(rs.getString("hashTag"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public ThemeBoardDTO getThemeBoardDTO(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ThemeBoardDTO dto = new ThemeBoardDTO();
		try {	
			pstmt = conn.prepareStatement(SELECT_THEMEBOARD_GET_NO_SQL);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setContents(rs.getString("contents"));
				dto.setWtime(rs.getString("wtime"));
				dto.setHit(rs.getInt("hit"));
				dto.setInteresting(rs.getString("interesting"));
				dto.setFileName1(rs.getString("fileName1"));
				dto.setFileName2(rs.getString("fileName2"));
				dto.setFileName3(rs.getString("fileName3"));
				dto.setFileName4(rs.getString("fileName4"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setLocationName(rs.getString("locationName"));
				dto.setLocationHoliday(rs.getString("locationHoliday"));
				dto.setLocationPay(rs.getString("locationPay"));
				dto.setLocationTime(rs.getString("locationTime"));
				dto.setHashTag(rs.getString("hashTag"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
	}
	public void themeboardWriteDAO(ThemeBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(INSERT_INTO_THEMEBOARD_WRITE_SQL);
			//title,id,nickName,contents,interesting,address1,address2,locationName,locationHoliday,
			//locationPay,locationTime,hashtag
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getNickName());
			pstmt.setString(4, dto.getContents());
			pstmt.setString(5, dto.getInteresting());
			pstmt.setString(6, dto.getAddress1());
			pstmt.setString(7, dto.getAddress2());
			pstmt.setString(8, dto.getLocationName());
			pstmt.setString(9, dto.getLocationHoliday());
			pstmt.setString(10, dto.getLocationPay());
			pstmt.setString(11, dto.getLocationTime());
			pstmt.setString(12, dto.getHashTag());
			pstmt.setString(13, dto.getFileName1());
			pstmt.setString(14, dto.getFileName2());
			pstmt.setString(15, dto.getFileName3());
			pstmt.setString(16, dto.getFileName4());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	public void updateHitBoard(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_THEMEBOARD_HIT_SQL);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(conn, pstmt);
	}
	public ThemeBoardDTO themeBoardViewDAO(int no) {
		updateHitBoard(no);
		ThemeBoardDTO dto = getThemeBoardDTO(no);
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
	public void deleteThemeBoardDAO(int no) {
			Connection conn = getConnection();
			int result = 0;
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(DELETE_THEME_BOARD_SQL);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(conn, pstmt);
			}
	}
	public ArrayList<ThemeBoardDTO> getSearchResult(String search,int curPage) {
		ArrayList<ThemeBoardDTO> searchResult = new ArrayList<ThemeBoardDTO>();
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SEARCH_THEMEBOARD_SQL);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, curPage * numOfPage);
			pstmt.setInt(3, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ThemeBoardDTO dto = new ThemeBoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setContents(rs.getString("contents"));
				dto.setWtime(rs.getString("wtime"));
				dto.setHit(rs.getInt("hit"));
				dto.setInteresting(rs.getString("interesting"));
				dto.setFileName1(rs.getString("fileName1"));
				dto.setFileName2(rs.getString("fileName2"));
				dto.setFileName3(rs.getString("fileName3"));
				dto.setFileName4(rs.getString("fileName4"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setLocationName(rs.getString("locationName"));
				dto.setLocationHoliday(rs.getString("locationHoliday"));
				dto.setLocationPay(rs.getString("locationPay"));
				dto.setLocationTime(rs.getString("locationTime"));
				dto.setHashTag(rs.getString("hashTag"));
				searchResult.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			close(conn, pstmt, rs);
		}
		return searchResult;
	}
	public ThemeBoardDTO themeBoardModifyDAO(int no) {
		ThemeBoardDTO dto = getThemeBoardDTO(no);
		
		return dto;
	}
	public void themeBoardModifyOKDAO(ThemeBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_THEMEBOARD_MODIFY);
//			title=?,contents=?,interesting=?,locationHoliday=?,locationPay=?,locationTime=? where no =?
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setString(3, dto.getInteresting());
			pstmt.setString(4, dto.getLocationHoliday());
			pstmt.setString(5, dto.getLocationPay());
			pstmt.setString(6, dto.getLocationTime());
			pstmt.setString(7, dto.getHashTag());
			pstmt.setInt(8, dto.getNo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<ThemeBoardDTO> getSearchResultForInteresting(String interesting,int curPage) {
		ArrayList<ThemeBoardDTO> searchResult = new ArrayList<ThemeBoardDTO>();
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SEARCH_THEMEBOARD_INTERESTING_SQL);
			pstmt.setString(1, "%"+interesting+"%");
			pstmt.setInt(2, curPage * numOfPage);
			pstmt.setInt(3, numOfPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ThemeBoardDTO dto = new ThemeBoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setNickName(rs.getString("nickName"));
				dto.setContents(rs.getString("contents"));
				dto.setWtime(rs.getString("wtime"));
				dto.setHit(rs.getInt("hit"));
				dto.setInteresting(rs.getString("interesting"));
				dto.setFileName1(rs.getString("fileName1"));
				dto.setFileName2(rs.getString("fileName2"));
				dto.setFileName3(rs.getString("fileName3"));
				dto.setFileName4(rs.getString("fileName4"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setLocationName(rs.getString("locationName"));
				dto.setLocationHoliday(rs.getString("locationHoliday"));
				dto.setLocationPay(rs.getString("locationPay"));
				dto.setLocationTime(rs.getString("locationTime"));
				dto.setHashTag(rs.getString("hashTag"));
				searchResult.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			close(conn, pstmt, rs);
		}
		return searchResult;
	}
	public int getCnt() {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_THEMEBOARD_CNT);
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
	public int getSearchCnt(String search) {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_THEMEBOARD_CNT_WHERE_HASHTAG);
			pstmt.setString(1, "%"+search+"%");
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
	public int getInterestingSearchCnt(String interesting) {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_THEMEBOARD_CNT_WHERE_CATEGORY);
			pstmt.setString(1, "%"+interesting+"%");
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
