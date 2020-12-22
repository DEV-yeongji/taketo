<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/admin.css">
<title>Insert title here</title>
</head>
<body>
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

</body>
</html>