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
<title>Take to.</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<script>
	function toggleMenuOpen() {
		document.getElementById("toggleMenu").style="width:350px";
	}
	function toggleMenuClose() {
		document.getElementById("toggleMenu").setyle="width:0";
	}
	function lostInfo(){		//아이디 비밀번호 찾기
		window.open("lostInfo.jsp","lostInfo","width=680px,height=350");
	}
</script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<header id="mainHeadder">
		<span class="menuBar" onclick="toggleMenuOpen()"><i class="fas fa-bars fa-4x"></i></span>
		<div id="toggleMenu" class="toggleMenuNav">
			<a href="" class="closeBtn"> <i class="far fa-window-close"></i> </a>
			<a class="theme" href="ThemeBoard.do"><i class="fas fa-star"></i>&nbsp;&nbsp;테마별 안내</a>
			<a href="localcourse.do"><i class="fas fa-map-marked-alt"></i>&nbsp;&nbsp;지역별 안내</a>
			<span class="pixedMenu"><i class="fas fa-comment"></i>&nbsp;&nbsp;게시판</span>
			<ul>
				<a href="reviewBoard.do"><li>- 리뷰게시판</li></a>
				<a href="userSeeNoticeBoard.do"><li>- 공지/이벤트</li></a>
			</ul>
			<a href="mypage.do?id=${sessionScope.id}"><i class="fas fa-user-cog"></i>&nbsp;&nbsp;회원정보 수정</a>
			<a href="contactInfo.do"><i class="fas fa-building"></i>&nbsp;&nbsp;회사 및 사이트 소개</a>
		</div>
		<p> &nbsp당신이 원하는 곳으로</p>
		
		<h1 class="logo"><img src="img/logo.png" /></h1>
	</header>
	<section id="mainSection">
	<!-- 관리자가 등록한 배너 출력 (배너 스크립트 시작 ) -->
		<div id="imgSlider">
			<div id="indexImg">
				<ul>
					<li><img src="bannerImg/${banner.bannerImg1 }" /> </li>
					<li><img src="bannerImg/${banner.bannerImg2 }" /> </li>
					<li><img src="bannerImg/${banner.bannerImg3 }" /> </li>
					<li><img src="bannerImg/${banner.bannerImg4 }" /> </li>
					<li><img src="bannerImg/${banner.bannerImg5 }" /> </li>
				</ul>
			</div> 
		</div>

		<!-- 로그인 -->
		<div class="loginLayOut">
			<c:choose>
				<c:when test="${sessionScope.id eq null}">
				<form method="post" action="loginOK.do">
			<table class="indexForm">
				<tr>
					<td><label for="id">아이디</label></td>
					<td><input type="text" name="id" id="id"></td>
					<td><label for="password">비밀번호</label></td>
					<td><input type="password" name="password" id="password"></td>
					<td><button onclick="lostInfo()" class="findBtn">아이디 / 비밀번호 찾기</button></td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="submit" value="로그인">
						<a href="TermsofUse.do">회원가입</a>
					</td>
				</tr>
			</table>
			</form>
				</c:when>
				<c:otherwise>
				<table class="indexForm">
					<tr>
						<td>${sessionScope.id} 님 안녕하세요</td>
					</tr>
					<tr>
						<td><a href="logout.do">로그아웃</a></td>
					</tr>	
				</table>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
	<script type="text/javascript">
	$('#indexImg>ul>li').hide(); 
	$('#indexImg>ul>li:first-child').show(); 
	setInterval(function(){ 
		$('#indexImg>ul>li:first-child').fadeOut().next().fadeIn().end(1000).appendTo('#indexImg>ul'); },4500);

	</script>
</body>
</html>