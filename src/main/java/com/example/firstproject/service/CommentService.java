package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 댓글 목록 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환 : 엔티티 -> dto형태
//        List<CommentDto> dtos = new ArrayList<>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c); // 클래스 메서드 호출
//            dtos.add(dto);
//        }

        // 반환(변환 과정에서 for문을 스트림을 사용한 방식으로 변경
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional // create()는 db를 건드리기 때문에 Transcational하여 중간에 문제 발생시 롤백하게 한다.
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("댓글 생성 실패, 대상 게시글이 없다.")); // 없다면 에러 발생

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        // 댓글 엔티티를 db로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글이 없다."));

        // 댓글 수정
        target.patch(dto);

        // 수정된 댓글을 db에 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패, 대상이 없다."));

        // 댓글 DB에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 dto로 반환
        return CommentDto.createCommentDto(target);
    }
}
