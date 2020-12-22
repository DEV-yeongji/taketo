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
<link rel="stylesheet" href="./css/reviewBoard.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to. - 리뷰게시판</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<section class="reviewBoard">
			<table class="reviewBoardList">
				<tr>
					<td>&nbsp;</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성날짜</td>
				</tr>
				<c:forEach items="${list }" var="list">
					<tr>
						<td>${list.no }</td>
						<td><a href="ReviewBoardView.do?no=${list.no }&&id=${list.id}">${list.title }</a></td>
						<td>${list.nickName }</td>
						<td>${list.hit }</td>
						<td>${list.wtime }</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.id ne null }">
				<a href="ReviewBoardWrite.do?id=${sessionScope.id }" class="write">리뷰 작성하기 <i class="fas fa-pencil-alt"></i></a>
			</c:if>
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
					<a href="reviewBoard.do?curPage=${i }">${i+1 }</a>
				</c:if>
			</c:forEach>
			</div>
	</section>
	
</body>
</html>