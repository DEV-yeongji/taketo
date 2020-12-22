create table alliance (
	no int auto_increment primary key, #기본기 auto_increment
	storeName varchar(50) not null,
	local varchar(20) not null,
	email varchar(50) not null,
	mobile varchar(20) not null,
	askForm text,
	wtime datetime default current_timestamp,
	readNotice int default 0,
	state int default 0
);
