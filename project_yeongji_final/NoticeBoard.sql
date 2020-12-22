create table noticeEventBoard (
	no int auto_increment primary key,
	subject varchar(30) not null,
	title varchar(50) not null,
	id varchar(10) not null,
	img varchar(50),
	term date not null,
	writeTime datetime default current_timestamp,
	contents text,
	hit int default 0,
	foreign key (id) references admin (id) on delete cascade 
	);

