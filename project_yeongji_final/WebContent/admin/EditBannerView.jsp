<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<body>

	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	<!-- 섹션 시작 -->
	<section class="bannerEdit">
		<form method="post" action="EditBanner.admin" enctype="multipart/form-data">
			<table class="bannerEdit">
				<tr>
					<th colspan="3">관리자님 최대 5개의 이미지를 등록해 주세요.</th>
				</tr>
				<c:forEach begin="1" end="5" var="i">
					<tr>
						<td>배너이미지${i}</td>
						<td colspan="2"><input type="file" name="bannerImg${i}" required="required"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">
						<input type="submit" value="확인">
					</td>
				</tr>
			</table>	
		</form>
		
		<table class="nowBanner">
			<tr>
				<td colspan="5">
					현재 등록된 이미지 입니다
				</td>
			</tr>
			<tr>

				<td><img src="./bannerImg/${banner.bannerImg1 }"></td>
				<td><img src="./bannerImg/${banner.bannerImg2 }"></td>
				<td><img src="./bannerImg/${banner.bannerImg3 }"></td>
				<td><img src="./bannerImg/${banner.bannerImg4 }"></td>
				<td><img src="./bannerImg/${banner.bannerImg5 }"></td>
			</tr>
		</table>
	</section>
</body>
</html>