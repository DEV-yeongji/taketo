<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>Take to.- 회원정보 수정</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/mypage.css">
<script src="js/effectiveness.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	 <%@ include file="/header.jsp"%>
	<c:if test="${sessionScope.id eq null }">
		<script>
			alert("로그인 후 이용 가능합니다.");
			history.back(-1);
		</script>
	</c:if>
	<h1 class="pageInfo"><i class="fas fa-user-cog"></i>&nbsp;&nbsp;회원정보수정</h1>
	<h2>${ dto.id } 님 안녕하세요^^</h2>
	<h2>현재 포인트 : ${dto.point } P</h2>
	<form method="post" action="userModifyOK.do" onsubmit="return passwordChk()" enctype="multipart/form-data">
		<div class="userInfo">
					<c:choose>
						<c:when test="${dto.profile eq null or dto.profile eq '' }">
							<p><img src="profile/profile.png" width="200"/></p>
							<p style="color: red">프로필 사진을 선택해 주세요!</p>		
						</c:when>
						<c:otherwise>
							<img src="profile/${dto.profile}"/>
						</c:otherwise>		
					</c:choose>
					<input type="file" id="profile" name="profile" >	
					<input type="hidden" name="beforeProfile" value="${ dto.profile }">
		</div>
		
		<table class="mypage">
			<tr>
				<td><label for="id">아이디</label></td>
				<td colspan="2"><input type="text"  readonly="readonly" value="${dto.id}" size="25" required="required"></td>
			</tr>
			<tr>
				<td><label for="name">이름</label></td>
				<td colspan="2"><input type="text" id="name" readonly="readonly" value="${dto.name }" size="25" required></td>
				
			</tr>
			<tr>
				<td><label for="nickName">닉네임</label></td>
				<td colspan="2"><input type="text" name="nickName" id="nickName" readonly="readonly"  value="${dto.nickName }" size="25" required></td>
			</tr>
			<tr>
				<td><label for="password">비밀번호 </label></td>
				<td><input type="password" name="password" id="password" size="25" required="required" minlength="8"></td>
				<td><span id="passchkLength" class="inputText"></span></td>
			</tr>
			<tr>
				<td><label for="passwordRe">비밀번호 확인</label></td>
				<td>
					<input type="password" name="passwordRe" id="passwordRe" size="25" required="required">
				</td>
				<td><span id="passchk" class="inputText"></span></td>
			</tr>
			<tr>
				<td><label for="email">이메일</label></td>
				<td colspan="2"><input type="email" name="email" id="email" value="${dto.email }" size="25"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td colspan="2">
					<input type="text" name="bYear" placeholder="ex)1990" value="${dto.bYear }" size="15">
						<select name="bMonth">
							<c:forEach begin="01" end="12" var="bMonth" step="1">
									<option value="${bMonth }" ${dto.bMonth eq bMonth ? "selected" : "" }>${bMonth }</option>
							</c:forEach>
						</select>
					<input type="text" name="bDay" value="${dto.bDay }" size="15">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" id="sample4_postcode" placeholder="우편번호" name="postCode" required value="${dto.postCode}" size="25">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
					<br>
					<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address1" required value="${dto.address1}" size="25">
					<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="address2" required value="${dto.address2}" size="25">
					<span id="guide" style="color:#999;display:none"></span>
					<br>
					<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detailAddress" required value="${dto.detailAddress}"  size="25">
				</td>
				<td>
					<input type="text" id="sample4_extraAddress" placeholder="참고항목"  size="25">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2">
					<select name="mTelecom">
							<option value="SKT" ${dto.mTelecom eq "SKT" ? "selected" : "" }>SKT</option>
							<option value="KT" ${dto.mTelecom eq "KT" ? "selected" : "" }>KT</option>
							<option value="LGU" ${dto.mTelecom eq "LGU" ? "selected" : "" }>LGU+</option>
							<option value="economi" ${dto.mTelecom eq "economi" ? "selected" : "" }>알뜰폰</option>
					</select>
					<input type="text" name="mobile" value="${dto.mobile }"  size="25" >
					<input type="button" value="인증번호받기">
					<br>
					<span>인증번호</span><input type="text" name="certifiCode" size="25">
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td colspan="2">
							<input type="radio" name="gender" value="M" id="M" ${dto.gender eq "M" ? "checked" : "" } required>
							<label for="M">남자</label>
							<input type="radio" name="gender" value="F" id="F" ${dto.gender eq "F" ? "checked" : "" }>
							<label for="F">여자</label>
				</td>
			</tr>					
			<tr>
				<td><label for="snsID">SNS 계정</label></td>
				<td colspan="2"><input type="text" name="snsId" id="snsId" placeholder="ex:)www.instagram/test01" value="${dto.snsId}"  size="30"></td>
			</tr>
			<tr>
				<td>관심주제</td>
				<td colspan="2">
						<input type="checkbox" name="interesting" value="자연" id="natureView" ${fn:indexOf(dto.interesting,"자연") ne -1 ? "checked" : "" }>
						<label for="natureView">자연/문화재</label>
						<input type="checkbox" name="interesting" value="액티비티" id="experience" ${fn:indexOf(dto.interesting,"액티비티") ne -1 ? "checked" : "" }>
						<label for="experience">체험/액티비티활동</label>
						<input type="checkbox" name="interesting" value="힐링" id="healing" ${fn:indexOf(dto.interesting,"힐링") ne -1 ? "checked" : "" }>
						<label for="healing">휴식</label>
						<input type="checkbox" name=interesting value="미식" id="food" ${fn:indexOf(dto.interesting,"미식") ne -1 ? "checked" : "" }>
						<label for="food">미식</label>
				</td>
			</tr>
			<tr>
				<td>소개말</td>
				<td colspan="2">
					<textarea rows="10" cols="50" name="introduce" style="resize: none">${dto.introduce}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="수정">
					<input type="button" value="탈퇴하기" onclick="deleteForm()" class="deleteBtn">
					<a href="index.do" class="btn">홈으로</a>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${dto.id}">
	</form>

</body>
</html>