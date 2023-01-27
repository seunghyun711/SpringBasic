package com.example.firstproject.entity;

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

}
