

/* Create Tables */

CREATE TABLE goods
(
	-- 상품코드 - 년월일일련번호 형식
	gCode char(12) NOT NULL,
	-- 상품명
	gName varchar2(100) NOT NULL,
	PRIMARY KEY (gCode)
);


CREATE TABLE member
(
	id varchar2(50) NOT NULL,
	pw varchar2(100) NOT NULL,
	name varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE orders
(
	-- 주문번호
	oNo char(20) NOT NULL,
	-- 상품코드 - 년월일일련번호 형식
	gCode char(12) NOT NULL,
	id varchar2(50) NOT NULL,
	PRIMARY KEY (oNo, gCode)
);


CREATE TABLE tbl_board
(
	-- 글번호
	bno number NOT NULL,
	titlte varchar2(100) NOT NULL,
	-- 글내용
	content varchar2(1000) NOT NULL,
	id varchar2(50) NOT NULL,
	PRIMARY KEY (bno)
);



/* Create Foreign Keys */

ALTER TABLE orders
	ADD FOREIGN KEY (gCode)
	REFERENCES goods (gCode)
;


ALTER TABLE orders
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE tbl_board
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;



