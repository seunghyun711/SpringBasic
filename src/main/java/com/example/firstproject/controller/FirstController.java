package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 해당 자바 파일이 컨트롤러임을 나타내는 애너테이션
public class FirstController {
    @GetMapping("/hi") // 접속할 url을 설정 -> /hi 라는 url을 받을 때 return으로 greetings.mustahce를 연결시켜준다.
    public String niceToMeetYou(Model model) { //  modeld=을 통해 데이터 처리 -> 템플릿 변수를 사용하기 위해
        model.addAttribute("username", "홍승현"); // hello.mustache파일의 username에 "홍승현"이라는 값을 넣어준다.
        return "greetings"; // templates/greetings.mustahce를 찾아서 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeYouAgain(Model model) {
        model.addAttribute("nickname","hongdangmoo");
        return "goodBye"; // goodBye는 templates에서 goodBye.mustache를 찾는다.
    }
}
