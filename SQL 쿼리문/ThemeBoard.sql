# 해쉬태그 저장할 칼럼 = 배열로 받아오기

create table mainBoard (
	no int auto_increment primary key,
	title varchar(50) not null,
	id varchar(20) not null,
	nickName varchar(20) not null,
	contents text,
	wtime datetime default current_timestamp,
	hit int default 0,
	interesting varchar(20) not null,
	fileName1 varchar(50) not null,  		#파일이름1
	fileName2 varchar(50) not null,			#파일이름2
	fileName3 varchar(50) not null,			#파일이름3
	fileName4 varchar(50) not null,			#파일이름4
	address1 varchar(50) not null,
	address2 varchar(50),
	locationName varchar(50) not null,
	locationHoliday varchar(50), #장소휴뮤일
	locationPay varchar(20), #입장료(금액),
	locationTime varchar(20), #이용시간
	hashTag text,
	foreign key (id) references user (id) on delete cascade );