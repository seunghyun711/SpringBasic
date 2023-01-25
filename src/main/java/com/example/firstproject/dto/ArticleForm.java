package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

// form 데이터를 받아올 그릇
@AllArgsConstructor // 이걸 쓰면 생성자 ArticleForm(String title, String content)를 쓴 것과 같은 효과
@ToString // toString()쓴 것과 같은 효과
@Getter
@NoArgsConstructor // 이게 없으면 ArticleContrller(api)에서 post로 api/articles/ title,content 정보를 보내면 500 에러 발생 why?
public class ArticleForm {

    private Long id; // 필드에 id 추가
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
        return new Article(id, title, content); // 필드에 id가 추가 되었기 때문에 생성자 매개변수에 null을 id로 변경
    }
}
