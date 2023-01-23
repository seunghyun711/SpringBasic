package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식할 수 있게 해주는 애너테이션
// 리펙토링
@AllArgsConstructor // 모든 필드를 포함한 생성자를 자동으로 생성
@ToString
public class Article {

    @Id
    @GeneratedValue // 1,2,3... 자동 생성 애너테이션
    private Long id; // 데이터를 구분할 수 있는 번호
    @Column // db에서 관리하는 테이블과 연결할 수 있게 해주는 애너테이선
    private String title;

    @Column
    private String content;

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
