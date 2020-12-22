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
<title>Take to. - 관리자모드</title>
<link rel="stylesheet" href="./css/admin.css">
<link rel="stylesheet" href="./css/NoticeBoardForAdmin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<body>
<jsp:include page="/adminHeader.jsp"></jsp:include>
	<h1>공지사항 / 이벤트 리스트 목록 관리</h1>
	<table class="noticeList" border="1">
		<tr>
			<td>
			<form method="post" action="NoticeEventListForAdminSearch.admin">
				<select name="subject">
					<option value="">전체</option>
					<option value="공지사항">공지사항</option>
					<option value="이벤트">이벤트</option>
				</select>
			</td>
			<td colspan="5">
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
			<td>&nbsp;&nbsp;</td>
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
				<a href="NoticeBoardView.admin?no=${notice.no }">${notice.title }</a>
				<c:if test="${notice.img ne null and notice.img ne ''}">
					<span class="camera"><i class="fas fa-camera"></i></span>
				</c:if>	
			</td>
			<td>${notice.id eq 'admin' ? '관리자':''}</td>
			<td>${notice.hit }</td>
			<td>${notice.writeTime }</td>
			<td><a href="NoticeBoardDelete.admin?no=${notice.no }">삭제</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<a href="noticeWrite.admin" class="btn">작성</a>
			</td>
	</table>
	<div class="paging">
	<c:set var="curPage" value="${param.curPage }"/>
			<c:if test="${curPage eq null }">
				<c:set var="curPage" value="0" />
			</c:if>
			<c:forEach var="i" begin="0" end="${maxPage }">
				<c:if test="${curPage eq i }">
					${i +1}
				</c:if>
				<c:if test="${curPage ne i }">
					<a href="NoticeEventListAdmin.admin?curPage=${i }">${i+1 }</a>
				</c:if>
			</c:forEach>
	</div>
</body>
</html>