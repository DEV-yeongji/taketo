<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Take to. - 관리자모드</title>
<link rel="stylesheet" href="./css/admin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<body>
	<jsp:include page="/adminHeader.jsp"></jsp:include>
	<br>
	<section>
		<h1 class="subMenuForAdmin">회원관리 페이지 입니다.</h1>
		<div class="registerList">
			<p>등록된 회원 목록(최근순 정렬)</p>
			
			
				<table class="subMenuForAdmin" border="1">
					<tr>
						<td>아이디</td>
						<td>닉네임</td>
						<td>이름</td>
						<td>전화번호</td>
						<td>전화번호<br>인증</td>
						<td>이메일</td>
						<td>생년월일</td>
						<td>주소</td>
						<td>성별</td>
						<td>sns아이디</td>
						<td>관심항목</td>
						<td>프로필사진</td>
						<td>포인트</td>
						<td>가입한 날짜</td>
						<td>비고</td>
					</tr>
					<c:forEach items="${memberRegisterForAdmin}" var="member">
					<tr>
						<td>${member.id }</td>
						<td>${member.nickName }</td>
						<td>${member.name }</td>
						<td>${member.mTelecom } ${member.mobile }</td>
						<td>
							<c:choose>
								<c:when test="${member.certifiCode eq '' }">
									X
								</c:when>
								<c:otherwise>
									O
								</c:otherwise>
							</c:choose>
							
						</td>
						<td>${member.email }</td>
						<td>${member.bYear }. ${member.bMonth }. ${member.bDay }</td>
						<td>${member.address1 } <br> ${member.detailAddress }</td>
						<td>${member.gender }</td>
						<td>${member.snsId }</td>
						<td>${member.interesting }</td>
						<td>
							<c:choose>
								<c:when test="${member.profile eq null}">
									X
								</c:when>
								<c:when test="${member.profile eq ''}">
									X
								</c:when>
								<c:otherwise>
									<img src="./profile/${member.profile }">
								</c:otherwise>
							</c:choose>
						</td>
						<form method="post" action="memberRegisterModify.admin">
						<td><input type="number" value="${member.point }" name="point" style="width: 50px"></td>
						<td>${member.joinDate }</td>
						<td>
						<!-- 최근 것 만 수정됨. 삭제는 가능 --> 
							<input type="hidden" value="${member.id }" name="id">
							<input type="submit" value="수정">
						</form>  
							<br><a href="memberDeleteForAdmin.admin?id=${member.id }" class="btn">삭제</a>
						</td>
					</tr>
					</c:forEach>
				</table>
		</div>
		<div class="paging">
		<c:set var="curPage" value="${param.curPage }"/>
			<c:if test="${curPage eq null }">
				<c:set var="curPage" value="0" />
			</c:if>
			<c:forEach var="i" begin="0" end="${maxPage }">
				<c:if test="${curPage eq i }">
					<span>${i +1}</span>
				</c:if>
				<c:if test="${curPage ne i }">
					<a href="memberRegisterForAdmin.admin?curPage=${i }">${i+1 }</a>
				</c:if>
			</c:forEach>
			</div>
	</section>

</body>
</html>