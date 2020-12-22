<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Take to.</title>
<link rel="stylesheet" href="css/headerStyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<body>
	<header class="headerInclude">
		<ul>
			<li><a href="index.do" class="logo">Take to.</a></li>
			<li><a href="ThemeBoard.do"><i class="fas fa-star"></i>&nbsp;테마별</a></li>
			<li><a href="localcourse.do"><i class="fas fa-map-marked-alt"></i>&nbsp;지역별</a></li>
			<li><a href="reviewBoard.do"><i class="fas fa-comment"></i>&nbsp;리뷰게시판</a></li>
			<li><a href="userSeeNoticeBoard.do"><i class="fas fa-exclamation-circle"></i>&nbsp;공지사항/이벤트</a></li>
		</ul>
		<div class="userInfoQuick">
			<input type="checkbox" id="userInfoQuick">
			<label for="userInfoQuick"><i class="fas fa-user "></label></i>
			<div class="userInfoCheck">
				<c:if test="${sessionScope.id eq null }">
					<p>원할한 서비스 이용을 위해</p>
					<p>로그인을 해주세요</p>
					<hr>
					<form method="post" action="loginOK.do">
					<table class="userInfoCheckTable">
						<tr>
							<td><label for="id">아이디</label></td>
							<td><input type="text" id="id" name="id"></td>
						</tr>
						<tr>
							<td><label for="password">비밀번호</label></td>
							<td><input type="password" id="password" name="password"></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="로그인">
							</td>
						</tr>
					</table>
					</form>
					<span><a href="TermsofUse.do">회원가입</a></span> |
					<span><a href="">아이디/비밀번호 찾기</a></span>
				</c:if>
				<c:if test="${sessionScope.id ne null }">
					<h2>${sessionScope.id } 님 안녕하세요!</h2>
					<hr>
					<h3>현재 포인트</h3>
						<span class="spanPoint">${sessionScope.point }</span>
					<br>
					<br>
					<a href="reviewBoard.do" class="goReview">리뷰쓰러가기 <i class="fas fa-pencil-alt"></i></a>
					<hr>
					<span><a href="mypage.do">마이페이지</a></span> |
					<span><a href="logout.do">로그아웃</a></span>
				</c:if>
			</div>
		</div>
	</header>
</body>
</html>