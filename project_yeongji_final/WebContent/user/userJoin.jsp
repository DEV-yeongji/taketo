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
<title>Take to.- 회원가입</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="css/mypage.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/effectiveness.js"></script>
</head>
<body>
	<h1 class="pageInfo"><i class="fas fa-user-plus"></i> 회원가입</h1>
	<form name="join" method="post" action="joinOK.do" onsubmit="return passwordChk()">
		<table class="userJoin">
			<tr>
				<td><label for="id">아이디</label></td>
				<td>
					<input type="text" name="id" id="id" readonly="readonly">
					<span id="idChkLength" class="inputText"></span>
				</td>
				<td><input type="button" value="아이디 중복확인" onclick="idChk()">
			</tr>
			<tr>
				<td><label for="name">이름</label></td>
				<td colspan="2"><input type="text" name="name" id="name" autocomplete='off'></td>
				
			</tr>
			<tr>
				<td><label for="nickName">닉네임</label></td>
				<td><input type="text" name="nickName" id="nickName" readonly="readonly"></td>
				<td><input type="button" value="닉네임 중복확인" onclick="nickChk()">
			</tr>
			<tr>
				<td><label for="password">비밀번호 </label></td>
				<td><input type="password" name="password" id="password" minlength="8"></td>
				<td><span id="passchkLength" class="inputText"></span></td>
			</tr>
			<tr>
				<td><label for="passwordRe">비밀번호 확인</label></td>
				<td>
					<input type="password" name="passwordRe" id="passwordRe">
				</td>
				<td><span id="passchk" class="inputText"></span></td>
			</tr>
			<tr>
				<td><label for="email">이메일</label></td>
				<td colspan="2"><input type="email" name="email" id="email" autocomplete='off'></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td colspan="2">
					<input type="text" name="bYear" placeholder="ex)1990" size="15">
						<select name="bMonth">
							<c:forEach begin="01" end="12" var="bMonth" step="1">
								<option vlaue="${bMonth }">${bMonth }</option>
							</c:forEach>
						</select>
					<input type="text" name="bDay" size="15">
				</td>
			</tr>
			<tr>
				<td>주소</td>
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
					<input type="text" id="sample4_extraAddress" placeholder="참고항목">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2">
					<select name="mTelecom">
						<option value="SKT">SKT</option>
						<option value="KT">KT</option>
						<option value="LGU">LGU+</option>
						<option value="economi">알뜰폰</option>
					</select>
					<input type="text" name="mobile" >
					<input type="button" value="인증번호 받기">
					<br>
					<span>인증번호<input type="text" name="certifiCode" size="13"></span>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td colspan="2">
					<input type="radio" name="gender" value="M" id="M">
					<label for="M">남자</label>
					<input type="radio" name="gender" value="F" id="F">
					<label for="F">여자</label>
				</td>
			</tr>					
			<tr>
				<td><label for="snsID">SNS 계정</label></td>
				<td colspan="2"><input type="text" name="snsId" id="snsId" placeholder="ex:)www.instagram/test01" size="27"></td>
			</tr>
			<tr>
				<td>관심주제</td>
				<td colspan="2">
					<input type="checkbox" name="interesting" value="자연" id="natureView">
					<label for="natureView">자연/문화재</label>
					<input type="checkbox" name="interesting" value="액티비티" id="experience">
					<label for="experience">체험/액티비티활동</label>
					<input type="checkbox" name="interesting" value="힐링" id="healing">
					<label for="healing">휴식</label>
					<input type="checkbox" name="interesting" value="미식" id="food">
					<label for="food">미식</label>
				</td>
			</tr>
			<tr>
				<td>소개말</td>
				<td colspan="2">
					<textarea rows="10" cols="50" name="introduce" style="resize: none" placeholder=" 간단한 소개말을 입력해주세요"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="가입완료">
					<a href="index.do" style="color: black;">가입취소</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>