package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

// ArticleRepository는 CrudRepository에서 제공하는 기능을 사용 가능
// CrudRepository<관리대상 엔티티, 관리대상 엔티티의 대표값(id)의 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
