package com.example.firstproject.service;

import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링 부트와 연동되어 test 된다.
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test // 해당 메서드가 테스트를 위한 메서드임을 나타낸다.
    void index() {
        // 예상
        Article a = new Article(1L, "안녕하세요", "1111");
        Article b = new Article(2L, "감사해요", "2222");
        Article c = new Article(3L, "잘있어요", "3333");
        Article d = new Article(4L, "다시만나요", "4444");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c, d));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }
}