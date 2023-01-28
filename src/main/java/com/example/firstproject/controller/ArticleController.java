package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 애너테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성한 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;
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
        return "redirect:/articles/" + saved.getId(); // 리다이렉트

    }

    // id로 데이터 조회
    @GetMapping("/articles/{id}") // id위치에 들어가는 값은 변하는 수다.  id 값은 컨트롤러에서 받아와야함
    public String show(@PathVariable Long id, Model model){ //@PathVarible : url주소로 부터 id가 입력이 된다.
        log.info("id = " + id);
        /**
         * 데이터 조회 흐름
         * 1. id로 데이터를 가져온다.
         * 2. 가져온 데이터를 Model에 등록
         * 3. 보여줄 페이지를 설정
         */
        // 1. id로 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null); // id값을 통해 찾았는데 해당 id값이 없다면 null리턴
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 가져온 데이터를 Model에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }

    // 데이터 목록 조회
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 view로 전달
        model.addAttribute("articleList",articleEntityList);

        //3 .뷰 페이지 설정
        return "articles/index"; // articles/index.mustache

    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){ // id를 위 url경로에서 가져온디.
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article",articleEntity); // artilce/edit페이지에서 보여주기 위해 등록

        // 뷰 페이지 설정
        return "articles/edit"; // 수정할 데이터를 보여주는 페이지
    }

    // 데이터 수정
    @PostMapping("/articles/update")
    public String update(ArticleForm form, Long id){
        log.info(form.toString());

        // 1. DTO를 엔티디로 변환
        Article articleEntity = form.toEntity(); // 이거 안 하면 기존 Entity에 덮어쓰기 때문에 toEntity()사용해 새로운 Entity생성
        log.info(articleEntity.toString());

        // 2. 엔티디릴 DB로 저장
        // 2-1. db에 있는 기존 데이터를 가져온ㄷ.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null); // 데이터가 있으면 articleEntity가 연결되고 없으면 null이된다.


        // 2.1. 기존 데이터 값 갱신
        if(target!=null){
            articleRepository.save(articleEntity); // 엔티티가 db로 갱신된다.
        }

        // 3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    // 데이터 삭제
    // html에서 post,get외에 다른 메서드 제공 안함 따라서 삭제 시 임의호 get방식 사용
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){ // RedirectAttribute : 삭제된 경우 해당 클래스의 addFlashAttributes()메서드를 통해 리다이렉트 된 페이지에서 일회성으로 사용할 휘발성 데이터를 등록한다.
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온디.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        // 3. 결과 페이지로 리다이렉트한다.
        return "redirect:/articles";
    }
}
