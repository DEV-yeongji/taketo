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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/NoticeBoardForAdmin.css">
<title>Take to. - 공지사항 게시판</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<table class="noticeListForUser">
		<tr>
			<td>
				<form method="post" action="NoticeEventListForUserSearch.do">
					<select name="subject" class="noticeSubject">
						<option value="">전체</option>
						<option value="공지사항">공지사항</option>
						<option value="이벤트">이벤트</option>
					</select>
					<input type="submit" value="검색">
				</form>
			</td>
		</tr>
		<tr>
			<td>분류</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>작성일</td>
			
		</tr>
			<c:forEach items="${notice }" var="notice">			
		<tr>
			<c:if test="${notice.subject eq '공지사항' }">
				<td class="red">${notice.subject }</td>
			</c:if>			
			<c:if test="${notice.subject eq '이벤트' }">
				<td class="gold">${notice.subject }</td>
			</c:if>
			
			<td>
				<a href="NoticeBoardView.do?no=${notice.no }">${notice.title }</a>
				<c:if test="${notice.img ne null and notice.img ne ''}">
					<span class="camera"><i class="fas fa-camera"></i></span>
				</c:if>
			</td>
			<td>${notice.id eq 'admin' ? '관리자':''}</td>
			<td>${notice.hit }</td>
			<td>${notice.writeTime }</td>
		</tr>
		</c:forEach>
	</table>
	<div class="pageing">
	<c:set var="curPage" value="${param.curPage }"/>
			<c:if test="${curPage eq null }">
				<c:set var="curPage" value="0" />
			</c:if>
			<c:forEach var="i" begin="0" end="${maxPage }">
				<c:if test="${curPage eq i }">
					<span>${i +1}</span>
				</c:if>
				<c:if test="${curPage ne i }">
					<a href="userSeeNoticeBoard.do?curPage=${i }">${i+1 }</a>
				</c:if>
			</c:forEach>
	</div>
</body>
</html>