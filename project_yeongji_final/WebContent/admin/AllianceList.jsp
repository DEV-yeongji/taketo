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
		<h1>제휴업체 관리</h1>
		<table class="allianceList" border="1">
			<tr>
				<td>No.</td>
				<td>매장이름</td>
				<td>지역</td>
				<td>연락처</td>
				<td>이메일</td>
				<td>문의시간</td>
				<td>제휴상태</td>
				<td>비고</td>
				<td></td>
			</tr>		
		<c:forEach items="${Alliancelist}" var="list">		
			<tr>
				<td>${list.no }</td>
				<td class="allianceTitle"><a href="allianceView.admin?no=${list.no }">${list.storeName }</a></td>
				<td>${list.local }</td>
				<td>${list.mobile }</td>
				<td>${list.email }</td>
				<td>${list.wtime }</td>
				<c:if test="${ list.state eq 0}">
					<td class="successWait">제휴대기중</td>
				</c:if>
				<c:if test="${ list.state eq 1}">
					<td class="successOK">수락 완료</td>
				</c:if>	
				
				<c:if test="${ list.readNotice eq 0}">
					<td class="readNo">읽지않음</td>
				</c:if>
				<c:if test="${ list.readNotice eq 1}">
					<td class="readOK">읽음</td>
				</c:if>	
				<td><a href="allianceDeleteOK.admin?no=${list.no }">삭제</a></td>
			</tr>	
		</c:forEach>			
		</table>
		<div class="allianceBanner">
			<h2 class="allianceBanner">제휴매장 배너이미지 등록</h2>
			<form action="allianceBannerOK.admin" method="post" enctype="multipart/form-data">
				<input type="text" name="allianceName" placeholder="매장명을 입력하세요"><br>
				<input type='text' name="allianceInfo" placeholder="매장소개말을 입력하세요">
				<input type="file" name="allianceBanner"><br>
				<input type='submit' value="등록">
			</form>
		</div>
		<div class='allianceBannerList'>
			<h2>등록된 제휴매장 배너</h2>
				<table class="allianceBannerListTable">
					<tr>
						<td>매장명</td>
						<td>매장소개</td>
						<td>매장대표이미지</td>
						<td>&nbsp;</td>
					</tr>
					<c:forEach items="${bannerDTO }" var="bannerDTO">
						<tr>
							<td>${bannerDTO.allianceName }</td>
							<td>${bannerDTO.allianceInfo }</td>
							<td><img src="./bannerImg/${bannerDTO.allianceBanner }"></td>
							<td><a href="allianceBannerListDelete.admin?no=${bannerDTO.no }">삭제</a></td>
						</tr>
					</c:forEach>
				</table>
		</div>
		<input type="hidden" value="${readNotice }" name="${readNotice }" id="readNotice"> 
	<script>
		var readNotice = document.getElementById('readNotice').value;
		if(readNotice>0){
			alert(readNotice + '건의 읽지않은 제휴 요청이 있습니다.');
		}
	</script>
</body>
</html>