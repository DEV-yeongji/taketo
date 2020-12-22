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
<link rel="stylesheet" href="./css/popupCss.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<div class="lostInfo">
		<input id="tab1" type="radio" name="tabs" checked>
		<!--디폴트 메뉴-->
		<label for="tab1">아이디 찾기</label> <input id="tab2" type="radio"
			name="tabs"> <label for="tab2">비밀번호 찾기</label>

		<section id="content1">
			<form method="post" action="idLoastOK.do">
				<p><input type="text" name="name" id="name" placeholder="이름을 입력하세요" size="30" autocomplete="off" required="required"></p>
				<p><input type="email" name="email" id="email" placeholder="이메일을 입력하세요" size="30" autocomplete="off" required></p>
				<p><input type="submit" value="확인"></p>
			</form>
		</section>

		<section id="content2">
			<form method="post" action="pwLostOK.do">
				<p><input type="text" name="name" id="name" placeholder="이름을 입력하세요" size="30" autocomplete="off" required></p>
				<p><input type="text" name="id" id="id" placeholder="아이디를 입력하세요" size="30" autocomplete="off" required></p>
				<p><input type="email" name="email" id="email" placeholder="이메일을 입력하세요" size="30" autocomplete="off" required></p>
				<p><input type="submit" value="확인"></p>
			</form>
		</section>


	</div>


</body>
</html>