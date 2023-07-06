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
