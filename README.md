### 테이블 생성
---

    CREATE TABLE `members` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `login_id` varchar(30) DEFAULT NULL,
      `password` varchar(30) DEFAULT NULL,
      `nickname` varchar(30) DEFAULT NULL,
      `del_yn` varchar(1) DEFAULT 'N',
      `created_at` datetime DEFAULT current_timestamp(),
      `updated_at` datetime DEFAULT current_timestamp(),
      PRIMARY KEY (`id`)
    );

    CREATE TABLE `boards` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `title` varchar(50) NOT NULL,
      `content` varchar(1000) NOT NULL,
      `members_id` int(11) NOT NULL,
      `created_at` datetime DEFAULT current_timestamp(),
      `updated_at` datetime DEFAULT current_timestamp(),
      `view_cnt` int(11) NOT NULL DEFAULT 0,
      `del_yn` varchar(1) DEFAULT 'N',
      `category_id` int(11) NOT NULL,
      PRIMARY KEY (`id`)
    );

    CREATE TABLE `category` (
      `id` int(11) NOT NULL,
      `name` varchar(50) NOT NULL
    );
