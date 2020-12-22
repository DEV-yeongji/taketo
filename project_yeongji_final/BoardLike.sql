create table boardLike (
	boardNum int,
	id varchar(20),
	foreign key (boardNum) references localBoard (no) on delete cascade 
);
#지역별 게시판이 삭제되면 찜도 삭제되기.