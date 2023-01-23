package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// ArticleRepository는 CrudRepository에서 제공하는 기능을 사용 가능
// CrudRepository<관리대상 엔티티, 관리대상 엔티티의 대표값(id)의 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {
    // ArticleRepository에서 CrudRepository인터페이스의 메서드를 오버라이딩 한다.
    // findAll()메서드를 오버라이딩한다.

    @Override
    ArrayList<Article> findAll();
}
