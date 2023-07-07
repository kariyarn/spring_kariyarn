-- 사용자(회원) 정보를 저장할 테이블
CREATE TABLE board_member(
    id VARCHAR2(100) PRIMARY KEY,
    pwd VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    profile VARCHAR2(100), --프로필 이미지 경로를 저장할 칼럼
    intro VARCHAR2(100), -- 자기소개
    regdate DATE
);
-- 회원가입 프라이머리 키
CREATE SEQUENCE board_member_seq;

-- 영화 리뷰
CREATE TABLE board_movie (
  num NUMBER PRIMARY KEY,
  writer VARCHAR2(100), -- 작성자도 포함
  title VARCHAR2(100) UNIQUE,
  caption VARCHAR2(200),
  imagePath VARCHAR2(100), -- 이미지 경로
  regdate DATE, -- 업로드 날짜
  thumsup NUMBER, -- 추천수
  thumsdown NUMBER -- 비추수
);
-- 영화 프라이머리 키
CREATE SEQUENCE board_movie_seq;

CREATE TABLE board_review(
    num NUMBER PRIMARY KEY,
		title VARCHAR2(200),
    writer VARCHAR2(100),
    review VARCHAR2(600),
    ref_group NUMBER,
    rate NUMBER,
    regdate DATE,
    CONSTRAINT rate_range CHECK (rate >= 0 AND rate <= 5)
);

-- 리뷰의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE review_seq;

-- 게시글을 저장할 테이블 
CREATE TABLE board_commu(
    num NUMBER PRIMARY KEY, --글번호
    writer VARCHAR2(100) NOT NULL, --작성자 (로그인된 아이디)
    title VARCHAR2(100) NOT NULL, --제목
    content CLOB, --글 내용
    viewCount NUMBER, -- 조회수
    regdate DATE --글 작성일
);
-- 게시글의 번호를 얻어낼 시퀀스
CREATE SEQUENCE board_commu_seq;

-- 댓글을 저장할 테이블
CREATE TABLE board_commu_comment(
    num NUMBER PRIMARY KEY, --댓글의 글번호
    writer VARCHAR2(100), --댓글 작성자의 아이디
    content VARCHAR2(500), --댓글 내용
    target_id VARCHAR2(100), --댓글의 대상자 아이디
    ref_group NUMBER, -- 원글의 글번호
    comment_group NUMBER, -- 댓글의 그룹번호
    deleted CHAR(3) DEFAULT 'no', --  삭제된 댓글인지 여부 'yes' or 'no'
    regdate DATE
);
-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_commu_comment_seq;
