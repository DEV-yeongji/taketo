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
<link rel="stylesheet" href="./css/popupCss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>Take to.</title>
</head>
<body>
	<c:choose>
		<c:when test="${flag}">
			<p class="findInfo"><b>${chk }</b>는 현재 사용가능합니다.</p>
			<a href='javascript:apply("${chk }")'  class="findSuccess_a">[적용]</a>
			<script>
				function apply(id_value){
					opener.document.join.nickName.value=id_value;
					window.close(); //창닫기
				}
			</script>
		</c:when>
		<c:otherwise>
			<p class="red2">${chk }는 현재 사용이 불가합니다.</p>
			<p class="findInfo_p">
				<a href="javascript:history.back()">[다시시도]</a>
				&nbsp; &nbsp;
				<a href="javascript:window.close()">[창닫기]</a>
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>