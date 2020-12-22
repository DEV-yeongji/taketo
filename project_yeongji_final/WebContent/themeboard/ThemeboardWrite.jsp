<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<link rel="stylesheet" href="./css/ThemeBoard.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/effectiveness.js"></script>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<section id="ThemeBoard">
		<form method="post" action="ThemeboardWriteOK.do" enctype="multipart/form-data">
		<table class="ThemeBoardTable">
			<tr>
				<td><label for="title">제목</label></td>
				<td colspan="2"><input type="text" name="title" id="title" autocomplete='off'></td>
			</tr>
			<tr>
				<td><label for="nickName">에디터 닉네임</label></td>
				<td colspan="2"><input type="text" name="nickName" id="nickName" readonly="readonly" value="${dto.nickName }"></td>
			</tr>
			<tr>
				<td>이미지 업로드</td>
				<td>
					<input type="file" name="fileName1" required="required">( 대표이미지 )<br>
					<input type="file" name="fileName2" required="required"><br>
					<input type="file" name="fileName3" required="required"><br>
					<input type="file" name="fileName4" required="required"><br>	
				</td>
				<td>
					<span class="msg"> * 주제와 상관없는 이미지 업로드시 게시물 삭제처리됩니다.</span>
				</td>
				
			</tr>
			<tr>
				<td><label for="locationName">장소이름</label></td>
				<td colspan="2">
					<input type="text" name="locationName" id="locationName" autocomplete='off'>
				</td>
			</tr>
			<tr>
				<td><label for="locationHoliday">휴무일</label></td>
				<td colspan="2">
					<input type="text" name="locationHoliday" id="locationHoliday" placeholder="EX:) 연중무휴" autocomplete='off'>
				</td>
			</tr>
			<tr>
				<td><label for="locationPay">금액(입장료)</label></td>
				<td colspan="2">
					<input type="text" name="locationPay" id="locationPay" placeholder="EX:)어른 3000원 / 미취학아동 무료" autocomplete='off'>
				</td>
			</tr>
			<tr>
				<td><label for="locationTime">이용시간</label></td>
				<td colspan="2">
					<input type="text" name="locationTime" id="locationTime" placeholder="EX:) 10시~22시" autocomplete='off'>
				</td>
			</tr>
			<tr>
				<td>관련테마</td>
				<td colspan="2">
					<input type="radio" name="interesting" id="natureView" value="자연">
					<label for="natureView">자연/문화재</label>
					<input type="radio" name="interesting" value="액티비티" id="experience">
					<label for="experience">체험/액티비티활동</label>
					<input type="radio" name="interesting" value="힐링" id="healing">
					<label for="healing">휴식</label>
					<input type="radio" name="interesting" value="미식" id="food">
					<label for="food">미식</label>
				</td>	
			</tr>
			<tr>
				<td colspan="3">
					<textarea rows="15" cols="80" name="contents"></textarea>
				</td>
			</tr>
			<tr>
				<td>위치</td>
				<td>
					<input type="text" id="sample4_postcode" placeholder="우편번호" required name="postCode">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
					<br>
					<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address1" required>
					<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="address2" required>
					<span id="guide" style="color:#999;display:none"></span>
					<br>
					<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detailAddress">
				</td>
				<td>
					<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
				</td>
			</tr>
			<tr>
				<td>관련 해쉬태그설정</td>
				<td colspan="2">
					<input type="checkbox" name="hashTag" value="#힐링이필요해" id="힐링">
					<label for="힐링">#힐링이필요해</label>
					<input type="checkbox" name="hashTag" value="#분위기좋은곳" id="분위기">
					<label for="분위기">#분위기좋은곳</label>
					<input type="checkbox" name="hashTag" value="#맛집추천" id="맛집">
					<label for="맛집">#맛집추천</label>
					<input type="checkbox" name="hashTag" value="#가족과함께" id="가족">
					<label for="가족">#가족과함께</label>
					<input type="checkbox" name="hashTag" value="#친구와함께" id="친구">
					<label for="친구">#친구와함께</label>
					<input type="checkbox" name="hashTag" value="#효도관광" id="효도">
					<label for="효도">#효도관광</label><br>
					<input type="checkbox" name="hashTag" value="#데이트코스" id="데이트">
					<label for="데이트">#데이트코스</label>
					<input type="checkbox" name="hashTag" value="#야외투어" id="야외">
					<label for="야외">#야외투어</label>
					<input type="checkbox" name="hashTag" value="#액티비티활동" id="액티비티">
					<label for="액티비티">#액티비티활동</label>
					<input type="checkbox" name="hashTag" value="#나들이" id="나들이">
					<label for="나들이">#나들이</label>
					<input type="checkbox" name="hashTag" value="#공방체험" id="공방체험">
					<label for="공방체험">#공방체험</label>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="hidden" name="id" value="${sessionScope.id }">
					<input type="submit" value="완료">
					<a href="ThemeBoard.do">목록으로</a>
				</td>
			</tr>
		</table>
		</form>
	</section>
</body>
</html>
