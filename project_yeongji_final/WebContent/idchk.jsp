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
<title>아이디 중복체크</title>
<link rel="stylesheet" href="./css/popupCss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<body>
	<h3> 아이디 중복체크 </h3>
		<form method="post" action="idchkOK.do" onsubmit="return blankCheck(this)">
			<span>아이디</span> : <input type="text" name="id" id="id" maxlength="12" autofocus>
			<input type="submit" value="중복확인">
		</form>

	<script>
		function blankCheck(f){
			var id=f.id.value;
			id=id.trim();
			if(id.length<5){
				alert("아이디는 5자 이상 입력해주세요.");
				return false;
			}else if(id =='admin'){
				alert("해당아이디는 사용할 수 없습니다.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>