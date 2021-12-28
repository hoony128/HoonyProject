CREATE OR replace FUNCTION ENCRYPT(V_INPUT_STRING IN VARCHAR2 ,V_KEY IN VARCHAR2 := 'KHJAVA301')  
    RETURN RAW
    IS
         V_ORIGINAL_RAW  RAW(64);
         V_ENCRYTED_RAW  RAW(64);
         V_KEY_RAW  RAW(64);
    BEGIN 
         V_ORIGINAL_RAW := UTL_I18N.STRING_TO_RAW(V_INPUT_STRING,'AL32UTF8'); 
         --입력된 암호를 RAW타입으로 변경
         V_KEY_RAW := UTL_I18N.STRING_TO_RAW(V_KEY,'AL32UTF8');
         --오라클에서 제공해주는 패키지 함수 (문자열을 RAW타입으로 바꿔준다.)
          V_ENCRYTED_RAW := DBMS_CRYPTO.ENCRYPT ( 
                              src => V_ORIGINAL_RAW             
                              ,typ => DBMS_CRYPTO.DES_CBC_PKCS5
                              ,key => V_KEY_RAW
                              ,iv => NULL
                             );
      RETURN V_ENCRYTED_RAW;  --암호화된 값 리턴
END ENCRYPT;
-- 함수 확인
SELECT 
ENCRYPT('1234') ,
ENCRYPT('1234','qwerty123'),
ENCRYPT('1234','qwerty~!@')
FROM DUAL;


--------------------------------------------------------------------------------
-- 복호화 함수
CREATE OR replace FUNCTION DECRYPT(V_INPUT_STRING IN VARCHAR2 ,V_KEY IN VARCHAR2 := 'KHJAVA301')
  return varchar2
    is
      V_DECRYPTED_RAW     VARCHAR2(64); -- 해독되어진 값이므로  VARCHAR2
      V_DECRYPTED_CHAR    VARCHAR2(64);  -- 해독된 값 저장 변수
      V_KEY_RAW           VARCHAR2(64);  
    BEGIN
       V_KEY_RAW := UTL_I18N.STRING_TO_RAW(V_KEY,'AL32UTF8');  
       --복호화 변수에 저장
       V_DECRYPTED_RAW := DBMS_CRYPTO.DECRYPT(
         src => V_INPUT_STRING
        ,typ => DBMS_CRYPTO.DES_CBC_PKCS5
        ,key => V_KEY_RAW
        ,iv => NULL
        );
        
        V_DECRYPTED_CHAR := UTL_I18N.RAW_TO_CHAR(V_DECRYPTED_RAW, 'AL32UTF8'); --해독 되어진 값 저장
        RETURN V_DECRYPTED_CHAR; --해독된 값 리턴
END DECRYPT;
-- 함수 확인
SELECT 
DECRYPT(ENCRYPT('1234')) ,
DECRYPT(ENCRYPT('1234','qwerty123'), 'qwerty123'),
DECRYPT(ENCRYPT('1234','qwerty~!@'), 'qwerty~!@')
FROM DUAL;

-- -------------------------------------------------------------------------------------------
DROP SEQUENCE duty;
DROP TABLE LOGCHECK;

-- 직책
CREATE SEQUENCE duty_idx_seq;
CREATE TABLE duty(
idx NUMBER PRIMARY KEY,
duty varchar2(15) NOT NULL
);


-- 직급
CREATE SEQUENCE lev_idx_seq;
CREATE TABLE lev(
 idx NUMBER PRIMARY KEY,
 lev VARCHAR2(15) NOT NULL
);


-- 휴직중인지 재직중인지
CREATE SEQUENCE state_idx_seq;
CREATE TABLE state(
 idx NUMBER PRIMARY KEY,
 state varchar2(15) NOT NULL
);

-- 고용경로(신입/경력, 공채/특채)
CREATE SEQUENCE hirestate_idx_seq;
CREATE TABLE hirestate(
 idx NUMBER PRIMARY KEY,
 hirestate varchar2(15) NOT NULL
);

-- 부서명
CREATE SEQUENCE depart_idx_seq;
CREATE TABLE depart(
 idx NUMBER PRIMARY KEY,
 depart VARCHAR2(25) NOT NULL
);

DROP TABLE INFO_EMP;
DROP SEQUENCE Info_idx_seq;
-- 인사정보
CREATE SEQUENCE Info_idx_seq;
CREATE TABLE Info_EMP(
	Info_idx NUMBER PRIMARY KEY, -- 번호
	Id_emp NUMBER NOT NULL,		-- 사번
	password varchar2(50) NOT NULL, -- 비밀번호
	name varchar2(20) NOT NULL,		-- 이름
	first_phone varchar2(5) NOT NULL, -- 핸드폰 앞자리
	mid_phone	varchar2(5) NOT NULL, -- 핸드문 중간자리
	last_phone	varchar2(5) NOT NULL, -- 핸드폰 마지막 자리
	email varchar2(50) NOT NULL, 	  -- 이메일
	state NUMBER NOT NULL,			  -- 직무상태(휴직or 재직 or 퇴사)	
	lev NUMBER NOT null,			  -- 직급
	depart NUMBER NOT NULL,			  -- 부서
	duty NUMBER NOT NULL,			  -- 직책
	zipcode varchar2(10) NOT NULL,	  -- 우편번호
	addr1 varchar2(100) NOT NULL,     -- 주소
	addr2 varchar2(100) NOT NULL,	  -- 상세주소
	hiredate timestamp NOT NULL,      -- 고용일
	hirestate NUMBER NOT NULL,        -- 고용상태
	bank varchar2(10) NOT NULL,       -- 은행(월급)
	account varchar2(25) NOT NULL,    -- 계좌
	temp1 varchar2(100) NOT NULL,     -- 인증코드 용
	birth timestamp NOT NULL    
);
INSERT INTO INFO_EMP VALUES(Info_idx_seq.nextval,1234,'0000','최동훈','010','3932','6549','hoony128@gmail.com','1','5','1','3','123',' ',' ','1991/11/28','1',' ',' ',' ','1991/11/28');
CREATE SEQUENCE logCheck_idx_seq;
CREATE TABLE logCheck(
	idx NUMBER PRIMARY KEY,
	Id_Emp NUMBER NOT NULL,
	logType NUMBER NOT NULL,
	ip varchar2(40) NOT NULL,
	logDate TIMESTAMP NOT NULL
);




DROP TABLE EVALUATIONREAD;
DROP SEQUENCE evaluationsave_idx_seq;

--고과점수 테이블
CREATE SEQUENCE evaluation_idx_seq;
CREATE TABLE evaluation(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT null, --평가자 사번
	name_p VARCHAR2(20) NOT NULL, --평가자 이름
	score NUMBER NOT NULL, --평가점수
	reason VARCHAR2 (1000) NOT NULL,
	Id_emp_v NUMBER NOT NULL, --피평가자 사번
	name_v VARCHAR2(20) NOT NULL, --피평가자 이름
	savedate TIMESTAMP NOT NULL, --평가 시간
	dname varchar2(20) NOT NULL,
	lev NUMBER NOT NULL,
	DUTY NUMBER NOT NULL,
	lev_l varchar2(20) NOT NULL,
	duty_d varchar2(20) NOT NULL
);

INSERT INTO EVALUATIONREAD(idx, ID_EMP_P, NAME_P,SCORE,REASON,ID_EMP_V,NAME_V,SAVEDATE,DNAME,LEV,DUTY,LEV_L,DUTY_D)
VALUES(evaluationread_idx_seq.nextval,12345,'테스트',80,'제출ㄱㄱ',12345678,'test1','2018/06/01','인사',1,1,'사원','팀원');
INSERT INTO EVALUATIONREAD(idx, ID_EMP_P, NAME_P,SCORE,REASON,ID_EMP_V,NAME_V,SAVEDATE,DNAME,LEV,DUTY,LEV_L,DUTY_D)
VALUES(evaluationread_idx_seq.nextval,12345,'테스트',80,'제출ㄱㄱ',12345678,'test1','2017/12/01','인사',1,1,'사원','팀원');
INSERT INTO EVALUATIONREAD(idx, ID_EMP_P, NAME_P,SCORE,REASON,ID_EMP_V,NAME_V,SAVEDATE,DNAME,LEV,DUTY,LEV_L,DUTY_D)
VALUES(evaluationread_idx_seq.nextval,12345,'테스트',80,'제출ㄱㄱ',12345678,'test1','2018/06/01','인사',1,1,'사원','팀원');
SELECT * FROM EVALUATIONREAD;

-- 진급시에 고과기록을 초기화한 것을 담는 테이블
CREATE SEQUENCE evaluationread_idx_seq;
CREATE TABLE evaluationread(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT null, --평가자 사번
	name_p VARCHAR2(20) NOT NULL, --평가자 이름
	score NUMBER NOT NULL, --평가점수
	reason VARCHAR2 (1000) NOT NULL,
	Id_emp_v NUMBER NOT NULL, --피평가자 사번
	name_v VARCHAR2(20) NOT NULL, --피평가자 이름
	savedate TIMESTAMP NOT NULL, --평가 시간
	dname varchar2(20) NOT NULL,
	lev NUMBER NOT NULL,
	DUTY NUMBER NOT NULL,
	lev_l varchar2(20) NOT NULL,
	duty_d varchar2(20) NOT NULL
);
-- 고과점수 저장테이블
CREATE SEQUENCE evaluationsave_idx_seq;
CREATE TABLE evaluationsave(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT null, --피평가자 사번
	name_p VARCHAR2(20) NOT NULL, --피평가자 이름
	score NUMBER NOT NULL, --평가점수
	reason VARCHAR2 (1000) NOT NULL,
	Id_emp_v NUMBER NOT NULL, --평가자 사번
	name_v VARCHAR2(20) NOT NULL, --평가자 이름
	dname varchar2(20) NOT NULL,
	lev NUMBER NOT NULL,
	DUTY NUMBER NOT NULL,
	lev_l varchar2(20) NOT NULL,
	duty_d varchar2(20) NOT NULL
);

--진급 후(or 입사 후) 최근 4회의 인사고과 평균점수가 75점수 이상인 사람 : 승진평가 대상자
SELECT ID_EMP_V Id_emp, NAME_V name, sum(SCORE) score, DNAME,DUTY,LEV,DUTY_D,LEV_L
FROM (SELECT * FROM EVALUATION WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
GROUP BY ID_EMP_V, NAME_V,DNAME,DUTY,LEV,DUTY_D,LEV_L
HAVING sum(SCORE)> 50;



-- 리스트로 가져올 때!
SELECT
	b.dname,b.ID_EMP ,a.NAME ,a.EMAIL ,b.duty_d ,b.lev_l ,b.score
FROM
	INFO_EMP A,
	(SELECT ID_EMP_V Id_emp, sum(SCORE) score, lev_l, duty_d,dname 
		FROM (SELECT * FROM EVALUATIONREAD WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
		GROUP BY ID_EMP_V,Lev_l,duty_d,dname
		HAVING sum(SCORE)> 75)B
WHERE a.ID_EMP =b.Id_emp;

--1개 만 가져올 때
SELECT
	a.ID_EMP,a.NAME,a.EMAIL,b.duty_d,b.lev_l,b.score
FROM
	INFO_EMP A,
	(SELECT ID_EMP_V Id_emp, NAME_V name, sum(SCORE) score, DNAME,DUTY,LEV,DUTY_D,LEV_L
		FROM (SELECT * FROM EVALUATIONREAD WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
		GROUP BY ID_EMP_V, NAME_V,DNAME,DUTY,LEV,DUTY_D,LEV_L
		HAVING sum(SCORE)> 75)B
WHERE a.ID_EMP =b.Id_emp AND a.ID_EMP=1234567;



-- 테이블 생성!(승진테스트)
DROP TABLE PROMOTIONTESTSAVE;
DROP SEQUENCE promotiontestsave_idx_seq;

CREATE SEQUENCE promotiontestRead_idx_seq;
CREATE TABLE promotiontestRead(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT NULL, --평가자 사번
	name_p varchar2(20) NOT NULL, --평가자이름
	dname varchar2(20) NOT NULL, --소속부서
	lev_l varchar2(20) NOT NULL, --직급
	duty_d varchar2(20) NOT NULL, --직책
	Id_emp_v NUMBER NOT NULL, --사번
	name_v varchar2(20) NOT NULL, --평가받는사람이름
	Testscore1 NUMBER NOT NULL,
	Testscore2 NUMBER NOT NULL,
	Testscore3 NUMBER NOT NULL,
	Testscore4 NUMBER NOT NULL,
	saveDate timestamp DEFAULT SYSDATE,
	TYPE NUMBER
);
CREATE SEQUENCE promotiontestsave_idx_seq;
CREATE TABLE promotiontestSave(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT NULL, --평가자 사번
	name_p varchar2(20) NOT NULL, --평가자이름
	dname varchar2(20) NOT NULL, --소속부서
	lev_l varchar2(20) NOT NULL, --직급
	duty_d varchar2(20) NOT NULL, --직책
	Id_emp_v NUMBER NOT NULL, --사번
	name_v varchar2(20) NOT NULL, --평가받는사람이름
	Testscore1 NUMBER NOT NULL,
	Testscore2 NUMBER NOT NULL,
	Testscore3 NUMBER NOT NULL,
	Testscore4 NUMBER NOT NULL,
	saveDate timestamp DEFAULT SYSDATE 
);


-- 승진시험결과
	SELECT
		d.DNAME, d.ID_EMP,d.name ,d.score, c.testscore,((d.score*0.45)+(c.testscore*0.55))Finalscore,d.lev_l,d.DUTY_D
	FROM 
		(SELECT ID_EMP_V,((sum(TESTSCORE1)+sum(TESTSCORE2)+sum(TESTSCORE3)+sum(TESTSCORE4))/3) testscore FROM PROMOTIONTESTREAD GROUP BY ID_EMP_V)C,
		(SELECT
			b.dname, a.NAME , b.ID_EMP , b.duty_d , b.lev_l , b.score
		FROM
			INFO_EMP A,
				(SELECT ID_EMP_V Id_emp, sum(SCORE)/4 score, lev_l, duty_d,dname
				FROM (SELECT * FROM EVALUATIONREAD WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
				GROUP BY ID_EMP_V,Lev_l,duty_d,dname
				HAVING sum(SCORE)/4> 75)B	
		WHERE a.ID_EMP =b.Id_emp)D 	
	WHERE c.ID_EMP_V=d.ID_EMP
	ORDER BY finalscore desc;



-- 승진대상자 구별!!
	SELECT
		d.DNAME, d.ID_EMP,d.name ,d.score, c.testscore,((d.score*0.45)+(c.testscore*0.55))Finalscore,d.lev_l,d.DUTY_D
	FROM 
		(SELECT ID_EMP_V,(avg(TESTSCORE1)+avg(TESTSCORE2)+avg(TESTSCORE3)+Avg(TESTSCORE4))testscore FROM PROMOTIONTESTREAD GROUP BY ID_EMP_V)C,
		(SELECT
			b.dname, a.NAME , b.ID_EMP , b.duty_d , b.lev_l , b.score
		FROM
			INFO_EMP A,
				(SELECT ID_EMP_V Id_emp, sum(SCORE)/4 score, lev_l, duty_d,dname
				FROM (SELECT * FROM EVALUATIONREAD WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
				GROUP BY ID_EMP_V,Lev_l,duty_d,dname
				HAVING sum(SCORE)/4> 75)B	
		WHERE a.ID_EMP =b.Id_emp)D 	
	WHERE ((d.score*0.45)+(c.testscore*0.55))>80 and c.ID_EMP_V=d.ID_EMP
	ORDER BY finalscore desc;

	
SELECT ID_EMP_V,((avg(TESTSCORE1)+avg(TESTSCORE2)+avg(TESTSCORE3)+Avg(TESTSCORE4)))testscore FROM PROMOTIONTESTREAD GROUP BY ID_EMP_V;



-- 승진평가 대상자 중 평가결과가 안올라온 사원
SELECT ID_EMP_V Id_emp, sum(SCORE)/4 score, lev_l, duty_d,dname
FROM (SELECT * FROM EVALUATIONREAD WHERE (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(TO_CHAR(SAVEDATE,'yyyy'))<=1))
GROUP BY ID_EMP_V,Lev_l,duty_d,dname
HAVING sum(SCORE)/4> 75 AND ID_EMP_V NOT IN(SELECT ID_EMP_V FROM PROMOTIONTESTREAD GROUP BY ID_EMP_V);



-- 이력 저장하는 테이블

CREATE SEQUENCE history_idx_seq;
CREATE TABLE history(
	idx NUMBER PRIMARY KEY,
	id_emp NUMBER NOT NULL, --사번
	TYPE NUMBER NOT NULL, -- 1. 승진,2. 부서이동, 3. 직책 이동
	lev NUMBER NOT NULL,
	lev_after NUMBER NOT NULL,
	depart NUMBER NOT NULL,--
	updatedate timestamp NOT NULL ,
	state NUMBER,
	duty NUMBER
);

DROP TABLE HISTORY;
DROP SEQUENCE history_idx_seq;
-- 특별승진 테이블
CREATE SEQUENCE specialpromotion_idx_seq;
CREATE TABLE specialpromotion(
	idx NUMBER PRIMARY KEY,
	dname varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	duty varchar2(20) NOT NULL,
	lev_now  varchar2(20) NOT NULL,
	lev_after NUMBER NOT NULL,
	Id_emp NUMBER NOT NULL,
	reason varchar(1500) NOT NULL,
	TYPE NUMBER NOT NULL,
	nowlev NUMBER NOT NULL
);

DROP SEQUENCE specialpromotion_idx_seq;
DROP TABLE specialpromotion;

--퇴사처리 테이블
CREATE SEQUENCE resignation_idx_seq;
CREATE TABLE resignation(
	idx NUMBER PRIMARY KEY,
	Id_emp_p NUMBER NOT null, --처리자 사번
	name_p VARCHAR2(20) NOT NULL, --처리자 이름
	reason VARCHAR2 (1000) NOT NULL, --퇴직사유
	Id_emp_v NUMBER NOT NULL, --퇴사자 사번
	name_v VARCHAR2(20) NOT NULL, --퇴사자 이름
	savedate TIMESTAMP NOT NULL, --퇴사 신청 시간
	dname varchar2(20) NOT NULL, -- 부서
	lev NUMBER NOT NULL, --사번
	DUTY NUMBER NOT NULL, -- 직책
	lev_l varchar2(20) NOT NULL, -- 직급 
	duty_d varchar2(20) NOT NULL, -- -- 직책
	TYPE NUMBER NOT NULL
);
DROP TABLE NOTICE;
DROP SEQUENCE notice_idx_seq;

-- 공지사항 테이블
CREATE SEQUENCE notice_idx_seq;
CREATE TABLE notice(
	idx NUMBER PRIMARY KEY,
	Id_emp NUMBER NOT NULL,
	subject VARCHAR2(300) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	dname VARCHAR2(20) NOT NULL,
	regdate TIMESTAMP NOT NULL,
	hit NUMBER NOT NULL,
	TYPE NUMBER NOT null 
	);

-- 게시판체크 테이블(모든게시판의 글 등록, 수정, 삭제시 이곳에 저장하자!)
DROP SEQUENCE boardCheck_idx_seq;
DROP TABLE BOARDCHECK;
CREATE SEQUENCE boardCheck_idx_seq;
CREATE TABLE boardCheck(
	idx NUMBER PRIMARY KEY,
	Id_emp NUMBER NOT NULL,
	ip VARCHAR2(35) NOT NULL,
	boardtype varchar2(30) NOT NULL, 
	TYPE varchar2(20) NOT NULL,
	updatedate TIMESTAMP NOT NULL,
	board_idx NUMBER NOT NULL
);

DROP TABLE FILEUPLOAD;
DROP SEQUENCE fileupload_idx_seq;

-- 파일 업로드 테이블
CREATE SEQUENCE fileupload_idx_seq;
CREATE TABLE fileupload(
	idx NUMBER PRIMARY KEY,
	boardTYPE VARCHAR2(30),
	board_idx NUMBER,
	ofile VARCHAR2(2000) NOT NULL,
	sfile VARCHAR2(2000) NOT NULL,
	savedate TIMESTAMP NOT null
);

-- 업무도움방 게시판 테이블
DROP TABLE HELPDESK;
DROP SEQUENCE helpdesk_idx_seq;

CREATE SEQUENCE helpdesk_idx_seq;
CREATE TABLE helpdesk(
	idx NUMBER PRIMARY KEY,
	Id_emp NUMBER NOT NULL,
	subject VARCHAR2(300) NOT NULL,
	content VARCHAR2(2000) NOT NULL,
	dname VARCHAR2(20) NOT NULL,
	name varchar2(20) NOT null,
	lev varchar2(20) NOT NULL,
	regdate TIMESTAMP NOT NULL,
	hit NUMBER NOT NULL,
	TYPE NUMBER NOT null 	
);

-- 업무도움방 게시판 댓글
DROP SEQUENCE helpcomment_idx_seq;
DROP TABLE HELPCOMMENT;

CREATE SEQUENCE helpcomment_idx_seq;
CREATE TABLE helpcomment(
	idx NUMBER PRIMARY KEY,
	helpdesk_idx NUMBER NOT NULL,
	Id_emp NUMBER NOT NULL,
	name varchar2(20) NOT NULL,
	lev varchar2(20) NOT NULL,
	subject varchar2(500) NOT NULL,
	regdate timestamp NOT NULL,
	TYPE NUMBER NOT NULL,
	dname varchar2(20) NOT NULL
);

-- 익명게시판
CREATE SEQUENCE anonymous_idx_seq;
CREATE TABLE anonymous(
	idx NUMBER PRIMARY KEY,
	id varchar2(50) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(300) NOT NULL,
	content varchar2(2000) NOT NULL,
	regdate timestamp NOT NULL,
	TYPE NUMBER NOT NULL,
	hit NUMBER NOT null
);
-- 익명게시판 댓글
CREATE SEQUENCE anonymouscomment_idx_seq;
CREATE TABLE anonymouscomment(
	idx NUMBER PRIMARY KEY,
	anonymous_idx NUMBER NOT NULL, 
	id varchar2(50) NOT NULL,
	password varchar2(200) NOT NULL,
	content varchar2(500) NOT NULL,
	regdate timestamp NOT NULL,
	TYPE NUMBER NOT NULL
);

-- Filehistory
CREATE SEQUENCE filehistory_idx_seq;
CREATE TABLE filehistory(
	idx NUMBER PRIMARY KEY,
	boardTYPE VARCHAR2(30),
	board_idx NUMBER,
	ofile VARCHAR2(2000) NOT NULL,
	sfile VARCHAR2(2000) NOT NULL,
	savedate TIMESTAMP NOT null
);


	select i.*, d.depart depart_d, l.lev lev_l, du.duty duty_du, 
	(SELECT COUNT(*) FROM SPECIALPROMOTION WHERE TYPE=1 AND ID_EMP=i.ID_EMP)rw
	from Info_emp i, depart d,lev l,duty du	 
	where i.depart=d.idx and i.lev=l.idx and i.duty=du.idx and i.state=1;


-- 데이터 추가 추가
-- 인 사
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201910001,'0000','테스트인사1','010','1234','1234', 'test201910001@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201910002,'0000','테스트인사2','010','2234','1234', 'test201910002@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201910003,'0000','테스트인사3','010','3234','1234', 'test201910003@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201910004,'0000','테스트인사4','010','3234','1234', 'test201910004@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201910005,'0000','테스트인사5','010','3234','1234', 'test201910005@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');

-- 총무
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920001,'0000','테스트총무1','010','1234','2234', 'test201910001@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920002,'0000','테스트총무2','010','2234','2234', 'test201910002@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920003,'0000','테스트총무3','010','3234','2234', 'test201910003@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920004,'0000','테스트총무4','010','4234','2234', 'test201910004@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920005,'0000','테스트총무5','010','5234','2234', 'test201910005@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201920006,'0000','테스트총무6','010','6234','2234', 'test201910006@gmail.com',1,1, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');

-- 개발
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930001,'0000','테스트개발1','010','1234','3234', 'test201930001@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930002,'0000','테스트개발2','010','1234','3234', 'test201930002@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930003,'0000','테스트개발3','010','3234','3234', 'test201930003@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930004,'0000','테스트개발4','010','4234','3234', 'test201930004@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930005,'0000','테스트개발5','010','5234','3234', 'test201930005@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201930006,'0000','테스트개발6','010','6234','3234', 'test201930006@gmail.com',1,3, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');

-- 회계

insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940001,'0000','테스트회계1','010','1234','4234', 'test201940001@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940002,'0000','테스트회계2','010','2234','4234', 'test201940002@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940003,'0000','테스트회계3','010','2234','4234', 'test201940003@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940004,'0000','테스트회계4','010','4234','4234', 'test201940004@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940005,'0000','테스트회계5','010','5234','4234', 'test201940005@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201940006,'0000','테스트회계6','010','6234','4234', 'test201940006@gmail.com',1,4, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');


-- 영업
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950001,'0000','테스트영업1','010','1234','5234', 'test201950001@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950002,'0000','테스트영업2','010','2234','5234', 'test201950002@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950003,'0000','테스트영업3','010','3234','5234', 'test201950003@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950004,'0000','테스트영업4','010','4234','5234', 'test201950004@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950005,'0000','테스트영업5','010','5234','5234', 'test201950005@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');
insert into Info_EMP(Info_idx,Id_emp,password,name,first_phone,mid_phone,last_phone,email,lev,depart,zipcode,addr1,addr2,hiredate,bank,account,temp1,state,hirestate,duty,birth)
VALUES (Info_idx_seq.nextval,201950006,'0000','테스트영업6','010','6234','5234', 'test201950006@gmail.com',1,5, ' ',' ',' ','2019/01/13',' ',' ',' ',1, 1, 1,'1991/11/28');

--고과점수 올려 놓기
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950002, '테스트영업2','영업',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950002, '테스트영업2','영업',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950002, '테스트영업2','영업',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950002, '테스트영업2','영업',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950003, '테스트영업3','영업',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950003, '테스트영업3','영업',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950003, '테스트영업3','영업',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950003, '테스트영업3','영업',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950004, '테스트영업4','영업',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950004, '테스트영업4','영업',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950004, '테스트영업4','영업',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201950004, '테스트영업4','영업',1,1,'팀원','사원','2018/01/13');


insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940002, '테스트회계2','회계',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940002, '테스트회계2','회계',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940002, '테스트회계2','회계',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940002, '테스트회계2','회계',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940003, '테스트회계3','회계',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940003, '테스트회계3','회계',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940003, '테스트회계3','회계',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940003, '테스트회계3','회계',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940004, '테스트회계4','회계',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940004, '테스트회계4','회계',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940004, '테스트회계4','회계',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201940004, '테스트회계4','회계',1,1,'팀원','사원','2018/01/13');


insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930002, '테스트개발2','개발',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930002, '테스트개발2','개발',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930002, '테스트개발2','개발',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930002, '테스트개발2','개발',1,1,'팀원','사원','2018/01/13');


insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930003, '테스트개발3','개발',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930003, '테스트개발3','개발',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930003, '테스트개발3','개발',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201930003, '테스트개발3','개발',1,1,'팀원','사원','2018/01/13');


insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201920002, '테스트총무2','총무',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201920002, '테스트총무2','총무',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201920002, '테스트총무2','총무',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201920002, '테스트총무2','총무',1,1,'팀원','사원','2018/01/13');


insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910002, '테스트인사2','인사',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910002, '테스트인사2','인사',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910002, '테스트인사2','인사',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910002, '테스트인사2','인사',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910003, '테스트인사3','인사',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910003, '테스트인사3','인사',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910003, '테스트인사3','인사',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910003, '테스트인사3','인사',1,1,'팀원','사원','2018/01/13');

insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910004, '테스트인사4','인사',1,1,'팀원','사원','2018/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910004, '테스트인사4','인사',1,1,'팀원','사원','2017/01/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910004, '테스트인사4','인사',1,1,'팀원','사원','2017/06/13');
insert into evaluationread (idx, Id_emp_p, name_p, score, reason, Id_emp_v, name_v,dname,duty,lev,duty_d,lev_l,savedate)
values(evaluationread_idx_seq.nextval, 7777, '테스트', 80, '테스트중', 201910004, '테스트인사4','인사',1,1,'팀원','사원','2018/01/13');


		SELECT
			a.*,b.dw
		FROM
			NOTICE A,
			(select 
				type,(TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyymmdd'))) dw 
			from 
				NOTICE 
			where 
				type=2 and (TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyyMMdd')))>=5)B
		WHERE  a.TYPE= b.type; 
		
		
		
		select 
				"TYPE",IDX,(TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyymmdd'))) dw 
			from 
				NOTICE 
			where 
				type=2 and (TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyyMMdd')))>=5;
			
			
		SELECT
			a.IDX
		FROM
			NOTICE A,
			(select 
				type,(TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyymmdd'))) dw 
			from 
				NOTICE 
			where 
				type=2 and (TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyyMMdd')))>=10)B
		WHERE  a.TYPE= b.TYPE
	;		  

	
	SELECT
			a.*,b.dw
		FROM
			NOTICE A,
			(select 
				type,(TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyymmdd'))) dw 
			from 
				NOTICE 
			where 
				type=2 and (TO_NUMBER(TO_CHAR(SYSDATE ,'yyyyMMdd'))- TO_NUMBER(TO_CHAR(REGDATE ,'yyyyMMdd')))>=10)B
		WHERE  a.TYPE= b.TYPE;
	
	SELECT * FROM NOTICE;
	
	
	CREATE SEQUENCE testing_idx_seq;


	INSERT INTO TESTING (idx,ref) VALUES (testing_idx_seq.nextval,3);
			