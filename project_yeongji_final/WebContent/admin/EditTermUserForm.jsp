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
	<section class="termOfUsersForm">
		<h1>개인정보처리 / 이용약관 정보 방침 설정</h1>
		<form method="post" action="termUserFormOK.admin">
			<table class="termOfUserForm">
				<tr>
					<td>이용약관</td>
					<td>
						<textarea rows="15" cols="80" name="termsOfUse">${dto.termsOfUse }</textarea>
					</td>
				</tr> 
				<tr>
					<td>개인정보처리</td>
					<td>
						<textarea rows="15" cols="80" name="personalInfo">${dto.personalInfo }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type='submit' value="수정완료">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>