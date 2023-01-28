package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

// 댓글 엔티티
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id") // 테이블에서 연결된 대상의 정보의 컬럼, article_id컬럼에 Article의 대표값 지정
    private Article article; // 댓글이 위치할 게시글(여러 개의 댓글이 하나의 Article에 달림 -> Many To One 관계)

    @Column
    private String nickname;

    @Column
    private String body; // 댓글 내용

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생 시킨다.
        if (dto.getId() != null) { // 잘못된 id가 입력된 경우
            throw new IllegalArgumentException("댓글 생성 실패, 댓글의 id가 잘못 입력되었다.");
        }

        if(dto.getArticleId() != article.getId()){ // url에 던져진 articleId와 Json에 담겨진 id와 다른경우
            throw new IllegalArgumentException("댓글 생성 실패, 대상 게시글이 없다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()) { // url에서 던전 id와 Json으로 던진 id가 다른경우 예외 발생
            throw new IllegalArgumentException("댓글 수정 실패, 잘못된 id가 입력됨");
        }

        // 객체를 갱신
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }

        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
