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
<title>Take to.</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<section class="ReviewView">
	<div class="write_info">
    		<c:choose>
				<c:when test="${getProfile eq null or getProfile eq '' }">
					<p>
						<img src="./profile/profile.png" width="200" />
					</p>
				</c:when>
				<c:otherwise>
					<img src="./profile/${getProfile}" />
				</c:otherwise>
			</c:choose>
    		
    		<span>에디터</span> <b>${dto.nickName }</b>
    		<p>소개말 <b>${introduce }</b></p>
    		<p>관심항목 <b>${userInteresting }</b></p>
    		<p class="wtime">${dto.wtime }</p>
    </div>
    <form method="post" action="reviewBoardModifyOK.do" enctype="multipart/form-data">
    <table class="review_view_table">
    	<tr>
    		<c:choose>
    			<c:when test="${sessionScope.id eq dto.id }">
    				<td><input type="text" name="title" value="${dto.title }" size="70"></td>
    			</c:when>
    			<c:otherwise>
    				<td>${dto.title }</td>
    			</c:otherwise>
    		</c:choose>
    	</tr>
    	<tr>
    		<td>
    			<c:if test="${sessionScope.id eq dto.id }">
    				<input type="file" name="img">
    				<br>
    			</c:if>
    			<img src="./img/reviewBoard/${dto.img }">
    			<input type="hidden" value="${dto.img }" name="beforeImg">
    		</td>
    	</tr>
    	<tr>
    			<c:choose>
    			<c:when test="${sessionScope.id eq dto.id }">
    				<td><textarea rows="8" cols="50" name="contents">${dto.contents }</textarea> </td>
    			</c:when>
    			<c:otherwise>
    				<td><textarea rows="8" cols="50" readonly="readonly">${dto.contents }</textarea> </td>
    			</c:otherwise>
    		</c:choose>
    	</tr>
    	<tr>
    		<td>
    			<c:if test="${sessionScope.id eq dto.id }">
    				<input type="hidden" value="${dto.no }" name="no">
    				<input type="submit" value="수정">
    				<a href="reviewBoardDeleteOK.do?no=${dto.no }">삭제</a>
    			</c:if>
    			<a href="javascript:history.back(-1)">목록</a>
    		</td>
    	</tr>
    </table>
    </form>
    </section>
</body>
</html>