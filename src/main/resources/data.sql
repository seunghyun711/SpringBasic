INSERT INTO article(id,title,content) VALUES(1,'안녕하세요','1111');
INSERT INTO article(id,title,content) VALUES(2,'감사해요','2222');
INSERT INTO article(id,title,content) VALUES(3,'잘있어요','3333');
INSERT INTO article(id,title,content) VALUES(4,'다시만나요','4444');

-- article 더미 데이터
INSERT INTO article(id,title,content) VALUES(5,'인생 영화?','댓글 ㄱ');
INSERT INTO article(id,title,content) VALUES(6,'너의 취미?','대앳글 ㄱ');
INSERT INTO article(id,title,content) VALUES(7,'니가 좋아하는 음식?','대앳그을 ㄱ');

-- comment 더미 데이터
-- 5번 게시글의 댓글
INSERT INTO comment(id,article_id,nickname, body) VALUES(1,5,'hong','토이스토리1'); -- 1번 댓글이 5번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(2,5,'kim','토이스토리2'); -- 2번 댓글이 5번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(3,5,'choi','토이스토리3'); -- 3번 댓글이 5번 게시글에 달림

-- 6번 게시글의 댓글
INSERT INTO comment(id,article_id,nickname, body) VALUES(4,6,'hong','숨쉬기'); -- 1번 댓글이 6번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(5,6,'kim','숨참기'); -- 2번 댓글이 6번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(6,6,'choi','숨기'); -- 3번 댓글이 6번 게시글에 달림

-- 7번 게시글의 댓글
INSERT INTO comment(id,article_id,nickname, body) VALUES(7,7,'hong','육회'); -- 1번 댓글이 4번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(8,7,'kim','육회비빔밥'); -- 2번 댓글이 4번 게시글에 달림
INSERT INTO comment(id,article_id,nickname, body) VALUES(9,7,'choi','육사시미'); -- 3번 댓글이 4번 게시글에 달림
