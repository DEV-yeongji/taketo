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
<title>닉네임 중복체크</title>
</head>
<body>
	<h3> 닉네임 중복체크 </h3>
		<form method="post" action="NickNameChk.do" onsubmit="return blankCheck(this)">
			<span>닉네임 :</span> <input type="text" name="nickName" id="nickName" maxlength="12" autofocus>
			<input type="submit" value="중복확인">
		</form>

	<script>
		function blankCheck(f){
			var nickName=f.nickName.value;
			nickName=nickName.trim();
			if(nickName.length<1){
				alert("닉네임은 2자 이상 입력해주세요.");
				return false;
			}else if(nickName == '관리자'){
				alert("해당 닉네임은 사용할 수 없습니다.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>