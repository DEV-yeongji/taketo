create table User (
	id varchar(20) primary key,
	nickName varchar(20) not null,
	name varchar(20) not null,
	password varchar(20) not null,
	mTelecom varchar(10) not null,
	mobile varchar(15) not null,
	certifiCode varchar(10),
	email varchar(50) not null,
	bYear varchar(10),
	bMonth varchar(10),
	bDay varchar(10),
	postCode varchar(10),
	address1 varchar(30),
	address2 varchar(30),
	detailAddress varchar(50),
	gender char(1) not null,
	snsId varchar(50),
	point int default 0,
	interesting varchar(50) not null,
	introduce text,
	profile varchar(30),
	joinDate datetime default current_timestamp
	);
	
	