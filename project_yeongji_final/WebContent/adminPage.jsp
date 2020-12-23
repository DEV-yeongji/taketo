<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/admin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to. - 관리자 모드</title>
</head>
<body>
<c:if test="${sessionScope.id ne null }">
	<c:if test="${readNotice > 0 }">
				<div class="readNotice">
					<p>${readNotice } 건의 읽지않은 제휴요청이 있습니다.</p>
				</div>
	</c:if>
	<header class="adminHeader">
		<ul class="adminMenu">
			<li><a href="adminPage.admin">홈</a>
			<li>
				<a href="">사이트편집</a>
				<ul class="subMenu">
					<li><a href="editBannerView.admin">배너 관리</a></li>
					<li><a href="termUserForm.admin">이용약관/개인정보방침 관리</a></li>
					<li><a href="editContact.admin">회사정보 관리</a></li>
				</ul>
			</li>
			<li><a href="memberRegisterForAdmin.admin">회원관리</a></li>
			<li>
				<a href="">유저게시판 관리</a>
				<ul class="subMenu2">
					<li><a href="ThemeBoardSetting.admin">테마별 게시판</a></li>
					<li><a href="LocalBoardSetting.admin">지역별 게시판</a></li>
					<li><a href="ReviewBoardSetting.admin">리뷰 게시판</a></li>
				</ul>
			</li>
			<li><a href="NoticeEventListAdmin.admin">공지사항/이벤트관리</a></li>
			<li><a href="allianceSetting.admin">제휴업체관리</a></li>
		</ul>
	</header>
	<section class="adminMain">
			<div class="loginOK">
				<h1>관리자님 환영합니다.</h1>
				<a href="adminLogOut.admin">로그아웃</a>
			</div>
		
		<div class="todayTotalVisited">
			<p>최근 10일간 총 방문자 수</p>
			
			<table>
			<!-- 어레이리스트에 담아서 뿌려주기 수정해야할 페이지 adminDAO의 메소드 AdiminMainPage 어레이리슽,설정 -->
			<c:forEach items="${totalVisited }" var="totalVisited">
				<tr>
					<td><span class="totalDay">일시 ${totalVisited.day } </span></td>
				</tr>
				<tr>
					<td>
						<c:forEach var="i" begin="1" end="${totalVisited.visited }" >
							<span class="visited">%nbsp;</span>
						</c:forEach>
						${totalVisited.visited }명
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="latestGetBoard">
			<p>최근테마게시물(최대 5건만 출력됩니다.)</p>
			<table>
				<c:forEach items="${latest_board }" var="board">
					<tr>
						<td><img src="img/${board.fileName1 }"></td>
						<td><span>${board.id }</span>님</td>
						<td><p class="title">${board.title }</p></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
		<!--                    로그인 안했을 때                            -->
		<c:if test="${sessionScope.id eq null }">
			<form method="post" action="adminLoginOK.admin">
				<table class="adminLogin">
					<tr>
						<td>관리자 계정 아이디</td>
						<td><input type="text" name="id"></td>
					</tr>
					<tr>
						<td>관리자 계정 패스워드</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan="2"> 
							<input type="submit" value="로그인">
						</td>
					</tr>
				</table>
			</form>	
		</c:if>
	</section>
</body>
</html>