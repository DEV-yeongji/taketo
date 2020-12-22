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
<link rel="stylesheet" href="./css/popupCss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to.</title>
</head>
<body>
	<c:choose>
		<c:when test="${id eq null }">
			<p class="red">해당정보를 가진 사용자가 없습니다.</p>
			<p class="findInfo_p">
			<a href="javascript:history.back()">[다시시도]</a>
			&nbsp; &nbsp;
			<a href="javascript:window.close()">[창닫기]</a>
			</p>
		</c:when>
		<c:otherwise>
		
			<p class="findInfo">아이디는 <b>${id }</b> 입니다.</p>
			<a href="javascript:window.close()" class="findSuccess_a">[창닫기]</a>
		</c:otherwise>
	</c:choose>
</body>
</html>