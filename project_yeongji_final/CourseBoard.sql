#코스게시판 필요한것

create table localBoard (
	no int auto_increment primary key,
	nickName varchar(20) not null,
	id varchar(20) not null,
	title varchar(100),
	whyRecom varchar(100),
	location_Name varchar(100) not null,
	location_contents text,
	location_img1 varchar(50) not null,
	location_img2 varchar(50) not null,
	location_img3 varchar(50) not null,
	location_img4 varchar(50) not null,
	location_address varchar(50)  not null,
	city varchar(50) not null,
	place varchar(50) not null,
	hit int default 0,
	wtime datetime default current_timestamp,
	foreign key (id) references user (id) on delete cascade 
);