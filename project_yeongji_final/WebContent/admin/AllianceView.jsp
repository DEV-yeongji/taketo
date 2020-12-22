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
<link rel="stylesheet" href="./css/Alliance.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to. - 관리자모드</title>
</head>
<body>
	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<section class="allianceView">
		<h2><a href="allianceSetting.admin">목록으로 돌아가기</a></h2>
		<form method="post" action="allianceSuccessOK.admin">
			<table class='allianceView' border="1">
				<tr>
					<td>매장정보</td>
					<td>${dto.storeName }</td>
				</tr>
				<tr>
					<td>지역</td>
					<td>${dto.local }</td>
				</tr>
				<tr>
					<td>연락처</td>
					<td>${dto.mobile } <a href="tel:${dto.mobile}">전화걸기</a></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${dto.email } <a href="mailto:${dto.email}">메일보내기</a></td>
				</tr>
				<tr>
					<td>문의내용</td>
					<td>${dto.askForm }</td>
				</tr>
				<tr>
					<td>제휴상태</td>
					<td>
						<label for="state">제휴를 원하시면 체크해 주세요</label>
						<input type="checkbox" name="state" value="1" id="state" ${dto.state eq 1?'checked':'' }>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="${dto.no }" name="no">
						<input type="submit" value="확인">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>