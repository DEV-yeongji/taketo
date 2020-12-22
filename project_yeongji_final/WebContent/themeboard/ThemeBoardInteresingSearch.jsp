<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Take to. - 테마별</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
	<link rel="stylesheet" href="./css/ThemeBoard.css">
	<script src="./js/"></script>
</head>
<body>
		<%@ include file="/header.jsp"%>
	<div class="search">
		<div class="searchForm">
			<form method="post" action="search.do">
				<input type="text" name="search" placeholder="해쉬태그(#) 를 이용하여 검색어를 입력하세요" id="search" autocomplete="off"> 
				<input type="submit" value="검색">
			</form>
		</div>
	</div>
	<section id="ThemeBoardList">
			<table class="ThemeBoardListTable">
				<c:forEach items="${searchResult}" var="searchResult" >
					<tr>
						<td><img src="./img/${searchResult.fileName1 }"></td>
						<td class="listInfo">
								<p class="likeAndHit">
								<i class="fas fa-eye"></i> ${searchResult.hit } 
							 </p>
							 <c:if test="${searchResult.interesting eq '미식' }">
							 	<h2 class="themeInfo_food">
									<i class="fas fa-utensils"></i> ${searchResult.interesting}
								</h2>
							 </c:if>
							 <c:if test="${searchResult.interesting eq '힐링' }">
							 	<h2 class="themeInfo_healing">
									<i class="fas fa-heartbeat"></i> ${searchResult.interesting}
								</h2>
							 </c:if>
							 <c:if test="${searchResult.interesting eq '액티비티' }">
							 	<h2 class="themeInfo_act">
									<i class="fas fa-running"></i> ${searchResult.interesting}
								</h2>
							 </c:if>
							 <c:if test="${searchResult.interesting eq '자연' }">
							 	<h2 class="themeInfo_nature">
									<i class="fas fa-leaf"></i> ${searchResult.interesting}
								</h2>
							 </c:if>
							<p class="listInfo"><a href="ThemeBoardView.do?no=${searchResult.no}&&id=${searchResult.id}"> " ${searchResult.title } " </a></p>
							<span class="listInfo_name">${searchResult.nickName } </span> 님의
							<span class="listInfo_location">${searchResult.locationName }</span>
							<p class="listHashTag">${searchResult.hashTag }</p>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${empty searchResult }">
				<p class="listNone">
					<i class="fas fa-comment-slash"></i>
					<br>
					등록된 게시글이 없습니다.
				</p>
			</c:if>
			<c:if test="${sessionScope.id ne null }">
				<a href="ThemeboardWrite.do?id=${sessionScope.id }" class="write">글 작성하기 <i class="fas fa-pencil-alt"></i></a>
			</c:if>
			<div class="pageing">
			<c:set var="curPage" value="${param.curPage }"/>
				<c:if test="${curPage eq null }">
					<c:set var="curPage" value="0" />
				</c:if>
				<c:forEach var="i" begin="0" end="${maxPage }">
					<c:if test="${curPage eq i }">
						<span>${i +1}</span>
					</c:if>
					<c:if test="${curPage ne i }">
						<a href="searchInteresting.do?curPage=${i }&interesting=${interesting}">${i+1 }</a>
					</c:if>
				</c:forEach>
			</div>
	</section>
	<nav id="ThemeBoardListNAV">
		<table>
			<tr>
				<td colspan="2">
					<h1>테마별 검색</h1>
				</td>
			</tr>
			<tr>
				<td><a href="searchInteresting.do?interesting=자연" class="ThemeNAV" style="color:#009148"><i class="fas fa-leaf"></i> 자연</a></td>
				<td><a href="searchInteresting.do?interesting=미식" class="ThemeNAV" style="color:#e39e2b"><i class="fas fa-utensils"></i> 미식</a></td>
			</tr>
			<tr>
				<td><a href="searchInteresting.do?interesting=액티비티" class="ThemeNAV" style="color:#3c9dff"><i class="fas fa-running"></i> 액티비티</a></td>
				<td><a href="searchInteresting.do?interesting=힐링" class="ThemeNAV" style="color:#9b7de1"><i class="fas fa-heartbeat"></i> 힐링</a></td>
			</tr>
		</table>
	</nav>
	
</body>
</html>