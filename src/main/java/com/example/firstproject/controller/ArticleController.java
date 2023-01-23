package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 애너테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성한 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") // new.mustache에서 데이터를 post 방식으로 전송하기 때문에 컨트롤러에서 PostMapping으로 설정한다.
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString()); println으로 로깅을 하는 것은 서버 성능에도 좋지 않은 영향을 미치므로 실제 서버 구현 시 사용하지 않는다. -> logging으로 대체
        log.info(form.toString());
        /**
         <데이터를 db로 저장>
         * 1.dto를 Entity로 변환
         * 2. Repository에게 Entity를 DB내에 저장하게 한다.
         */

        // 1.dto를 Entity로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString()); // println으로 로깅을 하는 것은 서버 성능에도 좋지 않은 영향을 미치므로 실제 서버 구현 시 사용하지 않는다. -> logging으로 대체
        log.info(article.toString());

        // 2. Repository에게 Entity를 db에 저장하게 한다.
        Article saved = articleRepository.save(article);
 //       System.out.println(saved.toString());
        log.info(saved.toString());
        return "";

    }


}
