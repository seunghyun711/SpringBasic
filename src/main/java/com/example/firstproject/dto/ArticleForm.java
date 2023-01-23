package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// form 데이터를 받아올 그릇
@AllArgsConstructor // 이걸 쓰면 생성자 ArticleForm(String title, String content)를 쓴 것과 같은 효과
@ToString // toString()쓴 것과 같은 효과
// 위 두개의 기능은 롬복을 이용한 리펙토링이다.
public class ArticleForm {
    private String title;
    private String content;

//    public ArticleForm(String title, String content) {
//        this.title = title; // 제목
//        this.content = content; // 내용
//    }

//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
