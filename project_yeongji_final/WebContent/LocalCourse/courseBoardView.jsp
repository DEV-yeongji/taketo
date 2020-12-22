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
<title>Take to.</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/localcourse.css">
   
</head>
<body>
	<%@ include file="/header.jsp"%>
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
    <section class="localBoard_view">
    	<table class="localBoard_view_table">
    		<tr>
    			<td class="city">
    				<i class="fas fa-map-marker-alt"></i> ${dto.city } / ${dto.place }
    			</td>
    		</tr>
    		<tr>
    			<td> 
    				" ${dto.title } "
    			</td>
    		</tr>
    		<tr>
    			<td>${dto.location_Name }</td>
    		</tr>
    		<tr>
    			<td>
    				<img src="./img/localBoard/${dto.location_img1 }">
    				<img src="./img/localBoard/${dto.location_img2 }">
    				<img src="./img/localBoard/${dto.location_img3 }">
    				<img src="./img/localBoard/${dto.location_img4 }">
    			</td>
    		</tr>
    		<tr>
    			<td> 
    				<pre>${dto.location_contents }</pre>
    			</td>
    		</tr>
    		<tr>
    			<td class="whyRecom">TIP. ${dto.whyRecom }</td>
    		</tr>
    		<tr>
    			<td>
    				<div id="map" style="width:1200px;height:300px;"></div>
    			</td>
    		</tr>
    		<tr>
    			<td> 
    				<c:if test="${sessionScope.id eq dto.id }">
    					<a href="localBoardDeleteOK.do?no=${dto.no }">삭제</a>
    					<a href="localBoadrModify.do?no=${dto.no }">수정</a>
    				</c:if>
    				<a href="localcourse.do">목록으로</a>
    			</td>
    		</tr>
    	</table>
    </section>
    <script type="text/javascript">
    function boardLikeF() {
    	alert('게시물을 찜했습니다.');
    	return true;
	}
		

	</script>
	<!-- 지도부분 script -->
    <input type="hidden" value="${dto.location_address }" id="address1">
    <input type="hidden" value="${dto.location_Name }" id="locationName">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은키를입력하세요.보안을위해지웠습니다.&libraries=services,clusterer"></script>
	<script src="./js/themeBoard.js"></script>
</body>
</html>