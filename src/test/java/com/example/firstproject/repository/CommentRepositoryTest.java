package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트에서 보여줄 이름
    void findByArticleId() {
        /* CASE 1 : 5번 게시글의 모든 댓글 조회 */
        // 입력 데이터 준비
        {
            Long articleId = 5L;

            // 실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(5L, "인생 영화?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "hong", "토이스토리1");
            Comment b = new Comment(2L, article, "kim", "토이스토리2");
            Comment c = new Comment(3L, article, "choi", "토이스토리3");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증 (예상 값과 일치하는지 검증)
            assertEquals(expected.toString(), comments.toString());
        }

        /* CASE 2 : 1번 게시글의 모든 댓글 조회 */
        // 입력 데이터 준비
        {
            Long articleId = 1L;

            // 실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(1L, "안녕하세요", "1111");
            List<Comment> expected = Arrays.asList();

            // 검증 (예상 값과 일치하는지 검증)
            assertEquals(expected.toString(), comments.toString(),"1번 게시글은 댓글이 없음");
        }
    }

    @Test
    void findByNickname() {
    }
}