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
<link rel="stylesheet" href="./css/admin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to. - 관리자모드</title>
</head>
<body>
	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<section class="contactEdit">
		<h2>회사 정보 관리 페이지</h2>
		<form method="post" action="contactEditOK.admin">
			<table class="contactEdit">
				<tr>
					<td>사이트(회사)소개 타이틀</td>
					<td><input type="text" name="officeInfoTitle" placeholder="50글자 이내에 회사(사이트)를 표현해 주세요" size="70" value="${dto.officeInfoTitle }"></td>
					
				</tr>
				<tr>
					<td>사이트(회사)소개 내용</td>
					<td>
					<textarea name="officeInfo" placeholder="자유롭게 회사(사이트)를 표현해 주세요" cols="73" rows="10" >${dto.officeInfo }</textarea>
					</td>
		
				</tr>
				<tr>
					<td>회사 대표</td>
					<td><input type="text" name="contactHost" size="20" value="${dto.contactHost }"></td>
				</tr>
				<tr>
					<td>회사 위치</td>
					<td><input type="text" name="contactAddress" placeholder="EX:)서울특별시 강남구 역삼동 635-17 장연빌딩" size="50" value="${dto.contactAddress }"></td>
				</tr>
				<tr>
					<td>회사 연락처</td>
					<td><input type="text" name="contactTelefon" size="20" value="${dto.contactTelefon }"></td>
				</tr>
				<tr>
					<td>회사 모바일 연락처</td>
					<td><input type="text" name="contactMobile" size="20" value="${dto.contactMobile }"></td>
				</tr>
				<tr>
					<td>회사 이메일</td>
					<td><input type="email" name="contactEmail" size="50" value="${dto.contactEmail }"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>