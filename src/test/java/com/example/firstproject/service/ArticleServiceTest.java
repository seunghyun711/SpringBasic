package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
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

    @Test
    // ArticleService의 show()메서드 테스트
    void show_성공() {
        // given
        Long id = 1L;
        Article expected = new Article(id, "안녕하세요", "1111");

        // when
        Article article = articleService.show(id);

        // then
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패() { // 존재하지 않는 id를 입력한 경우
        // given
        Long id = -1L;
        Article expected = null;

        // when
        Article article = articleService.show(id);

        // then
        assertEquals(expected,article);
    }

    @Test
    @Transactional // create_성공()테스트를 통해 객체가 생성되는데 이걸 롤백해준다.
    void create_성공() { // title,content만 있는 dto입력
        // given
        String title = "sadlklawfkl";
        String content = "12415125r1251";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(5L,title,content);

        // when
        Article article = articleService.create(dto);

        // then
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_실패(){ // id가 포함된 dto가 입력된 경우
        // given
        String title = "wqreafs";
        String content = "weqlk21";
        ArticleForm dto = new ArticleForm(5L, title, content);
        Article expected = null;

        // when
        Article article = articleService.create(dto);

        // then
        assertEquals(expected,article);

    }

    @Test
    @Transactional
    void update_성공() { // 존재하는 id와 title,content가 있는 dto입력
        // given
        Article a = new Article(1L, "안녕하세요", "1111");
        // title, content 값 수정
        a.setTitle("안녕못해요");
        a.setContent("0101");
        ArticleForm dto = new ArticleForm(a.getId(),a.getTitle(),a.getContent());
        Article expected = new Article(1L, "안녕못해요", "0101");

        // when
        Article article = articleService.update(1L, dto);

        // then
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_실패() { // 존재하지 않는 id와 title,content가 있는 dto입력
        // given
        Article a = new Article(1L, "안녕하세요", "1111");
        // title, content 값 수정
        a.setTitle("안녕못해요");
        a.setContent("0101");
        ArticleForm dto = new ArticleForm(a.getId(),a.getTitle(),a.getContent());
        Article expected = null;

        // when
        Article article = articleService.update(5L,dto); // 존재하지 않은 id 값에 대해 update하므로 null 리턴

        // then
        assertEquals(expected,article);
    }

    @Test
    void update_실패_onlyId() { // id만 있는 dto입력
        // 테스트 실패 -> ArticleService클래스의 update()메서드를 id만 있는 경우 null을 리턴하게 하여 update를 실패하게 수정하였다.
        // given
        Article a = new Article(1L, "안녕하세요", "1111");
        // title, content 값 수정
        ArticleForm dto = new ArticleForm(a.getId(),null,null); // title, content 모두 null
        Article expected = null;

        // when
        Article article = articleService.update(a.getId(), dto); // 존재하지 않은 id 값에 대해 update하므로 null 리턴


        // then
        assertEquals(expected,article);

    }

    @Test
    void delete_성공() { // 존재하는 id 입력
        // given
        Article a = new Article(1L, "안녕하세요", "1111");
        Article expected = a;

        // when
        Article article = articleService.delete(a.getId());

        // then
        assertEquals(expected.toString(), article.toString()); // delete의 결과는 삭제된 Article

    }

    @Test
    void delete_실패() { // 존재하지 않는 id 입력
        // given
        Article a = new Article(1L, "안녕하세요", "1111");
        Article expected = null;

        // when
        Article article = articleService.delete(8L); // 존재하지 않는 id 입력

        // then
        assertEquals(expected, article); // delete의 결과는 삭제된 Article

    }
}