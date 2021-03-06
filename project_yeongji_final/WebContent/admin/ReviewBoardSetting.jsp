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
<title>Take to. - 관리자모드</title>
</head>
<body>
	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<h1>리뷰게시판 관리</h1>
	<table class="themeBoardSet" border="1">
		<tr>
			<td></td>
			<td>제목</td>
			<td>작성자아이디</td>
			<td>작성일</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${list }" var="list">
		<tr>
			<td>${list.no }</td>
			<td>${list.title }</td>
			<td>${list.id }</td>
			<td>${list.wtime }</td>
			<td>
				<a href="reviewBoardSetDeleteOK.admin?no=${list.no }">삭제</a> |
				<a href="ReviewBoardView.do?no=${list.no }&&id=${list.id}">게시물 확인</a>
			</td>
			<td>
				<a href="memberRegisterForAdmin.admin">포인트 지급</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="paging">
				<c:set var="curPage" value="${param.curPage }"/>
				<c:if test="${curPage eq null }">
					<c:set var="curPage" value="0" />
				</c:if>
				<c:forEach var="i" begin="0" end="${maxPage }">
					<c:if test="${curPage eq i }">
						<span>${i +1}</span>
					</c:if>
					<c:if test="${curPage ne i }">
						<a href="ReviewBoardSetting.admin?curPage=${i }">${i+1 }</a>
					</c:if>
				</c:forEach>
			</div>
</body>
</html>