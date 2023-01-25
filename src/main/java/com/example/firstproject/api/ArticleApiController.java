package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j

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

    // PATCH
    // 데이터 수정
    @PatchMapping("/api/articles/{id}")
    // ResponseEntity로 Article을 담아서 보낸다. -> 상태코드를 같이 실어서 보낼 수 있다.
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 1.수정용 Entity 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}",id,article.toString());

        // 2. 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나 id가 다른 경우)
        if (target == null || id != article.getId()) {
            // 잘못된 요청 응답
            log.info("잘못된 요청이다.id : {}, article : {}",id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400번대 오류

        }

        // 4. 정상 동작(200응답)
        target.patch(article); // 대상을 붙여준다. 받아온 article에 새롭게 바뀐것에 기존에 있던 것을 붙여준다.
        // ex) id, title,content에서 요청 시 id, content만 수정하면 title은 null 값이 된다. 62번 라인은 기존 title을 그대로 쓰게 해준다.
        Article updated = articleRepository.save(target); // 기존 데이터가 붙여진 target을 db에 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated); // ResponseEntity에 updated를 실어서 보낸다.
    }

    // DELETE
    // 데이터 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 삭제할 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 삭제
        articleRepository.delete(target);

        // 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
