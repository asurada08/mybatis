# 내맘대로 게시판

Java & Spring Boot 기반의 커뮤니티 게시판 프로젝트입니다. CRUD, 회원 인증, 댓글/대댓글, 페이징, 검색 기능 등을 직접 구현하며 웹 개발 전반의 흐름을 경험하고, 실무 역량을 향상시키기 위해 만든 개인 프로젝트입니다.

## 기술 스택

- **Backend**: Java, Spring Boot, MyBatis, MariaDB
- **Frontend**: HTML, CSS, JavaScript (jQuery), Ajax

## 주요 기능

- 회원가입, 로그인, 회원정보 수정, 회원탈퇴
- 게시글 작성, 수정, 삭제 / 댓글 및 대댓글 기능
- 게시글 페이징 처리 및 검색 기능
- 로그인 세션 기반 인증, URL 접근 제한 처리

## 개발 목적

이전 팀 프로젝트에서 JPA를 사용하여 직접 SQL을 작성해볼 기회가 부족했던 점을 보완하고, 웹 개발의 기본적인 흐름을 전체적으로 직접 경험하고자 진행한 개인 프로젝트입니다. 특히 서버 측 인증/인가 로직을 구현하며 보안 처리에 대한 중요성을 체감할 수 있었습니다.

## 느낀 점

모든 기능을 직접 설계하고 구현하면서 웹 애플리케이션의 구조를 더 깊이 이해하게 되었고, 프론트엔드와 백엔드를 아우르는 문제 해결 능력을 키울 수 있었습니다.

## 데이터베이스 테이블 생성 쿼리

프로젝트에서 사용한 데이터베이스의 테이블 생성 쿼리는 아래와 같습니다.

```sql
-- members 테이블 생성
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `del_yn` char(1) DEFAULT 'N',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`)
);

-- boards 테이블 생성
CREATE TABLE `boards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `members_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `view_cnt` int(11) DEFAULT 0,
  `del_yn` char(1) DEFAULT 'N',
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- comments 테이블 생성
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `members_id` int(11) NOT NULL,
  `boards_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `del_yn` char(1) DEFAULT 'N',
  `reparent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- category 테이블 생성
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);
