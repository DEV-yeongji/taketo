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
<link rel="stylesheet" href="./css/admin.css">
<link rel="stylesheet" href="./css/NoticeBoardForAdmin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to.</title>
</head>
<body>
	<c:choose>

		<c:when test="${sessionScope.id eq 'admin' }">
		<jsp:include page="/adminHeader.jsp"></jsp:include>
		<section class="noticeBoardViewForAdmin">
				<form method="post" action="NoticeBoardModify.admin" enctype="multipart/form-data">
					<table class="noticeBoardViewForAdmin">
						<tr>
							<td>분류</td>
							<c:if test="${dto.subject eq '공지사항' }">
								<td class="red">
									${dto.subject }
								</td>
							</c:if>
							<c:if test="${dto.subject eq '이벤트' }">
								<td class="gold">
									${dto.subject }
								</td>
							</c:if>
						</tr>	
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" value="${dto.title }"></td>
						</tr>	
						<tr>
							<td>작성자</td>
							<td>관리자</td>
						</tr>	
						<tr>
							<td>기간</td>
							<td>
								<input type="date" name="term" value="${dto.term }">
							</td>
						</tr>
						<tr>
							<td>이미지 파일첨부</td>
							<td>
								<input type="file" name="img">
								<c:if test="${dto.img ne null and dto.img ne ''}">
									<p class="msg">현재 이미지 <img src="./img/NoticeEventImg/${dto.img }" width="80"></p>
								</c:if>
								<input type="hidden" name="beforeImg" value="${ dto.img }">
							</td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="contents" cols="120" rows="21">${dto.contents }</textarea></td>
						</tr>		
						<tr>
							<td colspan="2">
								<a href="javascript:history.back()">목록으로</a>
								<input type="hidden" name="no" value="${dto.no }">
								<input type="submit" value="수정완료">
							</td>
						</tr>
					</table>
				</form>
			</section>
			</c:when>
			<c:otherwise>
			<%@ include file="/header.jsp"%>
			<section class="noticeBoardViewForUser">
				<table class="noticeBoardViewForUser">
					<tr>
						<td><i class="fas fa-bookmark"></i> 분류</td>
						<c:if test="${dto.subject eq '공지사항' }">
							<td class="red">
								${dto.subject }
							</td>
						</c:if>
						<c:if test="${dto.subject eq '이벤트' }">
							<td class="gold">
								${dto.subject }
							</td>
						</c:if>
					</tr>	
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" value="${dto.title }" readonly="readonly"></td>
					</tr>	
					<tr>
						<td>작성자</td>
						<td>관리자</td>
					</tr>	
					<tr>
						<td> 기간</td>
						<td>
							~ <input type="date" name="term" value="${dto.term }" readonly="readonly">
						</td>
					</tr>
					<c:if test="${dto.img ne null and dto.img ne ''}">
						<tr>
							<td colspan="2">
								<hr>
								<img src="./img/NoticeEventImg/${dto.img }">
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="2">
							<hr>
							<textarea name="contents" cols="80" rows="8" readonly="readonly">${dto.contents }</textarea>
						</td>
					</tr>		
					<tr>
						<td colspan="2">
							<a href="javascript:history.back()">목록으로</a>
						</td>
					</tr>
				</table>
			</section>
			</c:otherwise>
		</c:choose>
	
</body>
</html>