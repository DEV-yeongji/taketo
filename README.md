# Take to [저장소 바로가기](https://github.com/DEV-yeongji/taketo)   
해당프로젝트는 JSP MVC 2 방식으로 구현한 웹 프로젝트 입니다.
  - 테마, 지역 , 리뷰 게시판 등 해당 장소를 공유하고 목적에 맞게끔 서칭할 수 있습니다.
  - 소상공인들의 홍보를 위한 제휴를 맺고, 포인트를 통해 (상인-유저) 1석2조의 효과를 얻고자 했습니다. 
  - 회원 및 사이트 수정 및 관리, 제휴매장 관리 등 다양한 관리자 모드로 유연한 관리를 제공했습니다. 

---------------------------------------------------
##### 실행환경 및 버전 
  - window 7 / window 10 ( x64 ) , browser ( chrome )
  - MYSQL ver.8.0.22 
  - JDK ver.15.0.1
  - Apache Tomcat ver.9.0   
  - 구동전 필요한 데이터 및 설치파일
    -  [SQL 쿼리](https://github.com/DEV-yeongji/taketo/tree/master/SQL%20%EC%BF%BC%EB%A6%AC%EB%AC%B8)
    -  [지역 DATA](https://github.com/DEV-yeongji/taketo/blob/master/project_yeongji_final/WebContent/%EC%A7%80%EC%97%AD%ED%85%8C%EC%9D%B4%EB%B8%94%20%EB%8D%B0%EC%9D%B4%ED%84%B0.txt) , [사이트관리 DATA](https://github.com/DEV-yeongji/taketo/blob/master/project_yeongji_final/WebContent/%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%86%8C%EA%B0%9C%20%EB%8D%B0%EC%9D%B4%ED%84%B0.txt) , [관리자 DATA](https://github.com/DEV-yeongji/taketo/blob/master/project_yeongji_final/WebContent/%EA%B4%80%EB%A6%AC%EC%9E%90%20%EB%8D%B0%EC%9D%B4%ED%84%B0.txt)
    -  [카카오 지도 API key 발급](https://accounts.kakao.com/login?continue=https%3A%2F%2Fdevelopers.kakao.com%2Flogin%3Fcontinue%3D%252Fconsole%252Fapp)
    -  [contextFile](https://github.com/DEV-yeongji/taketo/commit/d9f07e9e99472c88f8ca03e797a49e97acab4016)
    -  [Cos.jar](http://www.servlets.com/cos/) ,  [Jstl.jar](https://tomcat.apache.org/download-taglibs.cgi) , [Connector J](https://dev.mysql.com/downloads/connector/j/8.0.html)

>   쿼리문을 MYSQL에 생성하시고 링크에 기재된 Data 파일을 insert해주세요 !       
>   보안을 위해 contextFile과 API key는 비공개 처리 했습니다. 가이드 설명을 보고 맞게 넣어주세요.                   
>   해당 프로젝트는 JDBC 및 COS , JSTL JAR를 사용하였습니다.                                      
>   ...메인 페이지 접속은 "/index.do" 로 부탁드립니다. :)                           

### Tech 1 (메인페이지 ~ 회원가입)
 
+ 메인페이지에서는 관리자모드에서 등록한 배너이미지를 출력합니다.
+ 깔끔한 메인 페이지를 위해 Toggle 메뉴를 구현 하였습니다.
+ 개인정보 처리방침 및 이용약관은 관리자모드에서 수정/등록이 가능합니다.
+ DB의 정보를 읽어와서 아이디 및 닉네임 중복검사 , 비밀번호 유효성 검사를 실행합니다.
+ 입력한 정보를 바탕으로 아이디/비밀번호 찾기가 가능합니다.

```sh
//아이디 중복검사
public boolean chkIdEffectDAO(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(CHECK_ID_EFFECTIVENESS_SQL);
			//CHECK_ID_EFFECTIVENESS_SQL = "select * from " + TABLE_NAME + " where id = ?";
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
//아이디 찾기
public String getUserIDDAO(String name, String email) {
		String id = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(LOST_MY_ID_SQL);
			//LOST_MY_ID_SQL = " select id from " + TABLE_NAME + " where name=? and email= ?";
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
```

### Execution result
> ![join](https://user-images.githubusercontent.com/76148671/103000771-060af580-456f-11eb-979c-1fb1c94cd7d3.gif)

> ![find](https://user-images.githubusercontent.com/76148671/103000803-17ec9880-456f-11eb-8b2a-0f7b33f8292a.gif)

### Tech 2 ( 마이페이지 )
 
+ 마이페이지에선 프로필 사진 및 정보를 변경할 수 있습니다.
+ confirm 메세지를 통해 회원탈퇴를 결정 할 수 있습니다.

```sh
//프로필 사진 업로드
String path= request.getSession().getServletContext().getRealPath("profile");
		int size =1024*1024*10; 
		String profile = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String str = (String)files.nextElement();
				profile = multi.getFilesystemName(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		.
        .
        if(profile != null) 
			dto.setProfile(profile);
		else
			dto.setProfile(multi.getParameter("beforeProfile"));
		//프로필 사진 변경 없을시 기존 이미지로 세팅한다.
		dto.setIntroduce(multi.getParameter("introduce"));
		dao.userModifyOK(dto);
```
### Execution result
> ![mypage](https://user-images.githubusercontent.com/76148671/103000838-28047800-456f-11eb-8139-c767f44c7090.gif)

> ![exit](https://user-images.githubusercontent.com/76148671/103000821-1de27980-456f-11eb-88d6-5a5c4fa49a94.gif)
### Tech 3 ( 테마게시판 )
+ 로그인 한 유저만 게시글 작성이 가능합니다. (뷰 페이지는 접근이 가능합니다.)
+ 본문에 설정한 해쉬태그 및 테마별 로 검색이 가능하며 페이징 처리 기능이 있습니다.
+ 사진업로드를 구현하여 게시물 클릭시 해당 사진이 출력됩니다.
+ 작성자 정보를 볼 수 있으며 작성날짜, 조회수 표기가 됩니다.
+ 카카오 지도API를 이용하여 해당 주소에 맞는 지도뷰가 출력됩니다.
+ 댓글은 작성자만 수정 / 삭제가 가능합니다.
+ 게시물은 해당 작성자만 수정 / 삭제가 가능합니다.

```sh
//글쓴사람 아이디를 통해 프로필사진 가져오기
	String getProfile = dao.getProfileImg(request.getParameter("id"));
	request.setAttribute("getProfile", getProfile);
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
//조회수 업데이트
public void updateHitBoard(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_THEMEBOARD_HIT_SQL);
            //UPDATE_THEMEBOARD_HIT_SQL= "update "+TABLE_NAME+ " set hit = hit+1 where no = ?";
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(conn, pstmt);
	}
```
### Execution result
> ![theme view](https://user-images.githubusercontent.com/76148671/103002306-02c53900-4572-11eb-866b-af694a3a7bf1.gif)

>![theme w](https://user-images.githubusercontent.com/76148671/103002333-0eb0fb00-4572-11eb-8853-45b628202c86.gif)

### Tech 4 ( 지역게시판 )
+ 로그인 한 유저만 게시글 작성이 가능합니다. (뷰 페이지는 접근이 가능합니다.)
+ 게시물 작성시 설정한 지역으로 검색이 가능하며 페이징 처리 기능이 있습니다.
+ 사진업로드를 구현하여 게시물 클릭시 해당 사진이 출력됩니다.
+ 작성자 정보를 볼 수 있으며 작성날짜, 조회수 표기가 됩니다.
+ 카카오 지도API를 이용하여 해당 주소에 맞는 지도뷰가 출력됩니다.
+ 지역게시판 메인페이지에 관리자가 승인한 제휴매장 배너가 출력됩니다.
+ 게시물은 해당 작성자만 수정 / 삭제가 가능합니다.

```sh
//제휴매장 배너 가져오기
public ArrayList<AllianceBannerDTO> allianceBanneList() {
		ArrayList<AllianceBannerDTO> allianceBList = new ArrayList<AllianceBannerDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALLIANCE_BANNER_SQL);
            // SELECT_ALLIANCE_BANNER_SQL = 
            // "select * from " + ALLIANCE_BANNER_TABLE_NAME + " order by no desc";
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
	AllianceDAO allianceDAO = AllianceDAO.getAllianceDAO();
	ArrayList<AllianceBannerDTO> bannerDTO = allianceDAO.allianceBanneList();
	request.setAttribute("bannerDTO", bannerDTO);
```
### Execution result
> ![local view](https://user-images.githubusercontent.com/76148671/103002327-0d7fce00-4572-11eb-99ff-8556d75ea489.gif)

> ![local w](https://user-images.githubusercontent.com/76148671/103002325-0bb60a80-4572-11eb-9bc4-515d79432a4f.gif)
### Tech 5 ( 리뷰게시판 ~ 공지/이벤트 )
+ 헤더부분을 통해 포인트 조회 및 리뷰작성이 가능 합니다.
+ 관리자만 공지/이벤트 게시판 작성이 가능합니다. (일반유저는 뷰페이지만 조회 가능합니다.)
+ 공지/이벤트 게시판은 분류별 검색이 가능하며 페이징 처리 기능이 있습니다.
+ 공지/이벤트 게시판에 등록된 사진이 있을시 카메라 아이콘이 표시 됩니다.
+ 리뷰게시판 작성자 정보를 볼 수 있으며 작성날짜, 조회수 표기가 됩니다.
+ 게시물은 해당 작성자만 수정 / 삭제가 가능합니다.
### Execution result
> ![review](https://user-images.githubusercontent.com/76148671/103002350-140e4580-4572-11eb-813d-812cd76a7931.gif)

### Tech 6 ( 회사 , 사이트 소개 )
+ 관리자가 등록한 사이트 정보를 출력합니다.
+ 카카오 지도API를 이용하여 등록한 회사 주소에 맞는 지도뷰가 출력됩니다.
+ 비로그인 유저도 제휴매장 신청이 가능합니다.

```sh
//제휴매장 등록하기
public void AllianceOK(AllianceDTO dto) {
		Connection conn = getConnection();
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_ALLIANCE_SQL);
		  //INSERT_ALLIANCE_SQL =
		  //"insert into" +TABLE_NAME+ "(storeName,local,mobile,askForm,email)values(?,?,?,?,?)";
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
```
### Execution result
> ![site](https://user-images.githubusercontent.com/76148671/103002355-153f7280-4572-11eb-925a-e401d48f7d20.gif)

### Tech 7 ( 관리자 메인화면 ~ 회원관리 )
+ 제휴요청 매장이 있을때 알람을 출력합니다.
+ ( 방문데이터가 있을시 ) 최근 10일간의 방문자 수를 출력합니다.
+ 최근 작성된 5개의 테마게시물 정보를 출력합니다.
+ 최근 가입한 회원정보를 출력합니다. 페이징 기능이 있습니다.
+ 포인트 수정이 가능하며, 회원추방제도 기능이 있습니다.

```sh
//방문자 수 카운터
public class UserSessionGetListener implements HttpSessionListener{
	int sessionCnt;
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		// 세션발생시 이벤트 실행
		InetAddress ip;
		String getIP="";
			try {
				ip = InetAddress.getLocalHost();
				String ipAddress = ip.getHostAddress();
				System.out.print("ipaddress :" + ipAddress);
				getIP = ipAddress;
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		HttpSession session = sessionEvent.getSession();
		UserDAO dao = UserDAO.getUserDAO();
		
		boolean flag = dao.selectEqualsDAO(getIP);
		//아이피 다른것만 추가시키기 flag로 관리한다.
		if(!flag) {
			++sessionCnt;
			TotalVisitedDTO dto = new TotalVisitedDTO();
			dto.setIpAddress(getIP); 
			dto.setVisited(sessionCnt);
			dao.todayVisited(dto);
		}
		System.out.print("오늘 총 방문횟수 " + sessionCnt);
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
```
### Execution result
> ![admin main](https://user-images.githubusercontent.com/76148671/103003590-63557580-4574-11eb-8624-223daef701c3.gif)

### Tech 8 ( 사이트편집 )
+ 수정 및 등록 된 내용은 유저사이트에 실시간으로 반영이 됩니다.
+ 현재 등록된 메인배너 이미지를 확인하고 변경이 가능합니다.
+ 이용약관 및 개인정보 처리방침을 수정 할 수 있습니다.
+ 사이트 소개 및 회사정보를 수정 할 수 있습니다. 

```sh
//배너이미지 등록하기
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getAdminDAO();
		EditSiteBannerDTO dto = new EditSiteBannerDTO();
		String path= request.getSession().getServletContext().getRealPath("bannerImg");
		int size =1024*1024*10; 
		String bannerImg1 = "";
		String bannerImg2 = "";
		String bannerImg3 = "";
		String bannerImg4 = "";
		String bannerImg5 = "";
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		try {
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String img1 = (String)files.nextElement();
				bannerImg1 = multi.getFilesystemName(img1);
				String img2 = (String)files.nextElement();
				bannerImg2 = multi.getFilesystemName(img2);
				String img3 = (String)files.nextElement();
				bannerImg3 = multi.getFilesystemName(img3);
				String img4 = (String)files.nextElement();
				bannerImg4 = multi.getFilesystemName(img4);
				String img5 = (String)files.nextElement();
				bannerImg5 = multi.getFilesystemName(img5);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		.
		.
		.
	}
```
### Execution result
> ![admin site](https://user-images.githubusercontent.com/76148671/103003585-618bb200-4574-11eb-8f70-355663c2c858.gif)
### Tech 9 ( 게시판 관리 )
+ 수정 및 등록 된 내용은 유저사이트에 실시간으로 반영이 됩니다.
+ 현재 등록된 각 게시물들을 출력합니다. 페이징 기능이 있습니다.
+ 해당 게시물을 확인 할 수 있으며 , 삭제처리가 가능합니다. 
+ 리뷰게시판을 확인 후 작성유저에게 포인트 지급이 가능합니다.

### Execution result
> ![admin board](https://user-images.githubusercontent.com/76148671/103003594-651f3900-4574-11eb-8a6c-6b7d0699d187.gif)

### Tech 10 ( 공지사항 / 이벤트 게시판 )
+ 수정 및 등록 된 내용은 유저사이트에 실시간으로 반영이 됩니다.
+ 분류별로 검색이 가능합니다. 페이징 기능이 있습니다.
+ 사진이 포함된 게시판이면 카메라 아이콘이 표시됩니다.
+ 기간설정을 통해 해당 기간까지만 출력 됩니다.
+ 사용자 와 관리자의 뷰페이지의 디자인을 다르게 했습니다.
+ 게시물  수정 / 삭제가 가능합니다.

```sh
// 공지사항·이벤트 게시판 기간설정
public ArrayList<NoticeDTO> noticeListDAO(int curPage) {
		ArrayList<NoticeDTO> notice = new ArrayList<NoticeDTO>();
		Connection conn = getConnection();
		ResultSet rs= null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_NOTICE_LIST_SQL);
			//SELECT_NOTICE_LIST_SQL =
			//" select * from " + TABLE_NAME + " where term > now() order by no desc limit ?,?";
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
```
### Execution result
> ![admin notice](https://user-images.githubusercontent.com/76148671/103003593-6486a280-4574-11eb-947a-45dbe4ea298d.gif)

### Tech 11 ( 제휴업체 관리 )
+ 수정 및 등록 된 내용은 유저사이트에 실시간으로 반영이 됩니다.
+ 읽지않은 제휴요청이 있으면 메세지를 띄어줍니다.
+ 게시물을 누르면 읽음처리가 되며 , 수락 / 거부 요청을 할 수 있습니다.
+ 현재 등록한 제휴업체 배너이미지를 출력합니다.
+ 매장명 / 매장소개 정보를 입력하고 배너이미지를 등록·삭제 합니다.
+ 유저사이트 지역게시판에서 확인 할 수 있습니다.

```sh
// 제휴요청 미확인시 알림기능
public int noReadAllianceDAO() {
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int readNotice = 0;
		try {
			pstmt = conn.prepareStatement(SELECT_NO_READ_ALLIANCE_SQL);
			//SELECT_NO_READ_ALLIANCE_SQL =
			// " select count(*) from " + TABLE_NAME + " where readNotice = 0";
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
    <script>
		var readNotice = document.getElementById('readNotice').value;
		if(readNotice>0){
			alert(readNotice + '건의 읽지않은 제휴 요청이 있습니다.');
		}
	</script>
// 제휴요청 확인시 상태 업데이트
public void readNoticeCnt(int no) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE_READSTATE_SQL);
			// UPDATE_READSTATE_SQL = "update " + TABLE_NAME + " set readNotice = 1 where no =?";
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
```
### Execution result
> ![alliance admin](https://user-images.githubusercontent.com/76148671/103003591-63557580-4574-11eb-9962-9a7902d2a886.gif)

### 사용한 API 및 참고한 사이트

| API · SITE | Description |
| ------ | ------ |
| [ 카카오 지도 API ](https://accounts.kakao.com/login?continue=https%3A%2F%2Fdevelopers.kakao.com%2Flogin%3Fcontinue%3D%252Fconsole%252Fapp) | 해당 주소에 맞는 지도구현을 위해 이용 했습니다. |
|[다음 주소 API ](http://postcode.map.daum.net/guide) | 주소 검색시 이용 했습니다. |
| [전국 시군구 데이터 ](https://m.blog.naver.com/PostView.nhn?blogId=youstar8066&logNo=221342262967&proxyReferer=https:%2F%2Fwww.google.com%2F) | 시·군·구 데이터를 얻기위해 참고 했습니다. |
| [데이트팝](http://datepop.co.kr/view/) | 사이트 기능 구성을 참고 했습니다. |
| [대한민국구석구석](https://korean.visitkorea.or.kr/main/main.do#home) | 사이트 디자인구성을 참고 했습니다. |



### Development & Realization
현재 ver.2 로 자잘한 오류 및 디자인 업데이트 중 입니다.
> 프로젝트를 하면서 느낀 점과 부족했던 점....?
> > 시·군·구 데이터가 필요해서 관련 api를 찾아보다가 스킬이 부족해서 파싱하지 않고 중복제거를 통해 데이터를 삽입 했습니다.                      
> > 시간이 된다면 , 그런 부분을 좀 더 업그레이드 하고 싶단 생각이 들었습니다..                              
> > 또한, 첫 프로젝트를 성공시키고 나서 내 손으로 사이트를 다시한번 만들었던 점이 놀랍고 뿌듯했습니다.                 
> > 스킬업을 해서 다양한 프로젝트를 만들고 싶단 생각이 들었습니다.


**Free Software, Hell Yeah!**
