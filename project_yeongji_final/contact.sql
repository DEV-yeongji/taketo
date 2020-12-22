create table contact (
	officeInfoTitle varchar(100) not null,	#회사소개타이틀
	officeInfo text,				#회사소개 내용
	contactHost varchar(10) not null,		#회사대표이름
	contactAddress varchar(50) not null,		#회사주소
	contactTelefon varchar(20),		#회사전화
	contactMobile varchar(20),		#회사모바일
	contactEmail varchar(50) not null,		#회사이메일
	termsOfUse text not null,				#이용약관
	personalInfo text not null				#개인정보처리
);
