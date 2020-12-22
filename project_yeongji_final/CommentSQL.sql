create table comment (
	commentNo int auto_increment primary key,
	no int not null, #게시물번호
	writeUser varchar(20) not null,
	contents text,
	wtime datetime default current_timestamp,
	writeUserId varchar(20) not null,
	foreign key (no) references mainBoard (no) on delete cascade );