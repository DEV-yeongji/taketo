<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/localcourse.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/effectiveness.js"></script>
<title>Take to.</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<form method="post" action="courseBoardModifyOK.do" enctype="multipart/form-data">
			<div class="courseBoardModiDIV">
				<table class="courseBoardModiTable">
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" size="50" value="${dto.title }"></td>
					</tr>	
					<tr>
						<td>작성자</td>
						<td><input type="text" name="nickName" value="${dto.nickName }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>장소소개</td>
						<td>
							<input type="hidden" name="location_Name" size="50" value="${dto.location_Name}">
							<input type="hidden" id="sample4_postcode" placeholder="우편번호" name="postCode">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
							<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="location_address" required value="${dto.location_address}" size="44">
							<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" name="address2" >
							<span id="guide" style="color:#ddd;display:none"></span>
							<input type="hidden" id="sample4_detailAddress" placeholder="상세주소" name="detailAddress">
							<br>
							<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
							<textarea rows="10" cols="60" placeholder="장소에 대한 내용을 간략하게 기재해주세요" name="location_contents">${dto.location_contents }</textarea>
						</td>
					</tr>	
					<tr>
						<td>현재사진</td>
						<td>
							<img src="./img/localBoard/${dto.location_img1 }">
		    				<img src="./img/localBoard/${dto.location_img2 }">
		    				<img src="./img/localBoard/${dto.location_img3 }">
		    				<img src="./img/localBoard/${dto.location_img4 }">
		    				<input type="hidden" name="location_img1_before" value="${dto.location_img1 }">
		    				<input type="hidden" name="location_img2_before" value="${dto.location_img2 }">
		    				<input type="hidden" name="location_img3_before" value="${dto.location_img3 }">
		    				<input type="hidden" name="location_img4_before" value="${dto.location_img4 }">
						</td>
					<tr>
						<td>사진첨부</td>
						<td>
							<input type="file" name="location_img1" ><br>
							<input type="file" name="location_img2" ><br>
							<input type="file" name="location_img3" ><br>
							<input type="file" name="location_img4" ><br>
						</td>
					</tr>
					<tr>
						<td>장소 팁</td>
						<td>
							<input type="text" name="whyRecom" placeholder="추천이유를 간략하게 기재해주세요" size="50" value="${dto.whyRecom }">
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" value="${dto.no }" name="no">
							<input type="submit" value="수정완료">
							<a href="javascript:history.back(-1)">취소</a>
						</td>
				</table>
			</div>
	</form>
	
</body>
</html>