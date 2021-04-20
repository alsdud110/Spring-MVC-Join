drop table member;

create table member(
	id varchar(100) not null,
	pw varchar(100) not null,
	name varchar(100),
	tel varchar(100),
	birth varchar(100),
	addr_road varchar(100),
	addr_detail varchar(100),
	email varchar(100),
	regdate date
);

select * from member;

select birth from member where id = "alsdud110";

insert into member values("alsdudlove00", "dudlove00", "황민영", "서울시 마포구", "010-5064-3775", "alsdud110@naver.com", now());