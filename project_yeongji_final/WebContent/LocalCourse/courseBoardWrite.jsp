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
	<form method="post" action="courseBoardWriteOK.do" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${dto.id }">
			<div class="localChoice">
				<h2>지역선택</h2>
				<ul class="cityChoice">
					<c:forEach items="${city}" var="city">
						<li>
							<input type="radio" name="city" value="${city.city }" id="${city.city }" required="required">
							<label for="${city.city }">${city.city }</label>
							<ul class="town">
								<li>
									<c:forEach items="${localList }" var="localList">
										<c:if test="${localList.city eq city.city }">
											<c:if test="${localList.county ne null}">
												<input type="radio" name="place" id="${localList.county }" value="${localList.county }">
												${localList.county }	
											</c:if>
											<c:if test="${localList.district ne null}">
												<input type="radio" name="place" id="${localList.district}" value="${localList.district}">
												${localList.district }
											</c:if>
										</c:if>
									</c:forEach>
								</li>
							</ul>	
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="courseBoardWDIV">
				<table class="courseBoardWTable">
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" size="50" autocomplete='off'></td>
					</tr>	
					<tr>
						<td>작성자</td>
						<td><input type="text" name="nickName" value="${dto.nickName }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>장소소개</td>
						<td>
							<input type="text" name="location_Name" size="50" placeholder="장소의 이름을 적어주세요"><br>
							<input type="text" id="sample4_postcode" placeholder="우편번호" name="postCode">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
							<br>
							<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="location_address" required>
							<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="address2" >
							<span id="guide" style="color:#999;display:none"></span>
							<br>
							<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detailAddress">
							<br>
							<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
							<textarea rows="10" cols="60" placeholder="장소에 대한 내용을 간략하게 기재해주세요" name="location_contents"></textarea>
						</td>
					</tr>	
					<tr>
						<td>사진첨부</td>
						<td>
							<input type="file" name="location_img1" required="required"><br>
							<input type="file" name="location_img2" required="required"><br>
							<input type="file" name="location_img3" required><br>
							<input type="file" name="location_img4" required><br>
						</td>
					</tr>
					<tr>
						<td>장소 팁</td>
						<td>
							<input type="text" name="whyRecom" placeholder="해당장소 팁을 적어주세요" size="50" autocomplete='off'>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="작성완료">
						</td>
				</table>
			</div>
	</form>
	
</body>
</html>