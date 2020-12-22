<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fnc"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Take to.</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="./css/ThemeBoard.css">

</head>
<body>
	<section class="viewLayOut">
		<p class="interestingSubject">
			<i class="fas fa-star"></i> 테마 : <span> ${dto.interesting }</span>
		</p>
		<h2 class="title">${dto.title }</h2>
		<div class="write_info">
			<c:choose>
				<c:when test="${getProfile eq null or getProfile eq '' }">
					<p>
						<img src="./profile/profile.png" width="200" />
					</p>
				</c:when>
				<c:otherwise>
					<img src="./profile/${getProfile}" />
				</c:otherwise>
			</c:choose>

			<span>에디터</span> <b>${dto.nickName }</b>
			<p>
				소개말 <b>${introduce }</b>
			</p>
			<p>
				관심항목 <b>${userInteresting }</b>
			</p>
			<p class="wtime">${dto.wtime }</p>
		</div>
		<hr>
		<div class="viewImg">
			<img src="./img/${dto.fileName1 }" /> <img
				src="./img/${dto.fileName2 }" /> <img src="./img/${dto.fileName3 }" />
			<img src="./img/${dto.fileName4 }" />
		</div>
		<div class="viewContents">
<pre>${dto.contents }</pre>
		</div>
		<hr>
		<p class="map">위치안내</p>

		<div id="map" style="width: 1050px; height: 300px;"></div>
		<c:set value="${dto.address2}" var="local" />
		<table class="location_info">
			<tr>
				<td><i class="far fa-compass"></i> 지역</td>
				<td>${fnc:substring(local, 0, 3) }</td>
			</tr>
			<tr>
				<td><i class="fas fa-map-signs"></i> 위치</td>
				<td>${dto.address1}</td>
			</tr>
			<tr>
				<td><i class="fas fa-door-closed"></i> 휴무일</td>
				<td>${dto.locationHoliday }</td>
			</tr>
			<tr>
				<td><i class="fas fa-coins"></i> 입장료(금액)</td>
				<td>${dto.locationPay }</td>
			</tr>
			<tr>
				<td><i class="fas fa-clock"></i> 이용시간</td>
				<td>${dto.locationTime }</td>
			</tr>
		</table>
		<!-- HashTag는 jstl함수써서 split으로 얻어온 배열을 뿌리기  -->
		<c:set var="hasTag" value="${fnc:split(dto.hashTag,' ')}" />
		<div class="hashTag">
			<c:forEach items="${hasTag }" var="has">
				<a href="">${has}</a>
			</c:forEach>
		</div>
	</section>
	<!-- ********************************* 로그인 한 유저가 글쓴이 라면 삭제 수정버튼 구현 *********************************-->
	<div class="BoardMODIFY">
		<a href="ThemeBoard.do" class="list">목록으로</a>
		<c:if test="${sessionScope.id eq dto.id }">
		    	| <a href="ThemeBoardModify.do?no=${dto.no }" class="modify">게시물
				수정</a>
		    	 | <a href="ThemeBoardDeleteOK.do?no=${dto.no }" class="delete">게시물
				삭제</a>
		</c:if>
	</div>
	<div class="commentList">
		<h1>
			<i class="fas fa-comments"></i> 댓글
		</h1>
		<c:forEach items="${comment }" var="comment">
			<c:if test="${comment.no eq dto.no }">
				<form action="modifyComment.do" method="post">
					<table class="commentList">
						<tr>
							<td colspan="2">${comment.writeUser }<c:if
									test="${comment.writeUser eq dto.nickName}">
									<span class="boardHost">작성자</span>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>작성시간</td>
							<td>${comment.wtime }</td>
						</tr>
						<c:if test="${comment.writeUserID eq sessionScope.id  }">
							<tr>
								<td colspan="2"><textarea rows="5" cols="100"
										name="contents">${comment.contents }</textarea></td>
							</tr>
						</c:if>
						<c:if test="${comment.writeUserID ne sessionScope.id  }">
							<tr>
								<td colspan="2"><textarea rows="5" cols="120"
										readonly="readonly" name="contents">${comment.contents }</textarea>
								</td>
							</tr>
						</c:if>
						<c:if test="${comment.writeUserID eq sessionScope.id  }">
							<tr>
								<td colspan="2"><a
									href="commentDeleteOK.do?commentNo=${comment.commentNo}&no=${comment.no}">댓글삭제</a>
									<input type="hidden" value="${comment.commentNo }"
									name="commentNo"> <input type="hidden"
									value="${comment.no }" name="no"> <input type="submit"
									value="댓글수정"></td>
							</tr>
						</c:if>
					</table>
				</form>
			</c:if>
		</c:forEach>
	</div>
	<hr>
	<c:if test="${sessionScope.id ne null }">
		<div class="commentWrite">
			<form action="commentWriteOK.do" method="post">
				<table class="commentWriteTable">
					<tr>
						<td colspan="2"><c:choose>
								<c:when
									test="${userDTO.profile eq null or userDTO.profile eq '' }">
									<p>
										<img src="./profile/profile.png" width="200" />
									</p>
								</c:when>
								<c:otherwise>
									<img src="./profile/${userDTO.profile}" />
								</c:otherwise>
							</c:choose> <span class="writeName">${userDTO.nickName }</span></td>
					</tr>
					<tr>
						<td colspan="2"><textarea name="contents"
								placeholder="바른말 고운말 댓글을 써주세요" cols="80" rows="5"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="hidden" name="no"
							value="${dto.no}"> <input type="hidden" name="writeUser"
							value="${userDTO.nickName }"> <input type="hidden"
							name="writeUserID" value="${userDTO.id }"> <input
							type="submit" value="댓글작성"></td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>

	<input type="hidden" value="${dto.address1 }" id="address1">
	<input type="hidden" value="${dto.locationName }" id="locationName">
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은키를입력하세요.보안을위해지웠습니다&libraries=services,clusterer"></script>
	<script src="./js/themeBoard.js"></script>
</body>
</html>