create table todayVisited ( 
	day datetime default current_timestamp,
	visited int not null,
	ipAddress varchar(50) not null
	);