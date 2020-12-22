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
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/localcourse.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
	  $(document).ready(function(){ $('.slider').bxSlider(); });
	</script>
<title>Take to. - 지역별</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<div class="localSearch">
		<a href="localcourse.do?city=">전체</a>
		<c:forEach items="${city }" var="city">
			<a href="localCourseSearch.do?city=${city.city }">${city.city}</a>
		</c:forEach>
	</div>
	<section class="localcourseList">
		<c:forEach items="${cBoardList }" var="cList">
			<div class="localBoard">
				<div class="localBoardListImg">
					<img src="./img/localBoard/${cList.location_img1 }">
					<div class="localBoardContents">
						<p>${cList.city}${cList.place}</p>
						<a href="localBoardView.do?no=${cList.no }&&id=${cList.id}">${cList.title}</a>
						<br> <br> <span class="writeId">Write by.
							${cList.nickName } </span>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${empty cBoardList}">
			<p class="listNone">
				<i class="fas fa-comment-slash"></i> <br> 등록된 게시글이 없습니다.

			</p>
		</c:if>
		<div class="writeBtn">
			<c:if test="${sessionScope.id ne null }">
				<a href="courseBoardWrite.do?id=${sessionScope.id }">글 작성하기 
				<i class="fas fa-pencil-alt"></i></a>
			</c:if>
		</div>
		<div class="pageing">
			<c:set var="curPage" value="${param.curPage }" />
			<c:if test="${curPage eq null }">
				<c:set var="curPage" value="0" />
			</c:if>
			<c:forEach var="i" begin="0" end="${maxPage }">
				<c:if test="${curPage eq i }">
					<span>${i +1}</span>
				</c:if>
				<c:if test="${curPage ne i }">
					<a href="localcourse.do?curPage=${i }">${i+1 }</a>
				</c:if>
			</c:forEach>
		</div>
	</section>
	<nav class="localcourseNav">
		<div id="header2">
			<i class="fas fa-link"></i> 제휴매장 리스트
		</div>
			<ul class="bxslider">
				<c:forEach items="${bannerDTO }" var="bannerDTO">
					<li>
						<p class="allName">${bannerDTO.allianceName }</p>
						<p class="allianceInfo">${bannerDTO.allianceInfo }</p> 
						<img src="./bannerImg/${bannerDTO.allianceBanner }" width="500" />
					</li>
				</c:forEach>
			</ul>
	</nav>

	<script>
	$(document).ready(function () {
	    $('.bxslider').bxSlider({ // 클래스명 주의!
	        auto: true, // 자동으로 애니메이션 시작
	        speed: 500,  // 애니메이션 속도
	        pause: 5000,  // 애니메이션 유지 시간 (1000은 1초)
	        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
	        autoControls: false, // 시작 및 중지버튼 보여짐
	        pager: false, // 페이지 표시 보여짐
	        captions: false, // 이미지 위에 텍스트를 넣을 수 있음
	    });
	});
	</script>
</body>
</html>