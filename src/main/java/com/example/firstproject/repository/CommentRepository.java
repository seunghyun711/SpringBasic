package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    /**
     *     아래 기능들을 사용하기 위해 sql 작성 벙법
     *     1. @Query를 사용하여 sql문 작성
     *     2, xml 파일 작성
     **/


    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT *" +
            " FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true) // 수행 시킬 쿼리문 작성
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회

    List<Comment> findByNickname(String nickname); // 해당 메서드와 연관된 sql을 xml로 작성한다. -> orm.xml
}
