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
<link rel="stylesheet" href="./css/contact.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to. - 회사소개</title>
</head>
<body>
	<header class="contact" id="logo">
		<h1 class="logo"><img src="./img/logo.png" /></h1>
		<h2><a href="index.do"><i class="fas fa-home"></i> 메인화면</a></h2>
		<h3>
			<a href="#allianceService"><i class="fas fa-link"></i>제휴 서비스 안내</a>&nbsp;
			<a href="#contact"><i class="fas fa-briefcase"></i>CONTACT</a>
		</h3>
	</header>
	<section class="contact">
		<div id="officeInfo">
			<h2>${dto.officeInfoTitle }</h2>
			<p>
				${dto.officeInfo }
			</p>
			<img src="./img/contact.jpg" />
		</div>
		<div class="line">
			<h1>서로서로 다녀온 곳을 공유하고 이야기를 나눠보세요!</h1>
		</div>
		<div id="contact">
			<h2>CONTACT</h2>
			<table class="contactOfficeInfo">
				<tr>
					<td><i class="fas fa-user-alt"></i> 대표</td>
					<td>${dto.contactHost }</td>
				</tr>		
				
				<tr>
					<td><i class="fas fa-phone-volume"></i> 연락처</td>
					<td>${dto.contactTelefon }</td>
				</tr>		
				<tr>
					<td><i class="fas fa-mobile-alt"></i> 모바일</td>
					<td>${dto.contactMobile }</td>
				</tr>		
				<tr>
					<td><i class="fas fa-envelope"></i> 이메일</td>
					<td> ${dto.contactEmail }</td>
				</tr>		
				<tr>
					<td colspan="2">
						<span>회사위치</span>
					</td>
				</tr>		
			</table>
			<div id="map" style="width:1050px;height:340px;"></div>
			
		</div>
		<div class="line">
			<h1>소상공인들과 제휴를 맺어서 지역경제도 살리고 사용자도 좋아하는 1석2조 어떠신가요?</h1>
		</div>
		<div id="allianceService">
			<h2>제휴 서비스</h2>
			<p>제휴 서비스를 원하시는 사장님들은 부담없이 문의부탁드려요!</p>
			<form method="post" action="allianceOK.do">
			<table class="alliance">
				<tr>
					<td>상호명</td>
					<td><input type="text" name="storeName" placeholder="정확한 상호명을 입력해 주세요" size="40"></td>
				</tr>
				<tr>
					<td>지역</td>
					<td><input type="text" name="local" placeholder="Ex :인천시 서구" size="40"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" size="40"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" name="mobile" placeholder="Ex :010-1234-5678" size="40"></td>
				</tr>
				<tr>
					<td>문의내용</td>
					<td><textarea name="askForm" cols="50" rows="10" placeholder="문의하실 내용을 간략하게 적어주세요"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="확인">
					</td>
				</tr>
			</table>
			</form>
			<a href="#logo" class="goTop"><i class="fas fa-arrow-circle-up"></i></a>	
		</div>
	</section>
	<input type="hidden" value="${dto.contactAddress }" id="contactAddress">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은키를입력하세요.보안을위해지웠습니다.&libraries=services,clusterer"></script>
	<script src="./js/contactPage.js"></script>
</body>
</html>