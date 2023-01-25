package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RestAPI용 컨트롤러 데이터(JSON형식) 반환
public class ArticleApiController {
    @Autowired // ArticleRepository를 가져온다.
    private ArticleRepository articleRepository;
    // GET
    // 전체 데이터 조회
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    // 특정 데이터 가져오기(id로)
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){ //url 요청을 통해 id값 가져올 떄 pathvaraible사용
        return articleRepository.findById(id).orElse(null);
    }

    // POST 
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ //url 요청을 통해 id값 가져올 떄 pathvaraible사용
        // 이대로 하면 title이랑 content가 null값이다. RestAPI에서 JSON으로 데이터 전달 시 데이터가 받아지지 음
        // 따라서 @RequestBody를 이용해 Json데이터를 받아오게 한다.
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

}
