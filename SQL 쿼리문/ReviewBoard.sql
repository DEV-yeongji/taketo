create table reviewBoard(
	no int auto_increment primary key,
	id varchar(50) not null,
	nickName varchar(50)  not null,
	title varchar(100)  not null,
	contents text  not null,
	wtime datetime default current_timestamp,
	img varchar(50)  not null,
	foreign key (id) references user (id) on delete cascade 
	);
