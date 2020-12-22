<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fnc" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
	<link rel="stylesheet" href="./css/ThemeBoard.css">
<title>Take to.</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<section id="ThemeBoard">
		<form method="post" action="ThemeboardModifyOK.do">
		<table class="ThemeBoardTable">
			<tr>
				<td><label for="title">제목</label></td>
				<td colspan="2"><input type="text" name="title" id="title" value="${dto.title }" ></td>
			</tr>
			<tr>
				<td><label for="nickName">에디터 닉네임</label></td>
				<td colspan="2"><input type="text" name="nickName" id="nickName" readonly="readonly" value="${dto.nickName }"></td>
			</tr>
			<tr>
				<td><label for="locationName">장소이름</label></td>
				<td colspan="2">
					<input type="text" name="locationName" id="locationName" value="${dto.locationName }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td><label for="locationHoliday">휴무일</label></td>
				<td colspan="2">
					<input type="text" name="locationHoliday" id="locationHoliday" value="${dto.locationHoliday }">
				</td>
			</tr>
			<tr>
				<td><label for="locationPay">금액(입장료)</label></td>
				<td colspan="2">
					<input type="text" name="locationPay" id="locationPay" value="${dto.locationPay }">
				</td>
			</tr>
			<tr>
				<td><label for="locationTime">이용시간</label></td>
				<td colspan="2">
					<input type="text" name="locationTime" id="locationTime"  value="${dto.locationTime }">
				</td>
			</tr>
			<tr>
				<td>관련테마</td>
				<td colspan="2">
					<input type="radio" name="interesting" id="natureView" value="자연"  ${dto.interesting eq "자연" ? "checked":""}  >
					<label for="natureView">자연/문화재</label>
					<input type="radio" name="interesting" value="액티비티" id="experience" ${dto.interesting eq "액티비티" ? "checked":""}>
					<label for="experience">체험/액티비티활동</label>
					<input type="radio" name="interesting" value="힐링" id="healing" ${dto.interesting eq "힐링" ? "checked":""}>
					<label for="healing">휴식</label>
					<input type="radio" name="interesting" value="미식" id="food" ${dto.interesting eq "미식" ? "checked":""}>
					<label for="food">미식</label>
				</td>	
			</tr>
			<tr>
				<td colspan="3">
					<textarea rows="15" cols="80" name="contents">${dto.contents }</textarea>
				</td>
			</tr>
			<tr>
				<td>관련 해쉬태그설정</td>
				<td colspan="2">
					<input type="checkbox" name="hashTag" value="#힐링이필요해" id="힐링" ${fnc:indexOf(dto.hashTag,"#힐링이필요해") ne -1 ? "checked" : "" }>
					<label for="힐링">#힐링이필요해</label>
					<input type="checkbox" name="hashTag" value="#분위기좋은곳" id="분위기" ${fnc:indexOf(dto.hashTag,"#분위기좋은곳") ne -1 ? "checked" : "" }>
					<label for="분위기">#분위기좋은곳</label>
					<input type="checkbox" name="hashTag" value="#맛집추천" id="맛집"  ${fnc:indexOf(dto.hashTag,"#맛집추천") ne -1 ? "checked" : "" }>
					<label for="맛집">#맛집추천</label> <br>
					<input type="checkbox" name="hashTag" value="#가족과함께" id="가족"  ${fnc:indexOf(dto.hashTag,"#가족과함께") ne -1 ? "checked" : "" }>
					<label for="가족">#가족과함께</label>
					<input type="checkbox" name="hashTag" value="#친구와함께" id="친구"  ${fnc:indexOf(dto.hashTag,"#친구와함께") ne -1 ? "checked" : "" }>
					<label for="친구">#친구와함께</label>
					<input type="checkbox" name="hashTag" value="#효도관광" id="효도"  ${fnc:indexOf(dto.hashTag,"#효도관광") ne -1 ? "checked" : "" }>
					<label for="효도">#효도관광</label>
					<input type="checkbox" name="hashTag" value="#데이트코스" id="데이트" ${fnc:indexOf(dto.hashTag,"#데이트코스") ne -1 ? "checked" : "" }>
					<label for="데이트">#데이트코스</label>
					<input type="checkbox" name="hashTag" value="#야외투어" id="야외" ${fnc:indexOf(dto.hashTag,"#야외투어") ne -1 ? "checked" : "" }>
					<label for="야외">#야외투어</label>
					<input type="checkbox" name="hashTag" value="#액티비티활동" id="액티비티" ${fnc:indexOf(dto.hashTag,"#액티비티활동") ne -1 ? "checked" : "" }>
					<label for="액티비티">#액티비티활동</label>
					<input type="checkbox" name="hashTag" value="#나들이" id="나들이" ${fnc:indexOf(dto.hashTag,"#나들이") ne -1 ? "checked" : "" }>
					<label for="나들이">#나들이</label>
					<input type="checkbox" name="hashTag" value="#공방체험" id="공방체험" ${fnc:indexOf(dto.hashTag,"#공방체험") ne -1 ? "checked" : "" }>
					<label for="공방체험">#공방체험</label>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="hidden" name="no" value="${dto.no }">
					<input type="submit" value="수정완료">
					<a href="ThemeBoard.do">목록으로</a>
				</td>
		</table>
		
		</form>
	</section>
</body>
</html>