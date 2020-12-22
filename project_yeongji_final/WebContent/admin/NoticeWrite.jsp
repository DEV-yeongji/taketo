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
<title>Take to. - 관리자모드</title>
</head>
<body>
	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<h1>공지사항 / 이벤트 게시글 작성</h1>
	<form method="post" action="NoticeEventWriteOK.admin" enctype="multipart/form-data">
		<table class="noticeWrite">
			<tr>
				<td>분류</td>
				<td>
					<select name="subject">
						<option value="공지사항">공지사항</option>
						<option value="이벤트">이벤트</option>
					</select>
				</td>
			</tr>	
			<tr>
				<td>제목</td>
				<td><input type="text" name='title' required="required"></td>
			</tr>
			<tr>
				<td>이미지 파일첨부</td>
				<td><input type="file" name='img'></td>
			</tr>
			<tr>
				<td>기간</td>
				<td>
					<input type="date" name="term" min="2020-11-10" required="required" >
					<span> * 해당 게시물을 띄울기간을 설정해주세요</span>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="contents" cols="80" rows="20" required="required"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" value="${sessionScope.id }" name="id">
					<input type="submit" value="등록">
					<a href="NoticeEventListAdmin.admin">목록</a>
				</td>
			</tr>				
		</table>
	</form>
</body>
</html>