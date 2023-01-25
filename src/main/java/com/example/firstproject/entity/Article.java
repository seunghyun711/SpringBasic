package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식할 수 있게 해주는 애너테이션 (해당 클래스로 테이블을 만든다.)
// 리펙토링
@AllArgsConstructor // 모든 필드를 포함한 생성자를 자동으로 생성
@ToString
@NoArgsConstructor // 기본 생성자를 추가해주는 매너테이션
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3... 자동 생성 애너테이션 , db가 id를 자동 생성한다.
    private Long id; // 데이터를 구분할 수 있는 번호
    @Column // db에서 관리하는 테이블과 연결할 수 있게 해주는 애너테이선
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        // 있는 경우에만 갱신
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
