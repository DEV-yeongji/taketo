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
<link rel="stylesheet" href="./css/reviewBoard.css">
</head>
<body>
	<%@ include file="/header.jsp"%>
	<form method="post" action="reviewBoardWriteOK.do" enctype="multipart/form-data">
	<table class='reviewBoardWrite'>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" required="required" autocomplete='off'></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="nickName" readonly="readonly" value="${dto.nickName }"></td>
		</tr>
		<tr>
			<td>사진첨부</td>
			<td>
				<input type="file" name="img" required="required">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea rows="10" cols="50" name="contents" required="required" placeholder="다녀온 소감을 간단하게 작성해주세요"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" name="id" value="${dto.id }">
				<input type="submit" value="확인">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>