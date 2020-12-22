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
<title>Take to.- 회원가입</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="css/TermsofUse.css">
</head>
<body>
	<header>
		<h1 class="logo"><a href="index.do">Take to.</a></h1>
	</header>
	<hr />
	<section id="termsofUse">
		<h1 class="logoSub"><i class="fas fa-user-plus"></i> 회원가입</h1>
        <h2>* Take to. 이용약관</h2>
        <div class="termsofUseForm">
            <span>
                ${dto.termsOfUse }
            </span>
        </div>
        <h2> * Take to. 개인정보 수집 및 이용 </h2>
        <div class="termsofUseForm">
            <span>
                ${dto.personalInfo }
            </span>
        </div>
        
         <form method="post" action="userJoin.do">
         	<input type="checkbox" id="agree" required>
       		<label for="agree" class="agree">Take to. 이용약관, 개인정보 수집 및 이용,위치정보 이용약관 에 모두 동의합니다.</label>
        <br>
        <br>
   		<div class="btn">
	         <a href="index.do"><i class="fas fa-home"></i> 홈 화면</a>
	      	 <input type="submit" value="다음페이지 ▶">
      	 </div>
        </form> 
    </section>
</body>
</html>