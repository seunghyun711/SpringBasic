package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j

@RestController // RestAPI용 컨트롤러 데이터(JSON형식) 반환
public class ArticleApiController {
    @Autowired // DI, 생성 객체를 가져와서 연결
    private ArticleService articleService; // 컨트롤러와 리포지토리 사이의 처리는 ArticeService가 하게하여 컨트롤러는 컨트롤러의 기능만 하도록 구현한다.
    // GET
    // 전체 데이터 조회
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    // 특정 데이터 가져오기(id로)
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){ //url 요청을 통해 id값 가져올 떄 pathvaraible사용
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ //url 요청을 통해 id값 가져올 떄 pathvaraible사용
        // 이대로 하면 title이랑 content가 null값이다. RestAPI에서 JSON으로 데이터 전달 시 데이터가 받아지지 음
        // 따라서 @RequestBody를 이용해 Json데이터를 받아오게 한다.
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    // 데이터 수정
    @PatchMapping("/api/articles/{id}")
    // ResponseEntity로 Article을 담아서 보낸다. -> 상태코드를 같이 실어서 보낼 수 있다.
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto); // service에 update요청
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):// ResponseEntity에 updated를 실어서 보낸다.
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    // 데이터 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        // 데이터 반환
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 : 반드시 성공해야하는 일련의 과정 실패시 롤백함
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList!=null) ? ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
