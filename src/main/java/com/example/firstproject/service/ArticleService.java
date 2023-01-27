package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언(서비스 객체를 스프링 부트에 생성한다.)
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        // 기존에 있는 id 값을 post요청으로 데이터를 처리하면 기존 id에 있던 데이터가 덮어쓰여진다. 아래는 이를 방지하는 코드다.
        if(article.getId() != null){ // 이미 존재하는 id면 null return
            return null; // 이제 기존에 있는 id에 데이터를 처리하면 400 에러 발생한다.
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}",id,article.toString());

        // 2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나 id가 다른경우)
        if (target == null || id != article.getId()) {
            log.info("id : {}, article : {}",id,article.toString());
            return null; // 잘못되면 null 리턴
        }

        // 수정 시 title, content 모두 변경되지 않은 경우(둘다 null인 경우) null 리턴한다.
        if(article.getTitle() == null && article.getContent() == null){
            return null;
        }

        // 4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        // 삭제할 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 해당 메서드를 트렌젝션으로 묶는다. 따라서 해당 메서드 수행 중 실패하면 실패한 메서드가 수행되기 이전 상태로 초기화
    // transactional을 안하면 오류 발생시 데이터가 그냥 저장이 됨, Transactional붙이면 데이터 저장 중 실패 시 기존 값으로 되돌아감(데이터가 저장도지 않음)
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto묶음을 엔티티 묶음으로 변환 (stream 버전)
        List<Article> articleList = dtos.stream().map(dto->dto.toEntity())
                .collect(Collectors.toList()); // 리스토로 엔티티로 묶는다.

        // dto묶음을 엔티티 묶음으로 변환 (for문 버전)
//        List<Article> articleList = new ArrayList<>();
//        for (int i = 0; i < dtos.size(); i++) {
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // 엔티티 묶음을 db로 저장 (stream 버전)
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 엔티티 묶음을 db로 저장 (for 버전)
//        for (int i = 0; i < articleList.size(); i++) {
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow( // 강제로 예외 발생 시킨다.
                ()->new IllegalArgumentException("결제 실패")
        );

        // 결과값 반환
        return articleList;
    }
}
